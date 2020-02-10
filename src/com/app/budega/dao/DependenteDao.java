package com.app.budega.dao;

import com.app.budega.connection.Conexao;
import com.app.budega.model.Cliente;
import com.app.budega.model.Dependente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DependenteDao {

    private Conexao conexao;

    public DependenteDao() {
        this.conexao = new Conexao();
    }

    public boolean salvar(Dependente dependente) throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO dependente(responsavel, nome, parentesco, permissao)" +
                            "VALUES (?,?,?,?)");
            pstmt.setString(1, dependente.getResponsavel());
            pstmt.setString(2, dependente.getNome());
            pstmt.setString(3, dependente.getParentesco());
            pstmt.setBoolean(4, dependente.getPermissao());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Dependente buscarPorNomeEResponsavel(String nome, String cpf) throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM dependente WHERE responsavel = ? AND nome = ?");
            pstmt.setString(1, cpf);
            pstmt.setString(2, nome);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String parentesco = rs.getString("parentesco");
                boolean permissao = rs.getBoolean("permissao");

                return new Dependente(cpf, nome, parentesco, permissao);
            }
            return null;
        }
    }

    public Set<Dependente> getDependentes() throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM dependente");
            Set<Dependente> clientes = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String responsavel = rs.getString("responsavel");
                String nome = rs.getString("nome");
                String parentesco = rs.getString("parentesco");
                boolean permissao = rs.getBoolean("permissao");

                clientes.add(new Dependente(responsavel, nome, parentesco, permissao));
            }
            return clientes;
        }
    }

}