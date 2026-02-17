package org.interview.paiza.a091;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName: Main
 * Package: org.example.paiza.a091
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 10:24
 * @Version 1.0
 */
public class Main {


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int N = scanner.nextInt();
            int[] prices = new int[N];
            for (int i = 0; i < N; i++) {
                prices[i] = scanner.nextInt();
            }
            int[][] discounts = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    discounts[i][j] = scanner.nextInt();
                }
            }
            int mask = (1 << N);
            long[] dp = new long[mask];
            Arrays.fill(dp, Long.MAX_VALUE / 2);
            dp[0] = 0L;
            for (int tmpMask = 0; tmpMask < mask; tmpMask++) {
                if (dp[tmpMask] == Long.MAX_VALUE / 2) {
                    continue;
                }
                int i = 0;
                while (i < N && (tmpMask >> i & 1) != 0) {
                    i++;
                }
                if (i == N) {
                    break;
                }
                int freshMask = tmpMask | (1 << i);
                dp[freshMask] = Math.min(dp[freshMask], dp[tmpMask] + prices[i]);
                for (int j = 0; j < N; j++) {
                    int tempFreshMask = tmpMask | (1 << i) | (1 << j);
                    long cost = prices[i] + prices[j] - discounts[i][j];
                    dp[tempFreshMask] = Math.min(dp[tempFreshMask], cost + dp[tmpMask]);
                }
            }
            System.out.println(dp[mask - 1]);
        }
    }

}
