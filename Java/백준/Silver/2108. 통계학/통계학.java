import java.util.*;
import java.io.*;

public class Main {
    private static int[] arr;

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        solution();
    }

    private static void solution() {
        Arrays.sort(arr);

        System.out.print(
                average() + "\n" +
                median() + "\n" +
                mode() + "\n" +
                range()
        );
    }

    // 1. 산술평균: N개의 수들의 합을 N으로 나눈 값 (첫째 자리에서 반올림)
    private static long average() {
        double sum = 0;
        for (int i : arr) sum += i;

        return Math.round(sum / N);
    }

    // 2. 중앙값: N개의 수들을 증가하는 순서로 나열했을 경우, 그 중앙에 위치하는 값
    private static int median() {
        return arr[N / 2];
    }

    // 3. 최빈값: N개의 수들 중 가장 많이 나타나는 값 (여러 개 있을 때, 최빈값 중 두 번째로 작은 값)
    private static int mode() {
        int[][] cnts = new int[8001][2];

        // 양수
        for (int i = 0; i <= 4000; i++) {
            cnts[i][0] = i; // 숫자
            cnts[i][1] = 0; // 빈도수
        }

        // 음수
        for (int i = 4001; i <= 8000; i++) {
            cnts[i][0] = -i + 4000; // 숫자
            cnts[i][1] = 0; // 빈도수
        }

        for (int i : arr) {
            if (i >= 0) cnts[i][1]++;
            else cnts[-i + 4000][1]++;
        }

        Arrays.sort(cnts, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            else return b[1] - a[1];
        });

        return (cnts[0][1] == cnts[1][1]) ? cnts[1][0] : cnts[0][0];
    }

    // 4. 범위: N개의 수들 중 최댓값과 최솟값의 차이
    private static int range() {
        return arr[N - 1] - arr[0];
    }

}