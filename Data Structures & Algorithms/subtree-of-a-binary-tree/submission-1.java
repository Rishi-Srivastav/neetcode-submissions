/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {  
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null)
            return true;
       if(root == null || subRoot == null)
          return false;  
        if(isit(root, subRoot)){
                return true;
            }
        else return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isit(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null)
            return true;
        else if(root == null || subRoot == null)
          return false;
        else if(root.val!=subRoot.val)
            return false;
        else {
            boolean left = isit(root.left, subRoot.left);
            boolean right = isit(root.right, subRoot.right);
            return left && right;
        } 
    }
}
