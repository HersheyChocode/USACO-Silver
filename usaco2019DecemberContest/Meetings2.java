package usaco2019DecemberContest;

import java.io.*;
import java.util.*;

public class Meetings2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int l = Integer.valueOf(st.nextToken());
		
		Cows[] cows = new Cows[n];
		int tw = 0;
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = new Cows(Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),i);
			tw += cows[i].w;
		} 
		Arrays.sort(cows);
		
		ArrayList<Double> collisions = new ArrayList<>();
		for(int i = 0; i<n; i++) {
			Cows x = cows[i];
			for(int j = i; j<n; j++) {
				Cows y = cows[j];
				if(i!=j) {
					if(x.d!=y.d) {
						if(x.d==1) {
							if(x.x<=y.x) {collisions.add((y.x-x.x)/2); double temp = x.w; x.w=y.w; y.w=temp;}
						} else {
							if(y.x<=x.x) {collisions.add((x.x-y.x)/2); double temp = x.w; x.w=y.w; y.w=temp;}
						}
					}
				}
			}
		}
		//System.out.println(Arrays.toString(cows));
		Times[] times = new Times[n];
		for(int i = 0; i<n; i++) {
			if(cows[i].d==1) {
				times[i] = new Times(cows[i].w,(int) (l-cows[i].x),cows[i].d);
			} else {
				times[i] = new Times(cows[i].w,(int)(cows[i].x),cows[i].d);
			}
		}
		Arrays.sort(times);
		//System.out.println(Arrays.toString(times));
		Collections.sort(collisions);
		//System.out.println(collisions);
		boolean fine = true;
		int counter = -1;
		double w = 0;
		for(int i = 0; i<n&&fine; i++) {
			counter++;
			w+=times[i].w;
			if(w>=tw/2) fine = false;
		}
		
		double t = times[counter].t;
		//System.out.println(t);
		counter = 0;
		fine = true;
		double x = -2;
		for(int i = 0; i<collisions.size()&&fine; i++) {
			if(x==-2) counter++;
			if(x!=-2) {
				if(collisions.get(i)!=x) fine = false;
				else counter++;
			}
			else if(collisions.get(i)>=t) x = collisions.get(i);
		}
		System.out.println(x+ " " + counter);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		pw.println(counter);
		pw.close();
		br.close();
		System.out.println((counter));
	}

}
class Cows implements Comparable<Cows>{
	public double w;
	public double x; //position
	public int d; //direction
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
	public String toString() {
		return "x " +  x ;
	}
}
class Times implements Comparable<Times>{
	public double w;
	public double t; //position
	public int id;
	public Times(double w, double t, int id) {
		this.w = w;
		this.t = t; 
		this.id = id;
	}
	public int compareTo(Times other) {
		if(t!=other.t) return (int)(t*2 - other.t*2);
		else return (int)(w*2-other.w*2);
	}
	public String toString() {
		return "t " +  t  ;
	}
}