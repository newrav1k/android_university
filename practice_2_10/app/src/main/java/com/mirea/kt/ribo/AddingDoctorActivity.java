package com.mirea.kt.ribo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mirea.kt.ribo.database.DBManager;
import com.mirea.kt.ribo.database.MyAppSQLiteHelper;
import com.mirea.kt.ribo.model.Doctor;

public class AddingDoctorActivity extends AppCompatActivity {

    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_doctor);

        dbManager = new DBManager(new MyAppSQLiteHelper(getApplicationContext(), "doctors.db", null, 1));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        EditText doctor_name = findViewById(R.id.name);
        EditText doctor_surname = findViewById(R.id.surname);
        EditText doctor_speciality = findViewById(R.id.speciality);

        Button add_doctor = findViewById(R.id.add_doctor);

        add_doctor.setOnClickListener(v -> {
            String name = doctor_name.getText().toString().trim();
            String surname = doctor_surname.getText().toString().trim();
            String speciality = doctor_speciality.getText().toString().trim();

            RadioGroup radioGroup = findViewById(R.id.radio);
            int radioButtonId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(radioButtonId);
            boolean isCertification = radioButton.getText().toString().equals("Да");

            if (!name.isEmpty() && !surname.isEmpty() && !speciality.isEmpty()) {
                boolean result = dbManager.saveDoctorToDatabase(new Doctor(name, surname, speciality, isCertification));
                if (result) {
                    Toast.makeText(this, R.string.recording_successfully_saved, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this, R.string.an_error_occurred_while_recording, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), R.string.empty_field, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}