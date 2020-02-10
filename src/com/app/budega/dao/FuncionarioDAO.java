package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
