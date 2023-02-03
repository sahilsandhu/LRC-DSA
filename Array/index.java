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

// 