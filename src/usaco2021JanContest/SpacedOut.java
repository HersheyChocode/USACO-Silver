package usaco2021JanContest;

import java.io.*;
import java.util.*;

public class SpacedOut {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int[][] values = new int[n][n];
		boolean[][] board = new boolean[n][n];
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				values[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		for(int i = 0; i<n-1; i++) {
			for(int j = 0; j<n-1; j++) {
				int[] dx = {0,0,1,1};
				int[] dy = {0,1,0,1};
				int min1 = Integer.MAX_VALUE;
				int min1x = -1;
				int min1y = -1;
				int min2x = -1;
				int min2y = -1;
				int min2 = Integer.MAX_VALUE;
				
				int numTaken = 0;
				for(int k = 0; k<4; k++) {
					if(!board[i+dx[k]][j+dy[k]]) {
						if(values[i+dx[k]][j+dy[k]]<min2) {
							if(values[i+dx[k]][j+dy[k]]<min1) {
								min1 = values[i+dx[k]][j+dy[k]];
								min1x = i +dx[k];
								min1y = j + dy[k];
							} else {
								min2 = values[i+dx[k]][j+dy[k]];
								min2x = i+dx[k];
								min2y = j+dy[k];
							}
						}
					} else numTaken++;
				}
				System.out.println(values[min1x][min1y]);
				System.out.println(min1x + " " + min1y + " " + min2x + " " + min2y);
				System.out.println(numTaken);
				if(numTaken==1) { board[min1x][min1y] = true;}
				else if(numTaken==0) {
					System.out.println("yes");
					board[min1x][min1y] = true;
					board[min2x][min2y] = true;
					//System.out.println(min1 + " " + min2);
				}
				
			}
			
		}
		int total = 0;
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				if(!board[i][j]) System.out.print('C');
				else System.out.print('.');
				if(!board[i][j]) total+=values[i][j];
			}
			System.out.println();
		}
		System.out.println(total);
	}

}
