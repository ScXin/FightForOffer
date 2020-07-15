package leetcode;



import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author ScXin
 * @date 8/25/2019 10:10 AM
 */
public class FileSystem {
    private static HashMap<String, Integer> fileSystem = null;

    public FileSystem() {
    }

    /**
     * ["FileSystem","create","create","get","create","get"]
     * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
     *
     * @param path
     * @param value
     * @return
     */
    public boolean create(String path, int value) {
        if (path.equals("[]")) {
            return false;
        }
        String[] paths = path.split("/");
        if (paths.length == 2) {
            fileSystem.put("/" + paths[1], value);
            return true;
        }
        String temp = "/";
        if (paths.length > 2) {
            int i = 1;
            while (i < paths.length - 1) {
                temp += paths[i];
                if (!fileSystem.containsKey(temp)) {
                    return false;
                }
                temp += "/" + paths[i];
                i++;
            }
            fileSystem.put(temp + "/" + paths[paths.length - 1], value);
            return true;
        }
        return false;
    }

    public int get(String path) {

        if (fileSystem.containsKey(path)) {
            return fileSystem.get(path);
        }
        return -1;
    }

    public static int connectSticks(int[] sticks) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < sticks.length; i++) {
            queue.add( sticks[i]);
        }
        int ret = 0;
        while (queue.size() != 1 && !queue.isEmpty()) {

            int sum=queue.poll()+queue.poll();
            System.out.println("ret=="+ret);
            ret+=sum;
            queue.add(sum);
        }
        return ret;
    }



    public static void main(String[] args) {
   int[] mat={1,8,3,5};
        System.out.println(connectSticks(mat));
    }
}
