package org.interview.paiza.a029;

import java.io.*;
import java.util.*;

/**
 * ClassName: Main
 * Package: org.interview.paiza.a029
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/28 12:00
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            int G = Integer.parseInt(tokenizer.nextToken());
            List<Integer>[] adj = new List[N + 1], revAdj = new List[N + 1];
            Arrays.setAll(adj, p -> new ArrayList<>());
            Arrays.setAll(revAdj, p -> new ArrayList<>());
            for (int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(tokenizer.nextToken());
                int to = Integer.parseInt(tokenizer.nextToken());
                adj[from].add(to);
                revAdj[to].add(from);
            }
            int reachCount = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(G);
            boolean[] visited = new boolean[N + 1];
            visited[G] = true;
            while (!queue.isEmpty()) {
                int fresh = queue.poll();
                reachCount++;
                for (Integer next : revAdj[fresh]) {
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
            out.println(reachCount == N ? "OK" : "NG");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
