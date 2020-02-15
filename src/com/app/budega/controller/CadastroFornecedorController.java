package com.app.budega.controller;

import com.app.budega.dao.FornecedorDAO;
import com.app.budega.model.Fornecedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import util.MaskTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroFornecedorController implements Initializable {


    private FornecedorDAO fornecedorDAO;

    @FXML
    private MaskTextField campoCnpj;

    @FXML
    private TextField campoNome;

    @FXML
    private MaskTextField campoContato;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Label btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        campoCnpj.setMask("NNNNNNNNNNNNNN");
        campoContato.setMask("SNNSNNNN-NNNN");
    }

    public void acaoCadastrar(ActionEvent actionEvent) {
        if(campoCnpj.getText().equals("") || campoNome.getText().equals("") || campoNome.getText().equals("")||campoContato.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Fornecedor");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");
            alert.showAndWait();
        }else {

            fornecedorDAO = new FornecedorDAO();

            String cnpj = campoCnpj.getText();
            String nome = campoNome.getText();
            String contato = campoContato.getText();

            Fornecedor fornecedor = new Fornecedor(cnpj, nome, contato);

            try {
                if (fornecedorDAO.salvar(fornecedor)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cadastro de Fornecedor");
                    alert.setHeaderText("Cadastrado com sucesso.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cadastro de Fornecedor");
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
