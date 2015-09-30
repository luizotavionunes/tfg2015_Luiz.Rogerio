package br.com.sistematemporeal;

import java.sql.Date;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Faturamento;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.FaturamentoDAO;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;

public class TestFaturamentoDAO {

	public static void main(String[] args) {
		//cadastro();
		//altera();
		//exclui();
		//buscaId();
		//buscarTodos();
		
		

	}
	
	public static void cadastro(){
		Date data = null;
		
		
		Faturamento fat = new Faturamento();
		fat.setCpf_funcionario(12345678);
		//fat.setData(data);
		fat.setEstado("Nao processado.");
		fat.setValor_informado("10000");
		
		FaturamentoDAO fatDAO = new FaturamentoDAO();
		fatDAO.cadastrar(fat);
		
		System.out.println("Cadastrado com sucesso.");	
		
		
	}
	
	public static void altera(){
		Date data = null;
		
		
		Faturamento fat = new Faturamento();
		fat.setId(1);
		fat.setCpf_funcionario(12345678);
		fat.setData(data);
		fat.setEstado("Nao processado.");
		fat.setValor_informado("10000");
		
		FaturamentoDAO fatDAO = new FaturamentoDAO();
		fatDAO.alterar(fat);
		
		System.out.println("Alterado com sucesso.");	
		
		
	}
	
	public static void exclui(){
		Date data = null;
		
		
		Faturamento fat = new Faturamento();
		fat.setId(1);
		
		FaturamentoDAO fatDAO = new FaturamentoDAO();
		fatDAO.excluir(fat);
		
		System.out.println("Excluido com sucesso.");	
		
		
	}
	public static void buscaId(){
		FaturamentoDAO ftDAO = new FaturamentoDAO();
		Faturamento ft =ftDAO.buscaPorId(1);
		System.out.println(ft);
		
		
	}
	
	public static void buscarTodos(){
		FaturamentoDAO ftDAO = new FaturamentoDAO();
		List<Faturamento> listaFT =ftDAO.buscaTodos();
		for(Faturamento ft: listaFT){
			System.out.println(ft);
		}
	}

}
