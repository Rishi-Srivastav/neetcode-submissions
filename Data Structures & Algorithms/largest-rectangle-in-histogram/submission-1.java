class Solution {
    public int largestRectangleArea(int[] heights) {
        int n=heights.length;
        Stack<Integer> stack = new Stack<>();
        int max=0;
        for(int i=0;i<n;i++){
            while(!stack.isEmpty() && heights[i]<heights[stack.peek()]){
                int t=stack.pop();
                int width = (!stack.isEmpty()?i-stack.peek()-1:i);
                max=Math.max(max, heights[t]*width);
            }
            stack.push(i);
        }
        int prev=0;
        while(!stack.isEmpty()){
            int t=stack.pop();
            int w=(!stack.isEmpty()?n-stack.peek()-1:n);
            max=Math.max(max, heights[t]*w);
        }
        return max;
    }
}
