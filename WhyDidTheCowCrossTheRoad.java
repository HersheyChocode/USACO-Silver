import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int c = Integer.valueOf(st.nextToken());
		int n = Integer.valueOf(st.nextToken());
		
		ArrayList<Event> acs = new ArrayList<>();
		int[] tc = new int[c];
		int[][] ab = new int[n][2];
		HashMap<Integer,ArrayList<Integer>> nums = new HashMap<Integer,ArrayList<Integer>>();
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>();
		
		for(int i = 0; i<c; i++) {
			tc[i] = Integer.valueOf(br.readLine());
			acs.add(new Event(tc[i],-1,false));
		}
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			ab[i][0] = Integer.valueOf(st.nextToken());
			ab[i][1] = Integer.valueOf(st.nextToken());
			if(!nums.containsKey(ab[i][1])) { 
				nums.put(ab[i][1], new ArrayList<Integer>());
			}
			nums.get(ab[i][1]).add(ab[i][0]);
			acs.add(new Event(ab[i][0],ab[i][1],true));
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
		if(this.a==other.a && other.isCow) {
			return -1;
		}
		return this.a-other.a;
	}
	
	public String toString() {
		return "a: " + a + "\tb: " +b + "\tisCow: " + isCow;
	}
}
