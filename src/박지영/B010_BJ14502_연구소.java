package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B010_BJ14502_연구소 {
    static int N, M, result=Integer.MIN_VALUE;     // N, M: 연구소의 크기 , result : 결과 저장
    static int[][] arr, tmp;        // arr : 연구소의 정보 저장, tmp : 임시로 저장하기 위한 배열
    static int[] dy = {-1, 1, 0, 0};        // 상하좌우 이동
    static int[] dx = {0, 0, -1, 1};        // 상하좌우 이동
    static class Virus {        // 바이러스 정보저장을 위한 클래스
        int y, x;       // y: y의 좌표, x : x의 좌표

        public Virus(int y, int x) {        // 생성자
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {     // 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());        // 띄어쓰기를 기준으로 입력받기

        N = Integer.parseInt(st.nextToken());       // 연구소의 크기 입력 받기(y)
        M = Integer.parseInt(st.nextToken());       // 연구소의 크기 입력 받기(x)

        arr = new int[N][M];        // 배열을 크기만큼 생성
        tmp = new int[N][M];        // 배열을 크기만큼 생성
        for (int i = 0; i < N; i++) {       // 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeWall(0);        // 벽 색성하기
        System.out.println(result);     // 결과 출력
    }

    static void bfs() {         // 바이러스 퍼트리기
        Queue<Virus> queue = new LinkedList<>();        // 큐 생성
        for (int i = 0; i < N; i++) {           // 큐에 바이러스의 정보를 저장
            for (int j = 0; j < M; j++) {
                tmp[i][j]= arr[i][j];
                if(arr[i][j] == 2) {
                    queue.offer(new Virus(i, j));
                }
            }
        }

        while(!queue.isEmpty()) {       // 큐가 빌때 까지 반복
            Virus v = queue.poll();     // 바이러스의 정보 하나 꺼내기
            for (int i = 0; i < 4; i++) {       // 상하좌우 이동
                int ny = v.y + dy[i];       // 이동할 y
                int nx = v.x + dx[i];       // 이동할 x

                if (ny >= N || ny <0 || nx >= M || nx <0) continue;     // 범위를 벗어남
                if (tmp[ny][nx] == 0) {     // 갈 수 있는 곳
                    tmp[ny][nx] = 2;        // 바이러스를 퍼트림
                    queue.offer(new Virus(ny, nx));     // 큐에 저장
                }
            }
        }

        int count = 0;      // 바이러스가 퍼지지 않은 곳의 크기
        for (int i = 0; i < N; i++) {       // 바이러스가 퍼지지 않은 곳을 찾기 위한 반복문
            for (int j = 0; j < M; j++) {
                if(tmp[i][j] == 0) {        // 0이면 바이러스가 없음
                    count++;        // 카운트 증가
                }
            }
        }
        result = Math.max(count, result);       // count가 가장 최대가 될 때를 찾음
    }
    static void makeWall(int count) {       // 벽 세우기
        if (count == 3) {       // 벽이 3개가 되면
            bfs();      // 바이러스를 퍼트리기
            return;
        }
        for (int i = 0; i < N; i++) {       // 벽을 세우기 위한 반복문
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {       // 벽을 세울 수 있는 곳이면
                    arr[i][j] = 1;          // 벽을 세움
                    makeWall(count+1);      // 벽의 수를 증가시키면서 재귀
                    arr[i][j] = 0;          // 벽을 없앰
                }
            }
        }
    }
}
