package org.example.lab10_iweb.dto;

import java.math.BigDecimal;

/**
 * DTO de consulta: no representa ninguna tabla.
 * Guarda una fila del resultado del query de resumen por plan.
 */
public class ResumenPlanDTO {
    private String nombrePlan;
    private int cantidadLineas;
    private BigDecimal ingresoMensual;

    public ResumenPlanDTO() {
    }

    public ResumenPlanDTO(String nombrePlan, int cantidadLineas, BigDecimal ingresoMensual) {
        this.nombrePlan = nombrePlan;
        this.cantidadLineas = cantidadLineas;
        this.ingresoMensual = ingresoMensual;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public int getCantidadLineas() {
        return cantidadLineas;
    }

    public void setCantidadLineas(int cantidadLineas) {
        this.cantidadLineas = cantidadLineas;
    }

    public BigDecimal getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(BigDecimal ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }
}
