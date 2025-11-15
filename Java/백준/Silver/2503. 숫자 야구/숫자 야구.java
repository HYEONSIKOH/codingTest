import java.util.*;
import java.io.*;

public class Main {
	private static int[][] arr;
	private static int N;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	arr = new int[N][];
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		int number = Integer.parseInt(st.nextToken());
    		int s = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		arr[i] = new int[]{number, s, b};
    	}

    	System.out.print(solution());
    }
    
    private static int solution() {
    	boolean[] visited = new boolean[1000];
    	Arrays.fill(visited, true);
    	
    	for (int i = 102; i < 1000; i++)
    		visited[i] = isDueple(i);
    	
    	for (int idx = 0; idx < N; idx++) {
    		int n = arr[idx][0];
    		int s = arr[idx][1];
    		int b = arr[idx][2];
    		
    		for (int i = 102; i < 1000; i++) {
    			int[] temp = playball(n, i);
    			
    			if (visited[i] && (temp[0] != s || temp[1] != b))
    				visited[i] = false;
    		}
    	}
    	
    	int cnt = 0;
    	for (int i = 102; i < 1000; i++) {
    		if (visited[i]) cnt++;
    	}
    	
    	return cnt;
    }
    
    private static boolean isDueple(int num) {
    	int[] s1 = decom(num);
    	
    	for (int i = 0; i < 3; i++) {
    		if (s1[i] == 0) return false;
    		
    		for (int j = i + 1; j < 3; j++)
    			if (s1[i] == s1[j])
    				return false;
    	}
    	
    	return true;
    }
    
    private static int[] playball(int n1, int n2) {
    	int s = 0, b = 0;
    	
    	int[] s1 = decom(n1);
    	int[] s2 = decom(n2);
    	
    	for (int i = 0; i < 3; i++) {
    		if (s1[i] == s2[i]) s++;
    	}
    	
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++)
    			if (i != j && s1[i] == s2[j])
    				b++;
    	}
    	
    	return new int[]{s, b};
    }
    
    private static int[] decom(int num) {
    	int[] ans = new int[3];
    	
    	ans[2] = num % 10;
    	num /= 10;
    	
    	ans[1] = num % 10;
    	num /= 10;
    	
    	ans[0] = num;
    	
    	return ans;
    }
}