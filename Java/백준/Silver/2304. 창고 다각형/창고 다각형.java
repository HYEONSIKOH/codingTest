import java.util.*;
import java.io.*;

public class Main {
	private static int[][] arr;
	private static int N;
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	arr = new int[N][2];
    	for (int i = 0; i < N; i++) {
    		arr[i][0] = readInt();
    		arr[i][1] = readInt();
    	}
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
        int[] height = new int[1001];
        int maxHeight = Integer.MIN_VALUE, maxHeightIdx = 0;
        int startIdx = Integer.MAX_VALUE, endIdx = Integer.MIN_VALUE;

        for (int[] a : arr) {
            height[a[0]] = a[1];
            if (a[1] > maxHeight) {  
                maxHeight = a[1];
                maxHeightIdx = a[0];
            }
            startIdx = Math.min(startIdx, a[0]);
            endIdx = Math.max(endIdx, a[0]);
        }

        int ans = 0;

        // 왼쪽 -> 최대 기둥까지
        int temp = 0;
        for (int i = startIdx; i <= maxHeightIdx; i++) {
            temp = Math.max(temp, height[i]);
            ans += temp;
        }

        // 오른쪽 -> 최대 기둥까지
        temp = 0;
        for (int i = endIdx; i > maxHeightIdx; i--) {
            temp = Math.max(temp, height[i]);
            ans += temp;
        }

        return ans;
    }
    
    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}