<?xml version="1.0" encoding="UTF-8" ?>
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Faturamento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="css/style.css"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>

<script type="text/javascript">
	function confirmaExclusao(id) {
		if (window.confirm('ATENÇAO!! Ao excluir este faturamento você estará excluindo todos os eventos relacionados a ele. Tem certeza que deseja excluir?')) {
			location.href = "fatinf.do?acao=excluir&id=" + id;
		}
	}
	
	

</script>


</head>
<body>

		<%
			List<Faturamento> lista = (List<Faturamento>) request.getAttribute("listaFat");
		%>

<div id="footer">
		<h1>SISTEMA DE MONITORAMENTO EM TEMPO REAL</h1>
	</div>

	<div id="nav">
		<%@include file="menua.jsp"%>
	</div>

	<div id="section">
				<h2>Lista de Faturamentos</h2>
		<p><table border="1">
		<tr>
			<th>ID</th>
			<th>CPF FUNCIONÁRIO</th>
			<th>DATA ENTRADA</th>
			<th>HORA ENTRADA</th>
			<th>DATA SAÍDA</th>
			<th>HORA SAÍDA</th>
			<th>ESTADO</th>
			<th>VALOR TOTAL</th>
			<th>VALOR INFORMADO</th>
		</tr>


		<%
			for (Faturamento f : lista) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId());
				%>
			</td>
			<td><%=f.getCpf_funcionario() %></td>
			<td><%=f.getData_inicio() %></td>
			<td><%=f.getHora_inicio() %></td>
			<td><%=f.getData() %></td>
			<td><%=f.getHora() %></td>
			<td><%=f.getEstado() %></td>
			<td><%=f.getTotal_eventos() %></td>
			<td><%=f.getValor_informado() %></td>
			<td><a href="javascript:confirmaExclusao(<%=f.getId()%>)">Excluir</a> | <a href="fatinf.do?acao=check&id=<%=f.getId()%>"> Checar </a> </td>

		</tr>
		<%
			}
		%>


	</table></p>
	</div>

	<div id="footer">Copyright © tfgluizerogerio.ddns.net</div>


	


</body>
</html>