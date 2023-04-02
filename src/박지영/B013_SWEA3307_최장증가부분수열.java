package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B013_SWEA3307_최장증가부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] LIS = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                LIS[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i] && LIS[i] < LIS[j]+1) {
                        LIS[i] = LIS[j]+1;
                    }
                }
                max = Math.max(max, LIS[i]);
            }

            sb.append("#").append(test_case).append(" ").append(max).append('\n');
        }
        System.out.println(sb);
    }
}
