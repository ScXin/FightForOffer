package rmicroZhong;

/**
 * @author ScXin
 * @date 10/16/2019 9:54 PM
 */

//insert Sort
public class Main {

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;                        
        }
        int N = arr.length;
        for (int j = 1; j < N; j++) {
            int key = arr[j];
            int i = j - 1;
            while (i >= 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i = i - 1;
            }
            arr[i + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 9, 10,-5, -5,5, 5, -4, -4, -3, -1, 0, 1, 5,  6, 6, 6, 7, 7, 3, 11};
        int N = arr.length;
        insertSort(arr);
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
