package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B024_SWEA2383_점심식사시간_과제7 {
	static int t;// 테스트케이스 수
	static int n;// 맵 크기

	static class Stair {// 계단 위치와 길이를 저장할 클래스
		int row, col, len;

		Stair(int row, int col, int len) {
			this.row = row;
			this.col = col;
			this.len = len;
		}
	}

	static class Person {// 사람 위치를 저장할 클래스
		int row, col;

		Person(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static Stair stair[];// 계단 두개에 대한 배열
	static ArrayList<Person> pList;// 사람들의 위치를 저장할 리스트
	static int order[];// 각 사람당 선택할 계단을 구할 배열
	static int res;// 최소 이동횟수
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력받을 준비

		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력
		for (int test_case = 1; test_case <= t; test_case++) {// 총 t번의 테스트 케이스
			n = Integer.parseInt(in.readLine());// 맵 크기 입력
			stair = new Stair[2];// 두개의 계단 위치와 길이 각각 저장할 배열
			pList = new ArrayList<>();// 사람 위치 저장할 리스트 생성

			int idx = 0;// 계단 배열 저장 시 사용할 인덱스 변수
			for (int i = 0; i < n; i++) {// 총 n개의
				st = new StringTokenizer(in.readLine());// 라인 입력
				for (int j = 0; j < n; j++) {
					int input = Integer.parseInt(st.nextToken());// 입력값에 대해
					if (input == 1) {// 1이라면
						pList.add(new Person(i, j));// 사람 리스트에 추가
					} else if (input > 1) {// 2이상이라면
						stair[idx++] = new Stair(i, j, input);// 계단 배열에 저장
					}
				}
			}

			order = new int[pList.size()];// 사람당 선택 계단 구하기 위한 배열 생성
			res = Integer.MAX_VALUE;// 최소값 구하기 위한 초기화
			getTarget(0);// 사람당 이동할 계단 구할 메소드 호출
			str.append("#").append(test_case).append(" ").append(res).append("\n");// 테스트 케이스별 결과 붙여서
		}
		System.out.println(str.toString());// 한꺼번에 출력
	}

	static void getTarget(int cnt) {// 계단을 구할 중복순열 메소드
		if (cnt == pList.size()) {// 사람 모두에 대해 계단을 구했다면
			sol(order);// 솔루션 메소드 호출
			return;// 종료
		}
		for (int i = 0; i < 2; i++) {// 계단은 0번과 1번만 존재하고
			order[cnt] = i;// cnt번째 사람의 계단을 선택한 후
			getTarget(cnt + 1);// 재귀로 중복순열 구하기
		}
	}

	static void sol(int[] order) {// 사람당 계단을 선택하는 경우의 수에 대해
		PriorityQueue<Integer> pq0 = new PriorityQueue<>();// 오름차순을 위해 우선순위 큐 생성(인덱스0의 계단)
		PriorityQueue<Integer> pq1 = new PriorityQueue<>();// 오름차순을 위해 우선순위 큐 생성(인덱스1의 계단)
		Queue<Integer> q0 = new LinkedList<>();// 이동 및 탐색을 위한 큐 생성(계속 정렬을 할 필요는 없으므로 추가로 생성)
		Queue<Integer> q1 = new LinkedList<>();// 이동 및 탐색을 위한 큐 생성

		for (int i = 0; i < order.length; i++) {// 구한 중복순열에 대해
			if (order[i] == 0) {// 0이라면
				pq0.add(getD(pList.get(i), stair[0]));// 인덱스0의 계단 pq에, 그 사람과의 거리를 저장
			} else {// 1이라면
				pq1.add(getD(pList.get(i), stair[1]));// 인덱스1의 계단 pq에, 그 사람과의 거리를 저장
			}
		}
		q0.addAll(pq0);// 모든 pq값을 큐에 저장
		q1.addAll(pq1);// 모든 pq값을 큐에 저장

		int cap0 = 0;// 세 명의 제한을 체크할 변수
		int end0 = (-1) * (stair[0].len + 1);// 거리를 계속 -1하고, 이 값에 도달 시 계단 완주
		int turn0 = 0;// 0번 계단에 대한 총 이동시간
		while (!q0.isEmpty()) {
			turn0++;// 1분 추가
			int size = q0.size();// 현재 큐의 사이즈 백업
			for (int i = 0; i < size; i++) {// 큐의 모든 값에 대해
				int cur = q0.poll();
				cur--;// 남은 거리에서 1을 빼고
				if (cur >= 0 || (cur < -1 && cur > end0)) {// 계단까지 이동중이거나 이미 계단 위일 때
					q0.add(cur);// 그대로 큐에 추가
				} else if (cur == -1) {// 이제 계단을 내려가도 될 때
					if (cap0 >= 3) {// 3명 초과 시엔
						cur++;// 다시 원상복구시키고
						q0.add(cur);// 큐에 추가(0이 추가됨)
					} else {// 제한을 초과하지 않을 때
						cap0++;// 계단 위 사람 한명 추가 후
						q0.add(cur);// 큐에 추가
					}
				} else if (cur == end0) {// 종점에 도달 시
					cap0--;// 계단 위 사람 한명 제거
				}
			}
		}

		int cap1 = 0;// 세 명의 제한을 체크할 변수
		int end1 = (-1) * (stair[1].len + 1);// 거리를 계속 -1하고, 이 값에 도달 시 계단 완주
		int turn1 = 0;// 1번 계단에 대한 총 이동시간
		while (!q1.isEmpty()) {
			turn1++;// 1분 추가
			int size = q1.size();// 현재 큐의 사이즈 백업
			for (int i = 0; i < size; i++) {// 큐의 모든 값에 대해
				int cur = q1.poll();
				cur--;// 남은 거리에서 1을 빼고
				if (cur >= 0 || (cur < -1 && cur > end1)) {// 계단까지 이동중이거나 이미 계단 위일 때
					q1.add(cur);// 그대로 큐에 추가
				} else if (cur == -1) {// 이제 계단을 내려가도 될 때
					if (cap1 >= 3) {// 3명 초과 시엔
						cur++;// 다시 원상복구시키고
						q1.add(cur);// 큐에 추가(0이 추가됨)
					} else {// 제한을 초과하지 않을 때
						cap1++;// 계단 위 사람 한명 추가 후
						q1.add(cur);// 큐에 추가
					}
				} else if (cur == end1) {// 종점에 도달 시
					cap1--;// 계단 위 사람 한명 제거
				}
			}
		}
		int temp = Math.max(turn0, turn1);// 계단0과 계단1 중에서 더 오래 걸린 시간이 현재 경우의 수의 이동횟수
		res = Math.min(res, temp);// 최소 이동횟수 업데이트
	}

	static int getD(Person p, Stair s) {// 거리 구하는 메소드
		return Math.abs(p.row - s.row) + Math.abs(p.col - s.col);
	}
}
