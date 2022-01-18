package usaco2019DecemberContest;

import java.io.*;
import java.util.*;

public class Meetings {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int l = Integer.valueOf(st.nextToken());
		
		Cows[] cows = new Cows[(int) n*2];
		int w = 0;
		HashMap<Double,ArrayList<Double>> cowPositions = new HashMap<>();
		boolean unableToMeet = false;
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = new Cows(Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),i);
			cowPositions.put(cows[i].x, new ArrayList<Double>());
			cowPositions.get(cows[i].x).add((double) cows[i].id);
			w += cows[i].w;
		}
		int pos = 0;
		int neg = 0;
		for(int i = 0; i<n; i++) {
			if(cows[i].d == 1) pos++;
			else neg++;
		}
		if(pos==n || neg==n) unableToMeet = true;
		
		int totalSum = 0;
		int reachedEnd = 0;
		while(reachedEnd<(w+1)/2 && !unableToMeet) {
			double maxNeg = Integer.MIN_VALUE;
			double minPos = Integer.MAX_VALUE;
			
			//changes position
			for(int i = 0; i<n; i++) {
				if(cows[i].x!=0 && cows[i].x!=l) {
					cowPositions.get(cows[i].x).remove((double) cows[i].id);
					if(cows[i].d==1) {
						cows[i].x += 0.5;
					}
					else {
						cows[i].x -= 0.5;
					}
					if(cowPositions.containsKey(cows[i].x)) cowPositions.get(cows[i].x).add((double) cows[i].id);
					else {
						cowPositions.put(cows[i].x,new ArrayList<Double>());
						cowPositions.get(cows[i].x).add((double)cows[i].id);
					}
					System.out.print(cows[i].x + " ");
				}
			}
			System.out.println();
			
			//calculates how many meetings and changes the direction of those cows
			int meets = 0;
			for(int i = 0; i<n; i++) {
				if(cowPositions.get(cows[i].x).size()>1 && cows[i].x!=0 && cows[i].x!=l) {
					meets++;
					cows[i].d = -cows[i].d;
					if(cows[i].d==1.0 && cows[i].x<minPos) minPos = cows[i].x;
					if(cows[i].d==-1.0 && cows[i].x>maxNeg) maxNeg = cows[i].x;

				}
				System.out.print(cows[i].d + " ");
			}
			System.out.println();
			totalSum+=meets/2;
			
			//updates reachedEnd and unableToMeet
			for(int i = 0; i<n; i++) {
				if(cows[i].x==0 || cows[i].x==l) {
					reachedEnd+=cows[i].w;
					if(cows[i].d==1) pos--;
					else neg--;
				}
			}
			System.out.println(maxNeg + " " + minPos);
			if(maxNeg<=minPos) unableToMeet = true;
		}
		
		/*if(unableToMeet) {
			ArrayList<Cows> list = new ArrayList<>();
			for(int i = 0; i<n; i++) {
				if(cows[i].x!=0 && cows[i].x!=l) {
					if(cows[i].d==1) list.add(new Cows(cows[i].w,l-cows[i].x,-1,cows[i].id));
					else list.add(new Cows(cows[i].w,cows[i].x,-1,cows[i].id));
				}
			}
		}*/
		
		System.out.println(totalSum);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		pw.println(totalSum);
		pw.close();
		br.close();
	}
	

}
/*
class Cows implements Comparable<Cows>{
	public double w;
	public double x;
	public double d;
	public int id;
	public Cows(double w2, double e, int d, int id) {
		this.w = w2;
		this.x = e;
		this.d = d;
		this.id = id;
	}
	public int compareTo(Cows other) {
		if(x!=other.x) return (int) (x*2-other.x*2);
		else return id-other.id;
	}
}
*/