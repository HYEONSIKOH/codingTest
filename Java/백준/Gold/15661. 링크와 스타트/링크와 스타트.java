import java.util.*;
import java.io.*;

public class Main {

    private static int getSum(List<Integer> list, int[][] arr) {
        int result = 0 ;

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                result += arr[list.get(i)][list.get(j)];
                result += arr[list.get(j)][list.get(i)];
            }
        }

        return result;
    }

    private static int solution(int N, int[][] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < Math.pow(2, N); i++) {
            char[] bin = Integer.toBinaryString(i).toCharArray();

            List<Integer> start = new ArrayList<>(); // 0은 스타트
            List<Integer> link = new ArrayList<>();  // 1은 링크
            for (int j = 0; j < N; j++) {
                if (j + 1 > bin.length){
                    start.add(j);
                    continue;
                }

                if (bin[j] == '1') link.add(j);
                else start.add(j);
            }

            min = Math.min(min, Math.abs(getSum(start, arr) - getSum(link, arr)));
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solution(N, arr)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}