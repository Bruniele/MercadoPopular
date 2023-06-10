package br.com.fatec.model;

public class Funcionario {

    private int codigoFuncionario;
    private String nome;
    private String email;
    private String telefone;
    private String rg;
    private String cpf;
    private String setor;
    private double salario;

    public Funcionario() {
    }

    public Funcionario(int codigoFuncionario, String nome, String email, String telefone, String rg, String cpf, String setor, double salario) {
        this.codigoFuncionario = codigoFuncionario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.rg = rg;
        this.cpf = cpf;
        this.setor = setor;
        this.salario = salario;
    }

    public int getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(int codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    
  
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.codigoFuncionario;
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
        final Funcionario other = (Funcionario) obj;
        return this.codigoFuncionario == other.codigoFuncionario;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "codigoFuncionario=" + codigoFuncionario + '}';
    }

}
