package com.app.budega.controller;

import com.app.budega.App.AtualizarClienteMain;
import com.app.budega.App.AtualizarDependenteMain;
import com.app.budega.dao.ClienteDao;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Cliente;
import com.app.budega.model.Dependente;
import com.app.budega.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.MaskTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ListarClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tabelaCliente;

    @FXML
    private TableColumn<Cliente, String> colunaCpf;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaContato;

    @FXML
    private TableColumn<Cliente, Float> colunaDivida;

    @FXML
    private MaskTextField campoBuscarCpf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        campoBuscarCpf.setMask("NNN.NNN.NNN-NN");
    }

    ClienteDao clienteDao = new ClienteDao();

    private void initTable() throws SQLException, ClassNotFoundException {
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaContato.setCellValueFactory(new PropertyValueFactory<>("contato"));
        colunaDivida.setCellValueFactory(new PropertyValueFactory<>("divida"));
        tabelaCliente.setItems(atualizaTable());
    }

    private ObservableList<Cliente> atualizaTable() throws SQLException, ClassNotFoundException {
        ClienteDao clienteDao = new ClienteDao();
        return FXCollections.observableArrayList(clienteDao.getClientes());
    }


    @FXML
    void deleteCliente(ActionEvent event) {
        try{
            Cliente cliente = tabelaCliente.getSelectionModel().getSelectedItem();
            String cpf = cliente.getCpf();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deletar Cliente");
                alert.setHeaderText("Deseja realmente excluir ?");
                alert.setContentText(cliente.getNome());

                Optional<ButtonType> resultado = alert.showAndWait();
                if (resultado.get() == ButtonType.OK){
                    clienteDao.deletar(cpf);
                    initTable();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Cliente");
            alert.setHeaderText("Nenhum Cliente selecionado.");
            alert.setContentText("Selecione uma linha para excluir.");
            alert.show();
        }
    }

    @FXML
    void atualizarCliente(ActionEvent event){
        Cliente cliente = tabelaCliente.getSelectionModel().getSelectedItem();
        if(cliente != null){
            AtualizarClienteMain atualizarClienteMain = new AtualizarClienteMain(cliente);
            try {
                atualizarClienteMain.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Cliente");
            alert.setHeaderText("Nenhum Cliente selecionado.");
            alert.setContentText("Selecione uma linha para atualizar.");
            alert.show();

        }

    }

    public void buscarPorCpf(ActionEvent event) {
        ClienteDao clienteDao = new ClienteDao();
        String cpf = campoBuscarCpf.getText();
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente");
            alert.setHeaderText("Nome: " + clienteDao.buscarPorCPF(cpf).getNome()+
                    "\n"+"Contato: " + clienteDao.buscarPorCPF(cpf).getContato()+
                    "\n"+"Divida: " + clienteDao.buscarPorCPF(cpf).getDivida());
            alert.setContentText("Busca concluida.");
            alert.show();

            campoBuscarCpf.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Buscar Cliente");
            alert.setHeaderText("O campo de busca não pode estar vazio.");
            alert.setContentText("Verifique o campo e tente novamente.");
            alert.show();
        }
    }
}
