package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.entidades.Geral_Sensor;
import br.com.sistematemporeal.persistencia.entidades.Log_Sensor;
import br.com.sistematemporeal.persistencia.jdbc.Geral_SensorDAO;
import br.com.sistematemporeal.persistencia.jdbc.Log_SensorDAO;

@WebServlet("/lgsensor.do")
public class LogSensorController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");
		
		Geral_SensorDAO sensDAO = new Geral_SensorDAO();
		Geral_Sensor sens = new Geral_Sensor();
		Log_SensorDAO lgsensDAO = new Log_SensorDAO();
		
		if(acao.equals("alterar")){
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/inflg.jsp");
		dispatcher.forward(req, resp);
		
		}else if(acao.equals("listar")){
		
			List<Geral_Sensor> lista = sensDAO.buscaTodos();
			
			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listsens.jsp");
			dispatcher.forward(req, resp);
			
			
		} else if(acao.equals("on")){
			
			
			String id = req.getParameter("id");
			sens = sensDAO.buscaPorId(Integer.parseInt(id));
			//req.setAttribute("sens", sens);
			//System.out.println(sens.getEstado());
			if(sens.getEstado()==0){
				sens.setEstado(1);
				sensDAO.ligaSensor(sens);
				sensDAO.monitor(0, Integer.parseInt(id));
				//System.out.println(sens.getId_log());
				lgsensDAO.fecharLog(sens.getId_log());
				
				resp.getWriter().print("<script> window.alert('O sensor foi ligado!'); location.href='lgsensor.do?acao=listar'; </script>");
			}
			else{
				
				resp.getWriter().print("<script> window.alert('O sensor já está ligado!'); location.href='lgsensor.do?acao=listar'; </script>");
				
			}

			
			
		}else if(acao.equals("off")){
			String id = req.getParameter("id");
			sens = sensDAO.buscaPorId(Integer.parseInt(id));
			req.setAttribute("sens", sens);
			sensDAO.monitor(2, Integer.parseInt(id));
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formlg.jsp");
			dispatcher.forward(req, resp);
		}else if(acao.equals("listarlg")){
			List<Log_Sensor> lista = lgsensDAO.buscaTodos();
			
			req.setAttribute("listlg", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listlg.jsp");
			dispatcher.forward(req, resp);
		} else 	if (acao.equals("excluirlg")) {
			String id = req.getParameter("id");
			Log_Sensor lg = new Log_Sensor();
			if (id != null)
				lg.setId(Integer.parseInt(id));
			lgsensDAO.excluir(lg);
			resp.sendRedirect("lgsensor.do?acao=listarlg");
		}
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String observacao = req.getParameter("observacao");
		String id_sensor = req.getParameter("id");
		Log_Sensor lgsens = new Log_Sensor();
		Log_SensorDAO lgsensDAO = new Log_SensorDAO();
		Geral_SensorDAO sensGeDAO = new Geral_SensorDAO();
		Geral_Sensor sensGe = new Geral_Sensor();
		Integer id_fat = null;

		
		
		// Criando um objeto do tipo funcionario, para armazenar os atributos de
		// login, como cpf do funcionario
		Funcionarios funAUT = new Funcionarios();

		// Pegando a sessão na qual o funcionario está logado
		HttpSession sessao = req.getSession();

		// Atribuindo todos os atributos do funcionario logado para o objeto
		// funcionario criado previamente
		funAUT = (Funcionarios) sessao.getAttribute("funAUT");
		
		
		lgsens.setCpf_funcionario(funAUT.getCpf());
		lgsens.setObservacao(observacao);
		lgsens.setId_sensor(Integer.parseInt(id_sensor));
		id_fat = lgsensDAO.cadastrarDesligamento(lgsens);
		sensGe= sensGeDAO.buscaPorId(Integer.parseInt(id_sensor));
		sensGe.setEstado(0);
		sensGe.setId_log(id_fat);
		sensGeDAO.desligaSensor(sensGe);
		resp.sendRedirect("lgsensor.do?acao=listar");
	}

}
