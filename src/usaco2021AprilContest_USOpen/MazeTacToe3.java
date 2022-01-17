package usaco2021AprilContest_USOpen;

import java.io.*;
import java.util.*;

public class MazeTacToe3 {
	
	
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
		
		//. = 0
		//M = 1
		//O = 2
		//# = 3
		String a = digits(233232);
		//System.out.println(a + " " + Integer.valueOf(a));
		
		for(int i = 0; i<n; i++) {
			String st = (br.readLine());
			for(int j = 0; j<n; j++) {
				char type = st.charAt(j*3);
				if(type=='O' || type=='M') {
					int k = Integer.valueOf(st.charAt(j*3+1)-49);
					int l = Integer.valueOf(st.charAt(j*3+2)-49);
					if(type=='M') board[i][j] = new int[] {1,k,l};
					if(type=='O') board[i][j] = new int[] {2,k,l};
				} else if(type=='.' || type=='B'){
					board[i][j] = new int[] {0,-1,-1};
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
		System.out.println(answers.size());
		
	}
	
	public static boolean win(String b) {
		int[][] ttt = new int[3][3];
		for(int i = 0; i<3; i++) {
			ttt[i][0] = Character.getNumericValue(b.charAt(i*3));
			ttt[i][1] = Character.getNumericValue(b.charAt(i*3+1));
			ttt[i][2] = Character.getNumericValue(b.charAt(i*3+2));
		}
		for(int i = 0; i<3; i++) {
			if(ttt[i][0]==1 && ttt[i][1]==2 && ttt[i][2]==2) return true;
			if(ttt[i][0]==2&& ttt[i][1]==2 && ttt[i][2]==1) return true;
			if(ttt[0][i]==1 && ttt[1][i]==2 && ttt[2][i]==2) return true;
			if(ttt[0][i]==2 && ttt[1][i]==2 && ttt[2][i]==1) return true;
		}
		if (ttt[0][0] == 1 && ttt[1][1] == 2 && ttt[2][2] == 2) return true;
		if (ttt[0][0] == 2 && ttt[1][1] == 2 && ttt[2][2] == 1) return true;
		if (ttt[2][0] == 1 && ttt[1][1] == 2 && ttt[0][2] == 2) return true;
		if (ttt[2][0] == 2 && ttt[1][1] == 2 && ttt[0][2] == 1) return true;
		return false;
	}
	
	public static String digits (int status) { //makes something have 9 digits
		String s = String.valueOf(status);
		while(s.length()<9) {
			s="0"+s;
		}
		return s;
	}
	
	public static int base10(int b) {
		int x = 0;
		int counter = 0;
		while(b>0) {
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
		while(b>0) {
			int rem = b%3;
			b/=3;
			x+=rem*Math.pow(10, counter);
			counter++;
		}
		return x;
	}

	public static void DFS(int i, int j, int b) {
		boolean won = false;
		if(!visited[i][j][b]) {
			visited[i][j][b] = true;
			if(board[i][j][0]==1 || board[i][j][0]==2) {
				int indx = 3*board[i][j][1] + board[i][j][2];
				int b3 = base3(b);
				String val = digits(b3);
				String newVal = "";
				if(val.charAt(indx)=='0') {
					for(int k = 0; k<9; k++) {
						if(k!=indx) newVal+=val.charAt(k);
						else newVal+=String.valueOf(board[i][j][0]);
					}
					b = base10(Integer.valueOf(newVal));
					if(win(newVal)&&!visited[i][j][b]) {
						won = true;
						answers.add(b);
						visited[i][j][b] = true;
						//System.out.println(val + " " + newVal);
					}
				}
			}
			for(int l = 0; l<4; l++) {
				int nx = i+dx[l];
				int ny = j+dy[l];
				//System.out.println(i + " "  + j);
				if(!won && board[nx][ny][0]!=3) {
					//System.out.println("y");
					DFS(nx,ny,b);
				}
			}
		}
	}
	
}
