package com.mirea.kt.ribo.task6;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager manager = new PreferenceManager(getApplicationContext());

        Button add_button = findViewById(R.id.add_button);
        Button display_button = findViewById(R.id.display_button);

        EditText editText = findViewById(R.id.edit_text_id);

        add_button.setOnClickListener(v -> {
            String word = editText.getText().toString();
            manager.saveWord(word);
        });
        display_button.setOnClickListener(v -> {
            String word = editText.getText().toString();
            ((TextView) findViewById(R.id.text)).setText(manager.getWord());
        });
    }
}