package Alogrithm;

public class MatrixTraversal {

	// 对角线的规律是i和j的差值是固定的
	public static void upperTraversalWhenUsingDP(int[][] matrix) {
		int m = matrix.length; // row
		int n = matrix[0].length; // column
		for (int len = n - 1; len >= 0; len--) {
			int diff = n - len - 1;
			for (int j = 0; j < n; j++) {
				int i = j - diff;
				if (i >= 0 && i < m && j >= 0 && j < n)
					System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 反对角线的规律是i和j的和是固定的
	public static void backDiagonalTraversal(int[][] matrix) {
		int m = matrix.length; // row
		int n = matrix[0].length; // column
		for (int len = 0; len < n; len++) {
			int sum = len;
			for (int i = 0; i < n; i++) {
				int j = sum - i;
				if (i >= 0 && i < m && j >= 0 && j < n)
					System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { 
				{ 0, 1, 2, 3 }, 
				{ 4, 5, 6, 7 },
				{ 8, 9, 10, 11 }, 
				{ 12, 13, 14, 15 } };

		upperTraversalWhenUsingDP(matrix);
		//backDiagonalTraversal(matrix);
	}
}
