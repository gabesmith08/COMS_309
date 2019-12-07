package com.example.funfacts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private FactBook factBook = new FactBook();
    private ColorWheel colorWheel = new ColorWheel();
    // Declare our View variables
    private TextView factTextView;
    private Button showFactButton;
    private RelativeLayout factBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the Views from the layout file to the corresponding variables
        factTextView = findViewById(R.id.factTextView);
        showFactButton = findViewById(R.id.showFactButton);
        factBackground = findViewById(R.id.factBackground);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // changes random fact on click
                    String fact = factBook.getFact();
                    factTextView.setText(fact);

                    // changes background color on click
                    int color = colorWheel.getColor();
                    factBackground.setBackgroundColor(color);
            }
        };
        showFactButton.setOnClickListener(listener);
    }
}
