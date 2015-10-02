package br.com.sistematemporeal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;

@WebServlet("/registrasaida.do")
public class ControllerRegistraSaida extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Eventos evt = new Eventos();
		evt.setId(id);

		EventosDAO evtDAO = new EventosDAO();
		evtDAO.registraSaida(evt);
		System.out.println("Saida registrada com sucesso.");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
