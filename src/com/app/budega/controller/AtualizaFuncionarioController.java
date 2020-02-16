package com.app.budega.controller;

import com.app.budega.App.AtualizarFuncionarioMain;
import com.app.budega.App.Main;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
        if(campoNome.getText().equals("") || campoSenha1.getText().equals("")||campoSenha2.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atualização de Funcionario");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        }else if(!campoSenha2.getText().equals(campoSenha1.getText()) ){
            campoSenha1.setStyle("-fx-border-color: #f44336");
            campoSenha2.setStyle("-fx-border-color: #f44336");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atualização de Funcionario");
            alert.setHeaderText("As senhas não batem.");
            alert.setContentText("Verifique as senhas e tente novamente.");
            alert.showAndWait();

            campoSenha1.setStyle("-fx-border-color: #BDBDBD");
            campoSenha2.setStyle("-fx-border-color: #BDBDBD");
        }else {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            String nome = campoNome.getText();
            String senha = campoSenha1.getText();
            try {
                funcionarioDAO.atualizaFuncionario(nome, senha, funcionarioRetornado.getCpf());
                if(funcionarioDAO.atualizaFuncionario(nome, senha, funcionarioRetornado.getCpf()) == true){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.setTitle("Atualização de Funcionario");
                   alert.setHeaderText("Dados atualizados com sucesso.");
                   alert.setContentText(labelCPF.getText());

                    Optional <ButtonType> resultado = alert.showAndWait();
                       if (resultado.get() == ButtonType.OK){
                           Stage stage = (Stage) btnAtualizaFuncionario.getScene().getWindow();
                           stage.close();
                       }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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