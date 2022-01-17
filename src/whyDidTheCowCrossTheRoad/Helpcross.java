package whyDidTheCowCrossTheRoad;
import java.io.*;
import java.util.*;

public class Helpcross {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int c = Integer.valueOf(st.nextToken());
		int n = Integer.valueOf(st.nextToken());
		
		ArrayList<Event> acs = new ArrayList<>();
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>();
		
		for(int i = 0; i<c; i++) {
			acs.add(new Event(Integer.valueOf(br.readLine()),-1,false));
		}
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			acs.add(new Event(Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),true));
		}
		br.close();
		Collections.sort(acs);
		
		System.out.println(acs);
		//System.out.println(pq2);
		
		int counter = 0;
		for(int i = 0; i<acs.size(); i++) {
			if (acs.get(i).isCow) {
				pq2.add(acs.get(i).b);
			}
			else {
				int x = acs.get(i).a;
				boolean foundb = false;
				int b = -1;
				while(!foundb&&pq2.size()>0) {
					b = pq2.peek();
					if(b<x) { pq2.poll(); }
					else {
						foundb = true;
						/*if(nums.get(b).size()==1) {
							pq2.poll();
						}
						nums.get(b).remove(0);*/
						pq2.poll();
						counter++;
					}
				}
			}
		}
		
		System.out.println(counter);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
		pw.write(String.valueOf(counter));
		pw.close();
		
	}
	

}
class Event implements Comparable<Event>{
	public int a;
	public int b; 
	public boolean isCow;
	public Event(int a, int b, boolean isCow) {
		this.a = a;
		this.b = b;
		this.isCow = isCow;
	} 
	
	@Override
	public int compareTo(Event other) {
		if(this.a==other.a) {
			if(this.isCow||other.isCow) {
				return -1;
			}
			return -1;
		}
		return this.a-other.a;
	}
	
	public String toString() {
		return "a: " + a + "\tb: " +b + "\tisCow: " + isCow;
	}
}