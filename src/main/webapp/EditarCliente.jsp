<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.ClienteDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Cliente"%>
<%@ page import= "java.util.List"%>   
<!DOCTYPE html>
<html lang = pt-br>
<head>
<meta charset="utf-8">
<title>Editar Cliente</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
Object id = request.getAttribute("id");
String idCli = id.toString();
ClienteDao cliDao = new ClienteDao();
Cliente recuperado = cliDao.byId(Long.parseLong(idCli));
String nome = (String)recuperado.getNome();
String endereco = (String)recuperado.getEndereco();
String data = (String)recuperado.getDataCadastro();
%>
	<h1>Editar Cliente</h1>
		
		<form id="Cliente-form" action="updateCliente">
			
			<label for="id">Id:</label>
			<input type="text" id="id" name="id" value= "<%=idCli%>" readonly />
			
			
			<label for="nome">Nome:</label>
			<input type="text" id="nome" name="nome" value= "<%=nome%>" required />
			
			<label for="endereço">Endereço:</label>
			<input type="text" id="endereço" name="endereço" value= "<%=endereco%>" required />

			<label for="data">Data do cadastro:</label>
			<input type="date" id="data" name="data" value= "<%=data%>" required />
			<div class = "capsbtn">
			<button type="submit">Confirmar</button>
			<a href="Clientes.jsp"><button class ="button-remover" style={backgroud-color:red}>Cancelar</button></a>
			</div>
		</form>
	
	
	</body>
</html>