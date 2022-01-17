package usaco2020DecContest;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class StuckInARut {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		//int[][] coords = new int[1000000][1000000];
		int[][] positions = new int[n][2];
		ArrayList<Up> ups = new ArrayList<>();
		ArrayList<Right> rights = new ArrayList<>();
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String eu = st.nextToken();
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			positions[i] = new int[] {x,y};
			if(eu.equals("N")) ups.add(new Up(x,y,i,ups.size()));
			else rights.add(new Right(x,y,i,rights.size()));
			if(x>maxX) maxX = x;
			if(y>maxY) maxY = y;
		}
		int[][] coords = new int[maxX+1][maxY+1];
		for(int i = 0; i<n; i++) {
			coords[positions[i][0]][positions[i][1]] = i;
		}
		Collections.sort(rights);
		Collections.sort(ups);
		System.out.println(rights);
		System.out.println(ups);
		int[] blame = new int[n];
		int[][] stopped = new int[n][n]; //first n = curr cow, second n = cow that stopped curr, val = #steps
		
		for(int i = 0; i<rights.size(); i++) {
			Right x = rights.get(i);
			boolean foundOne = false;
			for(int j = 0; j<ups.size() && !foundOne; j++) {
				Up y = ups.get(j);
				if(y.x>x.x && y.y<x.y) {
					int distY = x.y-y.y;
					if(x.x+distY<y.x) {
						int pos = coords[y.x][y.y];
						blame[pos]+=1;
						stopped[pos][coords[x.x][x.y]] = distY;
						foundOne = true;
						System.out.println(x + " " + y);
					}
				}
			}
		}
		for(int i = 0; i<ups.size(); i++) {
			Up y = ups.get(i);
			boolean foundOne = false;
			for(int j = 0; j<rights.size() && !foundOne; j++) {
				Right x = rights.get(j);
				if(y.x>x.x && y.y<x.y) {
					int distY = x.y-y.y;
					if(x.x+distY>y.x) {
						int pos = coords[y.x][y.y];
						blame[pos]+=1;
						stopped[pos] [coords[x.x][x.y]] = distY;
						foundOne = true;
						//System.out.println(x + " " + y);
					}
				}
			}
		}
		
		for(int i = 0; i<n; i++) {
			
		}
		
		for(int i = 0; i<n; i++) {
			System.out.println(blame[i]);
		}
		
		
		
	}

}

class Up  implements Comparable<Up>{
	public int x;
	public int y;
	public int pos; 
	public int arrLoc;
	public Up (int x, int y, int pos, int arrLoc) {
		this.x = x; this.y = y; this.pos = pos; this.arrLoc = arrLoc;
	}
	
	public int compareTo(Up other) {
		if(other.x==this.x) {
			return other.y-this.y;
		} else {
			return other.x - this.x;
		}
	}
	public String toString() {
		return x + " " + y;
	}
}


class Right implements Comparable<Right>{
	public int x;
	public int y;
	public int pos;
	public int arrLoc;
	public Right(int x, int y, int pos, int arrLoc) {
		this.x = x; this.y = y; this.pos = pos; this.arrLoc = arrLoc;
	}
	
	public int compareTo(Right other) {
		if(other.x==this.x) {
			return other.y-this.y;
		} else {
			return other.x - this.x;
		}
	}
	
	public String toString() {
		return x + " " + y;
	}
}