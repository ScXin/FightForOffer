/**
 * @author ScXin
 * @date 5/10/2019 3:47 PM
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用BlockingQueue实现生产者消费者问题
 */
public class ProducerConsumer {
    static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

    public static class Producer extends Thread {
        @Override
        public void run() {
            try {
                queue.put("porduce");
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("producing...");
            }
        }
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                String consume = queue.take();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("consume...");
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer();
            producer.start();
        }


        System.out.println("hahahahhah");
        for (int i = 0; i < 6; i++) {
            Consumer consumer = new Consumer();
            consumer.start();
        }
        for (int i = 0; i < 4; i++) {
            Producer producer = new Producer();
            producer.start();
        }
    }


    /**
     * 其他方法实现生产者消费者模式
     */

}
