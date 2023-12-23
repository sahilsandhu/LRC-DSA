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

// approach 2  // Length of max seq containing only one ->  0

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

