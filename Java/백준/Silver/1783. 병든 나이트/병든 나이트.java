import java.util.*;
import java.io.*;

public class Main {
	private static int M, N;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
        if (N == 1) return 1;
        else if (N == 2) return Math.min(4, ((M + 1) / 2));
        else if (M < 7) return Math.min(4, M);
        else if(M >= 7) return M - 2;
        else return -1;
    }
}