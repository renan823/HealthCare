package br.ifsp.dsw3.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ifsp.dsw3.model.domain.Patient;

public class PatientDAO {
    public void createTable() {
        String sql = 
            "CREATE TABLE IF NOT EXISTS patient(" +
            "CPF char(14) PRIMARY KEY NOT NULL UNIQUE," +
            "name varchar(255) NOT NULL," +
            "sex char(1) NOT NULL," +
            "phone varchar(40) NOT NULL)";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void create(Patient patient) {
        String sql = "INSERT INTO patient (CPF, name, sex, phone) values (?, ?, ?, ?)";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, patient.getCPF());
            stmt.setString(2, patient.getName());
            stmt.setString(3, patient.getSex());
            stmt.setString(4, patient.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void update(Patient patient) {
        String sql = "UPDATE patient SET name=?, sex=?, phone=? WHERE CPF=?";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getSex());
            stmt.setString(3, patient.getPhone());
            stmt.setString(4, patient.getCPF());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void delete(String CPF) {
        String sql = "DELETE FROM patient WHERE CPF=?";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, CPF);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    
    public Patient get(String CPF) {
        String sql = "SELECT * FROM patient WHERE CPF=?";
        Patient patient = null;

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, CPF);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String phone = rs.getString("phone");

                patient = new Patient(name, CPF, sex, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    };

    public ArrayList<Patient> getAll() {
        String sql = "SELECT * FROM patient";
        ArrayList<Patient> patients = new ArrayList<>();

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String CPF = rs.getString("CPF");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String phone = rs.getString("phone");

                Patient patient = new Patient(name, CPF, sex, phone);
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public void ui() {
        String sql = "DROP TABLE patient";
        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
