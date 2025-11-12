import java.util.*;
import java.io.*;

public class Main {	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	boolean[][] visited = new boolean[100][100];
    	
    	for (int idx = 0; idx < 4; idx++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		
    		int xL = Integer.parseInt(st.nextToken()), yL = Integer.parseInt(st.nextToken());
    		int xR = Integer.parseInt(st.nextToken()), yR = Integer.parseInt(st.nextToken());
    		
    		for (int i = yL; i < yR; i++)
    			for (int j = xL; j < xR; j++)
    				visited[i][j] = true;
    	}
    	
    	int cnt = 0;
    	for (boolean[] temp : visited)
    		for (boolean flag : temp)
    			if (flag) cnt++;
    	
    	System.out.print(cnt);
    }
}