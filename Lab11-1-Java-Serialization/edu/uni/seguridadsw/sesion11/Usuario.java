package edu.uni.seguridadsw.sesion11;

import java.io.Serializable;

public class Usuario implements Serializable {
    
    private String username;
    private transient String password; // Información sensible
    private String rol;
    private String email;

    public Usuario(String username, String password, String rol, String email) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public String getEmail() {
        return email;
    }

}
