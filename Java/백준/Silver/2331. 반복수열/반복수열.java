import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int A = Integer.parseInt(st.nextToken());
    	int N = Integer.parseInt(st.nextToken());
    	
    	System.out.print(solution(A, N));
    }
    
    private static int solution(int A, int N) {
    	int MAX_RANGE = (int)Math.pow(9, 5) * 5 + 1, ans = 0;
    	boolean[] visited = new boolean[MAX_RANGE];
    	int[] arr = new int[MAX_RANGE];
    	Arrays.fill(arr, -1);
    	
    	int num = A, idx = 0;
    	while(!visited[num]) {
    		visited[num] = true;
    		arr[idx++] = num;
    		num = function(num, N);
    	}
    	
    	for(int i : arr) {
    		if (i == num || i == -1) break;
    		else ans++;
    	}
    	
    	return ans;
    }
    
    private static int function(int num, int N) {
    	double result = 0;
    	while (num != 0) {
    		result += Math.pow(num % 10, N);
    		num /= 10;
    	}
    	
    	return (int)result;
    }
}