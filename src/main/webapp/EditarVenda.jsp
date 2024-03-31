<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.VendaDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Venda"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <title>Editar Venda</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<%
Object id = request.getAttribute("id");
String idVenda = id.toString();
VendaDao vendaDao = new VendaDao();
Venda vendaRecuperada = vendaDao.byId(Long.parseLong(idVenda));
long codVenda = vendaRecuperada.getCodVenda();
long idVeiculo = vendaRecuperada.getId_Veiculo();
long codServico = vendaRecuperada.getCodServico();
float valorVenda = vendaRecuperada.getValorVenda();
%>
<h1>Editar Venda</h1>
<form id="Venda-form" action="updateVenda">
    <label for="id">Id:</label>
    <input type="text" id="id" name="id" value= "<%=idVenda%>" readonly />

    <label for="codVenda">Código da Venda:</label>
    <input type="text" id="codVenda" name="codVenda" value= "<%=codVenda%>" required />

    <label for="idVeiculo">ID do Veículo:</label>
    <input type="text" id="idVeiculo" name="idVeiculo" value= "<%=idVeiculo%>" required />

    <label for="codServico">Código do Serviço:</label>
    <input type="text" id="codServico" name="codServico" value= "<%=codServico%>" required />

    <label for="valorVenda">Valor da Venda:</label>
    <input type="text" id="valorVenda" name="valorVenda" value= "<%=valorVenda%>" required />

    <div class="capsbtn">
        <button type="submit">Confirmar</button>
        <a href="Vendas.jsp"><button class="button-remover">Cancelar</button></a>
    </div>
</form>
</body>
</html>
