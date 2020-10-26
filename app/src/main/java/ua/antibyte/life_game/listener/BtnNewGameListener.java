package ua.antibyte.life_game.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import ua.antibyte.life_game.GameActivity;

public class BtnNewGameListener implements View.OnClickListener {
    private final Context context;

    public BtnNewGameListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, GameActivity.class);
        context.startActivity(intent);
    }
}
