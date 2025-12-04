import java.util.*;
import java.io.*;

public class Main {
	private static int M, N, J;
	
    public static void main(String[] args) throws Exception {
    	N = readInt();
    	M = readInt();
    	J = readInt();
    	
    	int[][] arr = new int[J][];
    	for (int i = 0; i < J; i++)
    		arr[i] = new int[]{readInt(), readInt()};
    	
    	System.out.print(solution(arr));
    }
    
    private static int solution(int[][] arr) {
    	List<Integer> cutWidth = new ArrayList<>();
    	List<Integer> cutLength = new ArrayList<>();
    	for (int i = 0; i < J; i++) {
    		if (arr[i][0] == 0) cutWidth.add(arr[i][1]);
    		else cutLength.add(arr[i][1]);
    	}
    	
    	cutWidth.add(0);
    	cutWidth.add(M);
    	
    	cutLength.add(0);
    	cutLength.add(N);
    	
    	cutWidth.sort(null);
    	cutLength.sort(null);
    	
    	// 1. 최장 가로 구하기
    	int maxWidth = -1;
    	for (int i = 1; i < cutWidth.size(); i++)
    		maxWidth = Math.max(maxWidth, (cutWidth.get(i) - cutWidth.get(i-1)));
    	
    	// 2. 최장 세로 구하기 
    	int maxLength = -1;
    	for (int i = 1; i < cutLength.size(); i++)
    		maxLength = Math.max(maxLength, (cutLength.get(i) - cutLength.get(i-1)));
    	
    	return maxLength * maxWidth;
    }
    
    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}