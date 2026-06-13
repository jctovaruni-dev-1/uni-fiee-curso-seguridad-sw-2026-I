package edu.uni.seguridadsw.sesion11;

import java.util.Base64;

public class Encriptador {

    public static String encriptar(String valor) {
        //Aquí implemento un algoritmo de encriptación simple (no recomendado para producción)
        StringBuilder sb = new StringBuilder();
        for (char c : valor.toCharArray()) {
            sb.append((char)(c + 3)); // Desplazamiento de caracteres
        }
        //-- En un caso real, usaría una librería de encriptación robusta como AES
        //-- pero vamos a usar encoding de base64 para simplificar
        return Base64.getEncoder().encodeToString(valor.getBytes());
        //return sb.toString();
    }

}
