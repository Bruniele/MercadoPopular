package br.com.fatec.DAO;

import br.com.fatec.model.Fornecedor;
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
public class FornecedorDAO implements DAO<Fornecedor> {

    private Fornecedor fornecedor;

    private PreparedStatement pst;

    private ResultSet rs;

    @Override
    public boolean insere(Fornecedor dado) throws SQLException {
        boolean inseriu;

        //conectar com o banco
        Banco.conectar();

        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO Fornecedor (codigoFornecedor,nomeFornecedor,email, telefone, marca,cep,logradouro,bairro,localidade,uf) VALUES (?,?,?,?,?,?,?,?,?,?)";

        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);

        //colocar os dados no PST 
        pst.setInt(1, dado.getCodigoFornecedor()); //1° interrogação
        pst.setString(2, dado.getNomeFornecedor()); //2° interrogação
        pst.setString(3, dado.getEmail());
        pst.setString(4, dado.getTelefone());
        pst.setString(5, dado.getMarca());
        pst.setString(6, dado.getCep());
        pst.setString(7, dado.getLogradouro());
        pst.setString(8, dado.getBairro());
        pst.setString(9, dado.getLocalidade());
        pst.setString(10, dado.getUf());

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
    public boolean remove(Fornecedor dado) throws SQLException {
        boolean removeu;

        //conectar com o banco
        Banco.conectar(); //

        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM Fornecedor WHERE codigoFornecedor = ?";

        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);

        //colocar os dados no PST 
        pst.setInt(1, dado.getCodigoFornecedor()); //1° interrogação

        //executar o comando
        if (pst.executeUpdate() > 0) {
            removeu = true; //tudo certo com a  inserção
        } else {
            removeu = false; //
        }
        //feche a conexão
        Banco.desconectar();

        return removeu;
    }

    @Override
    public boolean altera(Fornecedor dado) throws SQLException {
        boolean alterou;

        Banco.conectar();

        String sql = "UPDATE fornecedor SET "
                + "nomeFornecedor = ?, "
                + "email = ?, "
                + "telefone = ?, "
                + "marca = ?, "
                + "cep = ?, "
                + "logradouro = ?, "
                + "bairro = ?, "
                + "localidade = ?, "
                + "uf = ? "
                + "WHERE codigoFornecedor = ?";

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(10, dado.getCodigoFornecedor());
        pst.setString(1, dado.getNomeFornecedor());
        pst.setString(2, dado.getEmail());
        pst.setString(3, dado.getTelefone());
        pst.setString(4, dado.getMarca());
        pst.setString(5, dado.getCep());
        pst.setString(6, dado.getLogradouro());
        pst.setString(7, dado.getBairro());
        pst.setString(8, dado.getLocalidade());
        pst.setString(9, dado.getUf());

        if (pst.executeUpdate() > 0) {
            alterou = true;
        } else {
            alterou = false;
        }

        Banco.desconectar();
        return alterou;
    }

    @Override
    public Fornecedor buscaID(Fornecedor dado) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();

        fornecedor = null;

        String sql = "SELECT * FROM fornecedor WHERE codigoFornecedor = ?";

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado.getCodigoFornecedor());

        rs = pst.executeQuery();

        if (rs.next()) {
            fornecedor = new Fornecedor();
            fornecedor.setCodigoFornecedor(rs.getInt("codigoFornecedor"));
            fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
            fornecedor.setEmail(rs.getString("email"));
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setMarcaFornecedor(rs.getString("marca"));
            fornecedor.setCep(rs.getString("cep"));
            fornecedor.setLogradouro(rs.getString("logradouro"));
            fornecedor.setBairro(rs.getString("bairro"));
            fornecedor.setLocalidade(rs.getString("localidade"));
            fornecedor.setUf(rs.getString("uf"));

        }
        Banco.desconectar();

        return fornecedor;
    }

    public boolean buscaID(int dado) throws SQLException {
        boolean result;

        String sql = "SELECT * FROM fornecedor WHERE codigoFornecedor = ?";
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado);

        rs = pst.executeQuery();

        if (rs.next()) {

            rs.getString("nomeFornecedor");
            result = true;
        } else {
            result = false;
        }

        Banco.desconectar();

        return result;
    }

    @Override
    public Collection<Fornecedor> lista(String criterio) throws SQLException {
        Collection<Fornecedor> listagem = new ArrayList<>();

        fornecedor = null;

        String sql = "SELECT * FROM fornecedor ";
        if (criterio.length() != 0) {
            sql += "WHERE " + criterio;
        }

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        rs = pst.executeQuery();
        
        
        while(rs.next()){
            fornecedor = new Fornecedor();
            
            fornecedor.setCodigoFornecedor(rs.getInt("codigoFornecedor"));
            fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
            
            listagem.add(fornecedor);
            
        }
        Banco.desconectar();
        
        return listagem;
    }

}
