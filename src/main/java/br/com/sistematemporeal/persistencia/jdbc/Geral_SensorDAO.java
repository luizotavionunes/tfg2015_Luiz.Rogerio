package br.com.sistematemporeal.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Geral_Sensor;

public class Geral_SensorDAO {
	
	private Connection con = ConexaoFactory.getConnection();

	public void atualizaSensor(Geral_Sensor sensor) {
		String sql = "update geral_sensor set estado=? where id_sensor=?";
	
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, sensor.getEstado());
			preparador.setInt(2, sensor.getId_sensor());

			// Executando comando SQL
			preparador.execute();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public List<Geral_Sensor> buscaTodos() {

		String sql = "Select * from geral_sensor";
		List<Geral_Sensor> lista = new ArrayList<Geral_Sensor>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while (resultado.next()) {
				Geral_Sensor gs = new Geral_Sensor();
				gs.setId_sensor(resultado.getInt("id_sensor"));
				gs.setEstado(resultado.getInt("estado"));
				lista.add(gs);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	
	
	public Geral_Sensor buscaPorId(Integer id) {
		String sql = "Select * from geral_sensor where id_sensor=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if (resultado.next()) {
				Geral_Sensor gs = new Geral_Sensor();
				gs.setId_sensor(resultado.getInt("id_sensor"));
				gs.setEstado(resultado.getInt("estado"));
				return gs;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
}
