package com.app.budega.App;

import com.app.budega.controller.AtualizaClienteController;
import com.app.budega.controller.AtualizaFornecedorController;
import com.app.budega.model.Cliente;
import com.app.budega.model.Fornecedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AtualizarFornecedorMain extends Application {

    public AtualizarFornecedorMain(Fornecedor fornecedorSetado) {
        AtualizaFornecedorController.setFornecedorRetornado(fornecedorSetado);
    }

    public static void main(String [] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/AtualizaFornecedor.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Budega App");
        stage.setScene(scene);
        stage.show();
    }

}
