package usaco2016OpenContest;

import java.io.*;
import java.util.*;

public class FieldReduction {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		Coord[] coords = new Coord[n];
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			coords[i] = new Coord(x,y);
		}
		int min = Integer.MAX_VALUE;
		System.out.println(Arrays.toString(coords));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		for(int i = 0; i<n-3; i++) {
			int yMax = -5;
			int xMax = -5;
			int yMin = Integer.MAX_VALUE;
			int xMin = Integer.MAX_VALUE;
			if(i>0) {
				for(int j = i; j<i+3; j++) {
					if(coords[j].y>yMax) yMax = coords[j].y;
					if(coords[j].x>xMax) xMax = coords[j].x;
					if(coords[j].y<yMin) yMin = coords[j].y;
					if(coords[j].x<xMin) xMin = coords[j].x;
				}
			}
			int dy = yMax - yMin;
			int dx = xMax - xMin;
			if(dx*dy<min) min = dx*dy;
		}
		
		pw.close();
		br.close();
		
		int xmin = -1;
		int minx = -1;
		int maxx = -1;
		int xmax = -1;
		for(int i = 1; i<n; i++) {
			
		}
	}

}

class Coord implements Comparable<Coord> {
	int x;
	int y;
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public  String toString() {
		return(x + " " + y);
	}
	public int compareTo(Coord other) {
		double dist = Math.sqrt(Math.pow(other.x-this.x,2) + Math.pow(other.y-this.y, 2));
		return (int) dist;
	}
}