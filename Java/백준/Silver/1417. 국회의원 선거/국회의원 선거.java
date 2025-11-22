import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static int N;
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	arr = new int[N];
    	for (int i = 0; i < N; i++)
    		arr[i] = readInt();
    	
    	System.out.print(solution());
    }
    
    private static int solution() {
    	if (N == 1) return 0;
    	
    	int cnt = 0;
    	while(true) {
    		int[] temp = findMax();
    		if (temp[1] < arr[0]) break;
    		else {
    			arr[0]++;
    			arr[temp[0]]--;
    			cnt++;
    		}
    	}
    	
    	return cnt;
    }
    
    private static int[] findMax() {
    	int[] ans = {-1, -1};
    	for (int i = 1; i < N; i++) {
    		if (ans[1] < arr[i]) {
    			ans[1] = arr[i];
    			ans[0] = i;
    		}
    	}
    	
    	return ans;
    }
    
    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}