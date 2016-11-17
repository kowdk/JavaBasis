import java.util.Scanner;

public class VM2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = in.nextInt();
			}
		}
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		int[][] cache = new int[n][m];
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int length = findSmallAround(i, j, matrix, cache,
						Integer.MIN_VALUE);
				max = Math.max(length, max);
			}
		}
		System.out.println(max);
		in.close();
	}

	private static int findSmallAround(int i, int j, int[][] matrix,
			int[][] cache, int pre) {
		// 如果超出了矩阵边界，递归结束
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
			return 0;
		}

		// 如果未超过上一个点，递归结束
		if (matrix[i][j] <= pre) {
			return 0;
		}

		// cache保存了该点的深度，如果计算过就直接相加
		if (cache[i][j] > 0) {
			return cache[i][j];
		} else { // 该点深度没有被计算过
			int cur = matrix[i][j]; // 记录当前数值
			int tempMax = 0; // tempMax用于计算该点的深度，是上下左右四个方向最大的深度
			tempMax = Math.max(findSmallAround(i - 1, j, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i + 1, j, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i, j - 1, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i, j + 1, matrix, cache, cur),
					tempMax);
			cache[i][j] = ++tempMax; // 更新cache数组
			return tempMax;
		}
	}
}
