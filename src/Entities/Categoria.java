package Entities;

import javafx.scene.control.ComboBox;

import java.util.List;

public class Categoria {
    
    private int id;
    private String nombre;
    private int idClasificacion;
    private String nombreClasificacion;
    private ComboBox subcategorias;
    //private List<SubCategoria> subCategorias;

    public Categoria() {}

    public Categoria(int id,String nombre,String nombreClasificacion) {
        this.id = id;
        this.nombre = nombre;
        this.nombreClasificacion=nombreClasificacion;
        this.subcategorias = new ComboBox();
    }

    public Categoria(String nombre, int idClasificacion) {
        this.nombre = nombre;
        this.idClasificacion = idClasificacion;
    }

    public int getId() {return id;}
    
    public void setId(int id) {this.id = id;}
    
    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}
        
    public int getIdClasificacion() {return idClasificacion;}
        
    public void setIdClasificacion(int idClasificacion) {this.idClasificacion = idClasificacion;}

//    public List<SubCategoria> getSubCategorias() {
//        return subCategorias;
//    }
//
//    public void setSubCategorias(List<SubCategoria> subCategorias) {
//        this.subCategorias = subCategorias;
//    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

//    public String getSubcategoria() {
//        return subcategoria;
//    }
//
//    public void setSubcategoria(String subcategoria) {
//        this.subcategoria = subcategoria;
//    }
//
//    @Override
//    public String toString() {
//        return "Categoria{" +
//                "id=" + id +
//                ", nombre='" + nombre + '\'' +
//                ", nombreClasificacion='" + nombreClasificacion + '\'' +
//                ", subCategorias=" + subCategorias +
//                '}';
//    }


    public ComboBox getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(ComboBox subcategorias) {
        this.subcategorias = subcategorias;
    }
}
