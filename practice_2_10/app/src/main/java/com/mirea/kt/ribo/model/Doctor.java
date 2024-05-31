package com.mirea.kt.ribo.model;

public class Doctor {
    private int doctorId;
    private String name;
    private String surname;
    private String speciality;
    private boolean isCertification;

    public Doctor(int doctorId, String name, String surname, String speciality, boolean isCertification) {
        this.doctorId = doctorId;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.isCertification = isCertification;
    }

    public Doctor(String name, String surname, String speciality, boolean isCertification) {
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.isCertification = isCertification;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public boolean isCertification() {
        return isCertification;
    }
}