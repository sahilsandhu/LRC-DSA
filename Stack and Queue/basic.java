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
    // right greater element
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
    // left greater element
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
