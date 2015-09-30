package br.com.sistematemporeal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;

public class TestEventosDAO {

	public static void main(String[] args) {
		//cadastro();
		//altera();
		//exclui();
		//buscaId();
		//buscarTodos();
		
		

	}
	
	public static void cadastro(){
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora_fim = null;
		Date hora_inicio = null;
		Date data = null;
	
		
		Eventos evt = new Eventos();
		evt.setEntrada(1);
		evt.setSaida(0);
		evt.setId_sensor(1);
		evt.setValor("400");
		//evt.setHora_inicio(hora_inicio);
		//evt.setHora_fim(hora_fim);
		//evt.setData(data);
		
		EventosDAO evtDAO = new EventosDAO();
		evtDAO.cadastrar(evt);
		
		System.out.println("Cadastrado com sucesso.");
		
	}
	
	public static void altera(){
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora_fim = null;
		Date hora_inicio = null;
		Date data = null;
	
		
		Eventos evt = new Eventos();
		evt.setId(1);
		evt.setEntrada(1);
		evt.setSaida(0);
		evt.setId_sensor(1);
		evt.setValor("400");
		evt.setHora_inicio(hora_inicio);
		evt.setHora_fim(hora_fim);
		evt.setData(data);
		
		EventosDAO evtDAO = new EventosDAO();
		evtDAO.alterar(evt);
		
		System.out.println("Alterado com sucesso.");
		
	}
	
	public static void exclui(){
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	
		Eventos evt = new Eventos();
		evt.setId(1);
		
		EventosDAO evtDAO = new EventosDAO();
		evtDAO.excluir(evt);
		
		System.out.println("Excluido com sucesso.");
		
	}
	
	// possivel erro por entradas que nao podem ser nulas * checar depois
	/*public static void atualiza(){
		Eventos evt = new Eventos();
		evt.setId(1);
		evt.setSaida(1);
		
		EventosDAO evtDAO = new EventosDAO();
		evtDAO.atualizaSensor(evt);
		
		System.out.println("Atualiado com sucesso.");
		
		
		
	}*/
	
	public static void buscaId(){
		EventosDAO evtDAO = new EventosDAO();
		Eventos ev =evtDAO.buscaPorId(1);
		System.out.println(ev);
		
		
	}
	
	public static void buscarTodos(){
		EventosDAO evtDAO = new EventosDAO();
		List<Eventos> listaEVT =evtDAO.buscaTodos();
		for(Eventos e: listaEVT){
			System.out.println(e);
		}
	}
	

}
