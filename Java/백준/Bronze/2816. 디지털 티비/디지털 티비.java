import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int kbs1_idx = -1, kbs2_idx = -1;
        String[] channels = new String[n];

        for (int i = 0; i < n; i++) {
            channels[i] = sc.next();
            if (channels[i].equals("KBS1")) kbs1_idx = i;
            if (channels[i].equals("KBS2")) kbs2_idx = i;
        }

        if (kbs1_idx > kbs2_idx) kbs2_idx++;

        for (int i = 0; i < kbs1_idx; i++) System.out.print('1');
        for (int i = 0; i < kbs1_idx; i++) System.out.print('4');

        if (kbs2_idx != 1) {
            for (int i = 0; i < kbs2_idx; i++) System.out.print('1');
            for (int i = 1; i < kbs2_idx; i++) System.out.print('4');
        }
    }
}