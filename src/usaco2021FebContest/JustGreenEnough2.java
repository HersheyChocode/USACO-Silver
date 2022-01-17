package usaco2021FebContest;

import java.io.*;
import java.util.*;

public class JustGreenEnough2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int[][] grid = new int[n][n];
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				grid[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		int[][] min100 = new int[n][n];
		int[][] less100 = new int[n][n];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				
				if(i==0) {
					if(j==0) {
						if(grid[0][0]==100) min100[0][0]=1; 
						else min100[0][0]=0;
						if(grid[0][0]<100) less100[0][0]=1;
						else less100[0][0] = 0;
					}
					else {
						if(grid[0][j]==100) min100[0][j] = min100[0][j-1]+1;
						else min100[0][j] = min100[0][j-1];
						if(grid[0][j]<100) less100[0][j] = less100[0][j-1]+1;
						else less100[0][j] = less100[0][j-1];
					}
				}
				else if(j==0) {
					if(grid[i][j]==100) min100[i][j] = min100[i-1][j]+1;
					else min100[i][j] = min100[i-1][j];
					if(grid[i][j]<100) less100[i][j] = less100[i-1][j]+1;
					else less100[i][j] = less100[i-1][j];
				}
				else {
					int d = 0;
					if(grid[i][j]==100) d = 1;
					min100[i][j] = d + min100[i][j-1] + min100[i-1][j] - min100[i-1][j-1];
					d = 0;
					if(grid[i][j]<100) d = 1;
					less100[i][j] = d + less100[i][j-1] + less100[i-1][j] - less100[i-1][j-1];
				}
			}
		}
		int[][] first100 = new int[n][n];
		int[][] firstLess = new int[n][n];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n;j++) {
				first100[i][j] = n;
				firstLess[i][j] = n;
				boolean foundOne = false;
				boolean foundTwo = false;
				for(int k = j; k<n; k++) {
					if(!foundOne && grid[i][k]==100) {
						foundOne = true;
						first100[i][j]=k;
					} else if (!foundTwo && grid[i][k]<100) {
						foundTwo = true;
						firstLess[i][j]=k;
					}
				}
			}
		//	System.out.println(Arrays.toString(first100[i]));
			//System.out.println(Arrays.toString(firstLess[i]));
		}
		long sum = 0;
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				if(grid[i][j]>=100) {
					int prev100 = n;
					int prevL = n;
					for(int k = i; k<n; k++) {
						int f100 = first100[k][j];
						int fL = firstLess[k][j];
						if(prevL!=n) {
							prevL=Math.min(fL, prevL);
						}
						else prevL = fL;
						if(prev100!=n) {
							if(prev100<prevL && f100<prevL) prev100 = Math.min(prev100, f100);
							else if(f100<prevL) prev100 = f100;
							else if(prev100>=prevL && f100>=prevL) prev100 = n;
						}
						else prev100 = f100;
						if(prev100!=n && prev100<prevL) {
							sum+=prevL-prev100;
						}
					}
				}
			}
		}
		
		//System.out.println(sum);
		
		/*for(int i = 0; i<n; i++) { //top left y
			for(int j = 0; j<n; j++) { //top left x
				for(int k = i; k<n; k++) { //bottom right y
					for(int l = j; l<n; l++) { //bottom right x
						if(i==0) {
							if(j==0) {
								if(min100[k][l]>=1 && less100[k][l]==0) {
									sum++;
									//System.out.println(i + " " + j +" " + k +" "  + l);
								}
							} else {
								if(min100[k][l]-min100[k][j-1]>=1 && less100[k][l]-less100[k][j-1]==0) {
									sum++;
									//System.out.println(i + " " + j +" " + k +" "  + l);
								}
							}
						}
						else if(j==0) {
							if(min100[k][l]-min100[i-1][l]>=1 && less100[k][l]-less100[i-1][l]==0) {
								sum++;
								//System.out.println(i + " " + j +" " + k +" "  + l);
							}
						}
						else if(min100[k][l]-min100[k][j-1]-min100[i-1][l]+min100[i-1][j-1]>=1) {
							if(less100[k][l]-less100[i-1][l]-less100[k][j-1]+less100[i-1][j-1]==0) {
								sum++;
								//System.out.println(i + " " + j +" " + k +" "  + l);
							}
						}
					}
				}
			}
		}*/

		//PrintWriter pw = new PrintWriter(System.out);
		//pw.print(sum);
		//pw.close();
		System.out.println(sum);
		
	}

}
