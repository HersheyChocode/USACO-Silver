package myCowAteMyHomework;

import java.io.*;
import java.util.*;

public class Homework {
	
	public static void main(String[] args) throws IOException {
    // File Input Example 1:
    // Reading in one piece of data per line

    // Note: In USACO, the first line will always tell you the number of subsequent lines you need to read in

    // 1. open the file
    BufferedReader br = new BufferedReader(new FileReader("homework.in"));

    // 2. read in the first line and convert to int
    int N = Integer.parseInt(br.readLine());

    // 3. create array of size N to store data
    int[] nums = new int[N];
    
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 4. read in the remaining lines
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    // 5. close file
    br.close();
    
    
    int[] suffixSum = new int[N];
    
    int totalSum = 0;
    for(int i = N-1; i>=0; i--) {
    	totalSum+=nums[i];
    	suffixSum[i]=totalSum;
    }
    
    
    
    //3 1 9 2 7
    System.out.println(Arrays.toString(suffixSum));
    
    
    int min = Math.min(nums[N-1], nums[N-2]);
    double[] averages = new double[N-2];
    int counter = 1;
    
    for(int k=N-2; k>=1; k--) {
    	if(nums[k]<min) {
    		min = nums[k];
    	}
    	
    	averages[k-1]=((double)suffixSum[k]-min)/counter;
    	counter+=1;
    }
    System.out.println(Arrays.toString(averages));
    
    int newMin = 0;
    for(int i =0;i<averages.length; i++) {
    	if(averages[i]>averages[newMin]) {
    		newMin=i;
    	}
    }
    

    // File Output Example:

    // 1. open the file
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));    
    
    
    for(int i =0;i<averages.length; i++) {
    	if(averages[i]==averages[newMin]) {
    		System.out.println(i+1);
    		pw.write(String.valueOf(i+1));
    		pw.write("\n");
    	}
    }
    newMin+=1;
    
    





    // 3. close file
    pw.close();
  }
}
