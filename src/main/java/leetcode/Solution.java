package leetcode;


//import sun.reflect.generics.tree.Tree;

import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.INACTIVE;
//import sun.jvm.hotspot.utilities.Bits;

import javax.swing.plaf.multi.MultiTableHeaderUI;
import javax.transaction.TransactionRequiredException;
import java.beans.IntrospectionException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.Inet4Address;
//import com.sun.tools.corba.se.idl.InterfaceGen;

//import sun.reflect.generics.tree.Tree;

import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ScXin
 * @date 7/12/2019 9:41 AM
 */
public class Solution {
    /**
     * 数独问题
     * [
     * ["5","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     */
    public boolean isValidSudoku(char[][] board) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (list.contains(board[i][j])) {
                    return false;
                }
                if (board[i][j] != '.') {
                    list.add(board[i][j]);
                }
            }
            list.clear();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (list.contains(board[j][i])) {
                    return false;
                }
                if (board[j][i] != '.') {
                    list.add(board[j][i]);
                }
            }
            list.clear();
        }

        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[i].length; j += 3) {
                if (!isRequirement(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isRequirement(char[][] board, int i, int j) {
        List<Character> list = new ArrayList<>();
        for (int k = i; k < i + 3; k++) {
            for (int l = j; l < j + 3; l++) {
                if (list.contains(board[k][l])) {
                    return false;
                }
                if (board[k][l] != '.') {
                    list.add(board[k][l]);
                }
            }
        }
        return true;
    }

    /**
     * 输入：hours = [9,9,6,0,6,6,9]
     * 输出：3
     * 解释：最长的表现良好时间段是 [9,9,6]。
     *
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            if (hours[i] > 8)
                array[i] = 1;
            else
                array[i] = -1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = i; j < n; j++) {
                temp += array[j];
                if (temp > 0)
                    ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++) {
            list2.add(arr2[i]);
        }
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        for (int j = 0; j < arr1.length; j++) {
            if (list2.contains(arr1[j])) {
                list1.add(arr1[j]);
            } else {
                list3.add(arr1[j]);
            }
        }
        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int x1 = list2.indexOf(o1);
                int x2 = list2.indexOf(o2);
                return x1 - x2;
            }
        });
        Collections.sort(list3);
        for (int k = 0; k < list3.size(); k++) {
            list1.add(list3.get(k));
        }
        int[] res = new int[arr1.length];
        for (int m = 0; m < list1.size(); m++) {
            res[m] = list1.get(m);
        }
        return res;
    }

    //
//    public static void main(String[] args) {
//        int[]arr1={2,3,1,3,2,4,6,7,9,2,19};
//        int[]arr2={2,1,4,3,9,6};
//        int[] ret=relativeSortArray(arr1,arr2);
//        for(int i=0;i<ret.length;i++){
//            System.out.print(ret[i]+" ");
//        }
//
//        List<Integer>list=new ArrayList<>();
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        System.out.println(list.indexOf(5));
//    }
    public static int[] searchRange(int[] nums, int target) {

        int first = binarySearch(nums, target);
        int last = binarySearch(nums, target + 1) - 1;
        if (first == nums.length || nums[first] != target) {

            return new int[]{-1, -1};
        }
        System.out.println("first==" + first + "last===" + last);
        return new int[]{first, last};

    }

    public static int binarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }


    public static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        List<TreeNode> ret = new ArrayList<>();
        List<Integer> delete = new ArrayList<>();
        for (int i = 0; i < to_delete.length; i++) {
            delete.add(to_delete[i]);
        }
        delNodeToForest(root, delete, ret);
        return ret;
    }

    /**
     * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
     * 输出：[[1,2,null,4],[6],[7]]
     *
     * @param root
     * @param to_delete
     * @param ret
     */
    public static void delNodeToForest(TreeNode root, List<Integer> to_delete, List<TreeNode> ret) {
        if (ret == null) {
            return;
        }
        delNodeToForest(root.left, to_delete, ret);
        delNodeToForest(root.right, to_delete, ret);
        if (to_delete.contains(root.val)) {
            if (root.left != null) {
                ret.add(root.left);
            }
            if (root.right != null) {
                ret.add(null);
            }
            root = null;
        }
    }

    public static void deleteNodeWitMark(TreeNode root) {

        if (root == null) {
            return;
        }
        if (root.left != null && root.left.val == 1001) {
            root.left = null;
        }
        if (root.right != null && root.right.val == 1001) {
            root.right = null;
        }
        deleteNodeWitMark(root.left);
        deleteNodeWitMark(root.right);
    }

    public class ListNode {
        int val;

        public ListNode(int val) {
            this.val = val;
        }

        ListNode next;
    }

    public ListNode rotateRight(ListNode head, int k) {

        int m = getLengthofListNode(head);
        int x = k % m;

        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        ListNode pCur = head;
        while (x > 0) {
            pCur = pCur.next;
        }
        ListNode pHead = pCur.next;
        pCur.next = null;
        return pHead;

    }

    public static int maxProduct(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];//min value
        dp[0][1] = nums[0];//max value
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                if (dp[i - 1][0] != 0 && dp[i - 1][1] != 0) {
                    dp[i][0] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
                    dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
                } else if (dp[i - 1][0] == 0) {
                    dp[i][0] = Math.min(nums[i], dp[i - 1][1] * nums[i]);
                    dp[i][1] = Math.min(nums[i], 0);
                } else {
                    dp[i][0] = Math.min(nums[i], 0);
                    dp[i][1] = Math.min(nums[i], nums[i] * dp[i - 1][0]);
                }

            } else if (nums[i] == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else {
                if (dp[i - 1][0] != 0 && dp[i - 1][1] != 0) {
                    dp[i][0] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
                    dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
                } else if (dp[i - 1][0] == 0) {
                    dp[i][0] = Math.min(nums[i], 0);
                    dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
                } else {
                    dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0]);
                    dp[i][1] = Math.max(nums[i], 0);
                }

            }
            max = Math.max(max, dp[i][1]);
        }
        return max;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        if (numCourses <= 1) {
            return true;
        }
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
        }
        Queue<Integer> res = new LinkedList<>();
        for (int j = 0; j < numCourses; j++) {
            if (indegree[j] == 0) {
                res.offer(j);
            }
        }
        while (!res.isEmpty()) {
            int course = res.poll();
            for (int k = 0; k < prerequisites.length; k++) {
                if (prerequisites[k][1] == course) {
                    indegree[prerequisites[k][0]]--;
                    if (prerequisites[k][0] == 0) {
                        res.offer(prerequisites[k][0]);
                    }
                }
            }
        }

        for (int m = 0; m < numCourses; m++) {
            if (indegree[m] != 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();


        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root.left);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                ret.add(node.val);
                root = node.right;
            }
        }
        return ret;

    }
//    Input: "babad"
//    Output: "bab"
//    Note: "aba" is also a valid answer.

    //
//    public static String longestPalindrome(String s) {
//
//        int[][]dp=new int[s.length()][s.length()];
//        dp[0][0]=1;
//
//    }
    /**
     * 输入: [-10,9,20,null,null,15,7]
     * <p>
     * -10
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 输出: 42
     */
    private static int maxPathVal = 0;

    public static int maxPathSum(TreeNode root) {
        dfsSolved(root);
        return maxPathVal;
    }

    public static int dfsSolved(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfsSolved(root.left);
        int right = dfsSolved(root.right);
        int max = Math.max(root.val, Math.max(root.val + left, root.val + right));


        int interSum = left + right + root.val;
        maxPathVal = Math.max(Math.max(max, interSum), maxPathVal);
        return max;
    }


//    public static void main(String[] args) {
////        int[] nums = {1,2,3,4,5,6,7,7,7,7};
////        int[] ret=searchRange(nums, 7);
////        System.out.println(ret[0]);
////        System.out.println(ret[1]);
////        String addr = "1.1.1.1";
////        System.out.println(defangIPaddr(addr));
////        int[] nums = {2, -1, 1, 1};
////        System.out.println(maxProduct(nums));
//        int[][] course = {{0, 1}};
//        System.out.println(canFinish(2, course));
//    }


    public static int getLengthofListNode(ListNode head) {

        int k = 0;
        ListNode cur = head;
        while (cur != null) {
            k++;
            cur = cur.next;
        }
        return k;
    }

    public static int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] ret = new int[rows][cols];
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                ret[m][n] = matrix[m][n];
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0) {
                    helper(matrix, i, j, ret);
                }
            }
        }
        return ret;
    }

    public static void helper(int[][] matrix, int i, int j, int[][] ret) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        queue.offer(j);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            while ((size = size - 2) >= 0) {
                int p = queue.poll();
                int q = queue.poll();

                if (p > 0 && matrix[p - 1][q] == 0) {

                    ret[i][j] = count;
                    return;
                }
                if (p > 0 && matrix[p - 1][q] != 0) {
                    queue.offer(p - 1);
                    queue.offer(q);
                }
                if (q > 0 && matrix[p][q - 1] == 0) {

                    ret[i][j] = count;
                    return;
                }
                if (q > 0 && matrix[p][q - 1] != 0) {
                    queue.offer(p);
                    queue.offer(q - 1);
                }
                if (p < matrix.length - 1 && matrix[p + 1][q] == 0) {

                    ret[i][j] = count;
                    return;
                }
                if (p < matrix.length - 1 && matrix[p + 1][q] != 0) {
                    queue.offer(p + 1);
                    queue.offer(q);
                }
                if (q < matrix[0].length - 1 && matrix[p][q + 1] == 0) {

                    ret[i][j] = count;
                    return;
                }
                if (q < matrix[0].length - 1 && matrix[p][q + 1] != 0) {
                    queue.offer(p);
                    queue.offer(q + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return;
    }

    private static List<List<Integer>> ret = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        solved(0, nums);
        return ret;
    }

    public static void solved(int i, int[] nums) {

        if (i == nums.length) {
            List list = Arrays.asList(nums);
            if (!ret.contains(list)) {
                ret.addAll(list);
            }
            return;
        }

        for (int j = i; j <= nums.length - 1; j++) {
            swap(nums, i, j);
            solved(j + 1, nums);
            swap(nums, i, j);
        }
    }
    /*
       int[]nums= {3,2,1,0,4};
     */

    public static boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 0; i < nums.length - 1; i++) {
            int k = nums[i];
            int m = 0;
            while (m <= k && m + i <= nums.length - 1) {
                if (i + m == nums.length - 1) {
                    return true;
                }
                dp[i + m] = true;
                m++;
            }
        }
        return false;
    }

    /**
     * Input: [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        if (intervals.length <= 0) {
            return new int[][]{};
        }
        List<Integer> list = new ArrayList<>();
        if (intervals.length <= 0) {
            return new int[][]{};
        }
        int pre = intervals[0][1];
        list.add(intervals[0][0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= pre && intervals[i][1] <= pre) {
                continue;
            } else if (intervals[i][0] <= pre && intervals[i][1] > pre) {
                pre = intervals[i][1];
            } else {
                list.add(pre);
                list.add(intervals[i][0]);
                pre = intervals[i][1];
            }
        }
        list.add(pre);

        int[][] ret = new int[list.size() / 2][2];
        int k = 0;
        int t = 0;
        while (t < list.size()) {
            ret[k][0] = list.get(t++);
            ret[k++][1] = list.get(t++);

        }
        return ret;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        dp[0][0] = 1;
        boolean preOb = false;
        for (int i = 0; i < obstacleGrid.length; i++) {
            if (obstacleGrid[i][0] != 1 && !preOb) {
                dp[i][0] = 1;
            } else {
                if (obstacleGrid[i][0] == 1) {
                    preOb = true;
                }
                dp[i][0] = 0;
            }
        }
        boolean preOb2 = false;
        for (int j = 0; j < obstacleGrid[0].length; j++) {
            if (obstacleGrid[0][j] != 1 && !preOb2) {
                dp[0][j] = 1;
            } else {
                if (obstacleGrid[0][j] == 1) {
                    preOb = true;
                }
                dp[0][j] = 0;
            }
        }

        for (int k = 1; k < obstacleGrid.length; k++) {
            for (int m = 1; m < obstacleGrid[0].length; m++) {
                if (obstacleGrid[k][m] == 1) {
                    dp[k][m] = 0;
                } else {
                    dp[k][m] = dp[k - 1][m] + dp[k][m - 1];
                }

            }
        }

        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];

    }

    public static String largestNumber(int[] nums) {
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = nums[i] + "";
        }
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        String ret = "";
        for (int j = 0; j < str.length; j++) {
            ret += str[j];
        }
        return ret;
    }

    /**
     * "abcabcbb"
     *
     * @param s
     * @return
     */

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 0) {
            return 0;
        }
        if (s.charAt(0) == ' ') {
            return 1;
        }
        int[] dp = new int[256];
        Arrays.fill(dp, -1);
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            int t = s.charAt(i);
            if (dp[t] != -1) {
                max = Math.max(max, i - dp[t]);
                left = Math.max(left, dp[t] + 1);
                dp[t] = i;
            } else {
                dp[t] = i;
                max = Math.max(i - left + 1, max);
            }

        }
        return max;
    }

    /**
     * 最大回文字符串
     *
     * @param s
     * @return 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     */
    public static String longestPalindrome(String s) {
        if (s.length() == 0 || s == null || s.length() == 1) {
            return s;
        }
        char[] chas = s.toCharArray();
        int max = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = maxLen(chas, i, i);
            int len2 = maxLen(chas, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > max) {
                max = len;
                left = i - ((len - 1) / 2);
                right = i + (len / 2);
            }
        }
        return s.substring(left, right + 1);
    }

    public static int maxLen(char[] chas, int left, int right) {

        while (left >= 0 && right < chas.length && chas[left] == chas[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * <p>
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {

        if (numRows <= 1) {
            return s;
        }
        int cycleLen = 2 * numRows - 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i + j < s.length(); j += cycleLen) {
                sb.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j - i + cycleLen < s.length()) {
                    sb.append(s.charAt(j - i + cycleLen));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 输入: [1,8,6,2,5,4,8,3,7]
     * 输出: 49
     **/
    public static int maxArea(int[] height) {

        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {

            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }

    /**
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.     * @param num
     *
     * @return
     */
    public static String intToRoman(int num) {
        int[] valus = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] map = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "L", "X", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valus.length; i++) {
            while (num >= valus[i]) {
                sb.append(map[i]);
                num -= valus[i];
            }
        }
        return sb.toString();
    }


    /**
     * 节点与其祖先之间的最大值
     *
     * @param args
     */
    public static int maxAncestorDiff(TreeNode root) {
        return solver(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int solver(TreeNode root, int m, int n) {
        if (root == null) {
            return 0;
        }
        m = Math.max(m, root.val);
        n = Math.min(n, root.val);
        int max = 0;
        if (root.left != null) {
            max = Math.max(max, Math.abs(m - root.left.val));
            max = Math.max(max, Math.abs(n - root.left.val));
        }
        if (root.right != null) {
            max = Math.max(max, Math.abs(m - root.right.val));
            max = Math.max(max, Math.abs(n - root.right.val));
        }
        return Math.max(max, Math.max(solver(root.left, m, n), solver(root.right, m, n)));
    }

    private static HashMap<String, Integer> map;

    public static String comparePoker(String cards) {
        String[] pokes = cards.split("-");
        String[] poke1 = pokes[0].split(" ");
        String[] poke2 = pokes[1].split(" ");
        if (poke1.length == 2 || poke2.length == 2) {

            if (poke1.length == 1 || poke1.length == 1) {
                if (poke1.length == 1) {
                    if (poke2[0].equals("joker") || poke2[1].equals("joker")) {
                        return poke2[0] + " " + poke2[1];
                    } else {
                        return "ERROR";
                    }
                }
                if (poke2.length == 1) {
                    if (poke1[0].equals("joker") || poke1[1].equals("joker")) {
                        return poke1[0] + " " + poke1[1];
                    } else {
                        return "ERROR";
                    }
                }
            }
            if (poke2[0].equals("joker") || poke2[1].equals("joker")) {
                return poke2[0] + " " + poke2[1];
            }
            if (poke1[0].equals("joker") || poke1[1].equals("joker")) {
                return poke1[0] + " " + poke1[1];
            }


            if (poke1.length == 2 && poke1.length == 2) {
                if (poke1[0].equals("joker") || poke1[1].equals("joker")) {
                    return poke1[0] + " " + poke1[1];
                }
                if (poke2[0].equals("joker") || poke2[1].equals("joker")) {
                    return poke2[0] + " " + poke2[1];
                }
                return map.get(poke1[0]) > map.get(poke2[0]) ? poke1[0] + " " + poke1[1] : poke2[0] + " " + poke2[1];
            }

//            if(poke1.length!=poke2.length)
//            {
//                return "ERROR";
//            }
            if (poke1.length == 4 || poke2.length == 4) {
                if (poke1.length == 4) {
                    return poke1[0] + " " + poke1[1] + " " + poke1[2] + " " + poke1[3];
                }
                if (poke2.length == 4) {
                    return poke2[0] + " " + poke2[1] + " " + poke2[2] + " " + poke2[3];
                }
            }
            if (poke1.length != poke2.length) {
                return "ERROR";
            }

            return map.get(poke1[0]) > map.get(poke2[0]) ? poke1[0] + " " + poke1[1] : poke2[0] + " " + poke2[1];

        }
        if (poke1.length == 4 || poke1.length == 4) {
            if (poke1.length == 4 && poke2.length == 4) {
                return map.get(poke1[0]) > map.get(poke2[0]) ? poke1[0] + " " + poke1[1] + " " + poke1[2] + " " + poke1[3] : poke2[0] + " " + poke2[1] + " " + poke2[2] + " " + poke2[3];
            }
            if (poke1.length == 4) {
                return poke1[0] + " " + poke1[1] + " " + poke1[2] + " " + poke1[3];
            }
            if (poke2.length == 4) {
                return poke2[0] + " " + poke2[1] + " " + poke2[2] + " " + poke2[3];
            }
            return "ERROR";

        }
        if (poke1.length == 1 && poke2.length == 1) {

            if (poke1[0].equals("joker") && poke2[0].equals("JOKER")) {
                return poke2[0];
            }

            if (poke2[0].equals("JOKER") && poke1[0].equals("joker")) {
                return poke1[0];
            }
            if (poke1[0].equals("joker") || poke1[0].equals("JOKER")) {
                return poke1[0];
            }
            if (poke2[0].equals("joker") || poke2[0].equals("JOKER")) {
                return poke2[0];
            }
            return map.get(poke1[0]) > map.get(poke2[0]) ? poke1[0] : poke2[0];
        }
        if (poke1.length == 3 && poke2.length == 3) {
            return map.get(poke1[0]) > map.get(poke2[0]) ? poke1[0] + " " + poke1[1] + " " + poke1[2] : poke2[0] + " " + poke2[1] + " " + poke2[2];
        }
        if (poke1.length == 5 && poke2.length == 5) {
            return map.get(poke1[0]) > map.get(poke2[0]) ? poke1[0] + " " + poke1[1] + " " + poke1[2] + " " + poke1[3] + " " + poke1[4] : poke2[0] + " " + poke2[1] + " " + poke2[2] + " " + poke2[3] + " " + poke2[4];
        }
        return "ERROR";
    }

//    public static String extendString(String str) {
//        String res = "";
//        Stack<String> stack = new Stack<>();
//        Stack<Character> par = new Stack<>();
//        int i = str.length() - 1;
////        String temp="";
//        while (i >= 0) {
//            if (str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}') {
//                par.push(str.charAt(i));
//                i--;
//                continue;
//            }
//            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
//                par.pop();
//                i--;
//                continue;
//            }
//             if (Character.isDigit(str.charAt(i))){
//                if (!stack.isEmpty()) {
//                    String startStr = stack.pop();
//                    String temp = startStr;
//                    int num=str.charAt(i)-'1';
//                    while (num > 0) {
//                        temp += startStr;
//                        num--;
//                    }
//                    if (par.isEmpty()) {
//                        res += temp;
//                    } else {
//                        stack.push(temp);
//                    }
//                    i--;
//                    continue;
//                }
//            }
//            if (Character.isLetter(str.charAt(i))) {
////                System.out.println(par.size()+"===");
//                if (par.isEmpty()) {
//                    res += str.charAt(i);
//                    i--;
//                    continue;
//                }
//                String temp = str.charAt(i) + "";
//
//                int k = i - 1;
//                while (Character.isLetter(str.charAt(k))) {
//                    temp = str.charAt(k) + temp;
//                    k--;
//                }
//        if(!stack.isEmpty())
//        {
//            temp+=stack.pop();
//        }
//                stack.push(temp);
//                i=k;
//                continue;
//            }
//        }
//        return res;
//    }
//    public static void main(String[] args) {
//
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String input = in.nextLine();
//            System.out.println(extendString(input));
//        }
//    }
//}
//    public static void main(String[] args) {
//        TreeNode root=new TreeNode(2);
//        root.left=new TreeNode(-1);
////        root.right=new TreeNode(20);
////        root.right.left=new TreeNode(15);
////        root.right.right=new TreeNode(7);
//        System.out.println(maxPathSum(root));
//        int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
//        int[][] ret = updateMatrix(matrix);
//        for (int i = 0; i < ret.length; i++) {
//            for (int j = 0; j < ret[i].length; j++) {
//                System.out.print(ret[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        int[] nums = {1, 1, 2};
//        List<List<Integer>> list = permuteUnique(nums);
//        System.out.println(list.size() + "  list.size()");
//        for (int i = 0; i < list.size(); i++) {
//            List<Integer> temp = list.get(i);
//            for (int j = 0; j < temp.size(); j++) {
//                System.out.print((temp.get(j) + " aaa"));
//            }
//            System.out.println();
////        }
//        int[] nums = {2, 3, 1, 1, 4};
//        System.out.println(canJump(nums));
//        int[][] nums = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//
//        merge(nums);
//        int[] arr = {3, 30, 34, 5, 9};
//        System.out.println(largestNumber(arr));

    //        System.out.println(longestPalindrome("babad"));
//        System.out.println(convert("LEETCODEISHIRING",3));
//         int num=1003;
//        System.out.println(intToRoman(num));
//    }
    private static String minVal = Integer.MAX_VALUE + "";

    public static String minimizeError(String[] prices, int target) {
        List<Double> pricesNum = new ArrayList<>();
        int sumFloor = 0;
        int sumCeil = 0;
        for (int i = 0; i < prices.length; i++) {
            double price = Double.parseDouble(prices[i]);
            sumFloor += Math.floor(price);
            sumCeil += Math.ceil(price);
            pricesNum.add(price);
        }
        if (sumFloor > target || sumCeil < target) {
            return "-1";
        }
        helper(pricesNum, target, 0, 0);
//        System.out.println("minVal=="+minVal);
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(Double.parseDouble(minVal)) + "";
    }

    public static void helper(List<Double> prices, int target, double sumVal, int k) {
        System.out.println("target==" + target + "   ==" + sumVal + "  k==" + k);
        if (k >= prices.size() && target == 0) {

            if (minVal.compareTo(sumVal + "") > 0) {
                minVal = sumVal + "";
            }
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = k; i < prices.size(); i++) {
            Double ceilVal = Math.ceil(prices.get(i));
            int ceilVal2 = ceilVal.intValue();
            double minusCeil = Math.abs(sub(ceilVal, prices.get(i)));
            System.out.println("presumVal==" + sumVal);
            helper(prices, target - ceilVal2, add(sumVal, minusCeil), k + 1);
            Double floorVal = Math.floor(prices.get(i));
            int floorVal2 = floorVal.intValue();
            double minusFloor = Math.abs(sub(prices.get(i), floorVal));
            System.out.println("sumVal==" + sumVal);
            helper(prices, target - floorVal2, add(sumVal, minusFloor), k + 1);
        }
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 5 100
     * 5 3 3
     * 5 2 3
     * 5 1 2
     * 26 100 4
     * 15 100 4
     *
     * @param money
     * @param pricesAndOthers
     * @return
     */
    public static int maxStafied(int money, int[][] pricesAndOthers) {
        Arrays.sort(pricesAndOthers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] >= o1[0] || o2[1] >= o2[0]) {
                    System.out.println("11111111");
                    System.out.println("o1[0]=" + o1[0] + "  o1[1]=" + o1[1] + "  o2[0]=" + o2[0] + "  o2[1]=" + o2[1]);
                    System.out.println("--------------------------------------------------------");
                    return o2[1] / o2[0] - o1[1] / o1[0];
                } else {
                    System.out.println("22222222");
                    System.out.println("o1[0]=" + o1[0] + "  o1[1]=" + o1[1] + "  o2[0]=" + o2[0] + "  o2[1]=" + o2[1]);
                    System.out.println("--------------------------------------------------------");
                    return o1[0] / o1[1] - o2[0] / o2[1];
                }
            }
        });
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < pricesAndOthers.length; i++) {
            System.out.print(pricesAndOthers[i][0] + "  " + pricesAndOthers[i][1] + "  " + pricesAndOthers[i][2]);
            System.out.println();
            minPrice = Math.min(minPrice, pricesAndOthers[i][0]);
        }
        int k = 0;
        int stafied = 0;
        while (money >= minPrice && k < pricesAndOthers.length) {
            while (money >= pricesAndOthers[k][0] && pricesAndOthers[k][2]-- > 0) {
                money -= pricesAndOthers[k][0];
                stafied += pricesAndOthers[k][1];
            }
            k++;
        }
        return stafied;
    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String str = in.nextLine();
//            String[] snackKindAndMoney = str.split(" ");
//            int snackKindNum = Integer.parseInt(snackKindAndMoney[0]);
//            int money = Integer.parseInt(snackKindAndMoney[1]);
//            int[][] snackPriceAndStafiedAndNumber = new int[snackKindNum][3];
//            for (int i = 0; i < snackKindNum; i++) {
//                String str1 = in.nextLine();
//                String[] snackInfomation = str1.split(" ");
//                int price = Integer.parseInt(snackInfomation[0]);
//                int stafied = Integer.parseInt(snackInfomation[1]);
//                int number = Integer.parseInt(snackInfomation[2]);
//                snackPriceAndStafiedAndNumber[i][0] = price;
//                snackPriceAndStafiedAndNumber[i][1] = stafied;
//                snackPriceAndStafiedAndNumber[i][2] = number;
//            }
//            System.out.println(maxStafied(money, snackPriceAndStafiedAndNumber));
//        }
//    }

    /**
     * 输入：nums = [1,2,3]
     * 输出：2
     * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
     *
     * @param nums
     * @return
     */
    public static int movesToMakeZigzag(int[] nums) {

        /**
         * change odd index
         */
        int[] nums1 = new int[nums.length];
        int[] nums2 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums[i];
            nums2[i] = nums[i];
        }
        int times1 = 0;
        int k1 = 0;
        while (k1 < nums1.length) {
            int temp1 = 0;
            int temp2 = 0;
            if (k1 + 1 < nums1.length && nums1[k1] <= nums1[k1 + 1]) {
                temp1 = nums1[k1 + 1] - nums1[k1] + 1;
                nums1[k1 + 1] = nums1[k1] - 1;
            }
            if (k1 - 1 >= 0 && nums1[k1 - 1] >= nums1[k1]) {
                temp2 = nums1[k1 - 1] - nums1[k1] + 1;
                nums1[k1 - 1] = nums1[k1] - 1;
            }
            times1 += temp1 + temp2;
            k1 = k1 + 2;
            System.out.println("times1==" + times1);
        }
        //change the odd index
        int time2 = 0;
        int k2 = 1;
        while (k2 < nums2.length) {
            int temp1 = 0;
            int temp2 = 0;
            if (nums2[k2] <= nums2[k2 - 1]) {
                temp1 = nums2[k2 - 1] - nums2[k2] + 1;
                nums2[k2 - 1] = nums2[k2] - 1;
            }
            if (k2 + 1 < nums2.length && nums2[k2 + 1] >= nums2[k2]) {
                temp2 = nums2[k2 + 1] - nums2[k2] + 1;
                nums2[k2 + 1] = nums2[k2] - 1;
            }
            System.out.println("time2==" + time2);
            time2 += temp2 + temp1;
            k2 += 2;
        }
        return times1 > time2 ? time2 : times1;
    }


    /**
     * 输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
     * 输出：True
     * 解释：第二个玩家可以选择值为 2 的节点。
     *
     * @param args
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (root.val == x) {
            return false;
        }
        if (parentNodeHavingRightNode(root, x)) {
            return false;
        }
        return true;
    }

    public static boolean parentNodeHavingRightNode(TreeNode root, int x) {
        if (root == null) {
            return false;
        }
        if (root.left != null && root.left.val == x) {
            if (root.right != null) {
                return true;
            } else {
                return false;
            }
        }
        if (root.right != null && root.right.val == x) {
            if (root.left != null) {
                return true;
            } else {
                return false;
            }
        }
        boolean leftRes = parentNodeHavingRightNode(root.left, x);
        boolean rightRes = parentNodeHavingRightNode(root.right, x);
        return leftRes || rightRes;
    }

    /**
     *
     * @param args
     */

//    private static int[][] mem;
//    public static int longestDecomposition(String text) {
//        int N = text.length();
//        mem = new int[N][N];
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//                mem[i][j] = -1;
//        return helper(text, 0, N-1);
//    }
//    private static int helper(String text, int left, int right){
//        if (left == right) return 1;
//        if (left > right) return 0;
//        if (mem[left][right] != -1) return mem[left][right];
//        int ans = 0;
//
//        int i = left, j = right;
//        while (i < j) {
//            String s1 = text.substring(left, i+1);
//            String s2 = text.substring(j, right+1);
//            if (s1.compareTo(s2) == 0) {
//                ans = Math.max(ans, 2 + helper(text,i+1, j-1));
//            }
//            i++; j--;
//        }
//        if (ans == 0) ans++;
//        mem[left][right] = ans;
//        return ans;
//    }

    /**
     * 输入：text = "ghiabcdefhelloadamhelloabcdefghi"
     * 输出：7
     * 解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
     * <p>
     * 输入：text = "merchant"
     * 输出：1
     * 解释：我们可以把字符串拆分成 "(merchant)"。
     * <p>
     * 输入：text = "antaprezatepzapreanta"
     * 输出：11
     * 解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
     *
     * @param text
     * @return
     */

    public static int longestDecomposition(String text) {
        int[][] mem = new int[text.length()][text.length()];
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < text.length(); j++) {
                mem[i][j] = -1;
            }
        }
        return helper(text, 0, text.length() - 1, mem);
    }

    public static int helper(String text, int left, int right, int[][] mem) {
        if (left == right) {
            return 1;
        }
        if (left > right) {
            return 0;
        }
        if (mem[left][right] != -1) {
            return mem[left][right];
        }
        int i = left;
        int j = right;
        int ans = 0;
        while (i < j) {
            if (text.substring(left, i + 1).equals(text.substring(j, right + 1))) {
                ans = Math.max(ans, 2 + helper(text, i + 1, j - 1, mem));
            }
            i++;
            j--;
        }
        if (ans == 0) {
            ans++;
        }
        mem[left][right] = ans;
        return ans;
    }

    /**
     * 输入：target = "leet"
     * 输出："DDR!UURRR!!DDD!"
     *
     * @param args
     * board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"].
     */
    private static int row1 = 0;
    private static int col1 = 0;

    public static String alphabetBoardPath(String target) {

        char[][] boardChar = {{'a', 'b', 'c', 'd', 'e'},
                {'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o'},
                {'p', 'q', 'r', 's', 't'},
                {'u', 'v', 'w', 'x', 'y'},
                {'z'}};
        boolean[][] flag = new boolean[boardChar.length][boardChar[0].length];

        String result = "";
        int row = 0;
        int col = 0;
        for (int i = 0; i < target.length(); i++) {
            broadDfs(target, i, boardChar, row1, col1, result, flag);
            boolean isLastRow = false;
            if (row1 > row) {
                int val = row1 - row;
                if (row1 == 5 && col != col1) {
                    isLastRow = true;
                    while (val-- > 1) {
                        result += "D";
                    }
                } else {
                    while (val-- > 0) {
                        result += "D";
                    }
                }
            }
            if (row1 < row) {
                int val = row - row1;
                while (val-- > 0) {
                    result += "U";
                }
            }
            if (col1 > col) {
                int val = col1 - col;
                while (val-- > 0) {
                    result += "R";
                }
            }
            if (col1 < col) {
                int val = col - col1;
                while (val-- > 0) {
                    result += "L";
                }
            }
            if (isLastRow) {
                result += "D";
            }
            result += "!";
            row = row1;
            col = col1;
        }
        return result;
    }

    public static void broadDfs(String target, int pos, char[][] board, int row, int col, String result, boolean[][] flag) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(row);
        queue.offer(col);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size - 2 >= 0) {
                size = size - 2;
                int x = queue.poll();
                int y = queue.poll();
                if (board[x][y] == target.charAt(pos)) {
                    row1 = x;
                    col1 = y;
                    return;
                }
                if (x + 1 < board.length && y < board[x + 1].length && !flag[x + 1][y]) {
                    queue.offer(x + 1);
                    queue.offer(y);
                }
                if (y + 1 < board[x].length && !flag[x][y + 1]) {
                    queue.offer(x);
                    queue.offer(y + 1);
                }
                if (x - 1 >= 0 && !flag[x - 1][y]) {
                    queue.offer(x - 1);
                    queue.offer(y);
                }
                if (y - 1 >= 0 && !flag[x][y - 1]) {
                    queue.offer(x);
                    queue.offer(y - 1);
                }
            }
        }
    }

    /**
     * 输入：grid = [[1,1,1],
     * [1,0,1],
     * [1,1,1]]
     * 输出：9
     */
    public static int largestBoard(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] left = new int[n][m];
        int[][] up = new int[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    up[i][j] = (grid[i][j] == 1) ? 1 : 0;
                } else {
                    up[i][j] = (grid[i][j] == 1) ? 1 + up[i - 1][j] : 0;
                }
                if (j == 0) {
                    left[i][j] = (grid[i][j] == 1) ? 1 : 0;
                } else {
                    left[i][j] = (grid[i][j] == 1) ? 1 + left[i][j - 1] : 0;
                }
                for (int k = 1; k <= Math.min(i, j) + 1; k++) {
                    if (left[i][j] < k) {
                        continue;
                    }
                    if (up[i][j] < k) {
                        continue;
                    }
                    if (left[i - k + 1][j] < k) {
                        continue;
                    }
                    if (up[i][j - k + 1] < k) {
                        continue;
                    }
                    res = Math.max(res, k * k);
                }
            }
        }
        return res;
    }

    /**
     * 自然数数组的排序
     *
     * @param args
     */
    public static void sortNaturalNumber(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i + 1) {
                swap(arr, i, arr[i] - 1);
            }
        }
    }

    /**
     * 奇数下标都是奇数，偶数下标都是偶数
     *
     * @param arr
     */
    public static void adjustIndex(int[] arr) {

        int even = 0;
        int odd = 1;
        int end = arr.length - 1;
        while (even <= end && odd <= end) {
            if ((arr[end] & 1) != 0) {
                swap(arr, end, odd);
                odd += 2;
            } else {
                swap(arr, end, even);
                even += 2;
            }
        }

    }

    /**
     * 子数组的最大累计和问题
     *
     * @param args
     */
    public static int maxSum(int[] arr) {
        int max = 0;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {

            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    /**
     * 数组中未出现的最小正整数
     * 时间复杂度O(N) 空间复杂度O(1)
     *
     * @param args
     */
    public static int getNoOccurMininumInteger(int[] arr) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= 1 || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                arr[l] = arr[--r];
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public static boolean isMajorityElement(int[] nums, int target) {
        int N = nums.length;
        int val = nums[N / 2];
        if (target != val) {
            return false;
        }
        int left = findLeftPos(nums, target);
        int right = findLeftPos(nums, target + 1);

        if (nums[right] == target + 1) {
            right = right - 1;
        }
        System.out.println("left==" + left + " rigth==" + right);
        return right - left + 1 > (N >> 1);
    }

    public static int findLeftPos(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else if (arr[mid] > target) {
                hi = mid - 1;
            } else {
                hi = mid;
            }
            System.out.println("11111111");
        }
        return lo;
    }
//    public static int findRightPos(int[]arr,int target){
//        int lo=0;
//        int hi=arr.length-1;
//        while(lo<hi){
//            int mid=(lo+hi)>>1;
//            if(arr[mid]<target){
//                lo=mid+1;
//            }else if(arr[mid]>target){
//                hi=mid-1;
//            }else{
//                lo=mid;
//            }
////            System.out.println("2222222222222");
//        }
//        return lo;
//    }


    /**
     * 输入：[1,0,1,0,1]
     * 输出：1
     * 解释：
     * 有三种可能的方法可以把所有的 1 组合在一起：
     * [1,1,1,0,0]，交换 1 次；
     * [0,1,1,1,0]，交换 2 次；
     * [0,0,1,1,1]，交换 1 次。
     * 所以最少的交换次数为 1。
     *
     * @param data
     * @return
     */

    public static int minSwaps(int[] data) {
        int oneNum = 0;
        int[] oneSum = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                oneNum++;
                if (i == 0) {
                    oneSum[0] = 1;
                } else {
                    oneSum[i] = oneSum[i - 1] + 1;
                }
            } else {
                if (i == 0) {
                    oneSum[0] = 0;
                } else {
                    oneSum[i] = oneSum[i - 1];
                }
            }
        }
        if (oneNum == 0) {
            return 0;
        }
        int minTime = data.length;
        for (int j = 0; j <= data.length - oneNum; j++) {
            int time = 0;
            if (data[j] == 1) {
                time = oneSum[j + oneNum - 1] - oneSum[j] + 1;
            } else {
                time = oneSum[j + oneNum - 1] - oneSum[j];
            }
            minTime = Math.min(minTime, oneNum - time);
        }
        return minTime;
    }

    /**
     * 输入：username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
     * 输出：["home","about","career"]
     * 解释：
     * 由示例输入得到的记录如下：
     * ["joe", 1, "home"]
     * ["joe", 2, "about"]
     * ["joe", 3, "career"]
     * ["james", 4, "home"]
     * ["james", 5, "cart"]
     * ["james", 6, "maps"]
     * ["james", 7, "home"]
     * ["mary", 8, "home"]
     * ["mary", 9, "about"]
     * ["mary", 10, "career"]
     *
     * @param username
     * @param timestamp
     * @param website
     * @return
     */
    public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        String[][] detials = new String[username.length][3];
        for (int i = 0; i < username.length; i++) {
            detials[i][0] = username[i];
            detials[i][1] = website[i];
            detials[i][2] = Integer.toString(timestamp[i]);
            System.out.println(detials[i][2]);
        }
        Arrays.sort(detials, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (!o1[0].equals(o2[0])) {
                    return o1[0].compareTo(o2[0]);
                } else {
                    if (o1[2].length() > o2[2].length()) {
                        return 0;
                    } else if (o1[2].length() < o2[2].length()) {
                        return 1;
                    } else {
                        return o2[2].compareTo(o1[2]);
                    }
                }
            }
        });
        for (int i = 0; i < detials.length; i++) {
            System.out.print(detials[i][0] + "   " + detials[i][1]);
            System.out.println();
        }
        System.out.println("------------------------------------------------");

        Map<List<String>, Integer> map = new HashMap<>();
        for (int j = 0; j < detials.length; j++) {
            List<String> list = new ArrayList<>();
            int k = j;
            while (k < detials.length && detials[k][0].equals(detials[j][0])) {
                list.add(detials[k][1]);
                k++;
            }

            if (map.containsKey(list)) {
                map.put(list, map.get(list) + 1);
            } else {
                map.put(list, 1);
            }
        }
        int max = 0;
        for (List in : map.keySet()) {
            if (in.size() == 3) {
                max = Math.max(max, map.get(in));
            } else {
                continue;
            }
        }
        int time = 0;
        List<List<String>> res = new ArrayList<>();
        List<String> ret = new ArrayList<>();
        for (List in : map.keySet()) {
            if (in.size() == 3) {
                if (map.get(in) == max) {
                    time++;
                    ret = in;
                    if (!res.contains(in)) {
                        res.add(in);
                    }
                    res.add(ret);
                }
            }
        }
        if (time == 1 && map.size() != 1) {
            return ret;
        }
        Collections.sort(res, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                if (!o1.get(0).equals(o2.get(0))) {
                    return o1.get(0).compareTo(o2.get(0));
                }
                if (!o1.get(1).equals(o2.get(1))) {
                    return o1.get(1).compareTo(o2.get(1));
                } else {
                    return o1.get(2).compareTo(o2.get(2));
                }
            }
        });
        return res.get(0);

    }

    /**
     * 并查集
     *
     * @param args
     */
    public static int findCircleNum(int[][] M) {
        UF uf = new UF(M.length);
        for (int i = 0; i < M.length - 1; i++) {
            for (int j = i + 1; j < M[i].length; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count;

    }

    static class UF {
        public int count;
        private int[] id;
        private int[] sz;

        public UF(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                sz[i] = 1;
            }
            this.count = N;
        }

        public int find(int k) {
            while (id[k] != k) {
                k = id[k];
            }
            return k;
        }

        public boolean isConnection(int m, int n) {
            int p = find(m);
            int q = find(n);
            if (p == q) {
                return true;
            }
            return false;
        }

        public void union(int m, int n) {
            if (isConnection(m, n)) {
                return;
            }
            int p = find(m);
            int q = find(n);
            if (sz[p] < sz[q]) {
                id[m] = q;
                sz[q] += sz[p];
            } else {
                id[n] = p;
                sz[p] += sz[q];
            }
            count--;
        }


    }

    public static int videoStitching(int[][] clips, int T) {

        int ret = 0;
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
//         for (int i = 0; i < clips.length; i++) {
//             // System.out.println("clips[" + i + "]" + "[" + 0 + "]==" + clips[i][0] + "---" + "clips[" + i + "]" + "[" + 1 + "]==" + clips[i][1]);

//         }
        if (clips[0][0] > 0) {
            return -1;
        }
        if (clips[clips.length - 1][clips[0].length - 1] < T) {
            return -1;
        }
        int val = 0;
        int k = 0;

        for (int i = 1; i < clips.length; i++) {
            if (clips[i][0] > 0) {
                k = i - 1;
                val = clips[i - 1][1];
                break;
            }
        }
        ret++;

        while (k < clips.length && val < T) {
            // System.out.println("k==" + k + "val==" + val);
            val = findRight(clips, k, val);
            ret++;
            if (val >= T) {
                return ret;
            }
        }
        return ret;
    }

    public static int findRight(int[][] clips, int i, int val) {
        int k = i;

        while (i < clips.length) {
            if (clips[i][0] > val) {
                break;
            }
            i++;
        }
        int p = i - 1;
        int res = clips[i - 1][1];
        while (i > k) {
            if (clips[i - 1][1] >= val && clips[i - 1][1] >= res) {
                p = i;
                res = clips[i - 1][1];
            }
            i--;
        }
        val = res;
        i = p;
        return res;
    }

    /**
     * 输入：text = "ghiabcdefhelloadamhelloabcdefghi"
     * 输出：7
     * 解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */

    public static int longestDecomposition2(String str) {
        int[][] mem = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                mem[i][j] = -1;
            }
        }
        return solved(str, 0, str.length(), mem);
    }

    public static int solved(String str, int left, int right, int[][] mem) {

        if (mem[left][right] != -1) {
            return mem[left][right];
        }
        int i = left;
        int j = right;
        int ans = 0;
        while (i < j) {
            if (str.substring(left, i + 1).equals(str.substring(j, right + 1))) {
                ans = Math.max(ans, 2 + solved(str, i, j, mem));
            }
            i++;
            j++;
        }
        if (ans == 0) {
            ans++;
        }
        mem[left][right] = ans;
        return ans;
    }
//     public static void main(String[] args) {
//         int[][] clips ={{16,18},{16,20},{3,13},{1,18},{0,8},{5,6},{13,17},{3,17},{5,6}};

//         System.out.println(videoStitching(clips,15));
//     }

    /**
     * 输入：n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
     * 输出：3
     * 解释：
     * 上图展示了铺设管道连接房屋的成本。
     * 最好的策略是在第一个房子里建造水井（成本为 1），然后将其他房子铺设管道连起来（成本为 2），所以总成本为 3。
     *
     * @param args
     */
    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {

        Arrays.sort(pipes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Math.min(wells[o1[1] - 1] + wells[o1[0] - 1], Math.min(o1[0] + o1[2], o1[1] + o1[2])) - Math.min(wells[o2[1] - 1] + wells[o2[0] - 1], Math.min(o2[0] + o2[2], o2[1] + o2[2]));
            }
        });
        int ret = 0;

        boolean[] flag = new boolean[wells.length];
        for (int i = 0; i < pipes.length; i++) {
            if (flag[pipes[i][0] - 1] && flag[pipes[i][1] - 1]) {
                continue;
            } else if (flag[pipes[i][0] - 1]) {
                int min = Math.min(wells[pipes[i][1] - 1], pipes[i][2]);
                flag[pipes[i][1] - 1] = true;
                ret += min;
                continue;
            } else if (flag[pipes[i][1] - 1]) {
                int min = Math.min(wells[pipes[i][0] - 1], pipes[i][2]);
                flag[pipes[i][0] - 1] = true;
                ret += min;
                continue;
            } else {
                int min = Math.min(wells[pipes[i][0] - 1] + wells[pipes[i][1] - 1], Math.min(wells[pipes[i][0] - 1] + pipes[i][2], wells[pipes[i][1] - 1] + pipes[i][2]));
                flag[pipes[i][1] - 1] = true;
                flag[pipes[i][0] - 1] = true;
                ret += min;
            }
        }

        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) {
                ret += wells[i];
            }
        }
        return ret;
    }

    int[] father;

    int find(int x) {
        if (father[x] == x) return x;
        return father[x] = find(father[x]);
    }

    public int minCostToSupplyWater2(int n, int[] wells, int[][] pipes) {
        father = new int[n + 1];
        for (int i = 0; i <= n; i++) father[i] = i;
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{wells[i], 0, i + 1});
        }
        for (int i = 0; i < pipes.length; i++) {
            queue.add(new int[]{pipes[i][2], pipes[i][0], pipes[i][1]});
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int fa = find(cur[1]);
            int fb = find(cur[2]);
            if (fa == fb) continue;
            father[fa] = fb;
            res += cur[0];
        }
        return res;
    }

    /**
     * @param n
     * @return
     */
    public static int numPrimeArrangements(int n) {
        int num = 0;
        for (int i = 1; i <= n; i++) {
            if (isValidNum(i)) {
                num++;
            }
        }
        System.out.println("num==="+num);
        int unNum = n - num;
        long ret=0;
        long numMul=1;
        while(num>=1){
            numMul*=num;
            if(numMul>(10^9)){
                numMul%=10^9;
            }
            if(numMul==(10^9)){
                numMul=1;
            }
            num--;
        }
        System.out.println("numMul==="+numMul);
        long unNumMul=1;
        while(unNum>=1){
            unNumMul*=unNum;
            if(unNumMul>(10^9)){
                unNumMul=unNumMul%10^9;
            }
            if(unNumMul==(10^9)){
                unNumMul=1;
            }
            unNum--;
        }
        System.out.println("unNumMul=="+unNumMul);
        ret=(unNumMul*numMul)%(10^9);
        return (int)ret;
    }

    public static boolean isValidNum(int k) {
        if(k==1){
            return false;
        }
        if ( k == 2) {
            return true;
        }
        int i = 2;
        while (i < k) {
            if (k % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
//        String text="antaprezatepzapreanta";
//        System.out.println(longestDecomposition(text));
//        System.out.println(alphabetBoardPath("zdz"));
//        int[][] grid = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        System.out.println(largestBoard(grid));
//        int[] arr = {-1, 2, 3, 4, 5, 6, 3, 1};
//        System.out.println(getNoOccurMininumInteger(arr));
//        sortNaturalNumber(arr);
//        adjustIndex(arr);
//        System.out.println(maxSum(arr));
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
//

//        int[]nums = {2,4,5,5,5,5,5,6,6};
////        int[]nums = {10,100,101,101};
//        int target = 5;
//        System.out.println(isMajorityElement(nums,target));
//
//        int[] data = {0, 0, 0, 1, 0};
//        System.out.println(minSwaps(data));

//        String[] username = {"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
//        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};
//        String[] username = {"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
//        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};
//        List<String> res = mostVisitedPattern(username, timestamp, website);
//        for (int i = 0; i < res.size(); i++) {
//            System.out.println(res.get(i));
//        }


//        System.out.println(findCircleNum(M));


//
//        int n = 10;
//        int[] wells = {22238,38788,73611,22861,18865,52721,85325,98901,72035,74803};
//
//        int[][] pipes ={{2,1,82145},{3,1,83958},{4,2,52824},{5,4,60736},{6,1,38042},{7,6,30343},{9,3,34316}};
//        System.out.println(minCostToSupplyWater(n, wells, pipes));
        System.out.println(numPrimeArrangements(100));
    }
}







