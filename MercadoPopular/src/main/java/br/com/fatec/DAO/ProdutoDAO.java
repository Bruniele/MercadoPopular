/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Categoria;
import br.com.fatec.model.Fornecedor;
import br.com.fatec.model.Produto;
import br.com.fatec.persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bruni
 */
public class ProdutoDAO implements DAO<Produto> {
    
    //variaveis auxiliares
    private Produto produto;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

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
        pst.setString(2, dado.getNomeProduto()); //2º interrogação
        pst.setFloat(3, dado.getPreco()); //3º interrogação
        pst.setString(4, dado.getDescricao()); //4º interrogação
        pst.setInt(5, dado.getQuantidade()); //5º interrogação
        LocalDate validade  = dado.getValidade();
        java.sql.Date sqlDate = java.sql.Date.valueOf(validade);
        pst.setDate(6, sqlDate); //6º interrogação
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
    public static ObservableList<Produto> getDataProduto() {
         ObservableList<Produto> list = FXCollections.observableArrayList();
 
         
       
            
        try {
            String sql = "SELECT * FROM produto";
            Banco.conectar();
            PreparedStatement pst;
            pst = Banco.obterConexao().prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            
            
            
            while(rs.next()){
                list.add(new Produto(
                rs.getInt("codigoProduto"),
                rs.getString("nomeProduto"),
                rs.getFloat("preco"),
                rs.getInt("quantidade")
               // rs.getString("nomeCategoria")
                ));
               
            }
            
              Banco.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
              return list;
        }
    
    

    @Override
    public boolean remove(Produto dado) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM Produto WHERE codigoProduto = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getCodigoProduto()); //1º interrogação
        
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
    public boolean altera(Produto model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Produto buscaID(Produto dado) throws SQLException {
        //criado para buscar dados do fornecedor e da categoria
        FornecedorDAO dao = new FornecedorDAO();
        CategoriaDAO dao1 = new CategoriaDAO();

        produto = null;
        //Comando SELECT
        String sql = "SELECT * FROM Produto WHERE codigoProduto = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getCodigoProduto());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto produto
            produto = new Produto();
            //move os dados do resultSet para o objeto produto
            produto.setCodigoProduto(rs.getInt("codigoProduto"));
            produto.setNomeProduto(rs.getString("nomeProduto"));
            produto.setPreco(rs.getFloat("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setValidade(rs.getDate("validade").toLocalDate());
            
            //precisa buscar os dados do fornecedor em fornecedorDAO
            Fornecedor fornecedor = new Fornecedor();
            //informa quem deve ser pego de fornec.
            fornecedor.setCodigoFornecedor(rs.getInt("codigoFornecedor"));
            //faz a busca
            fornecedor = dao.buscaID(fornecedor);
            produto.setFornecedor(fornecedor);
            
            //precisa buscar os dados do categoria em categoriaDAO
            Categoria categoria = new Categoria();
            //informa quem deve ser pego de categ.
            categoria.setCodigoCategoria(rs.getInt("codigoCategoria"));
            //faz a busca
            categoria = dao1.buscaID(categoria);
            produto.setCategoria(categoria);
            
        }
        
        Banco.desconectar();
        
        return produto;

    }

    @Override
    public Collection<Produto> lista(String filtro) throws SQLException {
        //para acessar o fornecedor e a categoria
        FornecedorDAO daoForn = new FornecedorDAO();
        CategoriaDAO daoCateg = new CategoriaDAO();
        
        //criar uma coleção
        Collection<Produto> listagem = new ArrayList<>();
        
        produto = null;
        //Comando SELECT
        String sql = "SELECT * FROM Produto ";
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
            //cria o objeto produto
            produto = new Produto();
            //move os dados do resultSet para o objeto produto
            produto.setCodigoProduto(rs.getInt("codigoProduto"));
            produto.setNomeProduto(rs.getString("nomeProduto"));
            produto.setPreco(rs.getFloat("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setValidade(rs.getDate("validade").toLocalDate());

            //precisa buscar os dados do fornecedor em fornecedorDAO
            Fornecedor fornecedor = new Fornecedor();
            //informa quem deve ser pego de forne.
            fornecedor.setCodigoFornecedor(rs.getInt("codigoFornecedor"));
            //faz a busca
            fornecedor = daoForn.buscaID(fornecedor);
            produto.setFornecedor(fornecedor);
            
            //precisa buscar os dados da categoria em categoriaDAO
            Categoria categoria = new Categoria();
            //informa quem deve ser pego de categ.
            categoria.setCodigoCategoria(rs.getInt("codigoCategoria"));
            //faz a busca
            categoria = daoCateg.buscaID(categoria);
            produto.setCategoria(categoria);
            
            //adicionar na coleção
            listagem.add(produto);
        }
        
        Banco.desconectar();
        
        return listagem;
        
    }
    
    
}
