package com.example.lic.digui;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @date 2019/12/19 14:26
 * @description 杨辉三角
 */
public class SolutionYangHui {
    public static void main(String args[]) {

        List<List<Integer>> generate = new SolutionYangHui().generate(5);
        for (List<Integer> list : generate) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>();
        }
        if (numRows == 1) {
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            List<List<Integer>> result = new ArrayList<>();
            result.add(integers);
            return result;
        }

        List<List<Integer>> generate = generate(numRows - 1);
        List<Integer> list = generate.get(generate.size() - 1);
        List<Integer> lastList = new ArrayList<>();
        for (int i = 0; i <= list.size(); i ++) {
            int a = i == 0 ? 0 : list.get(i - 1);
            int b = i == list.size() ? 0 : list.get(i);
            lastList.add(a + b);
        }

        generate.add(lastList);
        return generate;
    }
}