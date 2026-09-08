class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int n=triplets.length;
        int max1=0, max2=0, max3=0;

        for(int i=0;i<n;i++){
            int[] ti=triplets[i];
           if(ti[0]>target[0] || ti[1]>target[1] || ti[2]>target[2]){
                continue;
            }

            max1=Math.max(max1, ti[0]);
            max2=Math.max(max2, ti[1]);
            max3=Math.max(max3, ti[2]);

            if(max1==target[0] && max2==target[1] && max3==target[2]){
                return true;
            }
        }
        return false;
    }
}
