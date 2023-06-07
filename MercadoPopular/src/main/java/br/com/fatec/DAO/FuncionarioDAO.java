package br.com.fatec.DAO;

import br.com.fatec.model.Funcionario;
import br.com.fatec.persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class FuncionarioDAO implements DAO<Funcionario> {

    private Funcionario funcionario;

    private PreparedStatement pst;

    private ResultSet rs;

    @Override
    public boolean insere(Funcionario dado) throws SQLException {
        boolean inseriu;

        Banco.conectar();

        String sql = "INSERT INTO funcionario (codigoFuncionario, nome, email, telefone, rg, cpf, setor, salario) VALUES (?,?,?,?,?,?,?,?)";

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado.getCodigoFuncionario());
        pst.setString(2, dado.getNome());
        pst.setString(3, dado.getEmail());
        pst.setString(4, dado.getTelefone());
        pst.setString(5, dado.getRg());
        pst.setString(6, dado.getCpf());
        pst.setString(7, dado.getSetor());
        pst.setDouble(8, dado.getSalario());
        
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
    public boolean remove(Funcionario model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(Funcionario model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Funcionario buscaID(Funcionario dado) throws SQLException {
        FuncionarioDAO dao = new FuncionarioDAO();

        funcionario = null;

        String sql = "select * from funcionario where codigoFuncionario = ?";

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado.getCodigoFuncionario());

        rs = pst.executeQuery();

        if (rs.next()) {
            funcionario = new Funcionario();
            funcionario.setCodigoFuncionario(rs.getInt("codigoFuncionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setRg(rs.getString("rg"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setSetor(rs.getString("setor"));
            funcionario.setSalario(rs.getDouble("salario"));
        }

        Banco.desconectar();

        return funcionario;
    }

    public boolean BuscaID(int dado) throws SQLException {
        boolean result;

        String sql = "SELECT * FROM fornecedor WHERE codigoFuncionario = ?";

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado);

        rs = pst.executeQuery();
        if (rs.next()) {
            rs.getString("nome");
            result = true;
        } else {
            result = false;
        }
        Banco.desconectar();

        return result;
    }

    @Override
    public Collection<Funcionario> lista(String criterio) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
