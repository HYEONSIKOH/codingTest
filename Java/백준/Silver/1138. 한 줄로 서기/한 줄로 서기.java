import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static int N;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st /*= new StringTokenizer(br.readLine())*/;
    	
    	N = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	
    	arr = new int[N];
    	for (int i = 0; i < N; i++)
    		arr[i] = Integer.parseInt(st.nextToken());
    	
    	System.out.print(solution());
    }
    
    private static StringBuilder solution() {
    	StringBuilder ans = new StringBuilder();
    	List<Integer> temp = new ArrayList<>();
    	
    	for (int i = N-1; i >= 0; i--)
    		temp.add(arr[i], i + 1);
    	
    	for (int i = 0; i < N; i++)
            ans.append(temp.get(i)).append(' ');
    	
    	return ans;
    }
}