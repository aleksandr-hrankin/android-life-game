package ua.antibyte.life_game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import ua.antibyte.life_game.GameActivity;
import ua.antibyte.life_game.R;
import ua.antibyte.life_game.util.WindowUtil;

public class DrawView extends View {
    private static final int PAINT_WIDTH = 4;
    private static final int PAINT_COLOR = Color.BLACK;
    private int cell_side = 22;
    private float dp;

    private Paint paint;
    private int cellSideDp;
    private int width_playing_filed;
    private int height_playing_filed;

    private Cell[][] cells;
    private Thread liveThread;
    private boolean life = false;
    private int gen = 0;
    private int speed = 100;
    private TextView twGen;
    private TextView twSpeed;
    private TextView twSize;

    private Cell tempCell;

    public DrawView(Context context) {
        super(context);

        twGen = (TextView) ((GameActivity) context).findViewById(R.id.tw_gen);
        twSpeed = (TextView) ((GameActivity) context).findViewById(R.id.tw_speed);
        twSize = (TextView) ((GameActivity) context).findViewById(R.id.tw_size);

        dp = context.getResources().getDisplayMetrics().density;

        cellSideDp = (int) (cell_side * dp);

        width_playing_filed = WindowUtil.getWindowSize(context).x;
//        height_playing_filed = WindowUtil.getWindowSize(context).y;
        height_playing_filed = ((GameActivity) context).findViewById(R.id.layout_playing_field).getLayoutParams().height;
        ;

        reloadCells();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(PAINT_COLOR);
        paint.setStrokeWidth(PAINT_WIDTH);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        twGen.setText("GEN:" + gen);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Получаем точку касания
        int x = (int) event.getX();
        int y = (int) event.getY();

        x = x / cellSideDp;
        y = y / cellSideDp;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Первое касание. Сохраняем начальную позицию
//                invalidate();

            case MotionEvent.ACTION_MOVE:
                // Рисуем
                cells[y][x].setLife(!cells[y][x].isLife());

                invalidate();

            case MotionEvent.ACTION_UP:
                // Завершаем рисование и рисуем всё в канвас.
//                invalidate();
        }
        return true;
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int x = cells[i][j].getX();
                int y = cells[i][j].getY();

                if (cells[i][j].isLife()) {
                    paint.setStyle(Paint.Style.FILL);
                } else {
                    paint.setStyle(Paint.Style.STROKE);
                }

                canvas.drawRect(x, y, x + cellSideDp, y + cellSideDp, paint);
            }
        }
    }

    private void reloadCells() {
        int width = width_playing_filed / cellSideDp;
        int height = height_playing_filed / cellSideDp;

        Grid grid = new Grid(width, height, cellSideDp);
        cells = grid.fillCells();
    }

    private void live() {
        while (life) {
            nextGen();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void nextGen() {
        gen++;
        if (gen == Integer.MAX_VALUE) {
            gen = 0;
        }
        List<Cell> newCells = new ArrayList<>();
        for (int i = 1; i < cells.length - 1; i++) {
            for (int j = 1; j < cells[i].length - 1; j++) {
                Cell currentCell = cells[i][j];
                int live = getCountLivingNeighbors(currentCell);
                int indexI = currentCell.getIndex()[0];
                int indexJ = currentCell.getIndex()[1];
                if (!currentCell.isLife() && live == 3) {
                    newCells.add(new Cell(currentCell.getX(), currentCell.getY(), Color.BLACK, true, new int[]{indexI, indexJ}));
                    continue;
                }
                if (currentCell.isLife() && (live < 2 || live > 3)) {
                    newCells.add(new Cell(currentCell.getX(), currentCell.getY(), Color.BLACK, false, new int[]{indexI, indexJ}));
                }
            }
        }
        for (Cell cell : newCells) {
            int i = cell.getIndex()[0];
            int j = cell.getIndex()[1];
            cells[i][j] = cell;
        }
        invalidate();
    }

    private int getCountLivingNeighbors(Cell cell) {
        int countLiveCell = 0;
        int[] cellIndex = cell.getIndex();
        for (int i = cellIndex[0] - 1; i <= cellIndex[0] + 1; i++) {
            for (int j = cellIndex[1] - 1; j <= cellIndex[1] + 1; j++) {
                if (cells[i][j].isLife()) {
                    countLiveCell++;
                    if (i == cellIndex[0] && j == cellIndex[1]) {
                        countLiveCell--;
                    }
                }
            }
        }
        return countLiveCell;
    }

    public void clearCells() {
        pause();
        gen = 0;
        reloadCells();
        invalidate();
    }

    public void play() {
        life = true;
        liveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                live();
            }
        });
        liveThread.start();
    }

    public void pause() {
        life = false;
        if (liveThread != null) {
            liveThread.interrupt();
        }
    }

    public void next() {
        pause();
        nextGen();
    }

    public void reduceSpeed() {
        if (speed < 100) {
            speed += 10;
        } else {
            speed += 100;
            if (speed >= 3000) {
                speed = 3000;
            }
        }
        twSpeed.setText("SPEED: " + speed + "ms");
    }

    public void increaseSpeed() {
        if (speed <= 100) {
            speed -= 10;
            if (speed == 0) {
                speed = 10;
            }
        } else {
            speed -= 100;
        }
        twSpeed.setText("SPEED: " + speed + "ms");
    }

    public void reduceCell() {
        cell_side -= 1;
        if (cell_side == 4) {
            cell_side = 5;
        }
        cellSideDp = (int) (cell_side * dp);
        clearCells();
        twSize.setText("SIZE: " + cell_side + "px");
    }

    public void increaseCell() {
        cell_side += 1;
        if (cell_side >= 100) {
            cell_side = 100;
        }
        cellSideDp = (int) (cell_side * dp);
        clearCells();
        twSize.setText("SIZE: " + cell_side + "px");
    }

    public void reload() {
        cell_side = 22;
        cellSideDp = (int) (cell_side * dp);
        speed = 100;
        gen = 0;
        twSpeed.setText("SPEED: " + speed + "ms");
        twSize.setText("SIZE: " + cell_side + "px");
        twGen.setText("GEN:" + gen);
        clearCells();
    }
}
