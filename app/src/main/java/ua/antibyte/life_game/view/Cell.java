package ua.antibyte.life_game.view;

import android.graphics.Color;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Objects;

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean life) {
        isLife = life;
    }

    public int[] getIndex() {
        return index;
    }

    public void setIndex(int[] index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y &&
                color == cell.color &&
                isLife == cell.isLife &&
                Arrays.equals(index, cell.index);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y, color, isLife);
        result = 31 * result + Arrays.hashCode(index);
        return result;
    }

    protected Cell clone() {
        Cell cell = new Cell(x, y, index[0], index[1]);
        cell.setLife(isLife);
        cell.setColor(color);
        return cell;
    }
}
