package bovineShuffle;
import java.io.*;
import java.util.*;

public class Shuffle {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader ("shuffle.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		boolean[] cycled = new boolean[n];
		boolean[] discovered = new boolean[n];
		boolean[] totalDiscovered = new boolean[n];
		int[] nums = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(st.nextToken())-1;
		}
		
		int counter = 0;
		
		for(int i = 0; i<n; i++) {
			//Arrays.fill(discovered, false);
			//discovered  = new boolean[n];
			
			int prev = nums[i];
			int end = i;
			totalDiscovered[i] = true;
			discovered[i] = true;
			boolean found = false;
			ArrayList<Integer> countDiscovered = new ArrayList<>();
			
			for(int j = 0; j<n&&!discovered[prev]&&!cycled[prev]&&!found; j++) {
				if(totalDiscovered[prev]) found = true;
				discovered[prev] = true;
				countDiscovered.add(prev);
				totalDiscovered[prev]= true;
				end = prev;
				prev = nums[prev];
				//if(totalDiscovered[prev]&&nums[prev]!=nums[i]&&!cycled[prev]) found = true;
			} 
			discovered[i] = false;
			for(int j = 0; j<countDiscovered.size(); j++) {
				discovered[countDiscovered.get(j)] = false;
			}
			if(!cycled[prev] && !found) {
				//System.out.println(prev + " "  + end);
				for(int j = 0; j<n&&!cycled[end]; j++) {
					cycled[prev] = true;
					prev = nums[prev];
					counter++;
				}
			}
		}
		//System.out.println(counter);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		pw.write(String.valueOf(counter));
		pw.close();
	}
	

}
