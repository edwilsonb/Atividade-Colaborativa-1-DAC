package br.com.atividade_colaborativa1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.VendaDao;
import br.com.atividade_colaborativa1.entidades.Venda;

@WebServlet(urlPatterns ={"/VendaController","/addVenda","/allVendas","/updateVenda","/selectVenda","/deleteVenda"})
public class VendaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    VendaDao vendaDao = new VendaDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if(action.equals("/addVenda")) {
            Venda venda = new Venda();

            venda.setCodVenda(Long.parseLong(request.getParameter("codVenda")));
            venda.setId_Veiculo(Long.parseLong(request.getParameter("idVeiculo")));
            venda.setCodServico(Long.parseLong(request.getParameter("codServico")));
            venda.setValorVenda(Float.parseFloat(request.getParameter("valorVenda")));

            vendaDao.criaTabelaVenda();
            vendaDao.insereVenda(venda);

            response.sendRedirect("Vendas.jsp");
        }

        else if(action.equals("/updateVenda")){
            Venda venda = new Venda();

            venda.setId(Long.parseLong(request.getParameter("id")));
            venda.setCodVenda(Long.parseLong(request.getParameter("codVenda")));
            venda.setId_Veiculo(Long.parseLong(request.getParameter("idVeiculo")));
            venda.setCodServico(Long.parseLong(request.getParameter("codServico")));
            venda.setValorVenda(Float.parseFloat(request.getParameter("valorVenda")));

            vendaDao.atualizar(venda, venda.getId());

            response.sendRedirect("Vendas.jsp");
        }

        else if(action.equals("/selectVenda")){
            String id = request.getParameter("id");
            request.setAttribute("id", id);
            RequestDispatcher rd = request.getRequestDispatcher("EditarVenda.jsp");
            rd.forward(request, response);
        }
        else if(action.equals("/deleteVenda")) {
            long id = Long.parseLong(request.getParameter("id"));

            vendaDao.excluir(id);

            response.sendRedirect("Vendas.jsp");
        }

        else{
            System.out.println("Erro nas rotas");
        }
    }
}
