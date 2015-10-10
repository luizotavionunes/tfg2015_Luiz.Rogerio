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

/*
 * Este servlet responde a requisições do usuario, para mostrar todos os eventos
 * que ocorrerão. Caso seja de interesse pode-se excluir um evento também.
 * 
 */
@WebServlet("/gerenciaevt.do")
public class EventosController extends HttpServlet {

	// Construtor do método
	public EventosController() {
		// TODO Auto-generated constructor stub
	}

	// Responde a requisições do pelo método GET para apresentar a lista de
	// todos os eventos
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Obtendo ação desejada da URL acessada
		String acao = req.getParameter("acao");
		// Instanciando objetos para fazer acesso ao banco de dados
		EventosDAO evtDAO = new EventosDAO();
		if (acao.equals("listar")) {

			// Obtendo uma com todos os eventos do banco de dados
			List<Eventos> lista = evtDAO.buscaTodos();
			// Armazenando essa lista em um objeto para posterior
			// obtenção na página jsp
			req.setAttribute("listaEv", lista);
			// Respondendo a requisição com a lsita de eventos
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listevt.jsp");
			dispatcher.forward(req, resp);
		}

		/*
		 * Talvez essa função de exclusão não seja necessária, pois os eventos
		 * serão excluidos apenas quando um faturamento relacionado a aquele
		 * evento for excluido
		 * 
		 * }else if (acao.equals("excluir")){ Eventos evt = new Eventos();
		 * 
		 * String id = req.getParameter("id"); if (id != null)
		 * evt.setId(Integer.parseInt(id)); evtDAO.excluir(evt);
		 * resp.sendRedirect("gerenciaevt.do?acao=listar");
		 * 
		 * 
		 * }
		 */

	}

	// Destrutor do método
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
