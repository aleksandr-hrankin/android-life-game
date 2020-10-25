package ua.antibyte.life_game.draw;

public class Grid {
    public static Cell[][] of(int rowLength, int columnLength, int cellWidth) {
        Cell[][] grid = new Cell[rowLength][columnLength];

        int coordinateX = 0;
        int coordinateY = 0;

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                Cell cellN = new Cell();
                cellN.setCoordinates(new int[]{coordinateX, coordinateY});
                cellN.setIndices(new int[]{i, j});
                cellN.setWidth(cellWidth);
                grid[i][j] = cellN;
                coordinateX += cellWidth;
            }
            coordinateX = 0;
            coordinateY += cellWidth;
        }
        return grid;
    }
}
