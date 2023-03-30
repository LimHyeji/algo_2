package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B010_BJ14502_연구소 {
    static int N, M, result=Integer.MIN_VALUE;
    static int[][] arr, tmp;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static class Virus {
        int y, x;

        public Virus(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeWall(0);
        System.out.println(result);
    }

    static void bfs() {
        Queue<Virus> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j]= arr[i][j];
                if(arr[i][j] == 2) {
                    queue.offer(new Virus(i, j));
                }
            }
        }

        while(!queue.isEmpty()) {
            Virus v = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = v.y + dy[i];
                int nx = v.x + dx[i];

                if (ny >= N || ny <0 || nx >= M || nx <0) continue;
                if (tmp[ny][nx] == 0) {     // 갈 수 있는 곳
                    tmp[ny][nx] = 2;
                    queue.offer(new Virus(ny, nx));
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tmp[i][j] == 0) {
                    count++;
                }
            }
        }
        result = Math.max(count, result);
    }
    static void makeWall(int count) {
        if (count == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    makeWall(count+1);
                    arr[i][j] = 0;
                }
            }
        }
    }
}
