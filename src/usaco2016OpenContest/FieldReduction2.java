package usaco2016OpenContest;

import java.io.*;
import java.util.*;

public class FieldReduction2 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int[][] coords = new int[n][2];
		X[] coordsX = new X[n];
		Y[] coordsY = new Y[n];
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			coords[i] = new int[] {x,y};
			coordsX[i] = new X(x,y);
			coordsY[i] = new Y(x,y);
		}
		Arrays.sort(coordsX);
		Arrays.sort(coordsY);
		int[][] outliers = new int[12][2];
		int counter = -1; 
		for(int i = 0; i<3; i++) {
			counter++;
			outliers[counter] = new int[] {coordsX[i].x, coordsX[i].y};
		}
		for(int i = 0; i<3; i++) {
			counter++;
			outliers[counter] = new int[] {coordsX[n-1-i].x, coordsX[n-1-i].y};
		}
		for(int i = 0; i<3; i++) {
			counter++;
			outliers[counter] = new int[] {coordsY[i].x, coordsY[i].y};
		}
		for(int i = 0; i<3; i++) {
			counter++;
			outliers[counter] = new int[] {coordsY[n-1-i].x, coordsY[n-1-i].y};
		}
		int minArea = Integer.MAX_VALUE;
		for(int i = 0; i<12; i++) {
			for(int j = i; j<12; j++) {
				for(int k = j; k<12; k++) {
					int[] c1 = new int[] {outliers[i][0], outliers[i][1]};
					int[] c2 = new int[] {outliers[j][0], outliers[j][1]};
					int[] c3 = new int[] {outliers[k][0], outliers[k][1]};
					counter = 0;
					while(counter<n && (coordsX[counter].equals2(new X(c1[0],c1[1])) || coordsX[counter].equals2(new X(c2[0],c2[1])) || coordsX[counter].equals2(new X(c3[0],c3[1])))) 
						counter++;
					X minX = coordsX[counter];
					counter = n-1;
					while(counter>=0 && (coordsX[counter].equals2(new X(c1[0],c1[1]))||coordsX[counter].equals2(new X(c2[0],c2[1]))||coordsX[counter].equals2(new X(c3[0],c3[1])))) 
						counter--;
					X maxX = coordsX[counter];
					counter = 0;
					while(counter<n && (coordsY[counter].equals2(new Y(c1[0],c1[1]))||coordsY[counter].equals2(new Y(c2[0],c2[1]))||coordsY[counter].equals2(new Y(c3[0],c3[1])))) 
						counter++;
					Y minY = coordsY[counter];
					counter = n-1;
					while(counter>=0 && (coordsY[counter].equals2(new Y(c1[0],c1[1]))||coordsY[counter].equals2(new Y(c2[0],c2[1]))||coordsY[counter].equals2(new Y(c3[0],c3[1]))))
						counter--;
					Y maxY = coordsY[counter];
					int arr = (maxX.x-minX.x)*(maxY.y-minY.y);
					if(arr<minArea) minArea = arr;
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		System.out.println(minArea);
		pw.println(minArea);
		br.close();
		pw.close();
	}

}

class Y implements Comparable<Y> {
	public int x;
	public int y;
	public Y(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return x + " " + y;
	}
	public int compareTo(Y o) {
		if(this.y == o.y) return this.x-o.x;
		else return this.y-o.y;
	}
	public boolean equals2(Y o) {
		if(this.y==o.y && this.x==o.x) return true;
		return false;
	}
	
}
class X implements Comparable<X> {
	public int x;
	public int y;
	public X(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int compareTo(X o) {
		if(this.x == o.x) return this.y-o.y;
		else return this.x-o.x;
	}
	public String toString() {
		return x + " " + y;
	}
	public boolean equals2(X o) {
		if(this.y==o.y && this.x==o.x) return true;
		return false;
	}
}
