package java_basic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author ScXin
 * @date 8/31/2019 9:40 PM
 */
public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threshold=5;
    private int first;
    private int last;
    public ForkJoinExample(int first,int last){
        this.first=first;
        this.last=last;
    }
    protected Integer compute(){
        int result=0;
        if(last-first<=threshold){
            for(int i=first;i<=last;i++){
                result+=i;
            }
        }else{
            int middle=first+((last-first)>>1);
            ForkJoinExample leftTask=new ForkJoinExample(first,middle);
            ForkJoinExample rightTask=new ForkJoinExample(middle+1,last);
            leftTask.fork();
            rightTask.fork();
            result=leftTask.join()+ rightTask.join();
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException,InterruptedException {
        ForkJoinExample example=new ForkJoinExample(1,100);
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        Future result=forkJoinPool.submit(example);
        System.out.println(result.get());
    }
}
