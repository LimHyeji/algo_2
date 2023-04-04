package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B016_SWEA1263_사람네트워크2 {
    static int[][] adj, dist;
    static int INF = 9999999;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Integer.parseInt(st.nextToken());
                    if (i != j && dist[i][j] == 0) {
                        dist[i][j] = INF;
                    }
                }
            }

            for (int i = 0; i < N; i++) {       // 경유지
                for (int j = 0; j < N; j++) {   // 출발지
                    if(i == j) continue;
                    for (int k = 0; k < N; k++) {   // 목적지
                        if(i == k || j == k) continue;
                        if (dist[j][k] > dist[j][i] + dist[i][k]) {
                            dist[j][k] = dist[j][i] + dist[i][k];
                        }
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    dist[i][0] += dist[i][j];
                }
            }



            // 비교하기
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                min = Math.min(min, dist[i][0]);
            }

            sb.append("#").append(test_case).append(" ").append(min).append("\n");

        }
        System.out.println(sb);
    }
}
