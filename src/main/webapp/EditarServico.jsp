<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="br.com.atividade_colaborativa1.dao.ServicoDao"%>
<%@ page import="br.com.atividade_colaborativa1.entidades.Servico"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Serviço</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<%
    Object id = request.getAttribute("id");
    String idServico = id.toString();
    ServicoDao servicoDao = new ServicoDao();
    Servico recuperado = servicoDao.byId(Long.parseLong(idServico));
    String tipo = recuperado.getTipo();
    long codServico = recuperado.getCodServico();
    float valor = recuperado.getValor();
    String data = recuperado.getData();
    String descricao = recuperado.getDescricao();
%>
<h1>Editar Serviço</h1>
<form id="Servico-form" action="updateServico">
    <label for="id">Id:</label>
    <input type="text" id="id" name="id" value="<%=idServico%>" readonly />

    <label for="tipo">Tipo:</label>
    <input type="text" id="tipo" name="tipo" value="<%=tipo%>" required />

    <label for="codServico">Código do Serviço:</label>
    <input type="text" id="codServico" name="codServico" value="<%=codServico%>" required />

    <label for="valor">Valor:</label>
    <input type="number" id="valor" step=".01" name="valor" value="<%=valor%>" required />

    <label for="data">Data:</label>
    <input type="date" id="dataServico" name="dataServico" value="<%=data%>" required />

    <label for="descricao">Descrição:</label>
    <textarea id="descricao" name="descricao" rows="4" cols="50" required><%=descricao%></textarea>

    <div class="capsbtn">
        <button type="submit">Confirmar</button>
    </div>
</form>
<a href="Servicos.jsp"><button class="button-remover" style={backgroud-color:red}>Cancelar</button></a>
</body>
</html>
