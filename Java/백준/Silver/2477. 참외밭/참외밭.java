import java.util.*;
import java.io.*;

public class Main {
	private static int[][] arr;
	private static int N;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st /*= new StringTokenizer(br.readLine())*/;
    	
    	N = Integer.parseInt(br.readLine());
    	
    	arr = new int[6][2];
    	for (int i = 0; i < 6; i++) {
    		st = new StringTokenizer(br.readLine());
    		arr[i][0] = Integer.parseInt(st.nextToken());
    		arr[i][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
    	return N * (calcBigArea() - calcSmallArea());
    }
    
    private static int calcBigArea() {
    	int H = Integer.MIN_VALUE, W = Integer.MIN_VALUE;
    	
    	for (int i = 0; i < 6; i++) {
    		int dir = arr[i][0], length = arr[i][1];
    		
    		if (dir == 1 || dir == 2) W = Math.max(W, length);
    		if (dir == 3 || dir == 4) H = Math.max(H,  length);
    	}
    	
    	return W * H;
    }
    
    private static int calcSmallArea() {
    	for (int i = 0; i <= 12 - 4; i++) {
    		if (arr[i % 6][0] == arr[(i + 2) % 6][0] && arr[(i + 1) % 6][0] == arr[(i + 3) % 6][0])
    			return arr[(i + 2) % 6][1] * arr[(i + 1) % 6][1];
    	}
    	
    	return 0;
    }
}