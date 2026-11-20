<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.example.lab10_iweb.dto.ResumenPlanDTO" %>
<%
    ArrayList<ResumenPlanDTO> resumen = (ArrayList<ResumenPlanDTO>) request.getAttribute("resumen");
    BigDecimal total = new BigDecimal("0.00");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resumen por plan</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">
</head>
<body>
<h1>Resumen por plan</h1>

<table>
    <thead>
    <tr>
        <th>Plan</th>
        <th>Cantidad de líneas</th>
        <th>Ingreso mensual (S/)</th>
    </tr>
    </thead>
    <tbody>
    <% for (ResumenPlanDTO r : resumen) {
        total = total.add(r.getIngresoMensual()); %>
    <tr>
        <td><%=r.getNombrePlan()%></td>
        <td><%=r.getCantidadLineas()%></td>
        <td><%=r.getIngresoMensual()%></td>
    </tr>
    <% } %>
    </tbody>
</table>

<p><b>Ingreso mensual total: S/ <%=total%></b></p>

<p><a href="<%=request.getContextPath()%>/lineas">Volver al listado</a></p>
</body>
</html>
