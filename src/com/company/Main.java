package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};

        int k = scanner.nextInt();

        for (int idx = 1; idx <= k; idx++) {
            int n = scanner.nextInt();
            int[][] inputMap = new int[n][n];
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                String str = scanner.nextLine();
                for (int j = 0; j < n; j++) {
                    inputMap[i][j] = str.charAt(j) == '*' ? 1 : 0;
                }
            }

            int[][] numMap = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (inputMap[i][j] == 1) {
                        numMap[i][j] = 9;
                        continue;
                    }
                    int cnt = 0;
                    for (int p = 0; p < x.length; p++) {
                        int newX = i + x[p];
                        int newY = j + y[p];
                        if (0 <= newX && newX < n && 0 <= newY && newY < n) {
                            if (inputMap[newX][newY] == 1) {
                                cnt += 1;
                            }
                        }
                    }
                    numMap[i][j] = cnt;
                }
            }

            int numOfClick = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (numMap[i][j] == 0) {
                        Queue<Pair> q2 = new LinkedList<>();
                        q2.add(new Pair(i, j));

                        while (!q2.isEmpty()) {
                            Pair pair = q2.remove();

                            if (numMap[pair.x][pair.y] == 0) {
                                numMap[pair.x][pair.y] = -1;

                                for (int p = 0; p < x.length; p++) {
                                    int newX = pair.x + x[p];
                                    int newY = pair.y + y[p];
                                    if (0 <= newX && newX < n && 0 <= newY && newY < n) {
                                        if (numMap[newX][newY] == 0) {
                                            q2.add(new Pair(newX, newY));
                                        } else if (1 <= numMap[newX][newY] && numMap[newX][newY] <= 8) {
                                            numMap[newX][newY] = -1;
                                        }

                                    }

                                }
                            }
                        }
                        numOfClick += 1;
                        q2.clear();


                    }
                }
            }

            // 남은 칸 세기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (1 <= numMap[i][j] && numMap[i][j] <= 8) {
                        numOfClick += 1;
                    }
                }
            }
            System.out.println("#" + idx + " " + numOfClick);
        }
    }
}
