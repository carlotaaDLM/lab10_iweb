package org.example.lab10_iweb.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab10_iweb.dao.ClienteDao;
import org.example.lab10_iweb.dao.LineaDao;
import org.example.lab10_iweb.dao.PlanDao;
import org.example.lab10_iweb.dto.ClienteDTO;
import org.example.lab10_iweb.dto.LineaDTO;
import org.example.lab10_iweb.dto.PlanDTO;

import java.io.IOException;

@WebServlet(name = "LineaServlet", value = "/lineas")
public class LineaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");

        LineaDao lineaDao = new LineaDao();
        PlanDao planDao = new PlanDao();
        RequestDispatcher view;

        switch (action) {
            case "nuevo":
                cargarCombos(request);
                view = request.getRequestDispatcher("form.jsp");
                view.forward(request, response);
                break;

            case "resumen":
                request.setAttribute("resumen", planDao.resumenPorPlan());
                view = request.getRequestDispatcher("resumen.jsp");
                view.forward(request, response);
                break;

            default: // "listar" o cualquier acción desconocida
                request.setAttribute("lista", lineaDao.listar());
                view = request.getRequestDispatcher("lista.jsp");
                view.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        switch (action) {
            case "registrar":
                registrar(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/lineas");
                break;
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LineaDao lineaDao = new LineaDao();

        String numero = request.getParameter("numero") == null ? "" : request.getParameter("numero").trim();
        String fecha = request.getParameter("fecha") == null ? "" : request.getParameter("fecha");
        String idClienteStr = request.getParameter("idcliente");
        String idPlanStr = request.getParameter("idplan");

        // ---- Validaciones ----
        String error = null;
        int idCliente = 0;
        int idPlan = 0;

        if (!esNumeroValido(numero)) {
            error = "El número debe tener 9 dígitos y empezar con 9";
        } else if (fecha.isEmpty()) {
            error = "Debe ingresar la fecha de activación";
        } else {
            try {
                idCliente = Integer.parseInt(idClienteStr);
                idPlan = Integer.parseInt(idPlanStr);
            } catch (NumberFormatException e) {
                error = "Debe seleccionar un cliente y un plan";
            }
        }

        if (error == null && lineaDao.existeNumero(numero)) {
            error = "El número ya se encuentra registrado";
        }

        if (error != null) {
            /*
             * Error -> forward: necesitamos enviar a la vista el mensaje y lo que el usuario
             * escribió (atributos del request). Con sendRedirect esos atributos se pierden,
             * porque el navegador hace una petición nueva.
             */
            request.setAttribute("error", error);
            request.setAttribute("numero", numero);
            request.setAttribute("fecha", fecha);
            request.setAttribute("idClienteSel", idClienteStr);
            request.setAttribute("idPlanSel", idPlanStr);
            cargarCombos(request);

            RequestDispatcher view = request.getRequestDispatcher("form.jsp");
            view.forward(request, response);
            return;
        }

        // ---- Armar los DTOs y registrar ----
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdCliente(idCliente);

        PlanDTO plan = new PlanDTO();
        plan.setIdPlan(idPlan);

        LineaDTO linea = new LineaDTO();
        linea.setNumero(numero);
        linea.setFechaActivacion(fecha);
        linea.setCliente(cliente);
        linea.setPlan(plan);

        lineaDao.registrar(linea);

        /*
         * Éxito -> sendRedirect: no hay nada que enviar a la vista, y así la URL cambia a
         * /lineas. Si el usuario presiona F5, se recarga el listado (GET) y no se vuelve a
         * enviar el formulario (no se duplica el INSERT).
         */
        response.sendRedirect(request.getContextPath() + "/lineas");
    }

    /** 9 caracteres, todos dígitos, y el primero es 9. */
    private boolean esNumeroValido(String numero) {
        if (numero.length() != 9 || numero.charAt(0) != '9') {
            return false;
        }
        for (int i = 0; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /** Envía a la vista las listas para los combobox. */
    private void cargarCombos(HttpServletRequest request) {
        ClienteDao clienteDao = new ClienteDao();
        PlanDao planDao = new PlanDao();
        request.setAttribute("clientes", clienteDao.listar());
        request.setAttribute("planes", planDao.listar());
    }
}
