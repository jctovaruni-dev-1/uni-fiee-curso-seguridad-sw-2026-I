package edu.uni.seguridadsw.sesion11;

import java.util.Date;

public class Pago implements java.io.Serializable {
    
    private String id;
    private double monto;
    private String tarjetaCredito; // Información sensible
    private String cvv; // Información sensible
    private Date fechaExpiracion; // Información sensible

    public Pago(String id, double monto, String tarjetaCredito, String cvv, Date fechaExpiracion) {
        this.id = id;
        this.monto = monto;
        this.tarjetaCredito = tarjetaCredito;
        this.cvv = cvv;
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public String getCvv() {
        return cvv;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void descontarMonto(double descuento) {
        this.monto -= descuento;
        //Aqui tengo codigo para informar al banco del descuento aplicado,
        // lo cual es una operación sensible
        ApiBanco.informarDescuento(this.id, descuento);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        //Encriptar la información sensible antes de serializar
        String tarjetaEncriptada = Encriptador.encriptar(tarjetaCredito);
        String cvvEncriptado = Encriptador.encriptar(cvv);
        Date fechaEncriptada = fechaExpiracion; // Para simplificar, no encriptamos la fecha
        //out.defaultWriteObject();
        out.writeObject(tarjetaEncriptada);
        out.writeObject(cvvEncriptado);
        out.writeObject(fechaEncriptada);
    }

}
