package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B025_BJ17136_색종이붙이기 {
	static int paper[][];
	static class Node implements Comparable<Node>{
		int row,col,size;
		Node(int row,int col,int size){
			this.row=row;
			this.col=col;
			this.size=size;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.size, this.size);
		}
	}
	
	static PriorityQueue<Node> pq;
	static int cnt[];
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		paper=new int[10][10];
		
		for(int i=0;i<10;i++) {
			st=new StringTokenizer(in.readLine());
			for(int j=0;j<10;j++) {
				paper[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		pq=new PriorityQueue<>();
		cnt=new int[6];
		res=0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(paper[i][j]==1) {
					find(i,j);
				}
			}
		}

		bfs();

		if(!finish()) {
			res=-1;
		}
		System.out.println(res);		
	}
	
	static void find(int row,int col) {
		int size=1;
		while(size<5) {
			if(row+size<10&&col+size<10&&paper[row+size][col]==1&&paper[row][col+size]==1) {
				size++;
			}
			else {
				break;
			}
		}
		for(int i=size;i>0;i--) {
			if(check(row,col,i)) {
				pq.add(new Node(row,col,i));
			}
		}
	}
	
	static boolean check(int row,int col, int size) {
		for(int i=row;i<row+size;i++) {
			for(int j=col;j<col+size;j++) {
				if(paper[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean finish() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(paper[i][j]==1) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void update(int row,int col, int size) {
		for(int i=row;i<row+size;i++) {
			for(int j=col;j<col+size;j++) {
			//System.out.println(i+" , "+j+"는 update..."+size);
				paper[i][j]=0;
			}
		}
	}
	
	static void bfs() {
		while(!pq.isEmpty()) {
//			Node cur=pq.poll();
//			if(cnt[cur.size]<5&&check(cur.row,cur.col,cur.size)) {
//				System.out.println("호출하는 인덱스 "+cur.row+" , "+cur.col);
//			update(cur.row,cur.col,cur.size);
//			cnt[cur.size]++;
//			res++;
//			}
			
			
			
		}
	}
}
