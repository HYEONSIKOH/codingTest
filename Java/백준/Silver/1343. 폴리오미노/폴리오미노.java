import java.util.*;
import java.io.*;

public class Main {
	private static final StringBuilder sb = new StringBuilder();
	private static String str;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	str = br.readLine();
    	
    	solution();
    	System.out.print(sb);
    }
    
    private static void solution() {
    	char[] chs = str.toCharArray();
    	
    	int cnt = 0;
    	for (char c : chs) {
    		if (c == 'X') cnt++;
    		if (c == '.') {
    			if (cnt % 2 != 0) {
    				sb.setLength(0);
    				sb.append(-1);
    				return;
    			}
    			
    			append(cnt);
    			sb.append('.');
    			cnt = 0;
    		}
    	}
    	
    	if (cnt % 2 != 0) {
			sb.setLength(0);
			sb.append(-1);
			return;
		}
    	
    	append(cnt);
    }
    
    private static void append(int cnt) {
    	while (cnt > 0) {
			if (cnt >= 4) sb.append("AAAA");
			else sb.append("BB");
			
			cnt -= 4;
		}
    }
}