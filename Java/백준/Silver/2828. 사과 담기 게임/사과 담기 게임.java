import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static int M, N, J;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	J = Integer.parseInt(br.readLine());
    	
    	arr = new int[J];
    	for (int i = 0; i < J; i++)
    		arr[i] = Integer.parseInt(br.readLine());
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
    	int S = 1, E = M, ans = 0;
    	for (int i = 0; i < J; i++) {
    		int APPLE = arr[i];
    		int moveDist = 0;
    		
    		if (S <= APPLE && APPLE <= E) continue;
    		else if (E < APPLE) {
    			moveDist = APPLE - E;
    			S += moveDist;
    			E += moveDist;
    		} else if (APPLE < S) {
    			moveDist = S - APPLE;
    			S -= moveDist;
    			E -= moveDist;
    		}
    		
    		ans += moveDist;
    	}
    	
    	return ans;
    }
}