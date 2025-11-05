import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        solution();
        System.out.print(sb);
    }
    
    private static void solution() {
    	if (N == 1) {
    		sb.append(1);
    		return;
    	}
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	
    	for (int i = 1; i <= N; i++) q.add(i);
    	
    	while (true) {
    		sb.append(q.poll() + " ");
    		if (q.size() == 1) {
    			sb.append(q.poll());
    			break;
    		}
    		
    		q.add(q.poll());
    	}
    }
}