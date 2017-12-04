package com.example.fauza.sharedprefsexercise;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewNumber;
    private Button buttonRed;
    private Button buttonBlue;
    private Button buttonCount;

    private String filename = "ap.mobile.mypref";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumber = findViewById(R.id.tv_number);
        buttonRed = findViewById(R.id.bt_red);
        buttonBlue = findViewById(R.id.bt_blue);
        buttonCount = findViewById(R.id.bt_count);

        buttonRed.setOnClickListener(this);
        buttonBlue.setOnClickListener(this);
        buttonCount.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(filename,
                Context.MODE_PRIVATE);
    }

    private int lastColor = 0;

    private int lastCount = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_red:
                lastColor = Color.RED;
                textViewNumber.setBackgroundColor(lastColor);
                break;
            case R.id.bt_blue:
                lastColor = Color.BLUE;
                textViewNumber.setBackgroundColor(lastColor);
                break;
            case R.id.bt_count:
                lastCount++;
                textViewNumber.setText(String.valueOf(lastCount));
                break;
        }
        sharedPreferences.edit()
                .putInt("color", lastColor)
                .putInt("number", lastCount)
                .apply();

    }

    @Override
    protected void onResume() {
        super.onResume();

        int number = sharedPreferences.getInt("number", 0);
        int color = sharedPreferences.getInt("color", Color.GRAY);

        lastCount = number;
        lastColor = color;

        textViewNumber.setText(String.valueOf(number));
        textViewNumber.setBackgroundColor(color);
    }
}
