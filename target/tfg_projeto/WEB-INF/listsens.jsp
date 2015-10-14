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
<title>Estado dos Sensores</title>
</head>
<body>
<h1> Estago Geral dos Sensores </h1>
<table border="1">
		<tr>
			<th>ID</th>
			<th>ESTADO</th>
		</tr>
		<%
			List<Geral_Sensor> lista = (List<Geral_Sensor>) request.getAttribute("lista");
		%>

		<%
			for (Geral_Sensor f : lista) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId_sensor());
				%>
			</td>
			<td><%
				if(f.getEstado()==0)
					out.print("Desligado");
				else
					out.print("Ligado");	
			
			
			%></td>

			<td><a href="lgsensor.do?acao=on&id=<%=f.getId_sensor()%>">Ligar</a> | <a href="lgsensor.do?acao=off&id=<%=f.getId_sensor()%>">Desligar</a> </td>

		</tr>
		<%
			}
		%>


	</table>


</body>
</html>