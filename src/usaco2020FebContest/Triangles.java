package usaco2020FebContest;

import java.io.*;
import java.util.*;

public class Triangles {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int[][] locs = new int[n][2];
		X[] xs = new X[n];
		Y[] ys = new Y[n];
		long[][] sumLocs = new long[n][2];
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			locs[i][0] = Integer.valueOf(st.nextToken());
			locs[i][1] = Integer.valueOf(st.nextToken());
			xs[i] = new X(locs[i][0],locs[i][1],i);
			ys[i] = new Y(locs[i][0],locs[i][1],i);
		}
		Arrays.sort(xs);
		Arrays.sort(ys);
		//System.out.println(Arrays.toString(xs));
		
		int firsty = xs[0].y;
		int firstx = xs[0].x;
		
		ArrayList<Long> sumXs = new ArrayList<>();
		ArrayList<Integer> newXs = new ArrayList<>();
		newXs.add(0);
		sumXs.add((long) 0);
		for(int i = 1; i<n; i++) {
			if(xs[i].y!=firsty) {
				firsty = xs[i].y;
				firstx = xs[i].x;
				sumXs.add((long) 0);
				newXs.add(i);
			} else {
				sumXs.set(sumXs.size()-1, (sumXs.get(sumXs.size()-1))+xs[i].x-firstx);
			}
		}
		newXs.add(n);
		//System.out.println(sumXs);
		
		long[] sumLinesX = new long[sumXs.size()];
		sumLinesX[0]=sumXs.get(0);
		sumLocs[0][0] = sumXs.get(0);
		firsty = xs[0].y;
		int numy = 0;
		for(int i=1; i<n; i++) {
			if(xs[i].y!=firsty) {
				firsty = xs[i].y;
				numy++;
				sumLinesX[numy] = sumXs.get(numy);
				sumLocs[xs[i].id][0] = sumXs.get(numy);
			} else {
				sumLinesX[numy]+= (xs[i].x-xs[i-1].x)*(2*i-newXs.get(numy)-newXs.get(numy+1));
				//System.out.println((xs[i].x-xs[i-1].x)*(2*i-newXs.get(numy)-newXs.get(numy+1)));
				sumLocs[xs[i].id][0] = sumLinesX[numy];
			}
		}
		for(int i = 0; i<n; i++) {
		//	System.out.println(Arrays.toString(sumLocs[i]));
		}
		
		firstx = ys[0].x;
		firsty = ys[0].y;
		
		ArrayList<Long> sumYs = new ArrayList<>();
		ArrayList<Integer> newYs = new ArrayList<>();
		newYs.add(0);
		sumYs.add((long) 0);
		for(int i = 1; i<n; i++) {
			if(ys[i].x!=firstx) {
				firstx = ys[i].x;
				firsty = ys[i].y;
				sumYs.add((long) 0);
				newYs.add(i);
			} else {
				sumYs.set(sumYs.size()-1,sumYs.get(sumYs.size()-1)+ys[i].y-firsty);
			}
		}
		newYs.add(n);
		
		long[] sumLinesY = new long[sumYs.size()];
		
		sumLinesY[0]=sumYs.get(0);
		sumLocs[0][1] = sumYs.get(0);
		firstx = ys[0].x;
		int numx = 0;
		for(int i=1; i<n; i++) {
			if(ys[i].x!=firstx) {
				firstx = ys[i].x;
				numx++;
				sumLinesY[numx] = sumYs.get(numx);
				sumLocs[ys[i].id][1] = sumYs.get(numx);
			} else {
				sumLinesY[numx]+= (ys[i].y-ys[i-1].y)*((2*i)-newYs.get(numx)-newYs.get(numx+1)); //check this
				sumLocs[ys[i].id][1] = sumLinesY[numx];
			}

		}
		
		long sum = 0;
		for(int i = 0; i<n; i++) {
			System.out.println(Arrays.toString(sumLocs[i]));
			sum+=(Math.abs(sumLocs[i][0] * sumLocs[i][1]));
			sum %= 1000000007;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		pw.println(sum);
		System.out.println(sum);
		
		pw.close();
		br.close();
	}

}

class X implements Comparable<X>{
	public int x;
	public int y;
	public int id;
	public X(int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	@Override
	public int compareTo(X o) {
		// TODO Auto-generated method stub
		if(y==o.y) return x-o.x;
		else return y-o.y;
	}
	public String toString() {
		return x + " " + y + " " + id;
	}
}


class Y implements Comparable<Y>{
	public int x;
	public int y;
	public int id;
	public Y(int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	@Override
	public int compareTo(Y o) {
		// TODO Auto-generated method stub
		if(x==o.x) return y-o.y;
		else return x-o.x;
	}
	public String toString() {
		return x+ " " + y + " " + id;
	}
}
