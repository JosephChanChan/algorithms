/*
	Question Description:
		��N��������Ҫִ�У���i���������ʱռR[i]���ռ䣬������ͷ�һ���֣���󴢴��������Ҫռ��O[i]���ռ䣨O[i] < R[i]����
		���磺ִ����Ҫ5���ռ䣬��󴢴���Ҫ2���ռ䡣����N������ִ�кʹ洢����Ŀռ䣬��ִ����������������Ҫ���ٿռ䡣

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
		���� �����ͷſռ� ����������������Ч��̰�Ĳ��ԡ�

		̰�Ĳ��Ե���ȷ��ͨ������Ҫ֤������Ȼ˭�ܱ�֤����㷨����ȷ���أ�
		�� b[] һ�����飬b[i] = R[i] - O[i] �� �� b[] �������ͷſռ�.
		��������Գ���� һ������ÿһ�� ��Ҫ��ȥ a[i] = R[i] �ټ��� b[i] �����֤�����ָ�����
		��ô��������һ������ b[0] >= b[1] >= ... >= b[x] < b[x+1]
		��˼�� b[] ��һ��δ������ġ����� (a[1], b[1]) (a[2], b[2]) ... (a[x], b[x]) (a[x+1], b[x+1]) ����������ִ�У��ǲ�����ָ����ġ�
		������Ҫ֤������ (a[x+1], b[x+1]) ���� (a[x], b[x]) ǰ��Ҳ�ǲ�����ָ����ġ���֤������ b[] �����������ֲ�������ȷ�ġ�

		��ô (a[x], b[x]) (a[x+1], b[x+1]) �ܷ� x+1 ���е� x ǰ��ִ�У�
		ע�⵽ʵ��ÿ��ȥһ�����ţ�ʵ���Ǽ�ȥһ����������Ϊa[x] > b[x]�����Խ� x+1 ����ǰ��ִ�У���������ʱԽ������ -a[x+1] + b[x+1] > 0 ��
		��Ϊ -a[x] + b[x] - a[x+1] > 0 �����ǵļ�����������ģ���b[x+1] > b[x]
		���� -a[x+1] + b[x+1] - a[x] > -a[x] + b[x] - a[x+1]

		���������ɴ������Ľ���֮�����ǿ϶�������н����ɰ���b�Ĳ���˳������ġ��Ӷ�����֤���ˣ�
		�κο��еķ����������ڰ���b����˳�����������ִ�еķ������Ӷ�֤�������ǵ�̰�Ĳ�������Ч�ġ�

	Created by Joseph on 2018/8/07.
*/
#include <stdio.h>
#include <iostream>
#include <string>
using namespace std;

struct Mission
{
	int calculateSpace;	// ����ʱռ�õĿռ�
	int storeSpace;		// �洢ռ�õĿռ�
	int diffSpace;			// �����
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

		// �ۼӴ洢������������Ļ����ռ�
		baseSpace += temp.storeSpace;
	}

	quickSort(missionArray, 0, missionCount - 1);

	// ��ӡ���к������(�Ǳ�Ҫ�����Ҫ�ύOJ���ǵý��˴�ע��)
	for (size_t i = 0; i < missionCount; i++)
	{
		Mission temp = missionArray[i];
		cout << temp.calculateSpace << "   " << temp.storeSpace << "   " << temp.diffSpace << "\r\n";
	}

	/*
		����ѡ��洢�ռ���������ִ�С�
		�����ۻ������д洢����Ļ����ռ䡣������һ�����񣬼���ռ䲻��ʱ
		�����ռ��¼������������֮�����Ǿ�֪������Ҫ���ٲ��ռ䡣
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

	// Ҫ�ύOJ��Ҫע�ʹ˴�
	system("pause");

	return 0;
}
