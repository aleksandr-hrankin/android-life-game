package ua.antibyte.life_game.listener;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;

import ua.antibyte.life_game.GameActivity;
import ua.antibyte.life_game.R;
import ua.antibyte.life_game.service.DrawingService;

public class SeekBarScreenSizeListener extends AbstractListener implements SeekBar.OnSeekBarChangeListener {
    private final DrawingService drawingService;
    private TextView twScreenSize;

    public SeekBarScreenSizeListener(Context context, DrawingService drawingService) {
        super(context);
        this.drawingService = drawingService;
        twScreenSize = ((GameActivity) context).findViewById(R.id.tw_size);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        drawingService.stop();
        super.hideStopButtonAndShowStartButton();
        drawingService.changeScreenSize(i);
        twScreenSize.setText("SIZE: x" + i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
