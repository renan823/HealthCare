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
                    <p class="subtitle has-text-4 has-text-weight-bold has-text-primary">Paciente</p>
                    <div class="field">
                        <label class="label">CPF</label>
                        <div class="controls">
                            <div class="select">
                                <select name="CPF" id="CPF">
                                    <%
                                        for(Patient p : patients) {
                                    %>
                                        <option value="<%=p.getCPF()%>"><%=p.getCPF()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr class="hr"/>
                    <p class="subtitle has-text-4 has-text-weight-bold has-text-primary"> Médico</p>
                    <div class="field">
                        <label class="label">CRM</label>
                        <div class="controls">
                            <div class="select">
                                <select name="CRM" id="CRM">
                                    <%
                                        for(Doctor d : doctors) {
                                    %>
                                        <option value="<%=d.getCRM()%>"><%=d.getCRM()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr class="hr"/>
                    <p class="subtitle has-text-4 has-text-weight-bold has-text-primary">Consulta</p>
                    <div class="field">
                        <label class="label">Data</label>
                        <div class="controls">
                            <input type="datetime-local" class="input" name="date"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Sala</label>
                        <div class="controls">
                            <textarea class="textarea" name="room"></textarea>
                        </div>
                    </div>
                    <button class="button is-primary m-4" value="create" name="action">Cadastrar</button>
                </form>
            </div>
            <div class="column mx-4">
                <table class="table is-fullwidth is-striped">
                    <thead>
                        <tr>
                            <th>Médico</th>
                            <th>Paciente</th>
                            <th>Sala</th>
                            <th>Data</th>
                            <th></th>
                        </tr>
                    </thead>
                   <tbody>
                        <%
                            for(Check c : checks) {
                        %>
                            <tr>
                                <td><%=c.getDoctor().getName()%></td>
                                <td><%=c.getPatient().getName()%></td>
                                <td><%=c.getRoom()%></td>
                                <td><%=c.getDate()%></td>
                                <td>
                                    <a class="button is-danger is-small" href="../checks?id=<%=c.getId()%>">Excluir</a>
                                </td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
    <%@ include file="../WEB-INF/components/notification.jsp" %>
</body>
</html>