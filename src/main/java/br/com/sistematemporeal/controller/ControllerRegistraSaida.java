package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Geral_Sensor;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.Geral_SensorDAO;

@WebServlet("/registrasaida.do")
public class ControllerRegistraSaida extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id_sensor = Integer.parseInt(req.getParameter("id_sensor"));
		
		EventosDAO evtDAO = new EventosDAO();
		Eventos evt = new Eventos();
		
		Geral_SensorDAO sensGeDAO = new Geral_SensorDAO();
		Geral_Sensor sensGe = new Geral_Sensor();
		sensGe= sensGeDAO.buscaPorId(id_sensor);
		
		if(sensGe.getEstado()!=0 && sensGe.getMonitor()==1 ){
		
		evt.setId(evtDAO.fechaEventoAux(id_sensor));

		
		evtDAO.registraSaida(evt);
		sensGeDAO.monitor(0, id_sensor);
		System.out.println("Saida registrada com sucesso.");
		} else
			System.out.println("O sensor encontra-se desligado ou está em manutenção.");
		
	}
	
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
