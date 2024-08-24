class Solution 
{
    //Function to find the maximum number of meetings that can
    //be performed in a meeting room.
    private static class pair{
        int st;
        int et;
        int idx;
        pair(int st, int et, int idx){
            this.st = st;
            this.et = et;
            this.idx = idx;
        }
    }
    private static class meetingComparator implements Comparator<pair>{
        public int compare(pair o1, pair o2){
            if(o1.et < o2.et){
                return -1; 
            }else if(o1.et > o2.et){
                return 1;
            }else if(o1.idx < o2.idx){
                return -1;
            }
            return 1;
        }
    }
    public static int maxMeetings(int start[], int end[], int n)
    {
        ArrayList<pair> al = new ArrayList<>();
        for(int i=0; i<n; i++){
            al.add(new pair(start[i], end[i], i+1));
        }
        meetingComparator comp = new meetingComparator();
        Collections.sort(al, comp);
        int count=1;
        int prev = al.get(0).et;
        for(int i=1; i<n; i++){
            if(al.get(i).st > prev){
                count++;
                prev = al.get(i).et;
            }
        }
        return count;
    }
}
