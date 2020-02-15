package com.app.budega.controller;

import com.app.budega.conexao.Conexao;
import com.app.budega.dao.DependenteDAO;
import com.app.budega.dao.FornecedorDAO;
import com.app.budega.model.Cliente;
import com.app.budega.model.Dependente;
import com.app.budega.model.Fornecedor;
import com.app.budega.model.Parentesco;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.sql.*;
import java.util.*;

import static javafx.collections.FXCollections.*;

public class CadastroDependenteController implements Initializable {

    DependenteDAO dependenteDAO;

    @FXML
    private TextField CampoNome;

    @FXML
    private ComboBox<String> cbResponsavel;

    @FXML
    private ComboBox<Parentesco> cbParentesco;

    @FXML
    private ToggleGroup gpPermissao;

    private List<String> responsavel = new ArrayList<>();
    private ObservableList<String> responsavelOBS;

    private List<Parentesco> parentescos = new ArrayList<>();
    private ObservableList<Parentesco> parentescoOBS;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarResponsavel();
            carregarParentesco();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void carregarResponsavel() throws SQLException, ClassNotFoundException {
        Conexao conexao = new Conexao();
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT cpf FROM cliente");
            Set<Cliente> clientes = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("cpf");

                responsavel.add(cpf);

                responsavelOBS = observableArrayList(responsavel);

                cbResponsavel.setItems(responsavelOBS);
            }
        }
    }

    public void carregarParentesco() {
        Parentesco filho = new Parentesco(1, "Filho(a)");
        Parentesco conjuge = new Parentesco(2, "Cônjuge");
        Parentesco enteado = new Parentesco(3, "Enteado(a)");

        parentescos.add(filho);
        parentescos.add(conjuge);
        parentescos.add(enteado);

        parentescoOBS = observableArrayList(parentescos);

        cbParentesco.setItems(parentescoOBS);
    }

    public void acaoCadastrar(ActionEvent actionEvent) {
        if (CampoNome.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Dependente");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");
            alert.showAndWait();
        } else {

            dependenteDAO = new DependenteDAO();

            String responsavel = String.valueOf(cbResponsavel.getItems());
            String nome = CampoNome.getText();
            String parentesco = String.valueOf(cbParentesco.getItems());
//            Boolean permissao = Boolean.valueOf(String.valueOf(gpPermissao.getSelectedToggle()));
            System.out.println(gpPermissao.getSelectedToggle().selectedProperty().get());

            Dependente dependente = new Dependente("0", responsavel, nome, parentesco, true);

            try {
                if (dependenteDAO.salvar(dependente)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cadastro de Dependente");
                    alert.setHeaderText("Cadastrado com sucesso.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cadastro de Dependente");
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