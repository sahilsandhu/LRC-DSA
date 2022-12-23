// has Path ==============  DFS

bool hasPath(vector<Edge>graph[],int src,int dest,vector<int>&vis)
{
    if(src == dest)
    return true;
    vis[src] = 1;
    bool ans = false;
    for(Edge ed : graph[src])
    {
        if(vis[ed.nbr] == 0)
        {
          bool temp = hasPath(graph,ed.nbr,dest,vis);
          if(temp)
          ans = temp;
        }
    }
    return ans;
}

// Print Path

void printPath(vector<Edge> graph[],int src,int dest,vector<int> &vis,string psf)
{
    if(src == dest)
    {
        cout<<psf<<endl;
        return;
    }
    vis[src] = 1;
    for(Edge ed : graph[src])
    {
        if(vis[ed.nbr] == 0)
        {
            printPath(graph,ed.nbr,dest,vis,psf+to_string(ed.nbr));
        }
    }
    vis[src] = false;
    
}

// Rotting Oranges ====================

class Solution {
public:
    int orangesRotting(vector<vector<int>>& grid) {
          int n = grid.size();
        int m = grid[0].size();
        queue<int>q;
        int count = 0 ;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
            {
                if(grid[i][j] == 2)
                    q.push(i*m+j);
                else if(grid[i][j] == 1)
                    count++;
            }
        
        int level = 0;
        if(count == 0)
            return 0;
        vector<vector<int>>dirs= {{1,0},{0,1},{-1,0},{0,-1}};
        while(q.size() > 0)
        {
            int s = q.size();
            while(s-- > 0)
            {
                int t = q.front();
                q.pop();
                int i = t/m;
                int j = t%m;
                for(auto a : dirs)
                {
                    int x = i + a[0];
                    int y = j + a[1];
                    if(x>=0 && y>=0 && x<n && y <m && grid[x][y] == 1)
                    {
                        q.push(x*m+y);
                        grid[x][y] = 2;
                        count--;
                        cout<<count<<endl;
                    }
                }
            }
            level++;
        }
        if(count == 0)
            return level-1;
        else return -1;
    }

};



// Max area of island =========================

class Solution {
public:
    vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    void dfs(vector<vector<int>>&grid,int i,int j,int &count)
    {
        grid[i][j] = 2;
        for(auto a : dirs)
        {
            int x = i+a[0];
            int y = j+a[1];
            if(x>=0 && y>=0 && x<grid.size() && y<grid[0].size() && grid[x][y] == 1)
            {
                count++;
                dfs(grid,x,y,count);
            }
        }
    }
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int m =grid.size();
        int n = grid[0].size();
        int maxArea = ;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j] == 1)
                {
                    int area = 1;
                    dfs(grid,i,j,area);
                    maxArea = max(maxArea,area);
                }
            }
        }
        return maxArea;
    }
};

// Hamiltonian Path and cycle ========================

#include<bits/stdc++.h>

using namespace std;

class Edge {
public:
  int src = 0;
  int nbr = 0;
  int wt = 0;

  Edge(int src, int nbr, int wt) {
    this->src = src;
    this->nbr = nbr;
    this->wt  = wt;
  }
};
int count=0;
void dfs(vector<vector<Edge>> graph,int osrc,int src,vector<int> &vis,string s, int count)
{
    if(count == vis.size()-1)
    {
        int flag = 0;
        for(Edge ed : graph[src])
        {
            if(ed.nbr == osrc)
            {
                flag = 1;
                break;
            }
        }
        if(flag == 1)
        s+="*";
        else
        s+=".";
        
    cout<<s<<endl;
      return;  
    }
    vis[src] = 1;
    for(Edge ed : graph[src])
    {
        if(vis[ed.nbr] == 0)
        {
            dfs(graph,osrc,ed.nbr,vis,s+to_string(ed.nbr),count+1);
        }
    }
    vis[src] = 0;
}

int main() {
  int vtces;
  cin >> vtces;
  vector<vector<Edge>> graph(vtces, vector<Edge>());


  int edges;
  cin >> edges;

  for (int i = 0; i < edges; i++ ) {
    int u, v, w;
    cin >> u >> v >> w;

    graph[u].push_back(Edge(u, v, w));
    graph[v].push_back(Edge(v, u, w));

  }
  int src;
  cin >> src;
  vector<int> vis(vtces);
  int count = 0;
  dfs(graph,src,src,vis,to_string(src)+"",count);
  // write your codes here
  return 0;
}


// Hamiltonian Path ==========================
boolean dfs_(ArrayList<Integer>[] graph, int src, int[] vis, int count){
        if(count == vis.length-1){
           return true;
        }
        vis[src] = 1;
        boolean ans = false;
        for(int nbr : graph[src]){
            if(vis[nbr] == 0){
                ans = ans || dfs_(graph, nbr, vis, count+1);
            } 
        }
        vis[src] = 0;
        return ans;
    }
    boolean check(int n, int m, ArrayList<ArrayList<Integer>> Edges) 
    {
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=0; i<= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<Edges.size();i++){
            int src = Edges.get(i).get(0);
            int dest = Edges.get(i).get(1);
            graph[src].add(dest);
            graph[dest].add(src);
        }
        int[] vis = new int[n+1];
        for(int i=1;i<=n;i++){
            if(vis[i] == 0){
               if(dfs_(graph, i, vis, 1)){
                   return true;
                }
            }
        }
        return false;
        
    }

// is graph Cyclic ===========================

public boolean dfs_(ArrayList<ArrayList<Integer>> graph, int src, int[] vis){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        while(q.size() > 0){
            int rv = q.remove();
            if(vis[rv] == 1){
                return true;
            }
            vis[rv] = 1;
            for(int nbr : graph.get(rv)){
                if(vis[nbr] == 0){
                    q.add(nbr);
                }
            }
        }
        return false;
    }
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int[] vis = new int[V];
        for(int i=0;i<V;i++)
        {
            if(vis[i] == 0){      
                boolean ans = dfs_(adj,i, vis);
                if(ans){
                   return true;
                }
            }
        }
        return false;
    }

// Topological sort =======================================

// topo sort with cycle
void dfs_topo(vector<vector<int>>& graph, int src,vector<int>& ans,vector<int >& vis){
   vis[src]=1;
 
   for(int nbr:graph[src]){
       if(vis[nbr]==0){
           dfs_topo(graph,nbr,ans,vis);
       } else if(vis[nbr]==1){
           cycle=true;
       } else {
           continue;
       }
   }
 
    vis[src]=2;
   ans.push_back(src);
}
 
vector<int> topo_sort(vector<vector<int>>& graph,int N){
    vector<int> vis(N,0);
 
    vector<int> ans;
 
    for(int i=0; i<N; i++){
        if(!vis[i]){
            dfs_topo(graph,i,ans,vis);
        }
    }
 
    reverse(ans.begin(),ans.end());
 
    return ans;
 
}
 
// // topo sort 
void dfs_topo(vector<vector<int>>& graph, int src,vector<int>& ans,vector<bool>& vis){
   vis[src]=true;
 
   for(int nbr:graph[src]){
       if(!vis[nbr]){
           dfs_topo(graph,nbr,ans,vis);
       }
   }
 
   ans.push_back(src);
}
 
vector<int> topo_sort(vector<vector<int>>& graph,int N){
    vector<bool> vis(N,false);
 
    vector<int> ans;
 
    for(int i=0; i<N; i++){
        if(!vis[i]){
            dfs_topo(graph,i,ans,vis);
        }
    }
 
    reverse(ans.begin(),ans.end());
 
    return ans;
 
}
 
// Indegree Concept Topo Sort  // Kahns Algo

static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        // add your code here
        int[] degree = new int[V];
        for(int i=0; i<V; i++)
        {
            for(int val : adj.get(i)){
                degree[val]++;
            }
        }
        int[] vis = new int[V];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0;i<V;i++){
            if(degree[i] == 0){
                q.add(i);
            }
        }
        ArrayList<Integer> al = new ArrayList<>();
        while(q.size() > 0){
            int rv = q.remove();
            al.add(rv);
            for(int nbr : adj.get(rv)){
                degree[nbr]--;
                if(degree[nbr] == 0){
                    q.add(nbr);
                }
            }
        }
        int[] ans = new int[V];
        for(int i=0;i<V;i++){
            ans[i] = al.get(i);
        }
        return ans;
    }



/// Find Eventual safe states ===============

class Solution {
public:
    bool dfs(vector<vector<int>> &graph,vector<int> &vis,int src)
    {
      vis[src] = 1;
        for(int nbr : graph[src])
        {
            if(vis[nbr] == 0)
            {
                if(dfs(graph,vis,nbr))
                return true;
            }
            else if(vis[nbr] == 1)
                return true;
        }
        vis[src] = 2;
        return false;
        
    }
    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        vector<int> vis(graph.size(),0);
        int n= graph.size();
        vector<int> ans;
        for(int i=0;i<n;i++)
        {
            if(vis[i] == 0)
            {
                bool as = dfs(graph,vis,i);
                if(!as) 
                    ans.push_back(i);
                
            }
            else if(vis[i] == 2)
                ans.push_back(i);
        }
        return ans;
    }
};

// Longest Increasing Path in MAtrix

class Solution {
    public int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] degree = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int[] dir : dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if(x>=0 && y>=0 && x<matrix.length && y<matrix[0].length && matrix[x][y] > matrix[i][j]){
                        degree[x][y]++;
                    }
                }
            }
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(degree[i][j] == 0){
                    q.add(i*m+j);
                }
            }
        }
        int ans = 0;
        while(q.size() > 0){
            int sz = q.size();
            while(sz-- > 0){
            int rv = q.remove();
            int i = rv/m;
            int j = rv%m;
            for(int[] dir : dirs){
                int x = i + dir[0];
                int y = j + dir[1];
                if(x>=0 && y>=0 && x<n && y<m && matrix[x][y] > matrix[i][j])                 {
                    degree[x][y]--;
                    if(degree[x][y] == 0)
                       q.add(x*m+y);
                }
            }
            }
            ans++;
        }
        return ans;
    }
}

// Course Schedule 

class Solution {
public:
    bool canFinish(int n, vector<vector<int>>& courses) {
         vector<int> graph[n];
        for(int i=0;i<courses.size();i++)
        {
            graph[courses[i][0]].push_back(courses[i][1]);
            
        }
        vector<int>indegree(n,0);
        for(int i=0;i<n;i++)
        {
            for(int val : graph[i])
            {
                indegree[val]++;
            }
        }
        queue<int>q;
        for(int i=0;i<n;i++)
        {
            if(indegree[i] == 0)
                q.push(i);
        }
        int cnt = 0;
        while(q.size() > 0)
        {
            int t = q.front();
            q.pop();
            for(int u : graph[t])
            {
                indegree[u]--;
                if(indegree[u] == 0)
                    q.push(u);
            }
            cnt++;
        }
        if(cnt!=n)
            return false;
        else
            return true;
       
    }
};

// Course Schedule - 2

class Solution {
    public int[] findOrder(int n, int[][] ar) {
        int[] degree = new int[n];
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<ar.length; i++){
            int src = ar[i][0];
            int dest = ar[i][1];
            graph[dest].add(src);
        }
        
        for(int i=0; i<n; i++){
            for(int nbr : graph[i]){
                degree[nbr]++;
            }
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(degree[i] == 0){
                q.add(i);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        
        while(q.size() > 0){
            int rv = q.remove();
            ans.add(rv);
            for(int nbr : graph[rv]){
                degree[nbr]--;
                if(degree[nbr] == 0){
                    q.add(nbr);
                }
            }
        }
        
        int[] ansAr = new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            ansAr[i] = ans.get(i);
        }
        if(ans.size()==n) return ansAr;
        return new int[0];
    }
}

//  Kosa Raju ===============================

class Solution {
public:
    vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    void dfs(vector<vector<char>> & board, int i, int j)
    {
        board[i][j] = '#';
        for(auto dir : dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x>=0 && y>=0 && x<board.size() && y<board[0].size() && board[x][y] == 'O')
                dfs(board,x,y);
        }
    }
    void solve(vector<vector<char>>& board) {
        
        int m = board.size();
        int n = board[0].size();
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if((i == 0 || j == 0 || i == m-1 || j == n-1) && board[i][j] == 'O')
                {
                    dfs(board,i,j);
                }
            }
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                else if(board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }
};

/// Surrounded Regions ===========================

class Solution {
public:
    vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    void dfs(vector<vector<char>> & board, int i, int j)
    {
        board[i][j] = '#';
        for(auto dir : dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x>=0 && y>=0 && x<board.size() && y<board[0].size() && board[x][y] == 'O')
                dfs(board,x,y);
        }
    }
    void solve(vector<vector<char>>& board) {
        
        int m = board.size();
        int n = board[0].size();
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if((i == 0 || j == 0 || i == m-1 || j == n-1) && board[i][j] == 'O')
                {
                    
                    dfs(board,i,j);
                }
            }
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                else if(board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }
};

/// 1162. As Far from Land as Possible ========================================

class Solution {
public:
    int maxDistance(vector<vector<int>>& grid) {
        queue<int> q;
        int m = grid.size();
        int n = grid[0].size();
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j] == 1)
                    q.push(i*n+j);
            }
        }
        if(q.size() == m*n || q.size() == 0)
            return -1;
        
        int level = 0;
        vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        while(q.size() > 0)
        {
            int s = q.size();
            while(s-- > 0)
            {
            int rp = q.front();
            q.pop();
            int i = rp/n;
            int j = rp%n;
            for(auto dir : dirs)
            {
                int x = i + dir[0]; 
                int y = j + dir[1];
                if(x>=0 && y>=0 && x<m && y<n && grid[x][y] == 0){
                    q.push(x*n+y);
                 grid[x][y] = 1;
                }
            }
            }
            level++;
            
        }
    return level-1;
    }
    
};



/// Walls and Gatess ==============================


class Solution {
public:
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    void wallsAndGates(vector<vector<int>> &rooms) {
        // write your code here
        queue<int> q;
        int n = rooms.size();
        int m = rooms[0].size();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(rooms[i][j] == 0)
                q.push(i*m+j);
            }
        }
        int level = 1;
        vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        while(q.size()>0)
        {
            int s = q.size();
            while(s-- > 0)
            {
              int rv = q.front();
              q.pop();
              int i = rv/m;
              int j = rv%m;
              for(auto dir : dirs)
              {
                  int x = i + dir[0];
                  int y = j + dir[1];
                  if(x>=0 && y>=0 && x<n && y<m && rooms[x][y] == 2147483647)
                  {
                      rooms[x][y] = level;
                      q.push(x*m + y);
                  }
              }
            }
            level++;
        }
    }
};



// Number of Enclaves :::::::::::::: SAME (DFS)

class Solution {
public:
    
    void dfs(vector<vector<int>> &grid,int i,int j,vector<vector<int>> &dirs)
    {
        if(i<0 || j<0 || i==grid.size() || j==grid[0].size() || grid[i][j] == 0)
            return;
        
        grid[i][j] = 0;
        
        for(int k=0;k<4;k++)
        {
            int r = i+ dirs[k][0];
            int c = j+ dirs[k][1];
            dfs(grid,r,c,dirs);
        }
        
    }
    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int count = 0;
        vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if((i==0 || j == 0 || i == m-1 || j == n-1) && grid[i][j]==1)
                    dfs(grid,i,j,dirs);
            }
        }
        
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j] == 1)
                    count++;
                
            }
        }
        return count;
    }
};


// Keys and Rooms ==================================
class Solution {
public:
    void dfs(vector<vector<int>> &graph, vector<int> &v,int src)
    {
        v[src] = true;
        for(auto a : graph[src])
        {
            if(v[a] == 0)
             dfs(graph,v,a);
        }
    }
    bool canVisitAllRooms(vector<vector<int>>& graph) {
        vector<int> vis(graph.size(),0);
        dfs(graph,vis,0);
        for(int i=0;i<graph.size();i++)
            if(vis[i] == 0)
                return false;
        return true;
    }
};

// 0 1 Matrix =======================================

class Solution {
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat) {
        queue<int> q;
        int n = mat.size();
        int m = mat[0].size();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(mat[i][j] == 0)
                {
                    q.push(i*m+j);
                }
                else
                {
                    mat[i][j] = -1;
                }
            }
        }
        int level = 0;
        vector<vector<int>> dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        while(q.size() > 0)
        {
            int s = q.size();
            while(s-- > 0)
            {
                int t = q.front();
                q.pop();
                int i = t/m;
                int j = t%m;
                for(auto dir : dirs)
                {
                    int x = i + dir[0]; 
                    int y = j + dir[1];
                    if(x>=0 && y>=0 && x<n && y<m && mat[x][y] == -1)
                    {
                        mat[x][y] = level+1;
                        q.push(x*m+y);
                    }
                }
            }
            level++;
        }
        return mat;
    }
};


// Shortest Distance from All Buildings
public class Solution {
    /**
     * @param grid: the 2D grid
     * @return: the shortest distance
     */
      
    public class pair{
        int i;
        int j;
        pair(int i,int j)
        {
            this.i = i;
            this.j = j;
        }
    }
    public void bfs(pair p,int[][] grid, int[][] reach,int[][] dist)
    {
        Queue<pair> q = new ArrayDeque<>();
        q.add(p);
        int level = 1;
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        vis[p.i][p.j] = 1;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while(q.size() > 0)
        {
            int s = q.size();
            while(s-- > 0)
            {
            pair rp = q.remove();
            for(int[] val : dirs)
            {
                int x = val[0] + rp.i;
                int y = val[1] + rp.j;
                if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]==0 && vis[x][y] == 0)
                {
                    vis[x][y] = 1;
                    dist[x][y] += level;
                    q.add(new pair(x,y));
                    reach[x][y]++;
                }
            }
            }
            level++;
        }
    }   

    public int shortestDistance(int[][] grid) {
      int n = grid.length;
      int m =  grid[0].length;
      int[][] reach = new int[n][m];
      int[][] dist = new int[n][m];
      int count = 0;
      for(int i=0;i<n;i++)
      {
          for(int j=0;j<m;j++)
          {
              if(grid[i][j] == 1){
                  count++;
              bfs(new pair(i,j),grid,reach,dist);}
          }
      }

       int minval = Integer.MAX_VALUE;
      for(int i=0;i<n;i++)
      {
          for(int j=0;j<m;j++)
          {
              if(grid[i][j] == 0 && reach[i][j] == count){
                  minval = Math.min(minval,dist[i][j]);
               }
          }
      }
      return minval == Integer.MAX_VALUE?-1:minval;

      
    }   
}


// Word Ladder =============================================

class Solution {
public:
    bool isdiff(string a,string b)
    {
        if(a.size() != b.size())
            return false;
        int count =0;
        for(int i=0;i<a.size();i++)
        {
            if(a[i] != b[i])
                count++;
            if(count > 1)
                return false;
        }
        return true;
    }
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        int n = wordList.size();
        queue<string> q;
        q.push(beginWord);
        int level = 1;
        vector<bool> vis(n,false);
        while(q.size() > 0)
        {
            int s = q.size();
            while(s-- > 0)
            {
               string val = q.front();
                q.pop();
                for(int i=0;i<n;i++)
                {
                    if(isdiff(val,wordList[i]))
                    {
                        if(vis[i] == false){
                        if(wordList[i] == endWord)
                        {
                            return level+1;
                        }
                        vis[i] = true;
                        q.push(wordList[i]);
                    }
                    }
                }
            }
            level++;
        }
        return 0;
    }
};


// Word Ladder - 2 ========================================

class Solution {
public:
    bool isdiff(string a, string b)
    {
int c = 0;
        int i = 0;
        while(i<a.size())
        {
            if(a[i] != b[i])
                c++;
            if(c>1)return false;
            i++;
        }
        return c==1;
    }
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        int n = wordList.size();
        vector<string> v = {beginWord};  
        queue<vector<string>> q;
        q.push(v);
        vector<bool> vis(n,false);
        vector<int> index;
        int count = 0;
        vector<vector<string>> ans;
        while(q.size() > 0)
        {
            int s = q.size();
                       

            while(s-- > 0)
            {
                auto rp = q.front();
                q.pop();
                string word = rp[rp.size()-1];
                if(word == endWord)
                    return ans;
                for(int i=0;i<n;i++)
                {
                    if(vis[i] == false) ///////
                    {
                      if(isdiff(wordList[i],word)) 
                        { 
                           rp.push_back(wordList[i]);
                           index.push_back(i);
                            if(wordList[i] == endWord)
                            {
                                if(count == 0)
                                {
                                   count = rp.size();
                                   ans.push_back(rp);
                                   index.pop_back();
                                    
                                }else if(count >= rp.size())
                                {
                                   ans.push_back(rp);
                                    index.pop_back();
                                        
                                }
                            }
                          q.push(rp);
                          rp.pop_back();
                        }
                    }
                }
            }
            
            int i = 0;
            while(i < index.size())
            {
                vis[index[i]] = true;
                i++;
            }
        }
        return ans;
    }
};


//  Minimum Genetic Mutation ===============================

class Solution {
public:
     bool isonediff(string a, string b)
    {
int c = 0;
        int i = 0;
        while(i<a.size())
        {
            if(a[i] != b[i])
                c++;
            if(c>1)return false;
            i++;
        }
        return c==1;
    }
    int minMutation(string start, string end, vector<string>& bank) {
        int level = 1;
        int n = bank.size();
        queue<string> q;
        q.push(start);
        vector<int> vis(bank.size(),0);
        while(q.size() > 0)
        {
            int s = q.size();
            while(s-- > 0)
            {
            string s = q.front();
            q.pop();
            for(int i=0;i<n;i++)
            {
                if(vis[i] == false)
                {
                    if(isonediff(s,bank[i]))
                    {
                        if(bank[i] == end)
                        {
                            return level;
                        }
                        vis[i] = 1;
                        q.push(bank[i]);
                    }
                }
            }
            }
            level++;
        }
        return -1;
    }
};


// Is graph bipartite ======================================


class Solution {
public:
    bool helper(vector<vector<int>> &graph,vector<int> &vis,int src)
    {
        queue<int> q;
        q.push(src);
        int color = 0;
        while(q.size() > 0)
        {
            int s = q.size();
            while(s-- > 0)
            {
                int top = q.front();
                q.pop();
                if(vis[top]!=-1 && vis[top] != color)
                {
                    return true;
                }
                vis[top] = color;
                
               for(int nbr : graph[top])
               {
                   if(vis[nbr] == -1)
                       q.push(nbr);
               }
            }
            color  = (color+1)%2;
        } 
        return false;
    }
    
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int>vis(n,-1);
        for(int i=0;i<n;i++){
            if(vis[i]==-1)
        if(helper(graph,vis,i)) return false;}
        return true;
    }
};


// Journey to the moon =========================+

int dfs(vector<vector<int>> &graph,vector<bool> &vis,int src)
{
    int size = 0;
    vis[src] = true;
    for(auto nbr : graph[src])
    if(vis[nbr] == false)
    {
        size += dfs(graph,vis,nbr);
    }
    
    return size+1;
}
long long journeyToMoon(int n, vector<vector<int>> edge) {
vector<vector<int>>graph(n);
for(auto ed : edge)
{
    int u = ed[0];
    int v = ed[1];
graph[u].push_back(v);
graph[v].push_back(u);
}
vector<bool> vis(n,false);
vector<int> si;
for(int i=0;i<n;i++)
if(vis[i] == false){
    int size = dfs(graph,vis,i);
    si.push_back(size);
}
long long ans = 0;
long long cans = si[0];
for(int i=1;i<si.size();i++)
{
    ans+=(cans*si[i]);
    cans+=si[i];
}
return ans;

}

// ISland Perimeter =====================

class Solution {
public:
    int islandPerimeter(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();
        //vector<vector<int>> vis(n,vector<int>(m));
        vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        int ans = 0;
        for(int i=0;i<n;i++)
        {
            for(int  j=0;j<m;j++)
            {
                int count= 0;
                if(grid[i][j] == 1)
                {
                    for(auto dir : dirs)
                    {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1)
                        {
                            count++;
                        }
                    }
                ans+=(4-count);
                }
            }
        }
        return ans;
    }
};


// Redundant Connection ==================================

class Solution {
public:
    
    vector<int> parent;
    vector<int> size;
    int findParent(int u )
    { if(parent[u] == u)
        return u;
     else 
         return parent[u] = findParent(parent[u]);
        
    }
    void merge(int u, int v)
    {
        if(size[u] > size[v])
        {
            parent[v] = u;
            size[u] += size[v];
        }
        else
        {
            parent[u] = v;
            size[v] += size[u];
        }
    }
    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
          int n = edges.size();
        parent.resize(n+1);
        size.resize(n+1);
        for(int i=0;i<n;i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
        int x = -1,y = -1;
        for(auto ed : edges)
        {
            int u = ed[0];
            int v = ed[1];
            int par1 = findParent(u);
            int par2 = findParent(v);
            if(par1 == par2)
            {
                x = u;
                y = v;
            }
            else
            {
                merge(par1,par2);
          }
        }
        vector<int> ans = {x,y};
        return ans;
    }
};

// Bus Routes ===========================

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<routes[i].length; j++){
                int busStopNo = routes[i][j];
                ArrayList<Integer> al = hm.getOrDefault(busStopNo, new ArrayList<>());
                al.add(i);
                hm.put(busStopNo, al);
            }
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        HashSet<Integer> busStopVis = new HashSet<>();
        HashSet<Integer> busVis = new HashSet<>();
        int level = 0;
        q.add(source);
        busStopVis.add(source);
        while(q.size() > 0){
            int sz = q.size();
            while(sz-- > 0){
                int rv = q.remove();
                if(rv == target){
                    return level;
                }
                
                ArrayList<Integer> buses = hm.get(rv);
                for(int bus : buses){
                    if(busVis.contains(bus))
                        continue;
                    
                    int[] arr = routes[bus];
                    for(int busStop : arr){
                        if(busStopVis.contains(busStop))
                            continue;
                        q.add(busStop);
                        busStopVis.add(busStop);
                    }
                    busVis.add(bus);
                }
            }
            level++;
        }
        return -1;
    }
}


// Count server that communicate =============================

class Solution {
public:
    int countServers(vector<vector<int>>& grid) {
       int n = grid.size();
       int m = grid[0].size();
        vector<int> left(n,0);
        vector<int> right(m,0);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                left[i]+=grid[i][j];
            }
        }
        for(int j=0;j<m;j++)
         for(int i=0;i<n;i++)
            {
                right[j] += grid[i][j];
            }
        int count = 0;
         for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j] == 1)
                {
                    if(left[i]>1 || right[j] > 1)
                        count++;
                }
            }
        }
        return count;
        
    }
};
//  Lexicographically Smallest String ==========================

String smallestEquivalentString(String s1, String s2, String base){
int[] par = new int[26];
for(int i=0;i<26;i++){
    par[i] = i;
}
for(int i=0;i<s1.length();i++){
    int p1 = findPar(s1.charAt(i) - 'a');
    int p2 = findPar(s2.charAt(i) - 'a');
    if(p1 != p2){
        if(p1 < p2){
            par[p2] = p1;
        }
        else{
            par[p1] = p2;
        }
    }
}
    String ans = "";
    for(int i=0;i<base.length(); i++){
        int par = finaPar(base.charAt(i) - 'a');
        ans += (par+ 'a');
    }
    return ans;
}
// Similar String groups =======================================

class Solution {
public:
    int countServers(vector<vector<int>>& grid) {
       int n = grid.size();
       int m = grid[0].size();
        vector<int> left(n,0);
        vector<int> right(m,0);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                left[i]+=grid[i][j];
            }
        }
        for(int j=0;j<m;j++)
         for(int i=0;i<n;i++)
            {
                right[j] += grid[i][j];
            }
        int count = 0;
         for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j] == 1)
                {
                    if(left[i]>1 || right[j] > 1)
                        count++;
                }
            }
        }
        return count;
        
    }
};


// Count sub island ================================================

class Solution {
public:
    vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    bool dfs(vector<vector<int>>&grid1, vector<vector<int>>&grid2, int i,int j)
    {
        grid2[i][j] = 0;
        bool ans = true;
        for(auto dir : dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x>=0 && y>=0 && x<grid1.size() && y<grid1[0].size() && grid2[x][y] == 1 && grid1[x][y] == 1)
              ans = dfs(grid1,grid2,x,y) && ans;
        }
        return ans && grid1[i][j] == 1;
    }
    
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int n = grid1.size();
        int m = grid1[0].size();
        int count = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid1[i][j] == 1 && grid2[i][j] == 1)
                {
                   bool ans = dfs(grid1, grid2,i,j);
                    if(ans)
                    count++;
                }
            }
        }
        return count;
    }
};

// Number of island 2

class Solution {
public:
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */

     vector<int> parent;
    int findParent(int u)
    {
         if(parent[u] == u)
         return u;
         return parent[u] = findParent(parent[u]);
    }
    vector<int> numIslands2(int n, int m, vector<Point> &operators) {
        // write your code here
    parent.resize(n*m, -1);
    vector<int> ans;
    vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    int count = 0;
    for(Point op : operators)
    {
        int i = op.x;
        int j = op.y;
        int idx = i*m+j;
        if(parent[idx] != -1)
        {
            ans.push_back(count);
            continue;
        }
        count++;
        parent[idx] = idx;
        for(auto dir : dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x>=0 && y>=0 && x<n && y<m && parent[x*m+y] != -1)
            {
                int par1 = findParent(x*m+y);
                int par2 = findParent(idx);
                if(par1 != par2)
                {
                    parent[par1] = par2;
                    count--;
                }
            }
        }
        ans.push_back(count);
    }
    return ans;
    }
};


// Kruskal's Algorithm =================================================
vector<int> par;
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}
int Kruskal(vector<vector<int>> &edges, int n){
    par.resize(n,-1);
    for(int i=0;i<n;i++){
        par[i] = i;
    }
    
    sort(edges.begin(), edges.end());
    for(auto ed : edges){
        int u = ed[1];
        int v = ed[2];
        int par1 = findParent(u);
        int par2 = findParent(v);
        if(par1 != par2){
            parent[par1] = par2;
            ans += ed[0];
        }
    }
    return ans;
}

// Prims Algorithm ===========================================================

int ans = 0;
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      pq.add(new Pair(0, 0));
      boolean[] vis = new boolean[n+1]; 
      
      while(pq.size() > 0){
          Pair rp = pq.remove();
          if(vis[rp.v] == true){
              continue;
          }
          
          ans += rp.cost;
          vis[rp.v] = true;
          ArrayList<Pair> al = graph.get(rp.v);
          for(Pair p : al){
              if(vis[p.v] == false){
                  pq.add(p);
              }
          }
      }

// 1584. Min Cost to Connect All Points ==========================================

class Solution {
public:
    vector<int> parent;
    int findParent(int u)
    {
        if(parent[u] == u)
            return u;
        return parent[u] = findParent(parent[u]);
    }
    int mst(vector<vector<int>> &edges, int n)
    {
        parent.resize(n,-1);
        for(int i=0;i<n;i++)
            parent[i] = i;
        //queue<int>q;finipa
        int ans = 0;
        sort(edges.begin(),edges.end());
        for(auto ed : edges)
        {
            int u= ed[1]; 
            int v= ed[2];
            int par1 = findParent(u);
            int par2 = findParent(v);
            if(par1 != par2)
            {
                parent[par1] = par2; 
                ans+=ed[0];
            }
        }
        return ans;
        
    }
    int minCostConnectPoints(vector<vector<int>>& pt) {
        vector<vector<int>> edges;
        int n = pt.size();
        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                int x1 = pt[i][0];
                int y1 = pt[i][1];
                int x2 = pt[j][0];
                int y2 = pt[j][1];
                int ans = abs(x1 - x2) + abs(y1 - y2);
                edges.push_back({ans,i,j});
            }    
        }
        return mst(edges,n);
    }
};


// optimize water distribution ===============================================

#include<bits/stdc++.h>
using namespace std;

vector<int> par;
vector<vector<int>> dir{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
int findPar(int u)
{
    return par[u] == u ? u : (par[u] = findPar(par[u]));
}

int minCostToSupplyWater(int n, vector<int>&wells, vector<vector<int>>& pipes){
    for (int i = 0; i < wells.size(); i++)
    {
        pipes.push_back({0, i + 1, wells[i]});
    }

    sort(pipes.begin(), pipes.end(), [](vector<int> &a, vector<int> &b)
         { return a[2] < b[2]; });

    for (int i = 0; i <= n; i++)
        par.push_back(i);

    int cost = 0;
    for (vector<int> &e : pipes)
    {
        int u = e[0], v = e[1], w = e[2];
        int p1 = findPar(u), p2 = findPar(v);
        if (p1 != p2)
        {
            par[p1] = p2;
            cost += w;
        }
    }

    return cost;
}


// connecting cities with minimum costs  ===================================

#include<algorithm>
vector<int> parent;        // Write your code here.
int findParent(int u)
{
    if(u == parent[u])
        return u;
    return parent[u] = findParent(parent[u]);
}

int getMinimumCost(int n, int m, vector<vector<int>> &edges)
{
        sort(edges.begin(),edges.end(),[&](vector<int> a,vector<int> b){
            return a[2] > b[2];
        });
        
        parent.resize(n,0);
        for(int i=0;i<n;i++)
            parent[i] = i;
        
        int comp = n;
        int cost = 0;
        for(auto ed : edges)
        {
            int u = ed[0];
            int v = ed[1];
            int w = ed[2];
            int p1 = findParent(u);
            int p2 = findParent(v);
            if(p1!=p2)
            {
                parent[p1] = p2; 
                cost+=w;
                comp--;
            }  
        }
        return comp==0?cost:-1;
    }
   
	//	Write your code here.

// Satisfiability of equations equation ========================

class Solution {
public:
    vector<int> parent;
    vector<int> size;
    int findParent(int u)
    {
        if(parent[u] == u)
            return u;
        return parent[u] = findParent(parent[u]);
    }
    void merge(int p1,int p2)
    {
        if(size[p1] < size[p2])
        {
            parent[p1] = p2;
            size[p2]+=size[p1];
        }
        else
        {
            parent[p2] = p1;
            size[p1]+=size[p2];
        }
    }
    bool equationsPossible(vector<string>& equations) {
        parent.resize(26,0);
        size.resize(26,0);
        for(int i=0;i<26;i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
        for(auto eq : equations)
        {
            if(eq[1] == '=')
            {
                int u = eq[0]-'a';
                int v = eq[3] - 'a';
                int par1 = findParent(u);
                int par2 = findParent(v);
                if(par1 != par2)
                merge(par1,par2);
            }
        }
        for(auto eq : equations)
        {
            if(eq[1] != '=')
            {
                int u = eq[0]-'a';
                int v = eq[3] - 'a';
                int par1 = findParent(u);
                int par2 = findParent(v);
                if(par1==par2)
                {
                    return false;
                }
            }
        }
        return true;
    }
}; 


// Number of opreations to make network connected ================================

class Solution {
public:
    vector<int> parent;
    int findParent(int u)
    {
        if(parent[u] == u)
            return u;
        return parent[u] = findParent(parent[u]);
    }
    int makeConnected(int n, vector<vector<int>>& connections) {
        parent.resize(n,1);
        for(int i=0;i<n;i++)
            parent[i] = i;
        int cable = 0;
        for(auto val :  connections)
        {
            int u = val[0];
            int v = val[1];
            int par1 = findParent(u);
            int par2 = findParent(v);
            if(par1!=par2)
            {
                parent[par1] = par2;
            }
            else
                cable++;
        }
        
        int nodes = 0;
        for(int i=0;i<n;i++)
        {
            if(parent[i] == i)
               nodes++;
        }
        nodes--;
        return cable>=nodes?nodes:-1;
    }
};

// Minimize Malware Spread =============================

class Solution {
public:
    vector<int> parent;
    vector<int> size;
    int findParent(int u)
    {
        return parent[u]==u ? u : parent[u] = findParent(parent[u]);
    }
    void merge(int a,int b)
    {
        if(size[a] > size[b])
        {
            parent[b] = a;
            size[a]+=size[b];
        }else
        {
            parent[a] = b;
            size[b]+=size[a];
        }
    }
    int minMalwareSpread(vector<vector<int>>& graph, vector<int>& initials) {
        int n = graph.size();
        int m = graph[0].size();
        parent.resize(n,1);
        size.resize(n,1);
        for(int i=0;i<n;i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(graph[i][j] == 1)
                {
                    int p1 = findParent(i);
                    int p2 = findParent(j);
                    if(p1!=p2)
                    {
                        merge(p1,p2);
                    }
                }
            }
        }
        vector<int> count(n);
        for(int val : initials)
        {
            int p = findParent(val);
            count[p]++;
        }
        int maxi = -1;
        int ans = -1;
        for(int val : initials)
        {
            int p = findParent(val);
            if(count[p] == 1 && size[p] >= maxi)
            {
                if(maxi == size[p])
                {
                 ans =  min(val,ans);   
                }
                else
                {
                ans = val;
                }
                maxi = size[p];
            }
        }
        if(ans != -1)
            return ans;
        else
        {
            ans = n+1;
            for(int val: initials)
                ans = min(ans,val);
            return ans;
        }
    }
};


// minimum hamming distance ====================================

class Solution {
    int[] parent;
    
    int findParent(int u)
    {
        if(parent[u] == u)
            return u;
        return parent[u] = findParent(parent[u]);
    }
    
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];
        for(int i=0;i<n;i++)
        {
            parent[i] = i;
        }
        for(var val : allowedSwaps)
        {
            int u = val[0];
            int v = val[1];
            int p1 = findParent(u);
            int p2 = findParent(v);
            if(p1!=p2)
                parent[p1] = p2;
        }
        
        HashMap<Integer, HashMap<Integer,Integer>> hm = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            int root = findParent(i);
            int num = source[i];
            if(!hm.containsKey(root))
            {
                hm.put(root, new HashMap<>());
            }
            HashMap<Integer,Integer> mp = hm.get(root);
            if(mp.containsKey(num))
            {
                mp.put(num,mp.get(num)+1);
            }
            else
            {
                mp.put(num,1);
            }
        }
        int hd = 0;
        for(int i=0;i<n;i++)
        {
            int root = findParent(i);
            int num = target[i];
            HashMap<Integer,Integer>mp = hm.get(root);
            if(!mp.containsKey(num))
            {
                hd++;
                continue;
            }
            if(mp.get(num) <= 0)
                hd++;
            mp.put(num,mp.get(num)-1);
        }
        return hd;
    }
}

// Redundant Connection 2 


//Note : Whenever a tree get converted into the graph with an extra edge 3 cases will occur:
// 1. A Cycle will form
// 2. An Edge will have 2 parent
// 3. A cycle will form and also one edge will have 2 parent.
// Note : we can't use the DSU to find the cycle in a directed graph, but if we are sure that cycle is present in the graph, and there is only one cycle which
// is present. so we can use the DSU to find the last edge due to which the cycle is present.
// 

class Solution {
public:
    vector<int> parent;
    vector<int> size;
    
    int findParent(int u)
    {
        if(parent[u] == u)
            return u;
        return parent[u] = findParent(parent[u]);
    }
    void merge(int u, int v)
    {
        if(size[u] > size[v])
        {
            parent[v] = u;
            size[u]+=size[v];
        }
        else
        {
            parent[u] = v;
            size[v]+=size[u];
        }
    }
    vector<int> findRedundantDirectedConnection(vector<vector<int>>& edges) {
    
        int n = edges.size();
        vector<int> indegree(n+1,-1);
        int blacklist1 = -1;
        int blacklist2 = -1;
        for(int i=0;i<n;i++)
        {
            int u = edges[i][0];
            int v = edges[i][1];
            if(indegree[v] == -1)
               indegree[v] = i;
            else
            {    blacklist1 = i;
                 blacklist2 = indegree[v];
             break;
            }
                
        }
        parent.resize(n+1,0);
        size.resize(n+1,1);
        for(int i=1;i<=n;i++)
            parent[i] = i;
        
        for(int i=0;i<n;i++)
        {
            if(i == blacklist1)
                continue;
            int u = edges[i][0];
            int v = edges[i][1];
            int p1 = findParent(u);
            int p2 = findParent(v);
            if(p1!=p2)
                merge(p1,p2);
            else
            {
              if(blacklist1 == -1)   // case 2 ------ cycle, no 2 parent
                  return edges[i]; 
              else    
              return edges[blacklist2]; // case 3 ----- cycle and 2 parent
            }
        }
        return edges[blacklist1]; // case 1 ------ 2 parent
    }
};

// Smallest String With Swaps

class Solution {
    int[] par;
    int[] size;
    public int findParent(int u){
        if( par[u] == u ) return u;
        return par[u] = findParent(par[u]);
    }
    public void merge(int p1,int p2)
    {
        if(size[p1] < size[p2])
        {
            par[p1] = p2;
            size[p2]+=size[p1];
        }else
        {
            par[p2] = p1;
            size[p1]+=size[p2];   
        }
    }
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        par = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++){
            par[i] = i;
            size[i] = 1;
        }
        for(List<Integer> al : pairs){
            int u = al.get(0);
            int v = al.get(1);
            int p1 = findParent(u);
            int p2 = findParent(v);
            if(p1 != p2){
                merge(p1, p2);
            }
        }
        HashMap<Integer, PriorityQueue<Character>> values = new HashMap<>();
        
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            int p = par[i];
            if(values.containsKey(p)){
                PriorityQueue<Character> pq = values.get(p);
                pq.add(ch);
                values.put(p, pq);
                
            }
            else{
                PriorityQueue<Character> pq = new PriorityQueue<>();
                pq.add(ch);
                values.put(p, pq);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            PriorityQueue<Character> pq = values.get(par[i]);
            sb.append(pq.peek());
            System.out.println(par[i] +" "+ pq.poll());
            values.put(par[i],pq);
        }
        return sb.toString();
    }
}

// Accounts Merge

class Solution {
    int[] parent;
    public int findParent(int u){
        if(parent[u] == u){
            return u;
        }
        return parent[u] = findParent(parent[u]);
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        parent = new int[10001];
        for(int i=0; i<10001; i++){
            parent[i] = i;
        }
        
        HashMap<String, Integer> euid = new HashMap<>(); // email to id
        HashMap<String, String> etn = new HashMap<>();  // email to name
        
        int uid = 0;
        for(List<String> al : accounts){
            String name = "";
            for(String email : al){
                if(name == ""){    // first letter is name for every sentence
                   name = email;
                   continue;
                }
                if(!euid.containsKey(email)){ 
                    euid.put(email, uid);
                    uid++;
                }
                if(!etn.containsKey(email)){
                    etn.put(email, name);
                }
                int eluid = euid.get(al.get(1));
                int cuid = euid.get(email);
                
                int p1 = findParent(eluid);
                int p2 = findParent(cuid);
                if(p1 != p2){
                    parent[p1] = p2;
                }
             }
        }
        
        HashMap<Integer, List<String>> pe = new HashMap<>();
        for(String email : etn.keySet()){
            int cuid = euid.get(email);
            int par = findParent(cuid);
            
            if(pe.containsKey(par)){
                pe.get(par).add(email);
            }
            else{
                pe.put(par, new ArrayList<>());
                pe.get(par).add(email);
            }
        }
        
        List<List<String>> ans = new ArrayList<>();
        for(List<String> emails : pe.values()){
            Collections.sort(emails);
            List<String> tp = new ArrayList<>();
            String email = emails.get(0);
            String name = etn.get(email);
            
            tp.add(name);
            for(String em : emails){
                tp.add(em);
            }
            ans.add(new ArrayList<>(tp));
        }
        return ans;
    }
}

// Most Stones Removed with Same row and col
class Solution {
    int[] par;
    int[] size;
    public int findParent(int u){
        if(par[u] == u){
            return u;
        }
        return par[u] =  findParent(par[u]);
    }
    public void merge(int a, int b){
        if(size[a] > size[b]){
            par[b] = a;
            size[a] += size[b];
        }
        else {
            par[a] = b;
            size[b] += size[a];
        }
    }
    public int removeStones(int[][] stones) {
        par = new int[100005];
        size = new int[100005];
        
        for(int i=0; i<100005; i++){
            par[i] = i;
            size[i] = 1;
        }
        
        for(int[]stone : stones){
            int p1 = findParent(stone[0]);
            int p2 = findParent(stone[1]  + 10001);
            
            if(p1 != p2){
                merge(p1, p2);
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int[] stone : stones){
            int p = findParent(stone[0]);
            set.add(p);
        }
        return stones.length - set.size();
    } 
}

// Rank Transform of a Matrix

class Solution {
    int[] rows; // ab tak ka max in rows
    int[] cols; // ab tak ka max in cols
    public class pair implements Comparable<pair>{
        int r;
        int c;
        int val;
        pair(int r, int c, int val){
            this.r = r;
            this.c = c;
            this.val = val;
        }
        
        public int compareTo(pair o){
            return this.val - o.val;
        }
    }
    
    public int findParent(int u, int[] par){
        if(par[u] < 0){
            return u;
        }
        return par[u] = findParent(par[u], par);
    }
    public void process(ArrayList<pair> al, int[][] mat){
        int n = mat.length;
        int m = mat[0].length;
        
        int[] par = new int[n+m];
        Arrays.fill(par, -1);
        for(pair p : al){
            int i = p.r;
            int j = p.c;
            int p1 = findParent(i, par);
            int p2 = findParent(j+n, par);
            if(p1 != p2){

                int maxRank = Math.min(par[p1], Math.min(par[p2], -Math.max(rows[i], cols[j])-1));
                par[p1] = maxRank;
                par[p2] = p1;
            }
        }
        
        for(pair p : al){
            int i = p.r;
            int j = p.c;
            int pr = findParent(i, par);
            int maxR = -par[pr];
            mat[i][j] = maxR;
            rows[i] = maxR;
            cols[j] = maxR;
        }
    }
    public int[][] matrixRankTransform(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        rows = new int[n];
        cols = new int[m];
        
        pair[] ar = new pair[n*m];
        int k = 0;
        // converting the array into the 1D array
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                ar[k++] = new pair(i, j, mat[i][j]);
            }
        }
        
        // sorting on the basis of val;
        Arrays.sort(ar);
        
        // Processing only the equal element one by one
        ArrayList<pair> al = new ArrayList<>();
        int prev = Integer.MIN_VALUE;
        for(pair p : ar){
            if(p.val != prev){
                process(al, mat);
                prev = p.val;
                al = new ArrayList<>();
            }
            al.add(p);
        }
        process(al, mat);
        return mat;
    }
}

// Minimize Malware Spread 2

class Solution {
    int[] par;
    int[] size;
    public int findParent(int u){
        if(par[u] == u){
            return u;
        }
        return par[u] = findParent(par[u]);
    }
    
    public void merge(int p1, int p2){
        if(size[p1] > size[p2]){
            par[p2] = p1;
            size[p1] += size[p2];
        }
        else{
            par[p1] = p2;
            size[p2] += size[p1];
        }
    }
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        par = new int[n];
        size = new int[n];
        
        for(int i=0;i<n;i++){
            par[i] = i;
            size[i] = 1;
        }
        
        HashSet<Integer> hs = new HashSet<>();
        for(int in : initial){
            hs.add(in);
        }
        
        // creating graphs of graphs, excluding the initials values
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(graph[i][j] == 1 && !hs.contains(i) && !hs.contains(j)){
                    int p1 = findParent(i);
                    int p2 = findParent(j);
                    if(p1 != p2)
                       merge(p1, p2);
                }
            }
        }
        
         // now iterating all over the neighbour nodes of the infected node and finding the parent of them, also checking the count of groups they are forming.
        
        HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
        int[] infected = new int[n];
        for(int u : initial){
            hm.put(u, new HashSet<>());
            for(int j=0; j<n; j++){
                if(graph[u][j] == 1 && u!=j && !hs.contains(j)){
                    int p = findParent(j);
                    if(!hm.get(u).contains(p)){
                        hm.get(u).add(p);
                        infected[p]++;
                    }
                }
            }
        }
        
        int ans = -1;
        int max = -1;
        for(int u : initial){
            HashSet<Integer> pars = hm.get(u);
            int total = 0;
            for(int p : pars){
                if(infected[p] == 1){
                    total += size[p];
                }
            }
            if(total >= max){
                if(total == max){
                    ans = Math.min(ans, u);
                }
                else{
                    ans = u;
                }
                max = total;
            }
        }
       
        if(ans == -1){
            int min = n+1;
            for(int e : initial){
                min = Math.min(min, e);
            }
            return min;
        }
        
        return ans;
    }
}
// Dijkstra Alogrithm

void shortestPath(int s, ArrrayList<ArrayList<Node>> adj, int N){
    int[] dist = new int[N];
    for(int i=0;i<N;i++){
        dist[i] = 100000;
    }
    dist[s] = 0;
    PriorityQueue<Node> pq = new PriorityQueue<Node>(N, new Node());
    pq.add(new Node(s, 0));
    while(pq.size() > 0){
        Node n = pq.poll();
        for(Node it : adj.get(n.getV())){
            if(dist[n.getV()] + it.getW()  < dist[it.getV()]){
                dist[it.getV()] = dist[n.getV()] + it.getW();
                pq.add(new Node(it.getV(), dist[it.getV()]));
            }
        }
    }

    for(int i=0; i<N; i++){
        System.out.println(dist[i] + " ");
    }
}


// Network Delay Time
class Solution {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
    vector<vector<pair<int,int>>> graph(n+1);
    for(auto p : times)
    {
        int u = p[0];
        int v = p[1];
        int wt = p[2];
        graph[u].push_back({v,wt});
    }
    priority_queue<pair<int,int>,vector<pair<int,int>>, greater<pair<int,int>>>pq;
    pq.push({0,k});  // wsf, src
    vector<int> dis(n+1,-1);
    vector<int> vis(n+1,false);
    while(pq.size() > 0)
    {
        auto p = pq.top();
        int wsf = p.first; 
        int u = p.second;
        pq.pop();
        if(vis[u])
        {
            continue;
        }
        vis[u] = true;
        dis[u] = wsf;
        for(auto nbrp : graph[u])
        {
            int wt = nbrp.second;
            int nbr = nbrp.first;
            
            if(!vis[nbr])
            {
                   pq.push({wsf + wt,nbr});
            }
        }
    }
    
        int ans = 0;
        for(int i=1;i<dis.size();i++)
        {
            cout<<dis[i]<<" ";
            if(dis[i] == -1)
                return -1;
            ans = max(ans,dis[i]);
        }
        return ans;
    }
};

//  Parallel Course 2 ======== Topological Sort =========

class Solution {
public:
    int minimumTime(int n, vector<vector<int>>& relations, vector<int>& time) {
        vector<vector<int>> graph(n+1);
        vector<int> maxtime(n+1,0);
        for(auto val : relations)
        {
            graph[val[0]].push_back(val[1]);
        }
        vector<int> indegree(n+1,0);
        
        for(int i=0;i<=n;i++)
        {
            for(int v : graph[i])
            {
                indegree[v]++;
            }
        }
        queue<int>q;
        for(int i=1;i<=n;i++)
        {
            if(indegree[i] == 0)
                q.push(i);
        }
        
        int ans = 0;
        while(q.size() > 0)
        {
            int s = q.size();
            int m = 0;
            while(s-- > 0)
            {
                int t = q.front();
                q.pop();
                for(auto nbr : graph[t])
                {
                    indegree[nbr]--;
                    maxtime[nbr] = max(time[t-1],maxtime[nbr]);
                    if(indegree[nbr] == 0){
                     time[nbr-1] += maxtime[nbr];
                        q.push(nbr);
                    }
                }
            }
              
        } 
        ans = INT_MIN;
          for(int val : time)
              ans = max(ans, val);
          
        return ans;
    }
};

// Path with minimum effort

class Solution {
public:
    int minimumEffortPath(vector<vector<int>>& heights) {
     int n = heights.size();
     int m = heights[0].size();
    priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> pq;
    pq.push({0,0});
    vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    vector<vector<bool>> vis(n,vector<bool>(m));
    while(pq.size() > 0)
    {
        auto top = pq.top();
        pq.pop();
        int effort = top.first;
        int idx = top.second;
        int i = idx/m;
        int j = idx%m;
        cout<<effort<<" "<<i<<" "<<j<<endl;
        
        if(vis[i][j]) continue;
        
        if(i == n-1 && j == m-1)
            return effort;
        vis[i][j] = true;
        for(auto dir : dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x>=0 && y>=0 && x<n && y<m && !vis[x][y])
            {
                int neffort = max(effort, abs(heights[x][y] - heights[i][j]));
                pq.push({neffort,x*m+y});
            }
        }
    }
        return -100;
    
}};


// Regions Cut by Slashes ============================================
/* 
Here we assume a 2 d matrix and we work only on the cells of those matrixs.
*/
class Solution {
        int findParent(int u)
    {
        if(parent[u] == u)
            return u;
        return parent[u] = findParent(parent[u]);
    }
    
    void merge(int u,int v)
    {
        int p1 = findParent(u);
        int p2 = findParent(v);
        if(p1!=p2)
        {
            if(rnk[p1] > rnk[p2])
            {
                parent[p2] = p1;
                rnk[p1]+= rnk[p2];
            }
            else{
                parent[p1] = p2;
                rnk[p2]+= rnk[p1];
            }
        }
        else
        {
            count++;
        }
    }
    int[] parent;
    int[] rnk;
    int count;

    public int regionsBySlashes(String[] grid) {
        int size = grid.length+1;
        //int n = size;
        parent = new int[size*size];
        rnk =   new int[size*size];
        count = 1;
        
        for(int i=0;i<parent.length;i++)
        {
            parent[i] = i; 
            rnk[i] = 1;
        }
        
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(i==0 || j==0 || i == size-1 || j==size-1)
                {
                    int cell = i*size+j;
                    if(cell != 0)
                        merge(0,cell);
                }
            }
        }
        
        for(int i=0;i<grid.length;i++)
        {
            char[] ch = grid[i].toCharArray();
            for(int j=0;j<ch.length;j++)
            {
                if(ch[j] == '/')
                {
                   int p1 = (i+1)*size+j;
                   int p2 = i*size+(j+1);
                    merge(p1,p2);
                }
                else if(ch[j] == '\\')
                {
                    int p1 = i*size+j;
                    int p2 = (i+1)*size+(j+1);
                    merge(p1,p2);
                }
            }
        }
        return count;
        
    }
}



// the maze ================================

public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    public class pair{
        int i;
        int j;
        pair(int i,int j)
        {
            this.i = i;
            this.j = j;
        }
    }

    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        // write your code here
        Queue<pair> q = new ArrayDeque<>();
        q.add(new pair(start[0],start[1]));
        int n = maze.length;
        int m = maze[0].length;
        int[][] vis = new int[n][m];
        int [][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        vis[start[0]][start[1]] = 1;
        while(q.size()>0){
            pair rp = q.remove();
            for(int[] val : dirs)
            {
                int x = rp.i;
                int y = rp.j;
                vis[x][y] = 1;

                while(x>=0 && y>=0 && x<n && y<m && maze[x][y] == 0)
                {
                    x+=val[0];
                    y+=val[1];
                }
                x-=val[0];
                y-=val[1];
                if(x == dest[0] && y == dest[1])
                return true;
                if(vis[x][y] == 1)
                continue;
                //vis[x][y] = 1;
                
                q.add(new pair(x,y));
            }
        }
        return false;
        

    }
}

// maze 2 ===========================================

public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
     
public class pair implements Comparable<pair>{
        int i;
        int j;
        int cost;
        pair(int i,int j,int cost)
        {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
        public int compareTo(pair p) {
            return this.cost - p.cost;
        }
}

public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        // write your code here
        // write your code here
        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(start[0],start[1],0));
        int n = maze.length;
        int m = maze[0].length;
        int[][] vis = new int[n][m];
        int [][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        vis[start[0]][start[1]] = 1;
        while(pq.size()>0){
            pair rp = pq.remove();
            if(rp.i == dest[0] && rp.j == dest[1])
            return rp.cost;
            vis[rp.i][rp.j] = 1;
            for(int[] val : dirs)
            {
                int x = rp.i;
                int y = rp.j;
                int dis = 0;
                while(x>=0 && y>=0 && x<n && y<m && maze[x][y] == 0)
                {
                    x+=val[0];
                    y+=val[1];
                    dis++;
                }
                x-=val[0];
                y-=val[1];
                dis--;
                
                if(vis[x][y] == 1)
                 continue;
                
                pq.add(new pair(x,y,rp.cost+dis));
            }
        }
        return -1;

    }
}

// maze 3 =====================================

public class Solution {
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */

    public class pair implements Comparable<pair>{
        int i;
        int j;
        String ans;
        int count;
        pair(int i,int j,String ans, int count)
        {
            this.i = i;
            this.j = j;
            this.ans = ans;
            this.count = count;
        }
        public int compareTo(pair o)
        {
            if(this.count == o.count)
            return this.ans.compareTo(o.ans);
            else
            return this.count - o.count;
        }
    } 
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // write your code here
        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(ball[0],ball[1],"",0));
        int m = maze.length;
        int n = maze[0].length;
        int vis[][] = new int[m][n];
        char[] dstr = {'d', 'l', 'r', 'u'};
        int[][] dirs = {{1,0},{0,-1},{0,1},{-1,0}};
        while(pq.size() > 0)
        {
           pair rp = pq.remove();
           if(rp.i == hole[0] && rp.j == hole[1])
           return rp.ans;
            
            vis[rp.i][rp.j] = 1;

           for(int k=0;k<4;k++)
           {
               int x = rp.i;
               int y = rp.j;
               int count = 0;
               String dir = rp.ans;
               while(x+dirs[k][0]>=0 && y+dirs[k][1]>=0 && x+dirs[k][0]<m && y+dirs[k][1]<n && maze[x+dirs[k][0]][y+dirs[k][1]]==0)
               {
               
                x+=dirs[k][0];
                y+=dirs[k][1];
                count++;
                if(x == hole[0] && y == hole[1])
                   break;
               }
               if(vis[x][y] == 0)
               {
                   pq.add(new pair(x,y,dir+dstr[k],count+rp.count));
               }
           }
        }
        return "impossible";
    }
}



// Graph Valid Tree

public boolean isCycle(ArrayList<Integer>[] graph, int[] vis, int src){
         Queue<Integer> q = new ArrayDeque<>();
         q.add(src);
         while(q.size() > 0){
             int rv = q.remove();
             if(vis[rv] == 1){
                 return true;
             }
             vis[rv] = 1;
             for(int nbr: graph[rv]){
                 if(vis[nbr] == 0)
                    q.add(nbr);
             }
         }
         return false;
     }
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            graph[u].add(v);
            graph[v].add(u);
        }
        int[] vis = new int[n];
        if(isCycle(graph, vis, 0)){
            return false;
        }
        for(int i=0;i<n;i++){
            if(vis[i] == 0){
                return false;
            }
        }
        return true;
    }

// Alien Dictionary 

class Solution
{
    public String findOrder(String [] dict, int n, int K)
    {
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        HashMap<Character, Integer> indegree = new HashMap<>();
        for(String str : dict){
            for(char ch : str.toCharArray()){
                indegree.put(ch, 0);
            }
        }
        
        for(int i=0; i< dict.length-1; i++){
            String word1 = dict[i];
            String word2 = dict[i+1];
            boolean flag = false;
            int sz = Math.min(word1.length(), word2.length());
            for(int j=0;j<sz;j++){
                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(j);
                if(ch1 != ch2){
                   HashSet<Character> hs = new HashSet<>();
                   if(graph.containsKey(ch1) == true){
                       hs = graph.get(ch1);
                       if(hs.contains(ch2) == false){
                           hs.add(ch2);
                           indegree.put(ch2, indegree.get(ch2) + 1);
                           graph.put(ch1, hs);
                       }
                   }else{
                       hs.add(ch2);
                       indegree.put(ch2, indegree.get(ch2) + 1);
                       graph.put(ch1, hs);
                   }
                   flag = true;
                   break;
                }
            }
            if(flag == false && word1.length() > word2.length()){
                return "";
            }
        }
        
        Queue<Character> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(char ch : indegree.keySet()){
            if(indegree.get(ch) == 0){
                q.add(ch);
            }
        }
        
        while(q.size() > 0){
            Character rv = q.remove();
            sb.append(rv);
            count++;
            if(graph.containsKey(rv) == true){
                for(char nbrs: graph.get(rv)){
                    indegree.put(nbrs, indegree.get(nbrs) - 1);
                    if(indegree.get(nbrs) == 0){
                        q.add(nbrs);
                    }
                }
            }
        }
        if(count == indegree.size()){
            return sb.toString();
        }
        else{
            return "";
        }
    }
}

// Coloring a Border

class Solution {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        dfs(grid, row, col, grid[row][col]);
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] < 0){
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }
    int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    public void dfs(int[][] grid, int r, int c, int clr){
        grid[r][c] = -clr;
        int count = 0;
        
        for(int i=0; i<4; i++){
            int row = r + dirs[i][0];
            int col = c + dirs[i][1];
            
            if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || Math.abs(grid[row][col]) != clr){
                continue;
            }
            count++;
            
            if(grid[row][col] == clr){
                dfs(grid, row, col, clr);
            }
        }
        if(count == 4){
            grid[r][c] = clr;
        }
    }
}

// Number of Distinct Island

class Solution {
    class pair{
        int first;
        int second;
        pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
    int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
    void dfs(int r, int c, int[][] vis, int[][] grid, ArrayList<String> temp, int r0, int c0){
        vis[r][c] = 1;
        temp.add(toString(r-r0, c-c0));
        int n = grid.length;
        int m = grid[0].length;
        for(int i=0; i<4; i++){
            int row = r + dirs[i][0];
            int col = c + dirs[i][1];
            if(row>=0 && col>=0 && row<n && col<m && grid[row][col] == 1 && vis[row][col] == 0){
                dfs(row, col, vis, grid, temp, r0, c0);
            }
        }
        
    }
    
    String toString(int r, int c){
        return Integer.toString(r) + " " + Integer.toString(c);
    }
    int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        HashSet<ArrayList<String>> hs = new HashSet<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(vis[i][j] == 0 && grid[i][j] == 1){
                    ArrayList<String> temp = new ArrayList<>();
                    dfs(i, j, vis, grid, temp, i, j);
                    hs.add(temp);
                }
            }
        }
        return hs.size();
    }
}

// Shortest Bridge

class Solution {
    public class pair{
        int r;
        int c;
        pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    public void dfs(int r, int c, LinkedList<pair> q, boolean[][] vis, int[][] grid){
        vis[r][c] = true;
        q.addLast(new pair(r,c));
        for(int i=0; i<4; i++){
            int row = r + dirs[i][0];
            int col = c + dirs[i][1];
            if(row<0 || col<0 || row>=grid.length || col>=grid[0].length || vis[row][col] == true || grid[row][col] == 0){
                continue;
            }
            dfs(row, col, q, vis, grid);
        }
    }
    private void markIsland(int i, int j, int[][] grid){
        grid[i][j] = 2;
        for(int[] d: dirs){
            int x = i+d[0], y = j+d[1];
            if(x < 0 || y < 0 || x == grid.length || y == grid.length ||         grid[x][y] != 1)
                continue;
            markIsland(x, y, grid);
        }
    } 
    public int shortestBridge(int[][] grid) {
        LinkedList<pair> q = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        boolean flag = false;
        
        boolean isMarked = false;
        for(int i = 0; i< n; i++){
            for(int j = 0; j< n; j++){
                if(grid[i][j] == 1){
                    markIsland(i, j, grid);
                    isMarked = true;
                    break;
                }
            }
            if(isMarked) break;
        }
        
        for(int i=0; i<n && !flag; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] ==1){
                   dfs(i,j,q,vis, grid);
                   flag = true;
                }
            }
        }
        
        int level = 0;
        
        while(q.size() > 0){
            int s = q.size();
            while(s-- > 0){
                pair rem = q.removeFirst();
                //vis[rem.r][rem.c] = true;
                for(int i=0; i<4; i++){
                    int row = rem.r + dirs[i][0];
                    int col = rem.c + dirs[i][1];
                    if(row<0 || col<0 || row>=n || col>=m || vis[row][col] == true){
                        continue;
                    }
                    if(grid[row][col] == 2)
                        return level;
                    vis[row][col] = true;
                    q.addLast(new pair(row, col));
                } 
            }
            level++;
        }
        return 0;
    }
}

// Sliding Puzzle 

class Solution {
    public String swapChar(String st, int i, int j){
        StringBuilder sb = new StringBuilder(st);
        sb.setCharAt(i, st.charAt(j));
        sb.setCharAt(j, st.charAt(i));
        
        return sb.toString();
    }
    
    public int slidingPuzzle(int[][] board) {
        LinkedList<String> q = new LinkedList<>();
        String target = "123450";
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                sb.append(board[i][j]);
            }
        }
        
        String initial = sb.toString();
        int level = 0;
        HashSet<String> vis = new HashSet<>();
        q.addLast(initial);
        int[][] swapIdx = {{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{4,2}};
        while(q.size() > 0){
            int sz = q.size();
            while(sz-- > 0){
                String rs = q.removeFirst();
                if(rs.equals(target)){
                    return level;
                }
                
                int idx = -1;
                for(int i=0; i<rs.length(); i++){
                    if(rs.charAt(i) == '0'){
                        idx = i;
                        break;
                    }
                }
                
                int[] swap = swapIdx[idx];
                for(int i=0; i<swap.length; i++){
                    String tobeAdded = swapChar(rs, idx, swap[i]);
                    if(vis.contains(tobeAdded)){
                        continue;
                    }
                    q.addLast(tobeAdded);
                    vis.add(tobeAdded);
                }
            }
            level++;
        }
        return -1;
    } 
}

//