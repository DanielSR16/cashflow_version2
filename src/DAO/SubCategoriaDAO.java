package DAO;

import DB.Connector;
import Entities.Categoria;
import Entities.SubCategoria;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubCategoriaDAO {

    private final int ACCEPT = 1;
    Connector dataAdapter= new Connector();
    private Connection connection = dataAdapter.getConnectionMySql();

    public boolean insert(SubCategoria subcategoria) {
        boolean resultado = false;
        if (connection != null) {
            String sql = "insert into subcategoria (nombre,idcategoriapadre) values(?,?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, subcategoria.getNombre());
                statement.setInt(2, subcategoria.getIdCategoriaPadre());
                if (statement.executeUpdate() == ACCEPT)
                    resultado = true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resultado;
    }


    public ObservableList<SubCategoria> getAllSubCategoriaById(int idCategoria) { //retorna todos los registros
        ObservableList<SubCategoria> subCategorias = FXCollections.observableArrayList();

        if (connection != null) {
            String sql = " SELECT subcategoria.id id, subcategoria.nombre from categoria JOIN subcategoria\n" +
                    "ON categoria.id = subcategoria.idClasificacionPadre where categoria.id = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1,idCategoria);
                ResultSet results = statement.executeQuery();
                while (results.next()) {

                    int id = results.getInt(1);
                    String nombre = results.getString(2);

                    SubCategoria subCategoria = new SubCategoria(id,nombre);

                    subCategorias.add(subCategoria);


                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return subCategorias;
    }



}
