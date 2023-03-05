package com.lce.linkList;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public boolean hasCycle(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>();

        ListNode temp = head;
        while (true) {
            if(temp == null){
                return false;
            }
            map.put(temp.val, temp.val);
            if (map.containsKey(temp.next.val)) {
                return true;
            }
            temp = temp.next;
        }
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}