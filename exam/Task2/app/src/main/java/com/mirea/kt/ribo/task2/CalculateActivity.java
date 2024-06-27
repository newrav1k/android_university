package com.mirea.kt.ribo.task2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalculateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        EditText numberEt = findViewById(R.id.number);
        Button button = findViewById(R.id.back);

        button.setOnClickListener(v -> {
            String number = numberEt.getText().toString();
            double result = Double.parseDouble(number) * Double.parseDouble(number);
            Intent intent = new Intent();
            intent.putExtra("data", result);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}