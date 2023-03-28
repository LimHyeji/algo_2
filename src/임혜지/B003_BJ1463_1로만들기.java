package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B003_BJ1463_1로만들기 {
	static int res = Integer.MAX_VALUE;// 최소 연산 수 초기화

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		int n = Integer.parseInt(in.readLine());// 연산을 수행할 숫자 입력
		sol(0, n);// 0번부터 카운트하면서 연산 수행할 숫자를 solution 수행
		System.out.println(res);// 최소 연산 수 출력
	}

	static void sol(int cnt, int num) {
		if (num == 1) {// 1이 되었을 경우
			res = Math.min(res, cnt);// 최소값 업데이트
			return;// 재귀 종료
		} else if (num < 1) {// 1보다 작을 경우 1이 되는 조건을 만족하지 못했으므로
			return;// 재귀 종료
		}

		if (num % 6 == 0 && cnt + 2 < res) {// 6으로 나누어 떨어지면서 연산 수행 시에도 최소값보다 작은 경우
			sol(cnt + 2, num / 6);// 6으로 나누는 연산 수행
		}

		if (num % 3 == 0 && cnt + 1 < res) {// 3으로 나누어 떨어지면서 연산 수행 시에도 최소값보다 작은 경우
			sol(cnt + 1, num / 3);// 3으로 나누는 연산 수행
		}

		if (num % 2 == 0 && cnt + 1 < res) {// 2으로 나누어 떨어지면서 연산 수행 시에도 최소값보다 작은 경우
			sol(cnt + 1, num / 2);// 2으로 나누는 연산 수행
		}

		if (cnt + 1 < res) {// 연산 수행 시에도 최소값보다 작은 경우
			sol(cnt + 1, num - 1);// 1을 빼는 연산 수행
		}
	}
}
