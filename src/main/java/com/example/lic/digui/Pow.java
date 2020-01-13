package com.example.lic.digui;

import java.math.BigDecimal;

/**
 * @author wy
 * @date 2019/12/27 16:37
 * @description 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 *
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 */
public class Pow {

    public static void main(String args[]) {
        System.out.println(new Pow().myPow(0.00001, 100));
    }
/*
    private double pow(Double x, Integer n) {
        double[] num = new double[Math.abs(n) + 1];

        return powV1(x, n, num);



    }

    private double powV1(Double x, Integer n, double[] num) {
        if (x == 0 || x == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n > 1) {
            if (n % 2 == 1) {
                return powV1(x, n / 2, num) * powV1(x, n / 2 + 1, num);
            } else {
                return powV1(x, n / 2, num) * powV1(x, n / 2, num);
            }
        } else{
            if (n % 2 == 1) {
                return powV1(x, n / 2, num) * powV1(x, n / 2 - 1, num);
            } else {
                return powV1(x, n / 2, num) * powV1(x, n / 2, num);
            }
        }
    }*/

    public double myPow(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }
}