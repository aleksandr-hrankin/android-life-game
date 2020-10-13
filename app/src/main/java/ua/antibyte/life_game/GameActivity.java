package ua.antibyte.life_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import ua.antibyte.life_game.view.DrawView;

public class GameActivity extends AppCompatActivity {
    private RelativeLayout layoutPlayingField;
    private ImageButton btnClear;
    private ImageButton btnReload;
    private ImageButton btnSlower;
    private ImageButton btnFaster;
    private ImageButton btnReduce;
    private ImageButton btnIncrease;
    private ImageButton btnPrev;
    private ImageButton btnPause;
    private ImageButton btnPlay;
    private ImageButton btnNext;

    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnClear = findViewById(R.id.btn_clear);
        btnReload = findViewById(R.id.btn_reload);
        btnSlower = findViewById(R.id.btn_slower);
        btnFaster = findViewById(R.id.btn_faster);
        btnReduce = findViewById(R.id.btn_reduce);
        btnIncrease = findViewById(R.id.btn_increase);
        btnPrev = findViewById(R.id.btn_prev);
        btnPause = findViewById(R.id.btn_pause);
        btnPlay = findViewById(R.id.btn_play);
        btnNext = findViewById(R.id.btn_next);


        drawView = new DrawView(this);
        layoutPlayingField = findViewById(R.id.layout_playing_field);
        layoutPlayingField.addView(drawView);


        onClick();
    }

    public void onClick() {
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_clear:
                        drawView.clearCells();
                        btnPause.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.VISIBLE);
                        break;
                    case R.id.btn_reload:
                        btnPause.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.VISIBLE);
                        drawView.reload();
                        break;
                    case R.id.btn_slower:
                        drawView.reduceSpeed();
                        break;
                    case R.id.btn_faster:
                        drawView.increaseSpeed();
                        break;
                    case R.id.btn_reduce:
                        btnPause.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.VISIBLE);
                        drawView.reduceCell();
                        break;
                    case R.id.btn_increase:
                        btnPause.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.VISIBLE);
                        drawView.increaseCell();
                        break;
                    case R.id.btn_prev:
                        break;
                    case R.id.btn_pause:
                        btnPause.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.VISIBLE);
                        drawView.pause();
                        break;
                    case R.id.btn_play:
                        btnPlay.setVisibility(View.GONE);
                        btnPause.setVisibility(View.VISIBLE);
                        drawView.play();
                        break;
                    case R.id.btn_next:
                        btnPause.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.VISIBLE);
                        drawView.next();
                        break;
                    default:
                        throw new RuntimeException("Unknown button id");
                }
            }
        };
        btnClear.setOnClickListener(onClick);
        btnReload.setOnClickListener(onClick);
        btnSlower.setOnClickListener(onClick);
        btnFaster.setOnClickListener(onClick);
        btnReduce.setOnClickListener(onClick);
        btnIncrease.setOnClickListener(onClick);
        btnPrev.setOnClickListener(onClick);
        btnPause.setOnClickListener(onClick);
        btnPlay.setOnClickListener(onClick);
        btnNext.setOnClickListener(onClick);
    }
}