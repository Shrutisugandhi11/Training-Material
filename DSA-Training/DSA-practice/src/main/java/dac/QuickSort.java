package dac;


import java.util.Arrays;

public class QuickSort {
    public static void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition(int low, int high, int[] arr) {
        int pivot = low;
        while (low < high) {
            while (arr[low] <= arr[pivot]) {
                low++;
            }
            while (arr[high] > arr[pivot]) {
                high--;
            }
            if(arr[low]<arr[high])
                swap(low,high,arr);
        }
        swap(pivot, high, arr);
        return high;
    }

    public static void quickSort(int low, int high, int[] arr) {
        if (low<high){
            int pivot=partition(low,high,arr);
            quickSort(low,pivot-1,arr);
            quickSort(pivot+1,high,arr);
        }
    }

    public static void main(String[] args) {
        int []arr={10,5,8,6,15,12,16};
        quickSort(0, arr.length-1,arr);
        System.out.println(arr);

    Arrays.stream(arr).forEach(System.out::println);


    }
}