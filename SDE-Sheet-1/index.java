import java.util.*;
import java.lang.*;
import java.io.*;


class Main
{

// Maximum subarray sum in array formed by repeating the given array k times

/*
   Approach - 1 ::: Finding the max subarray sum using the modular over iterating the array of size n*k.   
*/
    static int maxSubArraySumRepeated(int a[], int n, int k)
    {
        int max_so_far = 0;
        int INT_MIN, max_ending_here=0;
 
        for (int i = 0; i < n*k; i++)
        {
            // This is where it differs from Kadane's algorithm. We use modular
            //  arithmetic to find next element.
            max_ending_here = max_ending_here + a[i % n];
 
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
 
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }

/*
   Approach - 2 ::: finding the max subarray sum of 2*n size araay, then check if the totalSum of the array
   is less than zero then return max subarray sum otherwise return max subarray sum + (k-2)*totalsum;
*/

    public static long kadaneAlgs(int[] ar, int n){
        long maxSoFar = Integer.MIN_VALUE;
		long maxEndingHere = 0;
		for(int i=0; i<(2*n); i++){
		    int idx = i%n;
		    maxEndingHere += ar[idx];
		    if(maxSoFar < maxEndingHere){
		       maxSoFar = maxEndingHere;
		    }
		    if(maxEndingHere < 0){
		         maxEndingHere = 0;
		    }
		 }
		 return maxSoFar;
    }
    public static long calculateMaxSum(int[] ar, int n, int k){
        long ans = 0;
        long maxSubArraySum = kadaneAlgs(ar, n);
        long totalSum = 0;
        for(int val : ar){
            totalSum += val;
        }
        if(totalSum < 0){
            return maxSubArraySum;
        }else{
            return (maxSubArraySum + (k-2)*totalSum);
        }
    }
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while(t-- > 0){
		    String str = scn.nextLine();
		    int[] temp = new int[2];
		    for(int i=0; i<2; i++){
		        temp[i] = scn.nextInt();
		    }
		    int n = temp[0];
		    int k = temp[1];
		    int[] ar = new int[n];
		    for(int i=0; i<n; i++){
		        ar[i] = scn.nextInt();
		    }
		    
		    long ans = calculateMaxSum(ar, n, k);
		    System.out.println(ans);
		}
	}
}


// Maximum Contiguous Circular Subarray Sum

// Approach 1 : Naive approach -> use nested loop to find the maximum sub array sum of each n elements 
// Approach 2 : Create a new array of size 2*n and insert the elements accordingly then use 2 pointer approach 
// to find the maximum sub array sum of n elements each time.

// Approach 3 : 

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int sum = 0, minSoFar = Integer.MAX_VALUE, maxSoFar = Integer.MIN_VALUE, n = nums.length;
        int minEndingHere = 0, maxEndingHere = 0;

        for(int i=0; i<n; i++){
            sum += nums[i];
            maxEndingHere += nums[i];
            minEndingHere += nums[i];
            if(maxSoFar < maxEndingHere){
                maxSoFar = maxEndingHere;
            }
            if(minSoFar > minEndingHere){
                minSoFar = minEndingHere;
            }
            if(maxEndingHere < 0){
                maxEndingHere = 0;
            }
            if(minEndingHere > 0){
                minEndingHere = 0;
            }
        }  
        return sum == minSoFar ? maxSoFar : Math.max(maxSoFar, sum - minSoFar);
    }
}

// Subarray Sum Equals K 

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int csum = 0, ans = 0;
        hm.put(0,1);
        for(int val : nums){
            csum += val;
            ans += hm.getOrDefault(csum-k, 0);
            hm.put(csum, hm.getOrDefault(csum, 0) + 1);
        } 
        return ans;
    }
}

// Equilibrium Index Of An Array

public static int equilibriumPoint(long arr[], int n) {
        long sum = 0;
        long[] leftSum = new long[n];
        for(int i=0; i<n; i++){
            if(i == 0){
                leftSum[i] = arr[i];
            }
            else{
                leftSum[i] = leftSum[i-1] + arr[i];
            }
            sum += arr[i];
        }
        
        for(int i=0; i<n; i++){
            long left = leftSum[i] - arr[i];
            long right = sum - leftSum[i];
            if(right == left){
                return i+1;
            }
        }
        return -1;
    }

    // 