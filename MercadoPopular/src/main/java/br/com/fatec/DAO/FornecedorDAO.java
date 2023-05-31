
package br.com.fatec.DAO;


import br.com.fatec.model.Fornecedor;
import br.com.fatec.persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author sanoj
 */
public class FornecedorDAO implements DAO<Fornecedor> {

    private Fornecedor fornecedor;

    private PreparedStatement pst;

    private ResultSet rs;

    @Override
    public boolean insere(Fornecedor dado) throws SQLException {
        boolean inseriu;

        //conectar com o banco
        Banco.conectar(); //

        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO Fornecedor (codigoFornecedor,nomeFornecedor,email, telefone,endereco,numero,cep) VALUES (?,?,?,?,?,?,?)";

        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);

        //colocar os dados no PST 
        pst.setInt(1, dado.getCodigoFornecedor()); //1° interrogação
        pst.setString(2, dado.getNomeFornecedor()); //2° interrogação
        pst.setString(3, dado.getEmail());
        pst.setString(4, dado.getTelefone());
        pst.setString(5, dado.getEndereco());
        pst.setString(6, dado.getNumero());
        pst.setString(7, dado.getCep());

        //executar o comando
        if (pst.executeUpdate() > 0) {
            inseriu = true; //tudo certo com a  inserção
        } else {
            inseriu = false; //
        }
        //feche a conexão
        Banco.desconectar();

        return inseriu;

    }

    @Override
    public boolean remove(Fornecedor model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(Fornecedor model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Fornecedor buscaID(Fornecedor model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Fornecedor> lista(String criterio) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
