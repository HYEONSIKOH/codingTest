import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int count = 1, range = 2;

        if (N == 1) bw.write('1');
        else {
            while (range <= N) {
                range = range + (6 * count);
                count++;
            }
            bw.write(String.valueOf(count));
        }

        bw.flush();
    }
}