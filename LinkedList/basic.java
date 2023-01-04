// Add Last

void addLast(Node head, int val){
    if(head == null){
        return new Node(val);
    }
    Node nn = new Node(val);
    Node ptr = head;
    while(ptr.next != null){
        ptr = ptr.next;
    }
    ptr.next = nn;
}

// Add First

void addFirst(Node head, int val){
    if(head == null){
        return new Node(val);
    }
    Node nn = new Node(val);
    nn.next = head;
    head = nn;
}

// Delete First

int deleteFirst(Node head){
    if(head == null){
        return -1;
    }
    int val = head.val;
    head = head.next;
    return val;
}

// Delete Last

int deleteLast(Node head){
    if(head == null){
        return -1;
    }
    Node ptr = head;
    while(ptr.next.next != null){
        ptr = ptr.next;
    }
    int val = ptr.next.val;
    ptr.next.val = null;
    return val;
}

// Add At Index

void addAtIndex(Node head, int idx, int val){
    if(idx == 0){
        return addFirst(val);
    }
    Node prev = head, ptr = head;
    while(ptr!=null && idx-- > 0){
        prev = ptr;
        ptr = ptr.next;
    }
    Node nn = new Node(val);
    nn.next = ptr;
    prev.next = nn;
}

// Remove At Index

void removeAtIndex(Node head, int idx){
    if(idx == 0){
        return deleteFirst(head);
    }
    Node ptr = head;
    while(ptr!=null && idx-- > 0){
        ptr = ptr.next;
    }
    ptr.next = ptr.next.next;
}

// Find Mid of LL 

import java.util.*;

class Main {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode(ListNode head) {
    if(head == null || head.next == null)
    return head;
    ListNode fast,slow;
    fast = head;
    slow = head;
    while(fast.next!=null && fast.next.next!=null)
    {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        ListNode head = midNode(dummy.next);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}

//Fold Of LL 

import java.util.*;

class Main {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    
    public static ListNode findMid(ListNode head)
    {
    if(head == null || head.next == null)
        return head;
        
     ListNode slow = head;
     ListNode fast = head;
     while(fast.next!=null && fast.next.next!=null)
     {
         fast = fast.next.next;
         slow = slow.next;
     }
     return slow;
    }
     
    public static ListNode reverse(ListNode node)
    {
        if(node == null || node.next == null)
        return node;
        ListNode curr = node;
        ListNode prev = null;
        ListNode nex = null;
        while(curr!=null)
        {
            nex = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nex;
        }
        return prev;
    }

    public static void fold(ListNode head) {
        if(head == null && head.next==null)
            return;
        ListNode mid = findMid(head);
        
        ListNode firstH = head;
        ListNode secondH = mid.next;
        mid.next= null;
        
        secondH = reverse(secondH);
        
        ListNode c1 = firstH;
        ListNode c2 = secondH;
        ListNode f1 = null;
        ListNode f2 = null;
        while(c1!=null && c2!=null)
        {
            f1 = c1.next;
            f2 = c2.next;
            
            c1.next = c2;
            c2.next = f1;
            
            c1 = f1;
            c2 = f2;
        }
    }

    static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        ListNode head = dummy.next;
        fold(head);
        printList(head);
    }
}

// Reverse a LL

import java.util.*;

class Main {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverse(ListNode head) {
      if(head==null || head.next == null)
      return null;
      ListNode nex = null;
      ListNode curr = head;
      ListNode prev = null;
      while(curr!=null)
      {
           nex = curr.next;
          curr.next = prev;
          prev = curr;
          curr = nex;
      }
      return prev;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        ListNode head = reverse(dummy.next);
        while (head != null) {
            System.out.print(head.val + " ");
head = head.next;
        }
    }
}


// UnFold a LL

import java.util.*;

class Main {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverse(ListNode node)
    {
        if(node == null || node.next == null)
        return node;
        ListNode curr = node;
        ListNode prev = null;
        ListNode nex = null;
        while(curr!=null)
        {
            nex = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nex;
        }
        return prev;
    }

    public static void unfold(ListNode head) {
        if(head == null || head.next == null)
        return ;
        
        ListNode odd = head.next,even = head;
        ListNode c1= head, c2 = head.next;
        
        while(c2!=null && c2.next !=null)
        {
            ListNode temp1 = c1.next.next;
            ListNode temp2 = c2.next.next;
            c1.next = temp1;
            c2.next = temp2;
            c1 = temp1;
            c2 = temp2;
            
        }
        ListNode newH = reverse(odd);
        c1.next =  newH;
    }

    static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        ListNode head = dummy.next;
        unfold(head);
        printList(head);
    }
}

// Fold a LL

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
    public ListNode findMid(ListNode head)
    {
        if(head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast !=null && fast.next !=null)
        {
            fast = fast.next.next;
            slow = slow.next;
            
        }
        return slow;
    }
    public ListNode reverse(ListNode node)
    {
        
        ListNode curr = node;
        ListNode prev = null;
        ListNode nex = null;
        while(curr!=null)
        {
            nex = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nex;
        }
        return prev;
    }
    public void reorderList(ListNode head) {
        if(head == null || head.next==null)
            return ;
        //finding mid
        ListNode mid = findMid(head);
        
        ListNode firH = head;
        ListNode secH = mid.next;
        mid.next = null;
        
        //reverse
        secH = reverse(secH);
        
       // head = firH;
        ListNode c1 = firH;
        ListNode c2 = secH;
        ListNode f1 = null;
        ListNode f2 = null;
        while(c1!=null && c2!=null)
        {
            f1 = c1.next;
            f2 = c2.next;
            
            c1.next = c2;
            c2.next = f1;
            
            c1 = f1;
            c2 = f2;
        }
        
    }
    
}

// Merge 2 Sorted Lists

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode prev = null;
        ListNode dummy = null;
        while(l1!=null && l2!=null)
        {
            if(l1.val < l2.val)
            {
                if(dummy == null)
                {
                    dummy = l1;
                    prev = dummy;
                }
                else
                {
                    prev.next = l1;
                    prev = prev.next;
                }
                l1 = l1.next;
            }
            else
            {
                if(dummy == null)
                {
                    dummy = l2;
                    prev = dummy;
                }
                else
                {
                    prev.next = l2;
                    prev = prev.next;
                }
                l2 = l2.next;
            }
        }
        if(l1!=null)
        {
           prev.next =  l1;
        }
        else if(l2!=null)
        {
            prev.next = l2;
        }
            
        return dummy;
    }
}


// Merge K Sorted lists

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
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2)
        {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode prev = null;
        ListNode dummy = null;
        while(l1!=null && l2!=null)
        {
            if(l1.val < l2.val)
            {
                if(dummy == null)
                {
                    dummy = l1;
                    prev = dummy;
                }
                else
                {
                    prev.next = l1;
                    prev = prev.next;
                }
                l1 = l1.next;
            }
            else
            {
                if(dummy == null)
                {
                    dummy = l2;
                    prev = dummy;
                }
                else
                {
                    prev.next = l2;
                    prev = prev.next;
                }
                l2 = l2.next;
            }
        }
        if(l1!=null)
        {
           prev.next =  l1;
        }
        else if(l2!=null)
        {
            prev.next = l2;
        }
            
        return dummy;
    }
    
    public  ListNode mergerSortLists(ListNode[] lists, int beg,int end)
    {
        if(beg == end)
        {
            return lists[beg];
        }
        int mid = (beg + end)/2;
        ListNode l1 = mergerSortLists(lists,beg,mid);
        ListNode l2 = mergerSortLists(lists,mid+1,end);
        return mergeTwoLists(l1,l2);
    }

    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];
        return mergerSortLists(lists,0,lists.length-1);
    }
}


// Merge Sort

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
    public ListNode midNode(ListNode head)
    {
        if(head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public ListNode merge2list(ListNode l1, ListNode l2)
    {
        if(l1 == null)
            return l2;
        if(l2== null)
            return l1;
        ListNode dummy = null,prev =null;
        while(l1!=null && l2!= null)
        {
            if(l1.val < l2.val){
                if(dummy == null)
                {
                dummy = l1;
                prev = dummy;
                } 
                else
                {
                prev.next = l1;
                prev = prev.next;
                }
                l1 = l1.next;
            }
            else
            {
                if(dummy == null)
                {
                   dummy = l2; 
                    prev = dummy;
                }
                else{
                    prev.next = l2;
                    prev = prev.next;
                }
                l2 = l2.next;
            }
        }
        if(l1!=null)
        {
           prev.next =  l1;
        }
        else if(l2!=null)
        {
            prev.next = l2;
        }
        return dummy;
    }
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode mid = midNode(head);
        ListNode nHead = mid.next;
        mid.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(nHead);
        return merge2list(l1,l2);
    }
}

// Odd Even LL 
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
    public ListNode oddEvenList(ListNode head) {
        ListNode oddP = new ListNode(-1);
        ListNode evenP = new ListNode(-1);
        ListNode odd = oddP;
        ListNode even = evenP;
        boolean flag = true;
        while(head!=null)
        {
            if(flag == true)
            {
                even.next = head;
                even = even.next;
                flag = false;
            }
            else
            {
                odd.next = head;
                odd = odd.next;
                flag = true;
            }
            head = head.next;
        }
      even.next = oddP.next;
        odd.next = null;
        return evenP.next;
    }
}

// Palindrome Linked List

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
    public ListNode findMid(ListNode head)
    {
        if(head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
        
    }
    public ListNode reverse(ListNode node)
    {
        ListNode prev = null;
        ListNode nex = null;
        ListNode  curr = node;
        while(curr!=null)
        {
            nex = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nex;
        }
        return prev;
    }
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        ListNode mid = findMid(head);
        ListNode secH = mid.next;
        ListNode firH = head;
        mid.next = null;
        secH = reverse(secH);
        while(secH!=null && firH!=null)
        {
            if(secH.val != firH.val)
            {
                return false;
            }
            firH = firH.next;
            secH = secH.next;
        }
    return true;
    }
    
}

// Remove N node from End 

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode dummy = head;
        while(n-- > 0)
        {
            fast = fast.next;
        }
        if(fast == null)
        {
            return head.next;
        }
        while(fast.next != null)
        {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
    return dummy;
    }
    
}

// Reverse Nodes K Groups 

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
    public int size(ListNode node)
    {
        int count = 0;
        while(node != null)
        {
            count++;
            node = node.next;
        }
        return count;
    }
    public  ListNode th = null;
    public  ListNode tt = null;
    
    public  void addFirst(ListNode node)
    {
        if(th == null)
        {
            th = node;
            tt = node;
        }
        else
        {
            node.next = th;
            th = node;
        }
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 0)
        return head;
    int len = size(head);
    ListNode oh = null;
    ListNode ot = null;
    ListNode curr = head;
    while(len >= k)
    {
        int temp = k;
        while(temp-- >0)
        {
            ListNode nex = curr.next;
            curr.next = null;
            addFirst(curr);
            curr = nex;
        }
        if(oh == null)
        {
           oh = th;
           ot = tt;
        }else
        {
            ot.next = th;
            ot = tt;
        }
        th = null;
        tt = null;
        len-=k;
    }
    ot.next = curr;
        
        return oh;
    }
}

// Seggregate Even Odd 

import java.util.*;

class Main {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        ListNode evenh = new ListNode(-1);
        ListNode oddh = new ListNode(-1);
        ListNode even= evenh, odd = oddh, curr =head;
        while(curr!=null)
        {
            if(curr.val%2 == 0)
            {
               even.next = curr;
               even = even.next;
            }
            else
            {
                odd.next = curr;
                odd = odd.next;
            }
            curr = curr.next;
        }
        even.next = oddh.next;
        odd.next =  null;
        return evenh.next;
        
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        ListNode head = segregateEvenOdd(dummy.next);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}


// Remove Duplicates From Sorted Lists

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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode prev = head, ptr = head.next;
        while(ptr != null ){
            if(ptr.val == prev.val){
                ptr = ptr.next;
            }
            else{
                prev.next = ptr;
                prev = ptr;
                ptr = ptr.next;
            }
        }
        prev.next = null;
        return head;

    }
}