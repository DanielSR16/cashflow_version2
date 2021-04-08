package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import application.Main;
public class RootController {

    @FXML
    void categoriasClicked(MouseEvent event) {
        Main.newStage("categoriaView","Categorias");
    }

    @FXML
    void flujoClicked(MouseEvent event) {
        Main.newStage("flujoView","Flujo de efectivo");

    }

    @FXML
    void indicadoresClicked(MouseEvent event) {

    }

    @FXML
    void reporteClicked(MouseEvent event) {

    }

}
