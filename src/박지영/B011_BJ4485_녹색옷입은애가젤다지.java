package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B011_BJ4485_녹색옷입은애가젤다지 {
    static int N;   // 동굴의 크기
    static int INF = Integer.MAX_VALUE;     // 최대로 배열을 채우기 위해 정수 최대 저장
    static int[][] cave, arr;       // cave : 동굴의 정보, arr : 최소 저장
    static int[] dy = {-1, 1, 0, 0};        // 상하좌우 이동
    static int[] dx = {0, 0, -1, 1};        // 상하좌우 이동

    static class Node implements Comparable<Node> {     // PriorityQueue를 사용하기 위한 클래스 생성
        int y, x, cost;     // y: y의 좌표, x : x의 좌표, cost: 도둑루피의 비용

        public Node(int y, int x, int cost) {       // 생성자
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {      // 도둑 루피의 비용으로 오름차순
            return cost-o.cost;
        }
    }

    public static void main(String[] args) throws IOException {     // 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       // 사용자 입력을 위한 BufferedReader
        StringTokenizer st;         // 띄어쓰기를 기준으로 입력받기
        StringBuilder sb = new StringBuilder();     // 출력 저장을 위한 StringTokenizer
        N = Integer.parseInt(br.readLine());        // N: 크기 입력 받기
        int T = 1;      // 테스트 케이스
        while(N!=0) {       // N이 0이될때 까지 반복
            cave = new int[N][N];       // 동굴 생성
            arr = new int[N][N];        // 최소값 저장을 위한 배열 생성
            for (int i = 0; i < N; i++) {       // 최소값 저장을 위한 배열에 큰 수 저장
                Arrays.fill(arr[i], INF);
            }
            for (int i = 0; i < N; i++) {       // 사용자 입력 받기
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());      // 동굴에 값 저장
                }
            }
            arr[0][0] = cave[0][0];     // 시작지점에 값 넣기
            search();       // 이동 시작
            sb.append("Problem ").append(T++).append(": ").append(arr[N-1][N-1]).append('\n');  // 결과 출력
            N = Integer.parseInt(br.readLine());        // 다음 N값 입력 받기
        }
        System.out.print(sb);       // 출력
    }

    static void search() {      // 이동을 위한 메서드
        PriorityQueue<Node> pq = new PriorityQueue<>();     // PriorityQueue 생성
        pq.offer(new Node(0, 0, cave[0][0]));   // 시작 지점을 pq에 넣기
        while(!pq.isEmpty()) {      // pq 가 빌때까지 반복
            Node cur = pq.poll();       // cur : pq에서 값 하나 빼기
            if (arr[cur.y][cur.x] >= cur.cost) {        // 뺀 값이 현재 최소가 저장되어있는 배열의 값 보다 비용이 작다면
                arr[cur.y][cur.x] = cur.cost;       // 해당 값으로 update
            } else continue;        // 아니라면 더이상 가지 않아도 됨

            for (int i = 0; i < 4; i++) {       // 상하좌우 이동을 위한 반복문
                int ny = cur.y + dy[i];     // 이동할 y지점
                int nx = cur.x + dx[i];     // 이동할 x지점

                if (ny >= N || ny < 0 || nx >= N || nx < 0) continue;       // 범위를 벗어났다면 이동하지 않음
                if (cave[ny][nx] + arr[cur.y][cur.x] < arr[ny][nx]) {       // 최소로 저장된 배열에서의 비용보다 이동할 지점의 이동 비용이 더 작다면
                    pq.offer(new Node(ny, nx, cave[ny][nx] + arr[cur.y][cur.x] ));      // 해당 지점을 pq에 넣음
                }
            }
        }
    }
}
