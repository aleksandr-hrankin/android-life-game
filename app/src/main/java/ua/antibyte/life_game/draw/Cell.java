package ua.antibyte.life_game.draw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell implements Cloneable {
    private int[] coordinates;
    private int[] indices;
    private int width;
    private boolean isActive = false;
}
