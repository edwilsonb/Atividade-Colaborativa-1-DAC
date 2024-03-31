<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.VeiculoDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Veiculo"%>
<%@ page import= "java.util.List"%>   
<%@ page import= "java.util.Collections"%>   
<!DOCTYPE html>
<html lang = pt-br>
<head>
<meta charset="utf-8">
<title>Veículos Disponíveis</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<header>
		<div class="menu">
			<a class="menu-item" href="index.html">Início</a>
			<a class="menu-item" href="">Sair</a>
		</div>
	</header>
	<h1>Lista de Veículos Disponíveis</h1>
	<div class="tabela">
		<table>
			<thead>
				<tr>				
					<th>Id</th>
					<th>Id do Cliente</th>
					<th>Placa</th>
					<th>Modelo</th>
					<th>Marca</th>
					<th colspan="2">Ações</th>
				</tr>
			</thead>
			<tbody>
				<%
				
				VeiculoDao vei = new VeiculoDao();
				vei.criaTabelaVeiculo();
				
				List<Veiculo> lista = vei.all();
				
				Collections.sort(lista);
				
				for (int i=0; i<lista.size(); i++) { %>
				<tr>
					<td><%=lista.get(i).getId()%></td>
					<td><%=lista.get(i).getId_Cliente()%></td>
					<td><%=lista.get(i).getPlaca()%></td>
					<td><%=lista.get(i).getModelo()%></td>
					<td><%=lista.get(i).getMarca()%></td>
					<td><a href="deleteVeiculo?id=<%=lista.get(i).getId()%>"><button class="button-remover">Remover</button></a></td>
					<td><a href="selectVeiculo?id=<%=lista.get(i).getId()%>&idCliente=<%=lista.get(i).getId_Cliente()%>"><button class="button-editar">Editar</button></a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<div class = "capsbtn">
	<a href="ADDVeiculo.html"><button>Adicionar Veículo</button></a></div>
	</body>
</html>