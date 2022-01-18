package cowDanceShow;
import java.util.*;
import java.io.*;
import java.util.*;

public class CowDance {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		int max = Integer.valueOf(st.nextToken());
		int[] nums = new int[n];

		
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.valueOf(br.readLine());
		}
		br.close();

		boolean canWork = false;
		int k = 1;
		int t = 0;
		int counter = 0;
		//int[] times = {nums[0]};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(nums[0]);
		while(!canWork) {
			if(k==n) { break; }
			t = pq.poll();
			pq.add(nums[counter]+t);
			
			if(counter==n-1) {
				while(pq.size()>0) {
					t=pq.poll();
				}
				if(t<=max) {
					break;
				} else {
					t = 0;
					k++;
					pq = new PriorityQueue<Integer>();
					for(int i = 0; i<k; i++) {
						pq.add(nums[i]);
					}
					counter = k;
				}
			} else {
				counter++;
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		pw.write(String.valueOf(k));
		pw.close();
		System.out.println(k);

	}
}
