package org.interview.paiza.a021;

/**
 * ClassName: Main
 * Package: org.interview.paiza.a021
 * Description:
 *
 * @Author Dunn
 * @Create 2026/3/1 22:18
 * @Version 1.0
 */

import java.io.*;
import java.util.*;

/**
 * ClassName: Main
 * Package: org.example.paiza.a021
 * Description:
 *
 * @Author Dunn
 * @Create 2026/3/1 21:22
 * @Version 1.0
 */
public class Main {

    static class Island implements Comparable<Island> {
        int area, perimeter;

        public Island(int area, int perimeter) {
            this.area = area;
            this.perimeter = perimeter;
        }

        @Override
        public int compareTo(Island o) {
            if (this.area != o.area) {
                return Integer.compare(o.area, this.area);
            }
            return Integer.compare(o.perimeter, this.perimeter);
        }
    }

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(tokenizer.nextToken());
            int W = Integer.parseInt(tokenizer.nextToken());
            char[][] grid = new char[H][W];
            for (int i = 0; i < H; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                grid[i] = tokenizer.nextToken().toCharArray();
            }
            boolean[][] visited = new boolean[H][W];
            List<Island> list = new ArrayList<>();
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == '#' && !visited[i][j]) {
                        list.add(helper(grid, i, j, visited));
                    }
                }
            }
            Collections.sort(list);
            for (Island island : list) {
                out.println(island.area + " " + island.perimeter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Island helper(char[][] grid, int i, int j, boolean[][] visited) {
        int area = 0, perimeter = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] fresh = queue.poll();
            area++;
            for (int[] dir : dirs) {
                int nx = dir[0] + fresh[0], ny = dir[1] + fresh[1];
                if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[i][j] == '.') {
                    perimeter++;
                } else if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return new Island(area, perimeter);
    }
}