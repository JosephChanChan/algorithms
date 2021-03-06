/**
 * 因为OJ平台Java语言提交没反应. 用C++提交可以, 所以用C++解题.

 * Question Description:
 * 有若干个活动，第i个开始时间和结束时间是[Si,fi)，活动之间不能交叠，要把活动都安排完，至少需要几个教室？
 *
 * Analysis:
 * 这个问题和之前的问题本质差别是什么？
 * 尝试抽象一下。第一个问题是 有限的资源，在给定的所有活动中，选择所有可以安排的活动。第二个问题，将所有活动用最少的资源处理完。
 * 用第一个问题的贪心策略可以解决？尽量将一个教室安排多的活动？答案是不行，反例： A：[1,2)  B：[1,4) C：[5,6) D：[3，7)
 *
 * 假设最优解是 k, 至少需要 k 间教室安排所有活动. 现在选择这些活动时，刚开始 k 是0.
 * 如果现有的k个教室里可以安排下这个活动则直接加进k个教室中。否则，代表什么？代表k个教室都有活动正在进行。
 * 那么必须开多一个教室，k+1. 就是说所有的活动中时间重叠最多的那个数量。意味着有个时间段有 N 个活动都要进行。
 * 那么必须开 N 个教室。其它的重叠活动数都小于这个max{重叠活动数}，N个教室中肯定有教室空下来可以安排剩余的活动。
 * 小于 N 个教室不行, 大于 N 个教室浪费。所以这个策略是正确的。
 *
 * 剩下的就是该如何实现算法？输入的活动都是无序的, 在遍历Ai个活动时需要判断与哪些活动重叠，这个重叠的是哪个时间段，非常麻烦。
 * 采取模拟算法，将开始时间和结束时间都按升序排序。
 * 遇到开始时间的代表活动开始，检查教室是否都被占用，是则开一间教室。遇到结束时间的则空出一间教室。
 *
 * 时间复杂度:
 * 快排序 O(N*logN) + 线性判断 O(N)
 *
 * created by Joseph
 * at 2018/7/31 17:27
 */
#include <stdio.h>
#include <iostream>
#include <string>
using namespace std;

class Activity
{
	public:
		int time;		// 可能是开始时间或结束时间
		bool start;	    // 0 为结束时间 1 为开始时间
};

int doQuickSort(Activity  activityArray[], int left, int right)
{
	Activity base = activityArray[left];
	
	while (left < right)
	{
		while (left < right && base.time <= activityArray[right].time)
		{
			right--;
		}
		
		activityArray[left] = activityArray[right];
		
		while (left < right && base.time >= activityArray[left].time)
		{
			left++;
		}

		activityArray[right] = activityArray[left];
	}

	activityArray[left] = base;
	return left;
}

void quickSort(Activity activityArray[], int left, int right)
{
	if (left < right)
	{
		int base = doQuickSort(activityArray, left, right);

		// sort left side
		quickSort(activityArray, left, base - 1);
		// sort right side
		quickSort(activityArray, base + 1, right);
	}
}

void checkAsc(Activity activeArray[], int count)
{
	for (size_t i = 0; i < count - 1; i++)
	{
		if (activeArray[i].time > activeArray[i+1].time)
		{
			cout << "第 " << i << " 行错误!";
			return;
		}
	}
	cout << "升序排列正确";
}

int main()
{
	int activeCount ;
	cin >> activeCount;
	activeCount = activeCount << 1;
	Activity *activeArray = new Activity[activeCount];

	for (size_t i = 0; i < activeCount; i++)
	{
		Activity *activeS = new Activity();
		cin >> activeS->time;
		activeS->start = true;
		activeArray[i] = *activeS;

		Activity *activeF = new Activity();
		cin >> activeF->time;
		activeF->start = false;
		activeArray[++i] = *activeF;
	}

	quickSort(activeArray, 0, activeCount - 1);

	int maxClass = 0;					// 最大的教室数
	int usingClass = 0;				// 正在使用的教室数

	for (size_t i = 0; i < activeCount; i++)
	{
		// 如果是开始时间
		if (activeArray[i].start)
		{
			if (maxClass - usingClass == 0)
			{
				maxClass++;
			}
			usingClass++;
		}
		// 如果是结束时间
		else
		{
			usingClass--;
		}
	}
	
	cout << maxClass;

	system("pause");

    return 0;
}

