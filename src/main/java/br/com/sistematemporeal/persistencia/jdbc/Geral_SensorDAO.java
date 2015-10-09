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

	public void ligaSensor(Geral_Sensor sensor) {
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
	
	
	public void desligaSensor(Geral_Sensor sensor) {
		String sql = "update geral_sensor set estado=?, id_log=? where id_sensor=?";
	
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, sensor.getEstado());
			preparador.setInt(2, sensor.getId_log());
			preparador.setInt(3, sensor.getId_sensor());
			// Executando comando SQL
			preparador.execute();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public List<Geral_Sensor> buscaTodos() {

		String sql = "Select * from geral_sensor order by id_sensor";
		List<Geral_Sensor> lista = new ArrayList<Geral_Sensor>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while (resultado.next()) {
				Geral_Sensor gs = new Geral_Sensor();
				gs.setId_sensor(resultado.getInt("id_sensor"));
				gs.setEstado(resultado.getInt("estado"));
				gs.setMonitor(resultado.getInt("monitor"));
				gs.setId_log(resultado.getInt("id_log"));
				gs.setPreco(resultado.getInt("preco"));
				lista.add(gs);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	
	public void monitor(int estado, int id_sensor){
		// Quarto desocupado
		if(estado==0){
			String sql="update geral_sensor set monitor=0 where id_sensor=?";
			try (PreparedStatement preparador = con.prepareStatement(sql)) {
				preparador.setInt(1, id_sensor);
				preparador.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//Quarto ocupado	
		}else if(estado==1){
			String sql="update geral_sensor set monitor=1 where id_sensor=?";
			try (PreparedStatement preparador = con.prepareStatement(sql)) {
				preparador.setInt(1, id_sensor);
				preparador.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//Quarto em manutencao	
		}else if(estado==2){
			String sql="update geral_sensor set monitor=2 where id_sensor=?";
			try (PreparedStatement preparador = con.prepareStatement(sql)) {
				preparador.setInt(1, id_sensor);
				preparador.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
		
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
				gs.setId_log(resultado.getInt("id_log"));
				gs.setMonitor(resultado.getInt("monitor"));
				gs.setPreco(resultado.getInt("preco"));
				
				return gs;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
}
