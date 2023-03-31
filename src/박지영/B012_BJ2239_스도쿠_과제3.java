package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B012_BJ2239_스도쿠_과제3 {
    static int[][] arr;     // 스도쿠를 저장하는 배열
    static StringBuilder sb;        // 출력을 위한 StringBuilder
    public static void main(String[] args) throws IOException {     // 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       // 입력 받기
        sb = new StringBuilder();       // 출력을 위한 생성
        arr = new int[9][9];            // 스도쿠 배열 생성
        for (int i = 0; i < 9; i++) {   // 스도쿠 입력 받기
            String s= br.readLine();        // 입력 받기
            for (int j = 0; j < 9; j++) {   // 스도쿠 입력 받기
                arr[i][j] = s.charAt(j) - '0';  // 정수로 바꿔서 배열에 저장
            }
        }
        sudoku(0, 0);       // 스도쿠 시작

    }

    static void sudoku(int y, int x) {      // 스도쿠를 수행하는 함수
        if (y == 9 && x == 0) {     // 스도쿠를 다 채웠으면 종료
            for (int i = 0; i < 9; i++) {       // 결과 출력을 위한 반복문
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]);       // 결과 저장
                }
                sb.append('\n');    // 띄어쓰기
            }
            System.out.print(sb);       // 결과 출력
            System.exit(0);      // 1-> 9 순서로 채웠기 때문에 항상 사전순 -> 프로그램 종료
        }
        if (arr[y][x] != 0) {               // 현재 값이 0이 아님 -> 이미 채워져 있는 수
            if (x<8) sudoku(y, x+1);    // x가 8미만이면 오른쪽으로 이동
            else sudoku(y+1, 0);     // x가 8이면 다음행 왼쪽으로 이동
            return;     // 리턴
        }

        for (int i = 1; i < 10; i++) {      // 1부터 9까지 채워보기
            if (check(y, x, i)) {       // 채울 수 있는 곳인지 확인

                arr[y][x] = i;      // 채우기
                if (x<8) sudoku(y, x+1);    // x가 8미만이면 오른쪽으로 이동
                else sudoku(y+1, 0);    // x가 8이면 다음행 왼쪽으로 이동
                arr[y][x] = 0;          // 조건에 맞지 않으므로 0으로 바꾸고 다른 숫자로 채우기

            }
        }

    }
    static boolean check(int y, int x, int num) {       // 채울 수 있는 숫자인지를 확인하는 메서드 (num: 채울 숫자)

        // 가로 세로 확인
        for (int i = 0; i < 9; i++) {       // 배열을 돌면서 확인
            if(x==i && y==i) continue;      // 자기 자신이면 확인하지 않음
            if (arr[y][i] == num || arr[i][x] == num) return false;     // 가로 또는 세로에 이미 해당 숫자가 있으면 false 리턴
        }

        // 3x3 확인
        for (int i = (y/3)*3; i < (y/3)*3+3; i++) {     // 3*3 배열 크기대로 반복문
            for (int j = (x/3)*3; j < (x/3)*3+3; j++) {     // 3*3 배열 크기대로 반복문
                if (y==i && x==j) continue;             // 자기 자신이면 확인하지 않음
                if (arr[i][j] == num) return false;     // 3*3에 이미 숫자가 있으면 false 리턴
            }
        }
        return true;        // 채울 수 있으므로 true 리턴
    }


}
