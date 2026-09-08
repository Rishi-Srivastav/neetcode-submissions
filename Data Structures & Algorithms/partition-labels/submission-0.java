class Solution {
    public List<Integer> partitionLabels(String s) {
        //store last index of all char.
        int n=s.length();
        int[] chr=new int[26];
        for(int i=0;i<n;i++){
            char c= s.charAt(i);
            chr[c-'a']=i;
        }
        List<Integer> list=new ArrayList<>();
        int end=0, start=0;
        for(int i=0;i<n;i++){
            //"xyxxyzbzbbisl"
            // 0123456789
            char c=s.charAt(i);
            if(start<=i){
                end=Math.max(end,chr[c-'a']);
            }  if(i==end){
                list.add(end-start+1);
                start=i+1;
                end=i+1;
            }
        }
        return list;
    }
}
