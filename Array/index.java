// Rotate an Array
// Reverse an Array

// Finding Maximum and minimum in an array
class Solution {
    public Pair<Long, Long> getMinMax(int[] arr) {
        Pair<Long, Long> p = new Pair<>(-1L, -1L);
        Long min = Long.MAX_VALUE;
        Long max = Long.MIN_VALUE;
        for(int val : arr){
            min = Math.min(min, val);
            max = Math.max(max, val);
        }
        p = new Pair((Long)min, (Long)max);
        return p;
    }
}

// Rotate an array by 1
// Rotate an Array by k
class Solution {
    public void rotate(int[] nums, int k) {
        k = k%(nums.length);
        int[] temp = new int[nums.length];
        int j=0;
        for(int i=nums.length-k; i<nums.length; i++){
            temp[j++] = nums[i];
        }
        for(int i=0; i<nums.length-k; i++){
            temp[j++] = nums[i];
        }
        for(int i=0; i<nums.length; i++){
            nums[i] = temp[i];
        }
    }
}

// Approach 2 :
class Solution {
    private void reverse(int[] nums,  int si, int ei){
        while(si <= ei){
            int temp = nums[si];
            nums[si] = nums[ei];
            nums[ei] = temp;
            si++;
            ei--;
        }
    }
    public void rotate(int[] nums, int k) {
        k = k%(nums.length);
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
}

// Find Union and Intersection of 2 sorted arrays

// Second Largest element in an array without sorting

class Solution {
    public int getSecondLargest(int[] arr) {
        // Code Here
        int max = Integer.MIN_VALUE;
        int sMax = -1;
        for(int i=0; i<arr.length; i++){
            if(arr[i] > max){
                sMax = max;
                max = arr[i];
            }
            if(arr[i] < max && arr[i] > sMax){
                sMax = arr[i];
            }
        }
        return sMax == Integer.MIN_VALUE ? -1 : sMax;
    }
}

//




// Finding Kth Smallest Element in an Array

// Approach 1 : Sorting

// Approach 2 : Min HEAP
public static int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<arr.length; i++){
            pq.add(arr[i]);
        }
        while(k-- > 1){
            pq.remove();
        }
        return pq.peek();
    }

// Approach 3 :: Quick Select Method 
class Solution {
    private static int findPivotIdx(int[] ar, int left, int right){
        int pivot = left;
        int l = left+1;
        int r = right;
        while(l <= r){
            if(ar[l] > ar[pivot] && ar[r] < ar[pivot]){
                int temp = ar[l];
                ar[l] = ar[r];
                ar[r] = temp;
                l++;
                r--;
            }else if(ar[l] < ar[pivot]){
                l++;
            }else if(ar[r] > ar[pivot]){
                r--;
            }
        }
        int temp2=ar[left];
        ar[left]=ar[r];
        ar[r]=temp2;
        return r;
    }

    public static int kthSmallest(int[] ar, int k) {
        int left = 0, right = ar.length-1;
        while(true){
            int idx = findPivotIdx(ar, left, right);
            if(idx == k-1){
                return ar[idx];
            }else if(idx < k){
                left = idx+1;
            }else{
                right = idx-1;
            }
        }
        //return -1;
    }
}

// Sort 0, 1, 2
public static void sort012(int a[], int n)
    {
        int l = 0;
        int r = n-1;
        int idx = 0;
        while(idx <= r){
            if(a[idx] == 1){
                idx++;
                continue;
            }else if(a[idx] == 0){
                int temp = a[idx];
                a[idx] = a[l];
                a[l] = temp;
                l++;
                idx++;
            }else if(a[idx] == 2){
                int temp = a[idx];
                a[idx] = a[r];
                a[r] = temp;
                r--;
            }
        }
    }

// Move All Negative elements to one side of the array

 void rearrange(ArrayList<Integer> arr) {
        int idx = 0;
        int n = arr.size();
        for(int i=0; i<n; i++){
            if(arr.get(i) < 0){
                swap();
                idx++;
            }else{
                continuel
            }
        }
    }
  
// Maximum Contiguous SubarraySum 

long maxSubarraySum(int[] arr) {
        long maxSum = Integer.MIN_VALUE, maxTillNow = 0;
        for(int i=0; i<arr.length; i++){
            maxTillNow += arr[i];
            maxSum = Math.max(maxSum, maxTillNow);
            maxTillNow = Math.max(0, maxTillNow);
        }
        return maxSum;
    }

// Minimize the Heights 2  || Minimize the maximum difference between heights

int getMinDiff(int[] arr, int n, int k) {
        Arrays.sort(arr);
        int ans = arr[n-1] - arr[0];
        int tempMin = arr[0];
        int tempMax = arr[n-1];
        for(int i=1; i<n; i++){
            if(arr[i]-k < 0){
                continue;
            }
            tempMin = Math.min(arr[0]+k, arr[i]-k);
            tempMax = Math.max(arr[i-1]+k, arr[n-1]-k);
            ans = Math.min(ans, tempMax-tempMin);
        }
        return ans;
    }


// Find The Duplicate Number 

/// Approach 1 : Naive approach of checking for each number
/// Approach 2 : HashMap

/// Approach 3 : Binary Search if the array is sorted || Otherwise we can also sort the array
public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length-1;
        while(low < high){
            int mid = (low + high)/2;
            int count = 0;
            for(int n : nums){
                if(n <= mid){
                    count++;
                }
            }
            if(count <= mid){
                low = mid+1;
            }else{
                high = mid;
            }
        }
        return low;
    }

/// Approach 4 : Bit Maniputation if Array is sorted
public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=0; i<n-1; i++){
            if((nums[i]^nums[i+1]) == 0){
                return nums[i];
            }
        }
        return -1;
    }

// Approach 5 : Slow and Fast Pointer Approach ||| This only works with the do - while loop

int findDuplicate(std::vector<int>& nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Find the intersection point of the two pointers
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Find the entrance of the cycle
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

// Find the element that appears once in an array where every other element appears twice
//  Mostly same type of solutions here also 

// Approach -> Binary Search || We try to create the groups of even and odd position

public int findElement(int[] ar){
    int low = 0, high = ar.length-2;
    while(low < high){
        int mid = (low + high)/2;
        if(ar[mid] == ar[mid^1]){
            low = mid+1;
        }else{
            high = mid-1;
        }
    }
    return ar[low];
}



// Find all Duplicates Element || All Elements Repeated Twice

// Approach 1 : Hashmap approach

// Approach 2 : Iteration and Indexing
// Note : Elements should be in range of 1-n
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> al = new ArrayList<>();
        for(int n : nums){
            int abs = Math.abs(n);
            if(nums[abs-1] > 0){
                nums[abs-1]*=-1;
            }else{
                al.add(abs);
            }
        }
        return al;
    }
}

// Approach 3 : Bit Manipulation

public int[] twoRepeated(int size, int arr[]) {
        int n = size-2;
        int[] ans = new int[2];
        int xor = arr[0];
        for(int i=1; i<size; i++){
            xor ^= arr[i];
        }
        for(int i=1; i<=n; i++){
            xor ^= i;
        }
        int v1 = 0, v2 = 0;
        int setBit = xor & ~(xor-1);
        for(int i=0; i<size; i++){
            int a = setBit&arr[i];
            if(a != 0){
                v1 ^= arr[i];
            }else{
                v2 ^= arr[i];
            }
        }
        
        
        for(int i=1; i<=n; i++){
            int a = setBit&i;
            if(a != 0){
                v1 ^= i;
            }else{
                v2 ^= i;
            }
        }
        
        ans[0] = v2;
        ans[1] = v1;
        return ans;
        
    }

// Repeat and Missing Number

int[] findTwoElement(int arr[]) {
        int xor = 0;
        for(int val : arr){
            xor ^= val;
        }
        for(int i=1; i<=arr.length; i++){
            xor ^= i;
        }
        
        int setBit = xor & ~(xor-1);
        int v1 = 0, v2 = 0;
        for(int i=0; i<arr.length; i++){
            int a = arr[i]&setBit;
            if(a != 0){
                v1 ^= arr[i];
            }else{
                v2 ^= arr[i];
            }
        }
        
        for(int i=1; i<=arr.length; i++){
            int a = i&setBit;
            if(a != 0){
                v1 ^= i;
            }else{
                v2 ^= i;
            }
        }
        
        int[] ans = new int[2];
        ans[0] = v2;
        ans[1] = v1;
        return ans;
    }


// Merge Sorted Arrays 

public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0, j=0;
        int[] ans = new int[m+n];
        int k = 0;
        while(i<m && j<n){
            if(nums1[i] < nums2[j]){
                ans[k++] = nums1[i++];
            }else if(nums1[i] > nums2[j]){
                ans[k++] = nums2[j++];
            }else{
                ans[k++] = nums1[i++];
                ans[k++] = nums2[j++];
            }
        }
        while(i < m){
            ans[k++] = nums1[i++];
        }
        while(j < n){
            ans[k++] = nums2[j++];
        }
        for(int x=0; x<(m+n); x++){
            nums1[x] = ans[x];
        }
    }


// Merge Overlapping Intervals 

class Solution {
    public class pair implements Comparable<pair>{
        int x, y;
        pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(pair o){
            if(this.x == o.x){
                return this.y - o.y;
            }else{
                return this.x - o.x;
            }
        }
    }
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        pair[] ar = new pair[n];
        for(int i=0; i<n; i++){
            ar[i] = new pair(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(ar);
        Stack<pair> st = new Stack<>();
        st.push(ar[0]);
        for(int i=1; i<n; i++){
            pair cp = ar[i];
            pair rp = st.pop();
            if(cp.x > rp.y){
                st.push(rp);
                st.push(cp);
            }else{
                st.push(new pair(Math.min(cp.x, rp.x), Math.max(cp.y, rp.y)));
            }
        }
        int[][] ans = new int[st.size()][2];
        for(int i=ans.length-1; i>=0; i--){
            pair rp = st.pop();
            ans[i][0] = rp.x;
            ans[i][1] = rp.y;
        }
        return ans;
    }
}

// Merge Sort 

class Solution
{
    void merge(int arr[], int l, int m, int r)
    {
        int n1 = m-l+1;
        int n2 = r-m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for(int i=0; i<n1; i++){
            L[i] = arr[l+i];
        }
        for(int j=0; j<n2; j++){
            R[j] = arr[m+j+1];
        }
        int k=l, i=0, j=0;
        while(i<n1 && j<n2){
            if(L[i] < R[j]){
                arr[k++] = L[i++];
            }else if(L[i] > R[j]){
                arr[k++] = R[j++];
            }else{
                arr[k++] = L[i++];
                arr[k++] = R[j++];
            }
        }
        while(i < n1){
            arr[k++] = L[i++];
        }
        while(j < n2){
            arr[k++] = R[j++];
        }
    }
    void mergeSort(int arr[], int l, int r)
    {
        if(l < r){
            int m = (l+r)/2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }
}

// Count Inversion :::

static long merge(long[] arr, int si, int mid, int ei){
        int n1 = mid-si+1;
        int n2 = ei-mid;
        long ans = 0;
        long[] L = new long[n1];
        long[] R = new long[n2];
        for(int i=0; i<n1; i++){
            L[i] = arr[i+si];
        }
        for(int j=0; j<n2; j++){
            R[j] = arr[j+mid+1];
        }
        
        int k=si, i=0, j=0;
        while(i<n1 && j<n2){
            if(L[i] <= R[j]){
                arr[k++] = L[i++];
            }else if(L[i] > R[j]){
                ans += (n1-i);
                arr[k++] = R[j++];
            }
            // else{
            //   arr[k++] = L[i++];
            //   arr[k++] = R[j++];
            // }
        }
        while(i < n1){
            arr[k++] = L[i++];
        }
        while(j < n2){
            arr[k++] = R[j++];
        }
        return ans;
    }
    static long mergeSort(long[] arr, int si, int ei){
        long ans = 0;
        if(si < ei){
            int mid = (si + ei)/2;
            ans += mergeSort(arr, si, mid);
            ans += mergeSort(arr, mid+1, ei);
            ans += merge(arr, si, mid, ei);
        }
        return ans;
    }
    static long inversionCount(long arr[], int n) {
        return mergeSort(arr, 0, n-1);
    }

// Target Sum Pair ||| 2 Sum 

public static ArrayList<ArrayList<Integer>> getPairs(int[] arr) {
        Arrays.sort(arr);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int si = 0, ei = arr.length-1;
        while(si < ei){
            int sum = arr[si] + arr[ei];
            if(sum == 0){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(arr[si]);
                temp.add(arr[ei]);
                ans.add(temp);
                si++;
                ei--;
            }else if(sum < 0){
                si++;
            }else if(sum > 0){
                ei--;
            }
        }
        return ans;
    }


// Triplet Sum || 3 Sum

public static boolean find3Numbers(int arr[], int n, int x) {
        Arrays.sort(arr);
        for(int i=0; i<n; i++){
            int si = i+1, ei = n-1;
            while(si < ei){
                int sum = arr[i] + arr[si] + arr[ei];
                if(sum == x){
                    return true;
                }else if(sum < x){
                    si++;
                }else{
                    ei--;
                }
            }
        }
        return false;
    }


// Commmon in 3 Sorted Arrays

public List<Integer> commonElements(List<Integer> arr1, List<Integer> arr2,
                                        List<Integer> arr3) {
                                            
                                            
        int l1=0, l2=0, l3=0;
        int sz1 = arr1.size(), sz2 = arr2.size(), sz3 = arr3.size();
        
        List<Integer> ans = new ArrayList<>();
        while(l1 < sz1 && l2 < sz2 && l3 < sz3){
            if(arr1.get(l1) == arr2.get(l2) && arr2.get(l2) == arr3.get(l3)){
                ans.add(arr1.get(l1));
                l1++;
                l2++;
                l3++;
            }else if(arr1.get(l1) < arr2.get(l2)){
                l1++;
            }else if(arr2.get(l2) < arr3.get(l3)){
                l2++;
            }else{
                l3++;
            }
        }
        return ans;
    }

//  SubArray with 0 Sum

static boolean findsum(int arr[],int n)
    {
        int sum = 0;
        HashSet<Integer> hs = new HashSet<>();
        for(int val : arr){
            sum += val;
            if(val == 0 || sum == 0 || hs.contains(sum)){
                return true;
            }
        }
        return false;
    }


// Maximum Product SubArray

long maxProduct(int[] arr, int n) {
    int maxSoFar = arr[0];
    int maxEndingHere = arr[0];
    int minEndingHere = arr[0];
    for(int i=1; i<n; i++){
        int temp = Math.max(arr[i], Math.max(maxEndingHere*arr[i], 
        minEndingHere*arr[i]));
        minEndingHere = Math.min(arr[i], Math.min(maxEndingHere*arr[i], 
        minEndingHere*arr[i]));
        maxEndingHere = temp;
        maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    return maxSoFar;
}


// length of Longest Conseuting Subsequence

static int findLongestConseqSubseq(int arr[], int N)
	{
	   int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	   HashSet<Integer> hs = new HashSet<>();
	   for(int a : arr){
	       min = Math.min(a, min);
	       max = Math.max(a, max);
	       hs.add(a);
	   }
	   
	   int count = 0;
	   int ans = Integer.MIN_VALUE;
	   while(min <= max){
	       if(hs.contains(min)){
	           count++;
	           ans = Math.max(ans, count);
	       }else{
	           count = 0;
	       }
	       min++;
	   }
	   return ans;
	  
	}

// Approach 2 : Sorting
static int findLongestConseqSubseq(int arr[], int N)
	{
	   Arrays.sort(arr);
	   int min = arr[0];
	   int ans = 0, cnt = 1;
	   for(int i=1; i<N; i++){
	       if(arr[i] == min){
	           continue;
	       }
	       if(arr[i] == min+1){
	           cnt++;
	           ans = Math.max(ans, cnt);
	           min = arr[i];
	       }else{
	           cnt = 1;
	           min = arr[i];
	       }
	   }
	   return Math.max(ans, cnt);
	}


// Majority Element

public int majorityElement(int[] nums) {
    int count = 1, val = nums[0];
    for(int i=1; i<nums.length; i++){
        if(val == nums[i]){
            count++;
        }else{
            count--;
        }
        if(count == 0){
            count = 1;
            val = nums[i];
        }
    }
    int num = 0;
    for(int v : nums){
        if(v == val){
            num++;
        }
    }
    return (num > nums.length/2) ? val : -1;
}

// Majority Element - 2

public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int val1=nums[0], count1=1, val2=0, count2=0, n=nums.length;
        for(int i=1; i<n; i++){
            if(val1 == nums[i]){
                count1++;
            }
            else if(val2 == nums[i]){
                count2++;
            }else{
                if(count1 == 0){
                    val1 = nums[i];
                    count1=1;
                }else if(count2 == 0){
                    val2 = nums[i];
                    count2=1;
                }else{
                    count1--;
                    count2--;
                }
            }
        }

        int ct1=0, ct2=0;
        for(int val : nums){
            if(val1 == val){
                ct1++;
            }
            if(val2 == val){
                ct2++;
            }
        }
        if(ct1 > nums.length/3){
            ans.add(val1);
        }
        if(ct2 > nums.length/3 && val1 != val2){
            ans.add(val2);
        }
        return ans;
    }


// Mpjority Element 3 || Element Present n/k times

private class ele{
        int e;
        int c;
        ele(){}
    }
    public int countOccurence(int[] arr, int n, int k) {
        ele[] temp = new ele[k-1];
        for(int i=0; i<k-1; i++){
            temp[i] = new ele();
        }
        for(int i=0; i<k-1; i++){
            temp[i].c = 0;
        }
        
        for(int i=0; i<n; i++){
            int j;
            for(j=0; j<k-1; j++){
                if(temp[j].e == arr[i]){
                    temp[j].c++;
                    break;
                }
            }
            if(j == k-1){
                int l;
                for(l=0; l<k-1; l++){
                    if(temp[i].c == 0){
                        temp[i].e = arr[i];
                        temp[i].c = 1;
                        break;
                    }
                }
                if(l == k-1){
                    for(l=0; l<k-1; l++){
                        temp[i].c -= 1;
                    }
                }
            }
        }
        int ans = 0;
        for(int i=0; i<k-1; i++){
            int ac = 0;
            for (int j=0; j<n; j++){
                if(arr[j] == temp[i].e){
                    ac++;
                }
            }
 
            if (ac > n/k){
                ans++;
            }
        }
        return ans;
    }


/// Buy and Sell Stock Exactly Once

class Solution {
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int ans = 0;
        for(int i=0; i<prices.length; i++){
            int sell = prices[i];
            ans = Math.max(ans, sell-buy);
            buy = Math.min(buy, prices[i]);
        }
        return ans;
    }
}

// Buy and Sell Stock any Number of Times ||| Array Solution 

public int maxProfit(int[] prices) {
        int ans = 0;
        for(int i=1; i<prices.length; i++){
            if(prices[i] > prices[i-1]){
                ans += (prices[i]-prices[i-1]);
            }
        }
        return ans;
    }



// Array Subset

public String isSubset( long a1[], long a2[], long n, long m) {
        Arrays.sort(a1);
        Arrays.sort(a2);
        int si=0, sj=0;
        if(n < m){
            return "No";
        }
        while(sj < m){
            if(a1[si] == a2[sj]){
                si++;
                sj++;
            }else if(a1[si] < a2[sj]){
                si++;
            }else{
                return "No";
            }
        }
        return "Yes";
    }

// Approach 2 ::: HashSet Operation -> TC : O(N) and SC : O(N)

// Trapping Rain Water |||  SC -> O(N), TC -> (N) 

static long trappingWater(int arr[]) {
        int n = arr.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = arr[0];
        for(int i=1; i<n; i++){
            leftMax[i] = Math.max(arr[i], leftMax[i-1]);
        }
        rightMax[n-1] = arr[n-1];
        for(int i=n-2; i>=0; i--){
            rightMax[i] = Math.max(arr[i], rightMax[i+1]);
        }
        
        long ans = 0;
        for(int i=0; i<n; i++){
            ans += (Math.min(leftMax[i], rightMax[i]) - arr[i]);
        }
        return ans;
    }

// Approach 2 : 2 Pointer

public int trap(int[] ar) {
        int si = 0, ei = ar.length-1;
        int ans = 0, leftMax = 0, rightMax = 0;
        while(si < ei){
            if(ar[si] <= ar[ei]){
                if(ar[si] >= leftMax){
                    leftMax = ar[si];
                }else{
                    ans += (leftMax-ar[si]);
                }
                si++;
            }else{
                if(ar[ei] >= rightMax){
                    rightMax = ar[ei];
                }else{
                    ans += (rightMax-ar[ei]);
                }
                ei--;
            }
        }
        return ans; 
    }           

// Chocolate Distribution Problem || Sorting

 public long findMinDiff (ArrayList<Integer> a, int n, int m)
    {
        Collections.sort(a);
        int minValue = Integer.MAX_VALUE;
        for(int i=0; i<n-m+1; i++){
            int min = a.get(i);
            int max = a.get(i+m-1);
            minValue = Math.min(minValue, max-min);
        }
        return minValue;
    }

// Smallest Subarray with sum greater than X

class Solution {

    public static int smallestSubWithSum(int x, int[] arr) {
        int si = 0, ei = 0;
        int sum = 0, minLength = Integer.MAX_VALUE;
        while(ei < arr.length){
            sum += arr[ei];
            while(sum > x){
                sum -= arr[si];
                minLength = Math.min(ei-si+1, minLength);
                si++;
            }
            ei++;
        }
        if(minLength == Integer.MAX_VALUE) return 0;
        return minLength;
    }
}


// Three Way Partitioning
private void swap(int[] arr, int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    public void threeWayPartition(int arr[], int a, int b)
    {
        int x=0, y=0, z=arr.length-1;
        while(y <= z){
            if(arr[y] < a){
                swap(arr, x, y);
                x++;
                y++;
            }else if(arr[y] > b){
                swap(arr, y, z);
                z--;
            }else if(arr[y]>=a && arr[y]<=b){
                y++;
            }
        }
    }

// Minimum Swaps and K Together

public static int minSwap (int arr[], int n, int k) {
        int count = 0;
        for(int val : arr){
            if(val <= k){
                count++;
            }
        }
        
        int i=0, j=0;
        int ans = 0;
        while(j<count){
            if(arr[j] > k){
                ans++;
            }
            j++;
        }
        int minAns = ans;
        while(j<n){
            if(arr[j] > k){
                ans++;
            }
            if(arr[i] > k){
                ans--;
            }
            minAns = Math.min(ans, minAns);
            i++; j++;
        }
        return minAns;
    }

// Next Permutation

class Solution {
    private void swap(int[] ar, int i, int j){
        int temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }
    private void reverse(int[] ar, int i, int j){
        while(i<j){
            swap(ar, i, j);
            i++;
            j--;
        }
    }
    public void nextPermutation(int[] ar) {
        int n = ar.length;
        int i=n-2;
        while(i>=0 && ar[i] >= ar[i+1]){
            i--;
        }
        if(i != -1){
            int j = n-1;
            while(j>=0 && ar[i] >= ar[j]){
                j--;
            }
            swap(ar, i, j);
        }
        reverse(ar, i+1, n-1);
    }
}

 
// Power of X, N

class Solution {
    public double myPow(double x, int n) {
        double ans = 1.0;
        double nn = n;
        if(nn < 0) nn *= -1;
        while(nn > 0){
            if(nn%2 == 0){
                x *= x; 
                nn/=2;
            }else{
                ans *= x;
                nn--;
            }
        }
        return (n>=0) ? ans : 1/ans;
    }
}

// Minimum Number of Operations to make array Palindrome

private static int minimumOperationForArrayPalindrome(int[] ar, int n){
        int ans = 0;
        int si = 0, ei = n-1;
        while(si <= ei){
            if(ar[si] == ar[ei]){
                si++;
                ei--;
            }else if(ar[si] > ar[ei]){
                ei--;
                ar[ei] += ar[ei+1];
                ans++;
            }else{
                si++;
                ar[si] += ar[si-1];
                ans++;
            }
        }
        return ans;
    }

// Longest Substring without Repeat

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int si = 0, ei = 0;
        HashSet<Character> hs = new HashSet<>();
        while(ei < s.length()){
            char ch = s.charAt(ei);
            if(!hs.contains(ch)){
                hs.add(ch);
            }else{
                while(hs.size()>0 && hs.contains(ch)){
                    char ch1 = s.charAt(si);
                    hs.remove(ch1);
                    si++;
                }
                hs.add(ch);
            }
            ans = Math.max(ans, hs.size());
            ei++;
        }
        return ans;
    }
}

// 


// 396 : Rotate Function

//Basic Maths 
class Solution {
    public int maxRotateFunction(int[] nums) {
        int prod = 0;
        int sum = 0;
        int n = nums.length;
        for(int i=0; i<n; i++){
            sum += nums[i];
            prod += (nums[i]*i);
        }
        int ans=Integer.MIN_VALUE;
        
        for(int i=n-1; i>=0; i--){
            prod = prod + sum - n*nums[i];
            ans = Math.max(ans, prod);
        }
        return ans;
    }
}

// Find the index of 0 to be replaced to get the maximum length sequence of continuous ones

// approach 1 

public static int findIndexofZero(int[] nums)
	{
		int n = nums.length, count = 0, prevZeroIdx = -1, ans = 0, maxZeroIdx = 0;
		for(int i=0; i<n; i++){
			if(nums[i] == 1){
				count++;
			}else{
				count = i - prevZeroIdx;
				prevZeroIdx = i;
			}
			if(count > ans){
				ans = count;
				maxZeroIdx = prevZeroIdx;
			}
		}
		return maxZeroIdx;
	}

// approach 2  // Length of max seq containing only one ->  0=\


class Solution
{
	public static int findIndexofZero(int[] nums)
	{
		int ans = 0, prevIdx = -1, left = 0, count = 0;
		for(int i=0; i<nums.length; i++){
			if(nums[i] == 0){
				count++;
				prevIdx = i;
			}
			if(count == 2){
				while(nums[left] != 0) left++;
				left++;
				count = 1;
			}
			if(i-left+1 > ans){
				ans = i-left+1;
			}
		}
		return ans;
	}
}

// First and Last index of the pivot element

public class Main
{
	public static void main(String[] args) {
		int[] ar = {1,5,10,15,22,33,33,33,33,33,40,42,55,66,77};
		int target = 33;
		int si = 0, ei = ar.length-1;
		int left = -1;
		while(si < ei){
		    int mid = (si+ei)/2;
		    if(ar[mid] == target){
		        left = mid;
		        ei = mid-1;
		    }else if(ar[mid] > target){
		        ei = mid-1;
		    }else{
		        si = mid+1;
		    }
		}
		int right = -1;
		si = 0; ei = ar.length-1;
		while(si<ei){
		    int mid = (si+ei)/2;
		    if(ar[mid] == target){
		        right = mid;
		        si = mid+1;
		    }else if(ar[mid] > target){
		        ei = mid-1;
		    }else{
		        si = mid+1;
		    }
		}
		System.out.println(left + " " + right);
	}
}

// Faulty Keyboard

class Solution {
    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == 'i'){
                sb.reverse();
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}


// Next Greater Element 3 

class Solution {
    public int getSize(int n){
        int size = 0;
        while(n > 0){
            size++;
            n = n/10;
        }
        return size;
    }
    public int[] getNumToArray(int n, int size){
        int[] ar = new int[size];
        int i = size-1;
        while(n > 0){
            int num = n%10;
            ar[i--] = num;
            n/=10;
        }
        return ar;
    }
    public int getMinElementIdx(int[] ar, int n){
        int i = n-2;
        while(i>=0 && ar[i] >= ar[i+1]){
            i--;
        }
        return i;
    }
    public void printArray(int[] ar){
        int n = ar.length;
        for(int i=0; i<n; i++){
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
    public void swap(int[] ar, int i, int j){
        int ele = ar[i];
        ar[i] = ar[j];
        ar[j] = ele;
    }
    public int getArrayToNum(int[] ar, int size){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<size; i++){
           
        }
        
    }
    public void reverseArray(int[] ar, int i, int j){
        while(i<=j){
            int temp = ar[i];
            ar[i] = ar[j];
            ar[j] = temp;
            i++;
            j--;
        };
    }
    public int nextGreaterElement(int n) {
        int size = getSize(n);
        int[] ar = getNumToArray(n, size);
        printArray(ar);
        int minElementIdx = getMinElementIdx(ar, size);
        if(minElementIdx == -1){
            return -1;
        }
        System.out.println("Min Element " + minElementIdx +" "+ ar[minElementIdx]);
        int greaterElementIdx = size-1;
        while(greaterElementIdx >=0 && ar[minElementIdx] > ar[greaterElementIdx]){
            greaterElementIdx--;
        }
        System.out.println("Min Element " + greaterElementIdx +" "+ ar[greaterElementIdx]);
        swap(ar, minElementIdx, greaterElementIdx);
        printArray(ar);
        reverseArray(ar, minElementIdx+1, size-1);
        printArray(ar);
        int ans = getArrayToNum(ar, size);
        return ans;
    }
}



// Product of Array Except Self

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] rightProd = new int[n];
        rightProd[n-1] = nums[n-1];
        for(int i=n-2; i>=-0; i--){
            rightProd[i] = nums[i]*rightProd[i+1];
        }
        int leftProd = 1;
        int[] ans = new int[n];
        for(int i=0; i<n-1; i++){
            ans[i] = leftProd * rightProd[i+1];
            leftProd *= nums[i];
        }
        ans[n-1] = leftProd;
        return ans;
    }
}



// MAX Chunks to make Array Sorted  :::  Chaining Technique

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int count = 0;
        for(int i=0; i<arr.length; i++){
            max = Math.max(max, arr[i]);
            if(max == i){
                count++;
            }
        }
        return count;
    }
}


// Max Chunks to make array Sorted - 2 ::: Chaining Technique

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length, count = 0;
        int[] lmin = new int[n+1];
        lmin[n] = Integer.MAX_VALUE;
        for(int i=n-1; i>=0; i--){
            lmin[i] = Math.min(arr[i], lmin[i+1]);
        }
        int max = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            max = Math.max(max, arr[i]);
            if(max <= lmin[i+1]){
                count++;
            }
        }
        return count;
    }
}



// Partition Labels

class Solution {
    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            hm.put(ch, i);
        }
        int prev = -1, max = 0;
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            max = Math.max(hm.get(ch), max);
            if(max == i){
                ans.add(max - prev);
                prev = max;
            }
        }
        return ans;
    }
}

// Partition Array into disjoint sets

class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length, ans = 0, leftMax = Integer.MIN_VALUE;
        int[] rightMin = new int[n+1];
        rightMin[n] = Integer.MAX_VALUE;
        for(int i=n-1; i>=0; i--){
            rightMin[i] = Math.min(nums[i], rightMin[i+1]);
        }
        for(int i=0; i<n; i++){
            leftMax = Math.max(leftMax, nums[i]);
            if(leftMax <= rightMin[i+1]){
                ans = i;
                break;
            }
        }
        return ans+1;
    }
}

// Approach 2 :

class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length, ans = 0;
        int max = nums[0], greater = nums[0];
        for(int i=1; i<n; i++){
            if(nums[i] > greater){
                greater = nums[i];
            }else if(nums[i] < max){
                ans = i;
                max = greater;
            }
        }
        return ans+1;
    }
}


//  Minimum Domino Rotations For Equal Row Easy


class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int val1 = tops[0], val2 = bottoms[0];
        int ans1=0, ans2=0, ans3=0, ans4=0;
        int n = tops.length;
        for(int i=0; i<n; i++){
            System.out.println(ans1+" "+ans2+" "+ans3+" "+ans4);
            // trying to achieve val1 on top
            if(ans1 != Integer.MAX_VALUE){
                if(val1 == tops[i]){

                }else if(val1 == bottoms[i]){
                    ans1++;
                }else{
                    ans1 = Integer.MAX_VALUE;
                }
            }
            // Trying to achieve the val1 on bottom
            if(ans2 != Integer.MAX_VALUE){
                if(val1 == bottoms[i]){
                   
                }else if(tops[i] == val1){
                    ans2++;
                }else{
                    ans2 = Integer.MAX_VALUE;
                }
            }
            // Trying to achieve the val2 on top
            if(ans3 != Integer.MAX_VALUE){
                if(val2 == tops[i]){

                }else if(val2 == bottoms[i]){
                    ans3++;
                }else{
                    ans3 = Integer.MAX_VALUE;
                }
            }
            // Trying to achieve the val2 on bottom
            if (ans4 != Integer.MAX_VALUE) {
                if (bottoms[i] == val2) {
                    // do nothing
                } else if (tops[i] == val2) {
                    ans4++;
                } else {
                    ans4 = Integer.MAX_VALUE;
                }
            }
        }
        int ans = Math.min(ans1, Math.min(ans2, Math.min(ans3, ans4)));
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

// Reverse vowles of a string

class Solution {
    public String reverseVowels(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++)
        {
            char ch = s.charAt(i);
            if(ch=='a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
              ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
                sb.append(ch);
        }
        StringBuilder ans = new StringBuilder();
        int j = sb.length()-1;
        for(int i=0; i<s.length(); i++)
        {
            char ch = s.charAt(i);
            if(ch=='a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
              ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
            {
                ans.append(sb.charAt(j));
                j--;
            }
            else
            {
                ans.append(ch);
            }
        }
        return ans.toString();
        
    }
}


// 

// Wiggle Sort - 1

public static void swap(int[] arr, int i, int j){
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
  }
  public static void wiggleSort(int[] arr) {
    int n = arr.length;
    for(int i=0; i<n-1; i++){
        if((i%2 == 0) && (arr[i] >= arr[i+1])){
            swap(arr, i, i+1);
        }else if((i%2 == 1) && (arr[i] <= arr[i+1])){
            swap(arr, i, i+1);
        }
    }
  }

  // Wiggle Sort 2

  class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] ans = new int[n];
        int j=1;
        if(n <= 1){
            return; 
        }
        for(int i=n-1; i>=0; i--){
            ans[j] = nums[i];
            j+=2;
            if(j >= n){
                j = 0;
            }
        }
        for(int i=0; i<n; i++){
            nums[i] = ans[i];
        }
    }
}

// Squares of Sorted Array

class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int i=0, j=n-1, idx = n-1;
        int[] ans = new int[n];
        while(i <= j){
            int num1 = nums[i]*nums[i];
            int num2 = nums[j]*nums[j];
            if(num1 <= num2){
                ans[idx--] = num2;
                j--;
            }else{
                ans[idx--] = num1;
                i++;
            }
        }
        return ans;
    }
}

// Min Jumps With +i -i Moves

public static int minJumps(int x) {
        int sum = 0, i = 1;
		// Calculate the sum until sum < x
        while(sum < x){
            sum += i;
            i+=1;
        }
        if(sum == x){
            return i-1; // return i-1 if sum is equal
        }else if((x-sum)%2 == 0){
            return i-1; // return i-1 if the diff between x and sum is even because we need to reverse only one step
        }else if((x-sum+i)%2 == 0){
            return i; // return the jump+1 because here we need to take one more step
        }else{ 
            return i+1; // here we will take 2 more steps
        }
    }

// Sort Array by Parity 

public static void sortArrayByParity(int[] nums) {
        int i = 0;
        int j = 0;
        
        while(i < nums.length) {
            if(nums[i] % 2 == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else {
                i++;
            }
        }
    }

// Best Meeting point

 public static int minTotalDistance(int[][] grid) {
      ArrayList<Integer> xCord = new ArrayList<>();
      ArrayList<Integer> yCord = new ArrayList<>();
      int n = grid.length, m = grid[0].length;
      for(int i=0; i<n; i++){
          for(int j=0; j<m; j++){
              if(grid[i][j] == 1){
                  xCord.add(i);
              }
          }
      }
      
      for(int i=0; i<n; i++){
          for(int j=0; j<m; j++){
              if(grid[i][j] == 1){
                  yCord.add(j);
              }
          }
      }
      
      int x = xCord.get(xCord.size()/2);
      int y = yCord.get(yCord.size()/2);
      
      int dist = 0;
      for(int i=0; i<xCord.size(); i++){
          dist += (Math.abs(xCord.get(i)-x) + 
          Math.abs(yCord.get(i)-y));
      }
      return dist;
  }


 // Number of Subarrays with Bounded Maximum

 class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int ans = 0, prev = 0, si=0;
        for(int ei = 0; ei<nums.length; ei++){
            if(nums[ei] >= left && nums[ei] <= right){
                // New subarrays will be created
                prev = ei-si+1;
                ans+=prev;
            }else if(nums[ei] < left){
                // No new subarray will be created, only the same subarray will be
                // reused by adding the new element in the end
                ans += prev ;
            }else if(nums[ei] > right){
                // Reset 
                si = ei+1;
                prev = 0;
            }
        }
        return ans;
    }
}

// Shortest Unsorted Contiguous SubArray

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int si = 0, ei=-1;
        // finding the index of min from the left
        for(int i=0; i<n; i++){
            if(nums[i] < max){
                ei = i;
           }else{
                max = nums[i];
            }
        }
        // finding the index of max from the right
        for(int i=n-1; i>=0; i--){
            if(nums[i] > min){
                si = i;
            }else{
                min = nums[i];
            }
        }
        return ei-si+1;
    }
}

// Transpose of a Matrix

public static int[][] transpose(int[][] matrix) {
    // write your code here
    int n = matrix.length, m = matrix[0].length;
    int[][] ar = new int[m][n];
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            ar[j][i] = matrix[i][j];
        }
    }
    return ar;
}

// Transpose of NxN matrix

public static int[][] transpose(int[][] ar) {
    // write your code here
    int n = ar.length, m = ar[0].length;
    //int[][] ar = new int[m][n];
    for(int i=0; i<n; i++){
        for(int j=0; j<=i; j++){
            int temp = ar[i][j];
            ar[i][j] = ar[j][i];
            ar[j][i] = temp;
        }
    }
    return ar;
   }


// Rotate an Image by 90Deg

public static int[][] transpose(int[][] ar) {
    // write your code here
    int n = ar.length, m = ar[0].length;
    //int[][] ar = new int[m][n];
    for(int i=0; i<n; i++){
        for(int j=0; j<=i; j++){
            int temp = ar[i][j];
            ar[i][j] = ar[j][i];
            ar[j][i] = temp;
        }
    }
    return ar;
   }
   
   public static void reverseRow(int[][] ar){
       int n = ar.length, m = ar[0].length;
       for(int i=0; i<n; i++){
           for(int k=0,l=m-1; k<=l; k++,l--){
               int temp = ar[i][k];
               ar[i][k] = ar[i][l];
               ar[i][l] = temp;
           }
       }
   }
 
public static int[][] rotate(int[][] matrix) {
    transpose(matrix);
	reverseRow(matrix);
    return matrix;
}


// Reverse Vowels of a String

class Solution {
    public String reverseVowels(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++)
        {
            char ch = s.charAt(i);
            if(ch=='a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
              ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
                sb.append(ch);
        }
        StringBuilder ans = new StringBuilder();
        int j = sb.length()-1;
        for(int i=0; i<s.length(); i++)
        {
            char ch = s.charAt(i);
            if(ch=='a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
              ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
            {
                ans.append(sb.charAt(j));
                j--;
            }
            else
            {
                ans.append(ch);
            }
        }
        return ans.toString();   
    }
}

//  Valid Palindrome -> 

class Solution {
    public boolean isPalindrome(String s, int si, int ei){
        while(si < ei){
            if(s.charAt(si) == s.charAt(ei)){
                si++;
                ei--;
            }else{
                return false;
            }
        }
        return true;
    }
    public boolean validPalindrome(String s) {
        int i=0, j=s.length()-1;
        while(i<=j){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }else{
                return isPalindrome(s, i+1, j) || isPalindrome(s, i, j-1);
            }
        }
        return true;
    }
}


// Car Fleet 

class Solution {
    class pair implements Comparable<pair>{
        int position;
        double time;
        pair(int position, double time){
            this.position = position;
            this.time = time;
        }
        public int compareTo(pair o){
            return this.position - o.position;
        }
    }
    public int carFleet(int target, int[] position, int[] speed) {
        int carFleet = 0, n = position.length;
        pair[] ar = new pair[n];
        for(int i=0; i<n; i++){
            double time = (double) (target-position[i])/ (double) speed[i];
            ar[i] = new pair(position[i], time);
        }
        Arrays.sort(ar);
        for(int i=n-1; i>=1; i--){
            if(ar[i].time >= ar[i-1].time){
                ar[i-1] = ar[i];
            }else{
                carFleet++;
            }
        }
        return carFleet+1;
    }
}


// Target Sum || Count Distinct Pairs with given sum

public static void targetSum(int[] ar, int target){
        int n = ar.length, si = 0, ei = n-1;
        Arrays.sort(ar);
        while(si < ei){
            if(si>0 && ar[si-1] == ar[si]){
                si++;
                continue;
            }
            int sum = ar[si] + ar[ei];
            if(sum == target){
                System.out.println(ar[si] + " " + ar[ei] +" -> "+sum);
                si++;
                ei--;
            }else if(sum < target){
                si++;
            }else {
                ei--;
            }
        }
    }

// 3 Sum || Distinc Pairs

public static void target3Sum(int[] ar, int target){
        int n = ar.length;
        Arrays.sort(ar);
        for(int i=0; i<n-3; i++){
            if(i>0 && ar[i] == ar[i-1]){
                continue;
            }
            int tar = target - ar[i];
            target2Sum(ar, i+1, ar[i], tar);
        }
    }
    public static void target2Sum(int[] ar, int i, int prev, int target){
        int n = ar.length, si = 0, ei = n-1;
        while(si < ei){
            if(si>0 && ar[si-1] == ar[si]){
                si++;
                continue;
            }
            int sum = ar[si] + ar[ei];
            if(sum == target){
                System.out.println(prev +" "+ ar[si] + " " + ar[ei]);
                si++;
                ei--;
            }else if(sum < target){
                si++;
            }else {
                ei--;
            }
        }
    }

//   Minimum Domino Rotations For Equal Row

class Solution {
    public int minDominoRotations(int[] tops, int[] bottom) {
        int num1 = tops[0], num2 = bottom[0], n = tops.length;
        int ans1 = 0, ans2 = 0, ans3 = 0, ans4 = 0;
        for(int i=1; i<n; i++){
            //checking if num1 made a longest ar in top
            if(ans1 != Integer.MAX_VALUE){
                if(num1 == tops[i]){

                }else if(num1 == bottom[i]){
                    ans1++;
                }else{
                    ans1 = Integer.MAX_VALUE;
                }
            }
            // checking if num1 made a longest ar in bottom
            if(ans2 != Integer.MAX_VALUE){
                if(num1 == tops[i]){
                    ans2++;
                }else if(num1 == bottom[i]){

                }else{
                    ans2 = Integer.MAX_VALUE;
                }
            }
            // checking if num2 made a longest ar in top
            if(ans3 != Integer.MAX_VALUE){
                if(num2 == tops[i]){

                }else if(num2 == bottom[i]){
                    ans3++;
                }else{
                    ans3 = Integer.MAX_VALUE;
                }
            }
            // checking if num2 made a longest ar in bottom
            if(ans4 != Integer.MAX_VALUE){
                if(num2 == tops[i]){
                    ans4++;
                }else if(num2 == bottom[i]){

                }else{
                    ans4 = Integer.MAX_VALUE;
                }
            }
        }
        int ans = Math.min(ans1, Math.min(ans2, Math.min(ans3, ans4)));
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

//  Maximum Swap

class Solution {
    public int count(int num){
        int count = 0;
        while(num>0){
            count++;
            num = num/10;
        }
        return count;
    }
    public int[] numToAr(int num){
        int sz = count(num);
        int[] ar = new int[sz];
        int pos = sz-1;
        while(num > 0){
            ar[pos--] = num%10;
            num = num/10;
        }
        return ar;
    }
    public int arToNum(int[] ar){
        int ans = 0, n = ar.length;
        for(int i=0;i<n;i++){
            ans = ans*10 + ar[i];
        }
        return ans;
    }
    public int maximumSwap(int num) {
        int[] ar = numToAr(num);
        
        int n = ar.length;
        int[] leftmax = new int[n];
        leftmax[n-1] = n-1;
        for(int i=n-2;i>=0;i--){
            if(ar[i] > ar[leftmax[i+1]])
                leftmax[i] = i;
            else
                leftmax[i] = leftmax[i+1];
        }
        
        for(int i=0;i<n;i++)
        {
            if(ar[leftmax[i]] > ar[i]){
                int temp = ar[leftmax[i]];
                ar[leftmax[i]] = ar[i];
                ar[i] = temp;
                break;
            }
        }
        int ans = arToNum(ar);
        return ans;
    }
}

// Boats to Save People

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int si=0, ei=people.length-1, ans = 0;
        while(si <= ei){
            int sum = people[si] + people[ei];
            if(sum <= limit){
                si++;
                ei--;
                ans++;
            }else{
                ei--;
                ans++;
            }
        }
        return ans;
    }
}

// Smallest Range Covering Elements from K Lists

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
       int[] res = {-100000,100000};
        int n = nums.size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->
                                                     a[0] - b[0]);
        
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int me = nums.get(i).get(0);
            int[] tba = {me,0,i};
            max = Math.max(max,me);
            pq.add(tba);
        }
        
        while(true){
            int[] min = pq.poll();
            if(res[1]-res[0] > max-min[0]){
                res[1] = max;
                res[0] = min[0];
            }
            
            min[1]++;
            List<Integer> cl = nums.get(min[2]);
            if(min[1] == cl.size()){
                break;
            }
            else{
                min[0] = cl.get(min[1]);
                max = Math.max(max,cl.get(min[1]));
                pq.add(min);
            }
        }
        return res;
    }
}


// First Missing Positive

class Solution {
    public int firstMissingPositive(int[] nums) {
        // 1. Mark Element which are out of range and manage presence of 1
        int n = nums.length;
        boolean onePresent = false;
        for(int i=0; i<n; i++){
            if(nums[i] == 1){
                onePresent = true;
            }
            if(nums[i] < 1 || nums[i] > n){
                nums[i] = 1;
            }
        }
        if(!onePresent){
            return 1; 
        }
        // 2. Map Elements with index
        for(int i=0; i<n; i++){
            int idx = Math.abs(nums[i]);
            nums[idx-1] = -Math.abs(nums[idx-1]);
        }
        // 3. find out missing index
        for(int i=0; i<n; i++){
            if(nums[i] > 0){
                return i+1;
            }
        }
        return n+1;
    }
}

// Pascal Triangle

class Solution {
    public List<Integer> getRow(int row) {
        int i = row;
        long val = 1;
        List<Integer> ans = new ArrayList<>();
        for(int j=0;j<=i;j++)
        {
            ans.add((int)val);
            val = val*(i-j)/(j+1);
        }
        return ans;
    }
}

//  Find All Duplicates in an Array 

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> al = new ArrayList<>();
        for(int n : nums){
            n = Math.abs(n);
            if(nums[n-1] > 0){
                nums[n-1]*=(-1);
            }else{
                al.add(n);
            }
        }
        return al;
    }
}

// find and Replace Pattern

class Solution {
    public boolean isMatching(String word, String pattern){
        if(word.length() != pattern.length()){
            return false;
        }
        int n = word.length();
        HashMap<Character, Character> hm = new HashMap<>();
        HashSet<Character> hs = new HashSet<>();
        for(int i=0; i<n; i++){
            char wch = word.charAt(i);
            char pch = pattern.charAt(i);
            if(hm.containsKey(pch)){
                char rch = hm.get(pch);
                if(rch != wch){
                    return false;
                }else{
                    continue;
                }
            }
            if(hs.contains(wch)){
                return false;
            }
            hm.put(pch, wch);
            hs.add(wch);
        }
        return true;
    }
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for(String word : words){
            if(isMatching(word, pattern)){
                ans.add(word);
            }
        }
        return ans;
    }
}

// Text Justification 


class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int i=0, n=words.length;
        while(i < n){
            int capacity = 0;
            int wc = words[i].length();
            int j = i+1;
            // Caclucation of the words accomodated in a line
            while(j<n && ((wc+1+words[j].length()+capacity) <= maxWidth)){
                capacity++;
                wc += words[j].length();
                j++;
            }
            // Disribution of space
            int space = maxWidth - wc;
            int equal = capacity == 0 ? 0 : space/capacity;
            int unequal = capacity == 0 ? 0 : space%capacity;
            // if this is the last line
            if(j == words.length){
                equal = 1;
                unequal = 0;
            }
            // Addition of spaces
            StringBuilder sb = new StringBuilder();
            for(int k=i; k<j; k++){
                sb.append(words[k]);
                // No spaces are needed at the end of the line
                if(k == j-1){
                    break;
                }
                for(int x=0; x<equal; x++){
                    sb.append(" ");
                }
                if(unequal > 0){
                    sb.append(" "); 
                    unequal--;
                }
            }
            i = j;
            while(sb.length() < maxWidth){
                sb.append(" ");
            }
            ans.add(sb.toString());
        }
        return ans;
    }
}


// Number of Sub-arrays With Odd Sum
class Solution {
    public int numOfSubarrays(int[] arr) {
        long ans = 0;
        long even = 0, odd = 0;
        long sum = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
            if(sum%2 == 0){
                ans += (odd);
                even++;
            }else{
                ans += (even+1);
                odd++;
            }
        }
        return (int)(ans%(1e9+7));
    }
}


// Minimum Number of Moves to Make Palindrome

class Solution {
    private boolean isValidPalindrome(char[] ar){
        int[] temp = new int[26];
        for(int i=0; i<ar.length; i++){
            temp[ar[i]-'a']++;
        } 
        int count = 0;
        for(int i=0; i<26; i++){
            if(temp[i]%2 == 1 && count == 0){
                count = 1;
            }else{
                return false;
            }
        }
        return true;
    }
     public int findKthIndexEqualToLthIndex(char[] ar, int k, int l){
        while(k > l){
            if(ar[k] == ar[l]){
                return k;
            }
            k--;
        }
        return k;
    }
    private void swap(char[] ar, int l){
        if(l+1 < ar.length){
            char temp = ar[l];
            ar[l] = ar[l+1];
            ar[l+1] = temp;
        }
    }
    public int minMovesToMakePalindrome(String s) {
        int n = s.length();
        char[] ar = s.toCharArray();
        if(isValidPalindrome(ar)){
            return -1;
        }
        int l=0, r=ar.length-1;
        int ans = 0;
        while(l < r){
            if(ar[l] == ar[r]){
                l++;
                r--;
            }else{
                int k = r;
                int idx = findKthIndexEqualToLthIndex(ar, k, l);
                if(idx == l){
                    ans++;
                    swap(ar, l);
                }else{
                    while(idx < r){
                        ans++;
                        swap(ar, idx);
                        idx++;
                    }
                    l++;
                    r--;
                }
            }
        }
        return ans;
    }
}


// Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array

private static int getLongestContinuousOnes(int[] ar, int n){
        int p_zero = -1;
        int p_p_zero = -1;
        int ans = 0;
        int ansIdx = 0;
        for(int i=0; i<n; i++){
            if(ar[i] == 0){
                if(i-p_p_zero > ans){
                    ansIdx = p_zero;
                    ans = i-p_p_zero;
                }
                p_p_zero = p_zero;
                p_zero = i;
            }
        }
        if(n-p_p_zero > ans){
            ansIdx = p_zero;
            ans = n-p_p_zero;
        }
        return ansIdx;
    }