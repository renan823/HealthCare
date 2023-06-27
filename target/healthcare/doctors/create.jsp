<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ifsp.dsw3.model.dao.*, br.ifsp.dsw3.model.domain.*"%>

<%
    DoctorDAO doctorDAO = new DoctorDAO();
    doctorDAO.createTable();
    ArrayList<Doctor> doctors = doctorDAO.getAll();
%>

<!DOCTYPE html>
<html lang="pt-br">
<%@ include file="../WEB-INF/components/head.jsp"%>
<body>
    <%@ include file="../WEB-INF/components/header.jsp"%>
    <%@ include file="../WEB-INF/components/message.jsp"%>
    <main class="container p-6">
        <h1 class="title m-4 has-text-centered has-text-link">Cadastrar médicos</h1>
        <div class="columns m-6">
            <div class="column mx-4">
                <form action="../doctors" class="box" method="post">
                    <p class="subtitle has-text-centered has-text-primary has-text-weight-bold">Preencha os dados do médico</p>
                    <div class="field">
                        <label class="label">Nome</label>
                        <div class="controls">
                            <input type="text" class="input" name="name" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">CRM</label>
                        <div class="controls">
                            <input type="text" class="input" name="CRM" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Especialização</label>
                        <div class="controls">
                            <textarea class="textarea" name="specialization" autocomplete="off"></textarea>
                        </div>
                    </div>
                    <button class="button is-primary m-4" value="create" name="action">Cadastrar</button>
                </form>
            </div>
            <div class="column mx-4">
                <table class="table is-striped is-fullwidth ">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>CRM</th>
                            <th>Especialização</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for(Doctor d : doctors) {
                        %>
                            <tr>
                                <td><%=d.getName()%></td>
                                <td><%=d.getCRM()%></td>
                                <td><%=d.getSpecialization()%></td>
                                <td>
                                    <a class="button is-danger is-small" href="../doctors?crm=<%=d.getCRM()%>">Excluir</a>
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