package com.app.budega.controller;

import com.app.budega.App.AtualizarProdutoMain;
import com.app.budega.App.LogarFuncionarioMain;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Funcionario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.MaskTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private MaskTextField campoCpf;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        campoCpf.setMask("NNN.NNN.NNN-NN");
    }

    public void acaoLogar(){
        if(campoCpf.getText().equals("") || campoSenha.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Funcionario");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        } else{
            try {
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

                String senha = campoSenha.getText();
                String cpf = campoCpf.getText();

                Funcionario funcionario = funcionarioDAO.buscarPorCpf(cpf);
                if(!funcionario.getSenha().equals(senha)){
                    campoSenha.setStyle("-fx-border-color: #f44336");

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login");
                    alert.setHeaderText("Senha incorreta");
                    alert.setContentText("Verifique a senha e tente novamente.");
                    alert.showAndWait();

                    campoSenha.setStyle("-fx-border-color: #BDBDBD");
                } else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login");
                    alert.setHeaderText("Funcionario autenticado.");
                    alert.setContentText("Bem vindo ao sistema.");
                    alert.showAndWait();
                    LogarFuncionarioMain logarFuncionarioMain = new LogarFuncionarioMain(cpf);
                    logarFuncionarioMain.start(new Stage());

                    Stage stageFecharLogin = (Stage) btnLogin.getScene().getWindow();
                    stageFecharLogin.close();
                }
            } catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login");
                alert.setHeaderText("Não existe nenhum funcionario cadastrado com esse CPF");
                alert.setContentText("Verifique o CPF e tente novamente");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login");
                alert.setHeaderText("Um erro desconhecido ocorreu.");
                alert.setContentText("Por favor tente novamente");
                alert.showAndWait();
            }

        }
    }

}