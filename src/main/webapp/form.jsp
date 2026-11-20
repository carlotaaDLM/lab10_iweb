<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.lab10_iweb.dto.ClienteDTO" %>
<%@ page import="org.example.lab10_iweb.dto.PlanDTO" %>
<%
    ArrayList<ClienteDTO> clientes = (ArrayList<ClienteDTO>) request.getAttribute("clientes");
    ArrayList<PlanDTO> planes = (ArrayList<PlanDTO>) request.getAttribute("planes");

    // Si se volvió al formulario por un error, estos atributos traen lo que se escribió
    String error = (String) request.getAttribute("error");
    String numero = request.getAttribute("numero") == null ? "" : (String) request.getAttribute("numero");
    String fecha = request.getAttribute("fecha") == null ? "" : (String) request.getAttribute("fecha");
    String idClienteSel = request.getAttribute("idClienteSel") == null ? "" : (String) request.getAttribute("idClienteSel");
    String idPlanSel = request.getAttribute("idPlanSel") == null ? "" : (String) request.getAttribute("idPlanSel");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nueva línea</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">
</head>
<body>
<h1>Nueva línea</h1>

<% if (error != null) { %>
<p class="error"><%=error%></p>
<% } %>

<form method="post" action="<%=request.getContextPath()%>/lineas">
    <input type="hidden" name="action" value="registrar">

    <p>
        <label for="numero">Número:</label><br>
        <input type="text" id="numero" name="numero" value="<%=numero%>">
    </p>

    <p>
        <label for="fecha">Fecha de activación:</label><br>
        <input type="date" id="fecha" name="fecha" value="<%=fecha%>">
    </p>

    <p>
        <label for="idcliente">Cliente:</label><br>
        <select id="idcliente" name="idcliente">
            <% for (ClienteDTO c : clientes) {
                String id = String.valueOf(c.getIdCliente()); %>
            <option value="<%=id%>" <%=id.equals(idClienteSel) ? "selected" : ""%>><%=c.getNombre()%></option>
            <% } %>
        </select>
    </p>

    <p>
        <label for="idplan">Plan:</label><br>
        <select id="idplan" name="idplan">
            <% for (PlanDTO p : planes) {
                String id = String.valueOf(p.getIdPlan()); %>
            <option value="<%=id%>" <%=id.equals(idPlanSel) ? "selected" : ""%>><%=p.getNombre()%> (S/ <%=p.getPrecioMensual()%>)</option>
            <% } %>
        </select>
    </p>

    <button type="submit" class="boton">Registrar</button>
</form>

<p><a href="<%=request.getContextPath()%>/lineas">Volver al listado</a></p>
</body>
</html>
