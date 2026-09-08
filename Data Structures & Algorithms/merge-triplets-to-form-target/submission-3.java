class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int n=triplets.length;
        for(int i=0;i<n;i++){
            int[] ti=triplets[i];
            // System.out.println(" -> "+ti[0]+", "+ti[1]+ ", "+ti[2]);
             // System.out.println(" -> "+target[0]+", "+target[1]+ ", "+target[2]);
            if(ti[0]==target[0] && ti[1]==target[1] && ti[2]==target[2]){
               // System.out.println(" -> "+ti[0]+", "+ti[1]+ ", "+ti[2]);
                return true;
            }
            if(ti[0]>target[0] || ti[1]>target[1] || ti[2]>target[2]){
                continue;
            }
            
            for(int j=i+1;j<n;j++){
                int[] tj=triplets[j];
                if(tj[0]>target[0] || tj[1]>target[1] || tj[2]>target[2]){
                    continue;
                }
                if(Math.max(ti[0], tj[0]) == target[0] && Math.max(ti[1], tj[1]) == target[1] && Math.max(ti[2], tj[2]) == target[2]){
                    return true;
                } else {
                    tj[0]=Math.max(ti[0], tj[0]);
                    tj[1]=Math.max(ti[1], tj[1]);
                    tj[2]=Math.max(ti[2], tj[2]);
                }
            } 
        }
        return false;
    }
}
