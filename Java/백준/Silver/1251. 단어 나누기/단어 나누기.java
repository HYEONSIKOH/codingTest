import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	//StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	String str = br.readLine();
    	
    	System.out.print(solution(str));
    }
    
    private static String solution(String str) {
    	int N = str.length();
    	String ans = null;
    	
    	for (int i = 1; i < N - 1; i++) {
    		for (int j = i + 1; j < N; j++) {
    			String temp = function(i, j, str);
    			if (ans == null || temp.compareTo(ans) < 0) {
    	            ans = temp;
    	        }
    		}
    	}
    	
    	return ans;
    }
    
    private static String function(int i, int j, String str) {
    	StringBuilder sb = new StringBuilder();
    	int N = str.length();
    	
    	sb.append(new StringBuilder(str.substring(0, i)).reverse());
    	sb.append(new StringBuilder(str.substring(i, j)).reverse());
    	sb.append(new StringBuilder(str.substring(j, N)).reverse());
    	
    	return sb.toString();
    }
}