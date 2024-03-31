package br.com.atividade_colaborativa1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.PecaDao;
import br.com.atividade_colaborativa1.entidades.Peca;


@WebServlet(urlPatterns ={"/PecaController","/addPeca","/allPeca","/updatePeca","/selectPeca","/deletePeca"})
public class PecaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PecaDao pecDao = new PecaDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if(action.equals("/addPeca")) {
		
		Peca pec = new Peca();
		
		pec.setNome(request.getParameter("nome"));
		pec.setCodPeca(Long.parseLong(request.getParameter("codPeca")));
		pec.setValor(Float.parseFloat(request.getParameter("valor")));
		
		pecDao.criaTabelaPeca();
		pecDao.inserirPeca(pec);
		
		response.sendRedirect("Pecas.jsp");
		
		}
		
		else if(action.equals("/updatePeca")){
			
			Peca pec = new Peca();
			
			pec.setId(Long.parseLong(request.getParameter("id")));
			pec.setNome(request.getParameter("nome"));
			pec.setCodPeca(Long.parseLong(request.getParameter("codPeca")));
			pec.setValor(Float.parseFloat(request.getParameter("valor")));
			
			
			pecDao.atualizarPeca(pec,pec.getId());
			
			response.sendRedirect("Pecas.jsp");
			
		}
		
		else if(action.equals("/selectPeca")){
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("EditarPeca.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("/deletePeca")) {
			long id = Long.parseLong(request.getParameter("id"));
			
			pecDao.removerPeca(id);
			
			response.sendRedirect("Pecas.jsp");
			
		}
		
		else{
			System.out.println("erro nas rotas");
		}
		
	}

}
