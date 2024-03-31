package br.com.atividade_colaborativa1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.ServicoDao;
import br.com.atividade_colaborativa1.entidades.Servico;

@WebServlet(urlPatterns ={"/ServicoController","/addServico","/allServico","/updateServico","/selectServico","/deleteServico"})
public class ServicoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    ServicoDao servDao = new ServicoDao();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if(action.equals("/addServico")) {
        	
            Servico servico = new Servico();
            
            servico.setTipo(request.getParameter("tipo"));
            servico.setCodServico(Long.parseLong(request.getParameter("codServico")));
            servico.setData(request.getParameter("dataServico"));
            servico.setDescricao(request.getParameter("descricao"));
            servico.setValor(Float.parseFloat(request.getParameter("valor")));
      
            
            servDao.criaTabelaServico();
            
            servDao.inserirServico(servico);
            
            response.sendRedirect("Servicos.jsp");
        }
        else if(action.equals("/updateServico")){
            Servico servico = new Servico();
            
            servico.setId(Long.parseLong(request.getParameter("id")));
            servico.setTipo(request.getParameter("tipo"));
            servico.setCodServico(Long.parseLong(request.getParameter("codServico")));
            servico.setData(request.getParameter("dataServico"));
            servico.setDescricao(request.getParameter("descricao"));
            servico.setValor(Float.parseFloat(request.getParameter("valor")));
            
           
            servDao.atualizarServico(servico, servico.getId());
            
            response.sendRedirect("Servicos.jsp");
        }
        else if(action.equals("/selectServico")){
            String id = request.getParameter("id");
            request.setAttribute("id", id);
            RequestDispatcher rd = request.getRequestDispatcher("EditarServico.jsp");
            rd.forward(request, response);
        }
        else if(action.equals("/deleteServico")) {
            long id = Long.parseLong(request.getParameter("id"));
            
            servDao.removerServico(id);
            
            response.sendRedirect("Servicos.jsp");
        }
        else{
            System.out.println("Erro nas rotas");
        }
    }
}
