package br.com.sistematemporeal;

import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;

public class TestFuncionariosDAO {
	public static void main(String [] args){
		//cadastro();
		//altera();
		//exclui();
		//buscaId();
		//buscarTodos();
	
	}
	
	public static void cadastro(){
		Funcionarios fun = new Funcionarios();
		
		fun.setNome("Rogerio");
		fun.setLogin("Roger");
		fun.setCpf(123456789);
		fun.setAcesso(3);
		fun.setSenha("12345");
		fun.setEmail("email");
		fun.setTelefone("telefone");
		fun.setEndereço("Endereço");

		FuncionariosDAO funDAO = new FuncionariosDAO();
		funDAO.cadastrar(fun);
		
		System.out.println("Cadastrado com sucesso.");
			
	}
	
	public static void altera(){
		Funcionarios fun = new Funcionarios();
		fun.setId(2);
		fun.setNome("Rogerio");
		fun.setLogin("Roger2");
		fun.setCpf(123456789);
		fun.setAcesso(3);
		fun.setSenha("12345");
		fun.setEmail("email");
		fun.setTelefone("telefone");
		fun.setEndereço("Endereço");

		FuncionariosDAO funDAO = new FuncionariosDAO();
		funDAO.alterar(fun);
		
		System.out.println("Alterado com sucesso.");
		
	}
	
	public static void exclui(){
		Funcionarios fun = new Funcionarios();
		fun.setId(2);

		FuncionariosDAO funDAO = new FuncionariosDAO();
		funDAO.excluir(fun);
		
		System.out.println("Excluido com sucesso.");
		
	}
	
	public static void buscaId(){
		FuncionariosDAO fcDAO = new FuncionariosDAO();
		Funcionarios fc =fcDAO.buscaPorId(1);
		System.out.println(fc);
		
		
	}
	
	public static void buscarTodos(){
		FuncionariosDAO fcDAO = new FuncionariosDAO();
		List<Funcionarios> listaFC =fcDAO.buscaTodos();
		for(Funcionarios f: listaFC){
			System.out.println(f);
		}
	}
	
	
}
