package com.mirea.kt.ribo.task5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText idEt = findViewById(R.id.numberId);
        EditText textEt = findViewById(R.id.text);
        EditText dateEt = findViewById(R.id.date);
        RadioButton radioButton = findViewById(((RadioGroup) findViewById(R.id.radio)).getCheckedRadioButtonId());
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            DBManager dbManager = new DBManager(new MySQLiteHelper(getApplicationContext(), "task5.db", null, 1));
            int id = Integer.parseInt(idEt.getText().toString().toString().trim());
            String text = textEt.getText().toString().toString().trim();
            LocalDateTime date = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                date = LocalDateTime.parse(dateEt.getText().toString().toString().trim());
            }
            boolean isChecked = radioButton.getText().equals("Да");

            Message message = new Message(id, text, date, isChecked);
            dbManager.saveMessageToDatabase(message);
        });
    }
}