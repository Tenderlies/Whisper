package com.tosh.whisper.utils.sort;

/**
 * 堆排序<br>
 * ClassName: HeapSort<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2017年6月7日
 */
public class HeapSort implements Sort
{
    @Override
    public void sort(int[] arr)
    {
        heapSort(arr, arr.length);
    }
    
    // 初始化堆
    private void initHeap(int a[], int len)
    {
        // 从完全二叉树最后一个非子节点开始
        // 在数组中第一个元素的索引是0
        // 第n个元素的左孩子为2n+1，右孩子为2n+2，
        // 最后一个非子节点位置在(n - 1) / 2
        for (int i = (len - 1) / 2; i >= 0; --i)
        {
            adjustMaxHeap(a, len, i);
        }
    }
    
    private void adjustMaxHeap(int a[], int len, int parentNodeIndex)
    {
        // 若只有一个元素，那么只能是堆顶元素，也没有必要再排序了
        if (len <= 1)
        {
            return;
        }
        // 记录比父节点大的左孩子或者右孩子的索引
        int targetIndex = -1;
        // 获取左、右孩子的索引
        int leftChildIndex = 2 * parentNodeIndex + 1;
        int rightChildIndex = 2 * parentNodeIndex + 2;
        // 没有左孩子
        if (leftChildIndex >= len)
        {
            return;
        }
        // 有左孩子，但是没有右孩子
        if (rightChildIndex >= len)
        {
            targetIndex = leftChildIndex;
        }
        // 有左孩子和右孩子
        else
        {
            // 取左、右孩子两者中最大的一个
            targetIndex = a[leftChildIndex] > a[rightChildIndex] ? leftChildIndex
                    : rightChildIndex;
        }
        // 只有孩子比父节点的值还要大，才需要交换
        if (a[targetIndex] > a[parentNodeIndex])
        {
            int temp = a[targetIndex];
            a[targetIndex] = a[parentNodeIndex];
            a[parentNodeIndex] = temp;
            // System.out.println(a[0]+" "+a[1]+" "+a[2]+" "+a[3]+" "+a[4]+" "+a[5]+" "+a[6]+" "+a[7]+" "+a[8]+a[9]+" "+a[10]+" "+a[11]+" "+a[12]+" "+a[13]+" "+a[14]+" "+a[15]+" "+a[16]+" "+a[17]);
            // 交换完成后，有可能会导致a[targetIndex]结点所形成的子树不满足堆的条件，
            // 若不满足堆的条件，则调整之使之也成为堆
            adjustMaxHeap(a, len, targetIndex);
        }
    }
    
    private void heapSort(int a[], int len)
    {
        if (len <= 1)
        {
            return;
        }
        // 初始堆成无序最大堆
        initHeap(a, len);
        for (int i = len - 1; i > 0; --i)
        {
            // 将当前堆顶元素与最后一个元素交换，保证这一趟所查找到的堆顶元素与最后一个元素交换
            // 注意：这里所说的最后不是a[len - 1]，而是每一趟的范围中最后一个元素
            // 为什么要加上>0判断？每次不是说堆顶一定是最大值吗？没错，每一趟调整后，堆顶是最大值的
            // 但是，由于len的范围不断地缩小，导致某些特殊的序列出现异常
            // 比如说，5, 3, 8, 6, 4序列，当调整i=1时，已经调整为3,4,5,6,8序列，已经有序了
            // 但是导致了a[i]与a[0]交换，由于变成了4,3,5,6,8反而变成无序了!
            if (a[0] > a[i])
            {
                int temp = a[0];
                a[0] = a[i];
                a[i] = temp;
            }
            // 范围变成为：
            // 0...len-1
            // 0...len-1-1
            // 0...1 // 结束
            // 其中，0是堆顶，每次都是找出在指定的范围内比堆顶还大的元素，然后与堆顶元素交换
            adjustMaxHeap(a, i - 1, 0);
        }
    }
    
}
