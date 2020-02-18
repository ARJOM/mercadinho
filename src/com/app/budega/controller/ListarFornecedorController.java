package com.app.budega.controller;

import com.app.budega.App.AtualizarClienteMain;
import com.app.budega.App.AtualizarFornecedorMain;
import com.app.budega.dao.FornecedorDAO;
import com.app.budega.model.Cliente;
import com.app.budega.model.Fornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.MaskTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ListarFornecedorController implements Initializable {

    @FXML
    private TableView<Fornecedor> tabelaFornecedor;

    @FXML
    private TableColumn<Fornecedor, String > colunaCnpj;

    @FXML
    private TableColumn<Fornecedor, String> colunaNome;

    @FXML
    private TableColumn<Fornecedor, String> colunaContato;

    @FXML
    private MaskTextField campoBuscarCNPJ;

    @Override
    public void initialize(URL url, ResourceBundle rs){
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                try {
                    initTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        },0,5000);

        campoBuscarCNPJ.setMask("NNNNNNNNNNNNNN");
    }

    FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public void initTable() throws SQLException, ClassNotFoundException {
        colunaCnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaContato.setCellValueFactory(new PropertyValueFactory("telefone"));
        tabelaFornecedor.setItems(atualizaTable());
    }

    public ObservableList<Fornecedor> atualizaTable() throws SQLException, ClassNotFoundException {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        return FXCollections.observableArrayList(fornecedorDAO.getFornecedor());
    }

    @FXML
    void deleteFornecedor(ActionEvent event) {
        try{
            Fornecedor fornecedor = tabelaFornecedor.getSelectionModel().getSelectedItem();
            String cnpj = fornecedor.getCnpj();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deletar Cliente");
                alert.setHeaderText("Deseja realmente excluir ?");
                alert.setContentText(fornecedor.getNome());

                Optional<ButtonType> resultado = alert.showAndWait();
                if (resultado.get() == ButtonType.OK){
                    fornecedorDAO.deletar(cnpj);
                    initTable();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Fornecedor");
            alert.setHeaderText("Nenhum Fornecedor selecionado.");
            alert.setContentText("Selecione uma linha para excluir.");
            alert.show();
        }
    }

    @FXML
    void atualizarFornecedor(ActionEvent event){
        Fornecedor fornecedor = tabelaFornecedor.getSelectionModel().getSelectedItem();
        if(fornecedor != null){
            AtualizarFornecedorMain atualizarFornecedorMain = new AtualizarFornecedorMain(fornecedor);
            try {
                atualizarFornecedorMain.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Fornecedor");
            alert.setHeaderText("Nenhum Fornecedor selecionado.");
            alert.setContentText("Selecione uma linha para atualizar.");
            alert.show();

        }

    }

    public void buscarCNPJ(ActionEvent event) {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        String cnpj = campoBuscarCNPJ.getText();

        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fornecedor");
            alert.setHeaderText("Nome: "+ fornecedorDAO.buscarPorCnpj(cnpj).getNome()+
                    "\n"+ "Telefone: " + fornecedorDAO.buscarPorCnpj(cnpj).getTelefone());
            alert.setContentText("Busca concluida.");
            alert.show();

            campoBuscarCNPJ.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Buscar Fornecedor");
            alert.setHeaderText("O campo de busca não pode estar vazio.");
            alert.setContentText("Verifique o campo e tente novamente.");
            alert.show();
        }

    }
}
