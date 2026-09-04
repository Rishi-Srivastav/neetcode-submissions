class Solution {

    // temperatures = [30,38,30,36,35,40,28]
   //temperature1 = [ 1, 4, 1, 2, 1, 0, 0]
   //stack = 0,  
   // res = 1,  
    public int[] dailyTemperatures(int[] temp) {
        int n=temp.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++){
            if(stack.isEmpty() || temp[stack.peek()]>temp[i]){
                stack.push(i);
            }
            while(!stack.isEmpty() && temp[stack.peek()]<temp[i]){
                res[stack.peek()]=i-stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
