package 박지영;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B004_BJ1149_RGB거리_과제1 {
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N][3];
        st = new StringTokenizer(br.readLine());
        arr[0][0] = Integer.parseInt(st.nextToken());
        arr[0][1] = Integer.parseInt(st.nextToken());
        arr[0][2] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Math.min(arr[i-1][1], arr[i-1][2]) + Integer.parseInt(st.nextToken());
            arr[i][1] = Math.min(arr[i-1][0], arr[i-1][2]) + Integer.parseInt(st.nextToken());
            arr[i][2] = Math.min(arr[i-1][0], arr[i-1][1]) + Integer.parseInt(st.nextToken());
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(result, arr[N-1][i]);
        }
        System.out.println(result);
    }
}
