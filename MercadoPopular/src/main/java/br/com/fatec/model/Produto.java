/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author sanoj
 */
public class Produto {
    private int id;
    private String nomeProduto;
    private String preco;
    private String descricao;
    private int quantidade;
    private String validade; //Existe um tipo de dado certo para a validade, estou sem tempo 
    private Fornecedor forcenedor;
    private Categoria categoria;
}
