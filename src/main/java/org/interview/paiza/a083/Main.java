package org.interview.paiza.a083;

import java.io.*;
import java.util.StringTokenizer;

/**
 * ClassName: Main
 * Package: org.example.paiza.a083
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 17:53
 * @Version 1.0
 */
public class Main {
    static StringBuilder sb;
    static String ans;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer fetch = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(fetch.nextToken()), W = Integer.parseInt(fetch.nextToken());
            char[][] chess = new char[H][W];
            for (int i = 0; i < H; i++) {
                fetch = new StringTokenizer(br.readLine());
                chess[i] = fetch.nextToken().toCharArray();
            }
            sb = new StringBuilder();
            helper(0, 0, H, W, chess);
            out.println(sb.toString());
            System.out.println(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void helper(int x, int y, int endX, int endY, char[][] chess) {
        if (x < 0 || x >= endX || y < 0 || y >= endY || chess[x][y] == 0) {
            return;
        }
        char curChar = chess[x][y];
        sb.append(curChar);

        // 2. 终点判定
        if (x == endX - 1 && y == endY - 1) {
            String currentPath = sb.toString();
            if (ans == null || currentPath.compareTo(ans) < 0) {
                ans = currentPath;
            }
        } else {
            // 3. 标记访问并递归
            chess[x][y] = 0; // 使用 '#' 标记已访问
            for (int[] dir : dirs) {
                helper(x + dir[0], y + dir[1], endX, endY, chess);
            }
            chess[x][y] = curChar; // 回溯：恢复现场
        }
        // 4. 回溯：删除当前字符
        sb.deleteCharAt(sb.length() - 1);
    }
}
