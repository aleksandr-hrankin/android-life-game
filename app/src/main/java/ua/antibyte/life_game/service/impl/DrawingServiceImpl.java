package ua.antibyte.life_game.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ua.antibyte.life_game.draw.CellN;
import ua.antibyte.life_game.draw.Draw;
import ua.antibyte.life_game.draw.GridN;
import ua.antibyte.life_game.service.DrawingService;
import ua.antibyte.life_game.util.WindowUtil;

public class DrawingServiceImpl implements DrawingService {
    private final Draw draw;
    private boolean life = false;
    private Thread liveThread;
    private int speed = 100;

    public DrawingServiceImpl(Draw draw) {
        this.draw = draw;
    }

    @Override
    public void fillGridRandom() {
        Random random = new Random();
        for (int i = 1; i < draw.getGrid().length - 1; i++) {
            for (int j = 1; j < draw.getGrid()[i].length - 1; j++) {
                draw.getGrid()[i][j].setActive(random.nextBoolean());
            }
        }
        draw.invalidate();
    }

    @Override
    public void clearGrid() {
        draw.getCellContainer().clear();
        for (int i = 1; i < draw.getGrid().length - 1; i++) {
            for (int j = 1; j < draw.getGrid()[i].length - 1; j++) {
                draw.getGrid()[i][j].setActive(false);
            }
        }
        draw.invalidate();
    }

    @Override
    public void nextGeneration() {
        List<CellN> nextGen = calculateNextGeneration();
        for (CellN cell : nextGen) {
            int i = cell.getIndices()[0];
            int j = cell.getIndices()[1];
            draw.getGrid()[i][j] = cell;
        }
        draw.invalidate();
    }

    @Override
    public void start() {
        life = true;
        liveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (life) {
                    nextGeneration();
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        liveThread.start();
    }

    @Override
    public void stop() {
        life = false;
        if (liveThread != null) {
            liveThread.interrupt();
        }
    }

    @Override
    public void changeScreenSize(int size) {
        int cellWidth = (int) (size * draw.getContext().getResources().getDisplayMetrics().density);
        int rowLength = WindowUtil.getWindowSize(draw.getContext()).y / cellWidth;
        int columnLength = WindowUtil.getWindowSize(draw.getContext()).x / cellWidth;
        draw.setGrid(GridN.of(rowLength, columnLength, cellWidth));
        draw.invalidate();
    }

    @Override
    public void changeSpeed(int value) {
        speed = value;
    }

    private List<CellN> calculateNextGeneration() {
        List<CellN> generation = new ArrayList<>();
        for (int i = 1; i < draw.getGrid().length - 1; i++) {
            for (int j = 1; j < draw.getGrid()[i].length - 1; j++) {
                CellN currentCell = draw.getGrid()[i][j];
                generation.add(updateCell(currentCell));
            }
        }
        return generation;
    }
    private CellN updateCell(CellN cell) {
        int[] statusNeighbors = findOutTheStatusOfNeighbors(cell);
        if (!cell.isActive() && statusNeighbors[0] == 3) {
            return new CellN(cell.getCoordinates(), cell.getIndices(), cell.getWidth(), true);
        }
        if (cell.isActive() && (statusNeighbors[0] < 2 || statusNeighbors[0] > 3)) {
            return new CellN(cell.getCoordinates(), cell.getIndices(), cell.getWidth(), false);
        }
        return cell;
    }

    private int[] findOutTheStatusOfNeighbors(CellN cell) {
        int[] status = new int[2];
        int[] indices = cell.getIndices();
        for (int i = indices[0] - 1; i <= indices[0] + 1; i++) {
            for (int j = indices[1] - 1; j <= indices[1] + 1; j++) {
                if (i == indices[0] && j == indices[1]) {
                    continue;
                }
                if (draw.getGrid()[i][j].isActive()) {
                    status[0]++;
                } else {
                    status[1]++;
                }
            }
        }
        return status;
    }
}
