import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static int N, K, cnt = 0;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	arr = new int[N];
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < N; i++)
    		arr[i] = Integer.parseInt(st.nextToken());

    	solution();
    }
    
    private static void solution() {
    	mergeSort(0, N - 1);
        System.out.print(-1);
    }
    
    private static void mergeSort(int s, int e) {
    	if (s < e) {
    		int m = (s + e) / 2;
    		mergeSort(s, m);
    		mergeSort(m + 1, e);
    		merge(s, m, e);
    	}
    }
    
    private static void merge(int s, int m, int e) {
    	int L = s, R = m + 1, size = e - s + 1;
    	int[] temp = new int[size];
    	
    	for (int i = 0; i < size; i++) {
    		if (L > m) temp[i] = arr[R++];    		
    		else if (R > e) temp[i] = arr[L++];
    		else {
    			if (arr[L] < arr[R]) temp[i] = arr[L++];
    			else temp[i] = arr[R++];
    		}
    		
    		cnt++;
    		if (cnt == K) {
    			System.out.print(temp[i]);
    			System.exit(0);
    		}
    	}
    	
    	int idx = 0;
    	for (int i = s; i <= e; i++)
    		arr[i] = temp[idx++];
    }
}