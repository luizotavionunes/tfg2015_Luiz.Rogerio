<?xml version="1.0" encoding="UTF-8" ?>
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Geral_Sensor"%>
<%@page import="java.util.List"%>
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Funcionarios"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="css/style.css"%>
<title>Monitoramento das Suites</title>
</head>
<body>
	<%
		int aux = 0;
		List<Geral_Sensor> lista = (List<Geral_Sensor>) request.getAttribute("list");
		Funcionarios fun = (Funcionarios)request.getSession().getAttribute("funAUT");
	%>


	<div id="footer">
		<h1>SISTEMA DE MONITORAMENTO EM TEMPO REAL</h1>
	</div>
	<%if(fun.getAcesso()==1){ %>
	<div id="nav">
		<%@include file="menuf.jsp"%>
	</div>
<%}else if(fun.getAcesso()==2){%>
<div id="nav">
		<%@include file="menua.jsp"%>
	</div><%} %>

	<div id="section">
	<h2 align="center" >Monitorar Suites</h2>
		<p>
			<table border="1">

				<%
					for (Geral_Sensor f : lista) {
						//for(int i=0;i<6;i++){

						if (aux == 3) {
				%>
				<tr></tr>
				<%
					}
				%>
				<td>
					<p align="center">
						<b>SUITE:</b>
						<%
							out.print(f.getId_sensor());
						%>
					</p> <br>
				<b>Estado:</b> <%
 	if (f.getMonitor() == 0) {
 			out.print("Desocupado");
 		} else if (f.getMonitor() == 1) {
 			out.print("Ocupado");
 		} else {
 			out.print("Manutenção");
 		}
 %>

					&nbsp;&nbsp;<br>
				<br><b>Data de Entrada:</b>
					<%
						out.print(f.getData());
					%> &nbsp;&nbsp;<br>
				<br><b>Hora de Entrada:</b>
					<%
						if (f.getHora() == null) {
								out.print("");
							} else {
								out.print(f.getHora());
							}
							;
					%>
&nbsp;&nbsp;
					<br>
				<br><b>Tempo Decorrido:</b>
					<%
						
					%>&nbsp;&nbsp;
				</td>

				<%
					//if(aux==3){
						//aux++;
				%>

				<%
					aux++;

					}
				%>


			</table>
		</p>
	</div>

	<div id="footer">Copyright © tfgluizerogerio.ddns.net</div>






</body>
</html>