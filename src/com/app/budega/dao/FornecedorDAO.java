package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FornecedorDAO {

    private Conexao conexao;

    public FornecedorDAO() {
        conexao = new Conexao();
    }

    public boolean salvar(Fornecedor fornecedor) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO fornecedor(cnpj, nome, contato)" +
                            "VALUES (?,?,?)");
            pstmt.setString(1, fornecedor.getCnpj());
            pstmt.setString(2, fornecedor.getNome());
            pstmt.setString(3, fornecedor.getTelefone());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean atualizar(Fornecedor fornecedor) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE fornecedor SET cnpj = ?, nome = ?, contato = ?");
            pstmt.setString(1, fornecedor.getCnpj());
            pstmt.setString(2, fornecedor.getNome());
            pstmt.setString(3, fornecedor.getTelefone());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(Fornecedor fornecedor) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM fornecedor WHERE cnpj = ?");
            pstmt.setString(1, fornecedor.getCnpj());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Fornecedor buscarPorCnpj(String cnpj) throws SQLException,
            ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM fornecedor WHERE cnpj = ?");
            pstmt.setString(1, cnpj);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");

                return new Fornecedor(cnpj, nome, contato);
            }
            return null;
        }
    }

    public Set<Fornecedor> getFornecedor() throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM fornecedor"
            );

            Set<Fornecedor> fornecedor = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String cnpj = rs.getString("cnpj");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");

                fornecedor.add(new Fornecedor(cnpj, nome, contato));
            }
            return fornecedor;
        }
    }

}
