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

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.entidades.Geral_Sensor;
import br.com.sistematemporeal.persistencia.entidades.Log_Sensor;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.Geral_SensorDAO;
import br.com.sistematemporeal.persistencia.jdbc.Log_SensorDAO;

@WebServlet("/lgsensor.do")
public class LogSensorController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Indentificando açao desejada
		String acao = req.getParameter("acao");

		// Instanciando objetos que serão utilizados para fazer acesso ao banco
		// de dados
		Geral_SensorDAO sensDAO = new Geral_SensorDAO();
		Geral_Sensor sens = new Geral_Sensor();
		Log_SensorDAO lgsensDAO = new Log_SensorDAO();

		// Listar o estado de todos os sensores (suites)
		if (acao.equals("listar")) {

			// Armazenando em uma lista todos os atributos de cada sensor
			List<Geral_Sensor> lista = sensDAO.buscaTodos();

			// Enviando esta lista para página JSP
			req.setAttribute("lista", lista);
			// Respondendo a requisição de listagem com a pagina que contém a
			// lista
			// com informações sobre os sensores
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listsens.jsp");
			dispatcher.forward(req, resp);

			// Ligando um sensor que esteja desligado
		} else if (acao.equals("on")) {

			// Obtendo o id do sensor que se deseja ligar
			String id = req.getParameter("id");
			// Instanciando um objeto do tipo geral_sensor, com os parametros
			// correspondentes ao
			// sensor desejado
			sens = sensDAO.buscaPorId(Integer.parseInt(id));
			// O sensor só será ligado ,caso ele esteja desligado (Estado=0->
			// Desligado)
			if (sens.getEstado() == 0) {
				// Ligando o sensor (Estado=1 -> Ligado)
				sens.setEstado(1);
				// Salvando no banco de dados as alterações
				sensDAO.ligaSensor(sens);
				// Liberando a suite para uso (Monitor=0-> Suite desocupada)
				sensDAO.monitor(0, Integer.parseInt(id));

				// Inserindo a data em que o sensor foi ligado no registro
				// Log_sensor correspondente
				lgsensDAO.fecharLog(sens.getId_log());

				resp.getWriter().print(
						"<script> window.alert('O sensor foi ligado!'); location.href='lgsensor.do?acao=listar'; </script>");
			} else {
				// Caso o sensor ja esteja ligado, uma mensagem é apresentada
				// e o redirecionamento para listagem é feito
				resp.getWriter().print(
						"<script> window.alert('O sensor já está ligado!'); location.href='lgsensor.do?acao=listar'; </script>");

			}

			// Desligando um sensor que esteja ligado
		} else if (acao.equals("off")) {
			// Obtendo o id do sensor que se deseja desligar
			String id = req.getParameter("id");
			// Instanciando um objeto do tipo geral_sensor, com os parametros
			// correspondentes ao
			// sensor desejado
			sens = sensDAO.buscaPorId(Integer.parseInt(id));
			// Caso o sensor esteja ligado, a operação sera realizada
			// (Estado=1-> Ligado)
			if (sens.getEstado() == 1) {
				// Enviando os atributos deste sensor para página JSP, que fará
				// o cadastro de desligamento
				req.setAttribute("sens", sens);
				// Alterando o estado da suite para manutenção (Monitor=2 ->
				// Manutenção)
				sensDAO.monitor(2, Integer.parseInt(id));

				EventosDAO evtDAO = new EventosDAO();
				// Caso a suite esteja ocupada no momento do desligamento, a
				// saida do cliente
				// será registrada
				if (sens.getMonitor() == 1)
					evtDAO.registraSaida(Integer.parseInt(id));
				// É feito o redirecionamento para página de cadastro de
				// desligamento de um sensor
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formlg.jsp");
				dispatcher.forward(req, resp);
			} else {
				// Caso o sensor ja esteja desligado, uma mensagem é apresentada
				// e o redirecionamento para listagem é feito
				resp.getWriter().print(
						"<script> window.alert('O sensor ja está desligado!'); location.href='lgsensor.do?acao=listar'; </script>");

			}
			// Esta opção é responsável por mostra os Logs_Sensor cadastrados ,
			// que conterão informações como
			// a data de desligamento e religamento, o funcionário que fez tais
			// alterações e outros
			// atributos
		} else if (acao.equals("listarlg")) {

			// Busca todos os logs cadastrados no banco de dados da tabela
			// Log_Sensor
			List<Log_Sensor> lista = lgsensDAO.buscaTodos();
			// Armazenas estes logs em uma lista para posterior recuperação na
			// pagina JSP
			req.setAttribute("listlg", lista);
			// Redireciona para página de listagem dos logs
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listlg.jsp");
			dispatcher.forward(req, resp);
			// Esta opção é responsável por excluir um log específico, de acordo
			// com seu Id
		} else if (acao.equals("excluirlg")) {
			// Obtem o id do log que se deseja excluir
			String id = req.getParameter("id");
			Log_Sensor lg = new Log_Sensor();
			if (id != null)
				lg.setId(Integer.parseInt(id));
			// Faz a exclusão do log
			lgsensDAO.excluir(lg);
			// Redireciona para listagem de logs
			resp.sendRedirect("lgsensor.do?acao=listarlg");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Parametros para cadastro de um novo log
		String observacao = req.getParameter("observacao");
		String id_sensor = req.getParameter("id");
		// Instanciando objetos que serão utilizados para acesso ao banco de
		// dados
		Log_Sensor lgsens = new Log_Sensor();
		Log_SensorDAO lgsensDAO = new Log_SensorDAO();
		Geral_SensorDAO sensGeDAO = new Geral_SensorDAO();
		Geral_Sensor sensGe = new Geral_Sensor();
		// Variavel responsável por armazenar o id do log cadastrado
		Integer id_log = null;

		// Criando um objeto do tipo funcionario, para armazenar os atributos de
		// login, como cpf do funcionario
		Funcionarios funAUT = new Funcionarios();
		Funcionarios admAUT = new Funcionarios();

		// Pegando a sessão na qual o funcionario está logado
		HttpSession sessao = req.getSession();

		// Atribuindo todos os atributos do funcionario logado para o objeto
		// funcionario criado previamente
		funAUT = (Funcionarios) sessao.getAttribute("funAUT");

		// Apenas será feito o cadastro caso a sessão ainda exista
		if (funAUT != null) {
			// Obtendo o cpf do funcionário que fez o cadastro de desligamento
			lgsens.setCpf_funcionario(funAUT.getCpf());
			// Armazenando informações passadas pelo funcionario
			lgsens.setObservacao(observacao);
			lgsens.setId_sensor(Integer.parseInt(id_sensor));
			// Fazendo o cadastro de desligamento no banco de dados e obtendo como
			// resposta o id do log cadastrado. O id será utilizado no caso de religamento
			// do sensor
			id_log = lgsensDAO.cadastrarDesligamento(lgsens);
			// buscando informações sobre o sensor que se deseja desligar no banco de dados
			sensGe = sensGeDAO.buscaPorId(Integer.parseInt(id_sensor));
			// Alterando seu estado para desligado
			sensGe.setEstado(0);
			// Armazenando o id do log em que foi desligado
			sensGe.setId_log(id_log);
			// Alterando as informações dos sensores, após o desligamento
			sensGeDAO.desligaSensor(sensGe);
			// Redirecionado para listagem do estado dos sensores
			resp.sendRedirect("lgsensor.do?acao=listar");
		} else
			resp.getWriter().print(
					"<script> window.alert('Você não esta logado no sistema!'); location.href='login.html'; </script>");
	}

}
