package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B005_BJ1010_다리놓기 {
    static int T, N, M;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            arr = new int[31][31];
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            sb.append(combination()).append('\n');
        }

        System.out.print(sb);
    }

    static int combination() {
        for (int i = 0; i <= N; i++) {
            int end = Math.min(i, M);
            for (int j = 0; j <= end; j++) {
                if (j==0 || i==j) arr[i][j] = 1;
                else arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
            }
        }
        return arr[N][M];
    }
}
