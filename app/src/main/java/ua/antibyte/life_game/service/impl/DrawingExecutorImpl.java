package ua.antibyte.life_game.service.impl;

import android.graphics.Canvas;
import android.graphics.Paint;
import ua.antibyte.life_game.draw.Cell;
import ua.antibyte.life_game.service.DrawingExecutor;

public class DrawingExecutorImpl implements DrawingExecutor {
    private final Paint paint;

    public DrawingExecutorImpl(Paint paint) {
        this.paint = paint;
    }

    @Override
    public void drawGrid(Canvas canvas, Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Cell cell = grid[i][j];
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
}
