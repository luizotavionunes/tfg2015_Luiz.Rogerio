package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;

//http://localhost:8080/tfg_projeto/funcontroller.do?nome=ze&senha=123&login=zezao
@WebServlet("/registraentrada.do")
public class ControllerRegistraEntrada extends HttpServlet {
	static String valorquarto1 = null;
	static String valorquarto2 = null;
	static String valorquarto3 = null;
	static String valorquarto4 = null;
	static String valorquarto5 = null;
	static String valorquarto6 = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		valorquarto1 = req.getParameter("valorquarto1");
		valorquarto2 = req.getParameter("valorquarto2");
		valorquarto3 = req.getParameter("valorquarto3");
		valorquarto4 = req.getParameter("valorquarto4");
		valorquarto5 = req.getParameter("valorquarto5");
		valorquarto6 = req.getParameter("valorquarto6");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id_sensor = Integer.parseInt(req.getParameter("id_sensor"));

		

		Eventos evt = new Eventos();
		evt.setEntrada(1);
		evt.setSaida(0);
		evt.setId_sensor(id_sensor);
		if (id_sensor == 1)
			evt.setValor(valorquarto1);
		if (id_sensor == 2)
			evt.setValor(valorquarto2);
		if (id_sensor == 3)
			evt.setValor(valorquarto3);
		if (id_sensor == 4)
			evt.setValor(valorquarto4);
		if (id_sensor == 5)
			evt.setValor(valorquarto5);
		if (id_sensor == 6)
			evt.setValor(valorquarto6);

		EventosDAO evtDAO = new EventosDAO();
		evtDAO.cadastrar(evt);

		System.out.println("Entrada registrada com sucesso.");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
