package org.interview.paiza.a014;

/**
 * ClassName: Main
 * Package: org.interview.paiza.a014
 * Description:
 *
 * @Author Dunn
 * @Create 2026/3/28 13:44
 * @Version 1.0
 */

import java.util.*;

public class Main {
    static int H, W, N;
    static char[][] grid;

    // 方向向量：0:上, 1:下, 2:左, 3:右
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        H = sc.nextInt();
        W = sc.nextInt();
        N = sc.nextInt();

        // 扩充边界，坐标从 0 到 H+1, 0 到 W+1
        grid = new char[H + 2][W + 2];
        for (int i = 0; i < H + 2; i++) {
            Arrays.fill(grid[i], '.');
        }

        // 修正：逐个读取字符，处理空格分隔的情况
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }

        for (int i = 0; i < N; i++) {
            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();

            if (canRemove(r1, c1, r2, c2)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
        sc.close();
    }

    static boolean canRemove(int r1, int c1, int r2, int c2) {
        if (grid[r1][c1] != grid[r2][c2]) return false;
        if (r1 == r2 && c1 == c2) return false;

        // dist[y][x][dir] 存储到达 (y,x) 且面向 dir 方向时的最小转弯次数
        int[][][] dist = new int[H + 2][W + 2][4];
        for (int i = 0; i < H + 2; i++) {
            for (int j = 0; j < W + 2; j++) {
                Arrays.fill(dist[i][j], 4);
            }
        }

        Queue<Node> q = new LinkedList<>();
        // 从起始点向四个方向发射
        for (int i = 0; i < 4; i++) {
            dist[r1][c1][i] = 0;
            q.add(new Node(r1, c1, i, 0));
        }

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.t > 2) continue;
            if (curr.t > dist[curr.y][curr.x][curr.d]) continue;

            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];
                int nt = (i == curr.d) ? curr.t : curr.t + 1;

                if (nt > 2) continue;

                if (ny >= 0 && ny < H + 2 && nx >= 0 && nx < W + 2) {
                    // 成功连接到目标卡片
                    if (ny == r2 && nx == c2) {
                        return true;
                    }
                    // 只有空格可以穿过
                    if (grid[ny][nx] == '.') {
                        if (nt < dist[ny][nx][i]) {
                            dist[ny][nx][i] = nt;
                            q.add(new Node(ny, nx, i, nt));
                        }
                    }
                }
            }
        }

        return false;
    }

    static class Node {
        int y, x, d, t;

        Node(int y, int x, int d, int t) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.t = t;
        }
    }
}