package ua.antibyte.life_game.listener;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import ua.antibyte.life_game.GameActivity;
import ua.antibyte.life_game.R;
import ua.antibyte.life_game.service.DrawingService;

public class BtnStopListener implements View.OnClickListener {
    private final Context context;
    private final DrawingService drawingService;
    private ImageButton btnStart;
    private ImageButton btnStop;

    public BtnStopListener(Context context, DrawingService drawingService) {
        this.context = context;
        this.drawingService = drawingService;
        init();
    }

    @Override
    public void onClick(View view) {
        hidePlayButtonAndShowPauseButton();
        drawingService.stop();
    }

    private void init() {
        btnStop = ((GameActivity) context).findViewById(R.id.btn_stop);
        btnStart = ((GameActivity) context).findViewById(R.id.btn_start);
    }

    private void hidePlayButtonAndShowPauseButton() {
        btnStop.setVisibility(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
    }
}
