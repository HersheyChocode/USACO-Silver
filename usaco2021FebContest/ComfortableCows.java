package usaco2021FebContest;

import java.io.*;
import java.util.*;

public class ComfortableCows {
	static int[][] board = new int[3000][3000];
	static boolean[][] exists = new boolean[3000][3000];
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int count = 0;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken())+1000;
			int y = Integer.valueOf(st.nextToken())+1000;
			
			if(exists[x][y]) count--;
			Change(x,y, false);
			System.out.println(count);
		}
		

	}
	
	public static void Change(int x, int y, boolean changed) {
		//System.out.println(x + "  " + y + " " + exists[x][y] + " " + board[x][y]);
		if(changed)count++;
		if(!exists[x][y]) {
			exists[x][y] = true; //adds new location there
			for(int i = 0; i<4; i++) {
				if(exists[x+dx[i]][y+dy[i]]) { //if neighbor exists
					board[x][y]++; //adds one to #neighbors
					board[x+dx[i]][y+dy[i]]++; //adds one to neighbor's #neighbors
				}
			}
		} 
		if(board[x][y]==3){
			for(int i = 0; i<4; i++) {
				if(!exists[x+dx[i]][y+dy[i]]) {
					Change(x+dx[i],y+dy[i], true);
					
				}
			}
		}
		for(int i = 0; i<4; i++) {
			if(board[x+dx[i]][y+dy[i]]==3) {
				Change(x+dx[i],y+dy[i], false);
				
			}
		}
	}

}
