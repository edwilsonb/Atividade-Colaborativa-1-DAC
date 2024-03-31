package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
import br.com.atividade_colaborativa1.entidades.Funcionario;

public class FuncionarioDAO {


            private Connection connection;


            public FuncionarioDAO() {
                try {
                    this.connection = new FabricaDeConexao().getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Erro ao criar conexão com o banco de dados", e);
                }
            }
            
            public void criaTabelaFuncionario() {
                String query = "CREATE TABLE IF NOT EXISTS funcionario (" +
                               "id SERIAL PRIMARY KEY," +
                               "nome_completo VARCHAR(100) NOT NULL," +
                               "endereco VARCHAR(100) NOT NULL," +
                               "cargo VARCHAR(20) NOT NULL)";

                try {
                    PreparedStatement statement = this.connection.prepareStatement(query);
                    statement.execute();
                    statement.close();
                } catch(SQLException e) {
                    throw new RuntimeException(e);
                }
            }

    public void insereFuncionario (Funcionario funcionario){

        String query  = "INSERT INTO funcionario ( nome_completo, endereco, cargo ) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, funcionario.getNomeCompleto());
            statement.setString(2, funcionario.getEndereco());
            statement.setString(3, funcionario.getCargo());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Funcionario byId(Long id){

        String query = "SELECT * FROM funcionario WHERE id=?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultado = statement.executeQuery();

            Funcionario funcionario = new Funcionario();

            while (resultado.next()) {
                funcionario.setId(resultado.getLong("id"));
                funcionario.setNomeCompleto(resultado.getString("nome_completo"));
                funcionario.setEndereco(resultado.getString("endereco"));
                funcionario.setCargo(resultado.getString("cargo"));
            }
            return funcionario;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * @return
     */
    public List<Funcionario> all(){
        try {
            List<Funcionario> funcionarios = new ArrayList<Funcionario>();
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM funcionario");
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getLong("id"));
                funcionario.setNomeCompleto(resultado.getString("nome_completo"));
                funcionario.setEndereco(resultado.getString("endereco"));
                funcionario.setCargo(resultado.getString("cargo"));

                funcionarios.add(funcionario);
            }
            resultado.close();
            statement.close();
            return funcionarios;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void altera(Funcionario funcionario) {
        String query = "UPDATE funcionario SET nome_completo=?, endereco=?, cargo=? WHERE id=?";
    
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, funcionario.getNomeCompleto());
            statement.setString(2, funcionario.getEndereco());
            statement.setString(3, funcionario.getCargo());
            statement.setLong(4, funcionario.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	public void excluir(long id) {
		
		try {			
				PreparedStatement stmt = this.connection.prepareStatement("delete from funcionario where id=?");
				stmt.setLong(1, id);
				stmt.execute();
				stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
