package com.app.budega.controller;

import com.app.budega.dao.AbateDividaDao;
import com.app.budega.dao.ClienteDao;
import com.app.budega.model.AbateDivida;
import com.app.budega.model.Cliente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import static javafx.collections.FXCollections.observableArrayList;

public class CadastroAbateDividaController implements Initializable {

    private static String funcionario;

    @FXML
    private TextField campoValor;

    @FXML
    private ComboBox<String> cbCliente;

    @FXML
    private Button btnAbater;

    @FXML
    private Label btnCancelar;

    private ObservableList<String> clientesOBS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            carregaCliente();
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Abate de Divida");
            alert.setHeaderText("Houve um erro ao carregar os clientes cadastrados");
            alert.setContentText("Tente novamente.");
            alert.showAndWait();
        }

    }

    public void carregaCliente() throws SQLException, ClassNotFoundException {
        ClienteDao clienteDao = new ClienteDao();
        Set<Cliente> clientes = clienteDao.getClientes();

        List<String> resultado = new ArrayList<>();
        for (Cliente cliente : clientes){
            resultado.add(cliente.getCpf());
        }

        clientesOBS = observableArrayList(resultado);
        cbCliente.setItems(clientesOBS);
    }

    public void acaoAbater(ActionEvent actionEvent){
        if(campoValor.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Abatimento de Divida");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");
            alert.showAndWait();
        } else if(Float.parseFloat(campoValor.getText())<=0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Abatimento de Divida");
            alert.setHeaderText("Valor abatido não pode ser negativo");
            alert.setContentText("Verifique os campos e tente novamente.");
            alert.showAndWait();
        } else{

            AbateDividaDao abateDividaDao = new AbateDividaDao();

            float valor = Float.parseFloat(campoValor.getText());
            String cliente = String.valueOf(cbCliente.getValue());


            AbateDivida abateDivida = new AbateDivida("0", cliente, null, valor);

            try {
                if (abateDividaDao.salvar(abateDivida)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Abatimento de Dívida");
                    alert.setHeaderText("Dívida abatida com sucesso em "+ abateDivida.getValor()+" reais");
                    alert.showAndWait();

                    Stage stageCancelar = (Stage) btnAbater.getScene().getWindow();
                    stageCancelar.close();
                }
            } catch (SQLException | ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Abatimento de Dívida");
                alert.setHeaderText("Erro desconhecido ao abater a dívida");
                alert.showAndWait();
            }
        }
    }

    public static void setFuncionario(String funcionario) {
        CadastroAbateDividaController.funcionario = funcionario;
    }

    public void acaoCancelar(MouseEvent mouseEvent) {
        Stage stageCancelar = (Stage) btnCancelar.getScene().getWindow();
        stageCancelar.close();
    }
}
