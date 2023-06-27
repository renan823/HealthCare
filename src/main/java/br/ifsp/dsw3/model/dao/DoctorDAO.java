package br.ifsp.dsw3.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ifsp.dsw3.model.domain.Doctor;

public class DoctorDAO {
     public void createTable() {
        String sql = 
            "CREATE TABLE IF NOT EXISTS doctor(" +
            "CRM char(8) PRIMARY KEY NOT NULL UNIQUE," +
            "name varchar(255) NOT NULL," +
            "specialization varchar(255) NOT NULL)";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     };

    public void create(Doctor doctor) {
        String sql = "INSERT INTO doctor (CRM, name, specialization) VALUES(?, ?, ?)";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, doctor.getCRM());
            stmt.setString(2, doctor.getName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void update(Doctor doctor) {
        String sql = "UPDATE doctor SET name=?, specialization=? WHERE CRM=?";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getCRM());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void delete(String CRM) {
        String sql = "DELETE FROM doctor WHERE CRM=?";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, CRM);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    
    public Doctor get(String CRM) {
        String sql = "SELECT * FROM doctor WHERE CRM=?";
        Doctor doctor = null;

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, CRM);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                doctor = new Doctor(CRM, name, specialization);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    };

    public ArrayList<Doctor> getAll() {
        String sql = "SELECT * FROM doctor";
        ArrayList<Doctor> doctors = new ArrayList<>();

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String CRM = rs.getString("CRM");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                Doctor doctor = new Doctor(CRM, name, specialization);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
