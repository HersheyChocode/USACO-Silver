import java.util.*;
import java.io.*;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

    int t = Integer.valueOf(st.nextToken());
    for(int count = 0; count<t; count++) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.valueOf(st.nextToken());
      int m = Integer.valueOf(st.nextToken());
      int[] friends = new int[n];
      int[] gifts = new int[m];
      st = new StringTokenizer(br.readLine());
      for(int i = 0; i<n; i++) {
        friends[i] = Integer.valueOf(st.nextToken());
      }
      st = new StringTokenizer(br.readLine());
      for(int i = 0; i<m; i++) {
        gifts[i] = Integer.valueOf(st.nextToken());
      }
      Friend[] maxFriends = new Friend[n];
      for(int i = 0; i<n; i++) {
        maxFriends[i] = new Friend(gifts[friends[i]-1], i);
      }
      Arrays.sort(maxFriends); //decreasing order (max-min)
     // System.out.println(Arrays.toString(maxFriends)); 
      //System.out.println(Arrays.toString(maxFriends));
      
      long price = 0;
      for(int i = 0; i<n; i++) {
        Friend x = maxFriends[i];
        int index = friends[x.index]-1;
        
        int potential;
        if(i<m) potential = gifts[i];
        else potential = Integer.MAX_VALUE;
        if(gifts[index]<potential) potential = gifts[index];
        price+=potential; 
      }

      System.out.println(price);
    }

  }
}

class Friend implements Comparable<Friend> {
  public int maxVal;
  public int index;
  
  public Friend(int val, int ind) {
    maxVal = val;
    index = ind;
  }

  public int compareTo(Friend other) {
    if(this.maxVal==other.maxVal) return this.index-other.index;
    else return -this.maxVal+other.maxVal;
  }

  public String toString() {
    return "(" + maxVal + " " + index + ") ";
  }

}