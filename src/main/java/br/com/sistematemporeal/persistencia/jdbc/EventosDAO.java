package br.com.sistematemporeal.persistencia.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Faturamento;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;

public class EventosDAO {

	private Connection con = ConexaoFactory.getConnection();

	/**
	 * Faz o cadastro de novos registros na tabela eventos. É responsável por marcar
	 * a entrada de um novo cliente
	 * 
	 * @param Recebe como parametro um objeto do tipo Eventos e o insere no banco
	 *            de dados
	 */
	public void cadastrar(Eventos evt) {
		
		// Cria um novo registro da tabela eventos
		String sql = "insert into eventos (entrada, saida, id_sensor, valor, hora_inicio, hora_fim, data_inicio, data_fim, tempo_i) values (?, ?, ?, ?, LOCALTIME(0), null, CURRENT_DATE, null, CURRENT_TIMESTAMP(0))";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, evt.getEntrada());
			preparador.setInt(2, evt.getSaida());
			preparador.setInt(3, evt.getId_sensor());
			preparador.setString(4, evt.getValor());
			preparador.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Registra a saida de um cliente, faz o calculo de horas de permanecia e altera
	 * o valor_total de cada evento com base nos calculos realizados 
	 * (Permanencia total X valor suite)
	 * 
	 * @param evt
	 *            Recebe como parametro um determinado evento para alterar este
	 *            registro
	 */
	public void registraSaida(Integer id_sensor) {


		// Registrando a saida de um cliente, marcando o evento como fechado no banco de dados
		String sql = "update eventos set saida=1, hora_fim=LOCALTIME(0), data_fim=CURRENT_DATE, tempo_f=CURRENT_TIMESTAMP(0) where id_sensor=? AND saida=0";
		// Fazendo o calculo do tempo total que o cliente permaneceu na suite
		String sql2 = "SELECT EXTRACT(HOUR FROM (tempo_f - tempo_i)) FROM eventos where id=?";
		// Obtendo o valor por hora da suite em que esta sendo registrada a saida
		String sql3 = "select valor from eventos where id=?";
		// Registrando o valor total da estadia do cliente, como base no numero de horas e no
		// valor da suite
		String sql4 = "update eventos set valor_total=? where id=?";

		ResultSet rsID = null;
		int id = 0, maxH = 0, val = 0;
		String valor = null;
		ResultSet hora = null;
		// Registrando a saida e obtendo o ID do evento fechado
		try (PreparedStatement preparador = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparador.setInt(1, id_sensor);
			preparador.executeUpdate();
			rsID = preparador.getGeneratedKeys();
			// Obtendo o id do evento fechado
			if (rsID.next())
				id = rsID.getInt("id");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Fazendo o calculo do tempo de permanencia do cliente de acordo com o id
		// obtido na consulta sql anterior
		try (PreparedStatement preparador = con.prepareStatement(sql2)) {
			preparador.setInt(1, id);
			// Salvando a hora obtida do calculo feito na consulta sql anterior
			// em um objeto do tipo resultset
			hora = preparador.executeQuery();
			if (hora.next())
				// Armazenando o valor encontrado em uma variavel inteira
				maxH = ((Number) hora.getObject(1)).intValue();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Obtendo o valor por hora da suite em questão
		try (PreparedStatement preparador = con.prepareStatement(sql3)) {
			preparador.setInt(1, id);
			// Armazenando oresultado em um objeto do tipo resultset
			hora = preparador.executeQuery();
			if (hora.next())
				// Salvando a hora da suite em uma string
				valor = hora.getString("valor");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Fazendo o calculo das horas de permanencia X o valor da suite
		val = (Integer.parseInt(valor)) * maxH;
		if (val == 0)
			// Caso o tmep ode permanencia seja menor que 1 hora,
			// o valor total passará a ser o valor por hora da suite
			val += (Integer.parseInt(valor));

		// Inserindo no banco de dados o valor total calculado anteriormente
		try (PreparedStatement preparador = con.prepareStatement(sql4)) {
			preparador.setInt(1, val);
			preparador.setInt(2, id);
			preparador.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	

	/**
	 * Esta função recebe como parâmetro o id do sensor onde o evento será marcado
	 * como fechado. Ela retorna o Id do evento que foi fechado.
	 * @param id_sensor Recebe como parametro o numero do sensor que precisar ser fechado
	 * @return retorna o id do evento que foi fechado
	 *//*
	public int fechaEventoAux(int id_sensor){
		int id=0;
		
		String sql = "Select * from eventos where id_sensor=? AND saida=0";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id_sensor);
		
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if (resultado.next()) {
				Eventos ev = new Eventos();
				ev.setId(resultado.getInt("id"));
				
				id=ev.getId();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return id;
	}*/



	/**
	 * Faz a exclusao de um registro da tabela eventos
	 * 
	 * @param Recebe
	 *            como parametro um objeto do tipo Eventos que esta associado ao
	 *            id que se deseja excluir
	 */
	public void excluir(Eventos evt) {
		String sql = "delete from eventos where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, evt.getId());
			// Executando comando SQL
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Falta implementar
	public void excluirEvtFat(List<Eventos> evt, Integer id) {
		String sql = "delete from eventos where fat_id=?";

		for(Eventos f : evt){
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);
			// Executando comando SQL
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	public List<Eventos> totalEventos(Date dataI, Date dataF, Time horaI, Time horaF, Integer id_fat) {
		String sql = "Select * from eventos where tempo_f>=(?|| ' ' ||?)::timestamp AND tempo_f<=(?|| ' ' ||?)::timestamp"; 
		String sql2 = "update eventos set fat_id=? where tempo_f>(?|| ' ' ||?)::timestamp AND tempo_f<=(?|| ' ' ||?)::timestamp";
		
		List<Eventos> lista = new ArrayList<Eventos>();
	
		
		try (PreparedStatement preparador = con.prepareStatement(sql2)) {
			preparador.setInt(1, id_fat);
			preparador.setString(2, dataI.toString());
			preparador.setString(3, horaI.toString());
			preparador.setString(4, dataF.toString());
			preparador.setString(5, horaF.toString());
			preparador.execute();
			
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, dataI.toString());
			preparador.setString(2, horaI.toString());
			preparador.setString(3, dataF.toString());
			preparador.setString(4, horaF.toString());
			
			//preparador.execute();
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while (resultado.next()) {
				Eventos ev = new Eventos();
				ev.setEntrada(resultado.getInt("entrada"));
				ev.setSaida(resultado.getInt("saida"));
				ev.setData_inicio(resultado.getDate("data_inicio"));
				ev.setHora_fim(resultado.getTime("hora_fim"));
				ev.setHora_inicio(resultado.getTime("hora_inicio"));
				ev.setValor(resultado.getString("valor"));
				ev.setId(resultado.getInt("id"));
				ev.setId_sensor(resultado.getInt("id_sensor"));
				ev.setData_fim(resultado.getDate("data_fim"));
				ev.setId_fat(resultado.getInt("fat_id"));
				ev.setValor_total(resultado.getInt("valor_total"));
				lista.add(ev);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(id_fat);
		
		

		
		return lista;

	}

	/**
	 * Faz uma busca de todos os elementos da tabela Eventos
	 * 
	 * @return Retorna uma lista de objetos do tipo eventos
	 */
	public List<Eventos> buscaTodos() {

		String sql = "Select * from eventos ";
		List<Eventos> lista = new ArrayList<Eventos>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while (resultado.next()) {
				Eventos ev = new Eventos();
				ev.setEntrada(resultado.getInt("entrada"));
				ev.setSaida(resultado.getInt("saida"));
				ev.setData_inicio(resultado.getDate("data_inicio"));
				ev.setHora_fim(resultado.getTime("hora_fim"));
				ev.setHora_inicio(resultado.getTime("hora_inicio"));
				ev.setValor(resultado.getString("valor"));
				ev.setId(resultado.getInt("id"));
				ev.setId_sensor(resultado.getInt("id_sensor"));
				ev.setData_fim(resultado.getDate("data_fim"));
				ev.setId_fat(resultado.getInt("fat_id"));
				ev.setValor_total(resultado.getInt("valor_total"));
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
	 * 
	 * @param Recebe
	 *            como parametro o id que se deseja buscar na tabela eventos
	 * @return Retorna um objeto do tipo eventos que esta associado ao id
	 *         pesquisado
	 */
	public Eventos buscaPorId(Integer id) {
		String sql = "Select * from eventos where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if (resultado.next()) {
				Eventos ev = new Eventos();
				ev.setEntrada(resultado.getInt("entrada"));
				ev.setSaida(resultado.getInt("saida"));
				ev.setData_inicio(resultado.getDate("data_inicio"));
				ev.setHora_fim(resultado.getTime("hora_fim"));
				ev.setHora_inicio(resultado.getTime("hora_inicio"));
				ev.setValor(resultado.getString("valor"));
				ev.setId(resultado.getInt("id"));
				ev.setId_sensor(resultado.getInt("id_sensor"));
				ev.setData_fim(resultado.getDate("data_fim"));
				ev.setId_fat(resultado.getInt("fat_id"));
				ev.setValor_total(resultado.getInt("valor_total"));
				return ev;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	public List<Eventos> buscaEvtFat(int fat_id) {

		String sql = "Select * from eventos where fat_id=?";
		List<Eventos> lista = new ArrayList<Eventos>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, fat_id);
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while (resultado.next()) {
				Eventos ev = new Eventos();
				ev.setEntrada(resultado.getInt("entrada"));
				ev.setSaida(resultado.getInt("saida"));
				ev.setData_inicio(resultado.getDate("data_inicio"));
				ev.setHora_fim(resultado.getTime("hora_fim"));
				ev.setHora_inicio(resultado.getTime("hora_inicio"));
				ev.setValor(resultado.getString("valor"));
				ev.setId(resultado.getInt("id"));
				ev.setId_sensor(resultado.getInt("id_sensor"));
				ev.setData_fim(resultado.getDate("data_fim"));
				ev.setId_fat(resultado.getInt("fat_id"));
				ev.setValor_total(resultado.getInt("valor_total"));
				lista.add(ev);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	
	public Eventos buscaEventosAtivos(int id_sensor){
		
		String sql = "Select * from eventos where saida=0 AND entrada=1 AND id_sensor=?";
		Eventos ev = new Eventos();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id_sensor);
			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if(resultado.next()) {
				ev.setEntrada(resultado.getInt("entrada"));
				ev.setSaida(resultado.getInt("saida"));
				ev.setData_inicio(resultado.getDate("data_inicio"));
				ev.setHora_fim(resultado.getTime("hora_fim"));
				ev.setHora_inicio(resultado.getTime("hora_inicio"));
				ev.setValor(resultado.getString("valor"));
				ev.setId(resultado.getInt("id"));
				ev.setId_sensor(resultado.getInt("id_sensor"));
				ev.setData_fim(resultado.getDate("data_fim"));
				ev.setId_fat(resultado.getInt("fat_id"));
				ev.setValor_total(resultado.getInt("valor_total"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ev;
	}
	
	
		
		
	}


