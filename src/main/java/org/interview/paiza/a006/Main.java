package org.interview.paiza.a006;

import java.util.*;

/**
 * ClassName: Main
 * Package: org.example.paiza.a006
 * Description:
 *
 * @Author Dunn
 * @Create 2026/2/15 9:34
 * @Version 1.0
 */
public class Main {

    static class Dwarf {
        // 番号
        int id;
        // 座標
        int x, y;
        int stepCount = 0;
        int maxStep = 1;
        boolean moving = true;
        int dir = 0;
        int turnCount = 0;

        public Dwarf(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        int[] getNextStep() {
            int nx = this.x, ny = this.y;
            switch (dir) {
                case 0:
                    nx++;
                    break;
                case 1:
                    ny--;
                    break;
                case 2:
                    nx--;
                    break;
                case 3:
                    ny++;
                    break;
            }
            return new int[]{nx, ny};
        }

        void move() {
            int[] pair = getNextStep();
            this.x = pair[0];
            this.y = pair[1];
            stepCount++;
            if (stepCount == maxStep) {
                stepCount = 0;
                dir = (dir + 1) % 4;
                turnCount++;
                if (turnCount % 2 == 0) {
                    maxStep++;
                }
            }
        }

    }


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            List<Dwarf> list = new ArrayList<>();
            Set<String> marked = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt(), y = scanner.nextInt();
                list.add(new Dwarf(i, x, y));
                marked.add(x + ">" + y);
            }
            long t = 0L;
            while (true) {
                boolean anyMove = false;
                for (Dwarf dwarf : list) {
                    if (dwarf.moving) {
                        anyMove = true;
                        break;
                    }
                }
                if (!anyMove) {
                    break;
                }
                Map<String, Integer> targetMap = new HashMap<>();
                Map<Integer, String> countPosition = new HashMap<>();
                for (Dwarf dwarf : list) {
                    if (dwarf.moving) {
                        int[] pair = dwarf.getNextStep();
                        String locate = pair[0] + ">" + pair[1];
                        countPosition.put(dwarf.id, locate);
                        targetMap.merge(locate, 1, Integer::sum);
                    }
                }
                List<Dwarf> stops = new ArrayList<>(), moves = new ArrayList<>();
                for (Dwarf dwarf : list) {
                    if (dwarf.moving) {
                        String locate = countPosition.get(dwarf.id);
                        if (marked.contains(locate) || targetMap.get(locate) > 1) {
                            stops.add(dwarf);
                        } else {
                            moves.add(dwarf);
                        }
                    }
                }
                for (Dwarf stop : stops) {
                    stop.moving = false;
                }
                for (Dwarf move : moves) {
                    move.move();
                    marked.add(countPosition.get(move.id));
                }
                if (anyMove) {
                    t++;
                }
            }
            System.out.println(t);
        }
    }
}
