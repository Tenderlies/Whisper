package com.tosh.whisper.utils.sort;

/**
 * 快速排序<br>
 * ClassName: QuickSort<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2017年6月7日
 */
public class QuickSort implements Sort
{
    @Override
    public void sort(int[] arr)
    {
        quickSort(arr, 0, arr.length);
    }
    
    private void quickSort(int arr[], int firstIndex, int length)
    {
        
        // 定义左游标
        int leftIndex = firstIndex;
        // 定义右游标
        int rightIndex = length - 1;
        // 提取需处理的基数
        int flag = arr[firstIndex];
        
        if (firstIndex < length - 1)
        {
            
            while (leftIndex < rightIndex)
            {
                
                // 当右游标位置的数比基数小时,将此数赋值给:基数在数组位置上的数/左游标位置空出来的数
                // 此时可以理解为右游标位置的数为空(未处理);
                while (leftIndex < rightIndex && arr[rightIndex] >= flag)
                {
                    rightIndex--;
                }
                if (leftIndex < rightIndex)
                {
                    arr[leftIndex++] = arr[rightIndex];
                }
                
                // 当左游标位置的数比基数大时,将此数赋值给:右游标位置空出来的数
                // 此时可以理解为左游标位置的数为空(未处理);
                while (leftIndex < rightIndex && arr[leftIndex] < flag)
                {
                    leftIndex++;
                }
                if (leftIndex < rightIndex)
                {
                    arr[rightIndex--] = arr[leftIndex];
                }
            }
            
            // 将提取出来的基数赋值给最后空的游标上
            arr[leftIndex] = flag;
            
            // 递归调用
            // 左区间排序
            if (firstIndex <= leftIndex - 1)
            {
                quickSort(arr, firstIndex, leftIndex);
            }
            // 右区间排序
            if (leftIndex + 1 <= length - 1)
            {
                quickSort(arr, leftIndex + 1, length);
            }
        }
    }
    
}