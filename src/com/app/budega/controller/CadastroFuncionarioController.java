package com.app.budega.controller;

import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Funcionario;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroFuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoNome;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Label btnCancelar;

    @FXML
    private PasswordField campoSenha1;

    @FXML
    private PasswordField campoSenha2;


    public CadastroFuncionarioController(){

    }

    public void acaoCadastrar(ActionEvent actionEvent) {
        funcionarioDAO = new FuncionarioDAO();

        String cpf = campoCpf.getText();
        String nome = campoNome.getText();
        String senha = campoSenha1.getText();

        Funcionario funcionario = new Funcionario(cpf,nome,senha);

        try {
            if(funcionarioDAO.Cadastrar(funcionario)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro de Funcionario");
                alert.setHeaderText("Cadastrado com sucesso.");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Funcionario");
                alert.setHeaderText("Cadastrado com sucesso.");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
