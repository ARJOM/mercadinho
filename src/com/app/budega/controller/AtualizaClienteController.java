package com.app.budega.controller;

import com.app.budega.App.Main;
import com.app.budega.dao.ClienteDao;
import com.app.budega.model.Cliente;
import com.app.budega.model.Dependente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.MaskTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AtualizaClienteController implements Initializable {

    private ClienteDao clienteDao;

    private static Cliente clienteRetornado;

    @FXML
    private Label labelCPF;

    @FXML
    private TextField campoNome;

    @FXML
    private MaskTextField campoContato;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        campoContato.setMask("SNNSNNNNN-NNNN");
        setaCliente();
    }

    @FXML
    void acaoAtualizar(ActionEvent event){
        if(campoNome.getText().equals("") ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atualização de Cliente");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        }else {
            ClienteDao clienteDao = new ClienteDao();

            String cpf = clienteRetornado.getCpf();
            String nome = campoNome.getText();
            String contato = campoContato.getText();
            float divida = clienteRetornado.getDivida();

            try {
                Cliente cliente = new Cliente(cpf, nome, contato, divida);
                clienteDao.atualizar(cliente);
                if(clienteDao.atualizar(cliente) == true){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Atualização de Cliente");
                    alert.setHeaderText("Dados atualizados com sucesso.");
                    alert.setContentText(labelCPF.getText());

                    Optional<ButtonType> resultado = alert.showAndWait();
                    if (resultado.get() == ButtonType.OK){
                        Main main = new Main();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setaCliente(){
        labelCPF.setText(clienteRetornado.getCpf());
        campoNome.setText(clienteRetornado.getNome());
        campoContato.setText(clienteRetornado.getContato());
    }

    public static Cliente getClienteRetornado(){
        return clienteRetornado;
    }

    public static void setClienteRetornado(Cliente clienteRetornado){
        AtualizaClienteController.clienteRetornado = clienteRetornado;
    }
}
