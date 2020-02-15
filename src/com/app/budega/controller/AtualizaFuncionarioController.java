package com.app.budega.controller;

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

    @Override
    public void initialize(URL url , ResourceBundle rs){

    }

    @FXML
    void acaoAtualizar(ActionEvent event) {

    }

}