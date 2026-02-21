package org.interview.paiza.a069;

/**
 * ClassName: Main
 * Package: org.interview.paiza.a069
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/21 20:34
 * @Version 1.0
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();

        long[] x = new long[n];
        long[] y = new long[n];

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextLong();
            y[i] = sc.nextLong();
        }

        int maxInternalStakes = 0;

        // 1. 3つの杭（頂点）の組み合わせをすべて試す
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {

                    // 三角形ABCの面積の2倍
                    long totalArea2 = calculateArea2(x[i], y[i], x[j], y[j], x[k], y[k]);

                    // 面積が0（3点が一直線上）の場合は三角形にならないためスキップ
                    if (totalArea2 == 0) continue;

                    int currentCount = 0;

                    // 2. 他のすべての杭が内部にあるかチェック
                    for (int m = 0; m < n; m++) {
                        // 【重要】選んだ頂点 (i, j, k) 自体はカウントから除外する
                        if (m == i || m == j || m == k) continue;

                        long a1 = calculateArea2(x[m], y[m], x[i], y[i], x[j], y[j]);
                        long a2 = calculateArea2(x[m], y[m], x[j], y[j], x[k], y[k]);
                        long a3 = calculateArea2(x[m], y[m], x[k], y[k], x[i], y[i]);

                        // 3つの小三角形の面積の和が全体の面積と一致すれば、内部（または辺上）
                        if (a1 + a2 + a3 == totalArea2) {
                            currentCount++;
                        }
                    }

                    if (currentCount > maxInternalStakes) {
                        maxInternalStakes = currentCount;
                    }
                }
            }
        }

        // 最終的な最大数を出力（末尾に改行）
        System.out.println(maxInternalStakes);
        sc.close();
    }

    // 三角形の面積の2倍を計算（整数で管理することで精度を保つ）
    private static long calculateArea2(long x1, long y1, long x2, long y2, long x3, long y3) {
        return Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
    }
}
