package ua.antibyte.life_game.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import ua.antibyte.life_game.service.DrawService;
import ua.antibyte.life_game.util.WindowUtil;


public class Draw extends View {
    private final Context context;
    private final Paint paint;
    private final DrawService drawService;

    Set<CellN> set = new LinkedHashSet<>();

    private CellN[][] grid;

    public Draw(Context context, Paint paint, DrawService drawService) {
        super(context);
        this.context = context;
        this.paint = paint;
        this.drawService = drawService;

        int cellWidth = (int) (100 * context.getResources().getDisplayMetrics().density);
        int rowLength = WindowUtil.getWindowSize(context).y / cellWidth;
        int columnLength = WindowUtil.getWindowSize(context).x / cellWidth;
        grid = GridN.of(rowLength, columnLength, cellWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawService.drawGrid(canvas, grid);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int cellWidth = (int) (100 * context.getResources().getDisplayMetrics().density);

        int y = (int) event.getY() / cellWidth;
        int x = (int) event.getX() / cellWidth;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Первое касание. Сохраняем начальную позицию
//                invalidate();
                if (set.size() == 1) {
                    set.remove(set.iterator().next());
                }

            case MotionEvent.ACTION_MOVE:
                if (!set.contains(grid[y][x])) {
                    grid[y][x].setActive(!grid[y][x].isActive());
                    set.add(grid[y][x]);
                    invalidate();
                }
                if (set.size() == 2) {
                    set.remove(set.iterator().next());
                }
//                invalidate();
            case MotionEvent.ACTION_UP:
                // Завершаем рисование и рисуем всё в канвас.
//                invalidate();
        }
        return true;
    }
}
