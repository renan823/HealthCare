<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ifsp.dsw3.model.dao.*, br.ifsp.dsw3.model.domain.*"%>

<%
    PatientDAO patientDAO = new PatientDAO();
    patientDAO.createTable();
    ArrayList<Patient> patients = patientDAO.getAll();
%>

<!DOCTYPE html>
<html lang="pt-br">
<%@ include file="../WEB-INF/components/head.jsp"%>
<body>
    <%@ include file="../WEB-INF/components/header.jsp"%>
    <%@ include file="../WEB-INF/components/message.jsp"%>
    <main class="container p-6">
        <h1 class="title m-4 has-text-centered has-text-link">Atualizar pacientes</h1>
        <div class="columns m-6">
            <div class="column mx-6">
                <form action="../patients" class="box" method="post">
                    <p class="subtitle has-text-centered has-text-primary has-text-weight-bold">Atualize os dados do paciente</p>
                    <div class="field">
                        <label class="label">Nome</label>
                        <div class="controls">
                            <input type="text" class="input" name="name" autocomplete="off">
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">CPF</label>
                        <div class="controls">
                            <div class="select">
                                <select name="CPF">
                                    <%
                                        for (Patient p : patients) {
                                    %>
                                        <option value="<%=p.getCPF()%>"><%=p.getCPF()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Sexo</label>
                        <div class="controls">
                            <div class="select">
                                <select name="sex">
                                    <option value="F">Feminino</option>
                                    <option value="M">Masculino</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Telefone</label>
                        <div class="controls">
                            <input type="tel" class="input" name="phone" placeholder="(xx) xxxxx-xxxx" autocomplete="off">
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