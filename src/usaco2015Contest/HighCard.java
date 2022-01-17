package usaco2015Contest;
import java.io.*;
import java.util.*;
public class HighCard {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
		int n = Integer.valueOf(br.readLine());
		
		int[] plays = new int[n];
		boolean[] taken = new boolean[n*2];
		
		TreeSet<Integer> list = new TreeSet<Integer>();
		
		for(int i = 0; i<n; i++) {
			plays[i] = Integer.valueOf(br.readLine())-1;
			taken[plays[i]] = true;
		}
		for(int i = 0; i<n*2; i++) {
			if(!taken[i]) {
				list.add(i);
			}
		}
		
		int counter = 0;
		for(int i = 0; i<n; i++) {
			int x = plays[i];
			Integer y = list.higher(x);
			if(y instanceof Integer) {
				counter++;
				list.remove(y);
			}
		}
		System.out.println(counter);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		pw.write(String.valueOf(counter));
		pw.close();
		br.close();
	}

}
