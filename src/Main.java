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
class ListNode { // fields for ListNode class
    int key;
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int val) { // constructor
        this.key = key;
        this.val = val;
    }
}

class LRUCache { // cache class will contains a doubly linked List and a Map with nodes as values for O(1) retrieval/replace
    // trrying to think of this as like a super data structure of the two combined
    // fields for cache class
    int capacity;
    Map<Integer, ListNode> dic;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {  // constructor for cache where capacity defined but all else empty
        this.capacity = capacity;
        dic = new HashMap<>();
        head = new ListNode(-1, -1); // this is a dummy/sentinal node such that when DLL is empty or we deleted only
        tail = new ListNode(-1, -1); // remaining node, that the head and tail pointers will never equal null
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!dic.containsKey(key)) { // no key -> -1
            return -1;
        }

        ListNode node = dic.get(key); // if key
        remove(node); // remove prior instance from DLL
        add(node); // add instance at tail of DLL since it was most recently used
        return node.val;
    }

    public void put(int key, int value) {
        if (dic.containsKey(key)) { // if exists
            ListNode oldNode = dic.get(key); // get node
            remove(oldNode); // remove it from DLL
        }

        ListNode node = new ListNode(key, value); // add node to end of DLL after defining
        dic.put(key, node); // note that the node key is the same key in the HashMap to link the two data structures
        add(node);

        if (dic.size() > capacity) { // if capacity breached,
            ListNode nodeToDelete = head.next; // define and delete true head, not sentinel
            remove(nodeToDelete);
            dic.remove(nodeToDelete.key);
        }
    }

    private void add(ListNode node) { // add new node to real end between prior end and sentinel end
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    private void remove(ListNode node) { // the add and remove methods were public, but that's bad encapsulation IMO.
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // this didn't actually remove the node though and it won't be garbage collected since its still linked
        // by its pointers even though nothing points to it. over time, this will use way too much memory.
        node.prev = null;
        node.next = null;
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


