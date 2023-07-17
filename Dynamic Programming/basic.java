//1.  fibonacci recurssion
public static int helper(int n)
{
    if(n<=1)
    return n;
    return helper(n-1) + helper(n-2);
}

// fibonacci tabulation
public static int helper(int n,int[]dp)
{
    if(n<=1)
    return dp[n] = n;
    if(dp[n] != 0)
    return dp[n];
    return dp[n] = (helper(n-1,dp) + helper(n-2,dp));
}

// fibonacci memoization
public static int helper(int n,int[]dp)
{
    dp[0] = 0;
    dp[1] = 1;
    for(int i=2;i<=n;i++)
    dp[i] = dp[i-1] + dp[i-2];
    return dp[n];
}

// 2. climb stairs Recurssion
public static int helper(int n)
    {
        if(n<0)
        return 0;
        if(n == 0)
        return 1;
        int count = 0;
        count += helper(n-1) + helper(n-2) + helper(n-3);
        return count;
    }

// climb stairs tabulation
public static int helper(int n,int[] dp)
    {
        if(n<0)
        return 0;
        if(n == 0)
        return dp[n] = 1;
        if(dp[n]!= 0)
        return dp[n];
        int count = 0;
        count += helper(n-1,dp) + helper(n-2,dp) + helper(n-3,dp);
        return dp[n] = count;
    }

// climb stairs memoization
public static int helper(int n,int[] dp)
    {
        for(int i=0;i<=n;i++){
        if(i == 0)
        {
         dp[i] = 1;
         continue;
        }
        int count = 0;
        count += (i-1>=0)?dp[i-1]:0;
        count += (i-2>=0)?dp[i-2]:0; 
        count += (i-3>=0)?dp[i-3]:0;
        dp[i] = count;
        }
        return dp[n];
        
    }
 
// 3. Climb stairs with variable jumps Recurssion

public static int helper(int n,int idx,int[] ar)
    {
        if(idx == n)
        return 1;
        
        int count = 0;
        for(int j = 1;j<=ar[idx] && idx+j<=n;j++)
        {
            count+=helper(n,idx+j,ar);
        }
        return count;
    }

//Climb stairs with variable jumps Memoization

 public static int helper(int n,int idx,int[] ar,int[] dp)
    {
        if(idx == n)
        return dp[idx] = 1;
        if(dp[idx] != 0)
        return dp[idx];
        int count = 0;
        for(int j = 1;j<=ar[idx] && idx+j<=n;j++)
        {
            count+=helper(n,idx+j,ar,dp);
        }
        return dp[idx] = count;
    }

// Climb stairs with variable jumps Tabulation
public static int helper(int n,int[] ar,int[] dp)
{
    for(int idx=n;idx>=0;idx--)
    {
        if(idx == n){
         dp[idx] = 1;
        continue;
        }
        int count = 0;
        for(int j = 1;j<=ar[idx] && idx+j<=n;j++)
        {
            count+=dp[idx+j];
        }
        dp[idx] = count;
    }
        return dp[0];
}

// 4. climb stairs with minimum moves Recurssion

public static int helper(int n,int idx,int[] ar)
    {
        if(idx==n)
        return 0;
        int count = 30;
        for(int j = 1;j<=ar[idx] && j+idx<=n; j++)
        {
            count = Math.min(count,helper(n,idx+j,ar)+1);
        }
        return count;
    }

// climb stairs with minimum moves Tabulation
public static int helper(int n,int idx,int[] ar,int[] dp)
    {
        if(idx==n)
        return 0;
        if(dp[idx] != 0)
        return dp[idx];
        int count = 30;
        for(int j = 1;j<=ar[idx] && j+idx<=n; j++)
        {
            count = Math.min(count,helper(n,idx+j,ar,dp)+1);
        }
        return dp[idx] = count;
    }

// climb stairs with minimum moves Memoization
public static int helper(int n,int[] ar,int[] dp)
    {
        for(int idx=n;idx>=0;idx--){
        if(idx==n){
        dp[idx] = 0;
        continue;
        }
        int count = 30;
        for(int j = 1;j<=ar[idx] && j+idx<=n; j++)
        {
            if(idx+j <= n)
            count = Math.min(count, dp[idx+j]+1);
            
        }
        dp[idx] = count;
        }
        return dp[0];
        
    }

// 5. Min cost maze traversal Tabulation

public static int helper(int sr,int sc,int dr,int dc,int[][] ar,int[][] dp)
    {
        if(sr == dr && sc == dc)
        return ar[sr][sc];
        if(sr>dr || sc>dc)
        return Integer.MAX_VALUE;
        if(dp[sr][sc] != 0)
        return dp[sr][sc];
        
        int count = 0;
        count = Math.min(helper(sr+1,sc,dr,dc,ar,dp),helper(sr,sc+1,dr,dc,ar,dp)) + ar[sr][sc]; 
        return dp[sr][sc] = count;
    }

// Min cost maze traversal Meoization

public static int helper(int sr,int sc,int dr,int dc,int[][] ar,int[][] dp)
    {
        for(sr = dr;sr>=0;sr--)
        {
            for(sc = dc;sc>=0;sc--)
            {
                if(sr == dr && sc == dc){
                    dp[sr][sc] = ar[sr][sc];
                    continue;
                }
            int count = 0;
            int a = sr+1>dr? Integer.MAX_VALUE : dp[sr+1][sc];
            int b = sc+1>dc? Integer.MAX_VALUE : dp[sr][sc+1];
            count = Math.min(a,b) + ar[sr][sc]; 
            dp[sr][sc] = count;
            }
        }
    return dp[0][0];
    }

//6. Special Matrix

     public int dirs[][] = {{1,0},{0,1}};
     public int helper(int sr,int sc,int dr,int dc,boolean[][] cells,int[][] dp)
     {
         if(sr == dr && sc == dc && cells[dr][dc] == false)
         return dp[sr][sc] = 1;
         if(dp[sr][sc] != -1)
         return dp[sr][sc];
         int count = 0;
         for(int[] dir : dirs)
         {
            int x = sr + dir[0];
            int y = sc + dir[1];
            
            if(x>=0 && y>=0 && x<=dr && y<= dc && cells[x][y] == false)
            count += helper(x,y,dr,dc,cells,dp)%1000000007;
         }
         return dp[sr][sc] = count%1000000007;
     }
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        boolean[][] ar = new boolean[n+1][m+1];
        for(int[] val : blocked_cells)
        {
            ar[val[0]][val[1]] = true;
        }
        if(ar[1][1] == true)
        return 0;
        int[][] dp = new int[n+1][m+1];
        for(int[] d:dp)
        Arrays.fill(d,-1);
        return helper(1,1,n,m,ar,dp);
    }

// 7. Unique Paths

 int[][] dirs = {{0,1},{1,0}};
    public int helper(int sr,int sc,int dr,int dc,int[][] dp)
    {
        if(sr == dr && sc == dc)
            return dp[sr][sc] =1;
        if(dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        for(int[] dir : dirs)
        {
            int x = sr + dir[0];
            int y = sc + dir[1];
            if(x>=0 && y>=0 && x<=dr && y<=dc)
            {
                count += helper(x,y,dr,dc,dp);
            }
        }
        return dp[sr][sc] = count;
        
    }

// Unique PAth 2
int[][] dirs = {{1,0},{0,1}};
    int uniquePathsWithObstacles_(int sr,int sc,int dr,int dc,int[][] grid,int[][] dp)
    {
        if(sr == dr && sc == dc)
            return 1;
        if(dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        for(int[] dir : dirs)
        {
            int x = sr+dir[0];
            int y = sc+dir[1];
            if(x>=0 && y>=0 && x<=dr && y<=dc && grid[x][y] != 1)
                count+= uniquePathsWithObstacles_(x,y,dr,dc,grid,dp);
        }
        return dp[sr][sc] = count;
        
    }
    public int uniquePathsWithObstacles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        if(grid[0][0] == 1)
            return 0;
        return uniquePathsWithObstacles_(0,0,n-1,m-1,grid,dp);
    }

// leetcode 70
public int climbStairs_(int n,int[] dp)
    {
        if(n<0)
            return 0;
        if(n == 0)
        {
            return dp[n] =  1;
        }
        if(dp[n] != 0)
            return dp[n];
       
        return dp[n] = climbStairs_(n-1,dp) + climbStairs_(n-2,dp);
    }
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return climbStairs_(n,dp);
    }

// leetcode 746

public int minCostClimbingStairs_(int[] cost,int n,int[] dp)
    {
        if(n <=1)
            return dp[n] = cost[n];
        if(dp[n] != 0)
            return dp[n];
        return dp[n] = Math.min(minCostClimbingStairs_(cost,n-1,dp),minCostClimbingStairs_(cost,n-2,dp)) + cost[n];
            
    }
    public int minCostClimbingStairs(int[] cost) {
        int dp[] = new int[cost.length+1];
        return Math.min(minCostClimbingStairs_(cost,cost.length-1,dp),minCostClimbingStairs_(cost,cost.length-2,dp));
    }

// Tabulation

public int minCostClimbingStairs_(int[] cost,int N,int[] dp)
    {
        for(int n=0;n<=N;n++)
        {
        if(n <=1){
             dp[n] = cost[n];
        continue;
        }
        dp[n] = Math.min(dp[n-1] , dp[n-2]) + cost[n];
        }  
        return dp[N];
    }
    public int minCostClimbingStairs(int[] cost) {
        int dp[] = new int[cost.length+1];
        minCostClimbingStairs_(cost,cost.length-1,dp);
        return Math.min(dp[cost.length-1] , dp[cost.length-2]);
    }

// Dice Question : 
// There is a dice which rolls and give a value (1,2,3,4,5,6), we are at position 0, and need to reach the 10.
// Each time the we rolls the dice and take the number of steps which comes up on the dice. Calculate the different
// number of ways with which we reach on the position n (10).

public static int helper(int n)
    {
        if(n<0)
        return 0;
        if(n == 0)
        {
            return 1;
        }
        int count = 0;
        for(int i=1;i<=6;i++)
        {
            count += helper(n-i);
        }
        return count;
    }

// Moves Question:
// There is an array of moves having different values, we can take the jumps which is given in the array. Tells us the 
// numer of ways to reach the top 

// Leetcode 91 Memoizaton
public int numDecodings_(String s,int idx, int[] dp){
        if(idx == s.length())
            return dp[idx] = 1;
        if(s.charAt(idx) == '0')
            return 0;
        if(dp[idx]!= -1)
            return dp[idx];
        int count = 0;
        count += numDecodings_(s,idx+1,dp);
        if(idx < s.length()-1)
        {
            int num = (s.charAt(idx)-'0')*10 + (s.charAt(idx+1)-'0');
            if(num<=26)
                count += numDecodings_(s,idx+2,dp);
        }
        return dp[idx] = count;
    }
    public int numDecodings(String s) {
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp,-1);
        return numDecodings_(s,0,dp);
    }

// Leetcode 91 Tabulation

 public int numDecodings_(String s,int idx, int[] dp){
        for(idx=s.length(); idx>=0; idx--){
        if(idx == s.length()){
             dp[idx] = 1;
            continue;
        }
        if(s.charAt(idx) == '0'){
            dp[idx] =  0;
            continue;
        } 
        int count = 0;
        count += dp[idx+1];
        if(idx < s.length()-1)
        {
            int num = (s.charAt(idx)-'0')*10 + (s.charAt(idx+1)-'0');
            if(num<=26)
                count += dp[idx+2];
        }
        dp[idx] = count;
        }
        return dp[0];
    }
    public int numDecodings(String s) {
        int[] dp = new int[s.length()+1];
        //Arrays.fill(dp,-1);
        return numDecodings_(s,0,dp);
    }

// Optimization with 2 pointers approach

public int numDecodings_(String s,int idx, int[] dp){
        int a = 1, b = 0;
        for(idx=s.length()-1; idx>=0; idx--)
        {
            int sum = 0;
          char ch = s.charAt(idx);
          if(ch!='0')
          {
              sum+=a;
              if(idx<s.length()-1)
              {
                  char ch1 = s.charAt(idx+1);
                  int num = (ch-'0')*10 + (ch1-'0');
                  if(num <= 26)
                      sum+=b;
              }
          }
        b = a;
        a = sum;
        }
        return a;
    }

// decode ways 2 

int mod = (int)1e9+7;
    public long numDecodings_(int idx,String s,long[] dp)
    {
        int n = s.length();
        if(idx == n)
            return dp[idx] = 1;
        
        if(dp[idx]!= -1)
            return dp[idx];
        char ch = s.charAt(idx);
        if(ch == '0')
            return dp[idx] = 0;
        
        long count = 0;

        if(ch == '*')
        {
            count = (count + numDecodings_(idx+1,s,dp) * 9)%mod;
            if(idx<n-1)
            {
               if(s.charAt(idx+1) == '*')
                count = (count + numDecodings_(idx+2,s,dp)*15)%mod; 
                
              else if(s.charAt(idx+1)<='6' && s.charAt(idx+1)>='0')
                count=(count+ numDecodings_(idx+2,s,dp)*2)%mod;  
            
              else if(s.charAt(idx+1)>='7' && s.charAt(idx+1)<='9')
                 count= (count+ numDecodings_(idx+2,s,dp)*1)%mod;
                }
            }
        else
        {
            count = (count + numDecodings_(idx+1,s,dp))%mod;
            
            if(idx<n-1)
            {
                char ch1 = s.charAt(idx+1);
                //char ch = s.charAt(idx);
                if(ch1=='*' && ch=='1')
                {
                    count = (count + 9*numDecodings_(idx+2,s,dp)) % mod;       
                }
                else if(ch1=='*' && ch=='2')
                {
                    count = (count + 6*numDecodings_(idx+2,s,dp)) % mod;
                }
                else if(s.charAt(idx+1)!='*')
                {
                    int num = (s.charAt(idx)-'0')*10 + (s.charAt(idx+1)-'0');
                    if(num<=26)
                        count=(count+numDecodings_(idx+2,s,dp))%mod;
                }
                 
            }
        }
         dp[idx] = count;
        return count;
    }
    public int numDecodings(String s) {
        long[] dp = new long[s.length()+1];
        Arrays.fill(dp,-1);
        return (int)numDecodings_(0,s,dp);
    }

// goldmine 
static int[][] dirs = {{-1,1},{0,1},{1,1}};
    static int maxGold_(int sr,int sc,int dr,int dc,int[][] M,int[][] dp)
    {
        if(sc == dc)
        return dp[sr][sc] = M[sr][sc];
        if(dp[sr][sc]!= -1)
        return dp[sr][sc];
        int gold = 0;
        for(int[] dir : dirs)
        {
            int x = sr+dir[0];
            int y = sc+dir[1];
            if(x>=0 && y>=0 && x<=dr && y<=dc)
            {
                gold = Math.max(gold,maxGold_(x,y,dr,dc,M,dp)+M[sr][sc]);
            }
        }
        return dp[sr][sc] = gold;
    }
    static int maxGold(int n, int m, int M[][])
    {
        int maxgold = 0;
        int[][] dp = new int[n][m];
        for(int[] d: dp)
        Arrays.fill(d,-1);
        for(int i=0;i<n;i++)
        {
            maxgold = Math.max(maxgold,maxGold_(i,0,n-1,m-1,M,dp));
        }
        return maxgold;
    }

// goldmine back engg

public static void maxGold_backEngg(int[][] dp, int sr,int sc,String s)
    {
        if(sc == dp[0].length-1)
        {
            s = s+"("+sr+" "+sc+")";
            System.out.println(s);
            return;
        }
        int idx = -1;
        int maxgold = 0;
        for(int i =0;i<3;i++)
        {
            int x = sr+dirs[i][0];
            int y = sc+dirs[i][1];
            if(x>=0 && y>=0 && x<dp.length && y<dp[0].length && dp[x][y] > maxgold)
            {
                idx = i;
                maxgold = dp[x][y];
            }
        }
        if(idx != -1)
            {
                int r = sr + dirs[i][0], c = sc + dirs[i][1];
                maxGold_backEngg(dp,r,c,s+"("+sr+" "+sc+")->");
            }
        
    }

// Maximum Path sum in a Matrix

static int[][] dirs = {{1,0},{1,-1},{1,1}};
    static int maximumPath_(int sr,int sc,int n,int[][] matrix,int[][] dp)
    {
        if(sr == n-1)
        return dp[sr][sc] = matrix[sr][sc];
        if(dp[sr][sc]!= -1)
        return dp[sr][sc];
        int count = 0;
        for(int[] dir: dirs){
            int x = sr + dir[0];
            int y = sc + dir[1];
            if(x>=0 && y>=0 && x<n && y<n)
            {
                count = Math.max(count,maximumPath_(x,y,n,matrix,dp) + matrix[sr][sc]);
            }
        }
        return dp[sr][sc] = count;
    }
    static int maximumPath(int N, int matrix[][])
    {
        int[][] dp = new int[N][N];
        for(int[] d : dp)
        Arrays.fill(d,-1);
        int maxs = 0;
        for(int i=0;i<N;i++)
        {
            maxs = Math.max(maxs,maximumPath_(0,i,N,matrix,dp));
        }
        return maxs;
        // code here
    }

// Friends Pairing Memoization

 int mod = (int)1e9 + 7;
    public long countFriendsPairings_(int n,long[] dp)
    {
        if(n == 0)
        return dp[n] = 1;
        if(n<0)
        return 0;
        if(dp[n] != -1)
        return dp[n];
        long ans = countFriendsPairings_(n-1,dp)+ (n-1) * countFriendsPairings_(n-2,dp);
        return dp[n] = ans%mod;
    }
    public long countFriendsPairings(int n) 
    { 
       //code here
       long[] dp = new long[n+1];
       Arrays.fill(dp, -1);
       return countFriendsPairings_(n,dp);
    }

// Tabulation  ( 2 pointers approach )

 int mod = (int)1e9 + 7;
    public long countFriendsPairings_(int n,long[] dp)
    {
       long a = 1;
       long b = 1;
       for(int i=2;i<=n;i++)
       {
           long sum = (a + b * (i-1))%mod;
            b  = a;
            a = sum;
       }
       return a;
    }

// Partition Into Subsets

 public static long helper(int n,int k,long[][]dp)
    {
        if(n == k || k==1)
        return 1;
        
        return helper(n-1,k-1,dp) + k*helper(n-1,k,dp);
    }

// Tabulation

public static long helper(int N,int K,long[][]dp)
    {
        for(int n=1;n<=N;n++)
        {
            for(int k=1;k<=K;k++)
            {
            if(n == k || k==1){
                  dp[n][k] = 1; 
                  continue;
            }
        
            dp[n][k] = dp[n-1][k-1] + k*dp[n-1][k];  
            }
        }
        return dp[N][K];
        
    }

// LCS tabulation
 public int lCS_(String s1,int I,String s2,int J,int[][]dp)
    {
        for(int idx1=0;idx1<=I ;idx1++)
        {
            for(int idx2 =0;idx2<=J;idx2++)
            {
           if(idx1 == 0 || idx2 == 0)
           {
               dp[idx1][idx2] = 0;
               continue;
           }
           int count = 0;
           if(s1.charAt(idx1-1) == s2.charAt(idx2-1)){
           count = dp[idx1-1][idx2-1]+1;
        }
        else
            count = Math.max(dp[idx1-1][idx2],dp[idx1][idx2-1]);
                 dp[idx1][idx2] = count;
            }
           
        }
        
        return dp[I][J];
    }
 
//  Longest Palindromic Subsequence  

int helper(String s, int i,int j,int[][] dp)
    {
        if(i>=j)
            return (i==j)?1:0;
        if(dp[i][j] != 0)
            return dp[i][j];
        int count = 0;
        if(s.charAt(i) == s.charAt(j))
        {
            count=helper(s,i+1,j-1,dp)+2;
            
        }
        else
        count = count + Math.max(helper(s,i+1,j,dp) , helper(s,i,j-1,dp));
        dp[i][j] = count;
        return count;
    }

// Longest common substring

int longestCommonSubstr(String s1, String s2, int I, int J){
        // code here
        int[][] dp = new int[I+1][J+1];
        int result = 0;
        for(int idx1=0;idx1<=I ;idx1++)
        {
            for(int idx2 =0;idx2<=J;idx2++)
            {
                if(idx1 == 0 || idx2 == 0)
                {
                   dp[idx1][idx2] = 0;
                   continue;
                }
                if(s1.charAt(idx1-1) == s2.charAt(idx2-1)){
                    dp[idx1][idx2] = dp[idx1-1][idx2-1]+1;
                    result = Math.max(dp[idx1][idx2],result);
                }
                else
                    dp[idx1][idx2] = 0;
            }
        }
        return result;
    }

// Delete Operations for 2 Strings

class Solution {
    public int minDistance_(String s1,String s2,int m,int n,int[][]dp)
    {
        for(int i=0;i<=m;i++)
        {
            for(int j=0;j<=n;j++)
            {
                if(i==0 || j==0)
                {
                    dp[i][j] = 0;
                    continue;
                }
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        return m+n - 2*minDistance_(word1,word2,m,n,dp);
    }
}

// Leetcode 72 // Edit Distance // Option Checking
public int minDistance_(String s1,String s2,int m,int n,int[][]dp)
    {
        
        if(m==0 || n == 0)
            return dp[m][n] = m==0?n:m;
        if(dp[m][n] != -1)
            return dp[m][n];
        int delete = minDistance_(s1,s2,m-1,n,dp); 
        int replace = minDistance_(s1,s2,m-1,n-1,dp);
        int add = minDistance_(s1,s2,m,n-1,dp);
        int count = 0;
        if(s1.charAt(m-1) == s2.charAt(n-1))
        {
           count =  replace;
        }
        else
        {
            count = Math.min(replace,Math.min(add,delete))+1;
        }
        return dp[m][n] = count;
        
    }

// NEW
// Min distance 02

public int minDistance_(String s1,String s2,int m,int n,int[][]dp,int []cost)
    {
        
        if(m==0 || n == 0)
            return dp[m][n] = (m==0?n*cost[1]:m*cost[2];
        if(dp[m][n] != -1)
            return dp[m][n];
        int delete = minDistance_(s1,s2,m-1,n,dp); 
        int replace = minDistance_(s1,s2,m-1,n-1,dp);
        int add = minDistance_(s1,s2,m,n-1,dp);
        int count = 0;
        if(s1.charAt(m-1) == s2.charAt(n-1))
        {
           count =  replace;
        }
        else
        {
            count = Math.min(replace + cost[0], Math.min(add+cost[1],delete + cost[2]))+1;
        }
        return dp[m][n] = count;
        
    }

// Leetcode 115  // Distict Subsequences

public int numDistinct_(String s,String t,int m,int n,int[][] dp)
    {
       if(m<n)
           return 0;
        if(n == 0)
            return 1;
        if(dp[m][n] != -1)
            return dp[m][n];
        int count = 0;
        if(s.charAt(m-1) == t.charAt(n-1))
        {
            count += numDistinct_(s,t,m-1,n-1,dp) + numDistinct_(s,t,m-1,n,dp);
        }
        else
        {
            count += numDistinct_(s,t,m-1,n,dp);
        }
        return dp[m][n] = count;
    }
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int dp[][] = new int[m+1][n+1];
        for(int[] d: dp)
            Arrays.fill(d,-1);
        return numDistinct_(s,t,m,n,dp);
    }


// Leetcode 44 // Wildcard Matching
int isMatch_(String s1,String s2,int l1,int l2,int[][]dp)
    {
       if(l1 == 0 || l2 == 0)
       {
           if(l1 == 0 && l2 == 0)
           {
               return dp[l1][l2] = 1;
           }
           else if(l2 == 1 && s2.charAt(l2-1) == '*')
           {
               return dp[l1][l2] = 1;
           }
           else
           {
               return dp[l1][l2] = 0;
           }
       }
        if(dp[l1][l2] != -1)
            return dp[l1][l2];
        
        
        if(s1.charAt(l1-1) == s2.charAt(l2-1) || s2.charAt(l2-1) == '?')
            return dp[l1][l2] = isMatch_(s1,s2,l1-1,l2-1,dp);
        
        else if(s2.charAt(l2-1) == '*')i
        {
            boolean res = false;
            res = res || (isMatch_(s1,s2,l1-1,l2,dp) == 1); // * string
            res = res || (isMatch_(s1,s2,l1,l2-1,dp) == 1); // empty String
            return dp[l1][l2] = res ? 1 : 0;
        }
        else
        {
            return dp[l1][l2] = 0;
        }
        ///return 0;
    }
    public boolean isMatch(String s, String p) {
    if((s.length() == 0 && p.length() == 0) )
        return true;
    if(p.length()==0)
        return false;
    String str = "";
    str+= p.charAt(0)+"";
    int i = 1;
    while(i<p.length())
    {
        while(i<p.length() && p.charAt(i)=='*' && p.charAt(i-1)=='*')
            i++;
        if(i<p.length())
            str+=p.charAt(i);
        i++;
    }
        int l1 = s.length();
        int l2 = str.length();
        int[][] dp = new int[l1+1][l2+1];
        for(int[] d : dp)
            Arrays.fill(d,-1);
        System.out.println(str);
        return isMatch_(s,str,l1,l2,dp)==1;
    }


// Leetcode 1035 // uncrossed 

public int lCS_(int[] s1,int I,int[] s2,int J,int[][]dp)
    {
        for(int idx1=0;idx1<=I ;idx1++)
        {
            for(int idx2 =0;idx2<=J;idx2++)
            {
           if(idx1 == 0 || idx2 == 0)
           {
               dp[idx1][idx2] = 0;
               continue;
           }
           int count = 0;
           if(s1[idx1-1] == s2[idx2-1]){
           count = dp[idx1-1][idx2-1]+1;
        }
        else
            count = Math.max(dp[idx1-1][idx2],dp[idx1][idx2-1]);
                 dp[idx1][idx2] = count;
            }
           
        }
        
        return dp[I][J];
    }
    
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
    
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        return lCS_(nums1,n,nums2,m,dp);
    }


// Leetcode 1458 / MAX dot product of 2 subsequences

class Solution {
    public int Maximum(int...ar){
        int max = ar[0];
        for(int val : ar){
            max = Math.max(val,max);
        }
        return max;
    }
    public int maxDotProduct_(int[] nums1, int[] nums2, int i, int j, int[][] dp){
        if(i == 0 || j == 0){
            return dp[i][j] = -(int)1e8;
        }
        if(dp[i][j] != -(int)1e9){
            return dp[i][j];
        }
        int curr = nums1[i-1]*nums2[j-1];
        int inc = maxDotProduct_(nums1,nums2,i-1,j-1, dp)+curr;
        int left = maxDotProduct_(nums1,nums2,i-1,j,dp);
        int right = maxDotProduct_(nums1, nums2,i, j-1, dp);
        int ans = Maximum(curr,inc,left,right);
        return dp[i][j] = ans;
    }
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        for(int[] ar : dp){
            Arrays.fill(ar, -(int)1e9);
        }
        return maxDotProduct_(nums1,nums2,n,m,dp);
    }
}


//  Leetcode 05

// longest Palindromic Substring

public String longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        String str= "";
        int max = 0;
        for(int gap = 0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(gap == 0)
                {
                  dp[i][j] = 1;
                  max=1;
                  str = s.substring(i,j+1);
                }
                else if(gap == 1 && s.charAt(i) == s.charAt(j))
                {
                    dp[i][j] = 2;
                    if(dp[i][j] > max)
                    {
                        str = s.substring(i,j+1);
                        max = dp[i][j];
                    }
                }
                else
                {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]!=0)?dp[i+1][j-1]+2 : 0;
                    if(dp[i][j] != 0 && dp[i][j] > max)
                    {
                        max = dp[i][j];
                        str = s.substring(i,j+1);
                    }
                }
            }
        }
        return str;
    }


public String longestPalindrome(String s) {
       int n = s.length();
        int[][] dp = new int[n][n];
        int ans = 0;
        for(int gap = 0; gap<n; gap++){
            for(int i=0,j = gap; j < n; i++, j++){
                if(gap == 0){
                    dp[i][j] = 1;
                }
                else if(gap == 1 && s.charAt(i) == s.charAt(j)){
                    dp[i][j] = 2;
                }
                else{
                    if((s.charAt(i) == s.charAt(j)) && dp[i+1][j
-1] > 0){
                      dp[i][j] = j-i+1;
                    }
                    else{
                        dp[i][j] = 0;
                    }
                }
                ans = Math.max(dp[i][j], ans);
            }
        }
        System.out.println(ans);
        return "";
    }

// leetcode 131
// Palindrome Partitioning

class Solution {
    public List<List<String>> ans;
    public boolean isPalindrome(String s, int si, int ei){
        while(si <= ei){
            if(s.charAt(si) != s.charAt(ei))
                return false;
            si++;
            ei--;
        }
        return true;
    }
    public void partition_(String s, List<String> al, int idx){
        if(idx==s.length()){
            System.out.println(al);
            ans.add(new ArrayList<>(al));
            //System.out.println(ans);
            return;
        }
        for(int i=idx; i<s.length();i++){
            if(isPalindrome(s, idx, i)){
                //System.out.println(s.substring(idx,i+1)+" "+idx+" "+ (i+1));
                al.add(s.substring(idx,i+1));
                partition_(s, al, i+1);
                al.remove(al.size()-1);
            }
        }
    }
    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        if(s==null || s.length()==0) return new ArrayList<>();
        partition_(s, new ArrayList<>(), 0);
        return ans;
    }
}


// leetcode 132
// Palindrome Partitioning 2 

    public int minCut_(String s,boolean[][] dp,int si,int ei,int[] pdp)
    {
        if(dp[si][ei])
            return 0;
        if(pdp[si] != -1)
            return pdp[si];
        int minans = (int)1e8;
        for(int cut = si;cut<=ei;cut++)
        {
            if(dp[si][cut])
            {
                minans = Math.min(minans, minCut_(s,dp,cut+1,ei,pdp)+1);
            }
        }
        return pdp[si] = minans;
    }
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(gap == 0)
                    dp[i][j] = true;
                else if(gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]);
            }
        }
        int[] pdp = new int[n+1];
        Arrays.fill(pdp,-1);
        return minCut_(s,dp,0,n-1,pdp);
    }

// count subsequence of aibjck 

int fun(string &s) {
        long acount = 0;
        long bcount = 0;
        long ccount = 0;
        long empty = 1;
        long mod = 1e9 + 7;
        for(int i=0;i<s.size();i++)
        {
            if(s[i] == 'a')
            {
                acount = (acount + acount + empty)%mod;
            }
            else if(s[i] == 'b')
            {
                bcount = (bcount + (acount + bcount))%mod;
            }
            else if(s[i] == 'c')
            {
                ccount = (ccount + (bcount + ccount))%mod;
            }
        }
        return (int)ccount%mod;
    }

// count subsequence of aibjckdlem // NEW 

// Palindrome Partitioning 3 ::::: Leetcode - 1278

class Solution {
    // this method will store the minimum cuts for each palindromic substring
    public int[][] getMin(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int gap = 1; gap<n; gap++){
            for(int i=0,j=gap; j<n; i++,j++){
                // If the gap is 1 and both characters are same the min cut is 0
                if(gap==1)
                    dp[i][j] = s.charAt(i)==s.charAt(j) ? 0 : 1;
                else{
                // If both characters are same the min cut is the previous cut because it leads to palindromic substring else add 1 to previous cut
                    dp[i][j] = s.charAt(i)==s.charAt(j) ? dp[i+1][j-1] : dp[i+1][j-1]+1;
                }
            }
        }
        return dp;
    }
    public int palindromePartition_(String s, int k, int si, int[][] getMin, int[][] dp){

        if(s.length()-si <= k){
            return dp[si][k] = (s.length()-si == k) ? 0 : (int)1e9;
        } 

        if(k==1)
            dp[si][k] = getMin[si][s.length()-1];

        if(dp[si][k] != -1)
            return dp[si][k];
        
        int ans = Integer.MAX_VALUE;
        //Here we check what is the minimum cuts of palindrome substrings for all the substrings of this string
        for(int i=si; i<s.length(); i++){
            int minChanges = getMin[si][i];
            int minRec = palindromePartition_(s, k-1, i+1, getMin, dp);
            if(minRec != (int)1e9)
            ans = Math.min(ans, minRec + minChanges);
        }

        return dp[si][k] = ans;
    }
    public int palindromePartition(String s, int k) {
        int[][] getMin = getMin(s);
        int n = s.length();
        int[][] dp = new int[n+1][k+1];
        for(int[] d : dp) Arrays.fill(d, -1);
        return palindromePartition_(s, k, 0, getMin, dp);
    }
}

// Leetcode - 139 ::::: Word Break

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int len = 0;
        for(String ss: wordDict)
        {
            set.add(ss);
            len = Math.max(ss.length(),len);
        }
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i=0;i<s.length();i++)
        {
            if(!dp[i]) 
                continue;
            for(int l = 1;l<=len && i+l<=s.length();l++)
            {
               
                    String str = s.substring(i,i+l);
                    if(set.contains(str))
                        dp[i+l] = true;
            }
        }
        return dp[s.length()];
    }
}

// Leetcode 140 -> WordBreak 2 

class Solution {
    public void wordBreak_(String s,boolean[] dp,int si,int ei,ArrayList<String>ans,String str,
                          HashSet<String>set, int mxlen)
    {
        if(si >= ei)
        {  
            ans.add(str.substring(0,str.length()-1));
            return;
        }
        for(int i=1;i<=mxlen && i+si<=ei ;i++)
        {
            if(dp[i+si]==true)
            {
                String sub = s.substring(si,si+i);
                if(set.contains(sub)){
                     wordBreak_(s,dp,si+i,ei,ans,str+sub+" ",set,mxlen);
                }
            }
               
        }
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int maxlen = 0;
        for(String str : wordDict)
        {
            set.add(str);
            maxlen = Math.max(maxlen,str.length());
        }
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        int n = s.length();
        for(int i=0;i<=n;i++)
        {
            if(dp[i] == false)
                continue;
            for(int j=1;j<=maxlen && i+j<=n; j++)
            {
                String str = s.substring(i,i+j);
                if(set.contains(str))
                    dp[i+j] = true;
            }
        }
        ArrayList<String> ans = new ArrayList<>();
        if(dp[n]==false)
            return ans;
        wordBreak_(s,dp,0,n,ans,"",set,maxlen);
        return ans;
    }
}

// Regulare Expression Matching 
// Leetcode ::::  10

class Solution {
    public int isMatch_(String s,String p,int n,int m,int[][] dp)
    {
        if(m == 0 && n==0)
        {
            return dp[n][m] = 1;
        }
        if(m == 0)
            return dp[n][m] = 0;
        if(dp[n][m] != -1)
            return dp[n][m];
        char ch1 = n>0 ? s.charAt(n-1) : '$';
        char ch2 = m>0 ? p.charAt(m-1) : '-';
        if(ch1 != '$' && (ch1 == ch2 || ch2=='.'))
            return dp[n][m] = isMatch_(s,p,n-1,m-1,dp);
        else if(ch2 == '*')
        {
            boolean res = false;
            if(n>0 && m>1 && (p.charAt(m-2) == '.' || p.charAt(m-2) == ch1))
                res = res || isMatch_(s,p,n-1,m,dp)==1;
            res = res || isMatch_(s,p,n,m-2,dp)==1;
            return dp[n][m] = res ? 1 : 0;
        }
        else {
            return dp[n][m] = 0;
        }
        
    }
    public String removeStars(String str)
    {
         if (str.length() == 0)
            return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }
    public boolean isMatch(String s, String p) {
         p = removeStars(p);
        int n = s.length(), m = p.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return isMatch_(s, p, n, m, dp) == 1; 
    }
}

// LPSS - BackEngg (Longest Palindromic Subsequence)

public static String lpss_backEngg(String s, int si, int ei, int[][] dp){
if(si >= ei){
    return si == ei ? s.charAt(si) + "" : "" ;
}
 if(s.charAt(si) == s.charAt(ei)){
     return s.charAt(si) + lpss_backEngg(s,si+1, ei-1) + s.charAt(ei);
 }
 else if(dp[si+1][ei] > dp[si][ei-1]){
     return lpss_backEngg(s, si+1, ei, dp);
 }
 else{
     return lpss_backEngg(s, si, ei-1, dp);
 }
}


// House Robber 2 
// LEETCODE :::: 213

class Solution {
    public int rob_(int[] nums,int si,int ei,int[] dp)
    {
        if(si > ei)
            return 0;
        if(dp[si]!= -1)
            return dp[si];
        int ans = -(int)1e9;
        int inc = nums[si] + rob_(nums,si+2,ei,dp);
        int exc = rob_(nums,si+1,ei,dp);
        ans = Math.max(inc,exc);

       return  dp[si] = ans;
    }
    public int rob(int[] nums) {
        int n =nums.length;
        if(n==0 || n==1)
            return n==1 ? nums[0] : 0;
        
        int[] dp1 = new int[n];
        Arrays.fill(dp1,-1);
        int[] dp2 = new int[n];
        Arrays.fill(dp2,-1);
        return Math.max(rob_(nums,0,n-2,dp1),rob_(nums,1,n-1,dp2));
    }
}

// House Robber
// Leetcode ::::  198 

class Solution {
    public int rob_(int[] nums,int si,int ei,int[] dp)
    {
        if(si > ei)
            return 0;
        if(dp[si]!= -1)
            return dp[si];
        int ans = -(int)1e9;
        int inc = nums[si] + rob_(nums,si+2,ei,dp);
        int exc = rob_(nums,si+1,ei,dp);
        ans = Math.max(inc,exc);

       return  dp[si] = ans;
    }
    public int rob(int[] nums) {
       int n = nums.length;
        if(n==0 || n==1)
            return n==1 ? nums[0] : 0;
        
        int[] dp1 = new int[n];
        Arrays.fill(dp1,-1);
        
        return rob_(nums,0,n-1,dp1); 
    }
}


// Pizza with 3n Slices 

class Solution {
    public int maxSizeSlices_(int[] nums,int si,int ei,int slices,int[][] dp)
    {
        if(si > ei || slices == 0)
            return 0;
        if(dp[ei][slices] != -1)
            return dp[ei][slices];
        int inc = nums[ei] + maxSizeSlices_(nums,si,ei-2,slices-1,dp);
        int exc = maxSizeSlices_(nums,si,ei-1,slices,dp);
        return dp[ei][slices] = Math.max(inc,exc);
    }
    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        int[][] dp1 = new int[n+1][n/3+1];
        for(int[] d : dp1)
            Arrays.fill(d,-1);
        int[][] dp2 = new int[n+1][n/3+1];
        for(int[] d : dp2)
            Arrays.fill(d,-1);
        return Math.max(maxSizeSlices_(slices,0,n-2,n/3,dp1),maxSizeSlices_(slices,1,n-1,n/3,dp2));
    }
}


// LIS  -> Longest Increasing Subsequence

class Solution {
    public int lengthOfLIS_(int[] nums,int[] dp,int ei)
    {
        
        if(dp[ei] != -1)
            return dp[ei];
        int mx = 1;
        for(int i = ei+1;i<dp.length;i++)
        {
            if(nums[i] < nums[ei]){
               mx = Math.max(mx,lengthOfLIS_(nums,dp,i)+1); 
            }
        }
        return dp[ei] = mx;
        
    }
    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        int[] dp = new int[nums.length+1];
        Arrays.fill(dp,-1);
        for(int i=0;i<nums.length;i++)
        {
            ans = Math.max(ans,lengthOfLIS_(nums,dp,i));
        }
        return ans;
    }
}

// LIS Tabulation

// Here we move in opposite direction
int helper(int[] ar,int idx,int[] dp)
    {
        int n = dp.length,maxlen = 0;
       for(int i=0;i<dp.length;i++)
       {
           dp[i] = 1;
           for(int j = i-1;j>=0;j--)
           {
               if(ar[i]>ar[j])
                   dp[i] = Math.max(dp[i],dp[j]+1);
           }
           maxlen = Math.max(dp[i],maxlen);
       }
        return maxlen;
    }
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        return helper(nums,0,dp);
    }

// Longest Decreasing Subsequence

class Solution {
    int helper(int[] ar,int idx,int[] dp)
    {
        int n = dp.length,maxlen = 0;
       for(int i=0;i<dp.length;i++)
       {
           dp[i] = 1;
           for(int j = i-1;j>=0;j--)
           {
               if(ar[i] < ar[j])
                   dp[i] = Math.max(dp[i],dp[j]+1);
           }
           maxlen = Math.max(dp[i],maxlen);
       }
        return maxlen;
    }
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        return helper(nums,0,dp);
    }
}

// LIS R-L
class Solution {
    public int lengthOfLIS_(int[] nums,int[] dp,int ei)
    {
        
        if(dp[ei] != -1)
            return dp[ei];
        int mx = 1;
        for(int i = ei+1;i<dp.length;i++)
        {
            if(nums[i] < nums[ei]){
               mx = Math.max(mx,lengthOfLIS_(nums,dp,i)+1); 
            }
        }
        return dp[ei] = mx;
        
    }
    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        int[] dp = new int[nums.length+1];
        Arrays.fill(dp,-1);
        for(int i=0; i<nums.length; i++)
        {
            ans = Math.max(ans,lengthOfLIS_(nums,dp,i));
        }
        return ans;
    }
}

// longest Bitonic subsequence
class Solution
{
    public void lis(int[] nums,int[] dp)
    {
        int n = nums.length;
        for(int i=0;i<n;i++)
        {
            dp[i] = 1;
            for(int j = i-1;j>=0;j--)
            {
                if(nums[i] > nums[j])
                dp[i] = Math.max(dp[i],dp[j]+1);
            }
        }
    }
    public void lis_RL(int[] nums,int[] dp)
    {
         int n = nums.length;
         for(int i= nums.length-1;i>=0;i--)
         {
             dp[i] = 1;
             for(int j = i+1;j<nums.length;j++)
             {
                 if(nums[j] < nums[i])
                 dp[i] = Math.max(dp[i],dp[j] + 1);
             }
         }
        
    }
    public int LongestBitonicSequence(int[] nums)
    {
        // Code here
        int n = nums.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1,-1);
        Arrays.fill(dp2,-1);
        
        lis(nums,dp1);
        lis_RL(nums,dp2);
        int ans = 0;
        for(int i=0;i<n;i++)
        {
            //System.out.println(dp1[i]+" "+dp2[i]);
            ans = Math.max(ans,dp1[i]+dp2[i]-1);
        }
        return ans;
    }
}

// Maximum Sum Bitonic Subsequence
class Compute {
    
    public static int[] LIS_LR(int[] ar, int n){
        int[] dp = new int[n];
        for(int i=0; i<n; i++){
            dp[i] = ar[i];
            for(int j=i-1; j>=0; j--){
                if(ar[j] < ar[i]){
                    dp[i] = Math.max(dp[i], ar[i] + dp[j]);
                }
            }
        }
        return dp;
    }
    
    public static int[] LIS_RL(int[] ar, int n){
        int[] dp = new int[n];
        for(int i=n-1; i>=0; i--){
            dp[i] = ar[i];
            for(int j=i+1; j<n; j++){
                if(ar[j] < ar[i]){
                    dp[i] = Math.max(dp[i], ar[i] + dp[j]);
                }
            }
        }
        return dp;
    }
    public static int maxSumBS(int arr[], int n)
    {
        int[] left = LIS_LR(arr, n);
        int[] right = LIS_RL(arr, n);
        int ans = 0;
        for(int i=0; i<n; i++){
            ans = Math.max(ans, left[i]+right[i]-arr[i]);
        }
        return ans;
    }
}

//Maximum Sum Increasing Bitonic Subsequence

class Solution
{
    public int LIS_LR(int[] ar, int n){
        int[] dp = new int[n];
        int ans = 0;
        for(int i=0; i<n; i++){
            dp[i] = ar[i];
            for(int j=i-1; j>=0; j--){
                if(ar[j] < ar[i]){
                    dp[i] = Math.max(dp[i], dp[j] + ar[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
	public int maxSumIS(int arr[], int n)  
	{  
	    return LIS_LR(arr, n);
	}  
}

// Longest Reverse Bitonic Subsequence :: ??????????

// Same as above

// Number of LIS

class Solution {
    int helper(int[] nums,int n,int[] dp,int[] count)
    {
        int max = 0;
        int mcount = 0;
        for(int i=0;i<n;i++){
            dp[i] = 1;
            count[i] = 1;
            for(int j = i-1;j>=0;j--)
            {
                if(nums[i] > nums[j]){
                    if(dp[i] < dp[j]+1){
                        dp[i] = dp[j]+1;
                        count[i] = count[j];
                    }
                    else if(dp[i] == dp[j]+1){
                        count[i]+=count[j];
                    }
                }
            }
            if(dp[i] > max){
                max = dp[i];
                mcount=count[i];
            }
            else if(dp[i] == max){
                mcount+=count[i];
            }
        }
        return mcount;
    }
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
         
        return helper(nums,n,dp,count);
    }
}

// Building Bridges
// Sort the array on the basis of north and perform LIS on the basis of South

public class Bridges{
    int n; 
    int s;
    Bridges(int n, int s){
        this.n = n;
        this.s = s;
    }

    public int compareTo(Bridges b){
        if(this.n != b.n){
            return this.s - b.s;
        }
        else{
            return this.s - b.s;
        }
    }
}

int maxBridges(Bridges[] ar, int n)
{       
    Arrays.sort(ar);
    int[] dp = new int[n];
    int ans = 0;
    for(int i=0; i<n; i++){
        dp[i] = 1;
        for(int j=i-1; j>=0; j--){
            if(ar[i].s >= ar[j].s){
                dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
        ans = Math.max(ans, dp[i]);
    }
    return ans;
}


// Russian Dolls

class Solution {
     public int helper(int[][] ar,int[] dp,int n)
    {
        int ans = 0;
        for(int i=0;i<n;i++)
        {
            dp[i] = 1;
            for(int j = i-1;j>=0;j--)
            {
                if(ar[j][0] < ar[i][0] && ar[j][1] < ar[i][1]){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(a,b)->{
            return a[1] - b[1];
        });
        int n = envelopes.length;
        int[] dp = new int[n];
        return helper(envelopes,dp,n);
    }
}


// Permutations

class Solution {
public:
    vector<vector<int>>finalans;
    void helper(vector<int>&nums,vector<int> &vis,vector<int>&ans)
    {
        if(ans.size() == nums.size()) 
        {
            finalans.push_back(ans);
            return;
        }
        for(int i=0;i<nums.size();i++)
        {
            if(vis[i] == 0)
            {
                vis[i] = 1;
                ans.push_back(nums[i]);
                helper(nums,vis,ans);
                ans.pop_back();
                vis[i] = 0 ;
            }
        }
    }
    vector<vector<int>> permute(vector<int>& nums) {
        finalans.clear();
        vector<int> ans;
        vector<int> vis(nums.size(),0);
        helper(nums,vis,ans);
        return finalans;
    }
};

// Combination

class Solution {
    List<List<Integer>> finalAns = new ArrayList<>();
    public void combine_(int[] nums, List<Integer> ans, int k, int pos, int n){
        if(pos == n || k == 0){
            if(k == 0)
                finalAns.add(new ArrayList<>(ans));
            return ;
        }
        for(int i=pos; i<n; i++){
            ans.add(nums[i]);
            combine_(nums, ans, k-1, i+1, n);
            ans.remove(ans.size()-1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for(int i=0; i<n; i++){
            nums[i] = i+1;
        }
        List<Integer> ans = new ArrayList<>();
        combine_(nums, ans, k, 0, n);
        return finalAns;
    }
}


// Subset Sum Problem

import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            int N = Integer.parseInt(read.readLine());
            String input_line[] = read.readLine().trim().split("\\s+");
            int arr[]= new int[N];
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(input_line[i]);
            int sum = Integer.parseInt(read.readLine());

            Solution ob = new Solution();
            if(ob.isSubsetSum(N, arr, sum))
            System.out.println(1);
            else
            System.out.println(0);

            
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution{

    static int isSubsetSum_(int n, int[] arr, int sum, int[][] dp){
        if(n < 0)
            return dp[n][sum] = 0;
        if(n == 0 || sum == 0){
            if(sum == 0)
                return dp[n][sum] = 1;
            return dp[n][sum] = 0;
        } 
        if(dp[n][sum] != -1){
            return dp[n][sum];
        }
        boolean ans = false;
        if(arr[n-1] > sum){
            ans = ans || (isSubsetSum_(n-1, arr, sum, dp)==1);
            return dp[n][sum] = ans == true ? 1 : 0;
        }else{
            ans = ((isSubsetSum_(n-1, arr, sum, dp) == 1) 
            || (isSubsetSum_(n-1, arr, sum - arr[n-1], dp)) == 1);
            return dp[n][sum] = ans == true ? 1 : 0;
        }
    }

    static Boolean isSubsetSum(int N, int arr[], int sum){
        int[][] dp = new int[N+1][sum+1];
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }
        return isSubsetSum_(N, arr, sum, dp)==1;
    }
}

// Target Sum ::: Leetcode 494

class Solution {
    public int findTargetSumWays_(int[] nums, int idx, int sum, int target, HashMap<String, Integer> hm){
        if(idx == nums.length){
            if(sum == target){
                return 1;
            }
            return 0;
        }
        String key = getString(idx, sum);
        if(hm.containsKey(key)){
            return hm.get(key);
        }

        int count = 0;
        count += findTargetSumWays_(nums, idx+1, sum+nums[idx], target, hm);
        count += findTargetSumWays_(nums, idx+1, sum-nums[idx], target, hm);
        hm.put(key, count);
        return count;
    }
    public String getString(int idx, int sum){
        return idx+" "+sum;
    }
    public int findTargetSumWays(int[] nums, int target) {
        HashMap<String, Integer> hm = new HashMap<>();
        return findTargetSumWays_(nums, 0, 0, target, hm);
    }
}


// Combination Sum 4 :::: Leetcode 377
class Solution {
    public int combinationSum4_(int[] nums, int sum, int target, int[] dp){
        if(sum > target){
            return 0;
        }
        if(sum == target){
            return dp[sum] = 1;
        }
        if(dp[sum] != -1){
            return dp[sum];
        }
        int count = 0;
        for(int i=0; i<nums.length; i++){
            count += combinationSum4_(nums, sum + nums[i], target, dp);
        }
        return dp[sum] = count;
    }
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);
        return combinationSum4_(nums, 0, target, dp);
    }
}


// coin change 

class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount + 1];
        for(int i=0; i<=n; i++){
            for(int j=0; j<=amount; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = (j==0) ? 0 : Integer.MAX_VALUE-1;
                }
            }
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=amount; j++){
                if(coins[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coins[i-1]]+1);
                }
            }
        }
        return dp[n][amount] == Integer.MAX_VALUE-1 ? -1 : dp[n][amount];
    }
}

// Minimum Cost For Tickets :::: Leetcode -> 983

class Solution {
    public int mincostTickets(int idx, int[] days, int day, int[] costs, 
    int[] dp){
        if(idx >= days.length){
            return 0;
        }
        if(dp[idx] != -1){
            return dp[idx];
        }
        int p1 = mincostTickets(idx + 1, days, day+1, costs, dp) + costs[0];
        
        for(idx = day; idx < days.length; idx++)
            if(days[idx] >= days[day] + 7) break;
        int p2 = mincostTickets(idx, days, idx, costs, dp) + costs[1];

        for(idx = day; idx < days.length; idx++)
            if(days[idx] >= days[day] + 30) break;
        int p3 = mincostTickets(idx, days, idx, costs, dp) + costs[2];
        return dp[day] = Math.min(p1, Math.min(p2, p3));
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        Arrays.fill(dp, -1);
        return mincostTickets(0, days, 0, costs, dp);
    }
}


// 0-1 Knapsack

class Solution 
{ 
    //Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[], int n) 
    { 
        int[][] dp = new int[n+1][W+1];
       for(int i=0; i<=n; i++){
           for(int j=0; j<=W; j++){
               if(i==0 || j==0){
                   dp[i][j] = 0;
                   continue;
               }
                          
               if(wt[i-1] > j){
                   dp[i][j] = dp[i-1][j];
               }
               else{
                   dp[i][j] = Math.max(dp[i-1][j], val[i-1]+dp[i-1][j-wt[i-1]]);
               }
           }
       }
       return dp[n][W];
    } 
}

// Unbounded Knapsack

class Solution{
    static int knapSack(int n, int W, int val[], int wt[])
    {
        int[][] dp = new int[n+1][W+1];
        for(int i=0; i<=n; i++){
           for(int j=0; j<=W; j++){
               if(i==0 || j==0){
                   dp[i][j] = 0;
                   continue;
               }
                          
               if(wt[i-1] > j){
                   dp[i][j] = dp[i-1][j];
               }
               else{
                   dp[i][j] = Math.max(dp[i-1][j], val[i-1]+dp[i][j-wt[i-1]]);
               }
           }
       }
       return dp[n][W];
    }
}

// 416. Partition Equal Subset Sum || Target Sum

class Solution {
    public int canPartition(int[] nums, int idx, int totalSum , 
    int[][] dp){
        if(totalSum == 0)
            return dp[idx][totalSum] = 1;

        if (idx == 0 && totalSum != 0)
            return 0;

        if(dp[idx][totalSum] != -1){
            return dp[idx][totalSum];
        }
        if(nums[idx-1] > totalSum){
            return canPartition(nums, idx-1, totalSum, dp);
        }
        boolean c1 = canPartition(nums, idx-1, totalSum, dp) == 1;
        boolean c2 = canPartition(nums, idx-1, totalSum-nums[idx-1], dp) == 1;
        return dp[idx][totalSum] = ((c1 || c2) == true) ? 1 : 0; 
    }

    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int totalSum = 0;
        for(int val : nums){
            totalSum += val;
        }
        if(totalSum % 2 != 0){
            return false;
        }
        totalSum = totalSum/2;
        int[][] dp = new int[nums.length+1][totalSum+1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        canPartition(nums, nums.length, totalSum, dp);
        return dp[nums.length][totalSum]==1;
    }
}


// 688. Knight Probability in Chessboard

class Solution {
    int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    public double knightProbability_(int n, int k, int row, int col,double[][][] dp) {
        if(k == 0)
            return dp[k][row][col] = 1.0;
        if(dp[k][row][col] != 0.0)
            return dp[k][row][col];
        
        double count = 0.0;
        for(int i=0;i<8;i++)
        {
            int x = row + xMove[i];
            int y = col + yMove[i];
            
            if(x>=0 && y>=0 && x<n && y<n)
            {
                count += knightProbability_(n,k-1,x,y,dp);
            }    
        }
        return dp[k][row][col] =  count / 8.0;
    }
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k+1][n+1][n+1];
        return knightProbability_(n,k,row,column,dp);
    }
}


//  576. Out of Boundary Paths

class Solution {
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    public long findPaths_(int r, int c, int dr, int dc, int mv, long[][][] dp){
        if(r<0 || c<0 || r>=dr || c>=dc){
            return 1;
        }
        if(mv == 0){
            return 0;
        }
        if(dp[r][c][mv] != -1){
            return dp[r][c][mv];
        }
        int mod = (int)1e9 + 7;
        long count = 0;
        for(int i=0; i<4; i++){
            int x = r + dirs[i][0];
            int y = c + dirs[i][1];
            count += findPaths_(x, y, dr, dc, mv-1, dp)%mod;
        }
        return dp[r][c][mv] = (count%mod);
    }
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int mod = (int)1e9 + 7;
        long[][][] dp = new long[m+1][n+1][maxMove+1];
        for(long[] []row: dp){
            for(long [] col : row){
                Arrays.fill(col, -1);
            }
        }
        int ans = (int)findPaths_(startRow, startColumn, m, n, maxMove, dp)%mod;
        return ans;
    }
}

// Matrix Chain Multiplication

class Solution{
    static int matrixMultiplication(int si, int ei, int[] arr, int[][] dp){
        if(ei-si == 1){
            return dp[si][ei] = 0;
        }
        if(dp[si][ei] != 0){
            return dp[si][ei];
        }
        int minRes = (int)1e9;
        for(int cut = si+1; cut<ei; cut++){
            int leftRes = matrixMultiplication(si, cut, arr, dp);
            int rightRes = matrixMultiplication(cut, ei, arr, dp);
            minRes = Math.min(minRes, leftRes + arr[si]*arr[cut]*arr[ei] + rightRes);
        }
        return dp[si][ei] = minRes;
    }
    static int matrixMultiplication(int N, int arr[])
    {
        int[][] dp = new int[N+1][N+1];
        return matrixMultiplication(0, N-1, arr, dp);
    }
}

// Tabulation of MCM

static int matrixMultiplication(int N, int arr[])
    {
        int[][] dp = new int[N+1][N+1];
        return matrixMultiplication(0, N-1, arr, dp);
    }
    
    static int matrixMultiplication(int SI, int EI, int[] arr, int[][] dp){
        int n = arr.length;
        for(int gap=0; gap<n; gap++){
            for(int si = 0, ei=gap; ei<n; si++,ei++){
                if(ei-si == 1){
                    dp[si][ei] = 0;
                    continue;
                }
                int minRes = (int)1e9;
                for(int cut = si+1; cut<ei; cut++){
                    int leftRes = dp[si][cut];
                    int rightRes = dp[cut][ei];
                    minRes = Math.min(minRes, leftRes + arr[si]*arr[cut]*arr[ei] + rightRes);
                }
                dp[si][ei] = minRes;
            }
        }
        return dp[SI][EI];
    }


// Brackets in Matrix Chain Multiplication 

class Solution{
    static String matrixChainOrder(int p[], int N){
        int[][] dp = new int[N+1][N+1];
        String[][] sdp = new String[N+1][N+1];
        return matrixMultiplication(0, N-1, p, dp, sdp);
    }


static String matrixMultiplication(int SI, int EI, int[] arr, int[][] dp, String[][] sdp){
        int n = arr.length;
        for(int gap=0; gap<n; gap++){
            for(int si = 0, ei=gap; ei<n; si++,ei++){
                if(ei-si == 1){
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char)(si + 'A') + "";
                    continue;
                }
                int minRes = (int)1e9;
                String minStr = "";
                for(int cut = si+1; cut<ei; cut++){
                    int leftRes = dp[si][cut];
                    int rightRes = dp[cut][ei];
                    if(minRes > (leftRes + arr[si]*arr[cut]*arr[ei] + rightRes)){
                        minRes = leftRes + arr[si]*arr[cut]*arr[ei] + rightRes;
                        minStr = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }
                dp[si][ei] = minRes;
                sdp[si][ei] = minStr;
            }
        }
        return sdp[SI][EI];
    }
}

// Follow UP Question :::: Now not only +,* we also have - as an operator now we will have different cases to perform, 
// as (-) * (-) = (+) so we need to find different permutations


// Boolean Parantization ::: 
class Solution{
    public static class pairBoolean{
        long trueCount;
        long falseCount;
        
        pairBoolean(long trueCount, long falseCount){
            this.trueCount = trueCount;
            this.falseCount = falseCount;
        }
        
        pairBoolean(){
        }
    }
    
    static void evaluate(pairBoolean leftR, pairBoolean rightR, char op, pairBoolean res){
        long mod = 1003;
        long totalTF = ((leftR.trueCount + leftR.falseCount) * 
        (rightR.trueCount + rightR.falseCount)) % mod;
        
        long trueRes = 0, falseRes = 0;
        if(op == '|'){
            falseRes = (leftR.falseCount * rightR.falseCount) % mod;
            trueRes = (totalTF - falseRes + mod)%mod;
        }
        else if(op == '&'){
            trueRes = (leftR.trueCount * rightR.trueCount) % mod;
            falseRes = (totalTF - trueRes + mod)%mod;
        }
        else if(op == '^'){
            trueRes = (leftR.falseCount * rightR.trueCount)%mod + 
            (leftR.trueCount * rightR.falseCount)%mod;
            falseRes = (totalTF - trueRes + mod)%mod;
        }
        
        res.falseCount = (res.falseCount + falseRes)%mod;
        res.trueCount = (res.trueCount  + trueRes)%mod;
    }
    
    static pairBoolean countWays_(String s, int si, int ei, pairBoolean[][] dp){
        if(si == ei){
            char ch = s.charAt(si);
            int tr = (ch == 'T') ? 1 : 0;
            int fr = (ch == 'F') ? 1 : 0;
            return new pairBoolean(tr, fr);
        }
        if(dp[si][ei] != null){
            return dp[si][ei];
        }
        
        pairBoolean res = new pairBoolean();
        for(int cut = si+1; cut<ei; cut+=2){
            char op = s.charAt(cut);
            pairBoolean left = countWays_(s, si, cut-1, dp);
            pairBoolean right = countWays_(s, cut+1, ei, dp);
            evaluate(left, right, op, res);
        }
        return dp[si][ei] = res; 
    }
    static int countWays(int N, String S){
        pairBoolean[][] dp = new pairBoolean[N][N];
        pairBoolean res = (countWays_(S, 0, N-1, dp));
        return (int)res.trueCount;
    }
}

// Optimal BST
static int optimalBST(int[] val, int[] freq, int si, int ei, int[][] dp){
    if(dp[si][ei] != 0){
        return dp[si][ei];
    }
    int minCost = (int)1e8;
    int sum = 0;
    for(int cut = si; cut<=ei; cut++){
        int left = si == 0 ? 0 : optimalBST(val, freq, si, cut - 1, dp);
        int right = ei == 0 ? 0 : optimalBST(val, freq, cut+1, ei, dp);
        sum += freq[cut];
        minCost = Math.min(minCost, left + right);
    }
    return dp[si][ei] = minCost + sum;
}


// Minimum Score Triangulation :::: LEETCODE : 1039
class Solution {
    public int minScoreTriangulation_(int[] values, int si, int ei, int[][] dp){
        if(ei-si< 2){
            return dp[si][ei] = 0;
        }
        if(dp[si][ei] != 0){
            return dp[si][ei];
        }
        int ans = Integer.MAX_VALUE;
        for(int cut = si+1; cut<ei; cut++){
            int left = minScoreTriangulation_(values, si, cut, dp);
            int right = minScoreTriangulation_(values, cut, ei, dp);
            int tempAns = left + right + (values[cut]*values[si]*values[ei]);
            ans = Math.min(ans, tempAns);
        }
        return dp[si][ei] = ans;
    }
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        return minScoreTriangulation_(values, 0, n-1, dp);
    }
}

//  Minimum and Maximum values of an expression with * and +

public class pairMM{
    int min;
    int max;
    pairMM(){

    }
    pairMM(int val){
        this.min = min;
    }
}

public static pairMM evaluate(pairMM left, pairMM right, char op){
    pairMM tempObj = new pairMM();
    if(ch == '+'){
        tempObj.min = left.min + right.min;
        tempObj.max = left.max + right.max;
    }
    else if(ch == '*'){
        tempObj.min = left.min * right.min;
        tempObj.max = left.max * right.max;
    }
    return tempObj;
}

public static pairMM minMax(String str, int si, int ei, pairMM[][] dp){
    if(si == ei){
        pairMM obj = new pairMM(str.charAt(si) - '0');
        return dp[si][ei] = obj;
    }
    if(dp[si][ei] != null){
        return dp[si][ei];
    }
    pairMM res = new pairMM();
    for(int cut = si+1; cut<ei; cut+=2){
        pairMM leftRes = minMax(str, si, cut-1, dp);
        pairMM rightRes = minMax(str, cut+1, ei, dp);
        pairMM tempRes = evaluate(leftRes, rightRes, str.charAt(cut));
        res.min = Math.min(leftRes.min, rightRes.min);
        res.max = Math.max(leftRes.max, rightRes.max);
    }
    return dp[si][ei] = res;
}

// Generate All BST :::: LEETCODE : 95

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void generate(List<TreeNode> left,List<TreeNode> right, int root,List<TreeNode> ans)
    {
        if(left.size() != 0 && right.size() != 0)
        {
            for(TreeNode l : left)
            {
                for(TreeNode r : right)
                {
                    TreeNode rn = new TreeNode(root);
                    rn.left = l;
                    rn.right = r;
                    ans.add(rn);
                }
            }
        }
        else if(left.size() != 0)
        {
            for(TreeNode node : left)
            {
                TreeNode rn = new TreeNode(root);
                rn.left = node;
                ans.add(rn);
            }
        }
        else if(right.size() != 0)
        {
            for(TreeNode node : right)
            {
                TreeNode ln = new TreeNode(root);
                ln.right = node;
                ans.add(ln);
            }
        }
        else
        {
            TreeNode node = new TreeNode(root);
            ans.add(node);
        }
    }
    public List<TreeNode> generateTrees(int si,int ei) {
        List<TreeNode> ans = new ArrayList<>();
        if(si == ei)
        {
            TreeNode n = new TreeNode(si);
            ans.add(n);
            return ans;
        }
        for(int cut = si;cut<=ei;cut++)
        {
            List<TreeNode> left = generateTrees(si,cut-1);
            List<TreeNode> right = generateTrees(cut+1,ei);
            generate(left,right,cut,ans);
        }
        return ans;
    }
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
       return generateTrees(1,n);
    }
}


// Largest square submatrix with all 1 :::: Leetcode -> 221

class Solution {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        
        int ans = 0;
        for(int i=n-1; i>=0; i--)
        {
            for(int j=m-1; j>=0; j--)
            {
                if(matrix[i][j] == '0')
                    dp[i][j] = 0;
                
                else if(matrix[i][j] == '1'){
                    if((i==n-1) || (j==m-1))
                        dp[i][j] = 1;
                    else{    
                        dp[i][j] = Math.min(Math.min(dp[i][j+1],dp[i+1][j+1]),dp[i+1][j]) + 1;
                    }
                }
                ans = Math.max(ans,dp[i][j]);
            }
        }
    return ans*ans;
    }
}

// print all paths with minimum jump
public static class pair{
    int idx;
    String psf;
    int jmp;
    
    pair(){
    }

    pair(int idx, String psf, int jmp){
        this.idx = idx;
        this.psf = psf;
        this.jmp = jmp;
    }
}

public static int[] minJumps(int[] val){
    int n = val.length;
    int[] dp = new int[n+1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[n-1] = 0;
    for(int i=n-2; i>=0; i--){
        int steps = val[i];
        int min = Integer.MAX_VALUE;
        for(int j=1; j<=steps && i+j < n; j++){
            if(dp[i+j] != Integer.MAX_VALUE && dp[i+j] < min){
                min = dp[i+j];
            }
        }
        if(dp[i] != 0)
        dp[i] = min + 1;
    }
    return dp;
}

public static String minJumpsPath(int[] val){
    int[] minJmp = minJumps(val);
    Queue<pair> q = new LinkedList<>();
    q.add(new pair(0, "", dp[0]));
    while(q.size() > 0){
        pair tmp = q.remove();
        if(tmp.jmp == 0){
            System.out.println(tmp.psf);
            continue;
        }
        for(int step = 1; step <= val[tmp.idx]; step++){
            if(tmp.idx + step < val.length && tmp.jmp-1 == dp[tmp.idx + step]){
                q.add(new pair(tmp.idx + step, psf + "->" + (tmp.idx + step), tmp.jmp-1));
            }
        }
    }
}

//  print all paths with minimum cost

public static class pair{
    int i, j;
    String path;
    pair(){
    }

    pair(int i, int j, String psf){
        this.i = i;
        this.j = j;
        this.psf = psf;
    }
}

public static void printAllMinPaths(int[][] ar){
    int n = ar.length, m = ar[0].length;
    int[][] dp = new int[n][m];
    for(int i=n-1; i>=0; i--){
        for(int j=m-1; j>=0; j--){
            if(i == n-1 && j == m-1){
                dp[i][j] = ar[i][j];
            }else if(i == m-1){
                dp[i][j] = dp[i][j+1] + ar[i][j];
            }else if(j == n-1){
                dp[i][j] = dp[i+1][j] + ar[i][j];
            }else{
                dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]) + ar[i][j];
            }
        }
    }

    Queue<pair> q = new ArrayDeque<>();
    q.add(new pair(0, 0, ""));
    while(q.size() > 0){
        pair rp = q.remove();
        if(rp.i == n-1 && rp.j == m-1){
            System.out.println(rm.psf);
        }else if(rp.i == n-1){
            q.add(new pair(rp.i, rp.j+1, rp.psf+"H"));
        }else if(rp.j == m-1){
            q.add(new par(rp.i+1, rp.j, rp.psf+"V"));
        }else{
            if(dp[rp.i][rp.j+1] < dp[rp.i+1][rp.j]){
                q.add(new pair(rp.i+1, rp.j, rp.psf + "H"));
            }else if(dp[rp.i+1][rp.j] < dp[rp.i][rp.j+1]){
                q.add(new pair(rp.i, rp.j+1, rp.psf + "V"));
            }else{
                q.add(new pair(rp.i+1, rp.j, rp.psf + "H"));
                q.add(new pair(rp.i, rp.j+1, rp.psf + "V"));
            }
        }
    }
}

// Print All paths with Maximum Gold

// :: right-up (d1), 1 cell right (d2) or 1 cell right-down(d3)
public static void printAllPathMaximumGold(int[][] ar){
    int n = ar.length, m = ar[0].length;
    int[][] dp = new int[n][m];
    for(int j = arr[0].length - 1; j >= 0; j--){
        for(int i = 0; i < arr.length; i++){
            if(j == arr[0].length - 1){
                dp[i][j] = arr[i][j];
            } else if(i == 0){
                dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
            } else if(i == arr.length - 1){
                dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i - 1][j + 1]);
            } else {
                dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], Math.max(dp[i - 1][j + 1], dp[i + 1][j + 1]));
            }
        }
    }

    int max = Integer.MIN_VALUE;
    for(int i = 0; i < dp.length; i++){
        if(dp[i][0] > max){
            max = dp[i][0];
        }
    }
    System.out.println(max);

    ArrayDeque<Pair> que = new ArrayDeque<>();
      
    for(int i = 0; i < dp.length; i++){
        if(dp[i][0] == max){
            que.add(new Pair(i + " ", i, 0));
        }
    }
      
    while(que.size() > 0){
        Pair rem = que.removeFirst();
          
        if(rem.j == arr[0].length - 1){
            System.out.println(rem.psf);
        } else if(rem.i == 0){
            int g = Math.max(dp[rem.i][rem.j + 1], dp[rem.i + 1][rem.j + 1]);
              
              
            if(g == dp[rem.i][rem.j + 1]){
                que.add(new Pair(rem.psf + "d2 ", rem.i, rem.j + 1));
            }
              
            if(g == dp[rem.i + 1][rem.j + 1]){
                que.add(new Pair(rem.psf + "d3 ", rem.i + 1, rem.j + 1));
            }
        } else if(rem.i == arr.length - 1){
            int g = Math.max(dp[rem.i][rem.j + 1], dp[rem.i - 1][rem.j + 1]);
              
            if(g == dp[rem.i - 1][rem.j + 1]){
                que.add(new Pair(rem.psf + "d1 ", rem.i - 1, rem.j + 1));
            }
              
            if(g == dp[rem.i][rem.j + 1]){
                que.add(new Pair(rem.psf + "d2 ", rem.i, rem.j + 1));
            }
          } else {
            int g = Math.max(dp[rem.i][rem.j + 1], Math.max(dp[rem.i - 1][rem.j + 1], dp[rem.i + 1][rem.j + 1]));
              
            if(g == dp[rem.i - 1][rem.j + 1]){
                que.add(new Pair(rem.psf + "d1 ", rem.i - 1, rem.j + 1));
            }
              
            if(g == dp[rem.i][rem.j + 1]){
                que.add(new Pair(rem.psf + "d2 ", rem.i, rem.j + 1));
            }
              
            if(g == dp[rem.i + 1][rem.j + 1]){
                que.add(new Pair(rem.psf + "d3 ", rem.i + 1, rem.j + 1));
            }
        }
    }
}


/// Print All Paths With Target Sum Subset 

public static void printAllPathWithTargetSum(int[] arr, int tar){
    boolean[][] dp = new boolean[arr.length + 1][tar + 1];
    for (int i = 0; i < dp.length; i++) {
        for (int j = 0; j < dp[0].length; j++) {
            if (i == 0 && j == 0) {
                dp[i][j] = true;
            } else if (i == 0) {
                dp[i][j] = false;
            } else if (j == 0) {
                dp[i][j] = true;
            } else {
                if(dp[i - 1][j] == true){
                    dp[i][j] = true;
                } else {
                    int val = arr[i - 1];
                    if (j >= val && dp[i - 1][j - val] == true) {
                        dp[i][j] = true;
                    }
                }
            }
        }
    }

    System.out.println(dp[dp.length - 1][tar]);
    Queue<Pair> q = new ArrayDeque<>();
    q.add(new Pair(n,tar,""));
      

    while (q.size() > 0) {
		Pair rp = q.remove();
		if (rp.i == 0 || rp.j == 0) {
			System.out.println(rp.psf);
		} else {
			boolean exc = dp[rp.i - 1][rp.j];
			boolean inc = rp.j - arr[rp.i - 1] >= 0 ? dp[rp.i - 1][rp.j - arr[rp.i - 1]] : false;
				
			if (inc == true) {
				q.add(new Pair(rp.i - 1, rp.j - arr[rp.i - 1], (rp.i - 1) + " " + rp.psf));
			}
			if (exc == true) {
				q.add(new Pair(rp.i - 1, rp.j, rp.psf));
			}
		}
	}

}

// Print aLL paths in 0-1 Knappsack

public void printAllKnapsackPath(int[] values, int[] wt, int cap, int n){
    int[][] dp = new int[n + 1][cap + 1];
    for (int i = 1; i < dp.length; i++) {
        for(int j = 1; j < dp[0].length; j++){
            int val = values[i - 1];
            int wt = wts[i - 1];

            if(j >= wt){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt] + val);
            } else {
                dp[i][j] = dp[i - 1][j];
            }
        }
    }

    System.out.println(dp[n][cap]);
    Queue<Pair> q = new ArrayDeque<>();
    q.add(new Pair(n,cap,""));
      

    while (q.size() > 0) {
		Pair rp = q.remove();
		if (rp.i == 0 || rp.j == 0) {
			System.out.println(rp.psf);
		} else {
			int exc = dp[rp.i - 1][rp.j];
			int inc = rp.j - wts[rp.i - 1] >= 0 ? (dp[rp.i - 1][rp.j - wts[rp.i - 1]] + values[rp.i - 1]) : Integer.MIN_VALUE;
				
			if (dp[rp.i][rp.j] == inc) {
				q.add(new Pair(rp.i - 1, rp.j - wts[rp.i - 1], (rp.i - 1) + " " + rp.psf));
			}
			if (dp[rp.i][rp.j] == exc) {
				q.add(new Pair(rp.i - 1, rp.j, rp.psf));
			}
		}
	}
}

// Follow up question : 1. we are provided some cost of insertion, deletion
// replacement we need to minimize the cost

#include<bits/stdc++.h>
using namespace std;
int helper(string s1,string s2,int i,int j,int ins,int del,int rep,vector<vector<int>>&dp){
    if(i == 0 || j == 0)
    {
        if(i == 0 && j==0)
        return dp[i][j] = 0;
        else
        return dp[i][j] = (i == 0) ? j*ins : i*del;
    }
    if(dp[i][j] != -1)
    return dp[i][j];
    int insert = helper(s1,s2,i,j-1,ins,del,rep,dp);
    int deletee = helper(s1,s2,i-1,j,ins,del,rep,dp);
    int replace = helper(s1,s2,i-1,j-1,ins,del,rep,dp);
    if(s1[i-1] == s2[j-1])
    dp[i][j] = replace;
    else
    dp[i][j] = min(min(insert+ins,deletee+del),replace+rep);
    return dp[i][j];
}
int main()
{
string s1 = "horse";
string s2 = "ros";
int ins = 2;
int del = 3;
int rep = 4;
vector<vector<int>>dp(s1.size()+1,vector<int>(s2.size()+1,-1));
cout<<helper(s1,s2,s1.size(),s2.size(),ins,del,rep,dp);
}



