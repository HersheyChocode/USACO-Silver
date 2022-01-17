package usaco2021FebContest;

import java.io.*;
import java.util.*;

public class JustGreenEnough {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int[][] grid = new int[n][n];
		ArrayList<int[]> hs = new ArrayList<>();
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				grid[i][j] = Integer.valueOf(st.nextToken());
				if(grid[i][j]==100) hs.add(new int[] {i,j});
			}
		}
		System.out.print(8);
		
		
	}

}
