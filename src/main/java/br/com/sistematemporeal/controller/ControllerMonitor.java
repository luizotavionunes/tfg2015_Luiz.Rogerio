package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Geral_Sensor;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.Geral_SensorDAO;
import br.com.sistematemporeal.persistencia.jdbc.Log_SensorDAO;

/**
 * Este servlet faz o gerenciamneto de todas as suites. Ao ser chamado pelo
 * método GET o usuario obtem como resposta uma pagina JSP que contém
 * informações sobre o estado atual de cada suite(sensor). Informações do tipo:
 * suite ocupada, desocupada ou manutenção. Caso as suites estejam ocupadas é
 * apresentada a data e hora de entrada do cliente.
 * 
 * @author luiz
 *
 */
@WebServlet("/monitor.do")
public class ControllerMonitor extends HttpServlet {

	// Construtor do método
	public ControllerMonitor() {
	}

	/*
	 * Método utilizado para responder requisições do cliente para checar o
	 * estado atual de cada sensor (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Instanciando objetos que serão utilizados para fazer acesso ao
		// banco de dados para buscar informações ou altera-las
		Geral_SensorDAO sensDAO = new Geral_SensorDAO();
		Geral_Sensor sens = new Geral_Sensor();
		//Log_SensorDAO lgsensDAO = new Log_SensorDAO();


		// Lista utilizada para armazenar os atributos da tabela Geral_Sensor
		// que contém informações sobre o estado de cada suite (sensor)
		List<Geral_Sensor> lista = sensDAO.buscaTodos();

		// Variaveis utilizadas para fazer a conversao do banco de dados do tipo
		// date
		// para string e apresenta-las na tela para o usuario
		String dataI = null;
		Date dataAux = null;
		Time horaI = null;

		// Variaveiz utilizadas para auxiliar na conversão e formato da data
		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		for (Geral_Sensor f : lista) {
			// Caso o atributo monitor seja igual a um, significa que o quarto
			// está ocupado
			// no momento.
			if (f.getMonitor() == 1) {
				Eventos ev = new Eventos();
				EventosDAO evDAO = new EventosDAO();
				// É feito uma busca no banco de dados na tabela dos eventos
				// para identificar
				// quais o s eventos estão ativos, ou seja, quais suites estão
				// ocupadas
				ev = evDAO.buscaEventosAtivos(f.getId_sensor());
				// Armazenando a data de entrada do cliente na respectiva suite
				dataAux = ev.getData_inicio();
				// Armazenando a hora de entrada docliente na respectiva suite
				horaI = ev.getHora_inicio();
				// Realizando a conversão da data, e alteração do seu formato
				try {
					dataI = out.format(in.parse(dataAux.toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// Armazenando a data e hora na lsita que será apresentada na
				// tela de
				// monitoramento
				f.setData(dataI);
				f.setHora(horaI);
			}
			// Caso os quartos estejão desocupados ou em manutenção, a data e a
			// hora
			// é setada como vazia
			else {
				f.setData("");
				f.setData("");

			}
		}

		// Armazena a lista gerada com a situação atual das suites, para
		// envia-la
		// a pagina jsp relacionada apresentação dos dados na tela
		req.setAttribute("list", lista);

		// Ao executar o método get deste servlet a pagina monitor.jsp é
		// carregada
		// Essa pagina contém informações sobre todas as suites. Nessa etapa é a
		// requisição feita pelo usuario é respondida
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/monitor.jsp");
		dispatcher.forward(req, resp);
	}

	// Destrutor do método
	@Override
	public void destroy() {
		super.destroy();
	}

}
