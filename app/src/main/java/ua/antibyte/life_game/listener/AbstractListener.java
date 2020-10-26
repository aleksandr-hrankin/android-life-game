package ua.antibyte.life_game.listener;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import ua.antibyte.life_game.GameActivity;
import ua.antibyte.life_game.R;

public class AbstractListener {
    private final Context context;
    private ImageButton btnStart;
    private ImageButton btnStop;

    public AbstractListener(Context context) {
        this.context = context;
    }

    protected void hideStopButtonAndShowStartButton() {
        initStartAndStopButtons();
        btnStop.setVisibility(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
    }

    private void initStartAndStopButtons() {
        btnStop = ((GameActivity) context).findViewById(R.id.btn_stop);
        btnStart = ((GameActivity) context).findViewById(R.id.btn_start);
    }
}
