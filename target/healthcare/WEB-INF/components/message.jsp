<%
        String msg = "";
        String type = "";
        
        if (session.getAttribute("message") != null) {
            msg = (String) session.getAttribute("message");
            
            if (session.getAttribute("type") == "success") {
                type = "notification is-success container has-text-centered my-5";
            } else {
                type = "notification is-danger container has-text-centered my-5";
            }

            session.removeAttribute("message");
            session.removeAttribute("type");
    %>
        <div class="<%=type%>">
            <button class="delete"></button>
            <%=msg %>
        </div>
    <%
        }
    %>