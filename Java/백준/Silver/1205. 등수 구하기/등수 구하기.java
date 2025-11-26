import java.util.*;
import java.io.*;

public class Main {
	private static Integer[] arr;
	private static int N, G, P;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	G = Integer.parseInt(st.nextToken());
    	P = Integer.parseInt(st.nextToken());
    	
    	arr = new Integer[N];
    	if (N == 0) {
    		System.out.print(1);
    		System.exit(0);
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < N; i++)
    		arr[i] = Integer.parseInt(st.nextToken());
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
    	Arrays.sort(arr, (a, b) -> Integer.compare(b, a));

        if (N == P && arr[N - 1] >= G) return -1;

        int rank = 1;
        for (int i = 0; i < N; i++) {
            if (arr[i] > G) rank++;
            else break;
        }
        
        return rank;
    }
}