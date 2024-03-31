<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.ClienteDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Cliente"%>
<%@ page import= "java.util.List"%>   
<%@ page import= "java.util.Collections"%>   
<!DOCTYPE html>
<html lang = pt-br>
<head>
<meta charset="utf-8">
<title>Clientes Cadastrados</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Lista de Clientes Cadastrados</h1>
	<table>
		<thead>
			<tr>				
				<th>Id</th>
				<th>Nome</th>
				<th>Endereço</th>
				<th>Data de Cadastro</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<%
			
			ClienteDao cli = new ClienteDao();
			
			List<Cliente> lista = cli.all();
			
			Collections.sort(lista);
			
			for (int i=0; i<lista.size(); i++) { %>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getEndereco()%></td>
				<td><%=lista.get(i).getDataCadastro()%></td>
				<td>
				<a href="deleteCliente?id=<%=lista.get(i).getId()%>"><button class="button-remover">Remover</button></a>
				<a href="selectCliente?id=<%=lista.get(i).getId()%>"><button class="button-editar">Editar</button></a>
				</td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<div class = "capsbtn"><a href="ADDCliente.html"><button>Adicionar Cliente</button></a></div>
	</body>
</html>