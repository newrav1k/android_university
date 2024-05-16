package com.mirea.kt.ribo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        Button calculation_button = findViewById(R.id.calculation_button);
        Button back_button = findViewById(R.id.back_button);
        calculation_button.setOnClickListener(this);
        back_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calculation_button) {
            Log.d("Click", "Крицкий Кирилл из РИБО-04-22 нажал на кнопку перемножения...");

            EditText number_1 = findViewById(R.id.number_1);
            EditText number_2 = findViewById(R.id.number_2);
            EditText number_3 = findViewById(R.id.number_3);

            String s_number_1 = number_1.getText().toString();
            String s_number_2 = number_2.getText().toString();
            String s_number_3 = number_3.getText().toString();

            if (!s_number_1.isEmpty() && !s_number_2.isEmpty() && !s_number_3.isEmpty()) {
                double multiplication = Double.parseDouble(number_1.getText().toString()) *
                        Double.parseDouble(number_2.getText().toString()) *
                        Double.parseDouble(number_3.getText().toString());

                DecimalFormat format = new DecimalFormat("#.####");
                String text = "Результат:  " + format.format(multiplication);

                Intent intent = new Intent(CalcActivity.this, ResultActivity.class);
                intent.putExtra("data", text);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Введите три числа!", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.back_button) {
            Log.d("Click", "Выполняется переход назад");
            finish();
        }
    }
}