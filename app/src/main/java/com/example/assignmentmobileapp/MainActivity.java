package com.example.assignmentmobileapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Pet myPet;
    private TextView hungerText, happinessText, cleanlinessText;
    private Button feedButton, cleanButton;
    private Handler handler;
    private Runnable gameloop;
    private ImageView petImage;
    private ImageView gameOverImage;
    private Button restartButton;
    private LinearLayout buttonsLayout;
    private ImageView dirtOverlay;
    private TextView statusPrompt;
    private boolean isGameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.main) != null){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });

            myPet = new Pet();
            hungerText = findViewById(R.id.hungerTextView);
            happinessText = findViewById(R.id.happinessTextView);
            cleanlinessText = findViewById(R.id.cleanlinessTextView);
            feedButton = findViewById(R.id.feedButton);

            cleanButton = findViewById(R.id.cleanButton);
            petImage = findViewById(R.id.petImageView);
            gameOverImage = findViewById(R.id.gameOverImage);
            restartButton = findViewById(R.id.restartButton);
            buttonsLayout = findViewById(R.id.buttonsLayout);
            dirtOverlay= findViewById(R.id.dirtOverlay);
            statusPrompt = findViewById(R.id.statusPromptText);

            restartButton.setOnClickListener(mainView ->{
                myPet = new Pet();
                isGameOver = false;

                gameOverImage.setVisibility(View.GONE);
                restartButton.setVisibility(View.GONE);
                buttonsLayout.setVisibility(View.VISIBLE);
            });

            gameOverImage.setVisibility(View.GONE);
            restartButton.setVisibility(View.GONE);
            buttonsLayout.setVisibility(View.VISIBLE);

            feedButton.setOnClickListener(mainView -> {
                myPet.eat();
                updateUI();
            });
            cleanButton.setOnClickListener(mainView -> {
                        myPet.clean();
                        updateUI();
            });

            handler = new Handler(Looper.getMainLooper());
            gameloop = new Runnable() {
                @Override
                public void run() {
                   if(!isGameOver){
                       myPet.passTime();
                       updateUI();
                   }
                   handler.postDelayed(this, 5000);
                }
            };
            handler.post(gameloop);
        }
    }
    private void updateUI(){
        hungerText.setText("Hunger: " + myPet.getHunger());
        happinessText.setText("Happiness: " + myPet.getHappiness());
        cleanlinessText.setText("Cleanliness: " + myPet.getCleanliness());

        if(myPet.getCleanliness() < 30){
            dirtOverlay.setAlpha(0.9f);
        }else if(myPet.getCleanliness() < 50){
            dirtOverlay.setAlpha(0.7f);
        }else if(myPet.getCleanliness() < 70){
            dirtOverlay.setAlpha(0.5f);
        } else if (myPet.getCleanliness() < 90) {
            dirtOverlay.setAlpha(0.2f);
        }else if (myPet.getCleanliness() > 90){
            dirtOverlay.setAlpha(0.0f);
        }

        if(myPet.getHunger() <= 0 || myPet.getHappiness() <=0) {
            isGameOver = true;
            petImage.setImageResource(R.drawable.lyon_angry);
            statusPrompt.setText("Noyl Is Dead. You Killed Him");
            statusPrompt.setTextColor(Color.BLACK);


            buttonsLayout.setVisibility(View.GONE);
            gameOverImage.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.VISIBLE);
        }
        else if(myPet.getHunger() < 25 || myPet.getHappiness() < 40 || myPet.getCleanliness() < 50){
            petImage.setImageResource(R.drawable.lyon_angry);
            statusPrompt.setText("Lyon just turned to Noyl. Please Take Care of him");
            statusPrompt.setTextColor(Color.RED);

        }else{
            petImage.setImageResource(R.drawable.lyon_happy);
            statusPrompt.setText("Lyon Is Happy. Good Job");
            statusPrompt.setTextColor(Color.BLUE);
        }
        if(isGameOver){
            dirtOverlay.setAlpha(0.0f);
        }
    }
}