package milkMeasurement;

import java.io.*;
import java.util.*;

public class Measurement {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		Cow[] cows = new Cow[n];
		ArrayList<Integer> outputs = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = new Cow(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()),
					Integer.valueOf(st.nextToken()));
		}
		Arrays.sort(cows);
		//System.out.println(Arrays.toString(cows));

		HashMap<Integer, Integer> cowNums = new HashMap<>();
		TreeMap<Integer, Integer> output = new TreeMap<>();
		output.put(0, n+1);
		//int max = Integer.MIN_VALUE;

		int counter = 0;

		for (int i = 0; i < n; i++) {
			int newVal;
			int original;
			//boolean istrue = false;
			if (cowNums.containsKey(cows[i].num)) {
				original = cowNums.get(cows[i].num);
				newVal = cowNums.get(cows[i].num) + cows[i].change;
				cowNums.put(cows[i].num, newVal);
			} else {
				original = 0;
				newVal = cows[i].change;
				cowNums.put(cows[i].num, cows[i].change);
				//istrue = true;
			}
			int wasCount = 0;
			boolean wasLast = false;
			boolean isLast = false;
			if (output.containsKey(newVal)) {
				wasCount = output.get(output.lastKey());
				if(output.lastKey()==original) wasLast = true;
				
				output.put(newVal, output.get(newVal) + 1);
				output.put(original, output.get(original)-1);
				
				if(output.get(original)==0) output.remove(original);
				if(output.lastKey()==newVal) isLast = true;
			} else {
				int lastKey = Integer.MAX_VALUE;
				if(output.size()>0) {
					if(output.lastKey()==original) wasLast = true;
					lastKey = output.lastKey();
				} 
				wasCount = output.get(output.lastKey());
				output.put(newVal, 1);
				output.put(original, output.get(original)-1);
				if(output.get(original)==0) output.remove(original);
				if(output.lastKey()==newVal) isLast = true;
			}
			if(wasLast && !isLast) counter++;
			else if(!wasLast&&isLast) counter++;
			else if(wasLast && isLast) {
				if(wasCount==1) {
					if(output.get(output.lastKey())!=1) counter++;
				} else {
					counter++;
				}
			}
			
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		pw.write(String.valueOf(counter));
		pw.close();
		System.out.println(counter);
	}

}

class Cow implements Comparable<Cow> {
	public int num;
	public int change;
	public int time;

	public Cow(int time, int num, int change) {
		this.change = change;
		this.num = num;
		this.time = time;
	}

	@Override
	public int compareTo(Cow other) {
		if (this.time != other.time) {
			return this.time - other.time;
		}
		return this.num - other.num;
	}

	@Override
	public String toString() {
		return this.time + " " + this.num + " " + this.change;
	}
}
