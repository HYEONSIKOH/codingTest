import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static int N;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	
    	arr = new int[N];
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < N; i++)
    		arr[i] = Integer.parseInt(st.nextToken());
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
    	if (N == 1) return 1;
    	
    	int ans = 1;
    	
    	// 가장 긴 오름차순
    	int cnt = 0;
    	for (int i = 0; i < N; i++) {
    		if (i != N-1 && arr[i] <= arr[i+1]) cnt++;
    		else {
    			ans = Math.max(cnt + 1, ans);
    			cnt = 0;
    		}
    	}
    	
    	// 가장 긴 내림차순
    	cnt = 0;
    	for (int i = 0; i < N; i++) {
    		if (i != N-1 && arr[i] >= arr[i+1]) cnt++;
    		else {
    			ans = Math.max(cnt + 1, ans);
    			cnt = 0;
    		}
    	}
    	
    	return ans;
    }
}