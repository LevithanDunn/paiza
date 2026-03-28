package org.interview.paiza.a011;

/**
 * ClassName: Main
 * Package: org.interview.paiza.a011
 * Description:
 *
 * @Author Dunn
 * @Create 2026/3/28 13:42
 * @Version 1.0
 */

import java.io.*;
import java.util.StringTokenizer;

/**
 * ClassName: Main
 * Package: org.example.challenge.a011
 * Description:
 *
 * @Author Dunn
 * @Create 2026/3/28 8:03
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(tokenizer.nextToken()), n = Integer.parseInt(tokenizer.nextToken());
            boolean[][] dp = new boolean[n + 1][(d << 1) + 1];
            dp[0][d] = true;
            int[] steps = new int[n];
            for (int i = 0; i < n; i++) {
                steps[i] = Integer.parseInt(br.readLine());
            }
            for (int i = 0; i < n; i++) {
                int move = steps[i];
                for (int pos = 0; pos <= (d << 1); pos++) {
                    if (dp[i][pos]) {
                        if (pos - move >= 0) {
                            dp[i + 1][pos - move] = true;
                        }
                        if (pos + move <= (d << 1)) {
                            dp[i + 1][pos + move] = true;
                        }
                    }
                }
            }
            int curPos = -1;
            for (int i = 0; i <= (d << 1); i++) {
                if (dp[n][i]) {
                    curPos = i;
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = n - 1; i >= 0; i--) {
                int move = steps[i];
                if (curPos - move >= 0 && dp[i][curPos - move]) {
                    sb.append("R");
                    curPos -= move;
                } else {
                    sb.append("L");
                    curPos += move;
                }
            }
            out.println(sb.reverse());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}