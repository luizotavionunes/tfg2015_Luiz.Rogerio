package br.com.sistematemporeal;

import java.sql.Date;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.entidades.Log_Sensor;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;
import br.com.sistematemporeal.persistencia.jdbc.Log_SensorDAO;

public class TestLog_SensorDAO {

	public static void main(String[] args) {
		//cadastro();
		//altera();
		//exclui();
		//buscaId();
		//buscarTodos();
		
	}
	
	public static void cadastro(){
		Date data = null;

		Log_Sensor log = new Log_Sensor();
		
		log.setCpf_funcionario(123456789);
		log.setId_sensor(1);
		log.setObservacao("Desligado para manutenção.");
		//log.setData(data);
		
		Log_SensorDAO logDAO = new Log_SensorDAO();
		logDAO.cadastrar(log);
		
		System.out.println("Cadastrado com sucesso.");	
		
	}
	
	public static void altera(){
		Date data = null;

		Log_Sensor log = new Log_Sensor();
		log.setId(1);
		log.setCpf_funcionario(123456789);
		log.setId_sensor(1);
		log.setObservacao("Desligado para manutenção.");
		log.setData(data);
		
		Log_SensorDAO logDAO = new Log_SensorDAO();
		logDAO.alterar(log);
		
		System.out.println("Alterado com sucesso.");	
		
	}
	
	public static void exclui(){
		Date data = null;

		Log_Sensor log = new Log_Sensor();
		log.setId(1);
		
		Log_SensorDAO logDAO = new Log_SensorDAO();
		logDAO.excluir(log);
		
		System.out.println("Excluido com sucesso.");	
		
	}
	
	public static void buscaId(){
		Log_SensorDAO logDAO = new Log_SensorDAO();
		Log_Sensor lg =logDAO.buscaPorId(1);
		System.out.println(lg);
		
		
	}
	
	public static void buscarTodos(){
		Log_SensorDAO logDAO = new Log_SensorDAO();
		List<Log_Sensor> listaLOG =logDAO.buscaTodos();
		for(Log_Sensor l: listaLOG){
			System.out.println(l);
		}
	}
	

}
