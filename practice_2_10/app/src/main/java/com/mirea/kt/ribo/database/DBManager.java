package com.mirea.kt.ribo.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mirea.kt.ribo.model.Doctor;

import java.util.ArrayList;

public class DBManager {

    private SQLiteOpenHelper sqLiteOpenHelper;

    public DBManager(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public boolean saveDoctorToDatabase(Doctor doctor) {
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", doctor.getName());
        values.put("surname", doctor.getSurname());
        values.put("speciality", doctor.getSpeciality());
        values.put("isCertification", doctor.isCertification());

        long rowId = database.insert("TABLE_DOCTORS", null, values);

        values.clear();
        database.close();

        return rowId != -1;
    }

    public ArrayList<Doctor> loadAllDoctorsFromDatabase() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();

        Cursor cursor = database.query("TABLE_DOCTORS",
                null, null, null,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String surname = cursor.getString(cursor.getColumnIndexOrThrow("surname"));
                String speciality = cursor.getString(cursor.getColumnIndexOrThrow("speciality"));
                int isCertification = cursor.getInt(cursor.getColumnIndexOrThrow("isCertification"));
                doctors.add(new Doctor(id, name, surname, speciality, isCertification == 1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return doctors;
    }

    public void deleteDoctorFromDatabase(Doctor doctor) {
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();
        database.delete("TABLE_DOCTORS",  "_id" + "=?", new String[]{String.valueOf(doctor.getDoctorId())});
    }

    public void clearDatabase() {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + "TABLE_DOCTORS");
        db.close();
    }
}