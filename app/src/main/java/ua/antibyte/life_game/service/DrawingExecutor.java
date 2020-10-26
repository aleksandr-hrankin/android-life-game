package ua.antibyte.life_game.service;

import android.graphics.Canvas;

import ua.antibyte.life_game.draw.Cell;

public interface DrawingExecutor {
    void drawGrid(Canvas canvas, Cell[][] grid);
}
