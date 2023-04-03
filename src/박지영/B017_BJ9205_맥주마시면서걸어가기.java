package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B017_BJ9205_맥주마시면서걸어가기 {
    static boolean[][] adj;
    static int[][] place;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            adj = new boolean[n+2][n+2];
            place = new int[n+2][2];
            for (int i = 0; i < n+2; i++) {
                st = new StringTokenizer(br.readLine());
                place[i][0] = Integer.parseInt(st.nextToken());
                place[i][1] = Integer.parseInt(st.nextToken());
            }


            for (int j = 0; j < n+2; j++) {         // 초기 비교 (출발지 -> 도착지 한번에 갈 수 있는 곳에 대해서 true)
                for (int k = 0; k < n+2; k++) {
                    if (j==k) continue;

                    if (Math.abs(place[j][0]-place[k][0]) + Math.abs(place[j][1] - place[k][1]) <= 1000) {
                        adj[j][k] = true;
                    }
                }
            }

            // j -> i -> k ( 경유지를 이용해야 도착이 가능한 경우)
            for (int i = 0; i < n+2; i++) {
                for (int j = 0; j < n+2; j++) {
                    if (i==j) continue;
                    for (int k = 0; k < n+2; k++) {
                        if (i==k || j==k) continue;

                        if ((adj[j][i] && adj[i][k])) {     // 경유지를 사용하는것이 가능한 경우
                            adj[j][k] = true;       // 해당 경로로 갈 수 있음
                        }
                    }
                }
            }
            if(adj[n+1][0]||adj[0][n+1]) {
                sb.append("happy").append("\n");
            }else {
                sb.append("sad").append("\n");
            }

        }
        System.out.println(sb);

    }
}
