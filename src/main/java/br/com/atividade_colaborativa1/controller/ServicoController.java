package br.com.atividade_colaborativa1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atividade_colaborativa1.dao.ServicoDao;
import br.com.atividade_colaborativa1.entidades.Servico;


@WebServlet(urlPatterns ={"/ServicoController","/addServico","/allServicos"})
public class ServicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServicoDao serDao = new ServicoDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if(action == "/addServico") {
		Servico ser = new Servico();
		ser.setTipo(request.getParameter("tipo"));
		ser.setData(request.getParameter("data"));
		ser.setCodServico(Long.parseLong(request.getParameter("codServico")));
		ser.setValor(Float.parseFloat(request.getParameter("valor")));
		
		serDao.criaTabelaServico();
		serDao.inserirServico(ser);
		
		} else{
			serDao.all();
		}
		
	}

}
