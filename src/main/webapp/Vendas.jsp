<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "br.com.atividade_colaborativa1.dao.VendaDao"%>
<%@ page import= "br.com.atividade_colaborativa1.entidades.Venda"%>
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
                <th>Código da Venda</th>
                <th>Id do Veículo</th>
                <th>Código do Serviço</th>
                <th>Valor da Venda</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
            
            VendaDao vendaDao = new VendaDao();
            
            List<Venda> lista = vendaDao.all();
            
            Collections.sort(lista);
            
            for (int i=0; i<lista.size(); i++) { %>
            <tr>
                <td><%=lista.get(i).getId()%></td>
                <td><%=lista.get(i).getCodVenda()%></td>
                <td><%=lista.get(i).getId_Veiculo()%></td>
                <td><%=lista.get(i).getCodServico()%></td>
                <td><%=lista.get(i).getValorVenda()%></td>
                <td>
                <a href="deleteVenda?id=<%=lista.get(i).getId()%>"><button class="button-remover">Remover</button></a>
                <a href="selectVenda?id=<%=lista.get(i).getId()%>"><button class="button-editar">Editar</button></a>
                </td>
            </tr>
            <%} %>
        </tbody>
    </table>
    <div class="capsbtn"><a href="ADDVenda.html"><button>Adicionar Venda</button></a></div>
</body>
</html>
