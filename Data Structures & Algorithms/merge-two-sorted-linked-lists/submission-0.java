/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode l1=list1, l2=list2, res=null, head=null;
        while(l1!=null || l2!=null){
            if(res!=null) {
                res.next=new ListNode();
                res=res.next;
            } else {
                res=new ListNode();
                head=res;
            }

            if(l1==null){
                res.val=l2.val;
                l2=l2.next;
                continue;
            } if(l2==null){
                res.val=l1.val;
                l1=l1.next;
                continue;
            }
            ListNode lnxt1=list1.next;
            ListNode lnxt2=list2.next;
            
            if(l1.val>l2.val){
                res.val=l2.val;
                l2=l2.next;
            } else {
                res.val=l1.val;
                l1=l1.next;
        }
    }
    return head;
    }
}