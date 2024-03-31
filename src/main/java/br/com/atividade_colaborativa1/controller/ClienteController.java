package br.com.atividade_colaborativa1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.ClienteDao;
import br.com.atividade_colaborativa1.entidades.Cliente;


@WebServlet(urlPatterns ={"/ClienteController","/addCliente","/allClientes","/updateCliente","/selectCliente","/deleteCliente"})
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClienteDao cliDao = new ClienteDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if(action.equals("/addCliente")) {
		Cliente cli = new Cliente();
		
		cli.setNome(request.getParameter("nome"));
		cli.setEndereco(request.getParameter("endereço"));
		cli.setDataCadastro(request.getParameter("data"));
		
		
		cliDao.criaTabelaCliente();
		cliDao.insereCliente(cli);
		
		response.sendRedirect("Clientes.jsp");
		
		}
		
		else if(action.equals("/updateCliente")){
			Cliente cli = new Cliente();
			
			cli.setId(Long.parseLong(request.getParameter("id")));
			cli.setNome(request.getParameter("nome"));
			cli.setEndereco(request.getParameter("endereço"));
			cli.setDataCadastro(request.getParameter("data"));
			
			
			cliDao.atualizar(cli,cli.getId());
			
			response.sendRedirect("Clientes.jsp");
			
		}
		
		else if(action.equals("/selectCliente")){
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("EditarCliente.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("/deleteCliente")) {
			long id = Long.parseLong(request.getParameter("id"));
			
			cliDao.excluir(id);
			
			response.sendRedirect("Clientes.jsp");
			
		}
		
		else{
			System.out.println("erro nas rotas");
		}
		
	}

}
