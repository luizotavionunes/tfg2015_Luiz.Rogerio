package br.com.sistematemporeal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;

@WebServlet("/logincontroller.do")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Acessando sessão, parametro false, diz que nao sera criada uma sessão caso ela nao exista
		HttpSession sessao = req.getSession(false);
		if(sessao != null){
			sessao.invalidate();
		}
		resp.sendRedirect("login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		
		Funcionarios fun = new Funcionarios();
		FuncionariosDAO funDAO = new FuncionariosDAO();

		fun.setLogin(login);
		fun.setSenha(senha);
		
		
		Funcionarios funAUT = funDAO.autenticar(fun);
		//fun.setAcesso(funAUT.getAcesso());
		//fun.setCpf(funAUT.getCpf());
		//System.out.println(acesso);
		//System.out.println(funAUT.getAcesso());
		
		if(funAUT != null){
			HttpSession sessao = req.getSession();
			if(funAUT.getAcesso()==1){
				sessao.setAttribute("funAUT", funAUT);
				sessao.setMaxInactiveInterval(60*5);
				req.getRequestDispatcher("WEB-INF/menufunc.jsp").forward(req, resp);
			} else if(funAUT.getAcesso()==2){
				sessao.setAttribute("funAUT", funAUT);
				sessao.setMaxInactiveInterval(60*5);
				req.getRequestDispatcher("WEB-INF/menuadm.jsp").forward(req, resp);
				
			} else resp.getWriter().print("<script> window.alert('Voce não possui permissão para acessar essa pagina!'); location.href='login.html'; </script>");
			
		} else {
			resp.getWriter().print("<script> window.alert('Usuario não encontrado!'); location.href='login.html'; </script>");
			
		}
		
	
	}
	

}
