package com.app.budega.dao;

import com.app.budega.connection.Conexao;
import com.app.budega.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ClienteDao {

    private Conexao conexao;

    public ClienteDao() {
        conexao = new Conexao();
    }

    public boolean salvar(Cliente cliente) throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO cliente(cpf, nome, contato, divida)" +
                            "VALUES (?,?,?,?)");

            pstmt.setString(1, cliente.getCpf());
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getContato());
            pstmt.setFloat(4, cliente.getDivida());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Cliente buscarPorCPF(String cpf) throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM cliente WHERE cpf = ?");
            pstmt.setString(1, cpf);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");
                float divida = rs.getFloat("divida");

                return new Cliente(cpf, nome, contato, divida);
            }
            return null;
        }
    }



}
