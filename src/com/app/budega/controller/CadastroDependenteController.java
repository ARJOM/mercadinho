package com.app.budega.controller;

import com.app.budega.model.Parentesco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroDependenteController implements Initializable {

    @FXML
    private TextField jTextNomeDep;

    @FXML
    private ComboBox<?> cbResponsaveu;

    @FXML
    private ComboBox<Parentesco> cbParentesco;

    @FXML
    private ToggleGroup gpPermissao;

    private List<Parentesco> parentescos = new ArrayList<>();
    private ObservableList<Parentesco> parentescoOBS;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        carregarParentesco();
    }

    public void carregarParentesco(){
        Parentesco filho =  new Parentesco(1, "Filho(a)");
        Parentesco conjuge = new Parentesco(2,"Cônjuge");
        Parentesco enteado = new Parentesco(3,"Enteado(a)");

        parentescos.add(filho);
        parentescos.add(conjuge);
        parentescos.add(enteado);

        parentescoOBS = FXCollections.observableArrayList(parentescos);

        cbParentesco.setItems(parentescoOBS);
    }
}
