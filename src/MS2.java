import java.util.Arrays;
import java.util.Scanner;

public class MS2 {
	private final static int CHAR_SIZE = 30;
	private static boolean[][] adj = new boolean[CHAR_SIZE][CHAR_SIZE];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] dp = new int[n+1];
		int max = Integer.MIN_VALUE;
		String str = in.next();
		int m = in.nextInt();
		
		for (int i = 0; i < m; i++) {
			char[] ps = in.next().toCharArray();
			adj[ps[0] - 'a'][ps[1] - 'a'] = true;
			adj[ps[1] - 'a'][ps[0] - 'a'] = true;
		}
		
		char[] cs = str.toCharArray();
		int[] check = new int[CHAR_SIZE];
		Arrays.fill(check, 0);
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for(int j = 0; j < 26; j++) {
				if(!adj[cs[i] - 'a'][j] && check[j] > 0){
					dp[i] = Math.max(dp[i], check[j] + 1);
				}
			}
			check[cs[i] - 'a'] = Math.max(check[cs[i] - 'a'], dp[i]);
			max = Math.max(max, dp[i]);
		}
		System.out.println(n - max);
		in.close();
	}
}