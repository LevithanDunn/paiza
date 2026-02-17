package org.interview.paiza.a035;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName: Main
 * Package: org.example.paiza.a035
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 22:00
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            boolean[] credits = new boolean[101];
            credits[0] = true;
            for (int i = 0; i < n; i++) {
                int score = scanner.nextInt();
                for (int j = 100; j >= score; j--) {
                    if (credits[j - score]) {
                        credits[j] = true;
                    }
                }
            }
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                if (credits[i]) {
                    list.add(i);
                }
            }
            System.out.println(list.size());
            for (Integer show : list) {
                System.out.println(show);
            }
        }
    }
}
