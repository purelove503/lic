package com.example.lic.digui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wy
 * @date 2019/12/19 14:26
 * @description 杨辉三角
 */
public class SolutionYangHuiV1 {
    public static void main(String args[]) {
        long start = System.currentTimeMillis();

        List<Integer> row1 = new SolutionYangHuiV1().getRow1(2000);
        System.out.println("耗时:" + (System.currentTimeMillis() - start) + " result: " + row1);

    }

    public List<Integer> getRow(int numRows) {
        if (numRows == 0) {
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            return integers;
        }

        List<Integer> generate = getRow(numRows - 1);
        List<Integer> lastList = new ArrayList<>();
        for (int i = 0; i <= generate.size(); i ++) {
            int a = i == 0 ? 0 : generate.get(i - 1);
            int b = i == generate.size() ? 0 : generate.get(i);
            lastList.add(a + b);
        }

        return lastList;
    }

    public List<Integer> getRow1(int rowIndex) {
        Integer[] dp = new Integer[rowIndex + 1];
        Arrays.fill(dp,1);

        for(int i = 2;i < dp.length;i++){
            for(int j = i - 1;j > 0;j--)
                dp[j] = dp[j] + dp[j - 1];
        }
        List<Integer> res = Arrays.asList(dp);
        return res;
    }
}