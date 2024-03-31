package br.com.atividade_colaborativa1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.FuncionarioDAO;
import br.com.atividade_colaborativa1.entidades.Cliente;
import br.com.atividade_colaborativa1.entidades.Funcionario;


@WebServlet(urlPatterns ={"/FuncionarioController","/addFuncionario","/allFuncionarios","/updateFuncionario","/selectFuncionario","/deleteFuncionario"})
public class FuncionarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FuncionarioDAO funDao = new FuncionarioDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		if(action.equals("/addFuncionario")) {
		Funcionario fun = new Funcionario();
		fun.setNomeCompleto(request.getParameter("nome"));
		fun.setCargo(request.getParameter("cargo"));
		fun.setEndereco(request.getParameter("endereço"));
		
		funDao.criaTabelaFuncionario();
		funDao.insereFuncionario(fun);
		
		response.sendRedirect("Funcionarios.jsp");
		}
		
		else if(action.equals("/updateFuncionario")){
			Funcionario fun = new Funcionario();
			
			fun.setId(Long.parseLong(request.getParameter("id")));
			fun.setNomeCompleto(request.getParameter("nome"));
			fun.setEndereco(request.getParameter("endereço"));
			fun.setCargo(request.getParameter("cargo"));
			
			
			funDao.altera(fun);
			
			response.sendRedirect("Funcionarios.jsp");
			
		}
		
		else if(action.equals("/selectFuncionario")){
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("EditarFuncionario.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("/deleteFuncionario")) {
			long id = Long.parseLong(request.getParameter("id"));
			
			funDao.excluir(id);
			
			response.sendRedirect("Funcionarios.jsp");
			
		}
		
		else{
			System.out.println("Erro na requisição");
		}
		
	}

}
