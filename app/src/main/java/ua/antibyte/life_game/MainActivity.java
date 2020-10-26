package ua.antibyte.life_game;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import ua.antibyte.life_game.listener.BtnNewGameListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNewGame = findViewById(R.id.btn_new_game);
        btnNewGame.setOnClickListener(new BtnNewGameListener(this));
    }
}