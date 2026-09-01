class Solution {
    public int findMin(int[] nums) {
        int n=nums.length;
        int low=0, high=n-1;
        int mid=0;
        if(nums[low]<=nums[high])
            return nums[low];
        while(low!=high){
            mid=(low + high)/2;
            System.out.println("mid1 : "+nums[mid]);
            if(nums[mid]>nums[high]){
                low=mid+1;
                System.out.println("low1 : "+nums[low]);
            } else {
                high=mid;
                System.out.println("mid : "+nums[mid]);
                System.out.println("low : "+nums[low]);
            }
        }
        return nums[low];     
    }
}
