package 박지영;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B024_SWEA2383_점심식사시간_과제7 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, minute, answer= Integer.MAX_VALUE;
    static int[][] arr, visited;
    static int[] isSelected;
    static PriorityQueue<Count> q;
    static ArrayList<Node> stair, person;

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Count implements Comparable<Count>{
        int min, count, s;

        public Count(int min, int count, int s) {
            this.min = min;
            this.count = count;
            this.s = s;
        }

        @Override
        public int compareTo(Count o) {
            if (this.s == o.s) {
                return (this.count-o.count);
            }
            return this.s-o.s;
        }

        @Override
        public String toString() {
            return "Count{" +
                    "min=" + min +
                    ", count=" + count +
                    ", s=" + s +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            person = new ArrayList<>();
            stair = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] != 0 && arr[i][j] != 1) {
                        stair.add(new Node(i, j));
                    } else if (arr[i][j] == 1) {
                        person.add(new Node(i, j));
                    }
                }
            }
            isSelected = new int[person.size()];


            subSet(0);
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);

    }

    static void subSet(int cnt) {
        if (cnt==person.size()) {

            System.out.println(Arrays.toString(isSelected));
            q = new PriorityQueue<>();
            for (int i = 0; i < person.size(); i++) {
                minute = Integer.MAX_VALUE;
                visited = new int[N][N];
                dfs(person.get(i).y, person.get(i).x, isSelected[i]);
//                System.out.println(person.get(i).y+":"+ person.get(i).x+":"+minute);
//                mins[i] = Integer.min(mins[i], minute);
                q.offer(new Count(0, minute, isSelected[i]));
            }
            System.out.println(q);
//            System.out.println(Arrays.toString(mins));
            answer = Math.min(answer, count());
            return;
        }
        isSelected[cnt] = 1;
        subSet(cnt+1);
        isSelected[cnt] = 0;
        subSet(cnt+1);
    }

    static int count() {
        Queue<Count> pqA = new ArrayDeque<>();
        Queue<Count> pqB = new ArrayDeque<>();
        int result = 0;
        int minA = 0;
        for (int i = 0; i < person.size(); i++) {

            Count cur = q.poll();
            if (cur.s == 0) {
                pqA.offer(new Count(0, cur.count, 0));
            } else if (cur.s == 1) {
                pqB.offer(new Count(0, cur.count, 1));
            }

            while (pqA.size() == 3) {
                System.out.println(pqA);
                Count curA = pqA.poll();

                if (curA.min == arr[stair.get(0).y][stair.get(0).x]) {
                    result = Math.max(result, curA.count+curA.min);

                    continue;
                }
                pqA.offer(new Count(curA.min+1, curA.count, curA.s));
            }
            while (pqB.size() == 3) {
                Count curB = pqB.poll();

                if (curB.min == arr[stair.get(1).y][stair.get(1).x]) {
                    result = Math.max(result, curB.count+curB.min);
                    continue;
                }
                pqB.offer(new Count(curB.min+1, curB.count, curB.s));
            }
        }
        while (!pqA.isEmpty()) {
            Count curA = pqA.poll();
            if (curA.min == arr[stair.get(0).y][stair.get(0).x]) {
                result = Math.max(result, curA.count+curA.min);
                continue;
            }
            pqA.offer(new Count(curA.min+1, curA.count, curA.s));
        }
        while (!pqB.isEmpty()) {
            Count curB = pqB.poll();
            if (curB.min == arr[stair.get(1).y][stair.get(1).x]) {
                result = Math.max(result, curB.count+curB.min);
                continue;
            }
            pqB.offer(new Count(curB.min+1, curB.count, curB.s));
        }
        System.out.println(result);
        return result;
    }

    static void dfs(int y, int x, int s) {
        if (y == stair.get(s).y && x == stair.get(s).x) {
            minute = Math.min(minute, visited[y][x]+1);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny >=N || ny < 0 || nx >= N || nx < 0) continue;
            if (visited[ny][nx] == 0) {
                visited[ny][nx] = visited[y][x] + 1;
                dfs(ny, nx, s);
                visited[ny][nx] = 0;
            }
        }
    }
}