package ua.antibyte.life_game;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import ua.antibyte.life_game.draw.CellN;
import ua.antibyte.life_game.draw.Draw;
import ua.antibyte.life_game.draw.GridN;
import ua.antibyte.life_game.listener.BtnClearFiledListener;
import ua.antibyte.life_game.listener.BtnFillFieldRandomListener;
import ua.antibyte.life_game.listener.BtnNextGenerationListener;
import ua.antibyte.life_game.listener.BtnStartListener;
import ua.antibyte.life_game.listener.BtnSettingsListener;
import ua.antibyte.life_game.listener.BtnStopListener;
import ua.antibyte.life_game.listener.SeekBarScreenSizeListener;
import ua.antibyte.life_game.listener.SeekBarSpeedListener;
import ua.antibyte.life_game.service.DrawingService;
import ua.antibyte.life_game.service.impl.DrawingServiceImpl;
import ua.antibyte.life_game.util.WindowUtil;

public class GameActivity extends AppCompatActivity {
    private RelativeLayout layoutPlayingField;

    private ImageButton btnClearField;
    private ImageButton btnFillFieldRandom;
    private ImageButton btnSettings;
    private ImageButton btnGenerationConditions;
    private ImageButton btnSave;
    private ImageButton btnPrevGeneration;
    private ImageButton btnNextGeneration;
    private ImageButton btnStart;
    private ImageButton btnStop;

    private SeekBar seekBarScreenSize;
    private SeekBar seekBarSpeed;

    private Draw draw;
    private CellN[][] grid;
    private DrawingService drawingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();

        int cellWidth = (int) (seekBarScreenSize.getProgress() * getResources().getDisplayMetrics().density);
        int rowLength = WindowUtil.getWindowSize(this).y / cellWidth;
        int columnLength = WindowUtil.getWindowSize(this).x / cellWidth;
        grid =  GridN.of(rowLength, columnLength, cellWidth);

        draw = new Draw(this, createPaint(), grid);
        layoutPlayingField = findViewById(R.id.layout_playing_field);
        layoutPlayingField.addView(draw);

        drawingService = new DrawingServiceImpl(draw);

        setClickListener();
    }


    private void init() {
        btnClearField = findViewById(R.id.btn_clear_field);
        btnFillFieldRandom = findViewById(R.id.btn_fill_field_random);
        btnSettings = findViewById(R.id.btn_settings);
        btnGenerationConditions = findViewById(R.id.btn_generation_conditions);
        btnSave = findViewById(R.id.btn_save);
        btnPrevGeneration = findViewById(R.id.btn_prev_generation);
        btnNextGeneration = findViewById(R.id.btn_next_generation);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);

        seekBarScreenSize = findViewById(R.id.seek_bar_screen_size);
        seekBarSpeed = findViewById(R.id.seek_bar_speed);
    }

    private void setClickListener() {
        btnClearField.setOnClickListener(new BtnClearFiledListener(this, drawingService));
        btnFillFieldRandom.setOnClickListener(new BtnFillFieldRandomListener(this, drawingService));
        btnSettings.setOnClickListener(new BtnSettingsListener(this));
        btnNextGeneration.setOnClickListener(new BtnNextGenerationListener(drawingService));
        btnStart.setOnClickListener(new BtnStartListener(this, drawingService));
        btnStop.setOnClickListener(new BtnStopListener(this, drawingService));

        seekBarScreenSize.setOnSeekBarChangeListener(new SeekBarScreenSizeListener(this, drawingService));
        seekBarSpeed.setOnSeekBarChangeListener(new SeekBarSpeedListener(this, drawingService));
    }

    private Paint createPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
}