package java_basic;



import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ScXin
 * @date 9/2/2019 3:57 PM
 */
public class DeadLockTest {

    private static String lock1 = "lock1";
    private static String lock2 = "lock2";

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,3,60L, TimeUnit.SECONDS,new LinkedBlockingDeque<>(2));
        DeadLockTest deadLockTest=new DeadLockTest();
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread() + "拿到第一把锁");
                        try {
                            Thread.sleep(10);
                            synchronized (lock2) {
                                System.out.println(Thread.currentThread() + "拿到第二把锁");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    System.out.println(Thread.currentThread() + "拿到第二把锁");
                    try {
                        Thread.sleep(10);
                        synchronized (lock1) {
                            System.out.println(Thread.currentThread() + "拿到第一把锁");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
