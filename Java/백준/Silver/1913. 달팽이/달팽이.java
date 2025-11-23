import java.util.*;
import java.io.*;

public class Main {
	private static int[][] arr;
	private static int N, M;
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	M = readInt();
    	arr = new int[N][N];
    	
    	solution();
    }
    
    private static void solution() {
    	int[] startPos = { N / 2, N / 2 };
    	int[] ansPos = { (N / 2) + 1, (N / 2) + 1 };
    	int num = 1, dist = 1, n = 0;
    	
    	arr[N / 2][N / 2] = num++;
    	startPos[0]--;
    	
    	int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    	int[] pos = {startPos[0], startPos[1]};
    	while(true) {
    		//System.out.println(pos[0] + " " + pos[1]);
    		
    		arr[pos[0]][pos[1]] = num++;
    		if (num - 1 == M) {
    			ansPos[0] = pos[0] + 1;
    			ansPos[1] = pos[1] + 1;
    		}
    		
    		// 다음 좌표
    		int nx = pos[0] + dx[n];
    		int ny = pos[1] + dy[n];
    		if (!isVaild(nx, ny, dist)) {
    			n = (n + 1) % 4;
    			nx = pos[0] + dx[n];
        		ny = pos[1] + dy[n];
    		}
    		
    		// 다음 좌표가 (0, 1)이면 종
    		if (nx == 0 && ny == 1) break;
    		
    		// 다음 좌표가 시작 좌표랑 같으면 다음 라인
    		else if (nx == startPos[0] && ny == startPos[1]) {
    			dist++;
    			nx = --startPos[0];
    			ny = --startPos[1];
    			n = 0;
    		}
    		
    		pos[0] = nx;
    		pos[1] = ny;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for (int a[] : arr) {
    		for (int i : a)
    			sb.append(i).append(" ");
    		sb.append("\n");
    	}
    	
    	sb.append(ansPos[0]).append(" ").append(ansPos[1]);
    	
    	System.out.print(sb);
    }
    
    private static boolean isVaild(int x, int y, int targetDist) {
        if (!(0 <= x && x < N && 0 <= y && y < N))
            return false;

        int cx = N / 2;
        int cy = N / 2;

        int dist = Math.max(Math.abs(x - cx), Math.abs(y - cy));
        return dist == targetDist;
    }
    
    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}