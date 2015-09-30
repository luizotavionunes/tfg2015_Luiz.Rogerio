package br.com.sistematemporeal.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Faturamento;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.entidades.Log_Sensor;

public class Log_SensorDAO {

	private Connection con = ConexaoFactory.getConnection();
	
	
	/**
	 * 
	 * Faz o cadastro de novos registros na tabela Log_Sensor
	 * @param Recebe como parametro um objeto do tipo Log_Sensor e o insere no banco de dados
	 */
	public void cadastrar(Log_Sensor log) {
		// TODO Auto-generated method stub
		
		
		String sql = "insert into log_sensor (cpf_funcionario, id_sensor, observacao, data) values (?, ?, ?, CURRENT_DATE)";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, log.getCpf_funcionario());
			preparador.setInt(2, log.getId_sensor());
			preparador.setString(3, log.getObservacao());
			//preparador.setDate(4, log.getData());;
			// Executando comando SQL
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	/**
	 * 
	 * Altera qualquer campo de um registro dda tabela Log_Sensor
	 * @param Recebe como parametro um objeto do tipo Log_Sensor associado a um id especifico previamente criado
	 */
	public void alterar(Log_Sensor log) {
		
		String sql = "update log_sensor set cpf_funcionario=?, id_sensor=?, observacao=?, data=? where id=?";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, log.getCpf_funcionario());
			preparador.setInt(2, log.getId_sensor());
			preparador.setString(3, log.getObservacao());
			preparador.setDate(4, log.getData());;
			preparador.setInt(5, log.getId());
			// Executando comando SQL
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	/**
	 * 
	 * faz a exclusão de um registro na tabela Log_Sensor
	 * @param Recebe como parametroum objeto log_sensor que contém um id especifico para ser excluido
	 */
	public void excluir(Log_Sensor log) {
		String sql = "delete from log_sensor where id=?";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, log.getId());
			// Executando comando SQL
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 
	 * Faz uma busca na tabela Log_Sensor de acordo com o parametro id
	 * @param Recebe o id do objeto a ser buscado na tabela Log_Sensor
	 * @return Retorna o objeto que corresponde ao id pesquisado, caso nao encontre retorna nulo
	 */
	public Log_Sensor buscaPorId(Integer id) {
		String sql = "Select * from faturamento where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, id);
			ResultSet resultado =preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if(resultado.next()){
				Log_Sensor log = new Log_Sensor();
				log.setCpf_funcionario(resultado.getInt("cpf_funcionario"));
				log.setObservacao(resultado.getString("observacao"));
				log.setData(resultado.getDate("data"));
				log.setId_sensor(resultado.getInt("id_sensor"));
				log.setId(resultado.getInt("id"));
				return log;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * Faz uma busca em todos os registros da tabela Log_Sensor
	 * @return Retorna uma lista de objetos com todos elementos da tabela Log_Sensor
	 */
	public List<Log_Sensor> buscaTodos() {
		String sql = "Select * from log_sensor";
		List<Log_Sensor> lista = new ArrayList<Log_Sensor>();
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while(resultado.next()){
				Log_Sensor log = new Log_Sensor();
				log.setCpf_funcionario(resultado.getInt("cpf_funcionario"));
				log.setObservacao(resultado.getString("observacao"));
				log.setData(resultado.getDate("data"));
				log.setId_sensor(resultado.getInt("id_sensor"));
				log.setId(resultado.getInt("id"));
				lista.add(log);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return lista;
	}

}
