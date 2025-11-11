import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static int N, M;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st /*= new StringTokenizer(br.readLine())*/;
    	StringBuilder sb = new StringBuilder();
    	
    	int T = Integer.parseInt(br.readLine());
    	while (T-- > 0) {
    		N = Integer.parseInt(br.readLine());
    		arr = new int[N];
    		
    		st = new StringTokenizer(br.readLine());
    		for (int i = 0; i < N; i++) 
    			arr[i] = Integer.parseInt(st.nextToken());
    		
    		M = Integer.parseInt(br.readLine());
    		
    		sb.append(solution()).append("\n");
    	}
    	
    	System.out.print(sb);
    }
    
    private static int solution() {
    	int[] dp = new int[M + 1];
    	
    	for (int i = 0; i < N; i++) {    		
    		if(arr[i] <= M) dp[arr[i]]++;
    		
    		for (int j = arr[i] + 1; j <= M; j++) {
    	        if (dp[j - arr[i]] != 0) dp[j] += dp[j - arr[i]];
    		}
    	}
    	
    	return dp[M];
    }
}