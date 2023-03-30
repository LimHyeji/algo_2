package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B009_BJ12865_평범한배낭 {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] V = new int[N+1];
        int[] W = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());

        }

        int[] result = new int[K+1];
        for (int i = 1; i <= N; i++) {      // 물건
            for (int j = K; j >= 0; j--) {  // 무게
                if (W[i] <= j) {
                    result[j] = Math.max(result[j], V[i] + result[j-W[i]]);
                }
            }
        }
        System.out.print(result[K]);
    }
}
