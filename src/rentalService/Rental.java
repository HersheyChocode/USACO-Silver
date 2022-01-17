package rentalService;

import java.io.*;
import java.util.*;

public class Rental {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("rental.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int r = Integer.valueOf(st.nextToken());
		
		int[] cows = new int[n];
		long[] prefixCows = new long[n];
		
		for(int i = 0; i<n; i++) {
			cows[i] = -Integer.valueOf(br.readLine());
		}
		Arrays.sort(cows);
		for(int i = 0; i<n; i++) {
			cows[i] = -cows[i];
			if(i==0) prefixCows[i] = cows[i];
			else prefixCows[i] = prefixCows[i-1]+cows[i];
		}
		Store[] stores = new Store[m];
		long[] maxPrice = new long[m];
		long[] maxGallons = new long[m];
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			stores[i] = new Store(Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()));
		}
		Arrays.sort(stores);
		for(int i = 0; i<m; i++) {
			if(i==0) {
				maxGallons[i] = stores[i].max;
				maxPrice[i] = stores[i].max*stores[i].price;
			}
			else {
				maxGallons[i] = stores[i].max + maxGallons[i-1];
				maxPrice[i] = maxPrice[i-1] + stores[i].max*stores[i].price;
			}
		}
		int[] rents = new int[r];
		long[] prefixRents = new long[r];
		for(int i = 0; i<r; i++) {
			rents[i] = -Integer.valueOf(br.readLine());
		}
		Arrays.sort(rents);
		for(int i = 0; i<r; i++) {
			rents[i] = -rents[i];
			if(i==0) prefixRents[i] = rents[i];
			else prefixRents[i] = prefixRents[i-1]+rents[i];
		}
		
		long max = -Long.MAX_VALUE;
		for(int i = 0; i<n; i++) {
			long numG = prefixCows[i];
			long sum = 0;
			
			int lo = 0; 
			int hi = m-1;
			while(lo+1<hi) {
				int mid= (lo+hi)/2;
				if(numG<maxGallons[mid]) hi = mid;
				else lo = mid;
			}
			if(numG<maxGallons[0]) hi = 0;
			if(hi<m) {
				if(hi==0) {
					sum+=numG*stores[0].price;
				} else {
					if(numG>=maxGallons[m-1]) {
						sum+=maxPrice[m-1];
					}
					else {
						sum+=maxPrice[hi-1];
						sum+=stores[hi].price*(numG-maxGallons[hi-1]);
					}
				}
			}
			
			//System.out.print(sum + " ");
			int left = n-i-1;
			if(left-1>=r) {
				sum+=prefixRents[r-1];
			}
			else if(left-1>=0) {
				sum+=prefixRents[left-1];
			}
			//System.out.println(sum);
			if(sum>max) max = sum;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		pw.println(max);
		pw.close();
		br.close();
		System.out.println(max);
	}

}

class Store implements Comparable<Store> {
	public int max;
	public int price;
	public Store(int max, int price) {
		this.max = max;
		this.price = price;
	}
	public int compareTo(Store o) {
		if(o.price==this.price) return o.max-this.max;
		return o.price-this.price;
	}
	public String toString() {
		return max + ":" + price;
	}
}