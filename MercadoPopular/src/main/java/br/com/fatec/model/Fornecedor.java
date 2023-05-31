package br.com.fatec.model;

public class Fornecedor {

    private int codigoFornecedor;
    private String nomeFornecedor;
    private String email;
    private String telefone;
    private String endereco;
    private String numero;
    private String cep;

    public Fornecedor() {
        
    }

    public Fornecedor(int codigoFornecedor, String nomeFornecedor, String email, String telefone, String endereco, String numero, String cep) {
        this.codigoFornecedor = codigoFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.numero = numero;
        this.cep = cep;
    }

    public int getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(int codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.codigoFornecedor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        return this.codigoFornecedor == other.codigoFornecedor;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "nomeFornecedor=" + nomeFornecedor + '}';
    }

   
    
    
}
