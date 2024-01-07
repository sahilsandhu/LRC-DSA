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

// Moore's Voting Algorithm : Find an element with occurene more than n/2

class Solution {
    public int majorityElement(int[] nums) {
        int val = nums[0], count = 1;
        for(int i=1; i<nums.length; i++){
            if(val == nums[i]){
                count++;
            }else{
                count--;
            }
            if(count == 0){
                val = nums[i];
                count = 1;
            }
        }
        count = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == val){
                count++;
            }
        }
        return (count >= (nums.length/2)) ? val : -1;
    }
}

// Find an element with occurence more than n/3 ::: Majority Element

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int val1=nums[0], count1=1, val2=0, count2=0;
        for(int i=1; i<nums.length; i++){
            if(val1 == nums[i]){
                count1++;
            }else if(val2 == nums[i]){
                count2++;
            }else{
                if(count1 == 0){
                    val1 = nums[i];
                    count1 = 1;
                }else if(count2 == 0){
                    val2 = nums[i];
                    count2 = 1;
                }else{
                    count1--;
                    count2--;
                }
            }
            
        }
        List<Integer> al = new ArrayList<>();
        count1 = 0; count2=0;
        for(int i=0; i<nums.length; i++){
            if(val1 == nums[i]){
                count1++;
            }
        }
        for(int i=0; i<nums.length; i++){
            if(val2 == nums[i]){
                count2++;
            }
        }
        if(count1 > (nums.length)/3){
            al.add(val1);
        }
        if(count2 > (nums.length)/3 && val1 != val2){
            al.add(val2);
        }
        return al;
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