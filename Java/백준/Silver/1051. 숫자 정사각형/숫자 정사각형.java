import java.util.*;
import java.io.*;

public class Main {
	private static int[][] arr;
	private static int N, M;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < M; j++) 
        		arr[i][j] = str.charAt(j) - '0';
        }		
        
        System.out.print(solution());
    }
    
    private static int solution() {
    	int max = Integer.MIN_VALUE;
    	
    	for (int i = 0; i < (N < M ? N : M); i++) {
    		boolean isTrue = false;
    		for (int x = 0; x < N - i; x++) {
    			for (int y = 0; y < M - i; y++) {
    				if (isVaild(x, y, i) ) {
    					isTrue = true;
    					max = i+1;
    					break;
    				}
    			}
    			if(isTrue) break;
    		}
    	}
    	
    	return max * max;
    }
    
    private static boolean isVaild(int x, int y, int n) {
    	return arr[x][y] == arr[x + n][y]
    			&& arr[x + n][y] == arr[x][y + n]
    			&& arr[x][y + n] == arr[x + n][y + n];
    }
}