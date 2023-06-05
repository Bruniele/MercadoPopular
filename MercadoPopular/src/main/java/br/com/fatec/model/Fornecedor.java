package br.com.fatec.model;

public class Fornecedor {

    private int codigoFornecedor;
    private String nomeFornecedor;
    private String email;
    private String telefone;
    private String marca;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public Fornecedor() {
    }

    public Fornecedor(int codigoFornecedor, String nomeFornecedor, String email, String telefone,String marca,String cep, String logradouro, String bairro, String localidade, String uf) {
        this.codigoFornecedor = codigoFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.email = email;
        this.telefone = telefone;
        this.marca = marca;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
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

    public String getMarca(){
        return marca;
    }
    
    public void setMarcaFornecedor(String marca){
        this.marca = marca;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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
        return this.getNomeFornecedor();
    }

    
    
    

   
    
    
}
