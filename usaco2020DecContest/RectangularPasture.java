package usaco2020DecContest;

import java.util.*;
import java.io.*;

public class RectangularPasture {
	static boolean[] totalVisited;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		
		int[][] coords = new int[n][2];
		boolean[][] cells = new boolean[n][n];
		X[] Xs = new X[n];
		Y[] Ys = new Y[n];
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			coords[i][0] = Integer.valueOf(st.nextToken());
			coords[i][1] = Integer.valueOf(st.nextToken());
			Xs[i] = new X(coords[i][0],coords[i][1],i);
			Ys[i] = new Y(coords[i][0],coords[i][1],i);
		}
		Arrays.sort(Xs);
		Arrays.sort(Ys);
		for(int i=0; i<n; i++) {coords[Xs[i].pos][0] = i; coords[Ys[i].pos][1] = i; Xs[i].x=i; Ys[i].y = i;}
		
		for(int i = 0; i<n; i++) { cells[coords[i][0]][coords[i][1]] = true;}
		int[][] prefixSums = new int[n][n];
		if(Xs[0].pos==Ys[0].pos) prefixSums[0][0] = 1;
		for(int i=1;i<n;i++) {
			if(cells[0][i]) prefixSums[0][i]+=1;
			else prefixSums[0][i] = prefixSums[0][i-1];
			if(cells[i][0]) prefixSums[i][0] += 1;
			else prefixSums[i][0] = prefixSums[i-1][0];
		}
		for(int i = 1; i<n; i++) {
			for(int j =1; j<n; j++) {
				int ab = prefixSums[i-1][j];
				int bc = prefixSums[i][j-1];
				int b = prefixSums[i-1][j-1];
				int x = 0; if(cells[i][j]) x= 1;
				prefixSums[i][j] = ab+bc-b+x;
			}
		}
		
		long counter = 0;
		for(int i = 0; i<n; i++) {
			for(int j = i; j<n; j++) {
				int x1 = coords[i][0]; 
				int y1 = coords[i][1];
				int x2 = coords[j][0];
				int y2 = coords[j][1];
				int top; int bottom;
				
				int x3 = Math.min(x1, x2);
				int x4 = Math.max(x1, x2);
				int y3 = Math.min(y1, y2);
				int y4 = Math.max(y1,y2);
				
				if(x3!=x4) {
					int abcd = prefixSums[x4][Ys[n-1].y];
					int bc = prefixSums[x3][Ys[n-1].y];
					int cd = prefixSums[x4][y4];
					int c = prefixSums[x3][y4];
					top = abcd-cd-bc+c;
					if(x3!=0)bottom = prefixSums[x4][y3]-prefixSums[x3][y3];
					else bottom = prefixSums[x4][y3];
				} else {
					int abcd = prefixSums[x3][Ys[n-1].y];
					int bc;
					if(x3!=0) bc = prefixSums[x3-1][Ys[n-1].y];
					else bc = 0;
					int cd = prefixSums[x3][y4];
					int c;
					if(x3!=0) c = prefixSums[x3-1][y4];
					else c = 0;
					top = abcd-bc-cd+c;
					if(x3!=0) bottom = prefixSums[x3][y3]-prefixSums[x3-1][y3];
					else bottom = prefixSums[x3][y3];
				}
				top++;
				if(x3!=0 && ((x3==x4&&cells[x3-1][y3])||(x3!=x4&&cells[x3][y3]))) bottom++;
				counter+=top*bottom;
			}
		}
		System.out.println(counter+1);
	}
	
	public static void subsets(ArrayList<Integer> arr, int n, int max) {
		if(n==max) {
			System.out.println(arr); return; 
		}
		subsets(arr,n+1,max);
		arr.add(n);
		subsets(arr,n+1,max);
		arr.remove(arr.size()-1);
	}

}

class X implements Comparable<X> {
	public int x;
	public int y;
	public int pos;
	public X(int x, int y, int pos) {
		this.x = x;
		this.y = y;
		this.pos = pos;
	}
	
	public int compareTo(X o) {
		// TODO Auto-generated method stub
		if(this.x==o.x) return this.y-o.y;
		return this.x-o.x;
	}
}

class Y implements Comparable<Y> {
	public int x;
	public int y;
	public int pos;
	public Y(int x, int y, int pos) {
		this.x = x;
		this.y = y;
		this.pos = pos;
	}
	
	public int compareTo(Y o) {
		// TODO Auto-generated method stub
		if(this.y==o.y) return this.x-o.x;
		return this.y-o.y;
	}
}