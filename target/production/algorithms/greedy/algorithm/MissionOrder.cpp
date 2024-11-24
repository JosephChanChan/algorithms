/*
	Question Description:
		有N个任务需要执行，第i个任务计算时占R[i]个空间，而后会释放一部分，最后储存计算结果需要占据O[i]个空间（O[i] < R[i]）。
		例如：执行需要5个空间，最后储存需要2个空间。给出N个任务执行和存储所需的空间，问执行所有任务最少需要多少空间。

		Input:
		20
		14 1
		2 1
		11 3
		20 4
		7 5
		6 5
		20 7
		19 8
		9 4
		20 10
		18 11
		12 6
		13 12
		14 9
		15 2
		16 15
		17 15
		19 13
		20 2
		20 1

		Output:
		135

	Analysis:
		按照 任务释放空间 降序排列任务，是有效的贪心策略。

		贪心策略的正确性通常都需要证明，不然谁能保证你的算法是正确的呢？
		设 b[] 一个数组，b[i] = R[i] - O[i] ， 即 b[] 是任务释放空间.
		该问题可以抽象成 一个整数每一次 需要减去 a[i] = R[i] 再加上 b[i] 结果保证不出现负数。
		那么存在这样一个数列 b[0] >= b[1] >= ... >= b[x] < b[x+1]
		意思是 b[] 是一个未经排序的。假设 (a[1], b[1]) (a[2], b[2]) ... (a[x], b[x]) (a[x+1], b[x+1]) 这样的序列执行，是不会出现负数的。
		我们需要证明，将 (a[x+1], b[x+1]) 排在 (a[x], b[x]) 前面也是不会出现负数的。即证明按照 b[] 降序排列这种策略是正确的。

		那么 (a[x], b[x]) (a[x+1], b[x+1]) 能否将 x+1 排列到 x 前面执行？
		注意到实际每减去一个括号，实际是减去一个负数，因为a[x] > b[x]，所以将 x+1 放在前面执行，被减数此时越大。所以 -a[x+1] + b[x+1] > 0 。
		因为 -a[x] + b[x] - a[x+1] > 0 （我们的假设就是这样的）。b[x+1] > b[x]
		所以 -a[x+1] + b[x+1] - a[x] > -a[x] + b[x] - a[x+1]

		而经过若干次这样的交换之后，我们肯定会把序列交换成按照b的不增顺序排序的。从而我们证明了，
		任何可行的方案都不好于按照b不增顺序排序的序列执行的方案，从而证明了我们的贪心策略是有效的。

	Created by Joseph on 2018/8/07.
*/
#include <stdio.h>
#include <iostream>
#include <string>
using namespace std;

struct Mission
{
	int calculateSpace;	// 计算时占用的空间
	int storeSpace;		// 存储占用的空间
	int diffSpace;			// 间隔差
};

int doQuickSort(Mission missionArray[], int left, int right)
{
	Mission base = missionArray[left];

	while (left < right)
	{
		while (left < right && base.diffSpace >= missionArray[right].diffSpace)
		{
			right--;
		}

		missionArray[left] = missionArray[right];

		while (left < right && base.diffSpace <= missionArray[left].diffSpace)
		{
			left++;
		}

		missionArray[right] = missionArray[left];
	}

	missionArray[left] = base;
	return left;
}

void quickSort(Mission missionArray[], int left, int right)
{
	if (left < right)
	{
		int base = doQuickSort(missionArray, left, right);

		// sort left side
		quickSort(missionArray, left, base - 1);
		// sort right side
		quickSort(missionArray, base + 1, right);
	}
}

int main()
{
	int missionCount , baseSpace = 0;
	cin >> missionCount;

	Mission *missionArray = new Mission[missionCount];

	for (size_t i = 0; i < missionCount; i++)
	{
		Mission temp;
		cin >> temp.calculateSpace;
		cin >> temp.storeSpace;
		temp.diffSpace = temp.calculateSpace - temp.storeSpace;
		missionArray[i] = temp;

		// 累加存储所有任务所需的基本空间
		baseSpace += temp.storeSpace;
	}

	quickSort(missionArray, 0, missionCount - 1);

	// 打印排列后的任务(非必要，如果要提交OJ，记得将此处注释)
	for (size_t i = 0; i < missionCount; i++)
	{
		Mission temp = missionArray[i];
		cout << temp.calculateSpace << "   " << temp.storeSpace << "   " << temp.diffSpace << "\r\n";
	}

	/*
		优先选择存储空间最大的任务执行。
		我们累积了所有存储所需的基本空间。当计算一个任务，计算空间不够时
		将差额空间记录下来，遍历完之后我们就知道还需要多少差额空间。
	*/
	int supplementSpace = 0, tempBaseSpace = baseSpace;
	for (size_t i = 0; i < missionCount; i++)
	{
		Mission mission = missionArray[i];
		if (mission.calculateSpace > tempBaseSpace)
		{
			int diff = mission.calculateSpace - tempBaseSpace;
			supplementSpace += diff;
			tempBaseSpace += diff;
		}
		tempBaseSpace -= mission.storeSpace;
	}

	cout << baseSpace + supplementSpace;

	// 要提交OJ需要注释此处
	system("pause");

	return 0;
}
