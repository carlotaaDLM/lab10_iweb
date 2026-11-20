package org.example.lab10_iweb.dto;

public class LineaDTO {
    private int idLinea;
    private String numero;
    private String fechaActivacion;
    private ClienteDTO cliente;
    private PlanDTO plan;

    public LineaDTO(){
    }

    public LineaDTO(int idLinea, String numero, String fechaActivacion, ClienteDTO cliente, PlanDTO plan){
        this.idLinea = idLinea;
        this.numero = numero;
        this.fechaActivacion = fechaActivacion;
        this.cliente = cliente;
        this.plan = plan;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(String fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public PlanDTO getPlan() {
        return plan;
    }

    public void setPlan(PlanDTO plan) {
        this.plan = plan;
    }
}
