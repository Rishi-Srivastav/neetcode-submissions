class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n=position.length;
        int[][] cars = new int[n][2];
        for(int i=0;i<n;i++){
            cars[i][0]=position[i];
            cars[i][1]=speed[i];
        }
        Arrays.sort(cars, (a,b)->b[0]-a[0]);
        Stack<Double> timestack = new Stack<>();
        for(int i=0;i<n;i++){
            double time=(target-cars[i][0])*1.0/cars[i][1];
            if(timestack.isEmpty()){
                timestack.push(time);
            } else { 
                if(timestack.peek() < time)
                    timestack.push(time);
            }
        }
        return timestack.size(); 
    }
}
