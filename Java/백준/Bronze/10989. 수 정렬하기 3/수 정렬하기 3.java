import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private final static int[] cnt = new int[10001];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++)
			cnt[Integer.parseInt(br.readLine())]++;
		
		for(int i = 1; i < 10001; i++) {
			while(cnt[i]-- > 0) { sb.append(i).append('\n'); }
		}
		
        System.out.println(sb);
	}
}

