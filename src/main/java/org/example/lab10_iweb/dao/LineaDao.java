package org.example.lab10_iweb.dao;

import org.example.lab10_iweb.dto.ClienteDTO;
import org.example.lab10_iweb.dto.LineaDTO;
import org.example.lab10_iweb.dto.PlanDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LineaDao extends BaseDao {

    public ArrayList<LineaDTO> listar() {
        ArrayList<LineaDTO> lista = new ArrayList<>();
        String sql = "SELECT l.idlinea, l.numero, l.fecha_activacion, "
                + "c.idcliente, c.nombre AS nombre_cliente, c.dni, "
                + "p.idplan, p.nombre AS nombre_plan, p.precio_mensual "
                + "FROM linea l "
                + "INNER JOIN cliente c ON l.idcliente = c.idcliente "
                + "INNER JOIN plan p ON l.idplan = p.idplan "
                + "ORDER BY l.fecha_activacion DESC";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNombre(rs.getString("nombre_cliente"));
                cliente.setDni(rs.getString("dni"));

                PlanDTO plan = new PlanDTO();
                plan.setIdPlan(rs.getInt("idplan"));
                plan.setNombre(rs.getString("nombre_plan"));
                plan.setPrecioMensual(rs.getBigDecimal("precio_mensual"));

                LineaDTO linea = new LineaDTO();
                linea.setIdLinea(rs.getInt("idlinea"));
                linea.setNumero(rs.getString("numero"));
                linea.setFechaActivacion(rs.getString("fecha_activacion"));
                linea.setCliente(cliente);
                linea.setPlan(plan);

                lista.add(linea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean existeNumero(String numero) {
        String sql = "SELECT COUNT(*) FROM linea WHERE numero = ?";
        boolean existe = false;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, numero);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

    public void registrar(LineaDTO linea) {
        String sql = "INSERT INTO linea (numero, fecha_activacion, idcliente, idplan) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, linea.getNumero());
            pstmt.setString(2, linea.getFechaActivacion());
            pstmt.setInt(3, linea.getCliente().getIdCliente());
            pstmt.setInt(4, linea.getPlan().getIdPlan());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
