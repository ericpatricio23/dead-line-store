package model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int id, String nome, double preco, int estoque){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;

    }

    public Produto(String nome, double preço, int estoque){
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;

    }

    public Produto() {

    }

    public int getId (){
        return id;
    }
    public void setId (int id){
        this.id = id;
    }

    public String getNome (){
        return nome;
    }
    public void setNome (String nome){
        this.nome = nome;
    }

    public double getPreco (){
        return preco;
    }
    public void setPreco (double preço){
        this.preco = preço;
    }

    public int getEstoque(){
        return estoque;
    }

    public void setEstoque (int estoque){
        this.estoque = estoque;
    }


}
