package ua.antibyte.life_game.listener;

import android.view.View;

import ua.antibyte.life_game.service.DrawingService;

public class BtnNextGenerationListener implements View.OnClickListener {
    private final DrawingService drawingService;

    public BtnNextGenerationListener(DrawingService drawingService) {
        this.drawingService = drawingService;
    }

    @Override
    public void onClick(View view) {
        drawingService.nextGeneration();
    }
}
