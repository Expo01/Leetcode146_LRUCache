import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class LRUCache {

    LinkedList<Node> l = new LinkedList<>();
    Node head = null;
    HashMap<Integer, Integer> m = new HashMap<>();

    public LRUCache(int capacity) {
        if (l.size() > capacity) {
            int temp = head.next();
            m.removeKey(head.val);
            head.next = null;
            head = temp;
        } else{
            head.next = val // not sure where this is supose to be at... this method doesn't reutn anything?
            // i would have put method call the LRU cache to check if size > cache, but it can't return anything
            // so what good is this method?
        }
    }

    public int get(int key) {
        return (m.get(key));
    }

    public void put(int key, int value) {
        m.put(key, value);
    }
}