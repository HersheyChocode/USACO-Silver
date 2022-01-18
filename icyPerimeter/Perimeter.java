package icyPerimeter;

import java.io.*;
import java.util.Arrays;

public class Perimeter {
	static int area;
	static int per;
	static int n;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));

		n = Integer.valueOf(br.readLine());
		char[][] board = new char[n][n];
		for(int i = 0; i<n; i++) {
			String line = br.readLine();
			for(int j = 0; j<n; j++) {
				board[i][j]=line.charAt(j);
			}
		}
		for(int i = 0; i<n; i++) System.out.println(Arrays.toString(board[i]));
		
		boolean[][] visited = new boolean[n][n];
		
		int maxArr = Integer.MIN_VALUE;
		int finPer = Integer.MIN_VALUE;
		for(int i = 0; i<n; i++) {
			for(int j = 0;  j<n; j++) {
				area = 0;
				per = 0;
				if(board[i][j]=='#'&&!visited[i][j]) {
					floodFill(i,j,visited,board);
					if(area>maxArr) {  maxArr=area; finPer=per; }
					else if(area==maxArr) {finPer = Math.min(finPer, per);}
				}
			}
		}
		//System.out.println(maxArr + " " + finPer);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		pw.write(maxArr + " " + finPer);
		pw.close();
		br.close();
	}
	
	
	public static void floodFill(int x, int y, boolean[][] visited, char[][]board) {
		if(!visited[x][y]) {
			visited[x][y] = true;
			//System.out.println(x+" " +y + " " + area);
			area++;
			if(x+1>=n||board[x+1][y]=='.') per++;
			if(y+1>=n||board[x][y+1]=='.') per++;
			if(x-1<0||board[x-1][y]=='.') per++;
			if(y-1<0||board[x][y-1]=='.') per++;
			if(y+1<n && !visited[x][y+1]&&board[x][y+1]=='#') floodFill(x,y+1,visited,board);
			if(y-1>=0 && !visited[x][y-1]&&board[x][y-1]=='#') floodFill(x,y-1,visited,board);
			if(x-1>=0 && !visited[x-1][y]&&board[x-1][y]=='#') floodFill(x-1,y,visited,board);
			if(x+1<n && !visited[x+1][y]&&board[x+1][y]=='#') floodFill(x+1,y,visited,board);
		}
	}

}
