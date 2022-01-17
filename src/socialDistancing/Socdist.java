package socialDistancing;
import java.io.*;
import java.util.*;

public class Socdist {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		int[] nums = new int[m*2];
		
		for(int i = 0; i<m*2;i+=2) {
			st = new StringTokenizer(br.readLine());
			nums[i] = Integer.valueOf(st.nextToken());
			nums[i+1] = Integer.valueOf(st.nextToken());
		}
		Arrays.sort(nums);
		br.close();
		
		//0 2 4 7 9 9
		long lo = 1;
		long hi = nums[m*2-1];
		while(lo+1<hi) {
			long mid = (lo+hi)/2;
			//System.out.println(mid);
			
			long counter = 1;
			long prev = nums[0];
			
			for(int i = 1; i<n && prev<=nums[m*2-1]; i++) {
				prev+=mid;
				long lo1 = 0;
				long hi1 = m*2-1;
				while(lo1+1<hi1) {
					long mid1 = (lo1+hi1)/2;
					if(nums[(int)mid1]<=prev) lo1 = mid1;
					else hi1 = mid1; 
				}
				
				if(prev>=nums[(int)lo1] && prev<=nums[(int)hi1]) {
					counter++;
					if(prev>nums[(int)lo1] && prev<nums[(int)hi1] && lo1%2==1) prev = nums[(int)hi1];
				}
				//System.out.println("prev: " + prev + "\t lo1: " + nums[(int)lo1] + "\t hi1: " + nums[(int)hi1]);
			}
			
			if(counter==n) lo = mid;
			else hi = mid;
			
			//System.out.println("lo: "+ lo + " hi: " + hi);
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
		pw.write(String.valueOf(lo));
		//System.out.println(hi + " " + lo);
		
		pw.close();
	}
}
