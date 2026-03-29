package org.interview.paiza.a015;

/**
 * ClassName: Main
 * Package: org.interview.paiza.a015
 * Description:
 *
 * @Author Dunn
 * @Create 2026/3/29 10:18
 * @Version 1.0
 */

import java.util.*;

public class Main {
    static int[][] targetCoords;
    static List<List<int[][]>> blocksRotations = new ArrayList<>();
    static int totalTargetPoints = 0;
    static int totalBlockPoints = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 读取目标形状 (8x8)
        List<int[]> targetList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String line = sc.next();
            for (int j = 0; j < 8; j++) {
                if (line.charAt(j) == '#') {
                    targetList.add(new int[]{i, j});
                }
            }
        }
        totalTargetPoints = targetList.size();
        targetCoords = targetList.toArray(new int[0][]);

        // 2. 读取4个块 (4x4 each)
        for (int k = 0; k < 4; k++) {
            char[][] rawBlock = new char[4][4];
            int blockPoints = 0;
            for (int i = 0; i < 4; i++) {
                String line = sc.next();
                for (int j = 0; j < 4; j++) {
                    rawBlock[i][j] = line.charAt(j);
                    if (rawBlock[i][j] == '#') blockPoints++;
                }
            }
            totalBlockPoints += blockPoints;
            blocksRotations.add(getAllRotations(rawBlock));
        }

        // 基础校验：总点数必须一致
        if (totalTargetPoints != totalBlockPoints) {
            System.out.println("No");
            return;
        }

        // 3. 开始回溯搜索
        boolean[] usedTarget = new boolean[totalTargetPoints];
        boolean[] usedBlock = new boolean[4];
        if (solve(0, usedTarget, usedBlock)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    // 回溯函数
    static boolean solve(int targetIdx, boolean[] usedTarget, boolean[] usedBlock) {
        // 找到目标中第一个还没被覆盖的点
        while (targetIdx < totalTargetPoints && usedTarget[targetIdx]) {
            targetIdx++;
        }

        // 所有点都覆盖了，成功
        if (targetIdx == totalTargetPoints) return true;

        int r = targetCoords[targetIdx][0];
        int c = targetCoords[targetIdx][1];

        // 尝试每一个还没用过的块
        for (int i = 0; i < 4; i++) {
            if (!usedBlock[i]) {
                usedBlock[i] = true;
                // 尝试该块的4种旋转状态
                for (int[][] rotation : blocksRotations.get(i)) {
                    // 尝试将块的第一个点对准目标点 (r, c)
                    // 注意：块的第一个点坐标通常是 (0,0)，偏移量已预处理
                    if (canPlace(rotation, r, c, usedTarget)) {
                        place(rotation, r, c, usedTarget, true);
                        if (solve(targetIdx + 1, usedTarget, usedBlock)) return true;
                        place(rotation, r, c, usedTarget, false); // 回溯
                    }
                }
                usedBlock[i] = false;
            }
        }
        return false;
    }

    static boolean canPlace(int[][] rotation, int r, int c, boolean[] usedTarget) {
        for (int[] offset : rotation) {
            int nr = r + offset[0];
            int nc = c + offset[1];
            int idx = findTargetIdx(nr, nc);
            if (idx == -1 || usedTarget[idx]) return false;
        }
        return true;
    }

    static void place(int[][] rotation, int r, int c, boolean[] usedTarget, boolean val) {
        for (int[] offset : rotation) {
            int idx = findTargetIdx(r + offset[0], c + offset[1]);
            usedTarget[idx] = val;
        }
    }

    static int findTargetIdx(int r, int c) {
        for (int i = 0; i < totalTargetPoints; i++) {
            if (targetCoords[i][0] == r && targetCoords[i][1] == c) return i;
        }
        return -1;
    }

    // 获取一个块的所有旋转坐标（相对坐标）
    static List<int[][]> getAllRotations(char[][] block) {
        Set<String> seen = new HashSet<>();
        List<int[][]> rotations = new ArrayList<>();
        char[][] current = block;
        for (int i = 0; i < 4; i++) {
            int[][] coords = getRelativeCoords(current);
            String sig = Arrays.deepToString(coords);
            if (!seen.contains(sig)) {
                seen.add(sig);
                rotations.add(coords);
            }
            current = rotate90(current);
        }
        return rotations;
    }

    static int[][] getRelativeCoords(char[][] block) {
        List<int[]> list = new ArrayList<>();
        int firstR = -1, firstC = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (block[i][j] == '#') {
                    if (firstR == -1) {
                        firstR = i;
                        firstC = j;
                    }
                    list.add(new int[]{i - firstR, j - firstC});
                }
            }
        }
        return list.toArray(new int[0][]);
    }

    static char[][] rotate90(char[][] b) {
        char[][] res = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[j][3 - i] = b[i][j];
            }
        }
        return res;
    }
}
