package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B021_SWEA4014_활주로_건설 {
    static int N, X;
    static int[][] map, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }



        }

    }

   static boolean buildRow(int y) {
        int cur = map[y][0];
        int index = 0;
        while(index < N-1) {
            if (cur == map[y][index+1]) continue;
            else if (cur == map[y][index+1]+1) {        // 오르막
                for (int i = index+1; i < index+1+X; i++) {
                    if (cur+1 == map[y][i]){

                    }
//                    count++;
                }
            } else if(cur == map[y][index+1]-1){        // 내리막

            } else {        // 건설 못함
                return false;
            }
        }
        return true;
   }
}
