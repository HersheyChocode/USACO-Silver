package usaco2016FebContest;

import java.io.*;
import java.util.*;

public class Balancing {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		
		CoordY[] yVals = new CoordY[n];
		CoordX[] xVals = new CoordX[n];
		
		HashMap<Integer,ArrayList<Integer>> yToX = new HashMap<>();
		HashMap<Integer,ArrayList<Integer>> xToY = new HashMap<>();
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			yVals[i] = new CoordY(x,y);
			xVals[i] = new CoordX(x,y);
			if(yToX.containsKey(y)) yToX.get(y).add(x);
			else {
				yToX.put(y,new ArrayList<>());
				yToX.get(y).add(x);
			}
			if(xToY.containsKey(x)) xToY.get(x).add(y);
			else {
				xToY.put(x,new ArrayList<>());
				xToY.get(x).add(y);
			}
		}
		Arrays.sort(yVals);
		Arrays.sort(xVals);
		
		int q1 = 0; 
		int q2 = 0; 
		int q3 = 0;
		int q4 = 0;
		int yLine = yVals[0].y+1;
		int xLine = xVals[0].x+1;
		for(int i = 0; i<n; i++) {
			if(yVals[i].y<yLine) {
				if(yVals[i].x<xLine) {
					q3++;
				} else q4++;
			} else {
				if(yVals[i].x<xLine) {
					q2++;
				} else q1++;
			}
		}
		int[] quarters = {q1,q2,q3,q4};
		int minOfMax = Math.max(Math.max(q1,q2),Math.max(q3,q4));
		for(int y = 0; y<n; y++) {
			yLine = yVals[y].y+1;
			q1 = quarters[0]; q2 = quarters[1]; q3 = quarters[2]; q4 = quarters[3];
			if(y!=0 && yLine!=yVals[y-1].y+1) {
				for(int i:yToX.get(yVals[y].y)) {
					if(i<xVals[0].x+1) {
						q2--;
						q3++;
					} else {
						q1--;
						q4++;
					}
				}
			}
			quarters = new int[] {q1,q2,q3,q4};
			minOfMax = Math.min(minOfMax,Math.max(Math.max(q1,q2),Math.max(q3,q4)));
			//System.out.println(q1+ " " + q2 + " " + q3 + " " + q4 + "here"+ " " + yLine);
			for(int x = 1; x<n; x++) {
				xLine = xVals[x].x+1;
				if(y!=0 && yLine!=yVals[y-1].y+1 && xLine!=xVals[x-1].x+1) {
					for(int i : xToY.get(xVals[x].x)) {
						if(i<yLine) {
							q3++;
							q4--;
						} else {
							q2++;
							q1--;
						}
					}
				}
				minOfMax = Math.min(minOfMax,Math.max(Math.max(q1,q2),Math.max(q3,q4)));
				//System.out.println(q1+ " " + q2 + " " + q3 + " " + q4 + " xline " + xLine);
			}
		}
		System.out.println(minOfMax);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		pw.write(String.valueOf(minOfMax));
		pw.close();
		br.close();
	}

}

class CoordY implements Comparable<CoordY>{
	public int x;
	public int y;
	public CoordY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int compareTo(CoordY other) {
	    if (y != other.y) {
	      return y - other.y;
	    } else {
	      return x-other.x;
	    }
	  }
	public String toString() {
		return x + " " + y;
	}
	
}

class CoordX implements Comparable<CoordX>{
	public int x;
	public int y;
	public CoordX(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int compareTo(CoordX other) {
	    if (x != other.x) {
	      return x - other.x;
	    } else {
	      return y-other.y;
	    }
	  }
	public String toString() {
		return x + " " + y;
	}
	
}