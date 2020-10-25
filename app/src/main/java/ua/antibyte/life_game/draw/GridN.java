package ua.antibyte.life_game.draw;

public class GridN {
    public static CellN[][] of(int rowLength, int columnLength, int cellWidth) {
        CellN[][] grid = new CellN[rowLength][columnLength];

        int coordinateX = 0;
        int coordinateY = 0;

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                CellN cellN = new CellN();
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
