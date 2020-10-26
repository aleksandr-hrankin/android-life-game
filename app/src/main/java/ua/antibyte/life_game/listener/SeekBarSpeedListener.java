package ua.antibyte.life_game.listener;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;
import ua.antibyte.life_game.GameActivity;
import ua.antibyte.life_game.R;
import ua.antibyte.life_game.service.DrawingService;

public class SeekBarSpeedListener implements SeekBar.OnSeekBarChangeListener {
    private final DrawingService drawingService;
    private TextView twSpeed;

    public SeekBarSpeedListener(Context context, DrawingService drawingService) {
        this.drawingService = drawingService;
        twSpeed = ((GameActivity) context).findViewById(R.id.tw_speed);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        drawingService.changeSpeed(i);
        twSpeed.setText("Speed: " + i + "ms");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
