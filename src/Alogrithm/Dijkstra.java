package Alogrithm;

/**
 * [1]----------- / \ 100 | 10 / \ 30 | /~ \ |~ [2] -\----~[5] \ / \ |~ 50\ /10
 * \ |60 \~ / \~ / [3] ~----~ [4] 20 ~表示箭头，有向图
 * */

public class Dijkstra {
	private final int WEIGHT_MAX = 1000;
	private final int N = 5;
	private int[][] cost;

	public Dijkstra() {
		initCost();
		executeDijkstra();
	}

	private void executeDijkstra() {
		boolean[] caled = new boolean[N + 1];
		int[] pathLen = new int[N + 1];
		caled[1] = true; // set 1 is the source.
		int curr = 1;
		
		// other nodes pathLen init
		for (int i = 2; i <= N; i++) {
			caled[i] = false;
			pathLen[i] = cost[1][i];
		}
		
		for (int i = 1; i <= N; i++) {
			//print the pathlen status...
			printPathLen(curr, pathLen);
			
			int min = WEIGHT_MAX;
			// find the min pathLen to be the curr node
			for (int j = 1; j <= N; j++) {
				if (!caled[j] && pathLen[j] < min) {
					min = pathLen[j];
					curr = j;
				}
			}
			caled[curr] = true;
			
			for (int j = 1; j <= N; j++) {
				// refresh the pathLen
				if (!caled[j]) {
					//Math.min(1 link to j directly, 1 cross curr to j)
					pathLen[j] = Math.min(pathLen[j], pathLen[curr]
							+ cost[curr][j]);
				}
			}
		}
	}

	private void printPathLen(int curr, int[] pathLen){
		System.out.print("curr  ");
		for (int k = 2; k <= N; k++) {
			System.out.print(k+"   ");
		}
		System.out.println();
		System.out.print(curr+"     ");
		for (int k = 2; k <= N; k++) {
			System.out.print(pathLen[k]+ "  ");
		}
		System.out.println();
	}
	
	private void initCost() {
		cost = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cost[i][j] = WEIGHT_MAX;
			}
		}

		// hard core to init directed graph
		String str = "1 2 10 1 4 30 1 5 100 2 3 50 3 4 20 3 5 10 4 3 20 4 5 60";
		String[] cs = str.split(" ");
		int k = -1, j = -1;
		for (int i = 0; i < cs.length; i++) {
			if (i % 3 == 0) {
				k = Integer.parseInt(cs[i]);
			} else if (i % 3 == 1) {
				j = Integer.parseInt(cs[i]);
			} else {
				// only kj is set cause it's directed graph
				cost[k][j] = Integer.parseInt(cs[i]);
			}
		}
	}

	public static void main(String[] args) {
		new Dijkstra();
	}
}
