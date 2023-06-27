<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ifsp.dsw3.model.dao.*, br.ifsp.dsw3.model.domain.*"%>

<header>
    <nav class="navbar" role="navigation" aria-label="main navigation">
        <div class="navbar-brand">
          <a class="navbar-item" href="/healthcare/index.jsp">
            <h1 class="title has-text-primary is-size-2 has-text-weight-bold">Health&Care</h1>
          </a>
        </div>
        <div class="navbar-menu">
          <div class="navbar-start">
            <div class="navbar-item has-dropdown is-hoverable">
              <a class="navbar-link">Médicos</a>
      
              <div class="navbar-dropdown">
                <a class="navbar-item" href="/healthcare/doctors/create.jsp">Cadastrar</a>
                <a class="navbar-item" href="/healthcare/doctors/update.jsp">Atualizar</a>
              </div>
            </div>
            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">Pacientes</a>
                <div class="navbar-dropdown">
                    <a class="navbar-item" href="/healthcare/patients/create.jsp">Cadastrar</a>
                    <a class="navbar-item" href="/healthcare/patients/update.jsp">Atualizar</a>
                </div>
            </div>
              <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">Consultas</a>
                <div class="navbar-dropdown">
                    <a class="navbar-item" href="/healthcare/checks/create.jsp">Cadastrar</a>
                    <a class="navbar-item" href="/healthcare/checks/update.jsp">Atualizar</a>
                </div>
              </div>
          </div>
        </div>
      </nav>
</header>