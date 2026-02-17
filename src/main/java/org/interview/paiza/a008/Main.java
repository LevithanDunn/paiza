package org.interview.paiza.a008;

import java.io.*;

/**
 * ClassName: Main
 * Package: org.example.paiza.a008
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/14 22:15
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));) {
            String[] lines = br.readLine().split(" ");
            int N = Integer.parseInt(lines[0]);
            int M = Integer.parseInt(lines[1]);
            int[] days = new int[M];
            for (int i = 0; i < M; i++) {
                days[i] = Integer.parseInt(br.readLine());
            }
            int win = 0, min = Integer.MAX_VALUE;
            int[] category = new int[N + 1];
            for (int left = 0, right = 0; right < M; right++) {
                win += category[days[right]] == 0 ? 1 : 0;
                while (win == N) {
                    min = Math.min(min, right - left + 1);
                    if (--category[days[left]] == 0) {
                        win--;
                    }
                    left++;
                }
            }
            out.println(min);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
