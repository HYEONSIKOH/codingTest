import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static boolean[] isCons = {
		    false, // a
		    true,  // b
		    true,  // c
		    true,  // d
		    false, // e
		    true,  // f
		    true,  // g
		    true,  // h
		    false, // i
		    true,  // j
		    true,  // k
		    true,  // l
		    true,  // m
		    true,  // n
		    false, // o
		    true,  // p
		    true,  // q
		    true,  // r
		    true,  // s
		    true,  // t
		    false, // u
		    true,  // v
		    true,  // w
		    true,  // x
		    true,  // y
		    true   // z
		};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	while(true) {
    		String str = br.readLine();
    		if (str.equals("end")) break;
    		
    		solution(str);
    	}
    	
    	System.out.print(sb);
    }
    
    private static void solution(String str) {
    	boolean isVaild = isVaildPassword(str);
    	
    	if (isVaild) sb.append('<' + str + "> is acceptable.");
    	else sb.append('<' + str + "> is not acceptable.");
    	
    	sb.append("\n");
    }
    
    private static boolean isVaildPassword(String str) {
    	boolean isIncludeVowel = !isCons[str.charAt(0) - 'a'];
    	
    	int cnt = 1;
    	for (int i = 1; i < str.length(); i++) {
    		boolean isCon =  isCons[str.charAt(i) - 'a'];
    		boolean isBeforeCon = isCons[str.charAt(i - 1) - 'a'];
    		
    		// 1. 모음(a,e,i,o,u) 하나를 반드시 포함
    		if (!isCon) isIncludeVowel = true;
    		
    		// 2. 모음이 3개 혹은 자음이 3개 연속
    		if (isCon == isBeforeCon) {
    			cnt++;
    			
    			if (cnt == 3) return false;
    		} else cnt = 1;
    		
    		
    		// 3. 같은 글자가 연속적으로 두번 오면 안됨 (ee, oo는 허용)
    		char c = str.charAt(i), bc = str.charAt(i - 1);
    		if ((c == bc) && c != 'e' && c != 'o')
    			return false;
    	}
    	
    	return isIncludeVowel;
    }
}