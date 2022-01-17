package maxCross;

import java.io.*;
import java.util.*;

public class MaxCross {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));

	    // 2. read in the first line and convert to int
	    //int N = Integer.parseInt(br.readLine());
	    
	 // 2. create a StringTokenizer object to read in the first line of data
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    // 3. get the two numbers from the first line
	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(st.nextToken());
	    int B = Integer.parseInt(st.nextToken());



	    // 3. create array of size N to store data
	    int[] broken = new int[B];

	    // 4. read in the remaining lines
	    for (int i = 0; i < B; i++) {
	      broken[i] = Integer.parseInt(br.readLine());
	    }
	    Arrays.sort(broken);
	    System.out.println(Arrays.toString(broken));
	    
	    //1 2 5 9 10
	    //1 2 2 2 3 3 3 3 4 5

	    // 5. close file
	    br.close();
	    
	    int[] signals = new int[N];
	    
	    int counter = 0;
	    for(int i = 1; i<N+1; i++) {
	      if(counter<B){
	        if(i==broken[counter]){
	          counter+=1;
	        }
	      }
	      signals[i-1] = counter;
	    }
	    
	    System.out.println(Arrays.toString(signals));
	    
	    int[] numBroken = new int[N-K+1];
	    for(int i = 0; i<=N-K; i++) {
	    	numBroken[i]= signals[i+K-1]-signals[i];
	    }
	    
	    Arrays.sort(numBroken);


	    // File Output Example:

	    // 1. open the file
	    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
	    
	    // 2. write to file
	    pw.write(String.valueOf(numBroken[0]));

	    // 3. close file
	    pw.close();
	  }
}
