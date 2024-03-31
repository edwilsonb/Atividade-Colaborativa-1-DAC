<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.FuncionarioDAO"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Funcionario"%>
<%@ page import= "java.util.List"%>   
<%@ page import= "java.util.Collections"%>   
<!DOCTYPE html>
<html lang = pt-br>
<head>
<meta charset="utf-8">
<title>Funcionarios Cadastrados</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<header>
		<div class="menu">
			<a class="menu-item" href="index.html">Início</a>
			<a class="menu-item" href="">Sair</a>
		</div>
	</header>
	<h1>Lista de Funcionarios Cadastrados</h1>
	<div class="tabela">
		<table>
			<thead>
				<tr>				
					<th>Id</th>
					<th>Nome</th>
					<th>Endereço</th>
					<th>Data de Cadastro</th>
					<th colspan="2">Ações</th>
				</tr>
			</thead>
			<tbody>
				<%
				
				FuncionarioDAO fun = new FuncionarioDAO();
				fun.criaTabelaFuncionario();
				
				List<Funcionario> lista = fun.all();
				
				Collections.sort(lista);
				
				for (int i=0; i<lista.size(); i++) { %>
				<tr>
					<td><%=lista.get(i).getId()%></td>
					<td><%=lista.get(i).getNomeCompleto()%></td>
					<td><%=lista.get(i).getEndereco()%></td>
					<td><%=lista.get(i).getCargo()%></td>
					<td><a href="deleteFuncionario?id=<%=lista.get(i).getId()%>"><button class="button-remover">Remover</button></a></td>
					<td><a href="selectFuncionario?id=<%=lista.get(i).getId()%>"><button class="button-editar">Editar</button></a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<div class = "capsbtn"><a href="ADDFuncionario.html"><button>Adicionar Funcionario</button></a></div>
	</body>
</html>