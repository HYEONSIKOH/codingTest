import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int x, y;
    }

    private static int solution(int M, int N, int L, int[] shootingRange, Pair[] animals) {
        Arrays.sort(shootingRange);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (binarySearch(M, L, animals[i], shootingRange)) ans++;
        }

        return ans;
    }

    private static boolean binarySearch(int M, int L, Pair animalTarget, int[] shootingRange) {
        int Left = 0, Right = M - 1;

        while (Left <= Right) {
            int mid = (Left + Right) / 2;
            int distance = Math.abs(animalTarget.x - shootingRange[mid]) + animalTarget.y;

            if (distance <= L) return true;
            else {
                if (animalTarget.x > shootingRange[mid]) Left = mid + 1;
                else if (animalTarget.x < shootingRange[mid]) Right = mid - 1;
                else return false;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] shootingRange = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            shootingRange[i] = Integer.parseInt(st.nextToken());

        Pair[] animals = new Pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Pair P = new Pair();
            P.x = Integer.parseInt(st.nextToken());
            P.y = Integer.parseInt(st.nextToken());
            animals[i] = P;
        }

        bw.write(String.valueOf(solution(M, N, L, shootingRange, animals)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}