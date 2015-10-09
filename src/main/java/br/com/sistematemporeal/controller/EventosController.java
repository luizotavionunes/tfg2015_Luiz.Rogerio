package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Faturamento;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;


@WebServlet("/gerenciaevt.do")
public class EventosController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao= req.getParameter("acao");
		EventosDAO evtDAO = new EventosDAO();
		if(acao.equals("listar")){
			
			List<Eventos> lista = evtDAO.buscaTodos();
			
			req.setAttribute("listaEv", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listevt.jsp");
			dispatcher.forward(req, resp);
			
		
		}else if (acao.equals("excluir")){
			Eventos evt = new Eventos();
			
			String id = req.getParameter("id");
			if (id != null)
				evt.setId(Integer.parseInt(id));
			evtDAO.excluir(evt);
			resp.sendRedirect("gerenciaevt.do?acao=listar");
			
			
		}
		
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
