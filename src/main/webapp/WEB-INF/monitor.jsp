<?xml version="1.0" encoding="UTF-8" ?>
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Geral_Sensor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Monitoramento das Suites</title>
</head>
<body>
<h1> Monitoramento das Suites</h1>

<%@include file="menu2.jsp" %>
		<%
			int aux=0;
			List<Geral_Sensor> lista = (List<Geral_Sensor>) request.getAttribute("list");
		%>

<table border="1">

		<%
			for (Geral_Sensor f : lista) {
				//for(int i=0;i<6;i++){
					
				
				if(aux==3){
		%>
		<tr></tr>
		<%} %>
<td><pre>
			SUITE: <%out.print(f.getId_sensor()); %>
			
  Estado: <%if(f.getMonitor()==0){out.print("Desocupado");}else if(f.getMonitor()==1){out.print("Ocupado");}else {out.print("Manutenção");}%>
				
  Data entrada: <%out.print(f.getData());%>	
							
  Hora entrada: <%if(f.getHora()==null){out.print("");}else{out.print(f.getHora());};%>
  
  Tempo decorrido: <% %>		
</pre></td>

		<%	
		//if(aux==3){
			//aux++;
		
		%>
		
	<% 
		
		aux++;
			
			}
		%>


	</table>


</body>
</html>