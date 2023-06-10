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
    public boolean insere(Venda model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
