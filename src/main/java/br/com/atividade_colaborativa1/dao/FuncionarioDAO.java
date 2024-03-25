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

    
    public FuncionarioDAO ()throws ClassNotFoundException{
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void criaTabelaFuncionario(){
        String query = "CREATE TABLE IF NOT EXISTS fucionario("+ 
        "id serial primary key," +
        " nome_completo varchar(100 ) not null," 
        + "endereco varchar(100) not null"
        +"cargo varchar (20) not null"; 

        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.execute();
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insereFuncionario (Funcionario funcionario){

        String query  = "INSERT INTO funcionario ( nome_completo, matricula ) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, funcionario.getNomeCompleto());
            statement.setString(3, funcionario.getEndereco());
            statement.setString(4, funcionario.getCargo());

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
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM funcionarios");
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
        String query = "UPDATE contatos SET nome_completo=?, matricula=? WHERE id=?";
    
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, funcionario.getNomeCompleto());
            statement.setString(2, funcionario.getEndereco());
            statement.setString(5, funcionario.getCargo());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove (Funcionario funcionario){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM WHERE id=?");
            statement.setLong(1, funcionario.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
