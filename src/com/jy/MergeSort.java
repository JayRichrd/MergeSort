package com.jy;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		DataWrap[] dataWraps = new DataWrap[] { new DataWrap(9, ""), new DataWrap(-16, ""), new DataWrap(21, ""),
				new DataWrap(23, ""), new DataWrap(-30, ""), new DataWrap(-49, ""), new DataWrap(21, ""),
				new DataWrap(30, ""), new DataWrap(13, "") };

		System.out.println("排序前：" + Arrays.toString(dataWraps));
		// 开始时间
		long startTime = System.currentTimeMillis();
		mergeSort(dataWraps);
		// 结束时间
		long stopTime = System.currentTimeMillis();
		System.out.println("*********************排序结束*********************");
		System.out.println("排序耗时t：" + (stopTime - startTime) + "ms");
		System.out.println("排序后（从小到大）：" + Arrays.toString(dataWraps));
	}

	/**
	 * 归并排序数组
	 * 
	 * @param dataWraps
	 *            待排序的数组
	 */
	public static void mergeSort(DataWrap[] dataWraps) {
		System.out.println("*********************开始排序*********************");
		mergeSort(dataWraps, 0, dataWraps.length - 1);
	}

	/**
	 * 归并排序数组
	 * 
	 * @param dataWraps
	 *            待排序的数组
	 * @param left
	 *            需要用来归并排序的数组左边边界值的索引
	 * @param right
	 *            需要用来归并排序的数组右边边界值的索引
	 */
	public static void mergeSort(DataWrap[] dataWraps, int left, int right) {
		// 当left=right时，表明此时左右子数组只有一个元素
		if (left < right) {
			int center = (left + right) >> 1;
			// 递归地排序左右两边的数组，然后再合并
			mergeSort(dataWraps, left, center);
			mergeSort(dataWraps, center + 1, right);
			merge(dataWraps, left, center, right);
		}
	}

	/**
	 * 合并两个排序的子数组
	 * 
	 * @param dataWraps
	 *            待合并的数组
	 * @param left
	 *            左边排序数组的起始索引
	 * @param center
	 *            左边排序数组的结束索引,center+1则是右边排序数组的起始索引
	 * @param right
	 *            右边排序数组的结束索引
	 */
	public static void merge(DataWrap[] dataWraps, int left, int center, int right) {
		// 定义一个与待合并数组相同大小的临时数组
		int tempArrLength = right - left + 1;
		DataWrap[] tmpArr = new DataWrap[tempArrLength];
		// 找到右边数组的开始位置
		int mid = center + 1;
		// 将左边数组的起始位置保存下来，便于后面从这个位置开始用临时数组的值覆盖
		int temp = left;
		// 临时数组的索引
		int tempIndex = 0;
		// 循环取出左右数组的值，并比较，根据大小情况放入临时数组中
		while (left <= center && mid <= right) {
			if (dataWraps[left].compareTo(dataWraps[mid]) < 0)
				tmpArr[tempIndex++] = dataWraps[left++];
			else
				tmpArr[tempIndex++] = dataWraps[mid++];
		}

		// 将左(右)数组剩余的部分直接全部复制到临时数组的后面
		while (left <= center)
			tmpArr[tempIndex++] = dataWraps[left++];
		while (mid <= right)
			tmpArr[tempIndex++] = dataWraps[mid++];

		// 将临时数组中排好序的数组元素反向拷贝回原来待排序的数组中，并覆盖对应位置的元素
		// 从临时数组的第一元素开始
		tempIndex = 0;
		System.arraycopy(tmpArr, 0, dataWraps, temp, tempArrLength);
		// while (temp <= right)
		// dataWraps[temp++] = tmpArr[tempIndex++];
		// 每一趟后都打印出当前的排序结果
		System.out.println(Arrays.toString(dataWraps));

	}

}
