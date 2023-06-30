package br.ifsp.dsw3.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifsp.dsw3.model.dao.CheckDAO;
import br.ifsp.dsw3.model.dao.DoctorDAO;
import br.ifsp.dsw3.model.dao.PatientDAO;
import br.ifsp.dsw3.model.domain.Check;
import br.ifsp.dsw3.model.domain.Doctor;
import br.ifsp.dsw3.model.domain.Patient;
import br.ifsp.dsw3.model.utils.Validator;

@WebServlet(name = "checkServlet", value = "/checks")
public class CheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //Data 
        String reqID = req.getParameter("id");

        //Send message to frontend
        String message = "";
        String type = "error";

        CheckDAO checkDAO = new CheckDAO();

        if (reqID != null) {
            int ID = Integer.parseInt(reqID);
            if (checkDAO.get(ID) != null) {
                checkDAO.delete(ID);
                message = "Consulta excluída!";
                type = "success";
            } else {
                message = "Algo deu errado!";
            }
        }

        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("type", type);
        res.sendRedirect("checks/create.jsp");
    }

    @Override
    public void init() throws ServletException {
        CheckDAO checkDAO = new CheckDAO();
        checkDAO.createTable();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        CheckDAO checkDAO = new CheckDAO();
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();

        // Data
        String CRM = req.getParameter("CRM");
        String CPF = req.getParameter("CPF");
        String room = req.getParameter("room");
        String action = req.getParameter("action");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String datetime = (date + ' ' + time);
        String getId = req.getParameter("id");

        //Message to frontend
        String message = "";
        String type = "error";

        if (action.contains("create")) {
            if ((Validator.cpf(CPF)) && (Validator.crm(CRM)) && (Validator.text(room)) && (datetime != null)) {
                System.out.println(datetime);
                Patient patient = patientDAO.get(CPF);
                Doctor doctor = doctorDAO.get(CRM);

                Check check = new Check(patient, doctor, datetime, room);
                Check existsCRM = checkDAO.getCRM(CRM, datetime);
                Check existsCPF = checkDAO.getCPF(CPF, datetime);

                System.out.println(existsCRM);
                System.out.println(existsCPF);

                if ((existsCPF == null) && (existsCRM == null)) {
                     checkDAO.create(check);
                     message = "Consulta agendada!";
                     type = "success";
                } else {
                    message = "Este médico/paciente já está ocupado nesta data!";
                } 
            } else {
                message = "Preencha corretamente os dados!";
            }
        } else {
            if (getId != null) {
                int ID = Integer.parseInt(getId);
                if ((Validator.text(room)) && (datetime != null)) {
                    Check exists = checkDAO.get(ID);

                    if (exists != null) {
                        Check check = new Check(datetime, room);
                        checkDAO.update(check, ID);
                        message = "Consulta atualizada!";
                        type = "success";
                    } else {
                        message = "Esta consulta não existe!";
                    }
                } else {
                    message = "Preencha corretamente os dados!";
                }
            } else {
                message = "Selecione uma consulta válida!";
            }
        }

        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("type", type);

        if (action.contains("create")) {
            res.sendRedirect("checks/create.jsp");
        } else {
            res.sendRedirect("checks/update.jsp");
        }
    }
}