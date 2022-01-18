package pairedUp;
import java.util.*;
import java.io.*;

public class Pairup {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
		StringTokenizer st; 
		
		int n = Integer.valueOf(br.readLine());
		int m = 0;
		HashMap<Integer,Integer> hm  = new HashMap<>();
		ArrayList<Integer> nums =  new ArrayList<Integer>();
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			m+=x;
			hm.put(y,x);
			nums.add(y);
		}
		Collections.sort(nums);
		br.close();
		
		int prevMin = nums.get(0);
		int minCounter = 0;
		int prevMax = nums.get(nums.size()-1);
		int maxCounter = 1;
		int totalMax = Math.max(0,prevMax+prevMin);
		for(;minCounter<nums.size()-maxCounter;) {
			int hm1 = hm.get(prevMin);
			int hm2 = hm.get(prevMax);
			int minHm = Math.min(hm1, hm2);
			
			hm.put(prevMin, hm.get(prevMin)-minHm);
			hm.put(prevMax, hm.get(prevMax)-minHm);
			
			if(hm.get(prevMin)>0) {
				if(hm.get(prevMax)==0) {
					maxCounter++;
					prevMax = nums.get(nums.size()-maxCounter);
				}
			} else {
				minCounter++;
				prevMin = nums.get(minCounter);
				if(hm.get(prevMax)==0) {
					maxCounter++;
					prevMax = nums.get(nums.size()-maxCounter);
				}
			}
			//System.out.println(prevMin + " " + prevMax);
			totalMax = Math.max(totalMax, prevMin+prevMax);
		}
		
		
		//System.out.println(totalMax);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		pw.write(String.valueOf(totalMax));
		pw.close();
	}

}
