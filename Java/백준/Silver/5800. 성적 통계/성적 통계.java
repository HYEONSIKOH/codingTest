import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	
    public static void main(String[] args) throws Exception {
    	StringBuilder sb = new StringBuilder();
    	int N = readInt(), classNum = 1;
    	
    	while(N-- > 0) {
    		sb.append("Class " + classNum++).append('\n');
    		int M = readInt();
    		
    		int[] arr = new int[M];
    		for (int i = 0; i < M; i++)
        		arr[i] = readInt();
    		
    		Arrays.sort(arr);
    		
    		int maxDist = Integer.MIN_VALUE;
    		for (int i = 1; i < M; i++)
    			maxDist = Math.max(maxDist, arr[i] - arr[i-1]);
    		
    		sb.append("Max " + arr[M-1])
    			.append(", Min " + arr[0])
    			.append(", Largest gap " + maxDist)
    			.append('\n');
    	}
    	
    	System.out.print(sb);
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}