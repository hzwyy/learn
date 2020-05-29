package com.example.demo.quickSort;


import java.util.Arrays;

/**
 * 快速排序
 *
 * @Author YJY
 * @Date 2020/5/29 14:06
 */
public class QuickSort {


    /**
     * 交换
     *
     * @param swopA
     * @param swopB
     * @param arr
     */
    public static void swop(int swopA, int swopB, int[] arr) {

        int temp = arr[swopA];
        arr[swopA] = arr[swopB];
        arr[swopB] = temp;
    }

    /**
     * 如果比较位选的是右边 则需要从左边开始循环
     * 如果比较位选的是左边 则需要从右边开始循环
     *
     * @param leftIndex
     * @param rightIndex
     * @param arr
     */
    public static void quickSort(int leftIndex, int rightIndex, int arr[]) {

        //方法出口
        if (leftIndex > rightIndex) {

            return;
        }

        /**
         *  这三个变量一定要申请,因为递归的时候还要用到形参的值(int leftIndex, int rightIndex)
         *  所以在方法里面自己申请新字段接受初始值
         */
        int beginIndex = leftIndex;
        int startLeftIndex = leftIndex;
        int startRightIndex = rightIndex;

        //不等于即可
        while (startRightIndex != startLeftIndex) {


            while (startLeftIndex < startRightIndex && arr[startRightIndex] >= arr[beginIndex]) {

                startRightIndex--;
            }

            //这里判断 >= 的原因是 初始排序比较位设置的是 0  左边开始比较的也是从0开始
            while (startLeftIndex < startRightIndex && arr[startLeftIndex] <= arr[beginIndex]) {

                startLeftIndex++;
            }

            //以上判断找到了两个符合的值 这个时候交换即可
            if (startLeftIndex < startRightIndex) {

                swop(startLeftIndex, startRightIndex, arr);
            }
        }

        //一轮交换完毕后 需要把比较位的值 和判断中最后位置的值 交换一下位置 ，这样比较位矫正完毕 即  左边<比较位<右边
        swop(beginIndex, startLeftIndex, arr);

        //开始递归 左边
        quickSort(leftIndex, startLeftIndex - 1, arr);
        //开始递归 右边
        quickSort(startLeftIndex + 1, rightIndex, arr);

    }


    public static void main(String[] args) {

        int arr[] = {5, 1, 3, 2, 10, 4, 7, 6, 9, 8};
        int rightIndex = arr.length - 1;
        int leftIndex = 0;
        quickSort(leftIndex, rightIndex, arr);
        System.out.println(Arrays.toString(arr));
    }
}
