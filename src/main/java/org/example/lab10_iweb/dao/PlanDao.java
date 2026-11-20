package org.example.lab10_iweb.dao;

import org.example.lab10_iweb.dto.PlanDTO;
import org.example.lab10_iweb.dto.ResumenPlanDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlanDao extends BaseDao {

    public ArrayList<PlanDTO> listar() {
        ArrayList<PlanDTO> lista = new ArrayList<>();
        String sql = "SELECT idplan, nombre, precio_mensual FROM plan ORDER BY nombre";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PlanDTO plan = new PlanDTO();
                plan.setIdPlan(rs.getInt("idplan"));
                plan.setNombre(rs.getString("nombre"));
                plan.setPrecioMensual(rs.getBigDecimal("precio_mensual"));
                lista.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Una fila por plan con su cantidad de líneas y su ingreso mensual.
     * LEFT JOIN para que también salgan los planes sin líneas.
     * En un plan sin líneas, el LEFT JOIN devuelve una fila con l.idlinea en NULL:
     * COUNT(l.idlinea) no la cuenta (da 0) y el CASE hace que no sume su precio (da 0.00).
     */
    public ArrayList<ResumenPlanDTO> resumenPorPlan() {
        ArrayList<ResumenPlanDTO> lista = new ArrayList<>();
        String sql = "SELECT p.nombre, COUNT(l.idlinea) AS cantidad, "
                + "SUM(CASE WHEN l.idlinea IS NULL THEN 0 ELSE p.precio_mensual END) AS ingreso "
                + "FROM plan p "
                + "LEFT JOIN linea l ON l.idplan = p.idplan "
                + "GROUP BY p.idplan, p.nombre "
                + "ORDER BY p.nombre";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ResumenPlanDTO resumen = new ResumenPlanDTO();
                resumen.setNombrePlan(rs.getString("nombre"));
                resumen.setCantidadLineas(rs.getInt("cantidad"));
                resumen.setIngresoMensual(rs.getBigDecimal("ingreso"));
                lista.add(resumen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
