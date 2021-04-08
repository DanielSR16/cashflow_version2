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
			String sql = "insert into categoria (nombre,idclasificacion) values(?,?)";

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

	public int getIdCategoria(String nombre) {
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
		int StringID = categoria.getId();
		System.out.println("VALOR DEL ID "+StringID);
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

	public boolean updateCategorias(Categoria categoria) {
		boolean resultado = false;

		if (connection != null) {
			String sql = "update categoria set "
					+ "nombre=?"
					+ "where id=?";

			try {
				PreparedStatement statement = connection.prepareStatement(sql);


				statement.setInt(2, categoria.getId());
				statement.setString(1, categoria.getNombre());
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