package com.example.lic.digui;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wy
 * @date 2019/12/27 14:10
 * @description 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairs {
    static Map<Integer, Integer> map = new HashMap<>();
    public int climbStairs(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int result = climbStairs(n - 1) + climbStairs(n - 2);
        map.put(n, result);
        return result;
    }

    /**
     * 假设已经走了 i 层台阶
     *
     * @param n
     * @author wy
     * @date 2019/12/27 14:28
     * @return:
     */
    public int climbStairsV1(int n) {
        int[] memo = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }

    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }

        if (i == n) {
            return 1;
        }

        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }

    /**
     * 单次循环解决
     *
     * @param n
     * @author wy
     * @date 2019/12/27 14:36
     * @return:
     */
    public int climbStairsV2(int n) {
        if (n <= 2) {
            return n;
        }

        int dp[] = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i ++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 通过数学规律，当前位置 = 为前一 + 前二
     * TODO 相对于V2， 优化空间
     *
     * @param n
     * @author wy
     * @date 2019/12/27 14:39
     * @return:
     */
    public int climbStairsV3(int n) {
        if (n <= 2) {
            return n;
        }

        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i ++) {
            int three = first + second;
            first = second;
            second = three;
        }
        return second;
    }
}