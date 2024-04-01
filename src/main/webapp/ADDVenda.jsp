<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.PecaDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Peca"%>
<%@ page import= "java.util.List"%>   
<%@ page import= "java.util.Collections"%> 
<% 
    PecaDao pecDao = new PecaDao();
    List<Peca> listaDePecas = pecDao.all(); 
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Adicionar Venda</title>
    <link rel="stylesheet" href="styles.css" />
</head>
<body>
    <h1>Adicionar Venda</h1>
    
    <form id="Venda-form" action="addVenda">
        <label for="idVeiculo">ID do Veículo:</label>
        <input type="text" id="idVeiculo" name="idVeiculo" required />

        <label for="codServico">Código do Serviço:</label>
        <input type="text" id="codServico" name="codServico" required />
        
        <label for="pecas">Peças:</label>
        <% for (Peca peca : listaDePecas) { %>
            <input type="checkbox" id="peca_<%=peca.getId()%>" name="pecas" value="<%=peca.getId()%>">
            <label for="peca_<%=peca.getId()%>"><%=peca.getNome()%></label>
            <input placeHolder="Quantidade:" type="number" id="quantidade_<%=peca.getId()%>" name="quantidade_<%=peca.getId()%>" min="1" required>
            <br>
        <% } %>

        <button type="submit">Adicionar</button>
    </form>
    <a href="index.html"><button type="button">Voltar</button></a>
</body>
</html>
