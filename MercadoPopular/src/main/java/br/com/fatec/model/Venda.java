package br.com.fatec.model;

public class Venda {
    
    private int codigoVenda;
    private int quantidade;
    private float valorTotal;
    private float totalRecebido;
    private float troco;

    //Agregação
    private Produto produto;
    private Funcionario funcionario;
    private Cliente cliente;

    public Venda() {

    }

    public Venda(int codigoVenda, float valorTotal, float totalRecebido, float troco, Produto produto, Funcionario funcionario, Cliente cliente) {
        this.codigoVenda = codigoVenda;
        this.valorTotal = valorTotal;
        this.totalRecebido = totalRecebido;
        this.troco = troco;
        this.produto = produto;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float getTotalRecebido() {
        return totalRecebido;
    }

    public void setTotalRecebido(float totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

    public float getTroco() {
        return troco;
    }

    public void setTroco(float troco) {
        this.troco = troco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.codigoVenda;
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
        final Venda other = (Venda) obj;
        return this.codigoVenda == other.codigoVenda;
    }

    @Override
    public String toString() {
        return "Venda{" + "codigoVenda=" + codigoVenda + '}';
    }
}
