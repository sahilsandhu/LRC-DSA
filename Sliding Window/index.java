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


// length of longest substring with k repeating characters

public int longestkSubstr(String s, int k) {
        int n = s.length();
        int i = -1, j = -1;
        int ans = -1;
        HashMap<Character, Integer> hm = new HashMap<>();
        while(true){
            boolean f1 = false, f2 = false;
            while(i < n-1){
                f1 = true;
                i++;
                char ch = s.charAt(i);
                hm.put(ch, hm.getOrDefault(ch, 0)+1);
                if(hm.size() < k)
                    continue;
                else if(hm.size() == k){
                    int len = i-j;
                    if(len > ans)
                       ans = len;
                }
                else{
                    break;
                }
            }
            while(j < i){
                f1 = true;
                j++;
                char ch = s.charAt(j);
                if(hm.get(ch) == 1)
                    hm.remove(ch);
                else
                    hm.put(ch, hm.get(ch)-1);
                
                if(hm.size() > k){
                    continue;
                }
                else if(hm.size() == k){
                    int len = i-j;
                    if(len > ans)
                       ans = len;
                    break;
                }
            }
            if(f1 == false && f2 == false)
            break;
        }
        return ans;
    }


