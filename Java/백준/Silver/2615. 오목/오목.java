import java.util.*;
import java.io.*;

public class Main {
	private static final int[][] arr = new int[19][19];
	
	// (0,1) (1,1) (1, 0) (1, -1)
	private static final int[] dx = {0, 1, 1, 1};
	private static final int[] dy = {1, 1, 0, -1};
	
    public static void main(String[] args) throws Exception {
    	for (int i = 0; i < 19; i++)
    		for (int j = 0; j < 19; j++)
    			arr[i][j] = readInt();
    	
    	int[] ans = solution();
    	if (ans[0] == 0) System.out.print(ans[0]);
    	else System.out.print(ans[0] + "\n" + (ans[1] + 1) + " " + (ans[2] + 1));
    }
    
    private static int[] solution() {
    	for (int i = 0; i < 19; i++) {
    		for (int j = 0; j < 19; j++) {
    			if (arr[i][j] == 0) continue;
    			
    			// 1. 3시
    			if (j == 0 || arr[i][j-1] != arr[i][j]) {
    				if (isVaild(i, j, 1, 0)) return new int[]{arr[i][j], i, j};
    			}
    			
    			// 2. 5시
    			if (j == 0 || i == 0 || arr[i-1][j-1] != arr[i][j]) {
    				if (isVaild(i, j, 1, 1)) return new int[]{arr[i][j], i, j};
    			}
    			
    			// 3. 6시
    			if (i == 0 || arr[i-1][j] != arr[i][j]) {
    				if (isVaild(i, j, 1, 2)) return new int[]{arr[i][j], i, j};
    			}
    			
    			// 4. 7시 
    			if ((j == 18 || i == 0) || arr[i-1][j+1] != arr[i][j]) {
    				if (isVaild(i, j, 1, 3)) return new int[]{arr[i][j], i + 4, j - 4};
    			}
    		}
    	}
    	
    	return new int[]{0};
    }
    
    private static boolean isVaild(int i, int j, int cnt, int dir) {
    	if (!isRange(i + dx[dir], j + dy[dir])) {
    		if (cnt == 5) return true;
    		else return false;
    	} else if (arr[i][j] != arr[i + dx[dir]][j + dy[dir]]) {
    		if (cnt == 5) return true;
    		else return false;
    	} else {
    		if (cnt == 5) return false;
    		else return isVaild(i + dx[dir], j + dy[dir], cnt + 1, dir);
    	}
    }
    
    private static boolean isRange(int i, int j) {
    	return 0 <= i && i < 19 && 0 <= j && j < 19;
    }
    
    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}