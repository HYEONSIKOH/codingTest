import java.util.*;
import java.io.*;

public class Main {
	private static char[][] arr;
	private static int N, M;
	
	private static final StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	arr = new char[N][M];
    	for (int i = 0; i < N; i++)
    		arr[i] = br.readLine().toCharArray();
    	
    	solution();
    	System.out.print(sb);
    }
    
    private static void solution() {
    	int ans = 0;
    	
    	for (int i = 0; i < M; i++) {
    		char conChar = getConsensusChar(i);
    		
    		for (int j = 0; j < N; j++)
    			if (conChar != arr[j][i]) ans++;
    		
    		sb.append(conChar);
    	}
    	
    	sb.append('\n').append(ans);
    }
    
    private static char getConsensusChar(int idx) {
    	int[] visitedChar = new int[26];
    	for (int i = 0; i < N; i++)
    		visitedChar[arr[i][idx] - 'A']++;
    	
    	int ansIdx = -1, max = -1;
    	for (int i = 0; i < 26; i++) {
    		if (max < visitedChar[i]) {
    			max = visitedChar[i];
    			ansIdx = i;
    		}
    	}
    	
    	return (char)(ansIdx + 'A');
    }
}