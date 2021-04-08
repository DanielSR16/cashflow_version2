package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DB.Connector;
import Entities.Categoria;

public class CategoriaDAO {

	private final int ACCEPT = 1;
	Connector dataAdapter= new Connector();
	private Connection connection = dataAdapter.getConnectionMySql();
	
	public boolean insert(Categoria categoria) {
		boolean resultado = false;
		if (connection != null) {
			String sql = "insert into categoria (nombre,idclasificacion,idsubcategoria) values(?,?,?)";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, categoria.getNombre());
			statement.setInt(2, categoria.getIdClasificacion());

				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public String getIdSubCategoria(String nombre) {
		Categoria categoria = null;
		if (connection != null) {
			String sql = "select id from categoria where nombre=?";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);

				statement.setString(1, nombre);
				ResultSet results = statement.executeQuery();
				results.next();
				if (results.getRow() == ACCEPT) {
					categoria = new Categoria();
					categoria.setId(results.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String StringID = String.valueOf(categoria.getId());

		return StringID;
	}




	public List<Categoria> getAllCategoria() { //retorna todos los registros
			List<Categoria> categorias = new ArrayList<>();

			if (connection != null) {
				String sql = "SELECT categoria.id id,clasificacion.nombre clasificacion, categoria.nombre categoria\n" +
						"\t\t\t\t\t\tFROM categoria JOIN clasificacion\n" +
						"\t\t\t\t\t\tON categoria.idclasificacion = clasificacion.id ";

				try {
					PreparedStatement statement = connection.prepareStatement(sql);
					ResultSet results = statement.executeQuery();
					while (results.next()) {

						int id = results.getInt(1);
						String clasificacion = results.getString(2);
						String categoria = results.getString(3);

						Categoria cat = new Categoria(id,categoria,clasificacion);

						SubCategoriaDAO daoSub = new SubCategoriaDAO();

						cat.getSubcategorias().setItems(daoSub.getAllSubCategoriaById(id));

					categorias.add(cat);

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return categorias;
		}


	public String nombresSubCategoria(String sentenciasql){
		List<String> subCategoriaNombre = new ArrayList<>();
		System.out.println(sentenciasql);
		try {
			PreparedStatement statement2 = connection.prepareStatement(sentenciasql);
			ResultSet results2 = statement2.executeQuery();
			while (results2.next()) {
				String idsubcategoria2 = results2.getString(1);
				subCategoriaNombre.add(idsubcategoria2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String nombresJuntos="";

		for (int i = 0; i < subCategoriaNombre.size(); i++) {
			nombresJuntos =  nombresJuntos + subCategoriaNombre.get(i) + ",";
		}

		return nombresJuntos;
	}

	public boolean updateSubcategorias(Categoria categoria,String nuevaSubcategoria) {
		boolean resultado = false;

		if (connection != null) {
			String sql = "update categoria set "
					+ "idsubcategoria=?"
					+ "where id=?";

			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, nuevaSubcategoria);
				statement.setInt(2, categoria.getId());
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;

	}



}