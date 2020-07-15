package java_revise;

import java.util.*;
import java.security.SecureRandom;

/**
 * @author ScXin
 * @date 8/6/2019 7:31 PM
 */
public class Solution {
    /**
     * 从小到大
     * 写一个堆排序
     * 堆排序的最坏的时间复杂度是:NlogN
     * 最好的时间复杂度:0(NlogN)
     * 平均时间复杂度:Nlog(N)
     * 空间复杂度是O(1)
     * 件堆的时间复杂度为O(N)
     * 调整堆的时间复杂度是O(LogN)
     * 不稳定算法
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapSort(int[] arr) {

        int N = arr.length;
        for (int i = N / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, N);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            adjustHeap(arr, 0, j);
            swap(arr, 0, j);
        }
    }

    public static void adjustHeap(int[] arr, int k, int N) {
        int temp = arr[k];
        for (int i = 2 * k + 1; i < N; i = i * 2 + 1) {
            if (i + 1 < N && arr[i] < arr[i + 1]) {
                i++;
            }
            if (arr[i] <= temp) {
                break;
            }
            arr[k] = arr[i];
            k = i;
        }
        arr[k] = temp;
    }

    /**
     * 堆拍序（从大到小）
     *
     * @param args
     */
    public static void heapSort2(int[] arr) {

        int N = arr.length;
        for (int i = N / 2 - 1; i >= 0; i--) {
            adjustHeap2(arr, i, N);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            adjustHeap2(arr, 0, j);
            swap(arr, 0, j);
        }
    }

    public static void adjustHeap2(int[] arr, int k, int N) {
        int temp = arr[k];
        for (int i = 2 * k + 1; i < N; i = i * 2 + 1) {
            if (i + 1 < N && arr[i] > arr[i + 1]) {
                i++;
            }
            if (arr[i] >= temp) {
                break;
            }
            arr[k] = arr[i];
            k = i;
        }
        arr[k] = temp;
    }

    /**
     * 快排
     * >最坏时间复杂度：O(n^2)
     * 平均时间复杂度：O(NlogN)
     * 最好时间复杂度: O(NlogN)
     * 空间复杂度:O(1)
     * 稳定性:不稳定
     *
     * @paam args
     */
    public static void quickSort(int[] arr) {
        quickSortMethod(arr, 0, arr.length - 1);
    }

    public static void quickSortMethod(int[] arr, int i, int j) {
        if (i >= j) {
            return;
        }
        int mid = helper(arr, i, j);
        quickSortMethod(arr, i, mid - 1);
        quickSortMethod(arr, mid + 1, j);
    }

    public static int helper(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] > temp) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
                left++;
            }
            while (left < right && arr[left] <= temp) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = temp;
        return left;
    }

    /**
     * 示例 1：
     * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出：9
     * 示例 2：
     * 输入：grid = [[1,1,0,0]]
     * 输出：1
     *
     * @param grid
     * @return
     */
    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] left = new int[n][m];
        int[][] up = new int[n][m];

        int res = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (i == 0) up[i][j] = (grid[i][j] == 1 ? 1 : 0);
                else up[i][j] = (grid[i][j] == 1 ? 1 + up[i - 1][j] : 0);

                if (j == 0) left[i][j] = (grid[i][j] == 1 ? 1 : 0);
                else left[i][j] = (grid[i][j] == 1 ? 1 + left[i][j - 1] : 0);


                for (int k = 1; k <= Math.min(i, j) + 1; k++) {
                    if (left[i][j] < k) continue;
                    if (up[i][j] < k) continue;

                    if (left[i - k + 1][j] < k) continue;
                    if (up[i][j - k + 1] < k) continue;
                    res = Math.max(res, k * k);
                }
            }
        return res;
    }

    /**
     * 非递归实现快速排序
     *
     * @param args
     */
    public static void quickSortWithoutRecursive(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int l = 0;
        int r = arr.length - 1;
        stack.push(r);
        stack.push(l);
        if (l < r) {
            while (!stack.isEmpty()) {
                int lo = stack.pop();
                int hi = stack.pop();
                int mid = partiotion(arr, lo, hi);
                if (hi > mid + 1) {
                    stack.push(hi);
                    stack.push(mid + 1);
                }
                if (lo < mid - 1) {
                    stack.push(mid - 1);
                    stack.push(lo);
                }
            }
        }
    }

    public static int partiotion(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] > temp) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
                left++;
            }
            while (left < right && arr[left] <= temp) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = temp;
        return left;
    }

    public static int findDuplicate(int[] arr) {
        int k = 0;
        while (k < arr.length) {
            int m = arr[k];
            if (k == m) {
                k++;
            } else {
                if (arr[k] == arr[arr[k]]) {
                    return arr[k];
                } else {
                    swap(arr, k, m);
                }
            }
        }
        return 0;
    }


    public static int calculateTime(String keyboard, String word) {
        int po = 0;
        int dis = 0;
        for (int i = 0; i < word.length(); i++) {
            int k = keyboard.indexOf(word.charAt(i));
            System.out.println("k==" + k);
            dis += Math.abs(k - po);
            po = k;
        }
        return dis;
    }


    private static SecureRandom secureRandom = new SecureRandom();

    /**
     * 求解最大状态值
     * 7 2 4 5 6
     * 选择4 5 6 最大状态值15*4=60
     * 采用分治法，能够很好的处理这个问题
     *
     * @param scores
     * @return
     */

    public static int getBestStatus(int[] scores, int lo, int hi, int[] sum) {
        if (lo == hi) {
            return scores[lo] * scores[lo];
        }
        if (lo > hi) {
            return 0;
        }
        int maxStatusVal = 0;
        int minVal = Integer.MAX_VALUE;
        int minIndex = lo;
        int i = lo;
        while (i <= hi) {
            if (scores[i] < minVal) {
                minVal = scores[i];
                minIndex = i;
            }
            i++;
        }
        maxStatusVal = minVal * (sum[hi] - sum[lo] + scores[lo]);
        int loVal = getBestStatus(scores, lo, minIndex - 1, sum);
        int hiVal = getBestStatus(scores, minIndex + 1, hi, sum);
        System.out.println("maxStatusVal==" + maxStatusVal);
        return Math.max(maxStatusVal, Math.max(loVal, hiVal));
    }

    /**
     * 搬运工问题
     *
     * @param args worker number:m
     *             offices number: n
     *             item number in every office
     */
    public static int calculate(String s) {

        char[] str=s.toCharArray();
        String re="";
        for(int m=0;m<str.length;m++)
        {
            if(str[m]==' ')
            {
                continue;
            }
            else
            {
                re+=str[m];

            }
        }
        Stack<Integer> number = new Stack<>();
        Stack<Character> operator = new Stack<>();
        int k = 0;
        int length = re.length();
        /**
         * "3*5-6+8/2"
         */
        while (k < length) {
            System.out.println("k=="+k);
            if (Character.isDigit(re.charAt(k))) {
                int po = k;
                while (po < length && Character.isDigit(re.charAt(po))) {
                    po++;
                }
                Integer.parseInt(re.substring(k, po));
                number.push(Integer.parseInt(re.substring(k, po)));
                k = po ;

            } else {

                if (re.charAt(k) == '-') {
                    operator.push('-');
                    k++;
                }
                if (re.charAt(k) == '+') {
                    operator.push('+');
                    k++;
                }
                if (re.charAt(k) == '*') {
                    int po = k + 1;

                    while (po < length && Character.isDigit(re.charAt(po))) {
                        po++;
                    }
                    int multiNum = Integer.parseInt(re.substring(k + 1, po));
                    int value = multiNum * number.pop();
                    number.push(value);
                    k = po ;
                }
                if (re.charAt(k) == '/') {
                    int po = k + 1;

                    while (po < length && Character.isDigit(re.charAt(po))) {
                        po++;
                    }
                    int divideNum = Integer.parseInt(re.substring(k + 1, po));
                    int value = number.pop() / divideNum;
                    number.push(value);
                    k = po ;
                }
            }

        }
        while (!operator.isEmpty()) {

            Character oper = operator.pop();

            if (oper == '+') {
                if(!operator.isEmpty()&&operator.peek()=='-')
                {
                    int first=number.pop();
                    int second=number.pop();
                    int res =  first-second;

                    number.push(res);
                    operator.pop();
                    operator.push('+');
                }
                else
                {

                int res = number.pop() + number.pop();
                number.push(res);
            }
        }

            if (oper == '-') {
                if(!operator.isEmpty()&&operator.peek()=='-')
                {
                    int first=number.pop();
                    int second=number.pop();
                    int res =  second+first;
                    number.push(res);
                }
                else {
                    int second = number.pop();
                    int first = number.pop();
                    int res = first - second;
                    number.push(res);
                }
            }
        }
        return number.pop();
    }

    public static String concatString(String s1,String s2,String s3){
        StringBuffer sb=new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        return sb.toString();
    }


    public static void main(String[] args) {
//        int[] arr = {-4, -6, -1, 5, 4, 3, 1, 3};
//        quickSortWithoutRecursive(arr);
//        heapSort(arr);
//        heapSort2(arr);
//        quickSort(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + "  ");
//        }

//        byte[] bytes=new byte[4];
//        int k=0;
//        while(k<100){
//            secureRandom.nextBytes(bytes);
//            for(int i=0;i<bytes.length;i++){
//                System.out.print(bytes[i]+" ");
//            }
//            System.out.println();
//            k++;
//        }
//        String keyboard = "abcdefghijklmnopqrstuvwxyz";
//        String word = "cba";
//        System.out.println(calculateTime(keyboard,word));
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int days = Integer.parseInt(in.nextLine());
//            int[] scores = new int[days];
//            String[] score = in.nextLine().split(" ");
//            int[] sum = new int[days];
//            int befVal = 0;
//            for (int i = 0; i < days; i++) {
//                scores[i] = Integer.parseInt(score[i]);
//                sum[i] = befVal + scores[i];
//                befVal = sum[i];
//            }
//            System.out.println(getBestStatus(scores,0,scores.length-1,sum));
//        }

    }
}
