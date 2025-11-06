import java.util.*;
import java.io.*;

public class Main {
	private static int[][] arr;
	private static int N,K;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 1;
        
        arr = new int[N][5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			
			arr[i][0] = idx;
			arr[i][1] = Integer.parseInt(st.nextToken()); // 금
			arr[i][2] = Integer.parseInt(st.nextToken()); // 은
			arr[i][3] = Integer.parseInt(st.nextToken()); // 동
		}

		Arrays.sort(arr, (a, b) -> {
			if (a[1] != b[1]) return b[1] - a[1]; // 금
			if (a[2] != b[2]) return b[2] - a[2]; // 은
			return b[3] - a[3]; // 동
		});

		for (int i = 0; i < N; i++) {
			arr[i][4] = i + 1;

			if (i > 0 && arr[i][1] == arr[i-1][1] && arr[i][2] == arr[i-1][2] && arr[i][3] == arr[i-1][3])
				arr[i][4] = arr[i-1][4];

			if (arr[i][0] == K) {
				System.out.println(arr[i][4]);
				break;
			}
		}
    }
}