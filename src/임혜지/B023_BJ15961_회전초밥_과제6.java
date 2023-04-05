package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B023_BJ15961_회전초밥_과제6 {
	static int n, d, k, c;// 접시 수, 초밥 종류 수, 연속해서 먹는 접시 수, 쿠폰 번호 수
	static int[] count;// 초밥 종류별 개수를 센 배열
	static int[] arr;// 입력된 배열
	static int res;// 초밥의 가짓수의 최댓값

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());// 접시 수 입력
		d = Integer.parseInt(st.nextToken());// 초밥 종류 수 입력
		k = Integer.parseInt(st.nextToken());// 연속해서 먹는 접시 수 입력
		c = Integer.parseInt(st.nextToken());// 쿠폰 번호 수 입력

		arr = new int[n + k];// 입력될 배열 생성
		count = new int[d + 1];// 초밥 종류별 개수 배열 생성
		count[c]++;// 쿠폰에 대한 개수 1 증가
		res = 0;// 결과값
		int cnt = 1;// 개수를 셀 변수(이미 1개의 초밥이 존재함)
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(in.readLine());
			if (i >= 1 && i <= k) {// 처음부터 k개의 대해
				if (add(arr[i])) {// 무조건 개수 배열 더하고, 이미 존재했는지 여부에 따라
					cnt++;// 존재하지 않았던 종류라면 1 증가
				}
			} else {// k+1번째부터는
				if (sub(arr[i - k])) {// 앞에 있는 1개를 제거하고, 0개가 된다면
					cnt--;// 개수 1 감소
				}

				if (add(arr[i])) {// 입력된 값을 더하고, 이미 존재하는 것이 아니라면
					cnt++;// 개수 1 증가
				}
			}
			res = Math.max(res, cnt);// 세고 있는 개수와 결과 중 최대값 찾기
		}

		int idx = 1;// 환형이므로 추가로 1~k-1을 뒤에 붙여서 다시 확인하기
		for (int i = n + 1; i < n + k; i++) {
			arr[i] = arr[idx++];
			if (sub(arr[i - k])) {// 앞에 있는 1개를 제거하고, 0개가 된다면
				cnt--;// 개수 1 감소
			}

			if (add(arr[i])) {// 입력된 값을 더하고, 이미 존재하는 것이 아니라면
				cnt++;// 개수 1 증가
			}
			res = Math.max(res, cnt);// 세고 있는 개수와 결과 중 최대값 찾기
		}

		System.out.println(res);// 최대값 출력
	}

	static boolean add(int num) {
		if (count[num] > 0) {// 그 종류가 이미 존재했다면
			count[num]++;// 일단 한 개 더하고
			return false;// cnt 증가 못하게 false 리턴
		}
		count[num]++;// 그 종류가 없없다면
		return true;// cnt 증가하게 true 리턴
	}

	static boolean sub(int num) {
		if (num != c || (num == c && count[num] > 1)) {// 쿠폰이 아니거나, 쿠폰이면서 개수가 1 초과일 경우에만
			count[num]--;// 한 개 빼기
		}
		if (count[num] > 0)// 해당 종류가 여전히 존재한다면
			return false;// cnt 1 감소 못하게 false 리턴
		return true;// 해당 종류가 0개가 되었다면 cnt 1 감소하게 true 리턴
	}
}
