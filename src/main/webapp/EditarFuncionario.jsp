<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.FuncionarioDAO"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Funcionario"%>
<%@ page import= "java.util.List"%>   
<!DOCTYPE html>
<html lang = pt-br>
<head>
<meta charset="utf-8">
<title>Editar Funcionário</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
Object id = request.getAttribute("id");
String idFun = id.toString();
FuncionarioDAO funDao = new FuncionarioDAO();
Funcionario recuperado = funDao.byId(Long.parseLong(idFun));
String nome = (String)recuperado.getNomeCompleto();
String endereco = (String)recuperado.getEndereco();
String cargo = (String)recuperado.getCargo();
%>
	<h1>Editar Funcionário</h1>
		
		<form id="Cliente-form" action="updateFuncionario">
			
			<label for="id">Id:</label>
			<input type="text" id="id" name="id" value= "<%=idFun%>" readonly />
			
			
			<label for="nome">Nome Completo:</label>
			<input type="text" id="nome" name="nome" value= "<%=nome%>" required />
			
			<label for="endereço">Endereço:</label>
			<input type="text" id="endereço" name="endereço" value= "<%=endereco%>" required />

			<label for="data">Cargo:</label>
			<input type="text" id="cargo" name="cargo" value= "<%=cargo%>" required />
			<button type="submit">Confirmar</button>
			<div class = "capsbtn">
			<a href="Funcionarios.jsp"><button class ="button-remover" style={backgroud-color:red}>Cancelar</button></a>
			</div>
		</form>
	
	
	</body>
</html>