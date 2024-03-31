package br.com.atividade_colaborativa1.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
	import br.com.atividade_colaborativa1.entidades.VendaPeca;

	public class VendaPecaDao {

	    private Connection connection;

	    public VendaPecaDao() {
	        try {
	            this.connection = new FabricaDeConexao().getConnection();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public void getErrorMessage(String method, String err) {
	        System.out.println("Erro ao " + method + ": " + err);
	    }

	    public void criarTabelaVendaPeca() {
	        String sql = "CREATE TABLE IF NOT EXISTS venda_peca (" + "id SERIAL PRIMARY KEY," + "id_venda BIGINT,"
	                + "id_peca BIGINT" + ")";

	        try {
	            PreparedStatement stmt = this.connection.prepareStatement(sql);
	            stmt.execute();
	            stmt.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public void associarPecaAVenda(long idVenda, long idPeca) {
	        String sql = "INSERT INTO venda_peca (id_venda, id_peca) VALUES (?, ?)";
	        try {
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            stmt.setLong(1, idVenda);
	            stmt.setLong(2, idPeca);
	            stmt.executeUpdate();
	            stmt.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public List<VendaPeca> listarPecasPorVenda(long idVenda) {
	        List<VendaPeca> vendasPecas = new ArrayList<>();
	        String sql = "SELECT * FROM venda_peca WHERE id_venda = ?";
	        try {
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            stmt.setLong(1, idVenda);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                VendaPeca venda = new VendaPeca();
	                venda.setId(rs.getLong("id"));
	                venda.setIdVenda(rs.getLong("id_venda"));
	                venda.setIdPeca(rs.getLong("id_peca"));
	                vendasPecas.add(venda);
	            }
	            rs.close();
	            stmt.close();
	            return vendasPecas;
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public void removerPecasDaVenda(long idVenda) {
	        String sql = "DELETE FROM venda_peca WHERE id_venda = ?";
	        try {
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            stmt.setLong(1, idVenda);
	            stmt.executeUpdate();
	            stmt.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	}
