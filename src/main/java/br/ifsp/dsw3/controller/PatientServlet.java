package br.ifsp.dsw3.controller;

import br.ifsp.dsw3.model.utils.Validator;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifsp.dsw3.model.dao.PatientDAO;
import br.ifsp.dsw3.model.domain.Patient;

@WebServlet(name = "patientServlet", value = "/patients")
public class PatientServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Data
        String CPF = req.getParameter("cpf");

        //Send message to frontend
        String message = "";
        String type = "error";

        PatientDAO patientDAO = new PatientDAO();

        if (Validator.cpf(CPF)) {
            if (patientDAO.get(CPF) != null) {
                patientDAO.delete(CPF);
                message = "Paciente excluído!";
                type = "success";
            } else {
                message = "Algo deu errado!";
            }
        }

        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("type", type);
        res.sendRedirect("patients/create.jsp");
    };

    @Override
    public void init() {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.createTable();
    };

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        PatientDAO patientDAO = new PatientDAO();

        // Data
        String CPF = req.getParameter("CPF");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        String action = req.getParameter("action");

        //Message to frontend
        String message = "";
        String type = "error";

        //Validate data
        if ((Validator.cpf(CPF)) && (Validator.text(name)) && (Validator.phone(phone))) {
            //User creation
            Patient patient = new Patient(name, CPF, sex, phone);
            Patient exists = patientDAO.get(CPF);

            if (action.contains("create")) {
                //Add
                if (exists == null) {
                    patientDAO.create(patient);
                    message = "Paciente adicionado!";
                    type = "success";
                } else {
                    message = "Este paciente já existe!";
                }
            } else {
                //Update
                if (exists != null) {
                    patientDAO.update(patient);
                    message = "Paciente atualizado!";
                    type = "success";
                } else {
                    message = "Este paciente não existe!";
                }
            }
        } else {
            message = "Preencha os campos corretamente!";
        }

        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("type", type);

        if (action.contains("create")) {
            res.sendRedirect("patients/create.jsp");
        } else {
            res.sendRedirect("patients/update.jsp");
        }
    };
}
