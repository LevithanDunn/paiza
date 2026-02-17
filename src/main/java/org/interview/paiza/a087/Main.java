package org.interview.paiza.a087;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ClassName: Main
 * Package: org.example.paiza.a087
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 14:53
 * @Version 1.0
 */
public class Main {


    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer fetch = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(fetch.nextToken());
            int D = Integer.parseInt(fetch.nextToken());
            fetch = new StringTokenizer(br.readLine());
            long[] minerals = new long[N];
            for (int i = 0; i < N; i++) {
                minerals[i] = Long.parseLong(fetch.nextToken());
            }
            long[] have = new long[D];
            Arrays.sort(minerals);
            fetch = new StringTokenizer(br.readLine());
            for (int i = 0; i < D; i++) {
                have[i] = Long.parseLong(fetch.nextToken());
            }
            long[] preSum = new long[N + 1];
            for (int i = 0; i < N; i++) {
                preSum[i + 1] = preSum[i] + minerals[i];
            }
            long total = 0L;
            for (long x : have) {
                total += helper(x, preSum);
            }
            out.println(total);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long helper(long x, long[] preSum) {
        int left = -1, right = preSum.length;
        while (left + 1 < right) {
            int mid = left + ((right - left) / 2);
            if (x >= preSum[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left == -1 ? 0 : left;
    }

}
