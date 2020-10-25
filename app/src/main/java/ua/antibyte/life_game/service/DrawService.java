package ua.antibyte.life_game.service;

import android.graphics.Canvas;

import ua.antibyte.life_game.draw.CellN;

public interface DrawService {
    void drawGrid(Canvas canvas, CellN[][] grid);

    void fillGridRandom();

    void clearGrid();
}
