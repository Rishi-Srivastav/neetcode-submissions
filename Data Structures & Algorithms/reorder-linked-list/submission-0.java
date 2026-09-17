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
    public void reorderList(ListNode head) {
        // -1 2 4 6 8
        ListNode start=new ListNode(-1);
        start.next=head;
        ListNode slow=start, fast=start;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode l1=head;
        ListNode l2=slow.next;
        slow.next=null;
        print(l2);
        l2=reverse(l2);
        print(l1);
        print(l2);
        ListNode dummy=new ListNode(-1);
        ListNode node=dummy;
        while(l1!=null && l2!=null){
            node.next=l1;
            l1=l1.next;
            node.next.next=l2;
            l2=l2.next;
            node=node.next.next;
        }
        while(l1!=null){
            node.next=l1;
            l1=l1.next;
        }
        while(l2!=null){
            node.next=l2;
            l2=l2.next;
        }
        head=dummy.next;
    }

    public ListNode reverse(ListNode head){
        ListNode curr=head, prev=null, nxt;
        while(curr!=null){
            nxt=curr.next; //8
            curr.next=prev;
            prev=curr;
            curr=nxt;
        }  
        return prev;      
    }

    public void print(ListNode n){
        while(n!=null){
            System.out.print(", "+n.val);
            n=n.next;
        }
        System.out.println();
    }
}
