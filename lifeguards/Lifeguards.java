package lifeguards;

import java.io.*;
import java.util.*;

public class Lifeguards {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		
		Endpoint[] endpoints = new Endpoint[2*n];
		Endpoint[] pairs = new Endpoint[2*n];
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			endpoints[i*2] = new Endpoint(x, true, false, i*2);
			endpoints[i*2+1] = new Endpoint(y, false, true, i*2+1);
			pairs[i*2] = new Endpoint(x, true, false, i*2);
			pairs[i*2+1] = new Endpoint(y, false, true, i*2+1);
		}
		Arrays.sort(endpoints);
		for(int i = 0; i<n; i++) {
			endpoints[i].arrLoc = i;
		}
		int[] individualTimes = new int[n];
		TreeSet<Endpoint> numList = new TreeSet<Endpoint>();
		int prevTime = 0;
		int actTime = 0;
		for(int i = 0; i<2*n; i++) {
			if(numList.size()==1) {
				individualTimes[numList.last().id/2]+=endpoints[i].t-prevTime;
			}
			if(numList.size()>0) {
				actTime+=endpoints[i].t-prevTime;
			}
			if(endpoints[i].s) {
				numList.add(endpoints[i]);
			}
			else {
				Endpoint x = pairs[endpoints[i].id-1];
				numList.remove(x);
			}
			prevTime = endpoints[i].t;
		}
		int minTime = Integer.MAX_VALUE;
		for(int j = 0; j<n; j++) {
			if(individualTimes[j]<minTime) minTime = individualTimes[j];
		}
		
	    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));    
	    pw.println(actTime-minTime);
	    pw.close();
	    br.close();
	}

}

class Endpoint implements Comparable<Endpoint>{
	public boolean s;
	public boolean e;
	public int t;
	public int id;
	public int arrLoc;
	public Endpoint(int t, boolean s, boolean e, int id) {
		this.s = s;
		this.e = e;
		this.t = t;
		this.id = id;
	}
	public int compareTo(Endpoint o) {
		return this.t-o.t;
	}
	public String toString() {
		return t+"";
	}
}
