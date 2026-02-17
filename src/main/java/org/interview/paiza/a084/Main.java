package org.interview.paiza.a084;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * ClassName: Main
 * Package: org.example.paiza.a084
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 23:50
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            TreeMap<Long, Long> map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                long size = scanner.nextLong();
                map.merge(size, 1L, Long::sum);
            }
            long category = 0L;
            while (!map.isEmpty()) {
                Map.Entry<Long, Long> entry = map.pollFirstEntry();
                long oldBlock = entry.getKey();
                long oldCount = entry.getValue();
                if (oldCount >= 2) {
                    long fresh = oldBlock << 1;
                    long freshCnt = oldCount >> 1;
                    map.merge(fresh, freshCnt, Long::sum);
                }
                if (oldCount % 2 != 0) {
                    category++;
                }
            }
            System.out.println(category);
        }
    }
}
