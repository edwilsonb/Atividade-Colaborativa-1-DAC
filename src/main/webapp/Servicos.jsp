<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="br.com.atividade_colaborativa1.dao.ServicoDao"%>
<%@ page import="br.com.atividade_colaborativa1.entidades.Servico"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Collections"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <title>Serviços</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Lista de Serviços</h1>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Tipo</th>
        <th>Código do Serviço</th>
        <th>Data</th>
        <th>Descrição</th>
        <th>Valor</th>
        <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <%
        ServicoDao servicoDao = new ServicoDao();
        List<Servico> lista = servicoDao.all();
        Collections.sort(lista);
        for (Servico servico : lista) {
    %>
    <tr>
        <td><%= servico.getId() %></td>
        <td><%= servico.getTipo() %></td>
        <td><%= servico.getCodServico() %></td>
        <td><%= servico.getData() %></td>
        <td><%= servico.getDescricao() %></td>
        <td><%= servico.getValor() %></td>
        <td>
            <a href="deleteServico?id=<%= servico.getId() %>"><button class="button-remover">Remover</button></a>
            <a href="selectServico?id=<%= servico.getId() %>"><button class="button-editar">Editar</button></a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<div class="capsbtn">
	<a href="index.html"><button>Voltar</button></a>
    <a href="ADDServico.html"><button>Adicionar Serviço</button></a>
</div>
</body>
</html>
