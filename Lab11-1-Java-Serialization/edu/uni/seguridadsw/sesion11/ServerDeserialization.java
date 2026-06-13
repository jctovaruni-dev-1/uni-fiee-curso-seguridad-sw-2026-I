package edu.uni.seguridadsw.sesion11;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerDeserialization {
    
    public static void main(String[] args) {
        System.out.println("UNI - Seguridad en Software - Sesión 11 - Java Serialization");
        //Deserializar el pago
        ObjectInputStream ois = null;
        try {
            //-- Proceso de deserializacion
            ois = new ObjectInputStream(new FileInputStream("pago.ser"));
            Pago pago = (Pago) ois.readObject();
            System.out.println("Pago deserializado exitosamente");
            //-- Imprimir información del pago
            System.out.println("ID: " + pago.getId());
            System.out.println("Monto: " + pago.getMonto());
            System.out.println("Tarjeta de Crédito: " + pago.getTarjetaCredito());
            System.out.println("CVV: " + pago.getCvv());
            System.out.println("Fecha de Expiración: " + pago.getFechaExpiracion());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }        
    }

}
