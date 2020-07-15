package code_guide;

/**
 * @author ScXin
 * @date 9/18/2019 8:14 AM
 */
public class Sort15 {

    //quick sort
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSortMethod(arr, 0, arr.length - 1);
    }

    public static void quickSortMethod(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = partition(arr, lo, hi);
        quickSortMethod(arr, lo, mid - 1);
        quickSortMethod(arr, mid + 1, hi);
    }

    public static int partition(int[] arr, int left, int right) {
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

    //heapSort
    public static void heapSort(int[] arr) {
        int N = arr.length;
        for (int i = N / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, N);
        }
        for (int j = N - 1; j > 0; j--) {
            swap(arr, 0, j);
            adjustHeap(arr, 0, j);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void adjustHeap(int[] arr, int k, int N) {
        int temp = arr[k];
        for (int i = 2 * k + 1; i < N; i = 2 * i + 1) {
            if (i + 1 < N && arr[i] < arr[i + 1]) {
                i++;
            }
            if (arr[i] < temp) {
                break;
            }
            arr[k] = arr[i];
            k = i;
        }
        arr[k] = temp;
    }


    // mergeSort
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        mergeSortMethod(arr, 0, arr.length - 1);
    }

    public static void mergeSortMethod(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) >> 1;
        mergeSortMethod(arr, lo, mid);
        mergeSortMethod(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] aux = new int[arr.length];
        for (int i = left; i <= right; i++) {
            aux[i] = arr[i];
        }
        int k = left;
        int l = mid + 1;
        for (int m = left; m <= right; m++) {
            if (k > mid) {
                arr[m] = aux[l++];
            } else if (l > right) {
                arr[m] = aux[k++];
            } else {
                if (aux[k] <= aux[l]) {
                    arr[m] = aux[k++];
                } else {
                    arr[m] = aux[l++];
                }
            }
        }
    }

    //mergeSortFromDownToTop

    public static void mergeFromDownToTop(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int N = arr.length;
        for (int sz = 1; sz < N; sz++) {
            for (int j = 0; j < N - sz + 1; j += sz + sz) {
                merge(arr, j, j + sz - 1, Math.min(j + sz + sz - 1, N - 1));
            }
        }
    }

    // insertSort

    public static void insertSort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    //selectsort
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N - 1; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }

    //bubbleSort
    public static void bubbleSort(int[] arr) {
        int N = arr.length;
        if (arr == null || N <= 1) {
            return;
        }
        for (int i = N - 1; i >= 0; i--) {
            boolean swap = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j + 1, j);
                    swap = true;
                }
            }
            if (!swap) {
                return;
            }
        }
    }



    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, -7, -6, -7, -8, 4, 5, 6, 9, -8, -3, -4, -2};
//        quickSort(arr);
//        heapSort(arr);
//        mergeSort(arr);
//        mergeFromDownToTop(arr);
//        insertSort(arr);
//        selectSort(arr);
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
