package edu.uni.seguridadsw.sesion11;

import java.io.*;

public class ServerSerialization {

    public static void main(String[] args) {
        System.out.println("UNI - Seguridad en Software - Sesión 11 - Java Serialization");
        //Crear un pago con información sensible
        Pago pago = new Pago("12345", 1000.0, "4111111111111111", "123", new java.util.Date());
        Usuario usuario1 = new Usuario("juanperez", "password123", "cliente", "juan.perez@example.com");
        //Serializar el pago
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("pago.ser"));
            oos.writeObject(pago);
            System.out.println("Pago serializado exitosamente");
            oos.close();
            oos = new ObjectOutputStream(new FileOutputStream("usuario.ser"));
            oos.writeObject(usuario1);
            System.out.println("Usuario serializado exitosamente");
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }        
    }

}
