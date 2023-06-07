/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Produto;
import br.com.fatec.persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author bruni
 */
public class ProdutoDAO implements DAO<Produto> {
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql

    @Override
    public boolean insere(Produto dado) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO Produto (codigoProduto, nomeProduto, "
                   + "preco, descricao, quantidade, validade, codigoFornecedor, "
                   + "nomeFornecedor, codigoCategoria, nomeCategoria) "
                   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getCodigoProduto()); //1º interrogação
        pst.setString(2, dado.getNomeProduto()); //2º interrogacao
        pst.setFloat(3, dado.getPreco()); //3º interrogacao
        pst.setString(4, dado.getDescricao()); //4º interrogacao
        pst.setInt(5, dado.getQuantidade()); //5º interrogação
        pst.setString(6, dado.getValidade()); //6º interrogacao
        pst.setInt(7, dado.getFornecedor().getCodigoFornecedor()); //7º interrogação
        pst.setString(8, dado.getFornecedor().getNomeFornecedor()); //8º interrogação
        pst.setInt(9, dado.getCategoria().getCodigoCategoria()); //9º interrogação
        pst.setString(10, dado.getCategoria().getNomeCategoria()); //10º interrogação
        
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
    public boolean remove(Produto model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(Produto model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Produto buscaID(Produto model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Produto> lista(String criterio) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
