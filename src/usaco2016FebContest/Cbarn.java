package usaco2016FebContest;

import java.io.*;
import java.util.*;

public class Cbarn {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
		
		int n = Integer.valueOf(br.readLine());
		int[] cowDoors = new int[n];
		HashMap<Integer,ArrayList<Cow>> locCows = new HashMap<>();
		int[] grid = new int[n];
		int numN = 0;
		
		int counter = 0;
		for (int i = 0; i<n; i++) {
			locCows.put(i, new ArrayList<Cow>());
			int curr = Integer.valueOf(br.readLine());
			grid[i] = curr;
			if(curr==1) numN++;
			for(int j = 0; j<curr; j++) {
				locCows.get(i).add(new Cow(i, counter));
				counter++;
			}
		}
		
		counter = 0;
		while(numN!=n) {
			if(grid[counter]>1) {
				for(int i = 0; i<grid[counter]-1; i++) {
					Cow x = locCows.get(counter).get(0);
					if(x.loc+1==n) {
						x.loc = 0;
						locCows.get(counter).remove(0);
						locCows.get(0).add(x);
					} else {
						x.loc+=1;
						locCows.get(counter).remove(0);
						locCows.get(counter+1).add(x);
					}
					cowDoors[x.id]+=1;
				}
				if(counter+1==n) {
					
					if(grid[0]!=1) {
						if(grid[0]==0) {
							if(grid[n-1]==2) numN+=2;
							else numN++;
						} else numN++;
					}
					
					grid[0]+=grid[counter]-1;
				}
				else {
					if(grid[counter+1]!=1) {
						if(grid[counter+1]==0) {
							if(grid[counter]==2) numN+=2;
							else numN++;
						} else numN++;
					}
					
					grid[counter+1]+=grid[counter]-1;
				}
				grid[counter] = 1;
			}
			if(counter+1==n) counter = -1;
			counter++;
		}
		
		int sum = 0;
		for(int i = 0; i<n; i++) {
			sum+=cowDoors[i]*cowDoors[i];
		}
		System.out.println(sum);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		pw.println(sum);
		
		br.close();
		pw.close();
	}
}

class Cow {
	public int loc;
	public int id;
	public Cow(int loc, int id) {
		this.loc = loc;
		this.id = id;
	}
	
	public String toString() {
		return(loc + " " +id);
	}
}