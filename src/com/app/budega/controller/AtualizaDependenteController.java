package com.app.budega.controller;

import com.app.budega.App.Main;
import com.app.budega.conexao.Conexao;
import com.app.budega.dao.DependenteDAO;
import com.app.budega.model.Dependente;
import com.app.budega.model.Parentesco;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class AtualizaDependenteController implements Initializable {

    DependenteDAO dependenteDAO;

    private static Dependente dependenteRetornado;

    @FXML
    private TextField CampoNome;

    @FXML
    private ComboBox<String> cbResponsavel;

    @FXML
    private ComboBox<Parentesco> cbParentesco;

    @FXML
    private ToggleGroup gpPermissao;

    @FXML
    private Label labelID;

    private List<String> responsavel = new ArrayList<>();
    private ObservableList<String> responsavelOBS;

    private List<Parentesco> parentescos = new ArrayList<>();
    private ObservableList<Parentesco> parentescoOBS;

    Boolean permissao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setaDependente();
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

    public void yes(ActionEvent actionEvent){
        permissao = true;
    }

    public void no(ActionEvent actionEvent){
        permissao = false;
    }


    public void carregarResponsavel() throws SQLException, ClassNotFoundException {
        Conexao conexao = new Conexao();
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT cpf FROM cliente");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("cpf");

                responsavel.add(cpf);

                responsavelOBS = observableArrayList(responsavel);

                cbResponsavel.setItems(responsavelOBS);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
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

    @FXML
    void acaoAtualizar(ActionEvent event){
        if(CampoNome.getText().equals("") ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atualização de Dependente");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        }else {
            DependenteDAO dependenteDAO = new DependenteDAO();

            String responsavel = String.valueOf(cbResponsavel.getValue());
            String nome = CampoNome.getText();
            String parentesco = String.valueOf(cbParentesco.getValue());

            String id = dependenteRetornado.getId();
            try {
                Dependente dependente = new Dependente(id, responsavel, nome, parentesco, permissao);
                dependenteDAO.atualizar(dependente);
                if(dependenteDAO.atualizar(dependente) == true){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Atualização de Funcionario");
                    alert.setHeaderText("Dados atualizados com sucesso.");
                    alert.setContentText(labelID.getText());

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

    public void setaDependente(){
        labelID.setText(dependenteRetornado.getId());
        CampoNome.setText(dependenteRetornado.getNome());
        cbResponsavel.setValue(dependenteRetornado.getResponsavel());

    }

    public static Dependente getDependenteRetornado(){
        return dependenteRetornado;
    }

    public static void setDependenteRetornado(Dependente dependenteRetornado){
        AtualizaDependenteController.dependenteRetornado = dependenteRetornado;
    }

}
