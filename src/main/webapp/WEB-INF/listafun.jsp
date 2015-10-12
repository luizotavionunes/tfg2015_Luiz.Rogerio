<?xml version="1.0" encoding="UTF-8" ?>
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Funcionarios"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Lista de Funcionários</title>

<script type="text/javascript">
	function confirmaExclusao(id) {
		if (window.confirm('Tem certeza que deseja excluir?')) {
			location.href = "funcontroller.do?acao=excluir&id=" + id;
		}
	}
	
	
	function novofunc(){
		location.href='funcontroller.do?acao=cadastrar'
		
	}
</script>


</head>
<body>
<h1> Lista de Funcionários Cadastrados </h1>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>CPF</th>
			<th>TELEFONE</th>
			<th>ENDEREÇO</th>
			<th>EMAIL</th>
			<th>LOGIN</th>
			<th>SENHA</th>
			<th>ACESSO</th>
			<th>AÇÃO</th>
		</tr>
		<%
			List<Funcionarios> lista = (List<Funcionarios>) request.getAttribute("lista");
		%>

		<%
			for (Funcionarios f : lista) {
		%>
		<tr>
			<td>
				<%
					out.print(f.getId());
				%>
			</td>
			<td><%=f.getNome()%></td>
			<td><%=f.getCpf()%></td>
			<td><%=f.getTelefone()%></td>
			<td><%=f.getEndereço()%></td>
			<td><%=f.getEmail()%></td>
			<td><%=f.getLogin()%></td>
			<td><%=f.getSenha()%></td>
			<td><%=f.getAcesso()%></td>
			<td><a href="javascript:confirmaExclusao(<%=f.getId()%>)">Excluir</a> | <a href="funcontroller.do?acao=alterar&id=<%=f.getId()%>"> Editar </a> </td>

		</tr>
		<%
			}
		%>


	</table>
		<input type="button" value="Cadastrar" onclick="javascript:novofunc()">
</body>
</html>