class Solution {
    public int search(int[] nums, int target) {
        int n=nums.length-1;
        int low=0, high=n;
        int mid=0;
        while(low<=high){
            mid=(low+high)/2;
            if(target==nums[mid])
                return mid;
            else if(target>nums[mid]){
                low=mid+1;
            }  else {
                high=mid-1;
            }
        }
        return -1;
    }
}
