package br.ifsp.dsw3.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ifsp.dsw3.model.domain.Check;
import br.ifsp.dsw3.model.domain.Doctor;
import br.ifsp.dsw3.model.domain.Patient;

public class CheckDAO {

    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS checks(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "CPF char(14) NOT NULL," +
                "CRM char(8) NOT NULL," +
                "date datetime NOT NULL," +
                "room varchar(255) NOT NULL," +
                "FOREIGN KEY(CPF) REFERENCES patient(CPF)," +
                "FOREIGN KEY(CRM) REFERENCES doctor(CRM))";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Check check) {
        String sql = "INSERT INTO checks(CPF, CRM, date, room) values(?, ?, ?, ?)";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            System.out.println("here");
            stmt.setString(1, check.getPatient().getCPF());
            stmt.setString(2, check.getDoctor().getCRM());
            stmt.setString(3, check.getDate());
            stmt.setString(4, check.getRoom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Check check, int id) {
        String sql = "UPDATE checks SET room=?, date=? WHERE id=?";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, check.getRoom());
            stmt.setString(2, check.getDate());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM checks WHERE id=?";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByCPF(String CPF) {
        String sql = "DELETE FROM checks WHERE CPF=?";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, CPF);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByCRM(String CRM) {
        String sql = "DELETE FROM checks WHERE CPF=?";

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, CRM);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Check get(int id) {
        String sql = "SELECT * FROM checks WHERE id=?";
        Check check = null;

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String CPF = rs.getString("CPF");
                String CRM = rs.getString("CRM");
                String room = rs.getString("room");
                String date = rs.getString("date");

                Doctor doctor = doctorDAO.get(CRM);
                Patient patient = patientDAO.get(CPF);

                check = new Check(patient, doctor, date, room, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public Check getCRM(String CRM, String datetime) {
        String sql = "SELECT * FROM checks WHERE CRM=? and date=?";
        Check check = null;

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, CRM);
            stmt.setString(2, datetime);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String room = rs.getString("room");
                String CPF = rs.getString("CPF");

                Doctor doctor = doctorDAO.get(CRM);
                Patient patient = patientDAO.get(CPF);

                check = new Check(patient, doctor, datetime, room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public Check getCPF(String CPF, String datetime) {
        String sql = "SELECT * FROM checks WHERE CPF=? and date=?";
        Check check = null;

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            stmt.setString(1, CPF);
            stmt.setString(2, datetime);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String room = rs.getString("room");
                String CRM = rs.getString("CRM");

                Doctor doctor = doctorDAO.get(CRM);
                Patient patient = patientDAO.get(CPF);

                check = new Check(patient, doctor, datetime, room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public ArrayList<Check> getAll() {
        String sql = "SELECT * FROM checks";
        ArrayList<Check> checks = new ArrayList<>();

        try (PreparedStatement stmt = Connections.createStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String CPF = rs.getString("CPF");
                String CRM = rs.getString("CRM");
                String room = rs.getString("room");
                int id = rs.getInt("id");
                String date = rs.getString("date");

                Doctor doctor = doctorDAO.get(CRM);
                Patient patient = patientDAO.get(CPF);

                Check check = new Check(patient, doctor, date, room, id);
                checks.add(check);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checks;
    }
}
