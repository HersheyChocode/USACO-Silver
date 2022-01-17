package usaco2021AprilContest_USOpen;

import java.io.*;
import java.util.*;

public class MazeTacToe2 {

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static TreeSet<Integer> answers = new TreeSet<Integer>(); //will hold the base3 values of the possible outcomes
	static int n;
	static int[][][]board; // [n][n][3]
	static boolean[][][] visited; //[n][n][3^9]
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());

		
		board = new int[n][n][3];
		visited = new boolean[n][n][(int) Math.pow(3, 9)];

		int xi = -1;
		int yi = -1;
		
		//M = 0
		//O = 1
		//. = 2
		//# = 3
		
		for(int i = 0; i<n; i++) {
			String st = (br.readLine());
			for(int j = 0; j<n; j++) {
				char type = st.charAt(j*3);
				if(type=='O' || type=='M') {
					int k = Integer.valueOf(st.charAt(j*3+1)-49);
					int l = Integer.valueOf(st.charAt(j*3+2)-49);
					if(type=='M') board[i][j] = new int[] {0,k,l};
					if(type=='O') board[i][j] = new int[] {1,k,l};
				} else if(type=='.' || type=='B'){
					board[i][j] = new int[] {2,-1,-1};
				} else if(type=='#') {
					board[i][j] = new int[] {3,-1,-1};
				}
				if(type=='B') {
					xi = i;
					yi = j;
				}
			}
		}
		
		DFS(xi,yi,0);
		System.out.println(answers);
	}
	
	public static boolean win(int b) {
		//b -> base 10
		b = base3(b);
		
		int[][]ttt = new int[3][3];
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				if(b>1) ttt[i][j] = b%10;
				else ttt[i][j] = 0;
				b/=10; //check
			}
		}
		for(int i = 0; i<3; i++) {
			if(ttt[i][0]==0 && ttt[i][1]==1 && ttt[i][2]==1) return true;
			if(ttt[i][0]==1 && ttt[i][1]==1 && ttt[i][2]==0) return true;
			if(ttt[0][i]==0 && ttt[1][i]==1 && ttt[2][i]==1) return true;
			if(ttt[0][i]==1 && ttt[1][i]==1 && ttt[2][i]==0) return true;
		}
		if (ttt[0][0] == 0 && ttt[1][1] == 1 && ttt[2][2] == 1) return true;
		if (ttt[0][0] == 1 && ttt[1][1] == 1 && ttt[2][2] == 0) return true;
		if (ttt[2][0] == 0 && ttt[1][1] == 1 && ttt[0][2] == 1) return true;
		if (ttt[2][0] == 1 && ttt[1][1] == 1 && ttt[0][2] == 0) return true;
		return false;
	}
	
	public static void DFS(int i, int j, int b) {
		//System.out.println("hi");
		if(!visited[i][j][b]) {
			visited[i][j][b] = true;
			System.out.println(board[i][j][0]);
			if(board[i][j][0]==0 || board[i][j][0]==1) {
				//System.out.println("hi");
				int indx = 3*board[i][j][1] + board[i][j][2];
			//b = base3(b);
				int currChar = base3(b);
				int rem = 0;
				for(int h = 0; h<indx-1; h++) {
					rem+=Math.pow(10, h)*currChar%3;
					currChar/=3;
				}
				currChar %= 3;
				System.out.println("new char: " + indx + " : "  + currChar);
			    if (currChar == 2) {
			    	int newChar;
			    	if(board[i][j][0]==0) newChar = 0;
			    	else newChar = 1;
			        currChar*=10;
			        currChar+=newChar;
			        currChar*=Math.pow(10, indx);
			        currChar+=rem;
			        b = base10(currChar);
			        if(win(b) && !visited[i][j][b]) answers.add(b);
			        visited[i][j][b]=true;
			    }
			}
			
			for(int l = 0; l<4; l++) {
				int nx = i+dx[l];
				int ny = j+dy[l];
				//System.out.println(i + " "  + j);
				if(board[nx][ny][0]!=3) {
					//System.out.println("y");
					DFS(nx,ny,b);
				}
			}
		}
	}
	
	public static int base10(int b) {
		int x = 0;
		int counter = 0;
		while(b>1) {
			int rem = b%10;
			b/=10;
			x+=rem*Math.pow(3, counter);
			counter++;
		}
		return x;
	}
	public static int base3(int b) {
		int x = 0;
		int counter = 0;
		while(b>1) {
			int rem = b%3;
			b/=3;
			x+=rem*Math.pow(10, counter);
			counter++;
		}
		return x;
	}

}
