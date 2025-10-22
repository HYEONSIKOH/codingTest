import java.util.*;
import java.io.*;

public class Main {
    private static final int N = 20;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double sum = 0.0;
        double gradeTotalSum = 0.0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            st.nextToken();
            double score = Double.parseDouble(st.nextToken());
            double credit = 0.0;

            switch (st.nextToken()) {
                case "A+":
                    credit = 4.5;
                    break;
                case "A0":
                    credit = 4.0;
                    break;
                case "B+":
                    credit = 3.5;
                    break;
                case "B0":
                    credit = 3.0;
                    break;
                case "C+":
                    credit = 2.5;
                    break;
                case "C0":
                    credit = 2.0;
                    break;
                case "D+":
                    credit = 1.5;
                    break;
                case "D0":
                    credit = 1.0;
                    break;
                case "F":
                    credit = 0.0;
                    break;
                case "P":
                    credit = -2.0;
                    break;
            }

            if (credit != -2.0) {
                sum += (score * credit);
                gradeTotalSum += score;
            }
        }

        System.out.println(sum / gradeTotalSum);
    }
}