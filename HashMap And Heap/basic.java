// Find Number of Employees under every Manager

public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = Integer.parseInt(scn.nextLine());
    HashMap<String, HashSet<String>> hm = new HashMap<>();
    
    String ceo = "";
    for(int i=0;i<n;i++){
        String[] temp = scn.nextLine().split(" ");
        if(temp[1].equals(temp[0])){
            ceo = temp[1];
            
        }
        else{
            if(hm.containsKey(temp[1])){
                HashSet<String> hs = hm.get(temp[1]);
                hs.add(temp[0]);
             
            }
            else{
                HashSet<String> hs = new HashSet<>();
                hs.add(temp[0]);
                hm.put(temp[1],hs);
            }
        }
    }
    HashMap<String, Integer> ans = new HashMap<>();
    getSize(ceo, hm, ans);
    for(String mp : ans.keySet()){
        System.out.println(mp+" "+ans.get(mp));
    } 
  }
   public static int getSize(String curr, HashMap<String,HashSet<String>> tree, HashMap<String,Integer> ans){
       if(tree.containsKey(curr) == false)
       {
        ans.put(curr,0);
        return 1;
       }
       int sz = 0;
        for(String child : tree.get(curr)){
            int cs = getSize(child,tree,ans);
            sz += cs;
        }
        ans.put(curr, sz);
        return sz+1;
   }

// Find Itinerary From Tickets

public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int noofpairs_src_des = scn.nextInt();
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < noofpairs_src_des; i++) {
			String s1 = scn.next();
			String s2 = scn.next();
			map.put(s1, s2);	
		}
		HashMap<String, Boolean> hm = new HashMap<>();
	    for(String src: map.keySet()){
	        String dest = map.get(src);
	        hm.put(dest, false);
	        if(hm.containsKey(src) == false){
	            hm.put(src,true);
	        }
	    }
	    String src="";
	    for(String itr: hm.keySet()){
	        if(hm.get(itr) == true){
	            src = itr;
	            break;
	        }
	    }
	    while(true){
	        if(map.containsKey(src)){
	            System.out.print(src+" -> ");
	            src = map.get(src);
	        }
	        else{
	            System.out.print(src+".");
	            break;
	        }
	    }
	}

// Largest Subarray With Contiguous Elements

public static int solution(int[] arr) {
		int n = arr.length;
		int ans = 0;
		for(int i=0;i<n-1;i++){
		    int min = arr[i];
		    int max = arr[i];
		    HashSet<Integer> hs = new HashSet<>();
		    hs.add(arr[i]);
		    for(int j=i+1;j<n;j++){
		        if(hs.contains(arr[j]))
		        break;
		        else
		        hs.add(arr[j]);
		        min = Math.min(min,arr[j]);
		        max = Math.max(max,arr[j]);
		        if(max-min == j-i){
		            int len = j-i+1;;
		            ans = Math.max(len, ans);
		        }
		    }
		}

		return ans;
	}

// Count Of Substrings Having All Unique Characters

	public static int solution(String str) {
		int ans = 0, n = str.length();
		int i = -1, j = -1;
		HashMap<Character, Integer> hm = new HashMap<>();
		while(true){
		    boolean f1 = false, f2 = false;
		    while(i < n-1){
		        f1 = true;
		        i++;
		        char ch = str.charAt(i);
		        hm.put(ch, hm.getOrDefault(ch,0) + 1);
		        if(hm.get(ch)==2){
		            break;
		        }
		        else{
		            int len = i-j;
		            ans += len;
		        }
		    }
		    while(j < i){
		        f2 = true;
		        j++;
		        char ch = str.charAt(j);
		        hm.put(ch, hm.get(ch)-1);
		        if(hm.get(ch) == 1)
		        {
		            ans += (i-j);
		            break;
		        }
		    }
		    if(f1 == false && f2 == false){
		        break;
		    }
		}
		return ans;
	}

// Maximum Consecutive Ones - 2
// 1. You are given an array(arr) which contains only 0's and 1's and a number K.
// 2. You have to find the maximum number of consecutive 1's in the given array 
// if you can flip at most K zeroes.

 public static int solution(int[] arr, int k){
        int count = 0, j = -1, ans = 0;
        for(int i=0;i<arr.length; i++){
            if(arr[i] == 0)
            count++;
            while(count > 1){
                j++;
                if(arr[j] == 0)
                count--;
            }
            int len = j-i;
            if(len > ans)
            ans = len;
        }
        return ans;
    }

// Longest Substring With At Most K Unique Characters

public static int solution(String str, int k) {
		// write your code here
		int ans = 0;
		int i = -1, j = -1, n = str.length();
		HashMap<Character,Integer> hm = new HashMap<>();
		while(true){
		    boolean f1 = false, f2 = false;
		    while(i < n-1){
		        f1 = true;
		        i++;
		        char ch = str.charAt(i);
		        hm.put(ch, hm.getOrDefault(ch,0)+1);
		        if(hm.size() <= k){
		            int len = i-j;
		            if(len > ans)
		            ans = len;
		        }
		        else{
		            break;
		        }
		    }
		    while(j < i){
		        f2 = true;
		        j++;
		        char ch = str.charAt(j);
		        if(hm.get(ch) == 1)
		        {
		            hm.remove(ch);
		        }
		        else{
		            hm.put(ch, hm.get(ch)-1);
		        }
		        if(hm.size() > k)
		        continue;
		        else
		        break;
		    }
		    if(f1 == false && f2 == false)
		    break;
		}
		
		return ans;
	}
	

// Count Of Substrings Having At Most K Unique Characters

	public static int solution(String str, int k) {
		int ans = 0;
		int i = -1, j = -1;
		HashMap<Character,Integer> hm = new HashMap<>();
		while(true){
		    boolean f1 = false, f2 = false;
		    while(i < str.length()-1){
		        f1 = true;
		        i++;
		        char ch = str.charAt(i);
		        hm.put(ch, hm.getOrDefault(ch,0)+1);
		        if(hm.size() <= k){
		            ans += (i-j);
		        }
		        else{
		            break;
		        }
		    }
		    
		    while(j < i){
		        f2 = true;
		        j++;
		        char ch = str.charAt(j);
		        if(hm.get(ch) == 1)
		        hm.remove(ch);
		        else
		        hm.put(ch, hm.get(ch)-1);
		        
		        if(hm.size() > k)
		        continue;
		        else{
		        ans+=(i-j);
		        break;
		        }
		    }
		    if(f1 == false && f2 == false)
		    break;
		}
		return ans;
	}
	

// Find Anagram Mappings

public static class pair{
        int idx;
        ArrayList<Integer> al = new ArrayList<>();
        pair(int idx, ArrayList<Integer> al){
            this.idx = idx;
            this.al = al;
        }
    }
	public static int[] anagramMappings(int[] arr1, int[] arr2) {
		HashMap<Integer, pair> hm = new HashMap<>();
		for(int i=0;i<arr2.length;i++){
		    int val = arr2[i];
		    if(hm.containsKey(val) == true){
		        pair rp = hm.get(val);
		        rp.al.add(i);
		        hm.put(val,rp);
		    }
		    else{
		        ArrayList<Integer> temp = new ArrayList<>();
		        temp.add(i);
		        pair tp = new pair(0,temp);
		        hm.put(val,tp);
		    }
		}
		
		int[] ans = new int[arr1.length];
		int idx = 0;
		for(int i=0;i<arr1.length;i++){
		    int val = arr1[i];
		    pair rp = hm.get(val);
		    ans[idx++] = rp.al.get(rp.idx);
		    rp.idx++;
		    hm.put(val, rp);
		}
		return ans;
	}

// Powerful Number

	public static ArrayList<Integer> powerfulIntegers(int x, int y, int bound) {
		HashSet<Integer> hs = new HashSet<>();
		for(int i=1; i<bound; i*=x){
		    for(int j=1; i+j <= bound; j*=y){
		        hs.add(i+j);
		        if(y == 1)
		        break;
		    }
		    if(x == 1)
		    break;
		}
		return new ArrayList<>(hs);
	}

// Pairs With Given Sum In Two Sorted Matrices

public static int solve(int[][] num1, int[][] num2, int k) {
		// write your code here
		int ans = 0;
		HashMap<Integer, Integer> hs = new HashMap<>();
		for(int i=0; i<num1.length; i++){
		    for(int j=0; j<num1[0].length; j++){
		        hs.put(num1[i][j], hs.getOrDefault(num1[i][j], 0) + 1);
		    }
		}
		
		for(int i=0;i<num2.length;i++){
		    for(int j=0;j<num2[0].length; j++){
		        if(hs.containsKey(k - num2[i][j]))
		        ans+= hs.get(k - num2[i][j]);
		    }
		}
		return ans;
	}
