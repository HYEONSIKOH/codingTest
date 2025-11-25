import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static final StringBuilder sb = new StringBuilder();
	private static int N;
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	arr = new int[N];
    	for (int i = 0; i < N; i++)
    		arr[i] = readInt();
    	
    	System.out.print(solution());
    }
    
    private static String solution() {
        // 1. 뒤에서부터 arr[i-1] > arr[i] 되는 i 찾기
        int i = N - 1;
        while (i > 0 && arr[i - 1] < arr[i]) i--;
        if (i == 0) return "-1";

        // 2. 뒤에서부터 arr[j] < arr[i-1] 되는 j 찾기
        int j = N - 1;
        while (arr[j] >= arr[i - 1]) j--;

        // 3. swap(arr[i-1], arr[j])
        int tmp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = tmp;

        // 4. suffix(i ~ N-1) 뒤집기
        int l = i, r = N - 1;
        while (l < r) {
            tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            l++;
            r--;
        }

        for (int k = 0; k < N; k++)
            sb.append(arr[k]).append(' ');
        
        return sb.toString();
    }
    
    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}