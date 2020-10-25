package ua.antibyte.life_game.listener;

import android.content.Context;
import android.view.View;
import ua.antibyte.life_game.service.DrawingService;

public class BtnClearFiledListener extends AbstractListener implements View.OnClickListener {
    private final Context context;
    private final DrawingService drawingService;

    public BtnClearFiledListener(Context context, DrawingService drawingService) {
        super(context);
        this.context = context;
        this.drawingService = drawingService;
    }

    @Override
    public void onClick(View view) {
        drawingService.stop();
        drawingService.clearGrid();
        super.hideStopButtonAndShowStartButton();
    }
}
