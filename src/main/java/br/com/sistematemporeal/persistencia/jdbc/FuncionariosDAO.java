package br.com.sistematemporeal.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Funcionarios;

public class FuncionariosDAO {
	
	private Connection con = ConexaoFactory.getConnection();

	
	/**
	 * Faz o cadastro de novos funcionarios no banco de dados
	 * @param Recebe como parametro um objeto funcionario e o insere no banco de dados
	 */
	public void cadastrar(Funcionarios fun) {

		String sql = "insert into funcionarios (nome, login, cpf, acesso, senha, email, telefone, endereco) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, fun.getNome());
			preparador.setString(2, fun.getLogin());
			preparador.setInt(3, fun.getCpf());
			preparador.setInt(4, fun.getAcesso());
			preparador.setString(5, fun.getSenha());
			preparador.setString(6, fun.getEmail());
			preparador.setString(7, fun.getTelefone());
			preparador.setString(8, fun.getEndereço());
			// Executando comando SQL
			preparador.execute();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	
	/**
	 * Faz alteração de qualquer parametro de um registro da tabela funcionarios
	 * @param Recebe como parametro um objeto do tipo funcionario associado a um id previamente criado
	 */
	public void alterar(Funcionarios fun) {

		String sql = "update funcionarios set nome=?, login=?, cpf=?, acesso=?, senha=?, email=?, telefone=?, endereco=? where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, fun.getNome());
			preparador.setString(2, fun.getLogin());
			preparador.setInt(3, fun.getCpf());
			preparador.setInt(4, fun.getAcesso());
			preparador.setString(5, fun.getSenha());
			preparador.setString(6, fun.getEmail());
			preparador.setString(7, fun.getTelefone());
			preparador.setString(8, fun.getEndereço());
			preparador.setInt(9, fun.getId());
			// Executando comando SQL
			preparador.execute();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Faz a exclusao de um determinado registro da tabela funcionarios
	 * @param Recebe como parametro um objeto do tipo Funcionario associado a um id especifico previamente criado
	 */
	public void excluir(Funcionarios fun) {
		String sql = "delete from funcionarios where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, fun.getId());
			// Executando comando SQL
			preparador.execute();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Faz a busca de um registro no banco de dados por id
	 * @param id Recebe como como parametros o numero do id de um funcionario
	 * @return Retorna um objeto Funcionario do Banco de dados, quando encontrado...Retorna nulo caso não encontre nenhum objeto 
	 */
	public Funcionarios buscaPorId(Integer id){
		String sql = "Select * from funcionarios where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, id);
			ResultSet resultado =preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if(resultado.next()){
				Funcionarios fc = new Funcionarios();
				fc.setId(resultado.getInt("id"));
				fc.setCpf(resultado.getInt("cpf"));
				fc.setAcesso(resultado.getInt("acesso"));
				fc.setEmail(resultado.getString("email"));
				fc.setLogin(resultado.getString("login"));
				fc.setSenha(resultado.getString("senha"));
				fc.setTelefone(resultado.getString("telefone"));
				return fc;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Faz busca de todos os registros da tabela Funcionarios
	 * @return Retorna uma lista de todos os objetos funcionarios cadastrados no banco de dados
	 */
	
	public List<Funcionarios> buscaTodos(){
		
		
		String sql = "Select * from funcionarios ";
		List<Funcionarios> lista = new ArrayList<Funcionarios>();
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			ResultSet resultado =preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while(resultado.next()){
				Funcionarios fc = new Funcionarios();
				fc.setId(resultado.getInt("id"));
				fc.setCpf(resultado.getInt("cpf"));
				fc.setAcesso(resultado.getInt("acesso"));
				fc.setEmail(resultado.getString("email"));
				fc.setLogin(resultado.getString("login"));
				fc.setSenha(resultado.getString("senha"));
				fc.setTelefone(resultado.getString("telefone"));
				lista.add(fc);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return lista;
		
	}
	

}
