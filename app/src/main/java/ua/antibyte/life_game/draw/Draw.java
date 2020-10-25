package ua.antibyte.life_game.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import ua.antibyte.life_game.service.DrawingExecutor;
import ua.antibyte.life_game.service.impl.DrawingExecutorImpl;

public class Draw extends View {
    private final DrawingExecutor drawingExecutor;
    @Getter
    private final Set<CellN> cellContainer = new LinkedHashSet<>();
    @Getter
    @Setter
    private CellN[][] grid;

    public Draw(Context context, Paint paint, CellN[][] grid) {
        super(context);
        this.grid = grid;
        this.drawingExecutor = new DrawingExecutorImpl(paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawingExecutor.drawGrid(canvas, grid);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY() / grid[0][0].getWidth();
        int x = (int) event.getX() / grid[0][0].getWidth();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!cellContainer.isEmpty()) {
                    cellContainer.remove(cellContainer.iterator().next());
                }
            case MotionEvent.ACTION_MOVE:
                if (!cellContainer.contains(grid[y][x])) {
                    grid[y][x].setActive(!grid[y][x].isActive());
                    cellContainer.add(grid[y][x]);
                    invalidate();
                }
                if (cellContainer.size() == 2) {
                    cellContainer.remove(cellContainer.iterator().next());
                }
        }
        return true;
    }
}
