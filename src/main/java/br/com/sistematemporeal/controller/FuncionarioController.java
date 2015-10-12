package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;


/*
 * Este serlvet é reponsável por fazer a listagem, remoção e cadastro ou alteração
 * de registros da tabela funcionarios do banco de dados
 * 
 */
@WebServlet("/funcontroller.do")
public class FuncionarioController extends HttpServlet {

	// Construtor do método
	public FuncionarioController() {

	}

	
	// O método GEt responde a requisições feitas por usuarios para listar funcionarios,
	// remover e no caso de cadastro, o método get redireciona a pagina para um formulario
	// onde serão inseridos os dados para cadastro
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");

		FuncionariosDAO funDAO = new FuncionariosDAO();
		Funcionarios fun = new Funcionarios();
		if (acao.equals("excluir")) {
			String id = req.getParameter("id");
			if (id != null)
				fun.setId(Integer.parseInt(id));
			funDAO.excluir(fun);
			resp.sendRedirect("funcontroller.do?acao=listar");
			// System.out.println("Excluido com sucesso.");

		} else if (acao.equals("listar")) {
			List<Funcionarios> lista = funDAO.buscaTodos();

			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listafun.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("alterar")) {
			String id = req.getParameter("id");
			fun = funDAO.buscaPorId(Integer.parseInt(id));
			req.setAttribute("func", fun);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formfuncionario.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("cadastrar")) {

			fun.setId(0);
			fun.setNome("");
			fun.setCpf(null);
			fun.setEmail("");
			fun.setLogin("");
			fun.setAcesso(null);
			fun.setSenha("");
			fun.setEndereço("");
			fun.setTelefone("");
			req.setAttribute("func", fun);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formfuncionario.jsp");
			dispatcher.forward(req, resp);
		}
	}

	
	//O método POST é responsável por realizar o cadastro de novos funcionarios no banco
	// de dados
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		String email = req.getParameter("email");
		String endereço = req.getParameter("endereco");
		String telefone = req.getParameter("telefone");
		Integer cpf = Integer.parseInt(req.getParameter("cpf"));
		Integer acesso = Integer.parseInt(req.getParameter("acesso"));

		Funcionarios fun = new Funcionarios();
		if (id != null)
			fun.setId(Integer.parseInt(id));
		fun.setNome(nome);
		fun.setCpf(cpf);
		fun.setAcesso(acesso);
		fun.setEmail(email);
		fun.setLogin(login);
		fun.setEndereço(endereço);
		fun.setTelefone(telefone);
		fun.setSenha(senha);

		FuncionariosDAO funDAO = new FuncionariosDAO();
		funDAO.salvar(fun);
		resp.sendRedirect("funcontroller.do?acao=listar");


	}
	
	// Destrutor do método
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
