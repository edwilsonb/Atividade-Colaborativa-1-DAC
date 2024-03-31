<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.PecaDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Peca"%>
<%@ page import= "java.util.List"%>   
<!DOCTYPE html>
<html lang = pt-br>
<head>
<meta charset="utf-8">
<title>Editar Peca</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
Object id = request.getAttribute("id");
String idPec = id.toString();
PecaDao pecDao = new PecaDao();
Peca recuperado = pecDao.byId(Long.parseLong(idPec));
String nome = (String)recuperado.getNome();
long codPeca = recuperado.getCodPeca();
float valor = recuperado.getValor();
%>
	<h1>Editar Peça</h1>
		
		<form id="Peca-form" action="updatePeca">
			
			<label for="id">Id:</label>
			<input type="text" id="id" name="id" value= "<%=idPec%>" readonly />
			
			
			<label for="nome">Nome:</label>
			<input type="text" id="nome" name="nome" value= "<%=nome%>" required />
			
			<label for="codPeca">Código da Peça:</label>
			<input type="text" id="codPeca" name="codPeca" value= "<%=codPeca%>" required />

			<label for="valor">Valor:</label>
			<input type="number" id="valor" step =".01" name="valor" value= "<%=valor%>" required />
			<div class = "capsbtn">
			<button type="submit">Confirmar</button>
			</div>
		</form>
		<a href="Pecas.jsp"><button class ="button-remover" style={backgroud-color:red}>Cancelar</button></a>
	</body>
</html>