package com.mirea.kt.ribo.task1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText number1Et = findViewById(R.id.num1);
        EditText number2Et = findViewById(R.id.num2);
        Button button = findViewById(R.id.button);
        TextView resultTv = findViewById(R.id.result);

        button.setOnClickListener(v -> {
            String result = String.valueOf(Float.parseFloat(number1Et.getText().toString()) + Float.parseFloat(number2Et.getText().toString()));
            resultTv.setText(result);
        });
    }
}