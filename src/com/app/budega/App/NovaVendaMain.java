package com.app.budega.App;

import com.app.budega.controller.AtualizaClienteController;
import com.app.budega.controller.NovaVendaController;
import com.app.budega.model.Cliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NovaVendaMain  extends Application {

    public NovaVendaMain(String id){
        NovaVendaController.setVenda(id);
    }

    public static void main(String [] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/NovaVenda.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Budega App");
        stage.setScene(scene);
        stage.show();
    }
}
