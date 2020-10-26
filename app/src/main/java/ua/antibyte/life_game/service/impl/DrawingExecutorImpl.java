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
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                int x = cell.getCoordinates()[0];
                int y = cell.getCoordinates()[1];

                if (cell.isActive()) {
                    paint.setStyle(Paint.Style.FILL);
                } else {
                    paint.setStyle(Paint.Style.STROKE);
                }
                canvas.drawRect(x, y, x + cell.getWidth(), y + cell.getWidth(), paint);
            }
        }
    }
}
