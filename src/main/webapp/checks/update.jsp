<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ifsp.dsw3.model.dao.*, br.ifsp.dsw3.model.domain.*"%>

<%
    DoctorDAO doctorDAO = new DoctorDAO();
    doctorDAO.createTable();
    ArrayList<Doctor> doctors = doctorDAO.getAll();

    PatientDAO patientDAO = new PatientDAO();
    patientDAO.createTable();
    ArrayList<Patient> patients = patientDAO.getAll();

    CheckDAO checkDAO = new CheckDAO();
    checkDAO.createTable();
    ArrayList<Check> checks = checkDAO.getAll();
%>

<!DOCTYPE html>
<html lang="pt-br">
<%@ include file="../WEB-INF/components/head.jsp"%>
<body>
    <%@ include file="../WEB-INF/components/header.jsp"%>
    <%@ include file="../WEB-INF/components/message.jsp"%>
    <main class="container p-6">
        <h1 class="title m-4 has-text-centered has-text-link">Agendar consultas</h1>
        <div class="columns m-6">
            <div class="column mx-4">
                <form action="../checks" class="box" method="post">
                    <p class="subtitle has-text-centered has-text-primary has-text-weight-bold">Preencha os dados da consulta</p>
                    <div class="field">
                    <label class="label">ID da consulta</label>
                    <div class="controls">
                        <div class="select">
                            <select name="id">
                                <%
                                    for(Check c: checks) {
                                %>
                                    <option value="<%=c.getId()%>"><%=c.getId()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <hr class="hr"/>
                    <p class="subtitle has-text-4 has-text-weight-bold has-text-primary">Dados da consulta</p>
                     <div class="field">
                        <label class="label">Data</label>
                        <div class="controls">
                            <input type="date" class="input" name="date"/>
                        </div>
                    </div>
                   <div class="field">
                        <label class="label">Hora</label>
                        <div class="controls">
                            <input type="time" class="input" name="time"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Sala</label>
                        <div class="controls">
                            <textarea class="textarea" name="room"></textarea>
                        </div>
                    </div>
                    <button class="button is-primary m-4" value="update" name="action">Atualizar</button>
                </form>
            </div>
        </div>
    </main>
    <%@ include file="../WEB-INF/components/notification.jsp" %>
</body>
</html>