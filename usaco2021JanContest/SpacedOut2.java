package usaco2021JanContest;

import java.io.*;
import java.util.*;

public class SpacedOut2 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int[][] values = new int[n][n];
		//boolean[][] board = new boolean[n][n];
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				values[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		//horizontal
		int total1 = 0;
		for(int i = 0; i<n; i++) {
			int num1 = 0;
			for(int j = 0; j<n; j+=2) {
				num1+=values[i][j];
			}
			int num2 = 0;
			for(int j = 1; j<n; j+=2) {
				num2+=values[i][j];
			}
			total1+=Math.max(num1, num2);
		}
		
		//vertical
		int total2 = 0;
		for(int i = 0; i<n; i++) {
			int num1 = 0;
			for(int j = 0; j<n; j+=2) {
				num1+=values[j][i];
			}
			int num2 = 0;
			for(int j = 1; j<n; j+=2) {
				num2+=values[j][i];
			}
			total2+=Math.max(num1, num2);
		}
		
		System.out.println(Math.max(total1, total2));
	}

}
