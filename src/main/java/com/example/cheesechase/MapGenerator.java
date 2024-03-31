package com.example.cheesechase;

import java.util.LinkedList;
import java.util.Queue;

public class MapGenerator {
    private int gridSize;
    private char [][] map;

    public MapGenerator (){
        this.gridSize = SessionUtils.getLevel()+4;
        this.map = new char[this.gridSize][this.gridSize];
        generateMap();
    }

    public void generateMap() {
        // Inițializăm harta cu spații libere
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                map[i][j] = '\0';
            }
        }
        map[gridSize-1][gridSize-1] = 'C';
        // Setăm poziția inițială a șoarecelui
        map[0][0] = 'M';

        // Setăm capcanele
        int numTraps = gridSize - 1; // Numărul de capcane este direct proporțional cu n
        for (int i = 0; i < numTraps; i++) {
            int trapRow;
            int trapCol;
            do {
                trapRow = (int) (Math.random() * gridSize);
                trapCol = (int) (Math.random() * gridSize);
            } while (map[trapRow][trapCol] != '\0' || hasPathToCheese(map, trapRow, trapCol, gridSize-1, gridSize-1));

            map[trapRow][trapCol] = 'T';
        }
    }


    // Verificăm dacă există o cale între capcană și brânză
    private boolean hasPathToCheese(char[][] map, int trapRow, int trapCol, int cheeseRow, int cheeseCol) {
        // Folosim algoritmul de căutare BFS (Breadth-First Search)
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[gridSize][gridSize];

        queue.add(new int[]{trapRow, trapCol});
        visited[trapRow][trapCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == cheeseRow && col == cheeseCol) {
                return true; // S-a găsit o cale către brânză
            }

            // Verificăm vecinii - sus, jos, stânga, dreapta
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // sus, jos, stânga, dreapta
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Verificăm dacă noul vecin este în interiorul hărții și este o celulă liberă nevizitată
                if (newRow >= 0 && newRow < gridSize && newCol >= 0 && newCol < gridSize && map[newRow][newCol] == '\0' && !visited[newRow][newCol]) {
                    queue.add(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return false; // Nu s-a găsit nicio cale către brânză
    }



    public char[][] getMap() {
        return map;
    }

    public Integer getGridSize(){
        return gridSize;
    }
}
