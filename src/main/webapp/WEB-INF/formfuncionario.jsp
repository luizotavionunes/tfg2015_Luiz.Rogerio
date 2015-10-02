<?xml version="1.0" encoding="UTF-8" ?>
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Funcionarios"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>



</head>
<body>

	<%
		Funcionarios fun = (Funcionarios)request.getAttribute("func");
	%>

	<form action="funcontroller.do" method="post">
		Id: <input type="text" name="id" readonly="readonly" value="<%=fun.getId()%>"/><br/>
		Nome: <input type="text" name="nome" value="<%=fun.getNome()%>"/><br/>
		CPF: <input type="text" name="cpf" value="<%=fun.getCpf()%>"/><br/>
		Login: <input type="text" name="login" value="<%=fun.getLogin()%>"/><br/>
		Senha: <input type="text" name="senha"value="<%=fun.getSenha()%>"/><br/>
		Email: <input type="text" name="email"value="<%=fun.getEmail()%>"/><br/>
		Endereço: <input type="text" name="endereco" value="<%=fun.getEndereço()%>"/><br/>
		Telefone: <input type="text" name="telefone" value="<%=fun.getTelefone()%>"/><br/>
		Acesso: <input type="text" name="acesso"value="<%=fun.getAcesso()%>"/><br/>
		
		<input type="submit" value="Salvar">

		
	
	
	</form>


</body>
</html>