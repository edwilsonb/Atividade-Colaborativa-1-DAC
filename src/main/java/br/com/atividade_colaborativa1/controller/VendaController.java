package br.com.atividade_colaborativa1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.PecaDao;
import br.com.atividade_colaborativa1.dao.ServicoDao;
import br.com.atividade_colaborativa1.dao.VendaDao;
import br.com.atividade_colaborativa1.entidades.Peca;
import br.com.atividade_colaborativa1.entidades.Servico;
import br.com.atividade_colaborativa1.entidades.Venda;

@WebServlet(urlPatterns ={"/VendaController","/addVenda","/allVendas","/updateVenda","/selectVenda","/deleteVenda"})
public class VendaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    VendaDao vendaDao = new VendaDao();
    ServicoDao SerDao = new ServicoDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if(action.equals("/addVenda")) {
                 Venda venda = new Venda();

                 venda.setId_Veiculo(Long.parseLong(request.getParameter("idVeiculo")));
                 venda.setCodServico(Long.parseLong(request.getParameter("codServico")));

         
                 long codServico = Long.parseLong(request.getParameter("codServico"));
                 Servico servico = SerDao.byCodServico(codServico);

                 double valorTotal = 0;
                 
                 PecaDao pecaDao = new PecaDao();
                 for (Peca peca : pecaDao.all()) {
                     String quantidadeParam = request.getParameter("quantidade_" + peca.getId());
                     if (quantidadeParam != null && !quantidadeParam.isEmpty()) {
                         int quantidadePeca = Integer.parseInt(quantidadeParam);
                         valorTotal += peca.getValor() * quantidadePeca;
                     }
                 }
                 if (servico != null) {
                     valorTotal += servico.getValor();
                 }

                 venda.setValorVenda((float) valorTotal);

                 vendaDao.criaTabelaVenda();
                 vendaDao.insereVenda(venda);

                 response.sendRedirect("Vendas.jsp");
             }

        else if(action.equals("/updateVenda")){
            Venda venda = new Venda();

            venda.setId(Long.parseLong(request.getParameter("id")));
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
