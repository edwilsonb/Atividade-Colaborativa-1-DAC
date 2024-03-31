package br.com.atividade_colaborativa1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.ServicoDao;
import br.com.atividade_colaborativa1.dao.VendaDao;
import br.com.atividade_colaborativa1.dao.VendaPecaDao;
import br.com.atividade_colaborativa1.entidades.Peca;
import br.com.atividade_colaborativa1.entidades.Servico;
import br.com.atividade_colaborativa1.entidades.Venda;

@WebServlet(urlPatterns ={"/VendaController","/addVenda","/allVendas"})
public class VendaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private VendaDao venDao;
    private VendaPecaDao vendaPecaDao;

    public VendaController() {
        super();
        venDao = new VendaDao();
        vendaPecaDao = new VendaPecaDao();
       
    }
    ServicoDao serDao= new ServicoDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/addVenda")) {
            Venda ven = new Venda();
            ven.setCodServico(Long.parseLong(request.getParameter("codServico")));
            ven.setId_Veiculo(Long.parseLong(request.getParameter("idVeiculo")));
            ven.setValorVenda(Float.parseFloat(request.getParameter("valorVenda")));
            ven.setId(Long.parseLong(request.getParameter("id")));
            ven.setCodVenda(Long.parseLong(request.getParameter("codVenda")));
     
            List<Peca> pecas = new ArrayList<>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.startsWith("peca")) {
                    String pecaNome = request.getParameter(paramName);
                    Peca peca = new Peca();
                    peca.setNome(pecaNome);
                    pecas.add(peca);
                }
            }
            ven.setPecas(pecas);
            
            Servico codServico = serDao.buscarPorCodServico(ven.getCodServico());
            
            ven.setValorVenda(ven.getValorVenda() + codServico.getValor());
            
            venDao.criaTabelaVenda();
            venDao.inserirVenda(ven);

            
            long idVenda = ven.getId();

            
            for (Peca peca : pecas) {
                vendaPecaDao.associarPecaAVenda(idVenda, peca.getId());
            }

            response.sendRedirect("venda-sucesso.jsp");
        } else {
        }
    }
}
