package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class flujoController {

    @FXML
    private TableView<?> tabla;

    @FXML
    private TableColumn<?, ?> fechaColum;

    @FXML
    private TableColumn<?, ?> descripcionColum;

    @FXML
    private TableColumn<?, ?> categoriaColum;

    @FXML
    private TableColumn<?, ?> subcategoriaColum;

    @FXML
    private CheckBox checkBoxEntrada;

    @FXML
    private CheckBox checkBoxSalida;

    @FXML
    private ComboBox<?> comboBoxCategoria;

    @FXML
    private TextField textFieldDescripcion;

    @FXML
    private TextField textFieldMonto;

    @FXML
    private ComboBox<?> comboBoxSubCategoria1;

    @FXML
    void guardarClicked(MouseEvent event) {

    }

    @FXML
    void houseClicked(MouseEvent event) {

    }

}