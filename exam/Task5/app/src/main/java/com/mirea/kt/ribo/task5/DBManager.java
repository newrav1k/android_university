package com.mirea.kt.ribo.task5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager {

    private SQLiteOpenHelper sqLiteOpenHelper;

    public DBManager(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public boolean saveMessageToDatabase(Message message) {
        SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", message.getId());
        values.put("text", message.getText());
        values.put("date", message.getLocalDateTime().toString());
        values.put("isChecked", message.isChecked() ? 1 : 0);

        long rowId = database.insert("task5", null, values);

        values.clear();
        database.close();

        return rowId != -1;
    }
}