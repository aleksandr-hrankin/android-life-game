package ua.antibyte.life_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewGame = findViewById(R.id.btn_new_game);
        onClick();
    }

    public void onClick() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_new_game:
                        openNewGame();
                        break;
                }
            }
        };
        btnNewGame.setOnClickListener(onClickListener);
    }

    private void openNewGame() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}