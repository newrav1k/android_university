package com.mirea.kt.ribo.task2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        result = findViewById(R.id.result);

        button.setOnClickListener(v -> activityResultLauncher.launch(new Intent(this, CalculateActivity.class)));
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            o -> {
                if (o.getData() != null) {
                    double res = o.getData().getDoubleExtra("data", 0);
                    result.setText("Результат возведения в квадрат: " + res);
                }
            }
    );
}