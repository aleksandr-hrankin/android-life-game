package ua.antibyte.life_game.view;

import android.graphics.Color;

import lombok.Data;

@Data
public class Cell implements Cloneable {
    private int x;
    private int y;
    private int color = Color.RED;
    private boolean isLife = false;
    private int[] index = new int[2];

    public Cell(int x, int y, int i, int j) {
        this.x = x;
        this.y = y;
        index[0] = i;
        index[1] = j;
    }

    public Cell(int x, int y, int color, boolean isLife, int[] index) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.isLife = isLife;
        this.index = index;
    }

    protected Cell clone() {
        Cell cell = new Cell(x, y, index[0], index[1]);
        cell.setLife(isLife);
        cell.setColor(color);
        return cell;
    }
}
