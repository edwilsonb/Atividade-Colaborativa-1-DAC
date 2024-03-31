package br.com.atividade_colaborativa1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.VeiculoDao;
import br.com.atividade_colaborativa1.entidades.Veiculo;


@WebServlet(urlPatterns ={"/VeiculoController","/addVeiculo","/allVeiculo","/updateVeiculo","/selectVeiculo","/deleteVeiculo"})
public class VeiculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	VeiculoDao veiDao = new VeiculoDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if(action.equals("/addVeiculo")) {
		
		Veiculo vei = new Veiculo();
		vei.setId_Cliente(Long.parseLong(request.getParameter("idCliente")));
		vei.setPlaca(request.getParameter("placa"));
		vei.setMarca(request.getParameter("marca"));
		vei.setModelo(request.getParameter("modelo"));
		
		System.out.println(vei.toString());
		
		veiDao.criaTabelaVeiculo();
		veiDao.inserirVeiculo(vei);
		
		response.sendRedirect("Veiculos.jsp");
		
		}
		
		else if(action.equals("/updateVeiculo")){
			
			Veiculo vei = new Veiculo();
			
			vei.setId(Long.parseLong(request.getParameter("id")));
			vei.setId_Cliente(Long.parseLong(request.getParameter("idCliente")));
			vei.setPlaca(request.getParameter("placa"));
			vei.setMarca(request.getParameter("marca"));
			vei.setModelo(request.getParameter("modelo"));
			
			System.out.println(vei.toString());
			
			veiDao.atualizarVeiculo(vei,vei.getId());
			
			response.sendRedirect("Veiculos.jsp");
			
		}
		
		else if(action.equals("/selectVeiculo")){
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("EditarVeiculo.jsp");
			rd.forward(request, response);
		}
		
		else if(action.equals("/deleteVeiculo")) {
			long id = Long.parseLong(request.getParameter("id"));
			
			veiDao.removerVeiculo(id);
			
			response.sendRedirect("Veiculos.jsp");
			
		}
		
		else{
			System.out.println("erro nas rotas");
		}
		
	}

}
