package com.app.budega.controller;

import com.app.budega.dao.ClienteDao;
import com.app.budega.dao.FornecedorDAO;
import com.app.budega.model.Cliente;
import com.app.budega.model.Fornecedor;
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

public class AtualizaFornecedorController implements Initializable {

    private FornecedorDAO fornecedorDAO;
    private static Fornecedor fornecedorRetornado;

    @FXML
    private Label labelCnpj;

    @FXML
    private TextField campoNome;

    @FXML
    private MaskTextField campoContato;

    @FXML
    private Button btnAtualizarFornecedor;

    @FXML
    private Label btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setaFornecedor();
    }

    @FXML
    void acaoAtualizar(ActionEvent event){
        if(campoNome.getText().equals("") || campoContato.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atualização de Fornecedor");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        }else{
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            String nome = campoNome.getText();
            String contato = campoContato.getText();
            try {
                fornecedorDAO.atualizar(fornecedorRetornado.getCnpj(), nome, contato);
                if(fornecedorDAO.atualizar(fornecedorRetornado.getCnpj(), nome, contato) == true){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Atualização de Funcionario");
                    alert.setHeaderText("Dados atualizados com sucesso.");
                    alert.setContentText(labelCnpj.getText());

                    Optional<ButtonType> resultado = alert.showAndWait();
                    if (resultado.get() == ButtonType.OK){
                        Stage stage = (Stage) btnAtualizarFornecedor.getScene().getWindow();
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

    public void setaFornecedor(){
        labelCnpj.setText(fornecedorRetornado.getCnpj());
        campoNome.setText(fornecedorRetornado.getNome());
        campoContato.setText(fornecedorRetornado.getTelefone());
    }

    public static Fornecedor getFornecedorRetornado(){
        return fornecedorRetornado;
    }

    public static void setFornecedorRetornado(Fornecedor fornecedorRetornado){
        AtualizaFornecedorController.fornecedorRetornado = fornecedorRetornado;
    }

}
