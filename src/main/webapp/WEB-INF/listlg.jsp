<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Log_Sensor"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="css/style.css"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Logs dos Sensores</title>

<script type="text/javascript">
	function confirmaExclusao(id) {
		if (window.confirm('Tem certeza que deseja excluir este registro?')) {
			location.href = "lgsensor.do?acao=excluirlg&id=" + id;
		}
	}
	
	

</script>

</head>
<body>
		<%
			List<Log_Sensor> lista = (List<Log_Sensor>) request.getAttribute("listlg");
		%>

	<div id="footer">
		<h1>SISTEMA DE MONITORAMENTO EM TEMPO REAL</h1>
	</div>

	<div id="nav">
		<%@include file="menua.jsp"%>
	</div>

	<div id="section">
		<h2>Lista de Funcionários Cadastrados no Sistema</h2>
		<p>
			<table border="1">
		<tr>
			<th>ID</th>
			<th>CPF FUNCIONÁRIO</th>
			<th>SUITE</th>
			<th>DATA DESLIGAMENTO</th>
			<th>HORA DESLIGAMENTO</th>
			<th>DATA RELIGAMENTO</th>
			<th>HORA RELIGAMENTO</th>
			<th>MOTIVO</th>

		</tr>

		<%
			for (Log_Sensor f : lista) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId());
				%>
			</td>
			<td><%=f.getCpf_funcionario() %></td>
			<td><%=f.getId_sensor() %></td>
			<td><%=f.getData_inicio() %></td>
			<td><%=f.getHora_inicio() %></td>
			<td><%=f.getData() %></td>
			<td><%=f.getHora() %></td>
			<td><%=f.getObservacao() %></td>
			<td><a href="javascript:confirmaExclusao(<%=f.getId()%>)">Excluir</a></td>

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