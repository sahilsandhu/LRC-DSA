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

    // Friends PAiring Memoization

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
            count=helper(s,i+1,j-1,dp)+2;;
            
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

// Leetcode 115 

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

// Leetcode 72
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
// min distance 02

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

// Leetcode 44
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

// Leetcode 1458

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


// Leetcode 1035

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

// leetcode 132

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

// Leetcode 140 -> WordBreak

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

// LPSS - BackEngg (Longest Palindromic Subsequence)

public static String lpss_backEngg(String s, int si, int ei, int[][] dp){
if(si > ei){
    return si == ei ? .charAt(si) + "" : "" ;
}
 if(s.charAt(si) == s.charAt(ei)){
     return s.charAt(si) + lpss_backEngg(s,) + s.charAt(ei);
 }
 else if(dp[si+1][ei] > dp[si][ei-1]){
     return lpss_backEngg(s, si+1, ei, dp);
 }
 else{
     return lpss_backEngg(s, si, ei-1, dp);
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
        // int ans = 0;
        // for(int i=0;i<dp.length;i++)
        // {
        //    int rans = 
        //   ans = Math.max(rans,ans);
        // }
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
               if(ar[i]<ar[j])
                   dp[i] = Math.max(dp[i],dp[j]+1);
           }
           maxlen = Math.max(dp[i],maxlen);
       }
        return maxlen;
    }
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        // int ans = 0;
        // for(int i=0;i<dp.length;i++)
        // {
        //    int rans = 
        //   ans = Math.max(rans,ans);
        // }
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
        for(int i=0;i<nums.length;i++)
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

//Maximum Sum Increasing Bitonic Subsequence

class Solution
{
	public int maxSumIS(int nums[], int n)  
	{  
	    //code here.
	    int max = 0;
	    int[] dp = new int[n];
	    for(int i=0;i<n;i++)
	    {
	        dp[i] = nums[i];
	        for(int j = i-1;j>=0;j--)
	        {
	            if(nums[j] < nums[i])
	            {
	                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
	            }
	        }
	        max = Math.max(max,dp[i]);
	    }
	    return max;
	}  
}

// Longest Reverse Bitonic Subsequence :: ??????????


// Edit Distance

class Solution {
public:
    int helper(string s1, string s2, int I,int J,vector<vector<int>> &dp)
    {
        for(int i=0;i<=I;i++)
        {
            for(int j=0;j<=J;j++){
               if(i == 0 || j == 0){
               dp[i][j] = i==0?j:i;
               continue;
               }
               int insert = dp[i][j-1];
               int deletee = dp[i-1][j];
               int replace = dp[i-1][j-1];
               if(s1[i-1] == s2[j-1])
               {
               dp[i][j] = replace;
               }
               else
               {
               dp[i][j] = min(min(insert,deletee),replace) + 1;
               } 
            }
        }
        
        return dp[I][J];
    }
    int minDistance(string word1, string word2) {
        vector<vector<int>> dp(word1.size()+1,vector<int>(word2.size()+1,-1)); 
        return helper(word1,word2,word1.size(),word2.size(),dp);
        
    }
};

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

// Stock Span

public static int[] solve(int[] arr){
   int n = arr.length;
   int[] lge = new int[n];
   Stack<Integer> st = new Stack<>();
   st.push(0);
   lge[0] = 1;
   for(int i=1; i<n; i++){
     while(st.size()>0 && arr[i] > arr[st.peek()])
     st.pop();
     if(st.size() > 0)
     lge[i] = i-st.peek();
     else
     lge[i] = i+1;
     st.push(i);
   }
   return lge;
 }


// 


