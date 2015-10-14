<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@
include file="css/style.css"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Cadastro de Faturamento</title>

       <script> 
          function mascara_data(data){ 
              var mydata = ''; 
              mydata = mydata + data; 
              if (mydata.length == 2){ 
                  mydata = mydata + '/'; 
                  document.forms[0].data.value = mydata; 
              } 
              if (mydata.length == 5){ 
                  mydata = mydata + '/'; 
                  document.forms[0].data.value = mydata; 
              } 
              if (mydata.length == 10){ 
                  verifica_data(); 
              } 
          } 
           
          function verifica_data () { 
 
            dia = (document.forms[0].data.value.substring(0,2)); 
            mes = (document.forms[0].data.value.substring(3,5)); 
            ano = (document.forms[0].data.value.substring(6,10)); 
 
            situacao = ""; 
            // verifica o dia valido para cada mes 
            if ((dia < 01)||(dia < 01 || dia > 30) && (  mes == 04 || mes == 06 || mes == 09 || mes == 11 ) || dia > 31) { 
                situacao = "falsa"; 
            } 
 
            // verifica se o mes e valido 
            if (mes < 01 || mes > 12 ) { 
                situacao = "falsa"; 
            } 
 
            // verifica se e ano bissexto 
            if (mes == 2 && ( dia < 01 || dia > 29 || ( dia > 28 && (parseInt(ano / 4) != ano / 4)))) { 
                situacao = "falsa"; 
            } 
    
            if (document.forms[0].data.value == "") { 
                situacao = "falsa"; 
            } 
    
            if (situacao == "falsa") { 
                alert("Data inválida!"); 
                document.forms[0].data.focus(); 
            } 
          } 
 
          function mascara_hora(hora){ 
              var myhora = ''; 
              myhora = myhora + hora; 
              if (myhora.length == 2){ 
                  myhora = myhora + ':'; 
                  document.forms[0].hora.value = myhora; 
              } 
              if (myhora.length == 5){ 
                  verifica_hora(); 
              } 
          } 
           
          function verifica_hora(){ 
              hrs = (document.forms[0].hora.value.substring(0,2)); 
              min = (document.forms[0].hora.value.substring(3,5)); 
               
             // alert('hrs '+ hrs); 
             // alert('min '+ min); 
               
              situacao = ""; 
              // verifica data e hora 
              if ((hrs < 00 ) || (hrs > 23) || ( min < 00) ||( min > 59)){ 
                  situacao = "falsa"; 
              } 
               
              if (document.forms[0].hora.value == "") { 
                  situacao = "falsa"; 
              } 
 
              if (situacao == "falsa") { 
                  alert("Hora inválida!"); 
                  document.forms[0].hora.focus(); 
              } 
          }   	
     
   

          
       </script> 


</head>
<body>
<div id="footer">
		<h1>SISTEMA DE MONITORAMENTO EM TEMPO REAL</h1>
	</div>

	<div id="nav">
		<%@include file="menuf.jsp"%>
	</div>

	<div id="section">
				<h2>Cadastrar Faturamento</h2>
		<p>	<form action="fatinf.do" method="post">
		Data de entrada: <input type="text" name="data" OnKeyUp="mascara_data(this.value)" maxlength="10"> dd/mm/aaaa<br> 
	 	Hora de entrada: <input type="text" name="hora" OnKeyUp="mascara_hora(this.value)" maxlength="5"> hh:mm<br> 
		Valor: <input type="text" name="valor_informado" /><br /> 
		<input	type="submit" value="Salvar" onclick="return confirm('Deseja confirmar o faturamento?')">
	</form></p>
	</div>

	<div id="footer">Copyright © tfgluizerogerio.ddns.net</div>

</body>
</html>




