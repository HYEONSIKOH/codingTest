import java.util.*;
import java.io.*;

public class Main {
    private static int solution(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            int s = arr[i][0];
            while(!pq.isEmpty() && pq.peek()[1] <= s){
                s = pq.peek()[0];
                pq.poll();
            }
            pq.add(arr[i]);
        }

        return pq.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solution(arr)));

        bw.flush();
        bw.close();
    }
}