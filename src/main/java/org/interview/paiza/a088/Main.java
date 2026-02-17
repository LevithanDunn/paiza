package org.interview.paiza.a088;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ClassName: Main
 * Package: org.example.paiza.a088
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 10:53
 * @Version 1.0
 */
public class Main {

    static int[] parents;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(str.nextToken()), M = Integer.parseInt(str.nextToken());
            int[][] edges = new int[M][3];
            reset(N);
            for (int i = 0; i < M; i++) {
                str = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(str.nextToken());
                edges[i][1] = Integer.parseInt(str.nextToken());
                edges[i][2] = Integer.parseInt(str.nextToken());
            }
            Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));
            long minCost = 0L;
            for (int[] edge : edges) {
                if (union(edge[0], edge[1])) {
                    minCost += edge[2];
                }
            }
            out.println(minCost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px != py) {
            parents[px] = py;
            return true;
        }
        return false;
    }

    private static int find(int x) {
        return parents[x] != x ? (parents[x] = find(parents[x])) : x;
    }

    private static void reset(int n) {
        parents = new int[n + 1];
        Arrays.setAll(parents, i -> i);
    }
}
