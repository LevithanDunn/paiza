package org.interview.paiza.a075;

import java.util.Scanner;

/**
 * ClassName: Main
 * Package: org.example.paiza.a075
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/17 22:10
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            long[][] matrix = new long[n][3];
            for (int i = 0; i < n; i++) {
                matrix[i][0] = scanner.nextLong();
                matrix[i][1] = scanner.nextLong();
                matrix[i][2] = scanner.nextLong();
            }
        }
    }

}
