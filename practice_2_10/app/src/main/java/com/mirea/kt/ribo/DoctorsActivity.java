package com.mirea.kt.ribo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.kt.ribo.database.DBManager;
import com.mirea.kt.ribo.database.MyAppSQLiteHelper;
import com.mirea.kt.ribo.model.Doctor;
import com.mirea.kt.ribo.model.DoctorAdapter;

import java.util.ArrayList;

public class DoctorsActivity extends AppCompatActivity implements DoctorAdapter.OnButtonDoctorClickListener {

    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

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

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}