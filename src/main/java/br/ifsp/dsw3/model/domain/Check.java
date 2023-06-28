package br.ifsp.dsw3.model.domain;

import java.sql.Date;

public class Check {
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String room;
    private int id;

    public Check(Patient patient, Doctor doctor, Date date, String room, int id) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.room = room;
        this.id = id;
    }

    public Check(Patient patient, Doctor doctor, Date date, String room) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.room = room;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Check [patient=" + patient + ", doctor=" + doctor + ", date=" + date + ", room=" + room + "]";
    }
}
