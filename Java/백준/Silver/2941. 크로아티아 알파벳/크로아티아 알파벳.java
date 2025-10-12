import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        System.out.println(solution(str));
    }

    private static int solution(String str) {
        String[] strs = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        Arrays.sort(strs);

        int s = 0, e = 2, cnt = 0;
        while (s < str.length()) {
            if (e > str.length()) e = str.length();
            String temp = str.substring(s, e);

            if (temp.equals("dz") && e < str.length() && str.charAt(e) == '=') {
                cnt++;
                s = e + 1;
                e = s + 2;
            } else {
                if (Arrays.binarySearch(strs, temp) >= 0) {
                    s += 2;
                    e += 2;
                } else {
                    s++;
                    e++;
                }
                cnt++;
            }
        }

        return cnt;
    }
}