package com.example.lic.digui;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wy
 * @date 2019/12/27 15:40
 * @description 二叉树
 */
public class BTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 求给定二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = 1 + maxDepth(root.left);
        int rightMax = 1 + maxDepth(root.right);
        return leftMax > rightMax ? leftMax : rightMax;
    }

    /**
     * 使用 DFS 策略访问每个结点，同时在每次访问时更新最大深度。
     * @param root
     * @return
     */
    public int maxDepthV1(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        stack.add(new Pair<>(root, 1));

        int result = 0;
        while (!stack.isEmpty()) {
            // 遍历取出
            Pair<TreeNode, Integer> poll = stack.poll();
            Integer value = poll.getValue();
            TreeNode key = poll.getKey();

            // 当前节点不为空时，其子节点入栈
            if (key != null) {
                result = Math.max(result, value);
                stack.add(new Pair<>(key.left, value + 1));
                stack.add(new Pair<>(key.right, value + 1));
            }
        }
        return result;
    }
}