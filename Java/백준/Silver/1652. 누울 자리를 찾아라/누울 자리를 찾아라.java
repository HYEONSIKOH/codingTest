import java.util.*;
import java.io.*;

public class Main {
	private static boolean[][] arr;
	private static int N;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	N = Integer.parseInt(br.readLine());
    	arr = new boolean[N + 2][N + 2];
    	for (int i = 0; i < N; i++) {
    		char[] cs = br.readLine().toCharArray();
    		
    		for (int j = 0; j < N; j++)
    			arr[i][j] = (cs[j] == '.');
    		
    		arr[i][N] = false;
    		arr[i][N+1] = false;
    		arr[N][i] = false;
    		arr[N+1][i] = false;
    	}
    	
    	System.out.print(solution());
    }
    
    private static String solution() {
    	int r = 0, c = 0;
    	
    	for (int i = 0; i < N; i++) {
    	    for (int j = 0; j < N - 1; j++) {
    	        if (arr[i][j] && arr[i][j + 1] && !arr[i][j + 2]) r++;
    	        if (arr[j][i] && arr[j + 1][i] && !arr[j + 2][i]) c++;
    	    }
    	}
    	
    	return r + " " + c;
    }
}