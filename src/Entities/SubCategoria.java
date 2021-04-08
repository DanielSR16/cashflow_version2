package Entities;

public class SubCategoria {
    private int id;
    private String nombre;
    private int idCategoriaPadre;
    public SubCategoria (){ }

    public SubCategoria(int id,String nombre) {
        this.id =id;
        this.nombre = nombre;
    }

    public SubCategoria(String nombre, int idCategoriaPadre) {
        this.nombre = nombre;
        this.idCategoriaPadre = idCategoriaPadre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCategoriaPadre() {
        return idCategoriaPadre;
    }

    public void setIdCategoriaPadre(int idCategoriaPadre) {
        this.idCategoriaPadre = idCategoriaPadre;
    }

    @Override
    public String toString() {
        return nombre;

    }
}

