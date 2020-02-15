package com.app.budega.controller;

import com.app.budega.dao.ClienteDao;
import com.app.budega.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import util.MaskTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroClienteController implements Initializable {

    private ClienteDao clienteDao;

    @FXML
    private MaskTextField campoCpf;

    @FXML
    private TextField campoNome;

    @FXML
    private MaskTextField campoContato;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        campoCpf.setMask("NNN.NNN.NNN-NN");
        campoContato.setMask("SNNSNNNNN-NNNN");
    }

    public void acaoCadastrar(ActionEvent actionEvent){
        if(campoContato.getText().equals("") || campoCpf.getText().equals("") || campoNome.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Cliente");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        } else{

            clienteDao = new ClienteDao();

            String nome = campoNome.getText();
            String contato = campoContato.getText();
            String cpf = campoCpf.getText();

            Cliente cliente = new Cliente(cpf, nome, contato, 0);

            try{
                if (clienteDao.salvar(cliente)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cadastro de Cliente");
                    alert.setHeaderText("Cliente cadastrado com sucesso.");
                    alert.showAndWait();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cadastro de Cliente");
                    alert.setHeaderText("Erro ao cadastrar.");
                    alert.setContentText("Por favor tente novamente.");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Cliente");
                alert.setHeaderText("Erro ao cadastrar");
                alert.setContentText("Cliente já cadastrado");
                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Cliente");
                alert.setHeaderText("Erro ao cadastrar");
                alert.setContentText("Erro desconhecido");
                alert.showAndWait();
            }
        }
    }

}
