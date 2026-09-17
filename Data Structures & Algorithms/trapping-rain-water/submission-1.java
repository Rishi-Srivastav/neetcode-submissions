class Solution {
    public int trap(int[] height) {
        int area=0, n=height.length;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++){
            while(!stack.isEmpty() && height[i]>=height[stack.peek()]){
                    int mid=stack.pop();
                    if(!stack.isEmpty()){
                        int left=stack.peek();
                        int h= (Math.min(height[left], height[i])-height[mid]);
                        h= (h>0)?h:0;
                        int w=i-left-1;
                        area+=h*w;
                    }
            }
            stack.push(i);
        }
        return area;
    }
}
