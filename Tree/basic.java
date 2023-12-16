// size, sum, max, height
public static int size(Node node) {
    if(node == null)
    return 0;
    int sz = 0;
    sz += size(node.left);
    sz += size(node.right);
    return sz+1;
  }

  public static int sum(Node node) {
    if(node == null)
    return 0;
    int sm = 0;
    sm += sum(node.left);
    sm += sum(node.right);
    return sm+node.data;
  }

  public static int max(Node node) {
    if(node == null)
    return Integer.MIN_VALUE;
    int mx = Integer.MIN_VALUE;
    mx = Math.max(mx,max(node.left));
    mx = Math.max(mx,max(node.right));
    return Math.max(mx,node.data); 
  }

  public static int height(Node node) {
    if(node == null)
    return -1;
    return Math.max(height(node.left), height(node.right))+1;
    }

// Level order Traversal

  public static void levelOrder(Node node) {
    Queue<Node>q = new ArrayDeque<>();
    q.add(node);
    while(q.size()>0)
    {
        int s = q.size();
        while(s-- > 0)
        {
            Node rn = q.remove();
            System.out.print(rn.data+" ");
            if(rn.left != null)
            q.add(rn.left);
            if(rn.right != null)
            q.add(rn.right);
        }
        System.out.println();
    }
  }

// Recurssive

class Solution {
    public void helper(TreeNode root, HashMap<Integer, ArrayList<Integer>> hm, int level)
    {
        if(root== null)
            return;
        if(!hm.containsKey(level)){
            hm.put(level, new ArrayList<>());
            hm.get(level).add(root.val);
        }
        else
        {
            hm.get(level).add(root.val);
        }
        helper(root.left,hm,level+1);
        helper(root.right,hm,level+1);
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        helper(root,hm,0);
        for(int i=0;i<hm.size();i++)
        {
            ans.add(hm.get(i));
        }
        return ans;
    }
}

// Iterative Preorder Inorder Postorder Traversal

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


//     1
//    / \
//   2   3
//  / \ / \
// 4  5 6  7
 
    public class pair{
        TreeNode node;
        int status;
        pair(TreeNode node, int status){
            this.node = node;
            this.status = status;
        }
    }
    public List<Integer> iterativeTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();
        if(root == null){
            return inorder;
        }
        Stack<pair> st = new Stack<>();
        st.push(new pair(root, 1));
        while(st.size() > 0){
            pair rp = st.peek();
            if(rp.status == 1){ // pre, st++, left
                preorder.add(rp.node.val);
                rp.status += 1;
                if(rp.node.left != null){
                    pair p = new pair(rp.node.left, 1);
                    st.push(p);
                }
            }else if(rp.status == 2){ // in, st++, right
                inorder.add(rp.node.val);
                rp.status += 1;
                if(rp.node.right != null){
                    pair p = new pair(rp.node.right, 1);
                    st.push(p);
                }
            }else if(rp.status == 3){
                postorder.add(rp.node.val);
                st.pop();
            }
        } 
        System.out.println(preorder);
        System.out.println(inorder);
        System.out.println(postorder);
        return inorder;
    }


// Morris Inorder Traversal
 public TreeNode RightMostNode(TreeNode left,TreeNode curr)
    {
        while(left.right!=null &&left.right != curr)
            left = left.right;
        return left;
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;
        TreeNode curr = root;
        while(curr != null)
        {
            TreeNode left = curr.left;
            if(left == null)
            {
               ans.add(curr.val);
                curr = curr.right;
            }
            else
            {
                TreeNode rgtn = RightMostNode(left,curr);
                if(rgtn.right == null)
                {
                    rgtn.right = curr;
                    curr = curr.left;
                }
                else
                {
                    rgtn.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

// Morris PreOrder Traversal

public TreeNode rightMostNode(TreeNode left,TreeNode curr)
    {
        while(left.right != null && left.right != curr)
            left = left.right;
        return left;
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;
        TreeNode curr = root;
        while(curr!=null)
        {
            TreeNode left = curr.left;
            if(left==null)
            {
                ans.add(curr.val);
                curr = curr.right;
            }
            else
            {
                TreeNode rhtnode = rightMostNode(left,curr);
                if(rhtnode.right == null)
                {
                 rhtnode.right = curr;
                 ans.add(curr.val);
                 curr = curr.left;
                }
                else
                {
                    rhtnode.left = null;
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

// find in the BT

public static boolean find(Node node, int data){
    if(node == null)
    return false;
    boolean res = false;
    res = res || find(node.left,data);
    res = res || find(node.right,data);
    return res || (node.data == data);
  }

// Node to Root Path 

public static ArrayList<Integer> nodeToRootPath(Node node, int data){
      if(node == null)
      return new ArrayList<>();
      ArrayList<Integer> ans = new ArrayList<>();
      if(node.data == data)
      {
          ans.add(node.data);
          return ans;
      }
      ArrayList<Integer> left = nodeToRootPath(node.left,data);
      if(left.size() > 0)
      {
        left.add(node.data);
        return left;
      }
      ArrayList<Integer> right = nodeToRootPath(node.right,data);
      if(right.size() > 0)
      {
          right.add(node.data);
          return right;
      }
      return ans;
  }

// Print k level Down

public static void printKLevelsDown(Node node, int k){
    if(node == null)
    return;
    if(k==0)
    {
        System.out.println(node.data);
        return;
    }
    printKLevelsDown(node.left,k-1);
    printKLevelsDown(node.right,k-1);
  }

// print k nodes away

public static void printKNodesFar(Node node, int data, int k) {
    // write your code here
    List<Node> ntrpath = nodeToRootPath(node,data);
    Node block = null;
    for(int i=0;i<ntrpath.size();i++)
    {
        printKLevelsDown(ntrpath.get(i),k-i,block);
        block = ntrpath.get(i);
        
    }

// path to leaves from root

public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
    // write your code here
    if(node == null)
    return;
    if(node.left == null && node.right == null)
    {
        if(sum+node.data >= lo && sum+node.data <= hi)
        System.out.println(path+node.data);
        return;
    }
    pathToLeafFromRoot(node.left,path+node.data+" ",sum+node.data,lo,hi);
    pathToLeafFromRoot(node.right,path+node.data+" ",sum+node.data,lo,hi);
  }

// Left CLoned Tree

public static Node createLCT(Node node)
  {
      if(node == null)
      return null;
      Node nnode = new Node(node.data,null,null);
      Node left = createLCT(node.left);
      Node right = createLCT(node.right);
      node.left = nnode;
      nnode.left = left;
      node.right = right;
      return node;
  }
  public static Node createLeftCloneTree(Node node){
    return createLCT(node);
  }

// Transform From Left Clonded Tree

public static Node tranformBFLCT(Node node)
  {
      if(node == null || node.left == null)
      return null;
      node.left = tranformBFLCT(node.left.left);
      node.right = tranformBFLCT(node.right);
      return node;
  }
  public static Node transBackFromLeftClonedTree(Node node){
    // write your code here
    return tranformBFLCT(node);
  }

//
Print single Child Nodes

 public static void printSingleChildNodes(Node node, Node parent){
    if(node == null)
    return;
    if(parent!= null && ((parent.left == null && parent.right != null)||(parent.left != null && parent.right == null)))
    {
        System.out.println(node.data);
    }
    printSingleChildNodes(node.left,node);
    printSingleChildNodes(node.right,node);
  }

// Remove Leaves In Binary Tree
public static Node removeLeaves(Node node){
    if(node == null)
    return null;
    if(node.left == null && node.right == null)
    return null;
    node.left = removeLeaves(node.left);
    node.right = removeLeaves(node.right);
    return node;
  }

// Tilt of a Binary Tree

static int tilt = 0;
  public static int tilt(Node node){
    if(node == null)
    return 0;
    int lsum = tilt(node.left);
    int rsum = tilt(node.right);
    tilt += Math.abs(lsum - rsum);
    return lsum + rsum + node.data;
  }

// Diameter of a Binary Tree

public static int height(Node node)
    {
        if(node == null)
            return -1;
        return Math.max(height(node.left),height(node.right))+1;
    }

public static int diameter1(Node node) {
    if(node == null)
    return 0;
    int dia1 = diameter1(node.left);
    int dia2 = diameter1(node.right);
    int h = height(node.left) + height(node.right) + 2;
    return Math.max(dia1,Math.max(dia2,h));
  }

/// Diameter 2

class Solution {
    public static int dia = 0;
    public int helper(TreeNode root)
    {
        if(root == null)
            return -1;
        int lh = helper(root.left);
        int rh = helper(root.right);
        int th = Math.max(lh,rh)+1;
        if(lh + rh + 2 > dia)
            dia = lh + rh + 2;
        return th;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        dia = 0;
        helper(root);
        return dia;
    }
}


///    Diameter 3

class Solution {
    public class pair{
        int ht = -1;
        int dia = 0;
        pair(int ht,int dia)
        {
            this.ht = ht;
            this.dia = dia;
        }
    }
    public pair helper(TreeNode root)
    {
        if(root == null)
            return new pair(-1, 0);
        pair l = helper(root.left);
        pair r = helper(root.right);
        pair ans = new pair(0,0);
        ans.ht = Math.max(l.ht,r.ht)+1;
        ans.dia = Math.max(l.ht + r.ht + 2,Math.max(l.dia,r.dia));
        return ans;
        
    }
    public int diameterOfBinaryTree(TreeNode root) {
        pair ans = helper(root);
        return ans.dia;
    }
}

// is bst 
class Solution {
    public boolean isValidBST_(TreeNode node,long min, long max){
        if(node == null)
            return true;
        
        boolean res = isValidBST_(node.left,min,node.val);
        boolean res2 = isValidBST_(node.right,node.val,max);
        if(node.val > min && node.val < max && res && res2)
            return true;
        else
            return false;
    }
    public boolean isValidBST(TreeNode root) {
        return isValidBST_(root,-(long)1e19, (long)1e19);
    }
}

// Balanced Binary Tree

class Solution {
    public class pair{
        int ht;
        boolean isBal;
        pair(int ht,boolean isBal)
        {
            this.ht = ht;
            this.isBal = isBal;
        }
    }
    public pair isBalanced_(TreeNode node)
    {
        if(node==null)
            return new pair(0,true);
        
        pair lp =  isBalanced_(node.left);
        pair rp =  isBalanced_(node.right);
        if(!lp.isBal || !rp.isBal || Math.abs(lp.ht-rp.ht) >1)
            return new pair(-1,false);
        return new pair(Math.max(lp.ht,rp.ht)+1,true);
            
    }
    public boolean isBalanced(TreeNode root) {
         return  isBalanced_(root).isBal;
    }
}

// Largest BST in a binary Tree subtree

    public static class pair{
        int min,max,size;
        boolean isBst;
        pair(){}
        pair(int min,int m,int size, boolean isBst)
        {
            this.max = max;
            this.min = min;
            this.size = size;
            this.isBst = isBst;
        }
    }
    public static pair largestBst_(Node root)
    {
        if(root == null)
        {
            return new pair(Integer.MIN_VALUE,Integer.MAX_VALUE,0, true);
        }
        pair p = new pair();
        pair left = largestBst_(root.left);
        pair right = largestBst_(root.right);
        p.min = Math.min(left.min, root.val);
        p.max = Math.max(right.max, root.val);
        p.isBst = left.isBst && right.isBst && (left.max < root.val && right.min > root.val);
        if(p.isBst){
            p.size = left.size + right.size + 1;
        }
        else{
            p.size = Math.max(left.size, right.size) + 1;
        }
        return p;
    }
    static int largestBst(Node root)
    {
      return largestBst_(root).size;
    }


//  camera in BT
 class Solution {
    static int camera = 0;
    public int countCamera(TreeNode node)
    {
        if(node == null)
            return 1;
        int leftc =  countCamera(node.left);
        int rightc = countCamera(node.right);
        if(leftc == -1 || rightc == -1)
        {
            camera++;
            return 0;
        }
        if(leftc == 0 || rightc == 0)
            return 1;
        return -1;
    }
    public int minCameraCover(TreeNode root) {
        if(root == null)
            return 1;
        camera = 0;
        if(countCamera(root) == -1)
            camera++;
        return camera;
    }
}


// House Robbery 3 
class pair{
    int ri = 0;
    int re = 0;
    pair(int ri,int re)
    {
        this.ri = ri;
        this.re = re;
    }
}
class Solution {
    public pair helper(TreeNode root)
    {
        if(root == null)
            return new pair(0,0);
        pair l = helper(root.left);
        pair r = helper(root.right);
        int ci = root.val + l.re + r.re;
        int ce = Math.max(l.ri,l.re) + Math.max(r.ri,r.re);
        return new pair(ci,ce);
    }
    public int rob(TreeNode root) {
        pair p = helper(root);
        return Math.max(p.ri,p.re);
    }
}

// Longest Zig-zag path in a binary tree
class Solution {
    public class pair{
        int lmax;
        int rmax;
        int self;
        pair(){}
        pair(int lmax, int rmax, int self){
            this.lmax = lmax;
            this.rmax = rmax;
            this.self = self;
        }
    }
    public pair longestZigZag_(TreeNode root){
        if(root == null){
            return new pair(-1, -1, -1);
        }
        pair ans = new pair();
        pair left = longestZigZag_(root.left);
        pair right = longestZigZag_(root.right);
        ans.lmax = left.rmax + 1;
        ans.rmax = right.lmax + 1;
        ans.self = Math.max(Math.max(left.self, right.self), Math.max(ans.lmax, ans.rmax));
        return ans;
    }
    public int longestZigZag(TreeNode root) {
        pair ans = longestZigZag_(root);
        return Math.max(Math.max(ans.self, ans.lmax), ans.rmax);
    }
}


// Recover BST
class Solution {
    public TreeNode predecessor(TreeNode root,TreeNode curr)
    {
        while(root.right != null && root.right != curr)
            root = root.right;
        return root;
    }
    public TreeNode recoverTree_(TreeNode root)
    {
        TreeNode curr = root;
        TreeNode a = null,b = null;
        TreeNode prev = null;
        while(curr != null)
        {
            TreeNode left = curr.left;
            if(left == null)
            {
                if(prev!= null && prev.val > curr.val)
                {
                if(a == null)
                    a = prev;
                b = curr;}
                prev = curr;
                curr = curr.right;
            }
            else
            {
                TreeNode node = predecessor(curr.left,curr);
                if(node.right == null)
                {
                    node.right = curr;
                    curr = curr.left;
                    
                }
                else
                {
                  node.right = null;
                  // add to list
                if(prev.val > curr.val)
                {
                if(a == null)
                    a = prev;
                b = curr;}
                prev = curr;
                  curr = curr.right;
                  
                }
            }
        }
        if(a!=null)
        {
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
        return root;
    }
    public void recoverTree(TreeNode root) {
         recoverTree_(root);
    }
}
// Construct BT from  preorder and inorder 

class Solution {
    public TreeNode buildTree_(int[] preorder,int[] inorder,int ps,int pe,int is,int ie){
        if(ps>pe)
        return null;
        TreeNode node = new TreeNode(preorder[ps]);
        int idx = is;
        while(inorder[idx] != preorder[ps])
        idx++;
        
        int count = idx - is;
        node.left = buildTree_(preorder,inorder,ps+1,ps+count,is,count-1);
        node.right = buildTree_(preorder,inorder,ps+count+1,pe,idx+1,ie);
        return node;
        
    } 
    public TreeNode buildTree(int[] preorder, int[] inorder)     {
       
        return buildTree_(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
}

// construct BT from inorder and postorder

class Solution {
    public  TreeNode buildTree_(int[] inorder,int is,int ie,int[] postorder,int ps,int pe)
    {
        if(ps>pe)
        {
            return null;
        }
        TreeNode node = new TreeNode(postorder[pe]);
        int idx = is;
        while(inorder[idx] != postorder[pe])
        idx++;
        int count = idx-is;
        node.left = buildTree_(inorder,is,idx-1,postorder,ps,ps+count-1);
        node.right = buildTree_(inorder,idx+1,ie,postorder,ps+count,pe-1);
        return node;
    }

    public  TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree_(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }
}

// construct BT from preorder and postorder

class Solution {
     public  TreeNode constructFromPrePost_(int[]preorder,int[]postorder,int pes,int pee,int pos,int poe)
    {
        if(pes > pee)
        return null;
        TreeNode node = new TreeNode(preorder[pes]);
        if(pes == pee)
        return node;
        int idx = pos;
        while(postorder[idx]!=preorder[pes+1])
        idx++;
        int count = idx-pos+1;
        node.left = constructFromPrePost_(preorder,postorder,pes+1,pes+count,pos,idx);
        node.right = constructFromPrePost_(preorder,postorder,pes+count+1,pee,idx+1,pos);
        return node;
        
    }
    public  TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePost_(pre,post,0,pre.length-1,0,post.length);
    }
}

// construct BT from inorder and levelorder
 public static TreeNode buildTree_(int[] inorder,int[] levelorder,int si,int ei)
  {
      if(si > ei)
      return null;
      
      TreeNode node = new TreeNode(levelorder[0]);
      
      HashSet<Integer> set = new HashSet<>();
      int idx = si;
      
      while(inorder[idx] != levelorder[0])
      idx++;
      
      for(int i=si;i<idx;i++)
      set.add(inorder[i]);
      
      int n = ei-si+1;
      int[] left = new int[set.size()];
      int[] right = new int[n - set.size()];
      int li=0,ri=0;
      for(int i=1;i<levelorder.length;i++)
      {
          if(set.contains(levelorder[i]))
          left[li++] = levelorder[i];
          else
          right[ri++] = levelorder[i];
      }
      node.left = buildTree_(inorder,left,si,idx-1);
      node.right = buildTree_(inorder,right,idx+1,ei);
      return node;
  }

// construct BST from inorder

public static int idx = 0;
    public static TreeNode constructFromInOrder_(int[] inorder,int si,int ei)
    {
        if(si >= ei)
        {
            return si==ei?new TreeNode(inorder[si]) : null;
        }
        int mid = (si + ei)/2;
        TreeNode node = new TreeNode(inorder[mid]);
        node.left = constructFromInOrder_(inorder,si,mid-1);
        node.right = constructFromInOrder_(inorder,mid+1,ei);
        return node;
        
    }
    public static TreeNode constructFromInOrder(int[] inorder) {
        idx = 0;
        return constructFromInOrder_(inorder,0,inorder.length-1);
    }
// construct bst from postorder
class GFG
{
    public static int idx;
    public static Node bstFromPostorder_(int[] postorder,int min,int max)
    {
        if(idx < 0 || postorder[idx] < min  || postorder[idx] > max)
        return null;
        Node node = new Node(postorder[idx--]);
        node.right = bstFromPostorder_(postorder,node.data,max);
        node.left = bstFromPostorder_(postorder,min,node.data);
        return node;
    }
    public static Node constructTree(int[] postorder,int n) {
        idx = postorder.length-1;
        return bstFromPostorder_(postorder,Integer.MIN_VALUE, Integer.MAX_VALUE);
        
    }
    
}

// Construct BT from levelorder traversal

 public static class pair{
        TreeNode node; 
        int min;
        int max;
        pair(TreeNode node,int min,int max)
        {
            this.node = node;
            this.min = min;
            this.max = max;
        }
    } 
    public static TreeNode constructBSTFromLevelOrder_(int[] levelorder)
    {
        Queue<pair> q = new ArrayDeque<>();
        q.add(new pair(null,Integer.MIN_VALUE,Integer.MAX_VALUE));
        int idx = 0;
        TreeNode root = null;
        while(q.size()>0 && idx < levelorder.length)
        {
            pair rp = q.remove();
            if (levelorder[idx] < rp.min || levelorder[idx] > rp.max)continue;
            
            TreeNode node = new TreeNode(levelorder[idx++]);
            if(rp.node == null)
            {
               root = node; 
            }
            else
            {
                //TreeNode node = new TreeNode(levelorder[idx++]);
                if(rp.node.val < node.val)
                {
                    rp.node.right = node;
                }
                else
                {
                    rp.node.left = node;
                }
            }
            q.add(new pair(node,rp.min,node.val));
            q.add(new pair(node,node.val,rp.max));
        }
        return root;
        
    }
    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        return constructBSTFromLevelOrder_(LevelOrder);
    }


// width of a Binary Tree
public static void solve(TreeNode root,int level,int[] wid)
    {
        if(root == null)
        return;
        solve(root.left,level-1,wid);
        solve(root.right,level+1,wid);
        wid[0] = Math.min(wid[0],level);
        wid[1] = Math.max(wid[1],level);
        //return wid[0]-wid[1]+1;
        
    }
    public static int width(TreeNode root) {
       int[] wid= new int[2];
       solve(root,0,wid);
       return wid[1]-wid[0]+1;
    }
    
//  vertical level order Traversal Sum

public static void findWid(TreeNode root,int level,int[] wid)
    {
        if(root == null)
        return;
        findWid(root.left,level-1,wid);
        findWid(root.right,level+1,wid);
        wid[0] = Math.min(wid[0],level);
        wid[1] = Math.max(wid[1],level);
    }
    public static void verticalOrderTraversal_(TreeNode root, int level, HashMap<Integer,Integer> hmap)
    {
        if(root == null)
        return;
        if(!hmap.containsKey(level))
        {
         hmap.put(level,0);   
        }
        int val = hmap.get(level) + root.val;
        hmap.put(level,val);
        verticalOrderTraversal_(root.left,level-1,hmap);
        verticalOrderTraversal_(root.right,level+1,hmap);
    }

    public static ArrayList<Integer> verticalOrderSum(TreeNode root) {
        int[] wid = new int[2];
        findWid(root,0,wid);
        int width = wid[1]-wid[0]+1;
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> hmap = new HashMap<>();
      verticalOrderTraversal_(root,0,hmap);  
      int si = wid[0], ei = wid[1];
      while(si<=ei)
      {
          ans.add(hmap.get(si++));
      }
      return ans;
    }

// level order traversal
public List<List<Integer>> levelOrder(TreeNode root) {
     Queue<TreeNode> q = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        q.add(root);
        while(q.size() > 0)
        {
            int s = q.size();
            List<Integer> ar = new ArrayList<>();
            while(s-- > 0)
            {
                TreeNode rn = q.remove();
                ar.add(rn.val);
                if(rn.left != null)
                    q.add(rn.left);
                if(rn.right != null)
                    q.add(rn.right);
            }
            ans.add(ar);
        }
        return ans;
    }

// Left View 

ArrayList<Integer> leftView(Node root)
    {
     Queue<Node> q = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;
        q.add(root);
        while(q.size() > 0)
        {
            int s = q.size();
            ArrayList<Integer> ar = new ArrayList<>();
            while(s-- > 0)
            {
                Node rn = q.remove();
                ar.add(rn.data);
                if(rn.left != null)
                    q.add(rn.left);
                if(rn.right != null)
                    q.add(rn.right);
            }
            ans.add(ar.get(0));
        }
        return ans;
    }

// Right View 
ArrayList<Integer> rightView(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;
        q.add(root);
        while(q.size() > 0)
        {
            int s = q.size();
            ArrayList<Integer> ar = new ArrayList<>();
            while(s-- > 0)
            {
                Node rn = q.remove();
                ar.add(rn.data);
                if(rn.left != null)
                    q.add(rn.left);
                if(rn.right != null)
                    q.add(rn.right);
            }
            ans.add(ar.get(ar.size()-1));
        }
        return ans;
    }

// Vertical order Traversal

 import java.util.*;

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }
    public static void width(TreeNode root,int[] minMax,int level)
    {
        if(root == null)
        return;
        minMax[0] = Math.min(minMax[0],level);
        minMax[1] = Math.max(minMax[1],level);
        width(root.left,minMax,level-1);
        width(root.right,minMax,level+1);
    }
    public static void verticalsolve(TreeNode root,ArrayList<ArrayList<Integer>> ans, int level)
    {
        if(root == null)
        return;
        ans.get(level).add(root.val);
        verticalsolve(root.left,ans,level-1);
        verticalsolve(root.right,ans,level+1);
    }
    
    public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root) {
    if(root == null)
    return new ArrayList<>();
    int minMax[] = new int[2];
    width(root,minMax,0);
    int size = minMax[1] - minMax[0]+1;
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    for(int i=0;i<size;i++)
    {
        ans.add(new ArrayList<>());
    }
    verticalsolve(root,ans,size/2);
    return ans;
    }

    // input_section=================================================

    public static TreeNode createTree(int[] arr, int[] IDX) {
        if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
            IDX[0]++;
            return null;
        }
        TreeNode node = new TreeNode(arr[IDX[0]++]);
        node.left = createTree(arr, IDX);
        node.right = createTree(arr, IDX);

        return node;
    }

    public static void solve() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        int[] IDX = new int[1];
        TreeNode root = createTree(arr, IDX);

        ArrayList<ArrayList<Integer>> ans = verticalOrderTraversal(root);
        int idx = 0;
        for (ArrayList<Integer> i : ans) {
            System.out.print(idx++ + " -> ");
            for (Integer j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        solve();
    }
}


// V O T : 2 

import java.util.*;

public class Main {
  public static Scanner scn = new Scanner(System.in);

  public static class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static class verticalpair {
    TreeNode node =  null;
    int hl = 0;
    verticalpair(TreeNode node, int hl)
    {
      this.node = node;
      this.hl = hl;
    }
  }

  public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root) {
    LinkedList<verticalpair> que = new LinkedList<>();
    que.addLast(new verticalpair(root, 0));
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    int minhl = 0;
    int maxhl = 0;
    while (que.size() != 0)
    {
      int size = que.size();
      while (size-- > 0)
      {
        verticalpair rp = que.removeFirst();
        map.putIfAbsent(rp.hl, new ArrayList<>());
        map.get(rp.hl).add(rp.node.val);
        minhl = Math.min(minhl, rp.hl);
        maxhl = Math.max(maxhl, rp.hl);

        if (rp.node.left != null)
          que.addLast(new verticalpair(rp.node.left, rp.hl - 1));
        if (rp.node.right != null)
          que.addLast(new verticalpair(rp.node.right, rp.hl + 1));

      }

      
      
    }
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
      while (minhl <= maxhl)
      {
        ans.add(map.get(minhl));
        minhl++;
      }return ans;
  }

  // input_section=================================================

  public static TreeNode createTree(int[] arr, int[] IDX) {
    if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
      IDX[0]++;
      return null;
    }
    TreeNode node = new TreeNode(arr[IDX[0]++]);
    node.left = createTree(arr, IDX);
    node.right = createTree(arr, IDX);

    return node;
  }

  public static void solve() {
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++)
      arr[i] = scn.nextInt();

    int[] IDX = new int[1];
    TreeNode root = createTree(arr, IDX);

    ArrayList<ArrayList<Integer>> ans = verticalOrderTraversal(root);
    int idx = 0;
    for (ArrayList<Integer> i : ans) {
      System.out.print(idx++ + " -> ");
      for (Integer j : i)
        System.out.print(j + " ");
      System.out.println();
    }
  }

  public static void main(String[] args) {
    solve();
  }
}

// V O T : 3

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
    class verticalpair{
    TreeNode node = null;
    int hl = 0;
    verticalpair(TreeNode node, int hl)
    {
        this.node = node;
        this.hl = hl;
    }
}

    public static void width(TreeNode root,int hl, int[]minMax)
    {
        if(root==null)
            return;
        minMax[0] = Math.min(minMax[0],hl);
        minMax[1] = Math.max(minMax[1],hl);
        width(root.left, hl-1, minMax);
        width(root.right,hl+1, minMax);
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        PriorityQueue<verticalpair> que = new PriorityQueue<>((a,b)->{
            return a.node.val - b.node.val;
        });
         PriorityQueue<verticalpair> childq = new PriorityQueue<>((a,b)->{
            return a.node.val - b.node.val;
        });
        
        int[] minMax = new int[2];
        width(root,0,minMax);
        int len = minMax[1] - minMax[0] +1;
        for(int i=0;i<len;i++)
        {
            ans.add(new ArrayList<>());
        }
        
        que.add(new verticalpair(root,Math.abs(minMax[0])));
        while(que.size()!=0)
        {
            verticalpair rp = que.remove();
            ans.get(rp.hl).add(rp.node.val);
            if(rp.node.left !=null)
            {
               childq.add(new verticalpair(rp.node.left,rp.hl-1)); 
            }
            if(rp.node.right != null)
            {
                childq.add(new verticalpair(rp.node.right,rp.hl+1));
            }
            if(que.size()==0)
            {
             PriorityQueue<verticalpair> temp = que;
                que = childq;
                childq = temp;
            }
        }
        return ans;
        
    }
}

    // Diagonal Traversal 1

        public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
       ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
       Queue<TreeNode> q = new ArrayDeque<>();
       
       q.add(root);
       int idx = 0;
       while(q.size() > 0)
       {
        int sz = q.size();
        ArrayList<Integer> tmp = new ArrayList<>();

           while(sz-- > 0)
           {
               TreeNode rn = q.remove();
               while(rn!=null)
               {
                   if(rn.left != null)
                   {
                       q.add(rn.left);
                   }
                   tmp.add(rn.val);
                   rn = rn.right;
               }
               
           }
           ans.add(tmp);
       }
       return ans;
    }

// Diagonal Traversal 2
public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    Queue<TreeNode> q = new ArrayDeque<>();
       
    q.add(root);
    int idx = 0;
    while(q.size() > 0)
       {
        int sz = q.size();
        ArrayList<Integer> tmp = new ArrayList<>();

           while(sz-- > 0)
           {
               TreeNode rn = q.remove();
               while(rn!=null)
               {
                   tmp.add(rn.val);
                   if(rn.right != null)
                   {
                       q.add(rn.right);
                   }
                   rn = rn.left;
               }
               
           }
           ans.add(tmp);
       }
       return ans;
    }

// Diagonal order sum of BT
// Same

// all single child parent in BT

public static void exactlyOneChild_(TreeNode root,ArrayList<Integer> ans)
  {
    if(root == null || (root.left==null && root.right==null))
    return;
    if((root.left==null && root.right!=null) || (root.right==null && root.left != null))
    {
        ans.add(root.val);
    }
    exactlyOneChild_(root.left,ans);
    exactlyOneChild_(root.right,ans);
    
    return;
  }

//// count all single child parent in BT


public static TreeNode createTree(int[] arr, int[] IDX) {
    if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
      IDX[0]++;
      return null;
    }
    TreeNode Treenode = new TreeNode(arr[IDX[0]++]);
    Treenode.left = createTree(arr, IDX);
    Treenode.right = createTree(arr, IDX);

    return Treenode;
  }


// all possible Full binary tree 

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
    public ArrayList<TreeNode> helper(int n)
    {
        if(n==1)
        {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(new TreeNode(0));
            return base;
        }
        ArrayList<TreeNode> ans =new ArrayList<>();
        for(int i=1;i<n;i+=2)
        {
            ArrayList<TreeNode> left = helper(i) ;
            ArrayList<TreeNode> right = helper(n-i-1);
            for(TreeNode l : left)
            {
                for(TreeNode r : right)
                {
                    TreeNode node = new TreeNode(0);
                    node.left = l;
                    node.right = r;
                    ans.add(node);
                }
            }
        }
        return ans;
    }
    public List<TreeNode> allPossibleFBT(int n) {
       return  helper(n);
    }
}


// Binary Tree Max Path Sum 

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
    public int helper(TreeNode root,int []ans)
    {
        if(root == null)
            return 0;
        int l = Math.max(0,helper(root.left,ans));
        int r = Math.max(0,helper(root.right,ans));
        ans[0] = Math.max(ans[0], l+r+root.val);
        return Math.max(l,r)+root.val;
        
        
    }
    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;
        int[] ans = new int[1];
        ans[0] = Integer.MIN_VALUE;
         helper(root,ans);
        return ans[0];
    }
}


// Complete Binary Tree

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
class CBTInserter {

    Queue<TreeNode> q = new LinkedList<>();
    TreeNode head = null;
    TreeNode nip = null;
    public CBTInserter(TreeNode root) {
        q.add(root);
        head = root;
        while(true)
        {
            TreeNode top = q.poll();
            if(top.left != null)
            {
                q.add(top.left);
            }
            else
            {
                nip = top;
                    break;
            }
            if(top.right != null)
            {
                q.add(top.right);
            }
            else
            {
                nip =  top;
                break;
            }
        }
    }
    
    public int insert(int val) {
        int v = nip.val;
        TreeNode node = new TreeNode(val);
      
            if(nip.left == null){
                nip.left = node;
                q.add(nip.left);
            }
            else if(nip.right == null)
            {
                nip.right = node;
                q.add(nip.right);
                nip = q.poll();
            }
        return v; 
        
    }
    
    public TreeNode get_root() {
        return head;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */

// Count Good nodes 

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
    public int helper(TreeNode root, int max)
    {
        if(root == null)
            return 0;
        int ans = 0;
        if(root.val >= max)
            ans = 1;
        max = Math.max(max, root.val);
        int left = helper(root.left,max);
        int right = helper(root.right,max);
        ans += left + right;
        return ans;
    }
    public int goodNodes(TreeNode root) {
        return helper(root, Integer.MIN_VALUE);
    }
}

// House Robbery 3 

class pair{
    int ri = 0;
    int re = 0;
    pair(int ri,int re)
    {
        this.ri = ri;
        this.re = re;
    }
}
class Solution {
    public pair helper(TreeNode root)
    {
        if(root == null)
            return new pair(0,0);
        pair l = helper(root.left);
        pair r = helper(root.right);
        int ci = root.val + l.re + r.re;
        int ce = Math.max(l.ri,l.re) + Math.max(r.ri,r.re);
        return new pair(ci,ce);
    }
    public int rob(TreeNode root) {
        pair p = helper(root);
        return Math.max(p.ri,p.re);
    }
}


// Maximum Product of Splitted Binary Tree

 public static long modulo = (int)1e9 + 7;
    public static long totalsum = 0;
    public static long product = -(int)1e9;
    public long sum(TreeNode root)
    {
        if(root == null)
            return 0;
        return sum(root.left) + sum(root.right) + root.val;
    }
    public long helper(TreeNode root)
    {
        if(root == null)
            return 0;
        long l = helper(root.left);
        long r = helper(root.right);
        product = Math.max(product,Math.max(l*(totalsum-l),r*(totalsum - r)));
        return l+r+root.val;
    }
    
    public int maxProduct(TreeNode root) {
        if(root == null)
          return 0;
        totalsum = sum(root);
        product = -(int)1e9;
        helper(root);
        return (int)(product%modulo);
    }
}


// Populating Next Pointer

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null)
            return null;
        Node black = root;
        while(black!=null && black.left!=null )
        {
            Node level = black;
            while(true)
            {
                level.left.next = level.right;
                if(level.next==null)
                    break;
                level.right.next = level.next.left;
                level = level.next;
            }
            black = black.left;
        }
        return root;
    }
}


// Bottom View of a Binary Tree

import java.util.*;

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void width(TreeNode root, int hl,int[] minMax)
    {
        if(root == null)
        return;
        minMax[0] = Math.min(hl, minMax[0]);
        minMax[1] = Math.max(hl,minMax[1]);
        width(root.left,hl-1,minMax);
        width(root.right,hl+1,minMax);
        
    }
    public static class vpair{
        TreeNode treenode = null;
        int level = 0;
        vpair(TreeNode treenode , int level)
        {
            this.treenode = treenode;
            this.level = level;
        }
    }
    public static ArrayList<Integer> BottomView(TreeNode root) {
        if(root == null)
        return new ArrayList<>();
        
        ArrayList<Integer> ans= new ArrayList<>();
    
        int[]minMax = new int[2];
        width(root,0,minMax);
        
        int wid = minMax[1]- minMax[0] +1;
        for(int i=0;i<wid;i++)
        ans.add(0);
        
        LinkedList<vpair> que = new LinkedList<>();
        que.add (new vpair(root,Math.abs(minMax[0])));
        while(que.size()!=0)
        {
             int size = que.size();
             while(size-- > 0)
             {
                 vpair rp = que.removeFirst();
                 TreeNode t = rp.treenode;
                 int hl = rp.level;
                 ans.set(hl,t.val);
                 if(t.left!=null)
                 que.addLast(new vpair(t.left,hl-1));
                 
                 if(t.right!=null)
                 que.addLast(new vpair(t.right,hl+1));
                 
             }
        }
        return ans;
    }

// Path Sum in a binary Tree

import java.util.Scanner;

public class Main {
  public static Scanner scn = new Scanner(System.in);

  public static class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static boolean hasPathSum(TreeNode root, int targetSum) {
   if(root == null)
   return false;
   if(root.left == null && root.right == null)
   return (targetSum - root.val) == 0;
    return hasPathSum(root.left, targetSum-root.val)||hasPathSum(root.right, targetSum-root.val);
  }

// Sorted Doubly Linked list to binary search tree

import java.util.*;

class Main {
  public static Scanner scn = new Scanner(System.in);

  public static class Node {
    int val = 0;
    Node left = null;
    Node right = null;

    Node(int val) {
      this.val = val;
    }
  }
  
  public static Node getMid(Node node)
  {
      if(node == null || node.right==null)
      return node;
      
      Node slow = node, fast = node;
      while(fast.right!=null && fast.right.right!=null)
      {
          fast = fast.right.right;
          slow = slow.right;
      }
      return slow;
  }

  // left : prev, right : next
  public static Node SortedDLLToBST(Node head) {
  if(head == null || head.right == null)
  return head;
  Node midNode = getMid(head);
  Node forw = midNode.right , prev = midNode.left;
  
  forw.left = midNode.left = midNode.right =  null;
  if(prev!=null)
  prev.right = null;
  
  Node leftHead = prev!=null?head:null;
  Node rightHead = forw;
  Node root = midNode;
  root.left = SortedDLLToBST(leftHead);
  root.right = SortedDLLToBST(rightHead);
  return root;
}

  // Input_code===================================================

  public static void display(Node node) {
    if (node == null)
      return;

    StringBuilder sb = new StringBuilder();
    sb.append((node.left != null ? node.left.val : "."));
    sb.append(" -> " + node.val + " <- ");
    sb.append((node.right != null ? node.right.val : "."));

    System.out.println(sb.toString());

    display(node.left);
    display(node.right);

  }

  public static Node makeList(int n) {
    Node dummy = new Node(-1);
    Node prev = dummy;
    while (n-- > 0) {
      Node node = new Node(scn.nextInt());
      prev.right = node;
      node.left = prev;
      prev = prev.right;
    }

    Node head = dummy.right;
    head.left = dummy.right = null;

    return head;
  }

  public static void main(String[] args) {
    Node head = makeList(scn.nextInt());

    head = SortedDLLToBST(head);
    display(head);
  }

}

// Conevert Sorted LL into BST
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        return createBST(head, null);
    }
    public TreeNode createBST(ListNode head, ListNode tail){
        ListNode fast = head;
        ListNode slow = head;
        if(head == tail) return null;
        while(fast != tail && fast.next != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode node = new TreeNode(slow.val);
        node.left = createBST(head, slow);
        node.right = createBST(slow.next, tail);
        return node;
    }
}

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: new root
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null){
            return null;
        }
        if(root.left == null && root.right == null){
            return root;
        }
        TreeNode left =  upsideDownBinaryTree(root.left);
        TreeNode right = upsideDownBinaryTree(root.right);
        // Original left becomes root
        left.left = right;
        left.right = root;
        return left;
    }
}

// Width of shadow

import java.util.*;

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }
    public static void findwidth(TreeNode root,int[] maxMin,int level)
    {
        if(root == null)
        return ;
        maxMin[0] = Math.min(maxMin[0],level);
        maxMin[1] = Math.max(maxMin[1],level);
        findwidth(root.left,maxMin,level-1);
        findwidth(root.right,maxMin, level+1);
    }

    public static int width(TreeNode root) {
       if(root == null)
       return 0;
       int[] maxMin = new int[2]; 
       findwidth(root,maxMin,0);
       return maxMin[1]-maxMin[0]+1;
    }

    // input_section=================================================

    public static TreeNode createTree(int[] arr, int[] IDX) {
        if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
            IDX[0]++;
            return null;
        }
        TreeNode node = new TreeNode(arr[IDX[0]++]);
        node.left = createTree(arr, IDX);
        node.right = createTree(arr, IDX);

        return node;
    }

    public static void solve() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        int[] IDX = new int[1];
        TreeNode root = createTree(arr, IDX);

        System.out.println(width(root));
    }

    public static void main(String[] args) {
        solve();
    }
}

// Boundary Traversal of a binary Tree

// { Driver Code Starts
import java.io.*;
import java.util.*;

class Node  
{ 
    int data; 
    Node left, right; 
   
    public Node(int d)  
    { 
        data = d; 
        left = right = null; 
    } 
}
// } Driver Code Ends


//User function Template for Java

// class Node  
// { 
//     int data; 
//     Node left, right; 
   
//     public Node(int d)  
//     { 
//         data = d; 
//         left = right = null; 
//     } 
// }

class Solution
{
    void leftboundry(Node root,ArrayList<Integer> ans)
    {
        if(root == null || root.left == null && root.right == null)
        return;
        ans.add(root.data);
        if(root.left != null)
        leftboundry(root.left,ans);
        else
        leftboundry(root.right,ans);
    }
    void rightboundry(Node root,ArrayList<Integer> ans)
    {
        if(root == null || root.left == null && root.right == null)
        return;
        if(root.right != null)
        rightboundry(root.right,ans);
        else
        rightboundry(root.left,ans);
        ans.add(root.data);
    }
    void leaves(Node root, ArrayList<Integer> ans)
    {
        if(root == null)
        return;
        if(root.left == null && root.right == null)
        ans.add(root.data);
        leaves(root.left, ans);
        leaves(root.right, ans);
    }
    void solve(Node root, ArrayList<Integer> ans)
    {
        if(root == null)
        return;
        ans.add(root.data);
        leftboundry(root.left,ans);
        leaves(root.left,ans);
        leaves(root.right,ans);
        rightboundry(root.right,ans);
        
    }
	ArrayList <Integer> boundary(Node node)
	{
	    ArrayList<Integer> ans = new ArrayList<>();
	    if(node == null)
	    return ans;
	    solve(node,ans);
	    return ans;
	}
}

// Top View of BT 

import java.util.*;

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void width(TreeNode root, int hl,int[] minMax)
    {
        if(root == null)
        return;
        minMax[0] = Math.min(hl, minMax[0]);
        minMax[1] = Math.max(hl,minMax[1]);
        width(root.left,hl-1,minMax);
        width(root.right,hl+1,minMax);
        
    }
    public static class vpair{
        TreeNode treenode = null;
        int level = 0;
        vpair(TreeNode treenode , int level)
        {
            this.treenode = treenode;
            this.level = level;
        }
    }
    public static ArrayList<Integer> BottomView(TreeNode root) {
        if(root == null)
        return new ArrayList<>();
        
        ArrayList<Integer> ans= new ArrayList<>();
    
        int[]minMax = new int[2];
        width(root,0,minMax);
        
        int wid = minMax[1]- minMax[0] +1;
        for(int i=0;i<wid;i++)
        ans.add(0);
        
        LinkedList<vpair> que = new LinkedList<>();
        que.add (new vpair(root,Math.abs(minMax[0])));
        while(que.size()!=0)
        {
             int size = que.size();
             while(size-- > 0)
             {
                 vpair rp = que.removeFirst();
                 TreeNode t = rp.treenode;
                 int hl = rp.level;
                 ans.set(hl,t.val);
                 if(t.left!=null)
                 que.addLast(new vpair(t.left,hl-1));
                 
                 if(t.right!=null)
                 que.addLast(new vpair(t.right,hl+1));
                 
             }
        }
        return ans;
    }

    // input_section=================================================

    public static TreeNode createTree(int[] arr, int[] IDX) {
        if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
            IDX[0]++;
            return null;
        }
        TreeNode node = new TreeNode(arr[IDX[0]++]);
        node.left = createTree(arr, IDX);
        node.right = createTree(arr, IDX);

        return node;
    }

    public static void solve() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        int[] IDX = new int[1];
        TreeNode root = createTree(arr, IDX);

        ArrayList<Integer> ans = BottomView(root);
        for (Integer i : ans)
            System.out.print(i + " ");

    }

    public static void main(String[] args) {
        solve();
    }
}


// AVL Tree

public class avl {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        int ht = 0;
        int balf = 0;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    // add in bst
    public static TreeNode add(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = add(root.right, val);
        } else {
            root.left = add(root.left, val);
        }
        root = balanceTree(root);
        return root;
    }

    // remove from bst
    public static TreeNode remove(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val < val) {
            root.right = remove(root.right, val);
        } else if (root.val > val) {
            root.left = remove(root.left, val);
        } else {
            if (root.left == null || root.right == null)
                return root.left == null ? root.right : root.left;
            TreeNode mVal = getMax(root.left);
            root.val = mVal.val;
            root.left = remove(root.left, mVal.val);
        }
        root = balanceTree(root);
        return root;
    }

    // get maximum
    public static TreeNode getMax(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    // balance if needed
    public static TreeNode balanceTree(TreeNode root) {
        update(root);
        int balf = root.balf;
        if (balf == 2) {
            int balfleft = root.left.balf;
            if (balfleft == 1) {// rr
                return rr(root);
            } else {// lr
                root.left = ll(root.left);
                return rr(root);
            }
        } else if (balf == -2) {
            int balfright = root.right.balf;
            if (balfright == 1) {// rl
                root.right = rr(root.right);
                return ll(root);
            } else {// ll
                return ll(root);
            }
        }
        return root;
    }

    // update height and balance factor
    public static void update(TreeNode root) {
        int lh = root.left == null ? -1 : root.left.ht;
        int rh = root.right == null ? -1 : root.right.ht;
        root.ht = Math.max(lh, rh) + 1;
        root.balf = lh - rh;
    }

    // left left rotation
    public static TreeNode ll(TreeNode A) {
        TreeNode B = A.right;
        TreeNode Bl = B.left;
        B.left = A;
        A.right = Bl;
        update(A);
        update(B);
        return B;
    }

    // right right rotation
    public static TreeNode rr(TreeNode A) {
        TreeNode B = A.left;
        TreeNode Br = B.right;
        B.right = A;
        A.left = Br;
        update(A);
        update(B);
        return B;
    }

    // display tree
    public static void display(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.left != null ? root.left.val : ".");
        System.out.print("->" + root.val + "<-");
        System.out.print(root.right != null ? root.right.val : ".");
        System.out.println();

        display(root.left);
        display(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int i = 1; i < 20; i++) {
            root = add(root, i);
        }
        display(root);
    }
}

// Burning Tree

import java.util.*;

public class Main {
  public static Scanner scn = new Scanner(System.in);

  public static class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static ArrayList<ArrayList<Integer>> burningTree(TreeNode root, int data) {
      if(root == null)
      return new ArrayList<>();
      HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();          
      burningTree_(root, data, hm);
      int i = 0;
      ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
      while(hm.containsKey(i))
      {
            ans.add(hm.get(i));
            i++;
        }
        return ans;
  }
  public static int burningTree_(TreeNode node, int data, HashMap<Integer,ArrayList<Integer>> hm)
  {
    if(node == null)
      return -1;
    if(node.val == data)
    {
        allaway(node, null,0, hm);
        return 1;
    }
    int left = burningTree_(node.left,data,hm);
    if(left!=-1)
    {
        allaway(node,node.left,left,hm);
        return left+1;
    }
    int right = burningTree_(node.right,data,hm);
    if(right!=-1)
    {
        allaway(node,node.right,right,hm);
        return right+1;
    }
    return -1;
  }
  public static void allaway(TreeNode node,TreeNode blocker,int time, HashMap<Integer,ArrayList<Integer>> hm)
  {
      if(node==null || node == blocker)
      return;
      if(!hm.containsKey(time))
      {
          hm.put(time, new ArrayList<>());
      }
      hm.get(time).add(node.val);
      allaway(node.left,blocker,time+1,hm);
      allaway(node.right,blocker,time+1,hm);
  }

  // input_section=================================================

public static void solve() {
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++)
      arr[i] = scn.nextInt();

    int[] IDX = new int[1];
    TreeNode root = createTree(arr, IDX);
    int fireNode = scn.nextInt();

    ArrayList<ArrayList<Integer>> ans = burningTree(root, fireNode);
    if (ans.size() == 0)
      System.out.println();
    for (ArrayList<Integer> ar : ans) {
      for (Integer ele : ar)
        System.out.print(ele + " ");
      System.out.println();
    }
  }

  public static void main(String[] args) {
    solve();
  }
}

// Amount of time for the binary tree to get infected 

class Solution {
    public static int amount = 0;
    public void countTime(TreeNode root, TreeNode blocker, int time){
        if(root == null || root == blocker){
            return;
        }
        amount = Math.max(amount, time);
        countTime(root.left, blocker, time+1);
        countTime(root.right, blocker, time+1);
    }
    public int findNode(TreeNode root, int start){
        if(root == null){
            return -1;
        }
        if(root.val == start){
            countTime(root, null, 0);
            return 1;
        }
        int left = findNode(root.left, start);
        if(left != -1){
            countTime(root, root.left, left);
            return left+1;
        }
        int right = findNode(root.right, start);
        if(right != -1){
            countTime(root, root.right, right);
            return right+1;
        }
        return -1;
    }
    public int amountOfTime(TreeNode root, int start) {
        amount = 0;
        findNode(root, start);
        return amount;
    }
}

// LCA of Binary Tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private static TreeNode LCA = null;
    public boolean DFS_LCS(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return false;
        }
        boolean self = (root == p || root == q);
        boolean left = DFS_LCS(root.left, p, q);
        if(LCA != null){
            return true;
        }
        boolean right = DFS_LCS(root.right, p, q);
        if(LCA != null){
            return true;
        }
        if((left && right) || (left && self) || (right && self)){
            LCA = root;
        }
        return left || self || right;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LCA = null;
        DFS_LCS(root, p ,q);
        return LCA;
    }
}

//  Clone a Binary Tree

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: The root of binary tree
     * @return: root of new tree
     */
     public TreeNode leftcloned(TreeNode root)
     {
         if(root == null)
         return null;
         TreeNode l = leftcloned(root.left);
         TreeNode r = leftcloned(root.right);
         TreeNode temp = new TreeNode(root.val);
         temp.left = l;
         root.left = temp;
         root.right = r;
         return root;
     }

    //  public void upddatingrandom(TreeNode node)
    //  {
    //      if(root == null)
    //      return;
    //      if(root.random != null)
    //      root.left.random = root.random.left;
    //      upddatingrandom(root.left);
    //       upddatingrandom(root.right);
    //  }

     public TreeNode breakingNode(TreeNode root){
        if(root == null)
        {
            return null;
        }
        TreeNode tr = root.left;
        root.left = root.left.left;
        TreeNode l = breakingNode(root.left);
        TreeNode r = breakingNode(root.right);
        tr.left = l;
        tr.right = r;
        return tr;
     }


    public TreeNode cloneTree(TreeNode root) {
        // write your code here
        if(root == null)
        return null;
        TreeNode root1 = leftcloned(root);
       // upddatingrandom(root1);
        TreeNode node = breakingNode(root1);
        return node;
    }
}

// Delete nodes and return forest

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
    public TreeNode helper(TreeNode root, HashSet<Integer> hm, List<TreeNode>ans)
    {
        if(root == null)
            return null;
        root.left = helper(root.left,hm,ans);
        root.right = helper(root.right,hm,ans);
        if(hm.contains(root.val))
        {
            if(root.left != null)
                ans.add(root.left);
            if(root.right!= null)
                ans.add(root.right);
            return  null;
        }
        return root;
    }
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        if(root == null)
            return ans;
        HashSet<Integer> hm = new HashSet<>();
        for(int val : to_delete)
            hm.add(val);
        
        TreeNode a = helper(root,hm,ans);
        if(a!= null)
            ans.add(a);
        return ans;
    }
}

// Path Sum 

// pathSum 1 
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
     if(root == null)
         return false;
     if(root.left == null && root.right == null && targetSum - root.val == 0)
         return true;
        return hasPathSum(root.left,targetSum - root.val) || hasPathSum(root.right,targetSum - root.val);
    }
}


//pathsum - 2

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
    public void helper(TreeNode root, List<List<Integer>> ans, List<Integer> smallans,int targetSum)
    {
        if(root == null)
            return;
        if(root.left == null && root.right == null && targetSum- root.val == 0)
        {
            smallans.add(root.val);
           ans.add(new ArrayList<>(smallans));
            smallans.remove(smallans.size()-1);
            return;
        }
        smallans.add(root.val);
        helper(root.left,ans,smallans,targetSum - root.val);
        helper(root.right,ans,smallans,targetSum - root.val);
        smallans.remove(smallans.size()-1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        List<Integer> smallans = new ArrayList<>();
        helper(root,ans,smallans,targetSum);
        return ans;
    }
}

//path sum - 3

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
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null)
          return 0;
    HashMap<Integer,Integer> hm = new HashMap<>();
        hm.put(0,1);
    int c = pathSum_(root,targetSum, 0, hm);
        return c;
    }
    public int pathSum_(TreeNode root, int tar,int sum, HashMap<Integer,Integer>map)
    {
        if(root == null)
          return 0;
       int count = 0;
        sum+=root.val;
        if(map.containsKey(sum-tar)){
            count+=map.get(sum - tar);
        }
        map.put(sum, map.getOrDefault(sum,0)+1);
        count += pathSum_(root.left,tar,sum,map);
        count += pathSum_(root.right,tar,sum,map);
        map.put(sum,map.getOrDefault(sum,0)-1);
        return count;
    }
}

// ???????????

// Longest Univalue Path

class Solution {
    public static int ans = 0;
    public class pair{
        int value;
        int height;
        pair(int value){
            this.value = value;
            this.height = 1;
        }
        pair(int value, int height){
            this.value = value;
            this.height = height;
        }
    }
    public pair longestUnivaluePath_(TreeNode root){
        if(root == null){
            return new pair(Integer.MIN_VALUE, 0);
        }
        pair left = longestUnivaluePath_(root.left);
        pair right = longestUnivaluePath_(root.right);
        pair me = new pair(root.val);
        if(left.value == me.value && right.value == me.value){
            ans = Math.max(ans, left.height + right.height + 1);
            me.height = Math.max(1, Math.max(left.height, right.height) + 1);
            return me;
        }else if(left.value != me.value && right.value != me.value){
            ans = Math.max(ans, 1);
            return me;
        }else if(left.value == me.value){
            me.height += left.height;
            ans = Math.max(ans, Math.max(right.height, me.height));
            return me;
        }else if(right.value == me.value){
            me.height += right.height;
            ans = Math.max(ans, Math.max(left.height, me.height));
            return me;
        }else{
            return null;
        }
    }
    public int longestUnivaluePath(TreeNode root) {
        ans = Integer.MIN_VALUE;
        if(root == null){
            return 0;
        }
        longestUnivaluePath_(root);
        return ans - 1;
    }
}

// Trim a Binary Search Tree
class Solution {
    public TreeNode trimBST_(TreeNode root, int low, int high){
        if(root == null){
            return root;
        }
        root.left = trimBST_(root.left, low, high);
        root.right = trimBST_(root.right, low, high);
        if(root.val < low){
            System.out.println("Out of range : " + root.val);
            return root.right;
        }else if(root.val > high){
            return root.left;
        }
        return root;
    }
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return trimBST_(root, low, high);
    }
}

// find Duplicate BT

class Solution {
    List<TreeNode> res;
    HashMap<String, Integer> hm;
    public String pos(TreeNode root){
        if(root == null){
            return "#";
        }
        String serial = root.val + "," + pos(root.left) + "," + pos(root.right);
        int freq = hm.getOrDefault(serial, 0);
        hm.put(serial, ++freq);
        if(freq == 2){
            res.add(root);
        }
        return serial;
    }
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res = new ArrayList<>();
        hm = new HashMap<>();
        pos(root);
        return res;
    }
}

// Sum of Distances in Tree

class Solution {
    public int root_results = 0;
    public static ArrayList<Integer> count;
    public int dfsRoot(ArrayList<ArrayList<Integer>> graph, int curr, int prev, int depth){
        int totalCount = 1;
        root_results += depth;
        for(int nbr : graph.get(curr)){
            if(nbr == prev){
                continue;
            }
            totalCount += dfsRoot(graph, nbr, curr, depth+1);
        }
        count.set(curr, totalCount);
        return totalCount;
    }
    public void DFS(ArrayList<ArrayList<Integer>> graph, int curr, int prev, int[] result, int n){
        for(int nbr : graph.get(curr)){
            if(nbr == prev){
                continue;
            }
            result[nbr] = result[curr] - count.get(nbr) + (n - count.get(nbr));
            DFS(graph, nbr, curr, result, n);
        }
    }
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);
        count = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            count.add(0);
        }

        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edg : edges){
            int u = edg[0];
            int v = edg[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        root_results = 0;
        dfsRoot(graph, 0, -1, 0);
        int[] result = new int[n];
        result[0] = root_results;
        DFS(graph, 0, -1, result, n);
        return result;
    }
}

// Flip Equivalent Binary Trees

class Solution {
    boolean flipEquiv_(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){
            return true;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        if(root1.val != root2.val){
            return false;
        }
        boolean ans1 = true, ans2 = true;
        ans1 = flipEquiv_(root1.left, root2.left) && flipEquiv_(root1.right, root2.right);
        ans2 = flipEquiv_(root1.left, root2.right) && flipEquiv_(root1.right, root2.left);
        return ans1 || ans2;
    }
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){
            return true;
        }else if(root1 == null || root2 == null){
            return false;
        }
        return flipEquiv_(root1, root2);
    }
}


// Check Completeness of a Binary Tree

class Solution {
    public boolean isCompleteTree_(TreeNode root){
        if(root == null){
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>(Arrays.asList(root));
        while(q.peek() != null){
            TreeNode node = q.poll();
            q.offer(node.left);
            q.offer(node.right);
        }

        //Removing NULL from the end of Queue
        while(!q.isEmpty() && q.peek() == null){
            q.poll();
        }

        // Check if there are any remaining nodes in the Queue, if so then queue is not 
        // complete as if there might be some null node
        return q.isEmpty();
    }
    public boolean isCompleteTree(TreeNode root) {
        return isCompleteTree_(root);
    }
}



