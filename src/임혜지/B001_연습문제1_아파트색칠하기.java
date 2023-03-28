package 임혜지;

public class B001_연습문제1_아파트색칠하기 {
	static int n = 8;//f(8) 구하기

	public static void main(String[] args) {
		System.out.println(sol(n));//f(8) 출력
	}

	static int sol(int n) {
		if(n==1) return 2;//1개의 아파트에서는 2가지 경우
		if(n==2) return 3;//2개의 아파트에서는 3가지 경우
		
		return sol(n-1)+sol(n-2);//점화식
	}
}
