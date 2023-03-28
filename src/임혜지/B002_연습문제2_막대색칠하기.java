package 임혜지;

public class B002_연습문제2_막대색칠하기 {
	static int b = 1, y = 1, r = 2;// 파란 막대, 노란 막대, 빨간 막대
	static int n = 6;// f(6) 값 구하기

	public static void main(String[] args) {

		System.out.println(sol(n));// f(6) 출력
	}

	static int sol(int n) {
		if (n <= 0)// 0보다 작거나 같은 값이 들어올 때는
			return 1;// 항상 1 리턴
		if (n == 1)// n이 1인 경우에는 2개의 방법 존재하므로
			return 2;// 2 리턴

		return 2 * sol(n - 1) + sol(n - 2);// 점화식
	}
}
