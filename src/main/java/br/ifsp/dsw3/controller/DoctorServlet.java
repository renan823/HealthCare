package br.ifsp.dsw3.controller;

import br.ifsp.dsw3.model.utils.Validator;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifsp.dsw3.model.dao.DoctorDAO;
import br.ifsp.dsw3.model.domain.Doctor;


@WebServlet(name = "doctorServlet", value = "/doctors")
public class DoctorServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Data
        String CRM = req.getParameter("crm");

        //Send message to frontend
        String message = "";
        String type = "error";

        DoctorDAO doctorDAO = new DoctorDAO();

        if (Validator.crm(CRM)) {
            if (doctorDAO.get(CRM) != null) {
                doctorDAO.delete(CRM);
                message = "Médico excluído!";
                type = "success";
            } else {
                message = "Algo deu errado!";
            }
        }

        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("type", type);
        res.sendRedirect("doctors/create.jsp");
    };

    @Override
    public void init() {
       DoctorDAO doctorDAO = new DoctorDAO();
       doctorDAO.createTable();
    };

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        DoctorDAO doctorDAO = new DoctorDAO();

        // Data
        String CRM = req.getParameter("CRM");
        String name = req.getParameter("name");
        String specialization = req.getParameter("specialization");
        String action = req.getParameter("action");

        //Message to frontend
        String message = "";
        String type = "error";

        //Validate data
        if ((Validator.crm(CRM)) && (Validator.text(name)) && (Validator.text(specialization))) {
            //User creation
            Doctor doctor = new Doctor(CRM, name, specialization);
            Doctor exists = doctorDAO.get(CRM);

            if (action.contains("create")) {
                //Add
                if (exists == null) {
                    doctorDAO.create(doctor);
                    message = "Médico adicionado!";
                    type = "success";
                } else {
                    message = "Este médico já existe!";
                }
            } else {
                //Update
                if (exists != null) {
                    doctorDAO.update(doctor);
                    message = "Médico atualizado!";
                    type = "success";
                } else {
                    message = "Este médico não existe!";
                }
            }
        } else {
            message = "Preencha os campos corretamente!";
        }

        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("type", type);

        if (action.contains("create")) {
            res.sendRedirect("doctors/create.jsp");
        } else {
            res.sendRedirect("doctors/update.jsp");
        }
    };
}
