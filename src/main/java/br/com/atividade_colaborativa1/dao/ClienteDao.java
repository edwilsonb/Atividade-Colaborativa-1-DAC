package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
import br.com.atividade_colaborativa1.entidades.Cliente;

public class ClienteDao {

	private Connection connection;
	
	public ClienteDao() throws ClassNotFoundException {
		
		this.connection = new FabricaDeConexao().getConnection();
	}
	
	public void criaTabelaCliente() {
		
		String sql = "CREATE TABLE IF NOT EXISTS clientes ("
				+ "id serial primary key,"
				+ "nome varchar(100) not null,"
				+ "endereco varchar(100) not null"
				+ "data_cadastro varchar(10) not null";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insereCliente(Cliente cliente) {
		
		String sql = "insert into clientes (nome, endereco, data_cadastro) values (?,?,?)";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getDataCadastro());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Cliente byId(Long id) {
		
		String sql = "select * from clientes where id=?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			Cliente cliente = new Cliente();
			while (rs.next()) {
				cliente.setId(id);
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setDataCadastro(rs.getString("data_cadastro"));
			}
			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Cliente> all() {
		
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from clientes");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setDataCadastro(rs.getString("data_cadastro"));
				
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void atualizar(Cliente cliente, long id) {
		
		String sql = "update clientes set nome=?, endereco=?, data_cadastro where id=?";
		
		try {
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cliente.getNome());
				stmt.setString(2, cliente.getEndereco());
				stmt.setString(3, cliente.getDataCadastro());
				stmt.setLong(4, id);
				stmt.execute();
				stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(long id) {
		
		try {			
				PreparedStatement stmt = this.connection.prepareStatement("delete from clientes where id=?");
				stmt.setLong(1, id);
				stmt.execute();
				stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
