import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static Deque<Integer> s = new ArrayDeque();
    
    private static int N;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            switch (st.nextToken()) {
            	case "push" :
            		s.push(Integer.parseInt(st.nextToken()));
            		break;
            	
            	case "pop":
            		if (s.isEmpty()) sb.append(-1 + "\n");
                    else sb.append(s.pop() + "\n");
            		break;
            	
            	case "size":
            		sb.append(s.size() + "\n");
            		break;
            	
            	case "empty":
            		if (s.isEmpty()) sb.append(1 + "\n");
                    else sb.append(0 + "\n");
            		break;
            	
            	case "top":
            		if (s.isEmpty()) sb.append(-1 + "\n");
                    else sb.append(s.peek() + "\n");
            }
        }

        System.out.print(sb);
    }
}
