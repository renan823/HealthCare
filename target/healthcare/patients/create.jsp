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
        <h1 class="title m-4 has-text-centered has-text-link">Cadastrar pacientes</h1>
        <div class="columns m-6">
            <div class="column mx-4">
                <form action="../patients" class="box" method="post">
                    <p class="subtitle has-text-centered has-text-primary has-text-weight-bold">Preencha os dados do paciente</p>
                    <div class="field">
                        <label class="label">Nome</label>
                        <div class="controls">
                            <input type="text" class="input" name="name" autocomplete="off">
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">CPF</label>
                        <div class="controls">
                            <input type="text" class="input" name="CPF" placeholder="xxx.xxx.xxx-xx" autocomplete="off">
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
                    <button class="button is-primary m-4" value="create" name="action">Cadastrar</button>
                </form>
            </div>
            <div class="column">
                <table class="table is-striped is-fullwidth">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Sexo</th>
                            <th>Telefone</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Patient p : patients) {
                        %>
                            <tr>
                                <td><%=p.getName()%></td>
                                <td><%=p.getCPF()%></td>
                                <td><%=p.getSex()%></td>
                                <td><%=p.getPhone()%></td>
                                <td>
                                    <a class="button is-danger is-small" href="../patients?cpf=<%=p.getCPF()%>">Excluir</a>
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