package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Geral_Sensor;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.Geral_SensorDAO;

/**
 * Este servlet é responsável por registrar a saida de um cliente no banco de
 * dados. Recebe como parâmetro via URL pelo método POST o id_sensor (id da
 * suite) em que se deseja registrar a saida.
 * 
 * @author luiz
 *
 */
@WebServlet("/registrasaida.do")
public class ControllerRegistraSaida extends HttpServlet {

	// construtor do método
	public ControllerRegistraSaida() {

	}

	/*
	 * Método que faz o update no banco de dados registrando a saida de um
	 * cliente. A hora e data em que o evento ocorre é registrada por comando
	 * SQL. (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Armazenando o id_do sensor(suite) para registrar a saida de um
		// cliente
		Integer id_sensor = Integer.parseInt(req.getParameter("id_sensor"));

		// Instanciando objetos que serão utilizados para fazer acesso ao
		// banco de dados para buscar informações ou altera-las
		EventosDAO evtDAO = new EventosDAO();
		Eventos evt = new Eventos();
		Geral_SensorDAO sensGeDAO = new Geral_SensorDAO();
		Geral_Sensor sensGe = new Geral_Sensor();
		// Fazendo uma busca na tabela geral sensor, com o parametro id_sensor
		// para checar o estado da respectiva suite
		sensGe = sensGeDAO.buscaPorId(id_sensor);

		// Caso a suite nao esteja em manutenção e esteja ocupada
		// a saida do cliente será registrada no banco de dados
		if (sensGe.getEstado() != 0 && sensGe.getMonitor() == 1) {
			// Obtendo o id do evento que corresponde ao sensor em que se deseja
			// registrar a saida
			evt.setId(evtDAO.fechaEventoAux(id_sensor));
			// Registrando a saida do evento no banco de dados, com o id do
			// evento
			// obtido da linha anterior
			evtDAO.registraSaida(evt);
			// Alterando a suite para desocupada na tabela de monitoramento de
			// suites
			sensGeDAO.monitor(0, id_sensor);
			System.out.println("Saida registrada com sucesso.");
		} else
			System.out.println("O sensor encontra-se desligado ou está em manutenção.");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
