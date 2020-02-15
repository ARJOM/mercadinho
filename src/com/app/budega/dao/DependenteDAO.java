package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Dependente;
import com.app.budega.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DependenteDAO {

    private Conexao conexao;

    public DependenteDAO() {
        this.conexao = new Conexao();
    }

    public boolean salvar(Dependente dependente) throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO dependente(id, responsavel, nome, parentesco, permissao)" +
                            "VALUES (?,?,?,?,?)");
            pstmt.setString(1, dependente.getId());
            pstmt.setString(2, dependente.getResponsavel());
            pstmt.setString(3, dependente.getNome());
            pstmt.setString(4, dependente.getParentesco());
            pstmt.setBoolean(5, Boolean.parseBoolean(dependente.getPermissao()));

            return pstmt.executeUpdate() > 0;
        }
    }

    public Dependente buscarPorId(String id) throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM dependente WHERE id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String responsavel = rs.getString("responsavel");
                String nome = rs.getString("nome");
                String parentesco = rs.getString("parentesco");
                String permissao = String.valueOf(rs.getBoolean("permissao"));

                return new Dependente(id, responsavel, nome, parentesco, permissao);
            }
            return null;
        }
    }

    public Set<Dependente> getDependentes() throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM dependente"
            );

            Set<Dependente> dependente = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String responsavel = rs.getString("responsavel");
                String nome = rs.getString("nome");
                String parentesco = rs.getString("parentesco");
                String permissao = String.valueOf(rs.getBoolean("permissao"));

                dependente.add(new Dependente(id, responsavel, nome, parentesco, permissao));
            }
            return dependente;
        }
    }
}
