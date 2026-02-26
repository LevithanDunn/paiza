package org.interview.paiza.a071;

import java.lang.annotation.Annotation;
import java.util.Scanner;

/**
 * ClassName: Main
 * Package: org.interview.paiza.a071
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/27 2:17
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] dp = new int[n + 1];
            dp[0] = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(scanner.next());
            }
            String digits = sb.toString();
            for (int i = 1; i <= n; i++) {
                dp[i] = dp[i - 1] + (digits.charAt(i - 1) - '0');
                if (i >= 2) {
                    int total = Integer.parseInt(digits.substring(i, i - 2));
                    dp[i] = Math.max(dp[i], dp[i - 2] + total);
                }
            }
            System.out.println(dp[n]);
        }
    }
}
