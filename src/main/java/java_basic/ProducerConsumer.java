package java_basic;



import javax.security.auth.login.Configuration;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author ScXin
 * @date 9/1/2019 4:36 PM
 */
public class ProducerConsumer {


    private static String object = "lock";
    /**
     * 基于blockQueue实现的生产者消费者
     */
//    private static BlockingQueue blockingQueue = new ArrayBlockingQueue(5);
//
//    static class Produce implements Runnable {
//        @Override
//        public void run() {
//            try {
//                blockingQueue.put("produce");
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            } finally {
//                System.out.println("producing...");
//            }
//        }
//    }
//
//    static class Consumer implements Runnable {
//        @Override
//        public void run() {
//            try {
//                blockingQueue.take();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            } finally {
//                System.out.println("Consuming...");
//            }
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        for(int i=0;i<2;i++){
//            Thread thread=new Thread(new Consumer());
//            thread.start();
//        }
//        for(int k=0;k<4;k++){
//            Thread thread=new Thread(new Consumer());
//            thread.start();
//        }
//        System.out.println("ready for priducing!");
//        for(int j=0;j<6;j++){
//            Thread thread=new Thread(new Produce());
//            thread.start();
//            Thread.sleep(2000);
//        }
//
    private static int maxSize = 10;
    private static Queue<Integer> queue = new LinkedList<>();

    /**
     * 基于wait,notify方法实现
     */
    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("当前产品个数为: " + queue.size());
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    System.out.println("当前产品个数为: " + queue.size());
                    queue.add((int) Math.random() * 10);
                    System.out.println("生产了一个产品，当前产品个数为: " + queue.size());
                    queue.notify();
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("当前产品为零");
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    System.out.println("当前产品个数为: " + queue.size());
                    queue.poll();
                    System.out.println("消费了一个产品，当前产品个数为: " + queue.size());
                    queue.notify();
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new Producer());
            thread.start();
        }
        for (int k = 0; k < 20; k++) {
            Thread thread = new Thread(new Consumer());
            thread.start();
        }




    }
}
