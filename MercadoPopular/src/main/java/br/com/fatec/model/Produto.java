/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 * Para todas as classes MODEL é preciso criar:
 * 1) getters e setters
 * 2) equals e hashCode
 * 3) Consutrotores default e Paramétrico
 * 4) toString
 * @author bruni
 */
public class Produto {
    private int codigoProduto;
    private String nomeProduto;
    private float preco;
    private String descricao;
    private int quantidade;
    public String validade;
    
    //Agregação
    private Fornecedor fornecedor;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int codigoProduto, String nomeProduto, float preco, String descricao, int quantidade, String validade, Fornecedor fornecedor, Categoria categoria) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.validade = validade;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }
    
    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.codigoProduto;
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
        final Produto other = (Produto) obj;
        return this.codigoProduto == other.codigoProduto;
    }

    @Override
    public String toString() {
        return getNomeProduto();
    }

}
