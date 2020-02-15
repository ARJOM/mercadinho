package com.app.budega.controller;

import com.app.budega.App.Main;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AtualizaFuncionarioController implements Initializable {

    private static Funcionario funcionarioRetornado;

    @FXML
    private TextField campoNome;

    @FXML
    private Button btnAtualizaFuncionario;

    @FXML
    private Label btnCancelar;

    @FXML
    private PasswordField campoSenha1;

    @FXML
    private PasswordField campoSenha2;

    @FXML
    private Label labelCPF;


    @Override
    public void initialize(URL url , ResourceBundle rs){
        setaFuncionario();
    }

    @FXML
    void acaoAtualizar(ActionEvent event){
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        String nome = campoNome.getText();
        String senha = campoSenha1.getText();
        try {
            funcionarioDAO.atualizaFuncionario(nome,senha,funcionarioRetornado.getCpf());
            ListarFuncionarioController listarFuncionarioController = new ListarFuncionarioController();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setaFuncionario(){
        labelCPF.setText(funcionarioRetornado.getCpf());
        campoNome.setText(funcionarioRetornado.getNome());
        campoSenha1.setText(funcionarioRetornado.getSenha());
        campoSenha2.setText(funcionarioRetornado.getSenha());

    }

    public static Funcionario getFuncionarioRetornado(){
        return funcionarioRetornado;
    }

    public static void setFuncionarioRetornado(Funcionario funcionarioRetornado){
        AtualizaFuncionarioController.funcionarioRetornado = funcionarioRetornado;
    }
}