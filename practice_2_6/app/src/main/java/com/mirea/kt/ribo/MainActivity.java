package com.mirea.kt.ribo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mirea.kt.ribo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button start_button = findViewById(R.id.start_button);
        Button github_button = findViewById(R.id.github_button);
        start_button.setOnClickListener(this);
        github_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_button) {
            Log.d("Click", "Крицкий Кирилл из РИБО-04-22 перешёл в CalcActivity...");
            Intent intent = new Intent(this, CalcActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.github_button) {
            Uri uri = Uri.parse("https://github.com/newrav1k/Android_devOps/tree/master/android_2_6");
            Log.d("Click", "Выполняется переход на гит");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}