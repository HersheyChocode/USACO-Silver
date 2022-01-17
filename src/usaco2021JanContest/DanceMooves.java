package usaco2021JanContest;

import java.io.*;
import java.util.*;

public class DanceMooves {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		TreeSet<Integer>[] loops = new TreeSet[n];
		int[] nums = new int[n];
		for(int i = 0; i<n; i++) {
			loops[i] = new TreeSet<>();
			nums[i] = i;
		}
		int[] endsUp = new int[n];
		for(int i = 0; i<n; i++) {
			endsUp[i] = i;
			loops[i].add(i);
		}
		for(int i = 0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken())-1;
			int y = Integer.valueOf(st.nextToken())-1;
			
			endsUp[nums[x]] = y;
			endsUp[nums[y]] = x;
			
			loops[nums[x]].add(y);
			loops[nums[y]].add(x);
			
			int temp = nums[x];
			nums[x] = nums[y];
			nums[y] = temp;
		}
		
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int[] cycles = new int[n];
		Arrays.fill(cycles, -1);
		for(int i = 0; i<n; i++) {
			if(cycles[i]==-1) {
				int counter1 = nums[i];
				TreeSet<Integer> x = new TreeSet<>();
				x.add(i);
				ArrayList<Integer> visited = new ArrayList<>();
				while(counter1!=nums[i] || visited.size()==0) {
					for(int j: loops[counter1]) {
						x.add(j);
					}
					visited.add(counter1);
					counter1 = endsUp[counter1];
				}
				for(int j = 0; j<visited.size(); j++) {
					cycles[visited.get(j)] = x.size();
				}
				sb.append(x.size());
				sb.append("\n");
				//pw.println(x.size());
			}
			else {
				//pw.println(cycles[i]);
				sb.append(cycles[i]);
				sb.append("\n");
			}
		}
		pw.print(sb.toString());
		pw.close();
		
	}

}
