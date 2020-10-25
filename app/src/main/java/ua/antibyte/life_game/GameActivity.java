package ua.antibyte.life_game;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import ua.antibyte.life_game.draw.Draw;
import ua.antibyte.life_game.listener.SettingsListener;
import ua.antibyte.life_game.service.DrawService;
import ua.antibyte.life_game.service.impl.DrawServiceImpl;
import ua.antibyte.life_game.view.DrawView;

public class GameActivity extends AppCompatActivity {
    private RelativeLayout layoutPlayingField;

    private ImageButton btnClearField;
    private ImageButton btnFillFieldRandom;
    private ImageButton btnSettings;
    private ImageButton btnGenerationConditions;
    private ImageButton btnSave;
    private ImageButton btnPrevGeneration;
    private ImageButton btnNextGeneration;
    private ImageButton btnPlay;
    private ImageButton btnPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        DrawView drawView = new DrawView(this);
//        layoutPlayingField = findViewById(R.id.layout_playing_field);
//        layoutPlayingField.addView(drawView);

        initButtons();
        setClickListenerToButtons();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);

        DrawService drawService = new DrawServiceImpl(paint);

        Draw draw = new Draw(this, paint, drawService);
        layoutPlayingField = findViewById(R.id.layout_playing_field);
        layoutPlayingField.addView(draw);
    }

    private void initButtons() {
        btnClearField = findViewById(R.id.btn_clear_field);
        btnFillFieldRandom = findViewById(R.id.btn_fill_field_random);
        btnSettings = findViewById(R.id.btn_settings);
        btnGenerationConditions = findViewById(R.id.btn_generation_conditions);
        btnSave = findViewById(R.id.btn_save);
        btnPrevGeneration = findViewById(R.id.btn_prev_generation);
        btnNextGeneration = findViewById(R.id.btn_next_generation);
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
    }

    public void setClickListenerToButtons() {
        btnSettings.setOnClickListener(new SettingsListener(this));
    }
}