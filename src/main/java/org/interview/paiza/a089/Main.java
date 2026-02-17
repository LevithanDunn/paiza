package org.interview.paiza.a089;

import java.io.*;
import java.util.StringTokenizer;

/**
 * ClassName: Main
 * Package: org.example.paiza.a089
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 12:27
 * @Version 1.0
 */
public class Main {

    static int[][] moveA = {{-1, -1}, {-1, 0}, {-1, 1}, {1, -1}, {1, 1}}, moveB = {{1, -2}, {-1, 2}};

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer fetch = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(fetch.nextToken()) - 1;
            int y = Integer.parseInt(fetch.nextToken()) - 1;
            int total = Integer.parseInt(fetch.nextToken());
            boolean[][][][] dp = new boolean[total + 1][2][9][9];
            dp[0][0][x][y] = true;
            for (int k = 0; k < total; k++) {
                for (int row = 0; row < 9; row++) {
                    for (int col = 0; col < 9; col++) {
                        if (dp[k][0][row][col]) {
                            for (int[] towards : moveA) {
                                int nx = row + towards[0], ny = col + towards[1];
                                if (0 <= nx && nx < 9 && 0 <= ny && ny < 9) {
                                    dp[k + 1][0][nx][ny] = true;
                                    dp[k + 1][1][nx][ny] = true;
                                }
                            }
                        }
                        if (dp[k][1][row][col]) {
                            for (int[] towards : moveB) {
                                int nx = row + towards[0], ny = col + towards[1];
                                if (0 <= nx && nx < 9 && 0 <= ny && ny < 9) {
                                    dp[k + 1][1][nx][ny] = true;
                                }
                            }
                        }
                    }
                }
            }
            long count = 0L;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (dp[total][0][i][j] || dp[total][1][i][j]) {
                        count++;
                    }
                }
            }
            out.println(count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
