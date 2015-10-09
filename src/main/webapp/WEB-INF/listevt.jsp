<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page
	import="br.com.sistematemporeal.persistencia.entidades.Eventos"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

	
		<table border="1">
		<tr>
			<th>ID</th>
			<th>SUITE</th>
			<th>DATA ENTRADA</th>
			<th>HORA ENTRADA</th>
			<th>DATA SAÍDA</th>
			<th>HORA SAÍDA</th>
			<th>VALOR SUITE</th>
			<th>VALOR TOTAL</th>
		</tr>
		<%
			List<Eventos> listaEvt = (List<Eventos>) request.getAttribute("listaEv");
		%>

		<%
			for (Eventos f : listaEvt) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId());
				%>
			</td>
			<td><%=f.getId_sensor() %></td>
			<td><%=f.getData_inicio() %></td>
			<td><%=f.getHora_inicio() %></td>
			<td><%=f.getData_fim() %></td>
			<td><%=f.getHora_fim() %></td>
			<td><%=f.getValor() %></td>
			<td><%=f.getValor_total() %></td>

		</tr>
		<%
			}
		%>


	</table>


</body>
</html>