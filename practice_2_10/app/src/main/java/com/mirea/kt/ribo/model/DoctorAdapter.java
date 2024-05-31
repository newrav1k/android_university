package com.mirea.kt.ribo.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.kt.ribo.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    public interface OnButtonDoctorClickListener {
        void onDeleteButtonClick(Doctor doctor, int resId);
    }

    private final List<Doctor> doctors;
    private final OnButtonDoctorClickListener onButtonDoctorClickListener;

    public DoctorAdapter(List<Doctor> doctors, OnButtonDoctorClickListener onButtonDoctorClickListener) {
        this.doctors = doctors;
        this.onButtonDoctorClickListener = onButtonDoctorClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView surname;
        private final TextView speciality;
        private final TextView isCertification;
        private final ImageButton delete_doctor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            surname = itemView.findViewById(R.id.surname);
            speciality = itemView.findViewById(R.id.speciality);
            isCertification = itemView.findViewById(R.id.isCertification);
            delete_doctor = itemView.findViewById(R.id.delete_doctor);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.name.setText(String.format("Имя: %s", doctor.getName()));
        holder.surname.setText(String.format("Фамилия: %s", doctor.getSurname()));
        holder.speciality.setText(String.format("Специальность: %s", doctor.getSpeciality()));
        holder.isCertification.setText(doctor.isCertification() ? "Прошёл аттестацию" : "Не проходил аттестацию");

        holder.delete_doctor.setOnClickListener(v -> onButtonDoctorClickListener.onDeleteButtonClick(doctor, holder.delete_doctor.getId()));
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }
}