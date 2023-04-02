package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B015_BJ2636_치즈 {
    static int N, M, result;        // N, M : 치즈의 크기 , result: 마지막 치즈 개수에 대한 결과 저장
    static int[][] arr, tmp;        // 치즈, 임시로 치즈의 정보를 저장할 배열
    static boolean flag = true;     // 치즈가 모두 사라졌을 때 false
    static int[] dy = {-1, 1, 0, 0};        // 상하좌우 이동
    static int[] dx = {0, 0, -1, 1};        // 상하좌우 이동
    static class Node {     // 현재 치즈에 대한 정보를 저장하기 위한 클래스
        int y, x;       // y, x의 좌표

        public Node(int y, int x) {     // 생성자
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {     // 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       // br : 사용자 입력
        StringTokenizer st = new StringTokenizer(br.readLine());        // st : 띄어쓰기를 통한 사용자 입력 받기
        N = Integer.parseInt(st.nextToken());       // N의 크기 입력 받기
        M = Integer.parseInt(st.nextToken());       // M의 크기 입력받기

        arr = new int[N][M];        // 배열 생성
        tmp = new int[N][M];        // 임시로 저장할 배열 입력 받기
        for (int i = 0; i < N; i++) {       // 반복문을 통해 치즈의 정보 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());       // 치즈값 입력 받기
            }
        }

        int hour = 0;       // hour : 지나간 시간 저장
        while(flag) {       // 치즈가 모두 녹을 때(flag == false) 까지 반복

            copy();     // 현재 치즈의 정보 (arr) -> 임시 배열 (tmp)에 저장
            bfs();      // 치즈의 바깥쪽 0을 돌면서 치즈 녹이기
            hour++;     // 시간 증가
        }

        System.out.print(hour-1+"\n");      // 결과 출력(한번 더 반복해서 치즈가 녹음을 확인하기 때문에 -1)
        System.out.print(result);       // 결과 출력

    }

    static void bfs() {     // 치즈 녹이기
        Queue<Node> queue = new ArrayDeque<>();     // 치즈의 바깥쪽 0을 기준으로 큐에 저장

        int count = 0;      // 치즈의 개수 세기
        queue.offer(new Node(0, 0));        // 초기 (0, 0) 넣기 -> 가장자리는 항상 0
        while(!queue.isEmpty()) {       // 큐가 빌때까지 반복
            Node cur = queue.poll();        // 큐에서 값을 하난 뺌
            tmp[cur.y][cur.x] = -1;     // 방문처리
            for (int i = 0; i < 4; i++) {       // 상하좌우 이동
                int ny = cur.y + dy[i];     // 현재의 y값
                int nx = cur.x + dx[i];     // 현재의 x값

                if (ny >= N || ny < 0 || nx >= M || nx < 0) continue;       // 범위를 벗어남

                if (tmp[ny][nx] == 0) {     // 0이면 빈공간
                    tmp[ny][nx] = -1;       // 방문처리
                    queue.offer(new Node(ny, nx));      // 다음 공간으로 이동
                } else if (tmp[ny][nx] == 1) {      // 1이면 치즈
                    tmp[ny][nx] = -1;       // 방방문처리
                    arr[ny][nx] = 0;        // tmp가 아닌 기존 arr에 0으로 바꿈
                    count++;        // 카운트 증가
                }
            }
        }
        if (count == 0) {       // count가 0이면 치즈가 모두 녹음
            flag = false;       // flag를 false로 바꾸어 치즈가 모두 녹았음을 나타냄
            return;
        }

        result = count;     // 결과 저장

    }

    static void copy() {        // 배열 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = arr[i][j];      // tmp에 arr를 저장
            }
        }
    }
}
