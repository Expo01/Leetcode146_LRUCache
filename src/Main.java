import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


// i was right with queue idea but need an O(1) solution since if a key is in the queue and we call it
// again, need to remove it frmo its position and add to end of queue, costing O(n)
class ListNode {
    int key;
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    int capacity;
    Map<Integer, ListNode> dic;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!dic.containsKey(key)) {
            return -1;
        }

        ListNode node = dic.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (dic.containsKey(key)) {
            ListNode oldNode = dic.get(key);
            remove(oldNode);
        }

        ListNode node = new ListNode(key, value);
        dic.put(key, node);
        add(node);

        if (dic.size() > capacity) {
            ListNode nodeToDelete = head.next;
            remove(nodeToDelete);
            dic.remove(nodeToDelete.key);
        }
    }

    public void add(ListNode node) {
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


// ATTEMPT
//class LRUCache {
//
//    LinkedList<Node> l = new LinkedList<>();
//    Node head = null;
//    HashMap<Integer, Integer> m = new HashMap<>();
//
//    public LRUCache(int capacity) {
//        if (l.size() > capacity) {
//            int temp = head.next();
//            m.removeKey(head.val);
//            head.next = null;
//            head = temp;
//        } else{
//            head.next = val // not sure where this is supose to be at... this method doesn't reutn anything?
//            // i would have put method call the LRU cache to check if size > cache, but it can't return anything
//            // so what good is this method?
//        }
//    }
//
//    public int get(int key) {
//        return (m.get(key));
//    }
//
//    public void put(int key, int value) {
//        m.put(key, value);
//    }
//}


// will need a map to store the key which the value is also the same?
// a little contrived. but the will also need a linked list which will
// essentially work like a queue but pointers pointing away so O(1) to
// change the head=head.next. drop the head which is least recently used
// and remove the val of the head from the keyset.
// 4,2,2,2,2 : suppose this example, does the cache store the
// # of operations, or the number of keys. suppose stores only 2 calls
// then cache would be (2,2) but if it stores keys then (4,2).
// going to assume calls, since idk how to do the other


