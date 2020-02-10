package com.app.budega.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String url;
    private String usuario;
    private String senha;

    public Conexao(){
        url = "jdbc:postgresql://localhost:5432/Budega";
        usuario = "postgres";
        senha = "admin123";
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url,usuario,senha);
    }
}

