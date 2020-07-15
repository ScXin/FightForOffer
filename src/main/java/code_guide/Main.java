package code_guide;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ScXin
 * @date 9/23/2019 7:21 PM
 */
//public class Main implements Serializable;
//    public static void main(String[] args) {
////        Map<Integer,Integer>map=new HashMap<>();
////        int output=1;
////        String str=null;
////        if((output+=1)==2||str.equals("")){
////            System.out.println("we are equals"+output);
////        }else{
////            System.out.println("hha");
////        }
////        int i=3;
////        int count=(i++)+(i++)+(i++);
////        System.out.println(i);
////        System.out.println(count);
////        System.out.println(Math.round(11.5));
////        System.out.println(Math.round(-11.5));
//
//        System.out.println(11.5);
//
//    }
//}


public class Main {
    static class Producer implements Runnable {
        CountDownLatch countDownLatch1;
        CountDownLatch countDownLatch2;

        public Producer(CountDownLatch countDownLatch1, CountDownLatch countDownLatch2) {
            this.countDownLatch1 = countDownLatch1;
            this.countDownLatch2 = countDownLatch2;
        }

        @Override
        public void run() {
            try {
                countDownLatch1.await();
                System.out.println(Thread.currentThread());
                countDownLatch2.countDown();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch1 = new CountDownLatch(0);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        CountDownLatch countDownLatch3 = new CountDownLatch(1);
        CountDownLatch countDownLatch4 = new CountDownLatch(1);
        Thread producer1Thread1 = new Thread(new Producer(countDownLatch1, countDownLatch2));
        Thread producer1Thread2 = new Thread(new Producer(countDownLatch2, countDownLatch3));
        Thread producer1Thread3 = new Thread(new Producer(countDownLatch3, countDownLatch4));
        Thread producer1Thread4 = new Thread(new Producer(countDownLatch4, countDownLatch4));
        producer1Thread1.start();
        producer1Thread2.start();
        producer1Thread3.start();
        producer1Thread4.start();
    }
}