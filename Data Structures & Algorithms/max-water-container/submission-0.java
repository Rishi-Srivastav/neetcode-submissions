class Solution {
    public int maxArea(int[] heights) {
        int low=0, high=heights.length-1;
        int max=0;
        while(low<high){
            max=Math.max(max, (high-low)*Math.min(heights[low], heights[high]));
            if(heights[low]<heights[high])
                low++;
             else
                high--;   
        }
        return max;
    }
}
