package net.ahmettalha.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    TextView textViewScore;
    TextView textViewTime;
    int score;
    int time; //type = millis
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        textViewTime = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);

        score = 0;
        time = 15000;
        textViewScore.setText("Score: " + score);
        textViewTime.setText("Time: " + time / 1000);
    }

    public void kennyClick(View view){
        score++;
        textViewScore.setText("Score: " + score);
    }
}