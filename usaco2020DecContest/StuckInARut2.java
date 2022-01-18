package usaco2020DecContest;

import java.io.*;
import java.util.*;

public class StuckInARut2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		Cow[] cows = new Cow[n];
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			String eu = st.nextToken();
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			
			cows[i] = new Cow(x,y,i,eu);
		}

		ArrayList<Cow> total = new ArrayList<>();
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				if(i!=j) { //b stops a
					Cow a = cows[i];
					Cow b = cows[j];
					if(a.direction.equals(b.direction)) {
						if(a.direction.equals("N") && a.x==b.x) {
							if(b.y-a.y>0) {
								total.add(new Cow(b.x,b.y,b.pos,b.direction,b.y-a.y,a));
							}
						}
						else if(a.direction.equals("E") && a.y==b.y) {
							if(b.x-a.x>0) {
								total.add(new Cow(b.x,b.y,b.pos,b.direction,b.x-a.x,a));
							}	
						}
					}
					else {
						if(a.direction.equals("N")) {
							if(b.y>a.y && b.x<=a.x && a.x-b.x<b.y-a.y) {
								total.add(new Cow(b.x,b.y,b.pos,b.direction,b.y-a.y,a));
							}
						}
						else {
							if(b.y<=a.y && b.x>a.x && a.y-b.y<b.x-a.x) {
								total.add(new Cow(b.x,b.y,b.pos,b.direction,b.x-a.x,cows[i]));
							}
						} 
					}
				}
			}
		}
		Collections.sort(total);
		int[] blamedBy = new int[n];
		int[] step = new int[n];
		Arrays.fill(blamedBy, -1);
		for(int i = 0; i<total.size(); i++) {
			Cow first = total.get(i); //b
			Cow second = total.get(i).c; //first stops second
			if(blamedBy[second.pos]==-1) {
				if(first.direction.equals(second.direction) && first.direction.equals("N")) {
					blamedBy[second.pos] = first.pos; step[second.pos] = first.step;
				}
				else if(first.direction.equals(second.direction) && first.direction.equals("E")) {
					blamedBy[second.pos] = first.pos; step[second.pos] = first.step;
				}
				else if(first.direction.equals("N") && second.direction.equals("E")) {
					if(blamedBy[first.pos]==-1) {
						blamedBy[second.pos] = first.pos; step[second.pos] = first.step;
					}
					else if(first.y==second.y) {
						blamedBy[second.pos] = first.pos; step[second.pos] = first.step;
					}
					else if(first.y+step[first.pos]>=second.y) {
						blamedBy[second.pos] = first.pos; step[second.pos] = first.step;
					}
				}
				else if(first.direction.equals("E") && second.direction.equals("N")) {
					if(blamedBy[first.pos]==-1) {
						blamedBy[second.pos] = first.pos; step[second.pos] = first.step;
					}
					else if(first.x==second.x) {
						blamedBy[second.pos] = first.pos; step[second.pos] = first.step;
					}
					else if(first.x+step[first.pos]>=second.x) {
						blamedBy[second.pos] = first.pos; step[second.pos] = first.step;
					}
				}
			}
		}
		int[] blame = new int[n];
		for(int i = 0; i<n; i++) {
			int curr = i;
			while(blamedBy[curr]>-1) {
				blame[blamedBy[curr]]+=1;
				curr = blamedBy[curr];
			}
		}
		
		for(int i = 0; i<n; i++) {
			System.out.println(blame[i]);
		}
		
	}

}

class Cow implements Comparable<Cow> {
	public int x; 
	public int y;
	public String direction; 
	public int pos;
	public int step; //steps until stopped
	public Cow c;
	public Cow(int x, int y, int pos, String direction) {
		this.x = x; this.y = y; this.pos = pos; this.direction = direction;
	}
	public Cow(int x, int y, int pos, String direction, int step, Cow c) {
		this.x = x; this.y = y; this.pos = pos; this.direction = direction; this.step = step; this.c=c;
	}
	
	public int compareTo(Cow other) {
		if(this.step!=other.step) return this.step-other.step;
		else if(this.x==other.x) return other.y-this.y;
		return other.x-this.x;
	}
	
	public String toString() {
		if(c==null) return (pos+1) + " " + x + " " + y + " " + step;
		return (pos+1) + " " + x + " " + y + " " + " " + step + "   cow  " + c;
	}
}