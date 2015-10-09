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

//http://localhost:8080/tfg_projeto/funcontroller.do?nome=ze&senha=123&login=zezao
@WebServlet("/registraentrada.do")
public class ControllerRegistraEntrada extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Geral_SensorDAO sensGeDAO = new Geral_SensorDAO();
		Geral_Sensor sensGe = new Geral_Sensor();

		Integer id_sensor = Integer.parseInt(req.getParameter("id_sensor"));
		sensGe = sensGeDAO.buscaPorId(id_sensor);

		if (sensGe.getEstado() != 0 && sensGe.getMonitor() == 0) {
			Eventos evt = new Eventos();
			evt.setEntrada(1);
			evt.setSaida(0);
			evt.setId_sensor(id_sensor);
			evt.setValor(sensGe.getPreco().toString());

			EventosDAO evtDAO = new EventosDAO();
			evtDAO.cadastrar(evt);
			sensGeDAO.monitor(1, id_sensor);

			System.out.println("Entrada registrada com sucesso.");
		} else
			System.out.println("O sensor encontra-se desligado ou ja esta em uso.");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
