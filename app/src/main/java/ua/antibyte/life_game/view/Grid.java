package ua.antibyte.life_game.view;

public class Grid {
    private int width;
    private int height;
    private int cellSide;
    private Cell[][] cells;

    public Grid(int width, int height, int cellSide) {
        this.width = width;
        this.height = height;
        this.cellSide = cellSide;
        cells = new Cell[height][width];
    }

    public Cell[][] fillCells() {
        int x = 0;
        int y = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(x, y, i, j);
                x += cellSide;
            }
            x = 0;
            y += cellSide;
        }
        return cells;
    }
}
