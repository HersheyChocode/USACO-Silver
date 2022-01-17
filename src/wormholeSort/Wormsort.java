package wormholeSort;
import java.io.*;
import java.util.*;
public class Wormsort {
	static int[] components;
	static boolean[] discovered;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		ArrayList<int[]>[] adjacencyList = new ArrayList[n];
		int[] widths = new int[m];
		components = new int[n];
		discovered = new boolean[n];
		
		ArrayList<Integer> misplaced = new ArrayList<>();
		
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(st.nextToken())-1;
			if(nums[i]!=i) misplaced.add(i);
		}
		
		
		for(int i = 0; i<n; i++) {
			adjacencyList[i] = new ArrayList<>();
		}
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken())-1;
			int b = Integer.valueOf(st.nextToken())-1;
			int w = Integer.valueOf(st.nextToken());
			
			adjacencyList[a].add(new int[] {b,w});
			adjacencyList[b].add(new int[] {a,w});
			widths[i] = w;
		}
		
		
		Arrays.sort(widths);
		
		int lo = 0;
		int hi = m;
		while(lo+1<hi&&misplaced.size()>0) {
			components = new int[n];
			discovered = new boolean[n];
			
			int mid = (lo+hi)/2;
			//System.out.println(widths[mid]);
			int compCount = 0;
			for(int i = 0; i<n; i++) {
				if(!discovered[i]) {
					DFS(widths[mid], adjacencyList, i, compCount, Integer.MAX_VALUE); 
					compCount++;
				}
			}
			//System.out.println(Arrays.toString(components));
			
			int check = 0;
			int counter = 0; 
			for(int i = 0; i<misplaced.size();i++) {
				if(i==0) {
					check = components[misplaced.get(i)];
					counter++;
				} else {
					if(components[misplaced.get(i)]==check) counter++;
				}
			}
			
			if(counter==misplaced.size()) lo = mid;
			else hi = mid;
		}
		if(misplaced.size()>0) pw.println(widths[lo]);
		else pw.println(-1);
		pw.close();
		br.close();
	}
	
	public static void DFS(int weight, ArrayList<int[]>[] adjacencyList, int x, int comp, int w) {
		if(!discovered[x] && w>=weight) {
			discovered[x] = true;
			components[x] = comp; 
			for(int i = 0; i<adjacencyList[x].size(); i++) {
				DFS(weight, adjacencyList, adjacencyList[x].get(i)[0], comp, adjacencyList[x].get(i)[1]);
			}
		}
	}
}