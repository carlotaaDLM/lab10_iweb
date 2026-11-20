<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.lab10_iweb.dto.LineaDTO" %>
<%
    ArrayList<LineaDTO> lista = (ArrayList<LineaDTO>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Líneas móviles</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">
</head>
<body>
<h1>Líneas móviles – Operadora Andina</h1>

<p>
    <a class="boton" href="<%=request.getContextPath()%>/lineas?action=nuevo">Nueva línea</a>
    <a class="boton" href="<%=request.getContextPath()%>/lineas?action=resumen">Resumen por plan</a>
</p>

<table>
    <thead>
    <tr>
        <th>Número</th>
        <th>Cliente</th>
        <th>DNI</th>
        <th>Plan</th>
        <th>Precio (S/)</th>
        <th>Activación</th>
    </tr>
    </thead>
    <tbody>
    <% for (LineaDTO linea : lista) { %>
    <tr>
        <td><%=linea.getNumero()%></td>
        <td><%=linea.getCliente().getNombre()%></td>
        <td><%=linea.getCliente().getDni()%></td>
        <td><%=linea.getPlan().getNombre()%></td>
        <td><%=linea.getPlan().getPrecioMensual()%></td>
        <td><%=linea.getFechaActivacion()%></td>
    </tr>
    <% } %>
    </tbody>
</table>

<p>Total de líneas: <%=lista.size()%></p>
</body>
</html>
