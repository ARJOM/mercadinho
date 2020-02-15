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

import javafx.scene.layout.StackPane;
import util.MaskTextField;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.SimpleFormatter;


public class CadastroFuncionarioController implements Initializable {

    private FuncionarioDAO funcionarioDAO;

    @FXML
    private MaskTextField campoCpf;

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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        campoCpf.setMask("NNN.NNN.NNN-NN");
    }



    public void acaoCadastrar(ActionEvent actionEvent) {
        if(campoCpf.getText().equals("") || campoNome.getText().equals("") || campoSenha1.getText().equals("")||campoSenha2.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Funcionario");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        }else if(!campoSenha2.getText().equals(campoSenha1.getText()) ){
            campoSenha1.setStyle("-fx-border-color: #f44336");
            campoSenha2.setStyle("-fx-border-color: #f44336");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro de Funcionario");
            alert.setHeaderText("As senhas não batem.");
            alert.setContentText("Verifique as senhas e tente novamente.");
            alert.showAndWait();

            campoSenha1.setStyle("-fx-border-color: #BDBDBD");
            campoSenha2.setStyle("-fx-border-color: #BDBDBD");
        }else {

            funcionarioDAO = new FuncionarioDAO();

            String cpf = campoCpf.getText();
            String nome = campoNome.getText();
            String senha = campoSenha1.getText();

            Funcionario funcionario = new Funcionario(cpf, nome, senha);

            try {
                if (funcionarioDAO.Cadastrar(funcionario)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cadastro de Funcionario");
                    alert.setHeaderText("Cadastrado com sucesso.");
                    alert.setContentText("Bem Vindo " + campoNome.getText() + ".");
                    alert.showAndWait();

                    campoCpf.setText("");
                    campoNome.setText("");
                    campoSenha1.setText("");
                    campoSenha2.setText("");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cadastro de Funcionario");
                    alert.setHeaderText("Erro ao Cadastrar.");
                    alert.setContentText("Por favor tente novamente.");
                    alert.showAndWait();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
