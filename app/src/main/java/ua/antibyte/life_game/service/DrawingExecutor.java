package ua.antibyte.life_game.service;

import android.graphics.Canvas;

import ua.antibyte.life_game.draw.CellN;

public interface DrawingExecutor {
    void drawGrid(Canvas canvas, CellN[][] grid);
}
