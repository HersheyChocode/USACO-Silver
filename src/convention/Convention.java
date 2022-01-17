package convention;
import java.io.*;
import java.util.*;

public class Convention {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader ("convention.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int c = Integer.valueOf(st.nextToken());
		int[] nums = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}
		Arrays.sort(nums);
		int largestDist = nums[n-1]-nums[0];
		
		int lo = 0;
		int hi = nums[n-1];
		while(lo+1<hi) {
			int mid = (lo+hi)/2;
			if (can(mid, nums, n, m, c)) {
				//System.out.println(mid + " can");
				hi = mid;
			} else {
				//System.out.println(mid + "cant");
				lo = mid; 
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		pw.write(String.valueOf(hi));
		pw.close();
		System.out.println(hi);
		
	}
	
	public static boolean can (int k, int[] nums, int n, int m, int c) {
		int numM = 1;
		int currC = 0;
		int numN = 0;
		int minT = nums[0];
		boolean possible = true;
		while(currC<n) {
			if(nums[currC]-minT<=k && numN+1<=c) {
				//System.out.println(nums[currC] + "add" + minT);
				currC++;
				numN++;
			}
			else {
				//System.out.println(nums[currC]);
				if (currC<n) minT = nums[currC];
				currC++;
				numM++;
				numN=1;
			}
			if(numM>m) {
				possible = false;
				break;
			}
		}
		return possible;
	}

}
