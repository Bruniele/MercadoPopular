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
 * @author sanoj
 */
public class CategoriaDAO implements DAO<Categoria> {

    private Categoria categoria;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Categoria buscaID(Categoria model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Categoria> lista(String criterio) throws SQLException {
        Collection<Categoria> listagem = new ArrayList<>();

        categoria = null;

        String sql = "SELECT nomeCategoria FROM categoria";

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        rs = pst.executeQuery();
        
        
        
        while(rs.next()){
            categoria = new Categoria();
          
            categoria.setNomeCategoria(rs.getString("nomeCategoria"));
            
            listagem.add(categoria);
        }
        
        Banco.desconectar();
        
        return listagem;
    }

}
