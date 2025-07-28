import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {
    private static int ansCost = MAX_VALUE;
    private static int[] baseline;
    private static StringBuilder res = new StringBuilder();

    private static void solution(int N, int[][] ingredients) throws IOException {
        int visited = 0;

        for (int i = 0; i < N; i++)
            backtrack(N, i, 1, visited | (1 << i), 0, 0, 0, 0, 0, ingredients);
    }

    private static void backtrack(int N, int idx, int cnt, int visited, int a, int b, int c, int d, int cost, int[][] ingredients) throws IOException {
        if (cnt > N) return;

        a += ingredients[idx][0];
        b += ingredients[idx][1];
        c += ingredients[idx][2];
        d += ingredients[idx][3];
        cost += ingredients[idx][4];

        if (a >= baseline[0] && b >= baseline[1] && c >= baseline[2] && d >= baseline[3]) {
            if (ansCost > cost) {
                ansCost = cost;

                res.setLength(0);
                res.append(ansCost).append("\n");

                for (int i = 0; i < ingredients.length; i++) {
                    if ((visited & (1 << i)) != 0) {
                        res.append(i + 1).append(" ");
                    }
                }
            }
        }

        for (int i = idx + 1; i < N; i++) {
            if ((visited & (1 << i)) == 0)
                backtrack(N, i, cnt + 1, visited | (1 << i), a, b, c, d, cost, ingredients);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = parseInt(br.readLine());

        baseline = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            baseline[i] = parseInt(st.nextToken());

        int[][] ingredients = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++)
                ingredients[i][j] = parseInt(st.nextToken());
        }

        solution(N, ingredients);

        if (ansCost == MAX_VALUE) bw.write("-1");
        else bw.write(res.toString());

        // System.gc();
        bw.flush();
        bw.close();
    }
}