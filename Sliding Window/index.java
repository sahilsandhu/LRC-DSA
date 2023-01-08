// Longest Substring Without Repeating Characters

// TC : O(2N)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hs = new HashSet<>();
        int l=0, r=0, ans=0, n = s.length();
        //System.out.println("R and L value : " + l + " " + r);
        while(r < n){
            char ch = s.charAt(r);
            if(hs.contains(ch)){
                hs.remove(s.charAt(l));
                l++;
            } 
            else{
                hs.add(ch);
                ans = Math.max(r-l+1,ans);
                r++;
            }
            System.out.println(ch +" "+ ans);
            System.out.println("R and L value : " + l + " " + r); 
        }
        return ans;
    }
}

// TC : O(N)

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        int l=0, r=0, ans=0, n=s.length();
        while(r < n){
            char ch = s.charAt(r);
            if(hm.containsKey(ch))
                l = Math.max(hm.get(ch)+1, l);
            hm.put(ch, r);
            ans = Math.max(r-l+1, ans);
            r++;
        }
        return ans;

    }
}


