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
		// ��������˾���߽磬�ݹ����
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
			return 0;
		}

		// ���δ������һ���㣬�ݹ����
		if (matrix[i][j] <= pre) {
			return 0;
		}

		// cache�����˸õ����ȣ�����������ֱ�����
		if (cache[i][j] > 0) {
			return cache[i][j];
		} else { // �õ����û�б������
			int cur = matrix[i][j]; // ��¼��ǰ��ֵ
			int tempMax = 0; // tempMax���ڼ���õ����ȣ������������ĸ������������
			tempMax = Math.max(findSmallAround(i - 1, j, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i + 1, j, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i, j - 1, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i, j + 1, matrix, cache, cur),
					tempMax);
			cache[i][j] = ++tempMax; // ����cache����
			return tempMax;
		}
	}
}
