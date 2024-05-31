package com.mirea.kt.ribo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start_button = findViewById(R.id.start_button);
        Button github_button = findViewById(R.id.github_button);

        start_button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), DoctorsActivity.class)));
        github_button.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://github.com/newrav1k/android_university/tree/master/practice_2_10");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }
}