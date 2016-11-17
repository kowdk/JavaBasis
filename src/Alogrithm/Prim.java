package Alogrithm;

import java.util.Arrays;

/** ��С�������㷨�����������ͼ
         60
    [1]-----[3]
    /       / \
 50/     52/   \45
  /  65   /  42 \
[2]-----[4]-----[7]
  \     / \
 40\   /50 \30
    \ /     \
    [5]-----[6]
         70
7����;10����;
��Ȩ��1 2 50 1 3 60 2 4 65 2 5 40 3 4 52 3 7 45 4 5 50 5 6 70 4 6 30 4 7 42;
*/

public class Prim {
	private final int pointNum = 7;
	private final int MAX_WEIGHT = 1000;
	
	private int weightSum = 0;
	private int[][] cost;
	
	public Prim(){
		initCost();
		executePrim();
		System.out.println("The total weight: " + getWeightSum());
	}
	
	public int getWeightSum() {
		return this.weightSum;
	}
	
	// execute the prim algorithm
	private void executePrim() {
		int[] closet = new int[pointNum+1]; //closet[i]��ʾ���ѱ�������U�е�һ�����㵽V-U�ж��㹹�ɵı�Ȩ��С��
		int[] lowCost = new int[pointNum+1]; //lowCost��ʾ(i, cloest[i])��Ȩ
		
		// init every other nodes is closet to 1 
		for(int i = 2; i <= pointNum; i++) {
			lowCost[i] = cost[1][i];
			closet[i] = 1;
		}
		
		closet[1] = 0;// 1 has been added to T set.
		for(int i = 2; i <= pointNum; i++) {
			System.out.println("lowCost status : " + Arrays.toString(lowCost));
			int min = MAX_WEIGHT, curr = i;
			for(int j = 2; j <=pointNum; j++) {
				if(closet[j] != 0 && lowCost[j] < min){
					min = lowCost[j];
					curr = j;
				}
			}
			weightSum += min;
			closet[curr] = 0; // curr has been added to U set
			for(int j = 2; j <= pointNum; j++) {
				// lowCost[j] less than cost between curr node to j, refresh lowCost[j]...
				// j��δ���㼯���еĵ㣬����if˵��curr��j�ȵ�ǰU�����е�j�����Ȩ���̣��滻lowCost
				if(closet[j] != 0 && cost[curr][j] < lowCost[j]) { 
					lowCost[j] = cost[curr][j];
					closet[j] = curr;
				}
			}
		}
	}
	
	// init adjacent matrix and cost
	private void initCost(){
		cost = new int[pointNum+1][pointNum+1];
		
		for(int i = 1; i <= pointNum; i++) {
			for(int j = 1; j <= pointNum; j++) {
				cost[i][j] = MAX_WEIGHT;
			}
		}
		
		// hard core to init the above graph
		String str = "1 2 50 1 3 60 2 4 65 2 5 40 3 4 52 3 7 45 4 5 50 5 6 70 4 6 30 4 7 42";
		String[] weights = str.split(" ");
		int k = -1, j = -1;
		for(int i = 0; i < weights.length; i++) {
			if(i % 3 == 0) 
				k = Integer.valueOf(weights[i]);
			else if(i % 3 == 1)
				j = Integer.valueOf(weights[i]);
			else {
				// kj and jk for undirected graph
				cost[k][j] = Integer.valueOf(weights[i]);
				cost[j][k] = Integer.valueOf(weights[i]);
			}
		}
		//System.out.println(Arrays.deepToString(cost));
	}
	
	public static void main(String[] args) {
		new Prim();
	}
}
