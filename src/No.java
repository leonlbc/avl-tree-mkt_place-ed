public class No<T> {
    T dado;
    No<T> esquerda, direita;

    public No(T dado){ 
        this.dado = dado; 
        this.esquerda = this.direita = null; 
    }
}