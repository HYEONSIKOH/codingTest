import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {
    private static List<Long> list = new ArrayList<>();

    private static Long solution(int N) {
        if (N <= 10) return (long) N;

        list.add(0L);
        for (long i = 1L; i < 10L; i++)
            backtrack(i, i);

        Collections.sort(list);

        return list.size() > N ? list.get(N) : -1L;
    }

    private static void backtrack(long num, long curNum) {
        list.add(num);

        for (long i = curNum - 1; i >= 0; i--) {
            long nextNum = num * 10 + i;
            backtrack(nextNum, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int N = parseInt(br.readLine());

        bw.write(String.valueOf(solution(N)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}