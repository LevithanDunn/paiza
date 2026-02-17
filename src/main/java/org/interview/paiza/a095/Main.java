package org.interview.paiza.a095;

import java.util.Scanner;

/**
 * ClassName: Main
 * Package: org.example.paiza.a095
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 0:03
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextLong();
            }
            System.out.println(solveCorrectly(arr));
        }
    }

    private static long solveCorrectly(long[] arr) {
        long total = 0, k = 0;
        int n = arr.length;
        total += (long) n - 1;
        for (int i = 0; i < n - 2; i++) {
            if (arr[i + 1] * arr[i + 1] == arr[i] * arr[i + 2]) {
                k++;
                total += k;
            } else {
                k = 0;
            }
        }
        return total;
    }
}
