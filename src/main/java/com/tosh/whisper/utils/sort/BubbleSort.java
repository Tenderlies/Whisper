package com.tosh.whisper.utils.sort;

public class BubbleSort implements Sort
{
    
    @Override
    public void sort(int[] arr)
    {
        bubbleSort(arr);
    }
    
    private void bubbleSort(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = 0; j < arr.length - i - 1; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    arr[j + 1] += arr[j];
                    arr[j] = arr[j + 1] - arr[j];
                    arr[j + 1] = arr[j + 1] - arr[j];
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        int[] arr = { 9, 1, 6, 4, 3, 5, 2 };
        Sort.sortByAlgorithm(arr, MergeSort.class);
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }
    }
    
}
