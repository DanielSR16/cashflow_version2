package Controller;

import DAO.CategoriaDAO;
import DAO.ClasificacionDAO;
import DAO.SubCategoriaDAO;
import Entities.Categoria;
import Entities.Clasificacion;
import Entities.SubCategoria;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class categoriaController implements Initializable {
    ClasificacionDAO clasificacionDAO  = new ClasificacionDAO();
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
    private boolean band = false;


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
    private Label labelError;

    @FXML
    void guardarClicked(MouseEvent event) {


        if(tabla.getSelectionModel().isEmpty()){

            labelError.setText("Selecciona la fila de la tabla");


        }else{
            labelError.setText("");
            if(band == false){

                Categoria categoria = new Categoria(textFliedCategoria.getText(),comboBoxClasificacion.getSelectionModel().getSelectedIndex()+1);
                categoriaDAO.insert(categoria);
                SubCategoria subCategoria = new SubCategoria(textFieldSubCategoria.getText(),categoriaDAO.getIdCategoria(textFliedCategoria.getText()));
                subCategoriaDAO.insert(subCategoria);

            } else {


                if(tabla.getSelectionModel().getSelectedItem().getSubcategorias().getSelectionModel().isEmpty() && textFieldSubCategoria.getLength() > 0){
                    System.out.println("ENTRE A SUBCATEGORIA");
                    SubCategoria subCategoria = new SubCategoria(textFieldSubCategoria.getText(),tabla.getSelectionModel().getSelectedItem().getId());
                    subCategoriaDAO.insert(subCategoria);



                }
                if(tabla.getSelectionModel().getSelectedItem().getSubcategorias().getSelectionModel().isEmpty() == false){

                    //PRIMERO COMPARAMOS LAS NOMBRE DE LA CATEGORIAS TABLA -> TEXTFLIELD

                    if(tabla.getSelectionModel().getSelectedItem().getNombre().compareTo(textFliedCategoria.getText()) != 0){

                        tabla.getSelectionModel().getSelectedItem().setNombre(textFliedCategoria.getText());

                        categoriaDAO.updateCategorias(tabla.getSelectionModel().getSelectedItem());

                    }
                    //SEGUNDO COMPARAMOS EL NOMBRE DE LAS SUBCATEGORIA TABLA(ComboBox) -> TEXTFLIED
                    if(tabla.getSelectionModel().getSelectedItem().getSubcategorias().getSelectionModel().getSelectedItem().toString().compareTo(textFieldSubCategoria.getText()) != 0){

                        subCategoriaDAO.updateSubCategorias(subCategoriaDAO.getSubcategoriaPorNombre(tabla.getSelectionModel().getSelectedItem().getSubcategorias().getSelectionModel().getSelectedItem().toString()),textFieldSubCategoria.getText());

                    }
                }

                band=false;
            }


            tabla.getItems().clear();

            ObservableList<Categoria> cat =  FXCollections.observableArrayList();
            calsificacionColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombreClasificacion"));
            categoriaColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombre"));
            subcategoriaColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("subcategorias"));
            cat.addAll(categoriaDAO.getAllCategoria());
            tabla.setItems(cat);

            textFliedCategoria.clear();
            comboBoxClasificacion.getSelectionModel().clearSelection();
            textFieldSubCategoria.clear();
        }



    }

    @FXML
    void houseClicked(MouseEvent event) {
        Main.secondStage.close();
    }

    @FXML
    void seleccionarClicked(MouseEvent event) {


        if(tabla.getSelectionModel().isEmpty()){

            labelError.setText("Selecciona la fila de la tabla");


        }else {
            labelError.setText("");
            band=true;
            if(tabla.getSelectionModel().getSelectedItem().getSubcategorias().getSelectionModel().isEmpty()){
                textFieldSubCategoria.clear();
                comboBoxClasificacion.setValue(tabla.getSelectionModel().getSelectedItem().getNombreClasificacion());
                textFliedCategoria.setText(tabla.getSelectionModel().getSelectedItem().getNombre());

            }else {

                comboBoxClasificacion.setValue(tabla.getSelectionModel().getSelectedItem().getNombreClasificacion());
                textFliedCategoria.setText(tabla.getSelectionModel().getSelectedItem().getNombre());
                textFieldSubCategoria.setText(tabla.getSelectionModel().getSelectedItem().getSubcategorias().getSelectionModel().getSelectedItem().toString());
            }

            tabla.getSelectionModel().getSelectedItem().getSubcategorias().getSelectionModel().clearSelection();

        }



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

        calsificacionColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombreClasificacion"));
        categoriaColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombre"));
        subcategoriaColum.setCellValueFactory(new PropertyValueFactory<Categoria,String>("subcategorias"));
        cat.addAll(categoriaDAO.getAllCategoria());
        tabla.setItems(cat);

        System.out.println(categoriaDAO.getAllCategoria());

    }

}