<?xml version="1.0" encoding="UTF-8" ?>
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Geral_Sensor"%>
	<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Funcionarios"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="css/style.css"%>
<title>Estado dos Sensores</title>
</head>
<body>
	<%
			List<Geral_Sensor> lista = (List<Geral_Sensor>) request.getAttribute("lista");
			Funcionarios fun = (Funcionarios)request.getSession().getAttribute("funAUT");
			
		%>
	<div id="footer">
		<h1>SISTEMA DE MONITORAMENTO EM TEMPO REAL</h1>
	</div>
	<%
		if (fun.getAcesso() == 1) {
	%>
	<div id="nav">
		<%@include file="menuf.jsp"%>
	</div>
	<%
		} else if (fun.getAcesso() == 2) {
	%>
	<div id="nav">
		<%@include file="menua.jsp"%>
	</div>
	<%
		}
	%>

	<div id="section">
		<h2 align="center">Estado Geral dos Sensores</h2>
		<p>
			<table border="1" align="center" >
				<tr>
					<th>ID</th>
					<th>ESTADO</th>
				</tr>

				<%
					for (Geral_Sensor f : lista) {
				%>
				<tr>
					<td>
						<%
							out.print(f.getId_sensor());
						%>
					</td>
					<td>
						<%
							if (f.getEstado() == 0)
									out.print("Desligado");
								else
									out.print("Ligado");
						%>
					</td>

					<td><a href="lgsensor.do?acao=on&id=<%=f.getId_sensor()%>">Ligar</a>
						| <a href="lgsensor.do?acao=off&id=<%=f.getId_sensor()%>">Desligar</a>
					</td>

				</tr>
				<%
					}
				%>


			</table>
		</p>
	</div>

	<div id="footer">Copyright © tfgluizerogerio.ddns.net</div>


</body>
</html>

