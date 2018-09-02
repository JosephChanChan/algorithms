/*
Question Description:
N个点M条边的无向连通图，每条边有一个权值，求该图的最小生成树。

输入
第1行：2个数N,M中间用空格分隔，N为点的数量，M为边的数量。（2 <= N <= 1000, 1 <= M <= 50000)
第2 - M + 1行：每行3个数S E W，分别表示M条边的2个顶点及权值。(1 <= S, E <= N，1 <= W <= 10000)

输出
输出最小生成树的所有边的权值之和。

Input:
9 14
1 2 4
2 3 8
3 4 7
4 5 9
5 6 10
6 7 2
7 8 1
8 9 7
2 8 11
3 9 2
7 9 6
3 6 4
4 6 14
1 8 8

Output:
37

Analysis:
	这次介绍的是另一种最小生成树算法，也是贪心算法一种，自然的贪心策略又要证明正确性（每次看贪心策略的证明感觉都有点烧脑...）
	Kruskal算法。
	贪心策略：将所有边按权值大小升序排序，然后依次处理每条边，若边的2头顶点不在同一个集合中，加入这条边，否则放弃。
	直到算法选择出 N - 1 条边，则这些边构成最小生成树，否则原图不连通。

	以下的证明中会尽量说的详细，并且应该会比网上其它证明要易懂。

	贪心策略证明：
		设由Kruskal算法得出的一个最小生成树 K。
		设原图 G 的最小生成树 T。
		如果 K ！= T，则 K 和 T 之间肯定存在有 J 条不相等的边。也可以说 K 树中有 J 条边不在 T 树中。
		要做的就是将这 J 条边，依次加入到 T 树中，使 T 树依然是一颗最小生成树。
		在 J 条边中选出一条最小的边，设为 e，e 在 K 树中，不在 T 树中。
		将 e 加入T树中，则T树必然出现唯一一个回路。
		（
			什么是回路？
				假设A顶点和B顶点和C顶点连通，只有边 f 进出，此时边e的加入，将A顶点直接和C顶点连通，A从f出发可以从e回到A，这就是回路
			为什么是唯一的？
				因为边 e 只可以连通2个顶点，e只是将A和另外某个顶点连通，所以只出现一条回路。
		）
		将回路的另外一条边 f 删除（f 是 T 树中原来的一条边）。f 不在K树中，因为f 和 e都在K树中的话就有环，而K树定义是没有环的。
		此时证明了，将e替代f，T树仍然是生成树。
		接下里只需要证明 T 树仍然是最小代价的生成树。
		设 w(i) 为边 i 的权值。
		考虑 w(e) 和 w(f) 的权值大小。
		如果 w(e) < w(f)，则变化后的T树代价小于变化前的T树，与T树原设定矛盾。
		如果 w(e) > w(f)，那在 K 树生成过程中，肯定考虑过f的加入，因为f会与K树中其它边形成回路而没有选择f。
		K 树和 T 树有 J 条边相异，则还有 N - (1 + J) 条边相等，将这些相等的边设为 S。
		按照 e 的定义，它是 J 中最小的边，而 f < e，所以 f 肯定在 S 中，而 S 所有边会在 T 树中，所以 T 树中的某条边会与 f 冲突，与 T 树设定矛盾。
		所以 w(e) 只能等于 w(f)。
		这样经过 J 个循环，将相异的 J 条边替代后，证明了将 e 替代 f，T树仍然是一颗生成树，并且代价不变。

		PS: 由证明过程可以知道，最小生成树可以不是唯一的。

Created by Joseph on 2018/9/02.
*/
#include <stdio.h>
#include <iostream>
using namespace std;

/* 边的结构体 */
typedef struct
{
	int a;				// A顶点
	int b;				// B顶点
	int weight;	// 边的权值
} Road;

/* 快排序 */
int doQuickSort(Road  roadArray[], int left, int right)
{
	Road base = roadArray[left];

	while (left < right)
	{
		while (left < right && base.weight <= roadArray[right].weight)
		{
			right--;
		}

		roadArray[left] = roadArray[right];

		while (left < right && base.weight >= roadArray[left].weight)
		{
			left++;
		}

		roadArray[right] = roadArray[left];
	}

	roadArray[left] = base;
	return left;
}
void quickSort(Road roadArray[], int left, int right)
{
	if (left < right)
	{
		int base = doQuickSort(roadArray, left, right);

		// sort left side
		quickSort(roadArray, left, base - 1);
		// sort right side
		quickSort(roadArray, base + 1, right);
	}
}

/* 从指定的边开始搜寻其根 */
int getRoot(int peak_array[], int road)
{
	while (true)
	{
		int f = peak_array[road];
		if (f != road)
		{
			road = f;
		}
		else
		{
			return f;
		}
	}
}

int main()
{
	int peak_count, road_count;
	cin >> peak_count >> road_count;

	Road road_array[50000];// 边的数组，输入最大50000

	for (size_t i = 0; i < road_count; i++)
	{
		cin >> road_array[i].a >> road_array[i].b >> road_array[i].weight;
	}

	quickSort(road_array, 0, road_count - 1);

	/*
		并查集是一种数据结构，不了解的先去学一下。
		在这里，每个顶点就是数组下标，值就是该顶点的根
	*/
	int peak_array[1001];// 顶点数组，作为并查集

	// 初始化并查集，每个顶点的根是自己
	for (size_t i = 1; i <= peak_count; i++)
	{
		peak_array[i] = i;
	}

	int sum_weight = 0;

	// 依次从权值升序选择边，构成最小生成树
	for (size_t i = 0; i < road_count; i++)
	{
		Road road = road_array[i];

		// 查看2个顶点的根是否相等，相等则表示在同个集合中
		int t1 = getRoot(peak_array, road.a);
		int t2 = getRoot(peak_array, road.b);

		if (t1 != t2)
		{
			sum_weight += road.weight;
			// 将 b 顶点的根挂到 a 顶点下去
			peak_array[t2] = road.a;
		}
	}

	cout << sum_weight << endl;

	system("pause");
	return 0;
}