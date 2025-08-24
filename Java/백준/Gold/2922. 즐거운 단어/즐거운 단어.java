import java.io.*;

public class Main {
    private static char[] arr;

    private static long ans = 0;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        arr = br.readLine().toCharArray();
        solution();

        System.out.println(ans);
    }

    private static boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    private static boolean isValid(int idx, char c) {
        boolean isCurrentVowel = isVowel(c);

        // 1. (idx-2), (idx-1), (idx)가 연속된 경우
        if (idx >= 2) {
            if (arr[idx - 1] != '_' && isVowel(arr[idx - 1]) == isCurrentVowel && arr[idx - 2] != '_' && isVowel(arr[idx - 2]) == isCurrentVowel) {
                return false;
            }
        }

        // 2. (idx-1), (idx), (idx+1)가 연속된 경우
        if (idx >= 1 && idx < N - 1) {
            if (arr[idx - 1] != '_' && isVowel(arr[idx - 1]) == isCurrentVowel && arr[idx + 1] != '_' && isVowel(arr[idx + 1]) == isCurrentVowel) {
                return false;
            }
        }

        // 3. (idx), (idx+1), (idx+2)가 연속된 경우
        if (idx < N - 2) {
            if (arr[idx + 1] != '_' && isVowel(arr[idx + 1]) == isCurrentVowel && arr[idx + 2] != '_' && isVowel(arr[idx + 2]) == isCurrentVowel) {
                return false;
            }
        }

        return true;
    }

    private static void solution() {
        boolean isAlphabetL = false;
        N = arr.length;

        for (int i = 0; i < N; i++) {
            char c = arr[i];
            if (c == 'L'){
                isAlphabetL = true;
                break;
            }
        }

        backtrack(0, 1, isAlphabetL);
    }

    private static void backtrack(int idx, long cnt, boolean isAlphabetL) {
        while (idx <= N - 1 && arr[idx] != '_') idx++;

        if (idx >= N) {
            if (isAlphabetL) ans += cnt;
            return;
        }

        // 1. 모음 넣기
        if (isValid(idx, 'A')) {
            arr[idx] = 'A';
            backtrack(idx + 1, cnt * 5, isAlphabetL);
            arr[idx] = '_';
        }

        // 2. 자음 넣기
        if (isValid(idx, 'B')) {
            arr[idx] = 'B';
            backtrack(idx + 1, cnt * 20, isAlphabetL);
            arr[idx] = '_';
        }

        // 3. L 넣기
        if (isValid(idx, 'L')) {
            arr[idx] = 'L';
            backtrack(idx + 1, cnt, true);
            arr[idx] = '_';
        }
    }
}