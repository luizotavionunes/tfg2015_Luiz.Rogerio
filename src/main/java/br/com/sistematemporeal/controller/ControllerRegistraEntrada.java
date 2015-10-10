package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.entidades.Geral_Sensor;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;
import br.com.sistematemporeal.persistencia.jdbc.Geral_SensorDAO;

/**
 * Esse servlet recebe uma requisição via método POST, para registrar a entrada
 * de um cliente, ou seja a criação de um novo evento. Recebe como parâmetro em
 * sua URL o id_sensor (id da suite) em que se deseja registrar a entrada de um
 * novo cliente
 * 
 * @author luiz
 *
 */
@WebServlet("/registraentrada.do")
public class ControllerRegistraEntrada extends HttpServlet {

	// Construtor do método
	public ControllerRegistraEntrada() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Fazendo oregistro de um novo cliente no banco de dados (criando um novo
	 * evento), pelo método POST. A hora e data em que o evento ocorre é registrada
	 * por comando SQL.
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Instanciando objetos que serão utilizados para fazer acesso ao
		// banco de dados para buscar informações ou altera-las
		Geral_SensorDAO sensGeDAO = new Geral_SensorDAO();
		Geral_Sensor sensGe = new Geral_Sensor();

		// Armazenando o id_do sensor(suite) para registrar a entrada de um
		// cliente
		Integer id_sensor = Integer.parseInt(req.getParameter("id_sensor"));
		// Fazendo uma busca na tabela geral sensor, com o parametro id_sensor
		// para checar o estado da respectiva suite
		sensGe = sensGeDAO.buscaPorId(id_sensor);

		// Checando se a suite nao está em manutençao (sensor desligado)
		// ou se ja esta ocupada por algum cliente. Caso nao esteja ocupada
		// e nem esteja em manutenção, será registrada a entrada do cliente
		if (sensGe.getEstado() != 0 && sensGe.getMonitor() == 0) {
			Eventos evt = new Eventos();
			// Inserindo a entrada de um novo cliente no objeto evento
			// para posterior registro no banco de dados
			evt.setEntrada(1);
			evt.setSaida(0);
			evt.setId_sensor(id_sensor);
			// Inserindo o preço da suite de acordo com a tabela Geral_sensor,
			// que contém o preço de cada suite
			evt.setValor(sensGe.getPreco().toString());

			EventosDAO evtDAO = new EventosDAO();
			// Fazendo o cadastro do novo evento no banco de dados
			evtDAO.cadastrar(evt);
			// Mudando o estado para ocupado da suite na tabela Geral_sensor
			sensGeDAO.monitor(1, id_sensor);

			System.out.println("Entrada registrada com sucesso.");
		} else
			System.out.println("O sensor encontra-se desligado ou ja esta em uso.");

	}

	// Destrutor do método
	@Override
	public void destroy() {
		super.destroy();
	}

}
