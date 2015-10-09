package br.com.sistematemporeal.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Faturamento;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;

public class FaturamentoDAO {

	private Connection con = ConexaoFactory.getConnection();

	/**
	 * 
	 * Faz o cadastro de um registro na tabela faturamento do banco de dados
	 * 
	 * @param Recebe
	 *            como parametro um objeto do tipo faturamento que sera inserido
	 *            no banco de dados
	 * @return 
	 */
	public int cadastrar(Faturamento fat) {
		ResultSet rsID = null;
		String sql = "insert into faturamento (cpf_funcionario, data, estado, valor_informado, data_inicio, hora_inicio, hora) values (?, CURRENT_DATE, ?, ?, ?, ?, LOCALTIME(0))";
		int id=0;
		try (PreparedStatement preparador = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparador.setInt(1, fat.getCpf_funcionario());
			preparador.setString(2, fat.getEstado());
			preparador.setString(3, fat.getValor_informado());
			preparador.setDate(4, fat.getData_inicio());
			preparador.setTime(5, fat.getHora_inicio());
			
			// Executando comando SQL
			preparador.executeUpdate();
			
			rsID = preparador.getGeneratedKeys();  
			  
			if(rsID.next())
				id=rsID.getInt("id");
				
			
			


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return id;
		
		
		

	}
	
	public void insereSoma(Faturamento fat) {
		String sql = "update faturamento set total_eventos=?, estado=? where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, fat.getTotal_eventos());
			preparador.setString(2, fat.getEstado());
			preparador.setInt(3, fat.getId());
			// Executando comando SQL
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	


	/**
	 * 
	 * Faz alteracao de um registro da tabela faturamento
	 * 
	 * @param recebe
	 *            como parametro um objeto do tipo faturamento que esta
	 *            associado a um id que se deseja alterar
	 */
	public void alterar(Faturamento fat) {
		String sql = "update faturamento set cpf_funcionario=?, data=?, estado=?, valor_informado=?, data=? where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, fat.getCpf_funcionario());
			preparador.setDate(2, fat.getData());
			preparador.setString(3, fat.getEstado());
			preparador.setString(4, fat.getValor_informado());
			preparador.setInt(5, fat.getId());
			// Executando comando SQL
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Faz a exclusão de um registro da tabela faturamento
	 * 
	 * @param recebe
	 *            como parametro um objeto do tipo faturamento que esta
	 *            associado ao id que se deseja excluir
	 */
	public void excluir(Faturamento fat) {
		String sql = "delete from faturamento where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, fat.getId());
			// Executando comando SQL
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Faz uma busca de um registro na tabela faturamento associado a um id
	 * 
	 * @param Recebe
	 *            como parametro um id especifico que esta associado a um
	 *            registro da tabela faturamento
	 * @return Retorna um objeto do tipo faturamento que esta associado ao id
	 *         pesquisado
	 */
	public Faturamento buscaPorId(Integer id) {
		String sql = "Select * from faturamento where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if (resultado.next()) {
				Faturamento ft = new Faturamento();
				ft.setCpf_funcionario(resultado.getInt("cpf_funcionario"));
				ft.setData(resultado.getDate("data"));
				ft.setEstado(resultado.getString("estado"));
				ft.setValor_informado(resultado.getString("valor_informado"));
				ft.setId(resultado.getInt("id"));
				ft.setHora_inicio(resultado.getTime("hora_inicio"));
				ft.setData(resultado.getDate("data"));
				ft.setHora(resultado.getTime("hora"));
				ft.setData_inicio(resultado.getDate("data_inicio"));
				ft.setTotal_eventos(resultado.getInt("total_eventos"));
				return ft;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Faz uma busca de todos os registros da tabela faturamento
	 * 
	 * @return Recebe como retorno uma lista contendo todos os objetos inseridos
	 *         na tabela faturamento
	 */
	public List<Faturamento> buscaTodos() {
		String sql = "Select * from faturamento ";
		List<Faturamento> lista = new ArrayList<Faturamento>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while (resultado.next()) {
				Faturamento ft = new Faturamento();
				ft.setCpf_funcionario(resultado.getInt("cpf_funcionario"));
				ft.setData(resultado.getDate("data"));
				ft.setEstado(resultado.getString("estado"));
				ft.setValor_informado(resultado.getString("valor_informado"));
				ft.setId(resultado.getInt("id"));
				ft.setHora_inicio(resultado.getTime("hora_inicio"));
				ft.setData(resultado.getDate("data"));
				ft.setHora(resultado.getTime("hora"));
				ft.setData_inicio(resultado.getDate("data_inicio"));
				ft.setTotal_eventos(resultado.getInt("total_eventos"));
				lista.add(ft);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

}
