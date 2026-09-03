class MinStack {

Stack<Integer> stack;
Stack<Integer> minstack;
// 1,2,5,3,0,
// 1,1,1,1,0
    public MinStack() {
        stack = new Stack<>();
        minstack=new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if(minstack.isEmpty())
            minstack.push(val);
        else{
            minstack.push(Math.min(val, minstack.peek()));
        }
    }
    
    public void pop() {
        stack.pop();
        minstack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minstack.peek();
    }
}
