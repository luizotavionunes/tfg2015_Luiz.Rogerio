package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Faturamento;
import br.com.sistematemporeal.persistencia.entidades.Funcionarios;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.FaturamentoDAO;
import br.com.sistematemporeal.persistencia.jdbc.FuncionariosDAO;

@WebServlet("/fatinf.do")
public class FaturamentoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/infat.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Pegando valor do faturamento
		String valor_informado = req.getParameter("valor_informado");
		// Pegando a data inicial do faturamento (data de entrada do
		// funcionario)
		String data = req.getParameter("data");
		// Pegando a hora inicial do faturamento (hora de entrada do
		// funcionario)
		String hora = req.getParameter("hora");
		// Criando uma variavel do tipo date, para receber a data do tipo string
		// no formato "dd//MM/yyyy"
		Date date = null;
		// Criando uma variavel do tipo string, para armazenar a variavel do
		// tipo date (Date date) no formato "yyyy/MM/dd"
		String dateDB = null;

		// Fazendo a primeira conversao, data do tipo string vinda, da pagina
		// jsp, para o tipo date no formato "dd/MM/yyyy"
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Fazendo a segunda conversão do tipo date para o tipo String, mudando
		// oformato para "yyyy/MM/dd"
		dateDB = new SimpleDateFormat("yyyy-MM-dd").format(date);
		// DateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");

		// Fazendo outra conversao do tipo string para o tipo Date sql, para ser
		// armazenada no banco de dados
		java.sql.Date datec = null;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			datec = new java.sql.Date(((java.util.Date) format.parse(dateDB)).getTime());
		} catch (ParseException e) {
			try {
				throw e;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// Pegando a variavel hora do tipo String e convertendo para o formato
		// "HH:mm" do tipo date
		SimpleDateFormat formatadorH = new SimpleDateFormat("HH:mm");
		Date horaDB = null;
		try {
			horaDB = formatadorH.parse(hora);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Convertendo do tipo date para o tipo Time, o qual sera inserido no
		// banco de dados
		Time timeDB = new Time(horaDB.getTime());

		// Criando um objeto do tipo faturamento, que será inserido no banco de
		// dados
		Faturamento fat = new Faturamento();

		// Criando um objeto do tipo funcionario, para armazenar os atributos de
		// login, como cpf do funcionario
		Funcionarios funAUT = new Funcionarios();

		// Pegando a sessão na qual o funcionario está logado
		HttpSession sessao = req.getSession();

		// Atribuindo todos os atributos do funcionario logado para o objeto
		// funcionario criado previamente
		funAUT = (Funcionarios) sessao.getAttribute("funAUT");
		// System.out.println(funAUT.getCpf());

		// Atribuindo o valor do cpf do funcionario no faturamento que será
		// registrado
		fat.setCpf_funcionario(funAUT.getCpf());
		// Atribuindo o valor do faturamento, ao objeto faturamento a ser
		// registrado
		fat.setValor_informado(valor_informado);
		// Atribuindo a data de entrada do faturamento, ao objeto faturamento a
		// ser registrado
		fat.setData_inicio(datec);
		// Atribuindo a hora de entrada do faturamento, ao objeto faturamento a
		// ser registrado
		fat.setHora_inicio(timeDB);
		// Criando um objeto auxiliar para inserir o faturamento no banco de
		// dados
		FaturamentoDAO fatDAO = new FaturamentoDAO();
		// Cadastrando o faturamento no banco de dados e recebendo o id o do
		// faturamento como retorno
		int ft = fatDAO.cadastrar(fat);

		// FaturamentoDAO ftDAO = new FaturamentoDAO();

		// Atribuindo ao objeto faturamento todos os atributos relacionados ao
		// id pesquisado..id obtido do cadastro do faturamento
		fat = fatDAO.buscaPorId(ft);
		// System.out.println(fat.getId());

		// Variavel para atribuição da soma de todos os eventos relacionados a
		// um faturamento
		Integer soma = 0;
		EventosDAO evtDAO = new EventosDAO();
		// Atribuindo a uma lista todos os eventos relacionados ao faturamento
		// de uma determinada data
		List<Eventos> listaEVT = evtDAO.totalEventos(datec, fat.getData(), timeDB, fat.getHora(), fat.getId());
		for (Eventos e : listaEVT) {
			soma += e.getValor_total();
		}
		
		// Verificando se o faturamento dos eventos é compátivel com o valor informado ou nao
		if (soma > Integer.parseInt(fat.getValor_informado())) {
			fat.setTotal_eventos(soma);
			fat.setEstado("Faturamento incompativel");
			fatDAO.insereSoma(fat);

		} else {
			fat.setTotal_eventos(soma);
			fat.setEstado("Faturamento Ok");
			fatDAO.insereSoma(fat);
		}
		
		// Redirecionando a pagina para o menu
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/menufunc.jsp");
		dispatcher.forward(req, resp);

	}

}
