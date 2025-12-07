import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String str = br.readLine();
    	
    	if (str.length() == 1) {
    		System.out.println(0);
        	System.out.print(Integer.parseInt(str) % 3 == 0 ? "YES" : "NO");
            System.exit(0);
    	}
    	
    	long cnt = 1, N = 0;
    	for(char c : str.toCharArray())
    		N += c - '0';
    	
    	while(N >= 10) {
    		cnt++;
    		long sum = 0, temp = N;
    		
    		while(temp != 0) {
    			sum += temp % 10;
    			temp /= 10;
    		}
    		
    		N = sum;
    	}
    	
    	System.out.println(cnt);
    	System.out.print(N % 3 == 0 ? "YES" : "NO");
    }
}