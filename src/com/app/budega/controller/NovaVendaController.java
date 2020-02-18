package com.app.budega.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.MouseEvent;

public class NovaVendaController {

    @FXML
    private TextField campoCodBarras;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private ComboBox<?> comboBoxFiado;

    @FXML
    private Button btnVender;

    @FXML
    private Label btnCancelar;

    @FXML
    void acaoCancelar(MouseEvent event) {

    }

    @FXML
    void acaoVender(ActionEvent event) {

    }

    public void acaoCancelar(javafx.scene.input.MouseEvent mouseEvent) {
    }
}
