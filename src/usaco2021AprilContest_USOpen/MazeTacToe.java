package usaco2021AprilContest_USOpen;

import java.io.*;
import java.util.*;

public class MazeTacToe {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Move[][]grid;
	static int n;
	static boolean[][] visited;
	static int numMoves;
	static int total = 0;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		
		grid = new Move[n][n];
		
		int xi = -1;
		int yi = -1;
		
		for(int i = 0; i<n; i++) {
			String st = (br.readLine());
			for(int j = 0; j<n; j++) {
				char type = st.charAt(j*3);
				if(type=='O' || type=='M') {
					int k = Integer.valueOf(st.charAt(j*3+1)-49);
					int l = Integer.valueOf(st.charAt(j*3+2)-49);
					grid[i][j]= new Move(k,l,type);
				} else {
					grid[i][j] = new Move(-1,-1,type);
				}
				if(type=='B') {
					xi = i;
					yi = j;
				}
			}
		}
		
		visited = new boolean[n][n];
		check(0,0);
		System.out.println(numMoves);
		char[][] ttt = new char[3][3];
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				ttt[i][j] = '.';
			}
		}
		DFS(xi,yi,-1,-1,ttt,0,0);
		System.out.println(total);

	}
	
	public static boolean win(char[][] board) {
		if(board[0][0]=='M' && board[0][1]=='O' && board[0][2]=='O') return true;
		if(board[0][0]=='M' && board[1][1]=='O' && board[2][2]=='O') return true;
		if(board[0][0]=='M' && board[1][0]=='O' && board[2][0]=='O') return true;
	
		if(board[2][0]=='M' && board[1][0]=='O' && board[0][0]=='O') return true;
		if(board[2][0]=='M' && board[1][1]=='O' && board[0][2]=='O') return true;
		if(board[2][0]=='M' && board[2][1]=='O' && board[2][2]=='O') return true;
		
		if(board[2][2]=='M' && board[1][1]=='O' && board[0][0]=='O') return true;
		if(board[2][2]=='M' && board[2][1]=='O' && board[2][0]=='O') return true;
		if(board[2][2]=='M' && board[1][2]=='O' && board[0][2]=='O') return true;
		
		if(board[0][2]=='M' && board[0][1]=='O' && board[0][0]=='O') return true;
		if(board[0][2]=='M' && board[1][1]=='O' && board[2][0]=='O') return true;
		if(board[0][2]=='M' && board[1][2]=='O' && board[2][2]=='O') return true;
		return false;
	}

	public static void DFS(int x, int y, int px, int py, char[][] ttt, int numMovesVisited, int numMovesTotal) {
		if(grid[x][y].type!='#') {
			System.out.println(x+ " " + y + " " + grid[x][y] + " " + px + " "+ py);
			numMovesTotal++;
			if((grid[x][y].type=='M'||grid[x][y].type=='O')&& ttt[grid[x][y].i][grid[x][y].j]=='.') {
				ttt[grid[x][y].i][grid[x][y].j]=grid[x][y].type;
				numMovesVisited++;
				//System.out.println("et; drga;ridgjardi");
			}
			for(int j = 0; j<3; j++) {
				System.out.println(Arrays.toString(ttt[j]));
			} System.out.println();
			for(int i = 0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx>=0 && nx <n && ny>=0 && ny<n && !win(ttt) && numMovesVisited<6 && numMovesVisited<numMoves && numMovesTotal<n*n) {
					if(nx==px && ny==py) {
						if(grid[x][y].type!='.'&&(grid[px][py].type=='M'||grid[px][py].type=='O')) DFS(nx,ny,x,y,ttt,numMovesVisited,numMovesTotal);
					} else {
						DFS(nx,ny,x,y,ttt,numMovesVisited,numMovesTotal);
					}
				} else if(win(ttt)){
					total++;
					for(int j = 0; j<3; j++) {
						System.out.println(Arrays.toString(ttt[j]));
					} System.out.println("hi");
				}
			}
		}
	}
	
	public static void check(int x, int y) {
		if(!visited[x][y]) {
			visited[x][y] = true;
			if(grid[x][y].type=='M'||grid[x][y].type=='O') {
				numMoves++;
			}
			for(int i = 0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx>=0 && nx <n && ny>=0 && ny<n) check(nx,ny);
			}
		}
	}
}

class Move{
	public int i;
	public int j;
	public char type;
	public Move(int i, int j, char type) {
		this.i = i;
		this.j = j;
		this.type = type;
	}
	public String toString() {
		return type + " " + i + " " + j + " ";
	}
}