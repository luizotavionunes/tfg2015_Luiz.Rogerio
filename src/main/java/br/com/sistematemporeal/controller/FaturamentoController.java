package br.com.sistematemporeal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Faturamento;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.jdbc.FaturamentoDAO;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;
/*
public class FaturamentoController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String valor_informado = req.getParameter("valor_informado");

		Faturamento fat = new Faturamento();
		
		if(id!=null)
			fat.setId(Integer.parseInt(id));
		

			FaturamentoDAO fatDAO = new FaturamentoDAO();

			FuncionariosDAO funDAO = new FuncionariosDAO();
			funDAO.salvar(fun);
			resp.sendRedirect("funcontroller.do?acao=listar");
			
	}

}*/
