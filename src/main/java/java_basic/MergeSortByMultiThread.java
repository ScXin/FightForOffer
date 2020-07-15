package java_basic;


import java.util.concurrent.CountDownLatch;

/**
 * @author ScXin
 * @date 8/31/2019 10:13 PM
 */
public class MergeSortByMultiThread {
    private static int [] aux1;
    private static int [] aux2;
    static class MergeClass implements Runnable{
        private int left;
        private int right;
        private CountDownLatch countDownLatch;
        public MergeClass(int left, int right, CountDownLatch countDownLatch){
            this.left=left;
            this.right=right;
            this.countDownLatch=countDownLatch;
        }
        @Override
        public void run(){
            mergeSortMethod(aux2,left,right);
            System.out.println("left=="+left+"right=="+right);
            countDownLatch.countDown();
        }
    }
    public static void mergeSortMethod(int[]arr,int lo,int hi){
        if(lo>=hi){
            return;
        }
        int mid=(lo+hi)>>1;
        mergeSortMethod(arr,lo,mid);
        mergeSortMethod(arr,mid+1,hi);
        merge2(arr,lo,mid,hi);
    }
    public static void merge(int[]arr,int left,int mid,int right){
        int k=left;
        int l=mid+1;
        for(int i=left;i<=right;i++){
            if(k>mid){
                arr[i]=aux1[l++];
            }else if(l>right){
                arr[i]=aux1[k++];
            }else{
                if(aux1[k]<=aux1[l]){
                    arr[i]=aux1[k++];
                }else{
                    arr[i]=aux1[l++];
                }
            }
        }
    }
    public static void merge2(int[]arr,int left,int mid,int right){
        int[]aux=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            aux[i]=arr[i];
        }
        int k=left;
        int l=mid+1;
        for(int i=left;i<=right;i++){
            if(k>mid){
                arr[i]=aux[l++];
            }else if(l>right){
                arr[i]=aux[k++];
            }else{
                if(aux[k]<=aux[l]){
                    arr[i]=aux[k++];
                }else{
                    arr[i]=aux[l++];
                }
            }
        }
    }

    public static void main(String[] args) {
        aux1=new int[100];
        aux2=new int[100];
        for(int i=0;i<aux1.length;i++){
            int rand=(int)(Math.random()*100);
            aux1[i]=rand;
            aux2[i]=rand;
        }
        int j=0;
        int threadNum=4;
        CountDownLatch latch=new CountDownLatch(threadNum);
        for(int s=0;s<threadNum;s++){
            Thread thread=new Thread(new MergeClass(j,j+24,latch));
            thread.start();
            j=j+25;
        }
        try{
            latch.await();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("多线程排序结束!!!");

        int lo=0;
        int mid=-1;
        int hi;
        for(int k=0;k<threadNum-1;k++){
            mid=25+mid;
            hi=25+mid;
            merge2(aux2,lo,mid,hi);
        }
        for(int m=0;m<aux2.length;m++){
            if((m+1)%25==0){
                System.out.println(aux2[m]+" ");
            }
            else{
                System.out.print(aux2[m]+" ");

            }
        }

    }
}
