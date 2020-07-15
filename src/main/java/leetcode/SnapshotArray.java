package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ScXin
 * @date 8/5/2019 9:12 PM
 */
public class SnapshotArray {
    static class Node {
        public int version;
        public int val;

        Node next;

        public Node(int version, int val) {
            this.version = version;
            this.val = val;
        }
    }

    private Node[] arr;
    private int time = 0;

    public SnapshotArray(int length) {
        arr = new Node[length];
    }

    public void set(int index, int val) {
        if (arr[index] != null) {
            Node node = arr[index];
            if (node.version == time) {
                node.val = val;
                return;
            }
            while (node.next != null && node.next.version != time) {
                node = node.next;
            }
            if (node.next != null) {
                node.next.val = val;
                return;
            }
            Node insertNode = new Node(time, val);
            node.next = insertNode;
            return;
        } else {
            arr[index] = new Node(time, val);
        }
    }

    public int snap() {
        time += 1;
        return time - 1;
    }

    public int get(int index, int snap_id) {
        Node head = arr[index];
        if (head != null) {
            Node node = head;
            if (node.version == snap_id) {
                return node.val;
            }
            while (node.next != null && node.next.version < snap_id) {
                node = node.next;
            }
            if (node.next == null) {
                return node.val;
            }
            if (node.next.val == snap_id) {
                return node.next.val;
            }
            return node.val;
//            if (node.next == null) {
//                return node.val;
//            } else {
//                return node.next.val;
//            }
        } else {
            return 0;
        }
    }

//    private int[][] arr;
//    private int time;
//
//    public SnapshotArray(int length) {
//        arr = new int[length][50001];
//    }
//
//    public void set(int index, int val) {
//        arr[index][time] = val;
//    }
//
//    public int snap() {
//        time += 1;
//        return time;
//    }
//
//    public int get(int index, int snap_id) {
//        if (arr[index][snap_id] != 0) {
//            return arr[index][snap_id];
//        }
//        if (snap_id != 0) {
//            while (snap_id >= 0) {
//                if (arr[index][snap_id] != 0) {
//                    return arr[index][snap_id];
//                }
//                snap_id--;
//            }
//            return 0;
//        }
//        return 0;
//    }
}
