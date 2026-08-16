class Solution {

    int[] mem=null;

    public int climbStairs(int n) {
        mem=new int[n+1];
        if(n<=2) return n;
            mem[1]=1;
            mem[2]=2;
            if(n<=2) return mem[n];

            for(int i=3;i<=n;i++){
                mem[i] = mem[i-1] + mem[i-2];
            }
            return mem[n];
         }
}
