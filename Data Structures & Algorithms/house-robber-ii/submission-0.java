class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==1) return nums[0];
        int[] nums1 = new int[n-1];
        int[] nums2 = new int[n-1];
        for(int i=0;i<n;i++){
            if(i!=n-1)
                nums1[i]=nums[i];
            if(i!=n-1)    
                nums2[i]=nums[i+1];    
        }
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        int a = rob1(nums1);
        int b = rob1(nums2);

        return Math.max(a, b);
    }

    public int rob1(int[] arr){
        int n=arr.length;
        if(n==1) return arr[0];
        
        int[] mem=new int[n];
        mem[0]=arr[0];
        mem[1]=Math.max(arr[0], arr[1]);
        
        for(int i=2;i<n;i++){
                mem[i]=Math.max(mem[i-2]+arr[i],mem[i-1]);
        }
        return mem[n-1];
    }

    }
