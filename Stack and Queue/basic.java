// Introduction TO STACKS

// Duplicate Brackets

public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        Stack<Character> st = new Stack<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == ')'){
                if(st.peek() == '('){
                    System.out.println(true);
                    return;
                }
                else{
                    while(st.size() > 0 && st.peek() != '('){
                        st.pop();
                    }
                    st.pop();
                }
            }
            else
            st.push(ch);
        }
        System.out.println(false);
    }

// Balanced Brackets

public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        Stack<Character> st = new Stack<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch=='(' || ch=='{' || ch=='['){
                st.push(ch);
                continue;
            }else if(ch==')' || ch==']' || ch=='}'){
                if(ch == ')' && (st.size()==0 || st.peek()!='(')){
                    System.out.println(false);
                    return;
                }
                else if(ch==']' && (st.size()==0 || st.peek()!='[')){
                    System.out.println(false);
                    return;
                }
                else if(ch=='}' && (st.size()==0 || st.peek()!='{')){
                    System.out.println(false);
                    return;
                }
                st.pop();
            }
        }
        if(st.size() == 0)
        System.out.println(true);
        else
        System.out.println(false);
    }

// Next Greated Element to Right

public static int[] solve(int[] arr){
    Stack<Integer> st = new Stack<>();
    int n = arr.length;
    int[] nge = new int[n];
    nge[n-1] = -1;
    st.push(n-1);
    for(int i=n-2; i>=0; i--){
        while(st.size() > 0 && arr[i] >= arr[st.peek()]){
            st.pop();
        }
        if(st.size() == 0)
            nge[i] = -1;
        else
            nge[i] = arr[st.peek()];
        st.push(i);
    }
   return nge;
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


// Largest Area Histogram

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
       a[i] = Integer.parseInt(br.readLine());
    }
    
    Stack<Integer> st = new Stack<>();
    // right smaller element
    int[] rge = new int[n];
    rge[n-1] = n;
    st.push(n-1);
    for(int i = n-2; i>=0; i--){
        while(st.size() > 0 && a[i] <= a[st.peek()])
        st.pop();
        if(st.size() == 0)
        rge[i] = n;
        else
        rge[i] = st.peek();
        st.push(i);
    }
    // left smaller element
    st = new Stack<>();
    int[] lge = new int[n];
    lge[0] = -1;
    st.push(0);
    for(int i=1; i<n; i++){
        while(st.size() > 0 && a[i] <= a[st.peek()]){
            st.pop();
        }
        if(st.size()==0)
        lge[i] = -1;
        else
        lge[i] = st.peek();
        st.push(i);
    }
    
    int ans=Integer.MIN_VALUE;
    for(int i=0; i<n; i++){
        ans = Math.max(ans, a[i]*(rge[i]-lge[i]-1));
    }
    System.out.println(ans);
    // code
 }


 // Largest Area Histogram - 2 (Time Optimized) // 1 Traversal
public static int largestHistogram(int[] heights){
    Stack<Integer> st = new Stack<>();
    st.push(-1);
    int maxArea = Integer.MIN_VALUE;
    for(int i=0; i<heights.length; i++){
        int val = i==heights.length ? 0 : heights[i];
        while(st.peek()!=-1 && heights[i] >= val){
            int rm = i;
            int h = heights[st.pop()];
            int lm = st.peek();
            maxArea = Math.max(maxArea, h*(rm-lm-1));
        }
        st.push(i);
    }
}


 // Sliding Window Maximum

 public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
       a[i] = Integer.parseInt(br.readLine());
    }
    int k = Integer.parseInt(br.readLine());
    
    int[] nge = new int[n];
    Stack<Integer> st = new Stack<>();
    st.push(n-1);
    nge[n-1] = n;
    for(int i= n-2; i>=0; i--){
        while(st.size()>0 && a[i] > a[st.peek()])
        st.pop();
        if(st.size() == 0)
        nge[i] = n;
        else
        nge[i] = st.peek();
        st.push(i);
    }
    
    int j=0;
    for(int i=0; i<=n-k; i++){
        if(j < i)
        j = i;
        while(nge[j] < i+k){
            j = nge[j];
        }
        System.out.println(a[j]);
    }
 }

 // Infix Evaluation

 public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();
    Stack<Integer> operand = new Stack<>();
    Stack<Character> operator = new Stack<>();
    for(int i=0; i<exp.length(); i++){
        char ch = exp.charAt(i);
        if(ch == '('){
            operator.push(ch);
        }
        else if(Character.isDigit(ch)){
            operand.push(ch-'0');
        }
        else if(ch=='+' || ch=='-' || ch=='*' || ch=='/'){
            while(operator.size()>0 && operator.peek()!='(' && precedence(ch) <= precedence(operator.peek())){
                int val2 = operand.pop();
                int val1 = operand.pop();
                char op = operator.pop();
                int res = evaluate(val1, val2, op);
                operand.push(res);
            }
            operator.push(ch);
        }
        else if(ch == ')'){
            while(operator.size()>0 && operator.peek()!='('){
                int val2 = operand.pop();
                int val1 = operand.pop();
                char op = operator.pop();
                int res = evaluate(val1, val2, op);
                operand.push(res);
            }
            if(operator.size() > 0)
                operator.pop();
        }
    }
    while(operator.size() > 0){
        int val2 = operand.pop();
        int val1 = operand.pop();
        char op = operator.pop();
        int res = evaluate(val1, val2, op);
        operand.push(res);
    }
    System.out.println(operand.pop());
 }
 public static int evaluate(int val2, int val1, char op){
    if(op == '+'){
        return val2+val1;
    }
    else if(op == '-'){
        return val2-val1;
    }
    else if(op == '*'){
        return val2*val1;
    }
    else{
        return val2/val1;
    }
 }
 public static int precedence(char ch)
 {
    if(ch=='+' || ch=='-')
    return 1;
    else if(ch=='*' || ch=='/') 
    return 2;
    return -1;
 }
}

// Infix Conversion

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();
    Stack<String> pre = new Stack<>();
    Stack<String> post = new Stack<>();
    Stack<Character> operator = new Stack<>();
    for(int i=0; i<exp.length(); i++){
        char ch = exp.charAt(i);
        if(ch=='('){
            operator.push(ch);
        }
        else if((ch>='a'&& ch<='z') || (ch>='0'&& ch<='9') || (ch>='A'&& ch<='Z')){
            pre.push(ch+"");
            post.push(ch+"");
        }
        else if(ch=='+' || ch=='-' || ch=='*' || ch=='/'){
            while(operator.size()>0 && operator.peek()!='(' && precedence(ch) <= precedence(operator.peek())){
                char op = operator.pop();
                String v2 = pre.pop();
                String v1 = pre.pop();
                String res = op+v1+v2;
                pre.push(res);
                
                String vp2 = post.pop();
                String vp1 = post.pop();
                String rest = vp1+vp2+op;
                post.push(rest);
            }
            operator.push(ch);
        }
        else if(ch == ')'){
            while(operator.size()>0 && operator.peek()!='('){
                char op = operator.pop();
                String v2 = pre.pop();
                String v1 = pre.pop();
                String res = op+v1+v2;
                pre.push(res);
                
                String vp2 = post.pop();
                String vp1 = post.pop();
                String rest = vp1+vp2+op;
                post.push(rest);
            }
            if(operator.size() > 0)
            operator.pop();
        }
    }
    while(operator.size()>0 && operator.peek()!='('){
        char op = operator.pop();
        String v2 = pre.pop();
        String v1 = pre.pop();
        String res = op+v1+v2;
        pre.push(res);
                
        String vp2 = post.pop();
        String vp1 = post.pop();
        String rest = vp1+vp2+op;
        post.push(rest);
    }
    System.out.println(post.pop());
    System.out.println(pre.pop());
    // code
 }
 public static int precedence(char ch){
    if(ch == '+' || ch == '-')
    return 1;
    else 
    return 2;
 }
}


// Celebrity Problem

 public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        for(int i=0; i<n; i++){
            st.push(i);
        }
        while(st.size() > 1){
            int a = st.pop();
            int b = st.pop();
            if(arr[a][b] != 1){
                st.push(a);
            }
            else{
                st.push(b);
            }
        }
        int pc = st.pop();
        int flag = 0;
        for(int i=0; i<n; i++){
            if(arr[pc][i] != 0 && i!=pc){
                flag = 1;
                System.out.println("none");
                break;
            }
            if(arr[i][pc] != 1 && i!=pc){
                flag = 1;
                System.out.println("none");
                break;
            }
        }
        if(flag ==0){
            System.out.println(pc);
        }
    }

//Postfix Evaluation -> Convert into Inorder and Preorder and calculate value

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();
    Stack<String> pre = new Stack<>();
    Stack<String> in = new Stack<>();
    Stack<Integer> operand = new Stack<>();
    
    for(int i=0; i<exp.length(); i++){
        char ch = exp.charAt(i);
        if(ch=='+' || ch=='-' || ch=='*' || ch=='/'){
            
            int val2 = operand.pop();
            int val1 = operand.pop();
            int ans = evaluate(val1, val2, ch);
                
            String in2 = in.pop();
            String in1 = in.pop();
            String inAns = "("+in1+ch+in2+")";
                
            String pre2 = pre.pop();
            String pre1 = pre.pop();
            String preAns = ch+pre1+pre2+"";
                
            operand.push(ans);
            in.push(inAns);
            pre.push(preAns);
        }
        else{
            operand.push(ch-'0');
            pre.push(ch+"");
            in.push(ch+"");
        }
    }
    
    System.out.println(operand.pop());
    System.out.println(in.pop());
    System.out.println(pre.pop());
    // code
 }

 public static int evaluate(int val1, int val2, char op){
    if(op == '+')
        return val1+val2;
    else if(op=='-')
        return val1-val2;
    else if(op=='*')
        return val1*val2;
    else
        return val1/val2;
 }
}

// Now for Prefix : the procedure remains same -> just reverse the string and follow the same steps

// Merge Overlapping Intervals

public class pair implements Comparable<pair>{
        int u, v;
        pair(int u, int v){
            this.u = u;
            this.v = v;
        }
        public int compareTo(pair other){
            if(this.u != other.u)
            return this.u - other.u;
            else
            return this.v - other.v;
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
            pair top = ar[i];
            pair curr = st.peek();
            if(curr.v >= top.u){
                st.pop();
                pair p = new pair(0, 0);
                p.u = Math.min(curr.u, top.u);
                p.v = Math.max(curr.v, top.v);
                st.push(p);
            }
            else{
                st.push(top);
            }
        }
        int[][] ans = new int[st.size()][2];
        for(int i=st.size()-1; i>=0; i--){
            ans[i][0] = st.peek().u;
            ans[i][1] = st.peek().v;
            st.pop();
        }
        return ans;
    }

    // Construct Smallest Number from DI string

    class Solution {
    public String smallestNumber(String pattern) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> st = new Stack<>();
        int val = 1;
        for(int i=0; i<pattern.length(); i++){
            st.push(val++);
            char ch = pattern.charAt(i);
            if(ch == 'I'){
                while(st.size() > 0){
                    sb.append(st.pop()+"");
                }
            }
        }
        st.push(val++);
        while(st.size() > 0){
            sb.append(st.pop()+"");
        }
        return sb.toString();
    }
}

// MIN STACK 

public class Main {

  public static class MinStack {
    Stack<Integer> allData;
    Stack<Integer> minData;

    public MinStack() {
      allData = new Stack<>();
      minData = new Stack<>();
    }


    int size() {
        return allData.size();
    }

    void push(int val) {
        if(allData.size()==0 || val <= minData.peek()){
            minData.push(val);
        }
        allData.push(val);
    }

    int pop() {
        if(allData.size() == 0){
            System.out.println("Stack underflow");
            return -1;
        }
        else if(allData.peek() == minData.peek())
            minData.pop();
        return allData.pop();
    }

    int top() {
      if(allData.size() == 0){
        System.out.println("Stack underflow");
        return -1;
      }
      return allData.peek();
      
    }
}

// MIN Stack O(1)

class MinStack {
    int min;
    Stack<Integer> st;
    public MinStack() {
        st = new Stack<>();
    }
    
    public void push(int val) {
        if(st.size() == 0){
            min = val;
            st.push(val);
        }
        else{
            if(val < min){
                st.push(val + val - min);
                min = val;
            }
            else{
                st.push(val);
            }
        }
    }
    
    public void pop() {
        if(min <= st.peek())
        st.pop();
        else{
            int v = st.pop();
            min = 2*min - v;
        }
    }
    
    public int top() {
        if(min > st.peek()){
            return min;
        }
        return st.peek();
    }
    
    public int getMin() {
        return min;
    }
}
// Next Greater element to Left
class Solution{
    public static int[] help_greater_element_Right(int[] arr, int n){
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[n];
        nge[0] = -1;
        st.push(0);
        for(int i=1; i<n; i++){
            while(st.size()>0 && arr[i] >= arr[st.peek()])
            st.pop();
            if(st.size() == 0){
                arr[i] = -1;
            }
            else{
                arr[i] = st.peek();
            }
            st.push(i);
        }
        return arr;
    }
}

// Next Smaller Element to Right

class Solution {
	public static int[] next_Smaller_element_Right(int arr[], int n) 
	{ 
	    Stack<Integer> st = new Stack<>();
	    int[] nse = new int[n];
	    nse[n-1] = -1;
	    st.push(n-1);
	    for(int i=n-2; i>=0; i--){
	        while(st.size() > 0 && arr[i] <= arr[st.peek()])
	           st.pop();
	        if(st.size() == 0)
	           nse[i] = -1;
	        else
	           nse[i] = arr[st.peek()];
	        st.push(i);
	    }
	    return nse;
	} 
}

// Next Smaller Element to Left

class Solution {
	public static int[] next_Smaller_element_Left(int arr[], int n) 
	{ 
	    Stack<Integer> st = new Stack<>();
	    int[] nse = new int[n];
	    nse[0] = -1;
	    st.push0);
	    for(int i=1; i<n; i++){
	        while(st.size() > 0 && arr[i] <= arr[st.peek()])
	           st.pop();
	        if(st.size() == 0)
	           nse[i] = -1;
	        else
	           nse[i] = arr[st.peek()];
	        st.push(i);
	    }
	    return nse;
	} 
}

// Next Greater Element - 1

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums2.length;
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[n];
        nge[n-1] = -1;
        st.push(n-1);
        for(int i=n-2; i>=0; i--){
            while(st.size() > 0 && nums2[i] >= nums2[st.peek()])
                st.pop();
            if(st.size() == 0){
                nge[i] = -1;
            }
            else{
                nge[i] = nums2[st.peek()];
            }
            st.push(i);
        }
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int j=0; j<n; j++){
            hm.put(nums2[j], nge[j]);
        }   

        int[] ans = new int[nums1.length];
        for(int j=0; j<nums1.length; j++){
            ans[j] = hm.get(nums1[j]);
        }
        return ans;
    }
}

// Next Greater Element - 2

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int[] nge = new int[n];
        nge[n-1] = -1;
        st.push(n-1);
        for(int i=2*n-1; i>=0; i--){
            while(st.size() > 0 && nums[i%n] >= nums[st.peek()]){
                st.pop();
            }
            if(st.size() == 0){
                nge[i%n] = -1;
            }
            else{
                nge[i%n] = nums[st.peek()];
            }
            st.push(i%n);
        }
        return nge;
    }
}

// Validate Stack Sequence

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<Integer>();
        int i = 1, j=0, n = pushed.length;
        st.push(pushed[0]);
        while(i < n){
            if(st.size() > 0 && st.peek() == popped[j]){
                st.pop();
                j++;
            }else{
                st.push(pushed[i]);
                i++;
            }
        }
        while(j<n && st.size() > 0 && st.peek() == popped[j]){
            st.pop();
            j++;
        }
        if(j == n && st.size() == 0){
            return true;
        }
        return false;
    }
}


// Minimum Add to Make Parentheses Valid

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        int n = s.length();
        int closingP = 0;
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                System.out.println("(");
                st.push(ch);
            }else if(ch == ')'){
                if(st.size() > 0 && st.peek() == '('){
                    System.out.println(") : Countering the closing P");
                    st.pop();
                }else{
                    System.out.println("( : Increasing the closing P");
                    closingP++;
                }
            }
        }
        return st.size() + closingP;
    }
}

//  Remove Outermost Parentheses

class Solution {
    public void prepareAns(StringBuffer sb, String s, int i, int j){
        String ss = s.substring(j+1, i+1);
        sb.append(ss.substring(1, ss.length()-1));
    }
    public String removeOuterParentheses(String s) {
        StringBuffer sb = new StringBuffer();
        int counter = 0;
        int j = -1, n = s.length();
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                counter++;
            }else if(ch == ')'){
                counter--;
            }
            if(counter == 0){
                prepareAns(sb, s, i, j);
                j = i;
            }
        }
        return sb.toString();
    }
}

//  Score of Paranthesis

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        int ans = 0, n = s.length();
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push(-1);
            }else if(ch == ')'){
                if(st.size() > 0 && st.peek() == -1){
                    st.pop();
                    st.push(1);
                }else{
                    int tempAns = 0;
                    while(st.size() > 0 && st.peek() != -1){
                        tempAns += st.pop();
                    }
                    st.pop();
                    tempAns *= 2;
                    st.push(tempAns);
                }
            }
        }
        
        ans = 0;
        while(st.size() > 0){
            ans += st.pop();
        }
        return ans;
    }
}


// 