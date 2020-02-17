package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FuncionarioDAO {
    private Conexao conexao;

    public FuncionarioDAO(){
        conexao = new Conexao();
    }

    public boolean Cadastrar(Funcionario funcionario) throws SQLException,ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO funcionario(cpf,nome,senha)"+"VALUES(?,?,?)");
            pstm.setString(1,funcionario.getCpf());
            pstm.setString(2,funcionario.getNome());
            pstm.setString(3,funcionario.getSenha());

            return pstm.executeUpdate() > 0;
        }
    }

    public Funcionario buscarPorCpf(String cpf) throws SQLException,ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM funcionario WHERE cpf = ?");
            pstm.setString(1,cpf);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(2);
                String senha = rs.getString(3);
                return new Funcionario(cpf,nome,senha);
            }
            return null;
        }
    }

    public Set<Funcionario> getFuncionarios() throws SQLException,ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM funcionario");
            Set<Funcionario> funcionarios = new HashSet<>();

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                String cpf = rs.getString(1);
                String nome = rs.getString(2);
                String senha = rs.getString(3);

                funcionarios.add(new Funcionario(cpf,nome,senha));
            }
            return funcionarios;
        }
    }

    public boolean deleteFuncionario(String cpf) throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM funcionario WHERE cpf = ?");
            pstm.setString(1,cpf);

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean atualizaFuncionario(String nome, String senha,String cpf) throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("UPDATE funcionario SET nome = ?,senha = ? WHERE cpf = ? ");
            pstm.setString(1,nome);
            pstm.setString(2,senha);
            pstm.setString(3,cpf);

            return pstm.executeUpdate() > 0;
        }
    }
}
