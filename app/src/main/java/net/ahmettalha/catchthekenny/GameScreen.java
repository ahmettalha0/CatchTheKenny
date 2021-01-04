package net.ahmettalha.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameScreen extends AppCompatActivity {
    TextView textViewScore;
    TextView textViewTime;
    int score;
    int time; //type = millis
    //Kennys
    ImageView imageViewKenny1, imageViewKenny2, imageViewKenny3, imageViewKenny4, imageViewKenny5, imageViewKenny6,
          imageViewKenny7, imageViewKenny8, imageViewKenny9;
    ImageView[] imageViews;
    Handler handler;
    Runnable runnable;
    int delay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        textViewTime = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);

        score = 0;
        delay = 600;
        time = 20000;
        textViewScore.setText("Score: " + score);
        textViewTime.setText("Time: " + time / 1000);

        imageViewKenny1 = findViewById(R.id.imageViewKenny1);
        imageViewKenny2 = findViewById(R.id.imageViewKenny2);
        imageViewKenny3 = findViewById(R.id.imageViewKenny3);
        imageViewKenny4 = findViewById(R.id.imageViewKenny4);
        imageViewKenny5 = findViewById(R.id.imageViewKenny5);
        imageViewKenny6 = findViewById(R.id.imageViewKenny6);
        imageViewKenny7 = findViewById(R.id.imageViewKenny7);
        imageViewKenny8 = findViewById(R.id.imageViewKenny8);
        imageViewKenny9 = findViewById(R.id.imageViewKenny9);

        imageViews = new ImageView[]{imageViewKenny1, imageViewKenny2, imageViewKenny3, imageViewKenny4,
                imageViewKenny5, imageViewKenny6,imageViewKenny7,imageViewKenny8,imageViewKenny9};

        new CountDownTimer(time, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewTime.setText("Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textViewTime.setText("Time off!");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(GameScreen.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(GameScreen.this, "Game Over!", Toast.LENGTH_LONG).show();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setCancelable(false);
                alert.show();
            }
        }.start();

        hideImages();
    }

    public void kennyClick(View view){
        int oldScore = score;
        score++;
        textViewScore.setText("Score: " + score);

        if (score != oldScore && delay > 300){
            delay = delay -50;
        }
    }

    public void exitGame(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void hideImages(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageViews[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, delay);

            }
        };

        handler.post(runnable);
    }
}