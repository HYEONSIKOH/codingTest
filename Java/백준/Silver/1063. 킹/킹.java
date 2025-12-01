import java.util.*;
import java.io.*;

public class Main {
	private static int[] king;
	private static int[] stone;
	private static String[] moveArr;
	private static int N;
	
	private static final StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 왕의 위치 
    	char[] temp = st.nextToken().toCharArray();
    	king = new int[]{temp[0] - 'A', temp[1] - '1'};
    	
    	// 돌의 위치 
    	temp = st.nextToken().toCharArray();
    	stone = new int[]{temp[0] - 'A', temp[1] - '1'};
    	
    	// 횟수 
    	N = Integer.parseInt(st.nextToken());
    	moveArr = new String[N];
    	
    	for (int i = 0; i < N; i++)
    		moveArr[i] = br.readLine();
    	
    	solution();
    	System.out.print(sb);
    }
    
    private static void solution() {
    	int[] dx = { 1, -1,  0,  0,  1, -1,  1, -1};
    	int[] dy = { 0,  0, -1,  1,  1,  1, -1, -1};
    	
    	for (String str : moveArr) {
    		int idx = getMoveIdx(str);
    		
    		// 다음 왕의 위
    		int nxKing = king[0] + dx[idx];
    		int nyKing = king[1] + dy[idx];
    		
    		// 다음 돌의 위치
    		int nxStone = stone[0] + dx[idx];
    		int nyStone = stone[1] + dy[idx];
    		
    		if (isVaild(nxKing, nyKing)) {
    			if (nxKing == stone[0] && nyKing == stone[1]) {
    				if (isVaild(nxStone, nyStone)) {
    					stone[0] = nxStone;
    					stone[1] = nyStone;
    					king[0] = nxKing;
    	    			king[1] = nyKing;
    				}
    			} else {
    				king[0] = nxKing;
        			king[1] = nyKing;
    			}
    		}
    	}
    	
    	sb.append((char)('A' + king[0])).append(king[1] + 1).append('\n');
    	sb.append((char)('A' + stone[0])).append(stone[1] + 1);
    }
    
    private static int getMoveIdx(String str) {
    	switch(str) {
    		case "R": return 0;
    		case "L": return 1;
    		case "B": return 2;
    		case "T": return 3;
    		case "RT": return 4;
    		case "LT": return 5;
    		case "RB": return 6;
    		case "LB": return 7;
    		default : return -1;
    	}
    }
    
    private static boolean isVaild(int x, int y) {
    	return 0 <= x && x < 8 && 0 <= y && y < 8;
    }
}