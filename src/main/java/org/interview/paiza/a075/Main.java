package org.interview.paiza.a075;

import java.util.Scanner;

public class Main {
    static int N;
    static int[][] blocks;
    // memo[使用済み集合][直前のブロックのインデックス][向き(0:w-d, 1:d-w)]
    static Integer[][][] memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        N = sc.nextInt();
        blocks = new int[N][3];
        for (int i = 0; i < N; i++) {
            blocks[i][0] = sc.nextInt(); // h (固定)
            blocks[i][1] = sc.nextInt(); // w
            blocks[i][2] = sc.nextInt(); // d
        }

        // N番目を「地面」として扱う
        memo = new Integer[1 << N][N + 1][2];

        System.out.println(solve(0, N, 0));
    }

    static int solve(int mask, int lastIdx, int ori) {
        if (memo[mask][lastIdx][ori] != null) {
            return memo[mask][lastIdx][ori];
        }

        int curW, curD;
        if (lastIdx == N) {
            // 地面の上にはどんなサイズでも置けるよう十分大きな値を設定
            curW = 1001;
            curD = 1001;
        } else {
            // 前の積み木の向きに応じて現在の許容サイズを決定
            curW = (ori == 0) ? blocks[lastIdx][1] : blocks[lastIdx][2];
            curD = (ori == 0) ? blocks[lastIdx][2] : blocks[lastIdx][1];
        }

        int maxHeight = 0;

        for (int i = 0; i < N; i++) {
            if ((mask & (1 << i)) == 0) {
                // パターン1: そのままの向き (w, d)
                if (blocks[i][1] <= curW && blocks[i][2] <= curD) {
                    maxHeight = Math.max(maxHeight, blocks[i][0] + solve(mask | (1 << i), i, 0));
                }
                // パターン2: 回転させた向き (d, w)
                // 幅と奥行きが同じ場合は計算をスキップ
                if (blocks[i][1] != blocks[i][2]) {
                    if (blocks[i][2] <= curW && blocks[i][1] <= curD) {
                        maxHeight = Math.max(maxHeight, blocks[i][0] + solve(mask | (1 << i), i, 1));
                    }
                }
            }
        }

        return memo[mask][lastIdx][ori] = maxHeight;
    }
}