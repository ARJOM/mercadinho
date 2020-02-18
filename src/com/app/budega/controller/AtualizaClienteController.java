package com.app.budega.controller;

import com.app.budega.dao.ClienteDao;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Cliente;
import com.app.budega.model.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.MaskTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AtualizaClienteController implements Initializable {

    private static Cliente clienteRetornado;

    private ClienteDao clienteDao;

    @FXML
    private Label labelCpf;

    @FXML
    private Button btnAtualizarCliente;

    @FXML
    private TextField campoNome;

    @FXML
    private MaskTextField campoContato;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        campoContato.setMask("SNNS*N*NNNN-NNNN");
        setaCliente();
    }

    @FXML
    void acaoAtualizar(ActionEvent event){
        if(campoNome.getText().equals("") || campoContato.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atualização de Funcionario");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        }else{
            ClienteDao clienteDao = new ClienteDao();
            String nome = campoNome.getText();
            String contato = campoContato.getText();
            try {
                clienteDao.atualizar(clienteRetornado.getCpf(), nome, contato);
                if(clienteDao.atualizar(clienteRetornado.getCpf(), nome, contato) == true){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Atualização de Funcionario");
                    alert.setHeaderText("Dados atualizados com sucesso.");
                    alert.setContentText(labelCpf.getText());

                    Optional<ButtonType> resultado = alert.showAndWait();
                    if (resultado.get() == ButtonType.OK){
                        Stage stage = (Stage) btnAtualizarCliente.getScene().getWindow();
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

    public void setaCliente(){
        labelCpf.setText(clienteRetornado.getCpf());
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

