package br.com.sistematemporeal.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;

public class EventosDAO {

	private Connection con = ConexaoFactory.getConnection();
	
	
	/**
	 * Faz o cadastro de novos registros na tabela eventos
	 * @param Recebe como parametro um objeto do tipo Eventos e o insere no banco de dados
	 */
	public void cadastrar(Eventos evt) {
		
		String sql = "insert into eventos (entrada, saida, id_sensor, valor, hora_inicio, hora_fim, data) values (?, ?, ?, ?, LOCALTIME, LOCALTIME, CURRENT_DATE)";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, evt.getEntrada());
			preparador.setInt(2, evt.getSaida());
			preparador.setInt(3, evt.getId_sensor());
			preparador.setString(4, evt.getValor());
			//preparador.setDate(5, evt.getHora_inicio());
			//preparador.setDate(6, evt.getHora_fim());
			preparador.setDate(5, evt.getData());
			// Executando comando SQL
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Faz a alteração de um registro da tabela eventos
	 * @param Recebe como parametro um objeto do tipo Eventos associado ao id que se deseja alterar
	 */
	public void alterar(Eventos evt) {
		String sql = "update eventos set entrada=?, saida=?, id_sensor=?, valor=?, hora_inicio=?, hora_fim=?, data=? where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, evt.getEntrada());
			preparador.setInt(2, evt.getSaida());
			preparador.setInt(3, evt.getId_sensor());
			preparador.setString(4, evt.getValor());
			preparador.setDate(5, evt.getHora_inicio());
			preparador.setDate(6, evt.getHora_fim());
			preparador.setDate(7, evt.getData());
			preparador.setInt(8, evt.getId());
			// Executando comando SQL
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Faz a exclusao de um registro da tabela eventos
	 * @param Recebe como parametro um objeto do tipo Eventos que esta associado ao id que se deseja excluir
	 */
	public void excluir(Eventos evt) {
		String sql = "delete from eventos where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, evt.getId());
			// Executando comando SQL
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Faz uma busca de todos os elementos da tabela Eventos
	 * @return Retorna uma lista de objetos do tipo eventos
	 */
	public List<Eventos> buscaTodos() {
		
		String sql = "Select * from eventos ";
		List<Eventos> lista = new ArrayList<Eventos>();
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while(resultado.next()){
				Eventos ev = new Eventos();
				ev.setEntrada(resultado.getInt("entrada"));
				ev.setSaida(resultado.getInt("saida"));
				ev.setData(resultado.getDate("data"));
				ev.setHora_fim(resultado.getDate("hora_fim"));
				ev.setHora_inicio(resultado.getDate("hora_inicio"));
				ev.setValor(resultado.getString("valor"));
				ev.setId(resultado.getInt("id"));
				ev.setId_sensor(resultado.getInt("id_sensor"));
				lista.add(ev);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return lista;
	}

	
	/**
	 * Faz uma busca na tabela Eventos de um registro especifico
	 * @param Recebe como parametro o id que se deseja buscar na tabela eventos
	 * @return Retorna um objeto do tipo eventos que esta associado ao id pesquisado
	 */
	public Eventos buscaPorId(Integer id) {
		String sql = "Select * from eventos where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if(resultado.next()){
				Eventos ev = new Eventos();
				ev.setEntrada(resultado.getInt("entrada"));
				ev.setSaida(resultado.getInt("saida"));
				ev.setData(resultado.getDate("data"));
				ev.setHora_fim(resultado.getDate("hora_fim"));
				ev.setHora_inicio(resultado.getDate("hora_inicio"));
				ev.setValor(resultado.getString("valor"));
				ev.setId(resultado.getInt("id"));
				ev.setId_sensor(resultado.getInt("id_sensor"));
				return ev;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
