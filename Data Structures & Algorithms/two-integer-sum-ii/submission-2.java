class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // [1,2,3,4, 5, 6, 7, 8, 9, 18, 20]
        // low=1, high=10 => high =5 (6 val) =>  5+6<
        int low=0, high=numbers.length-1, n=numbers.length-1;
        int[] res=new int[2];
        while(low<high){
            if(numbers[low]+numbers[high] == target){
                res[0]=low+1;
                res[1]=high+1;
                return res;
            }
            if(numbers[low]+numbers[high] < target){
                low++;
            }
            else {
                    high--;
                }
        }
        return res;
        }
}
