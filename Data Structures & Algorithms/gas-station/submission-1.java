class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n=cost.length;
        int total_sofar=0;
        int total_cost=0;
        int tank=0;
        int start=0;
        for(int i=0;i<n;i++){
            int net=gas[i]-cost[i];
            total_sofar+=net;
            tank+=net;
            if(tank<0){
                start=i+1;
                tank=0;
            }
        }
        return total_sofar>=0?start:-1;
}
}
