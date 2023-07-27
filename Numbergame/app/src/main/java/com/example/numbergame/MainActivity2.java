package com.example.numbergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private TextView instructionsTextView;
    private EditText guessEditText;
    private Button guessButton;
    private TextView resultTextView;
    private int targetNumber;
    private int attemptsLeft;
    private final int attemptsLimit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        instructionsTextView = findViewById(R.id.instructionsTextView);
        guessEditText = findViewById(R.id.guessEditText);
        guessButton = findViewById(R.id.guessButton);
        resultTextView = findViewById(R.id.resultTextView);

        generateRandomNumber();
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGuess();
            }
        });
    }

    private void generateRandomNumber() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attemptsLeft = attemptsLimit;
    }

    private void handleGuess() {
        String guessString = guessEditText.getText().toString().trim();

        if (!guessString.isEmpty()) {
            int userGuess = Integer.parseInt(guessString);
            String feedback = compareGuess(userGuess);
            resultTextView.setText(feedback);

            if (feedback.equals("Correct")) {
                Toast.makeText(this, "Congratulations! You guessed the number!", Toast.LENGTH_SHORT).show();
                generateRandomNumber();
                resultTextView.setText("");
            } else if (attemptsLeft == 0) {
                Toast.makeText(this, "Out of attempts. The number was " + targetNumber + ".", Toast.LENGTH_LONG).show();
                generateRandomNumber();
                resultTextView.setText("");
            } else {
                attemptsLeft--;
            }
        } else {
            Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
        }
    }

    private String compareGuess(int guess) {
        if (guess == targetNumber) {
            return "Correct";
        } else if (guess < targetNumber) {
            return "Too low. Attempts left: " + attemptsLeft;
        } else {
            return "Too high. Attempts left: " + attemptsLeft;
        }
    }
}