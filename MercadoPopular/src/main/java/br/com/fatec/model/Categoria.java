package br.com.fatec.model;

/**
 *
 * @author bruni
 */
public class Categoria {
    private int codigoCategoria;
    private String nomeCategoria;

    public Categoria() {
    }

    public Categoria(int codigoCategoria, String nomeCategoria) {
        this.codigoCategoria = codigoCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.codigoCategoria;
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
        final Categoria other = (Categoria) obj;
        return this.codigoCategoria == other.codigoCategoria;
    }

    @Override
    public String toString() {
        return this.getNomeCategoria();
    } 
   
}
