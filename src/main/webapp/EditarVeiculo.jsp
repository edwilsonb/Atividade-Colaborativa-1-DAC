<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.VeiculoDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Veiculo"%>
<%@ page import= "java.util.List"%>   
<!DOCTYPE html>
<html lang = pt-br>
<head>
<meta charset="utf-8">
<title>Editar Veículo</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
Object id = request.getAttribute("id");
Object idCliente = request.getAttribute("idCliente");
String idVei = id.toString();
String idCli = id.toString();
VeiculoDao veiDao = new VeiculoDao();
Veiculo recuperado = veiDao.buscarPorId((Long.parseLong(idVei)));
String placa = (String)recuperado.getPlaca();
String marca = (String)recuperado.getMarca();
String modelo = (String)recuperado.getModelo();
%>
<header>
	<div class="menu">
		<a class="menu-item" href="index.html">Início</a>
		<a class="menu-item" href="">Sair</a>
	</div>
</header>
	<h1>Editar Veiculo</h1>
		
		<form id="Veiculo-form" action="updateVeiculo">
			
			<label for="id">Id:</label>
			<input type="text" id="id" name="id" value= "<%=idVei%>" readonly />
			
			
			<label for="idCliente">Id do Cliente:</label>
			<input type="text" id="idCliente" name="idCliente" value= "<%=idCli%>" readonly />
			
			<label for="placa">Placa:</label>
			<input type="text" id="placa" name="placa" value= "<%=placa%>" required />

			<label for="marca">Marca:</label>
			<input type="text" id="marca" name="marca" value= "<%=marca%>" required />
			
			<label for="modelo">Modelo:</label>
			<input type="text" id="modelo" name="modelo" value= "<%=modelo%>" required />
			
			<div class = "capsbtn">
			<button type="submit">Confirmar</button>
			</div>
		</form>
	<a href="Veiculos.jsp"><button class ="button-remover" style={backgroud-color:red}>Cancelar</button></a>
	</body>
</html>