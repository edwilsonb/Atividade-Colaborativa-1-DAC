<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.VendaDao"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.VeiculoDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Venda"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Veiculo"%>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.Collections"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Vendas Cadastradas</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Lista de Vendas Cadastradas</h1>
    <table>
        <thead>
            <tr>                
                <th>Id</th>
                <th>Veículo</th>
                <th>Código do Serviço</th>
                <th>Valor da Venda</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
            
            VendaDao vendaDao = new VendaDao();
            VeiculoDao veiculoDao = new VeiculoDao();
            
            List<Venda> lista = vendaDao.all();
            
            Collections.sort(lista);
            
            for (int i=0; i<lista.size(); i++) {
                Venda venda = lista.get(i);
                Veiculo veiculo = veiculoDao.buscarPorId(venda.getId_Veiculo());
                %>
                <tr>
                    <td><%=venda.getId()%></td>
                    <td><%=veiculo.getModelo()%></td>
                    <td><%=venda.getCodServico()%></td>
                    <td><%=venda.getValorVenda()%></td>
                    <td>
                        <a href="deleteVenda?id=<%=venda.getId()%>"><button class="button-remover">Remover</button></a>
                        <a href="selectVenda?id=<%=venda.getId()%>"><button class="button-editar">Editar</button></a>
                    </td>
                </tr>
            <%} %>
        </tbody>
    </table>
    <div class="capsbtn">
    <a href="index.html"><button>Voltar</button></a>
    <a href="ADDVenda.jsp"><button>Adicionar Venda</button></a></div>
</body>
</html>
