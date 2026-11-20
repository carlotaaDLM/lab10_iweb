package org.example.lab10_iweb.dto;

import java.math.BigDecimal;

public class PlanDTO {
    private int idPlan;
    private String nombre;
    private BigDecimal precioMensual;

    public PlanDTO() {
    }

    public PlanDTO(int idPlan, String nombre, BigDecimal precioMensual) {
        this.idPlan = idPlan;
        this.nombre = nombre;
        this.precioMensual = precioMensual;

    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(BigDecimal precioMensual) {
        this.precioMensual = precioMensual;
    }
}
