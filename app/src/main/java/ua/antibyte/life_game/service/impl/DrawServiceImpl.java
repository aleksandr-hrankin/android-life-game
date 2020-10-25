package ua.antibyte.life_game.service.impl;

import android.graphics.Canvas;
import android.graphics.Paint;

import ua.antibyte.life_game.draw.CellN;
import ua.antibyte.life_game.service.DrawService;

public class DrawServiceImpl implements DrawService {
    private final Paint paint;

    public DrawServiceImpl(Paint paint) {
        this.paint = paint;
    }

    @Override
    public void drawGrid(Canvas canvas, CellN[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                CellN cell = grid[i][j];
                int x = cell.getCoordinates()[0];
                int y = cell.getCoordinates()[1];

                if (grid[i][j].isActive()) {
                    paint.setStyle(Paint.Style.FILL);
                } else {
                    paint.setStyle(Paint.Style.STROKE);
                }

                canvas.drawRect(x, y, x + cell.getWidth(), y + cell.getWidth(), paint);
            }
        }
    }

    @Override
    public void fillGridRandom() {

    }

    @Override
    public void clearGrid() {

    }
}
