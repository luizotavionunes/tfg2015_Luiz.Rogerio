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
	 * Faz o cadastro de novos registros na tabela eventos
	 * 
	 * @param Recebe
	 *            como parametro um objeto do tipo Eventos e o insere no banco
	 *            de dados
	 */
	public void cadastrar(Eventos evt) {

		String sql = "insert into eventos (entrada, saida, id_sensor, valor, hora_inicio, hora_fim, data_inicio, data_fim, tempo_i) values (?, ?, ?, ?, LOCALTIME(0), null, CURRENT_DATE, null, CURRENT_TIMESTAMP(0))";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, evt.getEntrada());
			preparador.setInt(2, evt.getSaida());
			preparador.setInt(3, evt.getId_sensor());
			preparador.setString(4, evt.getValor());
			// preparador.setDate(5, evt.getHora_inicio());
			// preparador.setDate(6, evt.getHora_fim());
			// preparador.setDate(5, evt.getData_inicio());
			// Executando comando SQL
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Registra a saida de um cliente em um determinado registro da tabela
	 * eventos
	 * 
	 * @param evt
	 *            Recebe como parametro um determinado evento para alterar este
	 *            registro
	 */
	public void registraSaida(Eventos evt) {

		String sql = "update eventos set saida=1, hora_fim=LOCALTIME(0), data_fim=CURRENT_DATE, tempo_f=CURRENT_TIMESTAMP(0) where id=?";
		String sql2 = "SELECT EXTRACT(HOUR FROM (tempo_f - tempo_i)) FROM eventos where id=?";
		String sql3 = "select valor from eventos where id=?";
		String sql4 = "update eventos set valor_total=? where id=?";

		ResultSet rsID = null;
		int id = 0, maxH = 0, val = 0;
		String valor = null;
		ResultSet hora = null;
		try (PreparedStatement preparador = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparador.setInt(1, evt.getId());
			// preparador.setInt(2, Integer.parseInt(evt.getValor()));
			// Integer valor = evt.getHora_fim()-evt.getHora_inicio();
			// preparador.setInt(2, );
			// Executando comando SQL
			preparador.executeUpdate();
			rsID = preparador.getGeneratedKeys();

			if (rsID.next())
				id = rsID.getInt("id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (PreparedStatement preparador = con.prepareStatement(sql2)) {
			preparador.setInt(1, id);
			hora = preparador.executeQuery();
			if (hora.next())
				maxH = ((Number) hora.getObject(1)).intValue();
			// System.out.print(val);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (PreparedStatement preparador = con.prepareStatement(sql3)) {
			preparador.setInt(1, id);
			hora = preparador.executeQuery();
			if (hora.next())
				valor = hora.getString("valor");
			// System.out.print(valor);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		val = (Integer.parseInt(valor)) * maxH;
		if (val == 0)
			val += (Integer.parseInt(valor));
		// System.out.println(val);

		try (PreparedStatement preparador = con.prepareStatement(sql4)) {
			preparador.setInt(1, val);
			preparador.setInt(2, id);
			preparador.execute();
			// System.out.print(valor);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Integer valor = evt.getHora_fim()-evt.getHora_inicio();

	}

	/**
	 * Faz a alteração de um registro da tabela eventos
	 * 
	 * @param Recebe
	 *            como parametro um objeto do tipo Eventos associado ao id que
	 *            se deseja alterar
	 */
	public void alterar(Eventos evt) {
		String sql = "update eventos set entrada=?, saida=?, id_sensor=?, valor=?, hora_inicio=?, hora_fim=?, data_inicio=?, data_fim=? where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, evt.getEntrada());
			preparador.setInt(2, evt.getSaida());
			preparador.setInt(3, evt.getId_sensor());
			preparador.setString(4, evt.getValor());
			preparador.setTime(5, evt.getHora_inicio());
			preparador.setTime(6, evt.getHora_fim());
			preparador.setDate(7, evt.getData_inicio());
			preparador.setInt(8, evt.getId());
			preparador.setDate(9, evt.getData_fim());
			// Executando comando SQL
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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

	public List<Eventos> totalEventos(Date dataI, Date dataF, Time horaI, Time horaF, Integer id_fat) {
		// AND hora_inicio>=? AND hora_fim<=?
		String sql = "Select * from eventos where data_fim<=? AND data_fim>=? AND hora_fim<=? AND hora_fim>=?";
		String sql2 = "update eventos set fat_id=? where data_fim<=? AND data_fim>=? AND hora_fim<=? AND hora_fim>=?";
		List<Eventos> lista = new ArrayList<Eventos>();
	
		try (PreparedStatement preparador = con.prepareStatement(sql2)) {
			preparador.setInt(1, id_fat);
			preparador.setDate(2, dataF);
			preparador.setDate(3, dataI);
			preparador.setTime(4, horaF);
			preparador.setTime(5, horaI);
			preparador.execute();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setDate(1, dataF);
			preparador.setDate(2, dataI);
			preparador.setTime(3, horaF);
			preparador.setTime(4, horaI);
			preparador.execute();
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
				ev.setData_inicio(resultado.getDate("data"));
				ev.setHora_fim(resultado.getTime("hora_fim"));
				ev.setHora_inicio(resultado.getTime("hora_inicio"));
				ev.setValor(resultado.getString("valor"));
				ev.setId(resultado.getInt("id"));
				ev.setId_sensor(resultado.getInt("id_sensor"));
				ev.setData_fim(resultado.getDate("data_fim"));
				return ev;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
