package com.tosh.whisper.utils.sort;

/**
 * 归并排序<br>
 * ClassName: MergeSort<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2017年6月7日
 */
public class MergeSort implements Sort
{
    @Override
    public void sort(int[] arr)
    {
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private void mergeSort(int[] array, int firstIndex, int lastIndex)
    {
        // 拆分为最小单元格时,则不拆分
        if (firstIndex < lastIndex)
        {
            int middle = (firstIndex + lastIndex) / 2;
            // 递归处理拆分数组
            mergeSort(array, firstIndex, middle);
            mergeSort(array, middle + 1, lastIndex);
            // 开始合并
            merge(array, firstIndex, middle, lastIndex);
        }
    }
    
    private void merge(int[] array, int leftIndex, int middle, int rightIndex)
    {
        // 创建一个临时数组用来存储合并后的数据
        int[] temp = new int[array.length];
        int leftFirstIndex = leftIndex;
        int rightFirstIndex = middle + 1;
        int tempIndex = leftIndex;
        
        // 左右数组均为有序状态,length=1的数组就是有序状态
        // 将左右数组的值在temp数组中顺序排列
        while (leftFirstIndex <= middle && rightFirstIndex <= rightIndex)
        {
            if (array[leftFirstIndex] < array[rightFirstIndex])
            {
                temp[tempIndex++] = array[leftFirstIndex++];
            }
            else
            {
                temp[tempIndex++] = array[rightFirstIndex++];
            }
        }
        
        // 处理左右数组大小范围穿插的现象
        while (leftFirstIndex <= middle)
        {
            temp[tempIndex++] = array[leftFirstIndex++];
        }
        while (rightFirstIndex <= rightIndex)
        {
            temp[tempIndex++] = array[rightFirstIndex++];
        }
        // 将临时数组中的内容存储到原数组中
        while (leftIndex <= rightIndex)
        {
            array[leftIndex] = temp[leftIndex++];
        }
    }
    
}