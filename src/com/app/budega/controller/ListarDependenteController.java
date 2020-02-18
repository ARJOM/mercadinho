package com.app.budega.controller;

import com.app.budega.App.AtualizarDependenteMain;
import com.app.budega.App.AtualizarFuncionarioMain;
import com.app.budega.dao.ClienteDao;
import com.app.budega.dao.DependenteDAO;
import com.app.budega.model.Dependente;
import com.app.budega.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ListarDependenteController implements Initializable {

    @FXML
    private TableView<Dependente> tabelaDependente;

    @FXML
    private TableColumn<Dependente, String> colunaId;

    @FXML
    private TableColumn<Dependente, String> colunaResponsavel;

    @FXML
    private TableColumn<Dependente, String> colunaNome;

    @FXML
    private TableColumn<Dependente, String> colunaParentesco;

    @FXML
    private TableColumn<Dependente, Boolean> colunaPermissao;

    @FXML
    private TextField campoBuscarID;

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
    }

    DependenteDAO dependenteDAO = new DependenteDAO();

    private void initTable() throws SQLException, ClassNotFoundException {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaParentesco.setCellValueFactory(new PropertyValueFactory<>("parentesco"));
        colunaPermissao.setCellValueFactory(new PropertyValueFactory<>("permissao"));
        tabelaDependente.setItems(atualizaTable());
    }

    private ObservableList<Dependente> atualizaTable() throws SQLException, ClassNotFoundException {
        DependenteDAO dependenteDAO = new DependenteDAO();
        return FXCollections.observableArrayList(dependenteDAO.getDependentes());
    }

    @FXML
    void deleteDependente(ActionEvent event) {
        try{
            Dependente dependente = tabelaDependente.getSelectionModel().getSelectedItem();
            String id = dependente.getId();
            System.out.println(id);
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deletar Funcionarios");
                alert.setHeaderText("Deseja realmente excluir ?");
                alert.setContentText(dependente.getNome());

                Optional<ButtonType> resultado = alert.showAndWait();
                if (resultado.get() == ButtonType.OK){
                    dependenteDAO.deleteDependente(id);
                    initTable();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Funcionario");
            alert.setHeaderText("Nenhum funcionario selecionado.");
            alert.setContentText("Selecione uma linha para excluir.");
            alert.show();
        }
    }

    @FXML
    void atualizarDependente(ActionEvent event){
        Dependente dependente = tabelaDependente.getSelectionModel().getSelectedItem();
        if(dependente != null){
            AtualizarDependenteMain atualizarDependenteMain = new AtualizarDependenteMain(dependente);
            try {
                atualizarDependenteMain.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Dependente");
            alert.setHeaderText("Nenhum Dependente selecionado.");
            alert.setContentText("Selecione uma linha para atualizar.");
            alert.show();

        }

    }

    public void buscarPorID(ActionEvent event) {
        DependenteDAO dependenteDAO = new DependenteDAO();
        ClienteDao clienteDao = new ClienteDao();
        String id = campoBuscarID.getText();
        try {
            if (dependenteDAO.buscarPorId(id).getPermissao() == true){
                String cpfCliente = dependenteDAO.buscarPorId(id).getResponsavel();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dependente");
                alert.setHeaderText("Nome: "+ dependenteDAO.buscarPorId(id).getNome()+
                        "\n"+"Responsavel: "+ clienteDao.buscarPorCPF(cpfCliente).getNome()+
                        "\n" + "Parentesco: " + dependenteDAO.buscarPorId(id).getParentesco()+
                        "\n" + "Permissão: Permitido");
                alert.setContentText("Busca concluida.");
                alert.show();
            }else{
                String cpfCliente = dependenteDAO.buscarPorId(id).getResponsavel();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dependente");
                alert.setHeaderText("Nome: "+ dependenteDAO.buscarPorId(id).getNome()+
                        "\n"+"Responsavel: "+ clienteDao.buscarPorCPF(cpfCliente).getNome()+
                        "\n" + "Parentesco: " + dependenteDAO.buscarPorId(id).getParentesco()+
                        "\n" + "Permissão: Não Permitido");
                alert.setContentText("Busca concluida.");
                alert.show();
            }
            campoBuscarID.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Buscar Dependente");
            alert.setHeaderText("O campo de busca não pode estar vazio.");
            alert.setContentText("Verifique o campo e tente novamente.");
            alert.show();
        }
    }
}
