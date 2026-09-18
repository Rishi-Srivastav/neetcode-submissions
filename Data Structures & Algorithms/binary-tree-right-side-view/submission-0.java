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
    public List<Integer> rightSideView(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null)
            return new ArrayList<Integer>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)    
                    queue.offer(node.right);
            }
            lists.add(list);
        }
        return lists.stream().filter(list->!list.isEmpty())
        .map(list->list.get(list.size()-1)).toList();
    }

    public void rightview(TreeNode root, List<Integer> list){
        if(root==null)
            return;
        list.add(root.val);
        if(root.right!=null){
            rightview(root.right, list);
        } else {
            rightview(root.left, list);
        }
    }
}
