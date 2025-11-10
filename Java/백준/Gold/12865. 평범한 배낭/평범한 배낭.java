import java.util.*;
import java.io.*;

public class Main {
	private static int[][] bags;
	private static int N, K;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	bags = new int[N][2];
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		bags[i][0] = Integer.parseInt(st.nextToken());
    		bags[i][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
    	int[] dp = new int[K + 1];
    	
    	for (int i = 1; i <= N; i++) {
    		int WEIGHT = bags[i-1][0];
    		int VALUE = bags[i-1][1];
    		
    		for (int j = K; j >= WEIGHT; j--)
    	        dp[j] = Math.max(dp[j], dp[j - WEIGHT] + VALUE);
    	}
    	
    	return dp[K];
    }
}