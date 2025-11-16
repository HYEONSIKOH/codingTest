import java.util.*;
import java.io.*;

public class Main {
	private static Object[][] arr;
	private static int N;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	arr = new Object[N][4];
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		String name = st.nextToken();
    		int d = Integer.parseInt(st.nextToken());
    		int m = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
    		
    		arr[i] = new Object[]{name, d, m, y};
    	}

    	System.out.print(solution());
    }
    
    private static String solution() {
    	Arrays.sort(arr, (a, b) -> {
    		int y1 = (Integer) a[3];
            int y2 = (Integer) b[3];
            if (y1 != y2) return Integer.compare(y1, y2);

            int m1 = (Integer) a[2];
            int m2 = (Integer) b[2];
            if (m1 != m2) return Integer.compare(m1, m2);

            int d1 = (Integer) a[1];
            int d2 = (Integer) b[1];
            return Integer.compare(d1, d2);
    	});
    	
    	return arr[N-1][0] + "\n" + arr[0][0];
    }
}