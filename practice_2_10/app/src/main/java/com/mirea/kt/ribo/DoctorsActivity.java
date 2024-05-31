package com.mirea.kt.ribo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.kt.ribo.database.DBManager;
import com.mirea.kt.ribo.database.MyAppSQLiteHelper;
import com.mirea.kt.ribo.dialog.ClearDatabaseDialog;
import com.mirea.kt.ribo.model.Doctor;
import com.mirea.kt.ribo.model.DoctorAdapter;

import java.util.ArrayList;

public class DoctorsActivity extends AppCompatActivity implements DoctorAdapter.OnButtonDoctorClickListener {

    private DBManager dbManager;
    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        dialog = new Dialog(this);
        dbManager = new DBManager(new MyAppSQLiteHelper(getApplicationContext(), "doctors.db", null, 1));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        ArrayList<Doctor> doctors = dbManager.loadAllDoctorsFromDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        DoctorAdapter doctorAdapter = new DoctorAdapter(doctors, this);
        recyclerView.setAdapter(doctorAdapter);
    }

    private void createClearDataBaseDialog() {
        dialog.setContentView(R.layout.confirm_clear_database_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button confirm_button = dialog.findViewById(R.id.confirm_button);


        RadioGroup radioGroup = dialog.findViewById(R.id.radio);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = dialog.findViewById(radioButtonId);
                if (radioButton.getText().toString().equals("Да")) {
                    dbManager.clearDatabase();
                    updateView();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onDeleteButtonClick(Doctor doctor, int position) {
        dbManager.deleteDoctorFromDatabase(doctor);
        updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.doctors_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.add_doctor) {
            startActivity(new Intent(getApplicationContext(), AddingDoctorActivity.class));
            return true;
        } else if (item.getItemId() == R.id.clear_database) {
            createClearDataBaseDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}