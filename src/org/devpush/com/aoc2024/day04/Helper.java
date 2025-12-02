package org.devpush.com.aoc2024.day04;

public class Helper {

    public int DFS(int i, int j, char[][] matrix) {
        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };
        int counter = 0;
        String target = "XMAS";
        for (int[] dir : directions) {
            int newRow = i;
            int newCol = j;
            StringBuilder sb = new StringBuilder();

            // Traverse in the current direction for the length of the target string
            for (int k = 0; k < target.length(); k++) {
                if (isSafe(newRow, newCol, matrix)) {
                    sb.append(matrix[newRow][newCol]);
                    newRow += dir[0];
                    newCol += dir[1];
                } else {
                    break;
                }
            }

            // Check if the constructed string matches the target
            if (sb.toString().equals(target)) {
                counter++;
            }
        }
        return counter;

    }

    public boolean isPatternFound(int i, int j, char[][] matrix) {
        // Offsets for 'M' diagonally from A
        int[][] mOffsets = {
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        if (isSquareSafe(i, j, matrix)) {

            int lefttopx = i + mOffsets[0][0];
            int lefttopy = j + mOffsets[0][1];

            int righttopx = i + mOffsets[1][0];
            int righttopy = j + mOffsets[1][1];

            int leftbottomx = i + mOffsets[2][0];
            int leftbottomy = j + mOffsets[2][1];

            int rightbottomx = i + mOffsets[3][0];
            int rightbottomy = j + mOffsets[3][1];

            if ((matrix[lefttopx][lefttopy] == 'M' && matrix[rightbottomx][rightbottomy] == 'S') &&
                    (matrix[righttopx][righttopy] == 'S' && matrix[leftbottomx][leftbottomy] == 'M')) {
                return true;
            }

            if ((matrix[lefttopx][lefttopy] == 'S' && matrix[rightbottomx][rightbottomy] == 'M') &&
                    (matrix[righttopx][righttopy] == 'M' && matrix[leftbottomx][leftbottomy] == 'S')
            ) {
                return true;
            }

            if ((matrix[lefttopx][lefttopy] == 'M' && matrix[rightbottomx][rightbottomy] == 'S') &&
                    (matrix[righttopx][righttopy] == 'M' && matrix[leftbottomx][leftbottomy] == 'S')
            ) {
                return true;
            }

            if ((matrix[lefttopx][lefttopy] == 'S' && matrix[rightbottomx][rightbottomy] == 'M') &&
                    (matrix[righttopx][righttopy] == 'S' && matrix[leftbottomx][leftbottomy] == 'M')
            ) {
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }

    }

    private static boolean isSafe(int i, int j, char[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        return (i >= 0 && i < rowSize) && (j >= 0 && j < colSize);
    }

    private static boolean isSquareSafe(int i, int j, char[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        return (i >= 1 && i < rowSize - 1) && (j >= 1 && j < colSize - 1);
    }
}
