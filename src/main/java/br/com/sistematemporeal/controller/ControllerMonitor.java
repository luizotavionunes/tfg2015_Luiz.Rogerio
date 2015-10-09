package br.com.sistematemporeal.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistematemporeal.persistencia.entidades.Eventos;
import br.com.sistematemporeal.persistencia.entidades.Geral_Sensor;
import br.com.sistematemporeal.persistencia.jdbc.EventosDAO;
import br.com.sistematemporeal.persistencia.jdbc.Geral_SensorDAO;
import br.com.sistematemporeal.persistencia.jdbc.Log_SensorDAO;

@WebServlet("/monitor.do")
public class ControllerMonitor extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Geral_SensorDAO sensDAO = new Geral_SensorDAO();
		Geral_Sensor sens = new Geral_Sensor();
		Log_SensorDAO lgsensDAO = new Log_SensorDAO();
		Eventos ev = new Eventos();
		EventosDAO evDAO = new EventosDAO();
		List<Geral_Sensor> lista = sensDAO.buscaTodos();
		String dataI=null;
		Date dataAux=null;
		Time horaI=null;
		
		SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd");  
	    SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		for(Geral_Sensor f : lista){
			if(f.getMonitor()==1){
				ev=evDAO.buscaEventosAtivos(f.getId_sensor());
				dataAux=ev.getData_inicio();
				horaI=ev.getHora_inicio();
			    try {
					 dataI = out.format(in.parse(dataAux.toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				f.setData(dataI);
				f.setHora(horaI);
			}else{
				f.setData("");
				f.setData("");
				
			}
		}
	      
	      

		
		
		req.setAttribute("list", lista);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/monitor.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
