package com.example.lic.digui;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wy
 * @date 2019/12/27 14:01
 * @description Fibonacci sequence 斐波那契数列
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 */
public class FibonacciSequence {

    static Map<Integer, Integer> map = new HashMap<>();

    public int fib(int N) {
        if (map.containsKey(N)) {
            return map.get(N);
        }
        if (N < 2) {
            return N;
        }

        int result = fib(N - 1) + fib(N - 2);
        map.put(N, result);
        return result;
    }
}