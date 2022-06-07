public class Produto implements Comparable<Produto>{
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

    //Apenas getter, mudar o codigo quebraria arvore
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public int getEstoque() {
        return estoque;
    }

    public void vender(int qtd) {
        if (this.estoque < qtd) {
            //erro
        } else {
            this.estoque -= qtd;
        }
    }

    @Override
    public int compareTo(Produto o) {
        if (this.codigo > o.getCodigo()){
            return 1;
        } else if (this.codigo < o.getCodigo()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "{ Codigo[" + this.getCodigo() + "] " +
                this.getNome() + ": " +
                this.getValorUnitario() + "$ -- " +
                this.getEstoque() + "em estoque }";
    }
}