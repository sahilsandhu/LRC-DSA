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

    // Maximum Sum Increasing Subsequence

    class Solution
{
    public int maxSumLIS(int[] arr ,int si, int[] dp, int n){
        int ans = 0;
        for(int i=0; i<n; i++){
            dp[i] = arr[i];
            for(int j=i-1; j>=0; j--){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
	public int maxSumIS(int arr[], int n)  
	{  
	    int[] dp = new int[n];
	    return maxSumLIS(arr, 0, dp, n);
	}  
}

// Convert Array to Zig - Zag Fashion


// Approach - 1
public void zigZag(int a[], int n){
        Arrays.sort(a);
        for(int i=2; i<n; i+=2){
            int temp = a[i-1];
            a[i-1] = a[i];
            a[i] = temp; 
        }
    }

//  Approach - 2
public void zigZag(int a[], int n){
        boolean flag = true;
        for(int i=1; i<n; i++){
            if(flag == true){
                if(a[i-1] > a[i]){
                  int temp = a[i];
                  a[i] = a[i-1];
                  a[i-1] = temp;
                }
            }
            else{
                if(a[i-1] < a[i]){
                  int temp = a[i];
                  a[i] = a[i-1];
                  a[i-1] = temp;
                }
            }
            flag = !flag;
        }
    }

    // Find a pair with a Given Difference

    // Approach 1 : use nested loops 
    // Approach 2 : Sort the array and use the binary search to find the value into sorted array.
    // Approach 3 : sort the array and use the 2 pointers approach to solve this problem.
    // Approach 4 : Use hashmap

class Solution
{
    public boolean findPair(int arr[], int size, int n)
    {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i=0; i<size; i++){
            hm.put(arr[i], hm.getOrDefault(arr[i], 0) + 1);
            if(n == 0 && hm.get(arr[i]) > 1){
                return true;
            }
        }
        if(n == 0){
            return false;
        }
        for(int i=0; i<size; i++){
            int num = arr[i] + n; 
            if(hm.containsKey(num) == true){
                return true;
            }
        }
        return false;
    }
}

 
// Chocolate Distribution  ::: GFG
int findMinDiff(int[] arr, int n, int m){
    if(m == 0 || n == 0){
        return -1;
    }
    Arrays.sort(arr);
    int ans = Integer.MAX_VALUE;
    for(int i=0; (i+m-1)<n; i++){
        int diff = arr[i+m-1] + arr[i];
        ans = Math.min(diff, ans);
    }
    return ans;
}

// Minimum Number of Platforms Required for a Railway/Bus Station

/*
First Approach ::::: 
Here we use 2 loops to iterate over the array. for each outer loop value we check the 
intersected time interval except itself.
*/

int findPlatform(int[] arr, int[] dep, int n){
    int platforms = 1, result = 1;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            if(i != j){
                if((arr[i] >= arr[j] && arr[i] <= dep[j]) || (arr[j] >= arr[i] && arr[j] <= dep[i])){
                    platforms++;
                }
            }
        }
        result = Math.max(result, platforms);
    }
    return result;
}


/*
Second Approach ::::::
Now we used the Sort approach. Sort the array and uses the MIN HEAP
*/
public class pair implements Comparator<pair>{
    int arr;
    int dept;
    pair(int arr, int dept){
        this.arr = arr;
        this.dept = dept;
    }

    public int compareTo(pair o){
        return this.arr - o.arr;
    }
}

int findPlatform(int[] arr, int[] dep, int n){
    pair[] array = new pair[n];
    for(int i=0; i<n; i++){
        array[i] = new pair(arr[i], dep[i]);
    }
    Arrays.sort(array);
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int result = 1;
    pq.add(array[0].dep);
    for(int i=1; i<n; i++){
        if(array[i].arr >= pq.peek()){
            pq.poll();
        }else{
            result++;
        }
        pq.add(array[i].dep);
    }
    return result;
}


/*
Third Approach ::::::
Now we used the Sort approach. Sort the array and inc counter for every train arrival
and decrement counter for each train departure.
*/

int findPlatform(int[] arr, int[] dep, int n){
    Arrays.sort(arr);
    Arrays.sort(dep);
    int i = 1, j = 0;
    int counter = 1, ans = 1;
    while(i<n && j<n){
        if(arr[i] <= dep[j]){
            counter++;
            i++;
        }else if(dep[j] < arr[i]){
            counter++;
            j++;
        }
        ans = Math.max(ans, counter);
    }
    return ans;
}

/*
Trapping Rain Water
Apprach  ::::: 1
*/

class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        // Calculating Prefix Max
        prefixMax[0] = height[0];
        for(int i=1; i<n; i++){
            prefixMax[i] = Math.max(prefixMax[i-1], height[i]);
        }
        // Calculating Suffix Max
        suffixMax[n-1] = height[n-1];
        for(int i=n-2; i>=0; i--){
            suffixMax[i] = Math.max(suffixMax[i+1], height[i]);
        }
        
        int ans = 0;
        for(int i=0; i<n; i++){
            ans += (Math.min(prefixMax[i], suffixMax[i]) - height[i]);
        }
        
        return ans;
    }
}

/*
Trapping Rain Water
Appraoch ::::: 2 
*/

class Solution {
    public int trap(int[] ar) {
        int n = ar.length;
        int l = 0, r = n-1;
        int leftMax = 0, rightMax = 0, water = 0;
        while(l <= r){
            if(ar[l] <= ar[r]){
                if(ar[l] >= leftMax){
                    leftMax = ar[l];
                }
                else{
                    water += (leftMax - ar[l]);
                }
                l++;
            }
            else{
                if(ar[r] > rightMax){
                    rightMax = ar[r];
                }
                else{
                    water += (rightMax - ar[r]);
                }
                r--;
            }
        }
        return water;
    }
}

/*
Best Time to Buy and Sell Stock I
*/
class Solution {
    public int maxProfit(int[] prices) {
        int maxPro = 0;
        int minval = prices[0];
        for(int i=1;i<prices.length;i++)
        {
            maxPro = Math.max(maxPro, prices[i]- minval);
            minval = Math.min(minval,prices[i]);
        }
        return maxPro;
    }
}


/*
 Best Time to Buy and Sell Stock II
*/
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int buy = prices[0], sell = prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i] >= sell){
                sell = prices[i]; 
            }
            else{
                ans += (sell-buy);
                buy = prices[i];
                sell = prices[i];
            }
            
        }
        ans+=(sell - buy);
        return ans;
    }
}

// DP Solution that works on all cases 

class Solution {
    public int buySellStock(int[] prices, int idx, int buySell, int[][] dp){
        if(idx == prices.length){
            return 0;
        }
        if(dp[idx][buySell] != -1){
            return dp[idx][buySell];
        }
        int ans = 0;
        if(buySell == 1){
            int buyKaro = -prices[idx] + buySellStock(prices, idx+1, 0, dp);
            int skipKaro = buySellStock(prices, idx+1, buySell, dp);

            ans = Math.max(buyKaro, skipKaro);
        }else{
            int sellKaro = +prices[idx] + buySellStock(prices, idx+1, 1, dp);
            int skipKaro = buySellStock(prices, idx+1, buySell, dp);
            ans = Math.max(sellKaro, skipKaro);
        }
        return dp[idx][buySell] = ans;
    }
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }
        return buySellStock(prices, 0, 1, dp);
    }
}

/*
Buy Sell 3 - Atmost 2 times are allowed to buy or sell the stock ||||| k can be any digit it works for all cases
*/

class Solution {
    public int buySellStock(int[] prices, int idx, int buySell, int[][][] dp, int k){
        if(idx == prices.length){
            return 0;
        }
        if(k == 0){
            return 0;
        }
        if(dp[idx][buySell][k-1] != -1){
            return dp[idx][buySell][k-1];
        }
        int ans = 0;
        if(buySell == 1){
            int buyKaro = -prices[idx] + buySellStock(prices, idx+1, 0, dp, k);
            int skipKaro = buySellStock(prices, idx+1, buySell, dp, k);

            ans = Math.max(buyKaro, skipKaro);
        }else{
            int sellKaro = +prices[idx] + buySellStock(prices, idx+1, 1, dp, k-1);
            int skipKaro = buySellStock(prices, idx+1, buySell, dp, k);
            ans = Math.max(sellKaro, skipKaro);
        }
        return dp[idx][buySell][k-1] = ans;
    }

    public int maxProfit(int[] prices) {
        int k = 2;
        int[][][] dp = new int[prices.length][2][k];
        for(int i=0; i<prices.length; i++){
            for(int j=0; j<2; j++){
                for(int z=0; z<k; z++){
                    dp[i][j][z] = -1;
                }
            }
        }
        return buySellStock(prices, 0, 1, dp, k);
    }
}

/*
Inplace rotate square matrix by 90 degrees 
::: This operation can be performed by reversing each row individually
and then performing Transpose of a matrix

 OR

::: Transpose the Matrix and then reverse the columns individually.
*/

class Solution {
    void rotateMatrix(int arr[][], int n) {
        // code here
        int ki = 0;
        while(ki<n){
            for(int beg = 0, end = n-1; beg<=end; beg++, end--){
                int temp = arr[ki][beg];
                arr[ki][beg] = arr[ki][end];
                arr[ki][end] = temp;
            }
            ki++;
        }
        for(int si=0; si<n; si++){
            for(int sj=si; sj<n; sj++){
                int temp = arr[si][sj];
                arr[si][sj] = arr[sj][si];
                arr[sj][si] = temp;
            }
        }
        
    }
}

/*
Find K Pairs with Smallest Sums
*/
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] A, int[] B, int K) {
        int n = A.length;
        PriorityQueue<int[]> pq
            = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<String> mySet = new HashSet<>();
 
        pq.offer(new int[] { A[0] + B[0], 0, 0 });
        mySet.add("0,0");
        List<List<Integer>> ans = new ArrayList<>();
        int count = 0;
        while (count < K && !pq.isEmpty()) {
            int[] temp = pq.poll();
            int i = temp[1], j = temp[2];
            List<Integer> tempA = new ArrayList<>();
            tempA.add(A[i]);
            tempA.add(B[j]);
            ans.add(tempA);
            
            boolean flag = false;
            if (i + 1 < n) {
                int sum = A[i + 1] + B[j];
                String key = (i + 1) + "," + j;
 
                if (!mySet.contains(key)) {
                    pq.offer(new int[] { sum, i + 1, j });
                    mySet.add(key);
                    flag = true;
                }
            }
 
            if (j + 1 < B.length) {
                int sum = A[i] + B[j + 1];
                String key = i + "," + (j + 1);
 
                if (!mySet.contains(key)) {
                    pq.offer(new int[] { sum, i, j + 1 });
                    mySet.add(key);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
            count++;
        }  
        return ans;
    }
}

/*
Find Minimum in Rotated Sorted Array
*/
class Solution {
    public int findMin(int[] arr) {
        int n = arr.length;
        int si = 0, ei = arr.length - 1;
        if(arr[si] <= arr[ei]){
            return arr[si];
        }
        while(si <= ei){
            int mid = si + (ei - si)/2;
            if(mid < ei && arr[mid] > arr[mid+1]){
                return arr[mid+1];
            }
            if(mid > si && arr[mid] < arr[mid-1]){
                return arr[mid];
            }
            if(arr[ei] > arr[mid]){
                ei = mid-1;
            }else{
                si = mid+1;
            }
        }
        return arr[si];
    }
}


/*
Search in a Rotated Sorted Array
*/

class Solution {
    public int search(int[] arr, int target) {
        int si = 0, ei = arr.length-1;
        while(si <= ei){
            int mid = si + (ei-si)/2;
            if(arr[mid] == target){
                return mid;
            }
            if(arr[si] <= arr[mid]){
                // Sorted Array
                // checking valid condition
                if(target <= arr[mid] && target >= arr[si]){
                    ei = mid-1;
                }else{
                    si = mid+1;
                }
            }else if(arr[mid] <= arr[ei]){
                // Reversed Array Point
                if(target <= arr[ei] && target > arr[mid]){
                    si = mid+1;
                }else{
                    ei = mid-1;
                }
            }
        }  
        return -1;
    }
}

/*
Find if there is a pair with a given sum in the rotated sorted Array
*/

public boolean isPairExists(int[] arr, int target){
    int si = 0, ei = arr.length-1, n = arr.length;
    int minIdx = -1;
    while(si<=ei){
        int mid = si + (ei-si)/2;
        if(mid<ei && arr[mid+1] <= arr[mid]){
            minIdx = mid;
            break;
        }
        if(mid>si && arr[mid-1] > arr[mid]){
            minIdx = mid;
            break;
        }
        if(arr[ei] > arr[mid]){
            ei = mid-1;
        }else{
            si = mid+1;
        }
    }
    si = minIdx;
    ei = (minIdx == 0) ? arr.length-1 : minIdx-1;
    while(si <= ei){
        int sum = arr[si] + arr[ei];
        if(sum < target){
            si = (si+1)%n;
        }else if(sum > target){
            ei = (n+ei-1)%n;
        }
        else{
            return true;
        }
    }
    return false;
}

/*
Maximum Sum i*arr[i] Among All Rotations Of A Given Array
*/

class GfG
{
    int max_sum(int A[], int n)
    {
        int totalSum = 0;
        for(int val : A){
            totalSum += val;
        }
        
        int currVal = 0;
        for(int i=0; i<n; i++){
            currVal += (i*A[i]);
        }
        int ans = currVal;
        for(int i=1; i<n; i++){
            currVal = currVal - (totalSum - A[i-1]) + (A[i-1]*(n-1));
            ans = Math.max(ans, currVal);
        }
        return ans;
    }	
}

/*
Rearrange Positive And Negative Numbers In O(n) Time And O(1) Extra Space
*/

// No Relative Ordering

class Solution {
    void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    void rearrange(int arr[], int n) {
        int i = -1, j = 0;
        for(j=0; j<n; j++){
            if(arr[j] < 0){
                i++;
                swap(arr, i, j);
            }
        }
        int pos = i+1, neg = 0;
        while(pos < n && neg < pos && arr[neg] < 0){
            swap(arr, pos, neg);
            pos++;
            neg += 2;
        }
    }
}
 
// Relative Ordering  || But it will be of N*N

// Using the Space but for the O(1) Space solution it will be of O(N*N)


/*
Three way partitioning of an array around a given range
*/

class Solution{
    //Function to partition the array around the range such 
    //that array is divided into three parts.
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public void threeWayPartition(int arr[], int a, int b)
    {
        int n = arr.length;
        int k=-1, i=0, j=n-1;
        while(i<=j){
            if(arr[i] < a){
                k++;
                swap(arr, k, i);
                i++;
            }else if(arr[i] > b){
                swap(arr, i, j);
                j--;
            }else{
                i++;
            }
        }
    }
}


/*
Maximum Length Bitonic Subarray
*/
class Solution {
    int bitonic(int[] arr, int n) {
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = 1;
        for(int i=1; i<n; i++){
            if(arr[i] >= arr[i-1]){
                leftMax[i] += leftMax[i-1]+1;
            }else{
                leftMax[i] = 1;
            }
        }
        
        rightMax[n-1] = 1;
        for(int i=n-2; i>=0; i--){
            if(arr[i] >= arr[i+1]){
                rightMax[i] += rightMax[i+1]+1;
            }else{
                rightMax[i] = 1;
            } 
        }
        int ans = 0;
        for(int i=0; i<n; i++){
            ans = Math.max(ans, leftMax[i] + rightMax[i] - 1);
        }
        return ans;
    }

}

/*
Largest subarray of 0's and 1's
*/

class Solution {

    // arr[] : the input array containing 0s and 1s
    // N : size of the input array
    
    // return the maximum length of the subarray
    // with equal 0s and 1s
    int maxLen(int[] arr, int n)
    {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int sum = 0, ans = 0;
        hm.put(0,-1);
        for(int i=0; i<n; i++){
            if(arr[i] == 0){
                sum-=1;
            }else{
                sum+=1;
            }
            
            if(hm.containsKey(sum)){
                int idx = hm.get(sum);
                int len = i - idx;
                ans = Math.max(ans, len);
            }else{
                hm.put(sum, i);
            }
        }
        return ans;
    }
}

/*
Maximum Product Subarray
*/

class Solution {
    // Function to find maximum product subarray
    long maxProduct(int[] arr, int n) {
        long maxEndingHere = 1, minEndingHere = 1, maxSoFar = 0;
        int flag = 0;
        for(int i=0; i<n; i++){
            if(arr[i] > 0){
                maxEndingHere *= arr[i];
                minEndingHere = Math.min(1, minEndingHere * arr[i]);
                flag = 1;
            }else if(arr[i] == 0){
                maxEndingHere = 1;
                minEndingHere = 1;
            }else{
                long temp = maxEndingHere;
                maxEndingHere = (minEndingHere * arr[i]);
                minEndingHere = temp * arr[i];
            }
            if(maxSoFar < maxEndingHere){
                maxSoFar = maxEndingHere;
            }
        }
        if(flag == 0 && maxSoFar == 0){
            return 0;
        }
        return maxSoFar;
    }
}


// More Easy Solutionn

class Solution {
    public int maxProduct(int[] nums) {
        int maxProd = Integer.MIN_VALUE, ansSoFar = 1, n = nums.length;
        for(int i=0;i<n;i++){
            ansSoFar *= nums[i];
            maxProd = Math.max(ansSoFar, maxProd);
            if(ansSoFar == 0){
                ansSoFar = 1;
            }
        }
        ansSoFar = 1;
        for(int i=n-1;i>=0;i--){
            ansSoFar *= nums[i];
            maxProd = Math.max(ansSoFar, maxProd);
            if(ansSoFar == 0){
                ansSoFar = 1;
            }
        }
        return maxProd;
    }
}

/*
Count Inversion 
*/

class Solution
{
    // arr[]: Input Array
    // N : Size of the Array arr[]
    //Function to count inversions in the array.
    long[] copyOfRange(long[] arr, long si, long ei){
        long[] temp = new long;
        long i = 0;
        while(si <= ei){
            temp[i++] = arr[si++];
        }
        return temp;
    }
    static long getCountInversion(long[] arr ,long l, long m, long r){
        long[] left = copyOfRange(arr, l, m+1);
        long[] right = copyOfRange(arr, m+1, r+1);
        long i=0, j=0, k=l;
        long swaps = 0;
        while(i < left.length && j < right.length){
            if(left[i] <= right[j]){
                arr[k++] = left[i++];
            }else{
                arr[k++] = right[j++];
                swaps += ((m+1) - (l+i));
            }
        }
        while(i < left.length){
            arr[k++] = left[i++];
        }
        while(j < right.length){
            arr[k++] = right[j++];
        }
        return swaps;
    }
    static long countInversion(long[] arr, long beg, long end){
        long count = 0;
        if(beg < end){
            long mid = beg + (end - beg)/2;
            countInversion(arr, beg, mid);
            countInversion(arr, mid+1, end);
            count = getCountInversion(arr, beg, mid, end);
        }
        return count;
    }
    static long inversionCount(long arr[], long N)
    {
        // Your Code Here
        return countInversion(arr,0, N);
    }
}


/*
Seggregate 0 and 1 

Algo 1 : Count the Zero and Count 1 then put those number of 1 and 0
*/

// Algo 2 ::

class Solution {
    void segregate0and1(int[] arr, int n) {
        int si = 0, ei = n-1;
        while(si <= ei){
            if(arr[si] == 0){
                si++;
            }else{
                int temp = arr[si];
                arr[si] = arr[ei];
                arr[ei] = temp;
                ei--;
            }
        }
    }
}


/*
Seggregate 0 1 2
*/

class Solution
{
    public static void swap(int[] arr, int si, int ei){
        int temp = arr[si];
        arr[si] = arr[ei];
        arr[ei] = temp;
    }
    public static void sort012(int a[], int n)
    {
        int i = -1, j=n-1, k = 0;
        while(k<=j){
            if(a[k]==0){
                i++;
                swap(a, i, k);
                k++;
            }else if(a[k] == 2){
                swap(a, j, k);
                j--;
            }else{
                k++;
            }
        }
    }
}

/*
Merge Overlapping Intervals
*/

class Solution
{
    public class pair implements Comparable<pair>{
        int u;
        int v;
        pair(){
        }
        pair(int u, int v){
            this.u = u;
            this.v = v;
        }
        
        public int compareTo(pair other){
            if(this.u != other.u){
                return this.u - other.u;
            }else{
                return this.v - other.v;
            }
        }
    }
    public int[][] overlappedInterval(int[][] Intervals)
    {
        int n = Intervals.length;
        pair[] ar = new pair[n];
        for(int i=0; i<n; i++){
            ar[i] = new pair(Intervals[i][0], Intervals[i][1]);
        }
        Arrays.sort(ar);
        Stack<pair> st = new Stack<>();
        st.push(ar[0]);
        for(int i = 1; i < n; i++){
            pair cp = ar[i];
            pair rp = st.peek();
            if(rp.v < cp.u){
                st.push(cp);
            }else if(rp.v >= cp.u){
                pair p = new pair();
                p.u = Math.min(cp.u, rp.u);
                p.v = Math.max(cp.v, rp.v);
                st.pop();
                st.push(p);
            }
        }
        int i = st.size() - 1;
        int[][] ans = new int[st.size()][2];
        while(st.size() > 0){
            pair p = st.pop();
            ans[i][0] = p.u;
            ans[i][1] = p.v;
            i--;
        }
        return ans;
        
    }
}

/*
Minimize the maximum difference between the heights
*/

class Solution {
    int getMinDiff(int[] arr, int n, int k) {
        Arrays.sort(arr);
        int ans = arr[n-1] - arr[0];
        int tempMin = arr[0], tempMax = arr[n-1];
        for(int i=1; i<n; i++){
            if(arr[i] - k < 0){
                continue;
            }
            tempMin = Math.min(arr[0] + k, arr[i] - k);
            tempMax = Math.max(arr[i-1] + k, arr[n-1] - k);
            ans = Math.min(ans, tempMax - tempMin);
        }
        return ans;
    }
}

/*
Minimum Swaps Required To Bring All Elements Less Than Or Equal To k Together
*/

class Complete{
   
    // Function for finding maximum and value pair
    public static int minSwap (int arr[], int n, int k) {
        //Complete the function
        int count = 0;
        for(int val : arr){
            if(val <= k){
                count++;
            }
        }
        int i=0, j = 0;
        int ans = Integer.MAX_VALUE;
        int minAns = 0;
        while(j < count){
            if(arr[j] > k){
                minAns++;
            }
            j++;
        } 
        ans = Math.min(ans, minAns);
        while(j < n){
            if(arr[i] > k){
                minAns--;
            }
            if(arr[j] > k){
                minAns++;
            }
            j++;
            i++;
            ans = Math.min(ans, minAns);
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
        
}


/*
Largest sum subarray with at-least k numbers
*/

class Test
{
	static int maxSumWithK(int a[], int n, int k)
	{
		int maxSum[] = new int [n];
		maxSum[0] = a[0];

		int curr_max = a[0];
		for (int i = 1; i < n; i++)
		{
			curr_max = Math.max(a[i], curr_max+a[i]);
			maxSum[i] = curr_max;
		}

		int sum = 0;
		for (int i = 0; i < k; i++)
			sum += a[i];

		int result = sum;
		for (int i = k; i < n; i++)
		{
			sum = sum + a[i] - a[i-k];
            result = Math.max(result, sum);
			// Include maximum sum till [i-k] also if it increases overall max.
			result = Math.max(result, sum + maxSum[i-k]);
		}
		return result;
	}
}

/*
Form Minimum Number From Given Sequence
*/

class Solution{
    static String printMinNumberForPattern(String S){
        Stack<Integer> st = new Stack<>();
        int num = 1;
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<S.length(); i++){
            char ch = S.charAt(i);
            if(ch == 'D'){
                st.push(num);
                num++;
            }else if(ch == 'I'){
                st.push(num);
                num++;
                while(st.size() > 0){
                    sb.append(st.pop());
                }
            }
        }
        st.push(num);
        while(st.size() > 0){
            sb.append(st.pop());
        }
        return sb.toString();
    }
}

/*
Find the smallest positive integer value that cannot be represented as sum of any subset of a given array
*/

class Solution { 
    long smallestpositive(long[] ar, int n){ 
        long num=1;
        Arrays.sort(ar);
        for(int i=0; i<n; i++){
            if(ar[i] > num){
                return num;
            }else{
                num += ar[i];
            }
        }
        return num;
    }
} 

class MenuRecommendation implements IMenuRecommendation{
    
    public class pair{
        Stirng displayName;
        int id;
        String rating;
        int noOfPeoplesRated;
    }

    private int dealOfTheDay;
    private ArrayList<pair> al;
    MenuRecommendation(){
        dishOfTheDay = -1;
        al = new ArrayList<>();
    }

    @Override
    public void addItem(int id, String displayName){
        pair p = new pair();
        p.id = id;
        p.displayName = displayName;
        p.rating = 0;
        p.noOfPeoplesRated = 0;
        al.add(p);
    }

    @Override
    public void outOfStockItem(int itemId){
        int idx = -1;
        for(int i=0;i<al.size(); i++){
            if(al.get(i).id == itemId){
                idx = i;
                break;
            }
        }
        if(al.get(idx).id == dealOfTheDay){
            dealOfTheDay = -1;
        }
        al.remove(idx);
    }

    @Override 
    public void makeDealOfTheDayItem(int itemId){
        dealOfTheDay = itemId;
    }

    @Override
    public void rateItem(int itemId, int rating){
        int idx = -1;
        for(int i=0; i<al.size(); i++){
            if(al.get(i).id == itemId){
                idx = i;
                break;
            }
        }
        if(idx == -1){
            return;
        }
        al.get(idx).rating = (al.get(idx).rating + rating) / (al.get(idx).noOfPeoplesRated() + 1);
        al.get(idx).noOfPeoplesRated = (al.get(idx).noOfPeoplesRated + 1);
    }
}

@Override
public MenuItem getRecommendedItem(){
    if(dealOfTheDay == -1){
        // find max rating value from arraylist
    }else{
        // find the value from arraylist where id is same as dealOfTheDay; 
    }
}
