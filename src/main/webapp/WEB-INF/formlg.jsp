<?xml version="1.0" encoding="UTF-8" ?>
<%@page
	import="br.com.sistematemporeal.persistencia.entidades.Geral_Sensor"%>
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
		Geral_Sensor sens = (Geral_Sensor)request.getAttribute("sens");
	%>

	<form action="lgsensor.do" method="post">
		Id do Sensor: <input type="text" name="id" readonly="readonly" value="<%=sens.getId_sensor()%>"/><br/>
		Motivo: <input type="text" name="observacao"/><br/>
	
		
		<input type="submit" value="Desligar">

		
	
	
	</form>

</body>
</html>