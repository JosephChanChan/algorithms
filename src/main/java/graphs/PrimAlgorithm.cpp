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
		最小生成树的Prim算法也是贪心算法的一大经典应用。Prim算法的特点是时刻维护一棵树，算法不断加边，加的过程始终是一棵树。
		Prim算法过程：
		一条边一条边地加， 维护一棵树。
		初始 E ＝ ｛｝空集合， V = ｛任意节点｝
		循环（n – 1）次，每次选择一条边（v1,v2）， 满足：v1属于V , v2不属于V。且（v1,v2）权值最小。
		E = E + （v1,v2）
		V = V + v2
		最终E中的边是一棵最小生成树， V包含了全部节点。

		Prim算法的证明：
		假设Prim算法得到一棵树P，有一棵最小生成树T。假设P和T不同，我们假设Prim算法进行到第(K – 1)步时选择的边都在T中，
		这时Prim算法的树是P’, 第K步时,Prim算法选择了一条边e = (u, v)不在T中。假设u在P’中，而v不在。
		因为T是树，所以T中必然有一条u到v的路径，我们考虑这条路径上第一个点u在P’中，最后一个点v不在P’中，
		则路径上一定有一条边f = (x,y)，x在P’中，而且y不在P’中。
		我们考虑f和e的边权w(f)与w(e)的关系：
		若w(f) > w(e)，在T中用e换掉f （T中加上e去掉f)，得到一个权值和更小的生成树，与T是最小生成树矛盾。
		若w(f) < w(e), Prim算法在第K步时应该考虑加边f，而不是e,矛盾。
		因此只有w(f) = w(e),我们在T中用e换掉f，这样Prim算法在前K步选择的边在T中了，有限步之后把T变成P,而树权值和不变， 从而Prim算法是正确的。

		明白了Prim算法生成图的最小生成树原理后，该怎么实现这个算法？
		设V[]为访问过的顶点集合，选择连通的v1 v2访问且满足v1 v2的边权值最小，v1属于V中，v2不属于V。
		直到V中访问过所有顶点，算法结束。

	Created by Joseph on 2018/8/07.
*/
#include <stdio.h>
#include <iostream>
#include <string>
#include <list>
#include <set>
#include <ctime>
using namespace std;

#define MAX 1001
#define MAX_WEIGHT 0x7fffffff

int lineCount = 0, nodeCount = 0;

// 选择过的边的权值和
int weight_sum = 0;

// 记录下访问过的节点
set<int> visitedNode;

struct Node
{
	bool connected;//		是否连通 
	int weight;//					权值
};

void initalizeMatrix(Node *nodeArray[])
{
	for (size_t i = 1; i <= nodeCount; i++)
	{
		Node *arr = new Node[nodeCount + 1];
		for (size_t k = 1; k <= nodeCount; k++)
		{
			Node temp = { false, 0 };
			arr[k] = temp;
		}
		nodeArray[i] = arr;
	}
}

// 建立邻接矩阵
void establish_adjacent_matrix(Node *nodeArray[])
{
		Node *arr, *arr2;
		Node *node, *node2;
		int p_index ;
		for (size_t i = 0; i < lineCount; i++)
		{
			int peak1, peak2, weight;
			cin >> peak1;
			cin >> peak2;
			cin >> weight;
	
			arr = nodeArray[peak1];
			node = &arr[peak2];
			node->connected = true;
			node->weight = weight;
	
			if (0 == i)
			{
				visitedNode.insert(peak1);
			}
	
			arr2 = nodeArray[peak2];
			node2 = &arr2[peak1];
			node2->connected = true;
			node2->weight = weight;
		}
}

// 自己的Prim算法，时间复杂度高 O(n^3)，超时
void my_prim_algorithm()
{
	// 二级指针。内容是指针数组，数组里的指针又指向Node一维数组
	Node **nodeArray = new Node*[nodeCount + 1];

	// 初始化矩阵
	initalizeMatrix(nodeArray);

	// 建立邻接矩阵
	establish_adjacent_matrix(nodeArray);

	/*
		设V[]为访问过的顶点集合，选择连通的v1 v2访问且满足v1 v2的边权值最小，v1属于V中，v2不属于V。
		直到V中访问过所有顶点，算法结束。
		每一次迭代V，枚举出所有V中顶点的边（排除回路的边），选择权值最小的边。
		时间复杂度:
		内循环固定为 1000, 外循环 1~1000。大约为 O(n^3)
	*/
	while (true)
	{
		int minum_peak = 0, minimum_weight = 10001;	// 权值最大 10000;

		std::set<int>::iterator ite;
		for (ite = visitedNode.begin(); ite != visitedNode.end(); ite++)
		{
			Node *arr = nodeArray[*ite];
			for (int peak = 1; peak <= nodeCount; peak++)
			{
				if (arr[peak].connected && 0 == visitedNode.count(peak))
				{
					if (arr[peak].weight < minimum_weight)
					{
						minimum_weight = arr[peak].weight;
						minum_peak = peak;
					}
				}
			}
		}

		if (minimum_weight < 10001)
		{
			visitedNode.insert(minum_peak);
			weight_sum += minimum_weight;
		}
		else
		{
			break;
		}
	}
}

int graph_matrix[MAX][MAX];

// 别人解题报告里的Prim算法, 设计很巧妙, 已AC. 时间复杂度 O(n * 2n)
void good_prim_algorithm()
{
	// 记录到达第 i 个顶点的所有边中，权值最小的一条边的权值。
	// 这个数组会记录所有顶点的连通边的权值，虽然在过程中，一些顶点会是未连通，
	// 但是输入数据保证图是一个连通图，所以最终都会记录到所有顶点连通权值。
	// minumin_weight_node[i] = -1 的话，代表第i节点已加入生成树
	int minumin_weight_node[MAX];

	// 记录起点。对应 minumin_weight_node[i] 的起点。
	// 即 顶点10的权值是 101，此时的顶点10是和 min_weight_original[i] 连通的
	// min_weight_original[i] = -1 的话，代表第i节点已加入生成树
	int min_weight_original[MAX];


	for (int i = 1; i <= nodeCount; i++)
	{
		for (int j = 1; j <= nodeCount; j++)
		{
			// 初始化矩阵，每个节点间距离无穷大
			graph_matrix[i][j] = MAX_WEIGHT;
		}
	}

	for (int i = 0; i < lineCount; i++)
	{
		int peak1, peak2, weight;
		cin >> peak1 >> peak2 >> weight;

		// 连通 顶点1 和 顶点2 并设置权值
		graph_matrix[peak1][peak2] = weight;
		graph_matrix[peak2][peak1] = weight;
	}

	// 初始化设置每一个顶点和顶点1的权值，未连通的还是无穷大
	for (int i = 1; i < MAX; i++)
	{
		minumin_weight_node[i] = graph_matrix[1][i];
		// 此时每个顶点的起点是顶点1
		min_weight_original[i] = 1;
	}

	// 默认选择顶点1出发
	minumin_weight_node[1] = -1;

	int minimun_peak = 0, pre_peak = 1;

	// 每一次会选择最小的一条边访问，N个顶点需要N-1条边组成最小生成树
	for (int t = 0; t < nodeCount - 1; t++)
	{
		int min_weight = MAX_WEIGHT;

		// 选择现在已连通的边中权值最小的，且顶点未加入生成树的
		for (int k = 1; k <= nodeCount; k++)
		{
			// 连通k顶点的边权值较小，且k顶点未加入生成树
			if (minumin_weight_node[k] < min_weight && minumin_weight_node[k] != -1)
			{
				min_weight = minumin_weight_node[k];
				minimun_peak = k;
			}
		}

		// 将此次选择的要到访的顶点加入生成树
		minumin_weight_node[minimun_peak] = -1;

		weight_sum += min_weight;

		cout << "pre_peak = " << pre_peak << "  dis_peak = " << minimun_peak << "  weight = " << min_weight << endl;

		pre_peak = minimun_peak;

		// 现在已经到了 k 顶点，这个新的顶点将会连通几个未知的新顶点，就是有几条新的边
		// 更新下顶点的连通权值信息，如果到达同一个顶点而新的边的权值更小，则覆盖前面老的边
		for (int v = 1; v <= nodeCount; v++)
		{
			if (minumin_weight_node[v] != -1 && graph_matrix[minimun_peak][v] < minumin_weight_node[v])
			{
				minumin_weight_node[v] = graph_matrix[minimun_peak][v];
			}
		}
	}

}

int main()
{
	// 节点数量不会大于 1000
	cin >> nodeCount >> lineCount;

	//my_prim_algorithm();

	good_prim_algorithm();

	cout << weight_sum;

	system("pause");

	return 0;
}
