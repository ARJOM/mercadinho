package com.app.budega.controller;

import com.app.budega.dao.*;
import com.app.budega.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import static javafx.collections.FXCollections.observableArrayList;

public class NovaVendaController implements Initializable {

    private static String venda;

    @FXML
    private TextField campoCodBarras;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private ComboBox<String> comboBoxFiado;

    @FXML
    private ComboBox<String> ComboBoxFiado1;

    @FXML
    private Button btnVender;

    @FXML
    private Label btnCancelar;

    private ObservableList<String> clientesOBS;
    private ObservableList<String> dependentesOBS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            carregaCliente();
            carregaDependente();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void acaoCancelar(MouseEvent event) {

    }

    @FXML
    void acaoAdicionar(ActionEvent event) {
        if(campoCodBarras.getText().equals("") || campoQuantidade.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Novo Item");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Verifique os campos e tente novamente.");
            alert.showAndWait();
        } else{
            try {
                ItemVendaDao itemVendaDao = new ItemVendaDao();
                ProdutoDAO produtoDAO = new ProdutoDAO();

                String codBarras = campoCodBarras.getText();
                int quantidade = Integer.parseInt(campoQuantidade.getText());

                Produto produto = produtoDAO.buscarPorCodBarras(codBarras);
                float preco = produto.getPreco();
                System.out.println(comboBoxFiado.getValue());

                ItemVenda itemVenda = new ItemVenda("0", quantidade, preco, codBarras, venda);

                if(itemVendaDao.salvar(itemVenda)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Novo item");
                    alert.setHeaderText(produto.getNome()+" adicionado a venda");
                    alert.setContentText("Feche para encerrar a venda.");
                    alert.showAndWait();
                    campoQuantidade.setText("");
                    campoCodBarras.setText("");
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Nova Venda");
                    alert.setHeaderText("Preencha todos os campos");
                    alert.setContentText("Verifique os campos e tente novamente.");
                    alert.showAndWait();
                }


            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nova Venda");
                alert.setHeaderText("Erro no banco");
                alert.setContentText("Erro no banco");
                alert.showAndWait();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public void acaoEncerrar(ActionEvent actionEvent){
        if(comboBoxFiado.getValue()!=null) {
            VendaFiadoDao vendaFiadoDao = new VendaFiadoDao();
            String cliente = comboBoxFiado.getValue();
            String dependente = ComboBoxFiado1.getValue();
            VendaFiado vendaFiado = new VendaFiado(venda, cliente, dependente);
            try {
                if (vendaFiadoDao.salvar(vendaFiado)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Nova Venda");
                    alert.setHeaderText("Venda associada ao cliente" + cliente);
                    alert.showAndWait();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void acaoCancelar(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public static void setVenda(String venda){
        NovaVendaController.venda = venda;
    }

    public void carregaCliente() throws SQLException, ClassNotFoundException {
        ClienteDao clienteDao = new ClienteDao();
        Set<Cliente> clientes = clienteDao.getClientes();

        List<String> resultado = new ArrayList<>();
        for (Cliente cliente : clientes){
            resultado.add(cliente.getCpf());
        }

        clientesOBS = observableArrayList(resultado);
        comboBoxFiado.setItems(clientesOBS);
    }

    public void carregaDependente() throws SQLException, ClassNotFoundException {
        DependenteDAO dependenteDAO = new DependenteDAO();
        Set<Dependente> dependentes = dependenteDAO.getDependentes();

        List<String> resultado = new ArrayList<>();
        for (Dependente dependente : dependentes){
            resultado.add(dependente.getId());
        }

        dependentesOBS = observableArrayList(resultado);
        ComboBoxFiado1.setItems(dependentesOBS);
    }


}
