package ua.antibyte.life_game.draw;

import lombok.Data;

@Data
public class CellN {
    private int[] coordinates;
    private int[] indices;
    private int width;
    private boolean isActive = false;
}
