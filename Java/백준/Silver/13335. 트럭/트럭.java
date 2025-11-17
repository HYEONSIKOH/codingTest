import java.util.*;
import java.io.*;

public class Main {
	private static int[][] arr;
	private static int N, W, L;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	W = Integer.parseInt(st.nextToken());
    	L = Integer.parseInt(st.nextToken());
    	
    	arr = new int[N][2];
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < N; i++) {
    		arr[i][1] = W;
    		arr[i][0] = Integer.parseInt(st.nextToken());
    	}

    	System.out.print(solution());
    }
    
    private static int solution() {
    	Deque<int[]> q = new ArrayDeque<>();	
    	int time = 0, idx = 0, weightOnBridge = 0;

        while (idx < N || !q.isEmpty()) {
            time++;
            
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] truck = q.pollFirst();
                truck[1]--;

                if (truck[1] == 0) weightOnBridge -= truck[0];
                else q.addLast(truck);
            }

            if (idx < N && weightOnBridge + arr[idx][0] <= L) {
                q.addLast(arr[idx]);            
                weightOnBridge += arr[idx][0];
                idx++;
            }
        }

        return time;
    }
}