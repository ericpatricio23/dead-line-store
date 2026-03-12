package model;



public class Venda {

    private int id;
    private  String data;
    private double valorTotal;


    public Venda(){}

    public Venda(int id, String data, double valorTotal){
        this.data = data;
        this.valorTotal = valorTotal;
        this.id = id;

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data= data;
    }


    public double getValorTotal(){
        return valorTotal;
    }

    public void setValorTotal(double valorTotal){
        this.valorTotal = valorTotal;
    }


}