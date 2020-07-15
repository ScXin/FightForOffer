package java_basic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ScXin
 * @date 8/31/2019 7:22 PM
 */
public class VolatileTest {

    static  class VolatileClass{
        private volatile int cnt=0;
        public void add(){
            cnt++;
        }public int get(){
            return cnt;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int threadSize=10000;
        VolatileClass volatileClass=new VolatileClass();
        final CountDownLatch countDownLatch=new CountDownLatch(threadSize);
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<threadSize;i++){
            executorService.execute(()->{volatileClass.add();countDownLatch.countDown();});
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(volatileClass.get());
    }
}
