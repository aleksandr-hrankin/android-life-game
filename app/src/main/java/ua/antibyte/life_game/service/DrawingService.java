package ua.antibyte.life_game.service;

public interface DrawingService {
    void fillGridRandom();

    void clearGrid();

    void nextGeneration();

    void start();

    void stop();

    void changeScreenSize(int size);

    void changeSpeed(int value);
}
