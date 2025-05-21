import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void solution(int H, int W, int[] arr) {
        List<int []> list = new ArrayList<>();
        int ans = 0;

        for (int i = 0; i < H; i++) {
            int[] temp = new int[3];
            temp[0] = -1;
            temp[1] = -1;
            list.add(temp);
        }

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < arr[i]; j++) {
                if (list.get(j)[0] == -1) list.get(j)[0] = i;
                list.get(j)[1] = i;
                list.get(j)[2]++;
            }
        }

        for (int[] temp : list) {
            if (temp[0] != temp[1]) {
                ans += temp[1] - temp[0] + 1 - temp[2];
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        solution(H, W, arr);
    }
}