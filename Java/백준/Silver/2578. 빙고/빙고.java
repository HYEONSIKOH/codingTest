import java.util.*;
import java.io.*;

public class Main {
	private static final int[][] bingo = new int[5][5];
	private static final int[][] callNumbers = new int[5][5];
	private static final boolean[][] visited = new boolean[5][5];
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	for (int i = 0; i < 5; i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		for (int j = 0; j < 5; j++)
    			bingo[i][j] = Integer.parseInt(st.nextToken());
    	}
    	
    	for (int i = 0; i < 5; i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		for (int j = 0; j < 5; j++)
    			callNumbers[i][j] = Integer.parseInt(st.nextToken());
    	}
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
    	int cnt = 0;
    	for (int i = 0; i < 5; i++) {
    		for (int j = 0; j < 5; j++) {
    			int[] pos = searchNum(callNumbers[i][j]);
    			
    			if (pos != null) {
    				visited[pos[0]][pos[1]] = true;
    				cnt += bingoFun(pos);
    				if (cnt >= 3) return i * 5 + j + 1;
    			}
    		}
    	}
    	
    	return 0;
    }
    
    private static int[] searchNum(int num) {
    	for (int i = 0; i < 5; i++)
    		for (int j = 0; j < 5; j++)
    			if (bingo[i][j] == num) return new int[]{i, j};
    	
    	return null;
    }
    
    private static int bingoFun(int[] pos) {
    	int X = pos[0], Y = pos[1], cnt = 0;
    	
    	for (int i = 0; i < 5; i++) {
    		if (!visited[i][Y]) break;
    		if (i == 4) cnt++;
    	}
    	
    	for (int i = 0; i < 5; i++) {
    		if (!visited[X][i]) break;
    		if (i == 4) cnt++;
    	}
    	
    	if (X == Y) {
    		for (int i = 0; i < 5; i++) {
        		if (!visited[i][i]) break;
        		if (i == 4) cnt++;
        	}
    	}
    	
    	if (X + Y == 4) {
    		for (int i = 0; i < 5; i++) {
        		if (!visited[i][4-i]) break;
        		if (i == 4) cnt++;
        	}
    	}
    	
    	return cnt;
    }
}