package citiesAndStates;
import java.io.*;
import java.util.*;

public class CityState {
	public static void main(String[] args) throws IOException {
	    // File Input Example 1: 
	    // Reading in one piece of data per line

	    // Note: In USACO, the first line will always tell you the number of subsequent lines you need to read in

	    // 1. open the file
	    BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    // 2. read in the first line and convert to int
	    int N = Integer.parseInt(st.nextToken());
	    
	    HashMap<String, Integer> citiesStates = new HashMap<String, Integer>();

	    /*prices.put("ice cream", 5);
	    prices.put("brownie", 2);
	    prices.put("cake", 10); 

	    System.out.println(prices);

	    // decrease the price of the brownie
	    prices.put("brownie", prices.get("brownie")-1);

	    System.out.println(prices.get("brownie"));

	    System.out.println(prices.containsKey("ice cream"));
	    System.out.println(prices.containsValue(10));
	    System.out.println(prices.size());

	    for (String key : prices.keySet()) {
	      System.out.println(key);
	    }*/

	    // 3. create array of size N to store data
	    //String[][] citiesStates = new String[N][2];
	    

	    // 4. read in the remaining lines
	    String code;
	    for (int i = 0; i < N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	code = st.nextToken().substring(0,2)+st.nextToken();
//	    	System.out.println(code.substring(2,4)+code.substring(0,2));
	    	if(!(code.substring(2,4)+code.substring(0,2)).equals(code)){
	    		if(!citiesStates.containsKey(code)) {
		    		citiesStates.put(code,0);
		    	}
		    		citiesStates.put(code,citiesStates.get(code)+1);
	    	
	    		
	    	}
	    		
	    }

	    int numPairs = 0;
	    String compareString = "";
	    for(String key: citiesStates.keySet()) {
	    	System.out.println(key);
	    	System.out.println(citiesStates.get(key));
	    	compareString = key.substring(2,4)+key.substring(0,2);
	    	if(citiesStates.containsKey(compareString)) {
	    	

	    		
	    		numPairs+=citiesStates.get(compareString)*citiesStates.get(key);
	    	}
	    	
	    }
	    

	    // 5. close file
	    br.close();


	    // 1. open the file
	    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
	    
	    // 2. write to file
	    pw.write(String.valueOf(numPairs/2));

	    // 3. close file
	    pw.close();
	  }
}
