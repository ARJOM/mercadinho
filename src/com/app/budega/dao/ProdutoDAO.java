package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.PedidoProduto;
import com.app.budega.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDAO {
    private Conexao conexao;

    public ProdutoDAO() {
        conexao = new Conexao();
    }

    public boolean salvar(Produto produto) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO produto(codbarras, nome, preco, quantidade)" +
                            "VALUES (?,?,?,?)");
            pstmt.setString(1, produto.getCodBarras());
            pstmt.setString(2, produto.getNome());
            pstmt.setFloat(3, produto.getPreco());
            pstmt.setInt(4, produto.getQuantidade());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean atualizar(Produto produto) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE produto SET codbarras = ?, nome = ?, preco = ?, quantidade = ?");
            pstmt.setString(1, produto.getCodBarras());
            pstmt.setString(2, produto.getNome());
            pstmt.setFloat(3, produto.getPreco());
            pstmt.setInt(4, produto.getQuantidade());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(Produto produto) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM pedidoproduto WHERE codbarras = ?");
            pstmt.setString(1, produto.getCodBarras());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Produto buscarPorCodBarras(String codbarras) throws SQLException,
            ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM produto WHERE codbarras = ?");
            pstmt.setString(1, codbarras);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                Float preco = rs.getFloat("preco");
                Integer quantidade = rs.getInt("quantidade");

                return new Produto(codbarras, nome, preco, quantidade);
            }
            return null;
        }
    }

    public Set<Produto> getProdutos() throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM produto"
            );

            Set<Produto> produtos = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String codbarras = rs.getString("codbarras");
                String nome = rs.getString("nome");
                Float preco = rs.getFloat("preco");
                Integer quantidade = rs.getInt("quantidade");
                produtos.add(new Produto(codbarras, nome, preco, quantidade));
            }
            return produtos;
        }
    }
}