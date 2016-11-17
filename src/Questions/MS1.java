package Questions;
import java.util.Scanner;


public class MS1 {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        int odd = 0, even = 0;
        for(int i = 0; i < n; i++) {
        	nums[i] = in.nextInt();
        }
        for(int i = 0; i < n; i++) {
        	if(nums[i] % 2 == 0){
        		even++;
        	} else {
        		odd++;
        	}
        }
        int res = n - 2 * Math.min(odd, even);
        System.out.println(res);
        in.close();
    }
}
