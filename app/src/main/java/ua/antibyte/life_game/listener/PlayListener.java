package ua.antibyte.life_game.listener;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import ua.antibyte.life_game.GameActivity;
import ua.antibyte.life_game.R;

public class PlayListener implements View.OnClickListener {
    private final Context context;
    private ImageButton btnPlay;
    private ImageButton btnPause;

    public PlayListener(Context context) {
        this.context = context;
        init();
    }

    @Override
    public void onClick(View view) {
        hidePlayButtonAndShowPauseButton();
    }

    private void init() {
        btnPlay = ((GameActivity) context).findViewById(R.id.btn_play);
        btnPause = ((GameActivity) context).findViewById(R.id.btn_pause);
    }

    private void hidePlayButtonAndShowPauseButton() {
        btnPlay.setVisibility(View.GONE);
        btnPause.setVisibility(View.VISIBLE);
    }
}
