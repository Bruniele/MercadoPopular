/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Funcionario;
import br.com.fatec.model.Venda;
import br.com.fatec.persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author sanoj
 */
public class VendaDAO implements DAO<Venda> {

    //variaveis auxiliares
    private Venda venda;
    //auxiliares para acesso aos dados
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Venda dado) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO Venda (codigoVenda, codigoProduto, nomeProduto, "
                   + "preco, quantidade, validade, valorTotal, totalRecebido, "
                   + "troco, codigoFuncionario, "
                   + "nomeFuncionario, codigoCliente, nomeCliente) "
                   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, dado.getCodigoVenda()); //1º interrogação
        pst.setInt(2, dado.getCodigoProduto()); //2º interrogação
        pst.setString(3, dado.getNomeProduto()); //3º interrogação
        pst.setFloat(4, dado.getPreco()); //4º interrogação
        pst.setInt(5, dado.getQuantidade()); //5º interrogação
        LocalDate validade  = dado.getValidade();
        java.sql.Date sqlDate = java.sql.Date.valueOf(validade);
        pst.setDate(6, sqlDate); //6º interrogação
        pst.setFloat(7, dado.getValorTotal()); //7º interrogação
        pst.setFloat(8, dado.getTotalRecebido()); //8º interrogação
        pst.setFloat(9, dado.getTroco()); //9º interrogação
        pst.setInt(10, dado.getCliente().getCodigoCliente()); //10º interrogação
        pst.setString(11, dado.getCliente().getNome()); //11º interrogação
        pst.setInt(12, dado.getFuncionario().getCodigoFuncionario()); //12º interrogação
        pst.setString(13, dado.getFuncionario().getNome()); //13º interrogação
        
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
    public boolean remove(Venda model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(Venda model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Venda buscaID(Venda model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Venda> lista(String filtro) throws SQLException {
        FuncionarioDAO daoFunc = new FuncionarioDAO();
        
        Collection<Venda> listagem = new ArrayList<>();

        String sql = "SELECT * FROM Venda ";
        if (filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }
        //conecta ao banco
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        //Executa o comando SELECT
        rs = pst.executeQuery();

        while (rs.next()) {
            venda = new Venda();

            Funcionario funcionario = new Funcionario();
            funcionario.setCodigoFuncionario(rs.getInt("codigoFuncionario"));
            funcionario = daoFunc.buscaID(funcionario);
            venda.setFuncionario(funcionario);
        }

        Banco.desconectar();

        return listagem;
    }

}
