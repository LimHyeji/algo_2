package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B008_BJ10971_외판원순회2_과제2 {
    static int N;       // N: 섬의 개수
    static int[][] W;   // W : 이동하는 비용 저장
    static long result; // 최소의 결과 저장
    public static void main(String[] args) throws IOException { // 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // 입력받기 위한 BufferedReader 생성
        StringTokenizer st;     // 띄어쓰기 기준으로 입력받기 위한 StringTokenizer
        N = Integer.parseInt(br.readLine());    // N입력받기
        W = new int[N][N];      // N의 크기만큼 W배열 생성

        for (int i = 0; i < N; i++) {       // 반복문으로 입력받기
            st = new StringTokenizer(br.readLine());    // 띄어쓰기 기준으로 입력받기
            for (int j = 0; j < N; j++) {       // 반복문으로 입력받기
                W[i][j] = Integer.parseInt(st.nextToken()); // 배열에 값 저장
            }
        }

        result = Long.MAX_VALUE;    // 결과를 최대값으로 설정


        for (int i = 0; i < N; i++) {       // 반복문을 통해 처음 방문할 지점 설정
            visit(i, i, 1<<i, 0);   // 방문하기
        }

        System.out.println(result);     // 결과 프린트

    }

    static void visit(int start, int index, int visited, long cost) {       // start: 처음 지점, index: 이동할 지점, visited: 방문확인, cost: 비용저장
        if (visited == (1<<N)-1) {
            // 모든 곳을 방문함

            if (W[index][start] != 0) {     // 되돌아갈 곳이 있으면
                result = Math.min(result, cost+W[index][start]);        // 비용저장한 값과 현재 result와 비교해서 최소 저장
            }
            return;
        }

        for (int i = 0; i < N; i++) {       // 방문할 지점을 확인하기 위한 반복문
            if (((1<<i) & visited) == 0 && W[index][i] != 0 && cost + W[index][i] < result) {    //  방문한 적이 없음 && 갈 수 있는 곳 && 현재 구한 최소보다 작음
                visit(start, i, visited | (1<<i), cost + W[index][i]);      // 해당 지점 방문
            }
        }
    }
}
