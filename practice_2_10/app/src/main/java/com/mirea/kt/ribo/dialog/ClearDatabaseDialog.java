package com.mirea.kt.ribo.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.mirea.kt.ribo.R;
import com.mirea.kt.ribo.database.DBManager;

public class ClearDatabaseDialog extends DialogFragment {

    private final DBManager dbManager;

    public ClearDatabaseDialog(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.are_you_sure_you_want_to_clear_the_database)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    dbManager.clearDatabase();
                    dismiss();
                })
                .setNegativeButton(R.string.no, (dialog, which) -> dismiss());
        return builder.create();
    }
}