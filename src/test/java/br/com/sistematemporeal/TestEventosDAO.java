package br.com.sistematemporeal;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
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
		//saida();
		//pegaEventos();
		
		

	}
	/*
	public static void cadastro(){
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora_fim = null;
		Date hora_inicio = null;
		Date data = null;
	
		
		Eventos evt = new Eventos();
		evt.setEntrada(2);
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
	
	public static void pegaEventos(){
		String dataI="2015-10-01";
		String dataF="2015-10-06";
		String horaI = "10:47:00";
		String horaF = "21:57:59";
		SimpleDateFormat formatadorH = new SimpleDateFormat("HH:mm:ss");
		java.util.Date horaDB = null;
		try {
			horaDB = formatadorH.parse(horaI);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Time timeDB = new Time(horaDB.getTime());
		
		SimpleDateFormat formatadorH1 = new SimpleDateFormat("HH:mm:ss");
		java.util.Date horaDB1 = null;
		try {
			horaDB1 = formatadorH1.parse(horaF);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Time timeDB1 = new Time(horaDB1.getTime());
		
		//dataI = new SimpleDateFormat("yyyy-MM-dd").format(date);
		//DateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");
		
	    java.sql.Date datec = null;  
        try {  
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
            datec = new java.sql.Date( ((java.util.Date)format.parse(dataI)).getTime() );  
        } catch (ParseException e) {              
            try {
				throw e;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
        } 
        
	    java.sql.Date datec1 = null;  
        try {  
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
            datec1 = new java.sql.Date( ((java.util.Date)format.parse(dataF)).getTime() );  
        } catch (ParseException e) {              
            try {
				throw e;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
        }  
        //System.out.println(timeDB);
       // System.out.println(timeDB1);
		
		EventosDAO evtDAO = new EventosDAO();
		List<Eventos> listaEVT =evtDAO.totalEventos(datec, datec1, timeDB, timeDB1, 2);
		for(Eventos e: listaEVT){
			System.out.println(e);
		}
		
		
		
		
		
	}
	
	public static void altera(){
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Time hora_fim = null;
		Time hora_inicio = null;
		Date data = null;
	
		
		Eventos evt = new Eventos();
		evt.setId(1);
		evt.setEntrada(1);
		evt.setSaida(0);
		evt.setId_sensor(1);
		evt.setValor("400");
		evt.setHora_inicio(hora_inicio);
		evt.setHora_fim(hora_fim);
		evt.setData_inicio(data);
		
		EventosDAO evtDAO = new EventosDAO();
		//evtDAO.alterar(evt);
		
		System.out.println("Alterado com sucesso.");
		
	}
	
	
	public static void saida(){

		
		Eventos evt = new Eventos();
		evt.setId(2);
		evt.setSaida(1);

		
		EventosDAO evtDAO = new EventosDAO();
		evtDAO.registraSaida(evt);
		
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
		
		
		
	}
	
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
	
*/
}
