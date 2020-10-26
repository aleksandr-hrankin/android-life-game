package ua.antibyte.life_game.listener;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import ua.antibyte.life_game.GameActivity;
import ua.antibyte.life_game.R;

public class BtnSettingsListener implements View.OnClickListener {
    private final Context context;
    private LinearLayout layoutSettingsButtons;
    private FrameLayout layoutSeekBarScreenSize;
    private SeekBar seekBarSpeed;
    private boolean isVisibly = false;

    public BtnSettingsListener(Context context) {
        this.context = context;
        init();
    }

    @Override
    public void onClick(View view) {
        isVisibly = !isVisibly;
        if (isVisibly) {
            show();
        } else {
            hide();
        }
    }
    
    private void init() {
        layoutSettingsButtons = ((GameActivity) context).findViewById(R.id.layout_settings_buttons);
        layoutSeekBarScreenSize = ((GameActivity) context).findViewById(R.id.layout_seek_bar_screen_size);
        seekBarSpeed = ((GameActivity) context).findViewById(R.id.seek_bar_speed);
    }

    private void show() {
        layoutSettingsButtons.setVisibility(View.VISIBLE);
        layoutSeekBarScreenSize.setVisibility(View.VISIBLE);
        seekBarSpeed.setVisibility(View.VISIBLE);
    }

    private void hide() {
        layoutSettingsButtons.setVisibility(View.GONE);
        layoutSeekBarScreenSize.setVisibility(View.INVISIBLE);
        seekBarSpeed.setVisibility(View.INVISIBLE);
    }
}
