package moocast;
import java.io.*;
import java.util.*;

public class moocast {
	public static void main(String[] args) throws IOException {
	    // File Input Example 1: 
	    // Reading in one piece of data per line

	    // Note: In USACO, the first line will always tell you the number of subsequent lines you need to read in


	    // File Input Example 2:
	    // Reading in multiple pieces of data per line

	    // Note: In this case, the first line will tell you the number of lines and the number of numbers in each line

	    // 1. open the file
	    BufferedReader br = new BufferedReader(new FileReader("moocast.in"));

	    // 2. create a StringTokenizer object to read in the first line of data
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    // 3. get the two numbers from the first line
	    int n = Integer.parseInt(st.nextToken());

	    // 3. create array of size NxM to store data
	    int[][] nums = new int[n][3];

	    // 4. read in the remaining lines. note that you only want to update the StringTokenizer once per line of data
	    for (int i = 0; i < n; i++) {
	      st = new StringTokenizer(br.readLine());
	      for (int j = 0; j < 3; j++) {
	        nums[i][j] = Integer.parseInt(st.nextToken());
	      }
	    }
	    
	    int x2;
	    int x1;
	    int y2;
	    int y1;
	    int distance;
	    ArrayList<Integer>[] adjacencyList = new ArrayList[n];
	    
	    for (int i = 0; i<n;i++) {
	    	adjacencyList[i] = new ArrayList<Integer>();
	    	for (int j=0;j<n;j++) {
	    		x1 = nums[i][0];
	    		y1 = nums[i][1];
	    		x2 = nums[j][0];
	    		y2 = nums[j][1];
	    		distance = ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2));
	    		if(distance<=nums[i][2]*nums[i][2]) {
	    			adjacencyList[i].add(j);
	    		}
	    	}
	    }
	    
	    Boolean[]checked = new Boolean[n];
	    for (int i = 0; i < n; i++) { 
	    	checked[i]=false;
            for(int j = 0;j<(adjacencyList[i]).size(); j++) {
            	System.out.print(adjacencyList[i].get(j));
            }
            System.out.println();
        } 
	    
	    int[] eachBroadcasts = new int[n];
	    
	    for(int i = 0; i<n; i++) {
	    	eachBroadcasts[i]=dfs(adjacencyList,i,checked,0)+1;
	    	Arrays.fill(checked, false);
	    	System.out.println(eachBroadcasts[i]);
	    }
//	    System.out.println(dfs(adjacencyList,0,checked,0));
        
	    // 5. close file
	    br.close();
	    // File Output Example:
	    
	    
	   int max=-1;
	   for(int i = 0;i<n;i++) {
		   if(eachBroadcasts[i]>max) {
			   max = eachBroadcasts[i];
		   }
			   
	   }
	    System.out.println(max);

	    // 1. open the file
	    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
	    
	    // 2. write to file
	    pw.write(Integer.toString(max));

	    // 3. close file
	    pw.close();
	  }
	
	public static int dfs(ArrayList<Integer>[]g, int v, Boolean[]checked, int numBroadcasts) {
		checked[v]= true;
		for(int i = 0; i< g[v].size(); i++) {
			//System.out.println(g[v].get(i));
			if(checked[g[v].get(i)]==false){
				/*System.out.print("g[v].get(i)aka the vector connecting the edge");
				System.out.println(g[v].get(i));
				System.out.print("numBroadcasts");
				System.out.println(numBroadcasts);
				System.out.print("v");
				System.out.println(v);*/
				numBroadcasts+=1;
				numBroadcasts += dfs(g,g[v].get(i),checked,0);
			}
		}
		return numBroadcasts;
	}
	
}
