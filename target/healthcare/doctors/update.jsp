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
        <h1 class="title m-4 has-text-centered has-text-link">Atualizar médicos</h1>
        <div class="columns m-6">
            <div class="column mx-6">
                <form action="../doctors" class="box" method="post">
                    <p class="subtitle has-text-centered has-text-primary has-text-weight-bold">Atualize os dados do médico</p>
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
                    <div class="field">
                        <label class="label">Nome</label>
                        <div class="controls">
                            <input type="text" class="input" name="name" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Especialização</label>
                        <div class="controls">
                            <textarea class="textarea" name="specialization" autocomplete="off"></textarea>
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