package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 슬라이딩 윈도우 활용
public class B023_BJ15961_회전초밥_과제6 {
    static int[] numbers, arr;      // numbers : 벨트에 놓인 초밥 종류에 따른 개수 count, arr: 초밥 벨트에 놓인 정보
    static int N, d, k, c;  // 회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
    static int result = 0;      // 최종 결과
    public static void main(String[] args) throws IOException {     // 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());        // 띄어쓰기 기준으로 입력 받기
        N = Integer.parseInt(st.nextToken());       // 회전 초밥 벨트에 놓인 접시의 수 N
        d = Integer.parseInt(st.nextToken());       // 초밥의 가짓수 d
        k = Integer.parseInt(st.nextToken());       // 연속해서 먹는 접시의 수 k
        c = Integer.parseInt(st.nextToken());       // 쿠폰 번호 c

        numbers = new int[d+1];     // 초밥의 종류만큼 배열 생성
        arr = new int[N+k-1];       // 컨테이너에 올라가는 초밥의 수에서 회전하기 때문에 뒤에 k만큼 앞부분 연결    1-> 2-> 3 이 돌고 있다면 1 -> 2-> 3->1-> 2로 연결하게 됨
        for (int i = 0; i < N; i++) {       // 입력 받기
            arr[i] = Integer.parseInt(br.readLine());       // 초밥 정보 입력 받기
        }

        for (int i = N; i < N+k-1; i++) {       // 연속되는 수만큼 컨테이너 연결
            arr[i] = arr[i-N];      // 앞부분 연결
        }

        // 초기
        int count = 0;      // 초기 카운트 0
        for (int i = 0; i < k; i++) {   // 연속되는 수만큼 진행
            if (numbers[arr[i]] == 0) {     // 아직 고르지 않았던 초밥이라면
                count++;        // 카운트 증가
            }
            numbers[arr[i]]++;      // 고른 초밥의 수 증가
        }

        // 초기 - 쿠폰 확인
        if (numbers[c]==0) {        // 아직 고르지 않은 종류라면
            result = Math.max(result, count+1);     // 카운트 비교
        } else {                    // 골랐던 종류라면
            result = Math.max(result, count);       // 카운트 비교
        }

        for (int i = 1; i < N-1; i++) {     // 슬라이딩 윈도우
            if (numbers[arr[i-1]] == 1) count--;        // 이동하기 전의 index => index-1
            numbers[arr[i-1]]--;        // 종류의 수 감소

            if (numbers[arr[i+k-1]] == 0) {     // 이동할 곳의 index가 0이면 아직 고르지 않은 초밥
                count++;        // 초밥의 수 증가
            }
            numbers[arr[i+k-1]]++;      // 초밥 종류의 수 증가

            // 쿠폰 확인
            if (numbers[c]==0) {        // 쿠폰 사용 가능
                result = Math.max(result, count+1);
            } else {                    // 쿠폰 사용 불가능
                result = Math.max(result, count);
            }

        }


        System.out.print(result);       // 결과 출력

    }

}
