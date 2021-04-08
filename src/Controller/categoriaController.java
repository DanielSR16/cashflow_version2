package Controller;

import DAO.CategoriaDAO;
import DAO.ClasificacionDAO;
import Entities.Categoria;
import Entities.Clasificacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class categoriaController implements Initializable {
    ClasificacionDAO clasificacionDAO  = new ClasificacionDAO();
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    Categoria categoria = new Categoria();


    @FXML
    private ComboBox<String> comboBoxClasificacion;

    @FXML
    private TextField textFliedCategoria;

    @FXML
    private TextField textFieldSubCategoria;

    @FXML
    private TableView<Categoria> tabla;

    @FXML
    private TableColumn<Categoria, String> calsificacionColum;

    @FXML
    private TableColumn<Categoria, String> categoriaColum;

    @FXML
    private TableColumn<Categoria, String> subcategoriaColum;

    @FXML
    void guardarClicked(MouseEvent event) {





        tabla.getItems().clear();

        ObservableList<Categoria> cat =  FXCollections.observableArrayList();
        calsificacionColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombre"));
        categoriaColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombreClasificacion"));
        subcategoriaColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("idSubCategoria"));
        cat.addAll(categoriaDAO.getAllCategoria());
        tabla.setItems(cat);

        textFliedCategoria.clear();
        comboBoxClasificacion.getSelectionModel().clearSelection();
        textFieldSubCategoria.clear();

    }

    @FXML
    void houseClicked(MouseEvent event) {

    }
    @FXML
    void seleccionarClicked(MouseEvent event) {

        System.out.println(tabla.getSelectionModel().getSelectedItem().getSubcategorias().getSelectionModel().getSelectedItem());
//        if(tabla.getSelectionModel().isEmpty()){
//            System.out.println("no seleccioneeeee");
//            tabla.getSelectionModel().clearSelection();
//        }else{
//            comboBoxClasificacion.setValue(tabla.getSelectionModel().getSelectedItem().getNombre());
//
//        }



//        System.out.println( tabla.getSelectionModel().getSelectedItem().getId());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //llenado del comboBox
        ObservableList<String> clasificacionesView = FXCollections.observableArrayList();
        for (int i = 0; i < clasificacionDAO.getAllClasificacion().size(); i++) {
            clasificacionesView.addAll(clasificacionDAO.getAllClasificacion().get(i).getNombre());
        }
        comboBoxClasificacion.setItems(clasificacionesView);

        ObservableList<Categoria> cat =  FXCollections.observableArrayList();

        calsificacionColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombre"));
        categoriaColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombreClasificacion"));
        subcategoriaColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("subcategorias"));
        cat.addAll(categoriaDAO.getAllCategoria());
        tabla.setItems(cat);

        System.out.println(categoriaDAO.getAllCategoria());

    }

}