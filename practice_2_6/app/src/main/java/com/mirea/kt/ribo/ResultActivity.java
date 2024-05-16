package com.mirea.kt.ribo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView result_text = findViewById(R.id.result);
        Button back_button = findViewById(R.id.back_button);
        Button start_button = findViewById(R.id.start_menu);
        back_button.setOnClickListener(this);
        start_button.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        result_text.setText(bundle.get("data").toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button) {
            Log.d("Click", "Выполняется переход назад");
            finish();
        }
        if (v.getId() == R.id.start_menu) {
            Log.d("Click", "Выполняется переход в стартовое меню");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}