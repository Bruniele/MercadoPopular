/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Categoria;
import br.com.fatec.persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author bruni
 */
public class CategoriaDAO implements DAO<Categoria> {

    //variaveis auxiliares
    private Categoria categoria;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Categoria dado) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO Categoria (codigoCategoria, nomeCategoria)"
                   + " values (?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getCodigoCategoria()); //1º interrogação
        pst.setString(2, dado.getNomeCategoria()); //2º interrogação
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            inseriu = true; //tudo certo com a inserção
        else
            inseriu = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public boolean remove(Categoria dado) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM Categoria WHERE codigoCategoria = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getCodigoCategoria()); //1º interrogação
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            removeu = true; //tudo certo com a inserção
        else
            removeu = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return removeu;
    }

    @Override
    public boolean altera(Categoria dado) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem alterados
        String sql = "UPDATE Categoria SET nomeCategoria = ?"
                   + "WHERE codigoCategoria = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, dado.getNomeCategoria());  
        pst.setInt(2, dado.getCodigoCategoria());  
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            alterou = true; //tudo certo com a inserção
        else
            alterou = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return alterou;
    }

    @Override
    public Categoria buscaID(Categoria dado) throws SQLException {
        categoria = null;
        //Comando SELECT
        String sql = "SELECT * FROM Categoria WHERE codigoCategoria = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getCodigoCategoria());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto categoria
            categoria = new Categoria();
            //move os dados do resultSet para o objeto categoria
            categoria.setCodigoCategoria(rs.getInt("CodigoCategoria"));
            categoria.setNomeCategoria(rs.getString("nomeCategoria"));
        }
        
        Banco.desconectar();
        
        return categoria;
    }

    @Override
    public Collection<Categoria> lista(String filtro) throws SQLException {
        //criar uma coleção
        Collection<Categoria> listagem = new ArrayList<>();
        
        categoria = null;
        //Comando SELECT
        String sql = "SELECT * FROM Categoria ";
        //colocar filtro ou nao
        if(filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        while(rs.next()) { //achou 1 registro
            //cria o objeto categoria
            categoria = new Categoria();
            //move os dados do resultSet para o objeto categoria
            categoria.setCodigoCategoria(rs.getInt("codigoCategoria"));
            categoria.setNomeCategoria(rs.getString("nomeCategoria"));
            
            //adicionar na coleção
            listagem.add(categoria);
        }
        
        Banco.desconectar();
        
        return listagem;
    }

}
