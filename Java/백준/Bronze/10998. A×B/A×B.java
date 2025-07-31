import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = parseInt(st.nextToken());
        int b = parseInt(st.nextToken());

        bw.write(String.valueOf(a * b));

        // System.gc();
        bw.flush();
        bw.close();
    }
}