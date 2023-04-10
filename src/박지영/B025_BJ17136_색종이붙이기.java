package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B025_BJ17136_색종이붙이기 {
    static int[][] arr;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = new int[10][10];
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) count++;
            }
        }
        int[] paper = new int[6];
        Arrays.fill(paper, 5);

        for (int c = 5; c >= 1; c--) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (paper[c] == 0) continue;
                    if (check(i, j, c)) {
                        change(i, j, c);
//                        System.out.println(Arrays.toString(paper));
                        paper[c]--;
//                        print();
                    }
                }
            }
        }


//        System.out.println(Arrays.toString(paper));
//        System.out.println(count);
        int sum = 25;
        for (int i = 1; i < 6; i++) {
            sum -= paper[i];
        }

        System.out.print(count == 0 ? sum : -1);
    }

    static void print() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("=========================");
    }

    static void change(int startY, int startX, int c) {
        for (int i = startY; i < startY+c; i++) {
            for (int j = startX; j < startX+c; j++) {
                arr[i][j] = 0;
                count--;
            }
        }
    }

    static boolean check(int startY, int startX, int c) {
        if (startY + c > 10 || startX + c > 10) return false;
        for (int i = startY; i < startY+c; i++) {
            for (int j = startX; j < startX+c; j++) {
                if (i >= 10 || j >= 10) return false;
                if (arr[i][j] != 1) {       // 덮을 공간이 아님
                    return false;
                }
            }
        }
        return true;
    }
}
