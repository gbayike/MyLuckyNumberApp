package com.example.myluckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyNumberActivity extends AppCompatActivity {

    TextView txt, txtVw;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        txt = findViewById(R.id.textView2);
        btn = findViewById(R.id.shareBtn);
        txtVw = findViewById(R.id.luckyNumber);

        // UserName
        Intent i = getIntent();
        String user_name = i.getStringExtra("name");

        Toast.makeText(this, "Username: "+ user_name, Toast.LENGTH_SHORT).show();

        // Random generated number
        int randomNum = generateRandomNumber();

        txtVw.setText(""+randomNum);

        // Share button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(user_name, randomNum);
            }
        });
    }


    public int generateRandomNumber(){
        Random random = new Random();
        int upper_limit = 1000;

        int randomNum = random.nextInt(upper_limit);

        return randomNum;
    }

    public void shareData(String username, int randomNum){
        // Implicit intents
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        String number = String.valueOf(randomNum);

        i.putExtra(Intent.EXTRA_SUBJECT, username);
        i.putExtra(Intent.EXTRA_TEXT, number);


        startActivity(Intent.createChooser(i,"Choose a platform"));
    }
}