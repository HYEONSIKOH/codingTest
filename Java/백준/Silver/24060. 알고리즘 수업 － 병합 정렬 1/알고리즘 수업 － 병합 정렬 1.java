import java.io.*;

public class Main {
	private static int[] arr;
	private static int N, K, cnt = 0;
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	K = readInt();
    	
    	arr = new int[N];
    	for (int i = 0; i < N; i++)
    		arr[i] = readInt();

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
    
    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}