public class Produto{
    private int codigo;
    private String nome;
    private double valorUnitario;
    private int estoque;
    
    public Produto(int codigo, String nome, double valorUnitario, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.estoque = estoque;
    }
}