package code_guide;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author ScXin
 * @date 9/16/2019 10:17 AM
 */

public class Solution {

    //矩阵的最小路径和
    public static int minPathSum(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (col <= 0 || row <= 0) {
            return 0;
        }
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int k = 1; k < row; k++) {
            for (int l = 1; l < col; l++) {
                dp[k][l] = Math.min(dp[k - 1][l], dp[k][l - 1]) + matrix[k][l];
            }
        }
        return dp[row - 1][col - 1];
    }

    //换钱的最少货币数

    public static int minCoinNum(int[] coins, int target) {
        if (target <= 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[] dp = new int[target + 1];
        for (int coin : coins) {
            if (target < coin) {
                continue;
            }
            dp[coin] = 1;
            for (int j = coin + 1; j <= target; j++) {
                if (dp[j - coin] != 0) {
                    if (dp[j] == 0) {
                        dp[j] = dp[j - coin] + 1;
                    } else {
                        dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                    }
                } else {
                    continue;
                }
            }
        }
        return dp[target];
    }

    // 机器人到达指定的位置的方法数
    public static int robotsMethods(int N, int cur, int rest, int P) {
        int[][] dp = new int[N + 1][rest + 1];
        dp[P][0] = 1;
        for (int i = 1; i <= N; i++) {
            if (i != P) {
                dp[i][0] = 0;
            }
        }
        for (int step = 1; step <= rest; step++) {
            for (int n = 1; n <= N; n++) {
                if (n == 1) {
                    dp[n][step] = dp[n + 1][step - 1];
                } else if (n == N) {
                    dp[n][step] = dp[n - 1][step - 1];
                } else {
                    dp[n][step] = dp[n - 1][step - 1] + dp[n + 1][step - 1];
                }
            }
        }
        return dp[cur][rest];
    }

    //换钱的方法数
    public static int coinsNum(int[] coins, int target) {

        if (target <= 0) {
            return 1;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= target; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[target];
    }

    //最长增长子序列
    public static int[] getLongestChildrenSequence(int[] arr) {

        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    System.out.println("dp[" + i + "]==" + dp[i]);
                } else {

                    continue;
                }
            }
        }
        int longest = 0;
        for (int k = 0; k < arr.length; k++) {
            longest = Math.max(longest, dp[k]);
        }
        System.out.println("longest===" + longest);
        int l = arr.length - 1;
        int[] ret = new int[longest];
        int pos = longest - 1;
        while (l >= 0 && pos >= 0) {
            if (dp[l] == longest) {
                ret[pos--] = arr[l--];
                longest--;
            } else {
                l--;
            }
        }
        return ret;
    }

    /**
     * str1="1A2C3D4B56"
     * STR2="B1D23CA45B6A"
     *
     * @param str1
     * @param str2
     * @return
     */
    //最大公共子序列问题
    public static String longestCommonChildSequence(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1][len2];
        if (str1.charAt(0) == str2.charAt(0)) {
            dp[0][0] = 1;
        }
        for (int i = 1; i < len1; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        for (int j = 1; j < len2; j++) {
            if (str2.charAt(j) == str1.charAt(0)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int k = 1; k < len1; k++) {
            for (int l = 1; l < len2; l++) {
                if (str1.charAt(k) == str2.charAt(l)) {
                    dp[k][l] = dp[k - 1][l - 1] + 1;
                } else {
                    dp[k][l] = Math.max(dp[k - 1][l], dp[k][l - 1]);
                }
            }
        }
        int longest = dp[len1 - 1][len2 - 1];
        int n = len1 - 1;
        String ret = "";
        for (int i = len1 - 1; i >= 0; i--) {
            if (longest <= 0) {
                break;
            }
            for (int j = len2 - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j) && dp[i][j] == longest) {
                    ret = str1.charAt(i) + ret;
                    longest--;
                    break;
                } else {
                    continue;
                }
            }
        }
        return ret;
    }

    //最长公共子串问题
    public static String longestCommonChildrenString(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1][len2];
        if (str1.charAt(0) == str2.charAt(0)) {
            dp[0][0] = 1;
        }
        for (int i = 1; i < len1; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int j = 1; j < len2; j++) {
            if (str2.charAt(j) == str1.charAt(0)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        int max = 0;
        int pos = -1;
        for (int k = 1; k < len1; k++) {
            for (int l = 1; l < len2; l++) {
                if (str1.charAt(k) == str2.charAt(l)) {
                    dp[k][l] = dp[k - 1][l - 1] + 1;
                    if (dp[k][l] > max) {
                        max = dp[k][l];
                        pos = k;
                    }
                } else {
                    dp[k][l] = 0;
                }
            }
        }
        return str1.substring(pos - max + 1, pos + 1);

    }

    //最小编辑代价
    public static int minCostofEdit(String str1, String str2, int ic, int dc, int rc) {
        int len1 = str1.length() + 1;
        int len2 = str2.length() + 1;
        int[][] dp = new int[len1][len2];// dp[i][j] represents str1[0...i-1] edits into str2[0...j-1]
        for (int i = 1; i < len1; i++) {
            dp[i][0] = dc * i;
        }
        for (int j = 1; j < len2; j++) {
            dp[0][j] = j * ic;
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[len1 - 1][len2 - 1];
    }


    //龙与地下城问题
    public static int minHP(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (row <= 0 || col <= 0) {
            return 0;
        }
        int[][] dp = new int[row][col];
        if (matrix[row - 1][col - 1] >= 0) {
            dp[row - 1][col - 1] = 1;
        } else {
            dp[row - 1][col - 1] = 1 - matrix[row - 1][col - 1];
        }

        for (int j = col - 2; j >= 0; j--) {
            dp[row - 1][j] = Math.max(1, dp[row - 1][j + 1] - matrix[row - 1][j]);
        }
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = Math.max(1, dp[i + 1][col - 1] - matrix[i][col - 1]);
        }
        for (int k = row - 2; k >= 0; k--) {
            for (int l = col - 2; l >= 0; l--) {
                dp[k][l] = Math.max(Math.min(dp[k + 1][l], dp[k][l + 1]) - matrix[k][l], 1);
            }
        }
        return dp[0][0];
    }

    //数字字符串转换为字母组合的总数

    static List<String> ret = new ArrayList<String>();
    static HashMap<Integer, String> numToStrMap = new HashMap<>();

    public static List<String> numricalStringKindNum(String nums) {
        numToStrMap.put(1, "A");
        numToStrMap.put(2, "B");
        numToStrMap.put(3, "C");
        numToStrMap.put(4, "D");
        numToStrMap.put(5, "E");
        numToStrMap.put(6, "F");
        numToStrMap.put(7, "G");
        numToStrMap.put(8, "H");
        numToStrMap.put(9, "I");
        numToStrMap.put(10, "J");
        numToStrMap.put(11, "K");
        numToStrMap.put(12, "L");
        numToStrMap.put(13, "M");
        numToStrMap.put(14, "N");
        numToStrMap.put(15, "O");
        numToStrMap.put(16, "P");
        numToStrMap.put(17, "Q");
        numToStrMap.put(18, "R");
        numToStrMap.put(19, "S");
        numToStrMap.put(20, "T");
        numToStrMap.put(21, "U");
        numToStrMap.put(22, "V");
        numToStrMap.put(23, "W");
        numToStrMap.put(24, "X");
        numToStrMap.put(25, "Y");
        numToStrMap.put(26, "Z");

        kindNum(nums, 0, "");
        return ret;
    }

    public static void kindNum(String numric, int i, String str) {
        if (i >= numric.length()) {
            ret.add(str);
            return;
        } else if (i == numric.length() - 1) {
            kindNum(numric, i + 1, str + numToStrMap.get(numric.charAt(i) - '0'));
        } else if (Integer.parseInt(numric.substring(i, i + 2)) <= 26) {
            kindNum(numric, i + 1, str + numToStrMap.get(numric.charAt(i) - '0'));
            kindNum(numric, i + 2, str + numToStrMap.get(Integer.parseInt(numric.substring(i, i + 2))));
        } else {
            kindNum(numric, i + 1, str + numToStrMap.get(numric.charAt(i) - '0'));
        }
    }

    //跳跃游戏
    public static int jumpTime(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int jump = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                jump++;
                cur = next;
            }
            next = Math.max(next, arr[i] + cur);
        }
        return jump;
    }

    //数组中的最长子序列
    public static int getLongest(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
                if (map.containsKey(arr[i] - 1)) {
                    max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
                }
                if (map.containsKey(arr[i] + 1)) {
                    max = Math.max(max, merge(map, arr[i], arr[i] + 1));
                }
            }
        }
        return max;
    }

    public static int merge(HashMap<Integer, Integer> map, int less, int more) {
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        map.put(left, left);
        map.put(right, right);
        return left;
    }

    private static List<List<String>> result = new ArrayList<>();

    public static List<List<String>> solveNQueens(int n) {

        if (n < 1) {
            return result;
        }
        int[] record = new int[n];
        List<String> list = new ArrayList<>();
        process(record, 0, n, list);
        return result;
    }

    public static void process(int[] record, int i, int n, List<String> list) {
        if (i == n) {
            List<String> tempList = new ArrayList<>();
            tempList.addAll(list);
            result.add(tempList);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                String str = "";
                for (int k = 0; k < j; k++) {
                    str = str + ".";
                }
                str = str + "Q";
                for (int l = j + 1; l < n; l++) {
                    str = str + ".";
                }
                list.add(str);
                process(record, i + 1, n, list);
                list.remove(str);

            }
        }
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }




    public static void main(String[] args) {
//        int[][] matrix = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
//        System.out.println(minPathSum(matrix));
//        int[] coins = {5, 10, 25, 1};
//        System.out.println(minCoinNum(coins, 20));
//        System.out.println(robotsMethods(5, 4, 3, 5));
//        System.out.println(coinsNum(coins, 15));
//        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
//        int[] ret = getLongestChildrenSequence(arr);
//        for (int i = 0; i < ret.length; i++) {
//            System.out.print(ret[i] + "  ");
//
////       }
//        String str1 = "abc";
//        String str2 = "adc";
//        System.out.println(longestCommonChildSequence(str1, str2));
//        System.out.println(longestCommonChildrenString(str1, str2));
//        System.out.println(minCostofEdit(str1, str2, 5, 3, 100));
//        int[][] matrix = {{-2, -3, 3}, {-5, -10, 1}, {0, 30, -5}};
//        System.out.println(minHP(matrix));
//        String nums = "1111";
//        List<String> ret = numricalStringKindNum(nums);
//        for (int i = 0; i < ret.size(); i++) {
//            System.out.print(ret.get(i));
//            System.out.println();
//        }
//        int[] arr = {3, 2, 3, 1, 1, 4};
//        System.out.println(jumpTime(arr));
        List<List<String>> ret = solveNQueens(4);
        System.out.println(ret.size());
        for (int i = 0; i < ret.size(); i++) {
            System.out.println("===" + ret.get(i).size());
            for (int j = 0; j < ret.get(i).size(); j++) {
                System.out.print(ret.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
