package org.interview.paiza.a094;

import java.util.*;

/**
 * ClassName: Main
 * Package: org.example.paiza.a094
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 0:34
 * @Version 1.0
 */
public class Main {


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            List<int[]>[] adj = new List[n + 1];
            Arrays.setAll(adj, p -> new ArrayList<>());
            for (int i = 0; i < m; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int value = scanner.nextInt();
                adj[from].add(new int[]{to, value});
                adj[to].add(new int[]{from, value});
            }
            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(p -> p[0]));
            heap.offer(new int[]{0, 1, 0});
            int[][] distances = new int[n + 1][2];
            for (int[] distance : distances) {
                Arrays.fill(distance, Integer.MAX_VALUE);
            }
            distances[1][0] = 0;
            while (!heap.isEmpty()) {
                int[] out = heap.poll();
                int value = out[0], ver = out[1], used = out[2];
                if (value < distances[ver][used]) {
                    continue;
                }
                for (int[] next : adj[ver]) {
                    int fresh = next[0], path = next[1];
                    if (distances[fresh][used] > path + value) {
                        distances[fresh][used] = path + value;
                        heap.offer(new int[]{distances[fresh][used], fresh, used});
                    }
                    if (used == 0) {
                        if (distances[fresh][0] > path) {
                            distances[fresh][0] = path;
                            heap.offer(new int[]{distances[fresh][used], fresh, 1});
                        }
                    }
                }
            }
            int min = Math.min(distances[n][0], distances[n][1]);
            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        }
    }


}
