package leetcode;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

/**
 * @author ScXin
 * @date 9/27/2019 4:40 PM
 */
public class Main {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> ret = new ArrayList<>();
        if (arr.length <= 1) {
            return ret;
        }
        Arrays.sort(arr);
        int N = arr.length - 1;
        int min = arr[1] - arr[0];
        for (int i = 2; i <= N; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }
        for (int i = 1; i <= N; i++) {
            if (arr[i] - arr[i - 1] == min) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                ret.add(list);
            }
        }
        return ret;
    }

    public static int nthUglyNumber(int n, int a, int b, int c) {
        int i = 1;
        int j = 1;
        int k = 1;
        int val=0;
        while(n-->0){
            if(i*a<b*j&&i*a<k*c){
                val=i*a;
                i++;
                continue;
            }
            if(b*j<i*a&&b*j<k*c){
                val=j*b;
                j++;
                continue;
            }
            if(c*k<i*a&&c*k<j*b){
                val=c*k;
                k++;
                continue;
            }
            if(a*i==b*j){
                if(a*i<c*k){
                    val=i*a;
                    i++;
                    j++;
                    continue;
                }
                if(a*i==c*k){
                    val=i*a;
                    i++;
                    j++;
                    k++;
                    continue;
                }
                if(c*k<a*i){
                    val=k*c;
                    k++;
                    continue;
                }
            }
            if(a*i==c*k){
                if(b*j==a*i){
                    val=a*i;
                    i++;
                    j++;
                    k++;
                    continue;
                }
                if(b*j<a*i){
                    val=b*j;
                    j++;
                    continue;
                }
                if(b*j>a*i){
                    val=a*val;
                    i++;
                    k++;
                }
            }
            if(b*j==c*k){
                if(a*i==b*j){
                    val=a*i;
                    i++;
                    j++;
                    k++;
                    continue;
                }
                if(a*i<b*j){
                    val=a*i;
                    i++;
                    continue;
                }
                if(a*i>b*j){
                    val=b*j;
                    j++;
                    k++;
                    continue;
                }
            }

        }
        return val;
    }

    public static void main(String[] args) {
       int n= 1000000000;
        int a=2;
        int b=217983653;
        int c=336916467;
        System.out.println(nthUglyNumber(n,a,b,c));
    }

}
