<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.PecaDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Peca"%>
<%@ page import= "java.util.List"%>   
<%@ page import= "java.util.Collections"%>   
<!DOCTYPE html>
<html lang = pt-br>
<head>
<meta charset="utf-8">
<title>Peças Disponíveis</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Lista de Peças Disponíveis</h1>
	<table>
		<thead>
			<tr>				
				<th>Id</th>
				<th>Código</th>
				<th>Valor</th>
				<th>Nome</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<%
			
			PecaDao pec = new PecaDao();
			
			List<Peca> lista = pec.all();
			
			Collections.sort(lista);
			
			for (int i=0; i<lista.size(); i++) { %>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getCodPeca()%></td>
				<td><%=lista.get(i).getValor()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td>
				<a href="deletePeca?id=<%=lista.get(i).getId()%>"><button class="button-remover">Remover</button></a>
				<a href="selectPeca?id=<%=lista.get(i).getId()%>"><button class="button-editar">Editar</button></a>
				</td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<div class = "capsbtn">
	<a href="index.html"><button>Voltar</button></a>
	<a href="ADDPeca.html"><button>Adicionar Peça</button></a></div>
	</body>
</html>