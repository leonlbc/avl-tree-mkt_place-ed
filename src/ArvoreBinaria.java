import java.util.ArrayList;

public class ArvoreBinaria<T extends Comparable<T>> {
    No<T> raiz;

    public ArvoreBinaria(){
        this.raiz = null;
    }

    public void deletaDado(T dado) { 
        raiz = deletaRecursivo(raiz, dado); 
    } 
   
    public No<T> deletaRecursivo(No<T> raiz, T dado)  { 
        if (raiz == null)  return raiz; 
   
        if (dado.compareTo(raiz.dado) < 0){
            raiz.esquerda = deletaRecursivo(raiz.esquerda, dado); 
        }
        else if (dado.compareTo(raiz.dado) > 0){
            raiz.direita = deletaRecursivo(raiz.direita, dado); 
        }
        else  { 
            if (raiz.esquerda == null) {
                return raiz.direita; 
            }
            else if (raiz.direita == null) {
                return raiz.esquerda; 
            }
            raiz.dado = minimoValor(raiz.direita); 
            raiz.direita = deletaRecursivo(raiz.direita, raiz.dado); 
        } 
        return raiz; 
    } 

    public T minimoValor(No<T> raiz){
        T minimo = raiz.dado;

        while(raiz.esquerda != null){
            minimo = raiz.esquerda.dado; 
            raiz = raiz.esquerda; 
        } 
        return minimo; 
    }

    public void inserir(T dado)  { 
        raiz = inserirRecursivo(raiz, dado); 
    }

    public No<T> inserirRecursivo(No<T> raiz, T dado) { 
        if (raiz == null) { 
            raiz = new No<T>(dado); 
            return raiz; 
        } 
        if (dado.compareTo(raiz.dado) < 0){
            raiz.esquerda = inserirRecursivo(raiz.esquerda, dado);
        }
        else if (dado.compareTo(raiz.dado) > 0) {
            raiz.direita = inserirRecursivo(raiz.direita, dado); 
        }   
        return raiz; 
    }

    public ArrayList<T> emOrdem(ArrayList<T> listaEmOrdem) { 
        return emOrdemRecursivo(raiz, listaEmOrdem);
    } 
   
    public ArrayList<T> emOrdemRecursivo(No<T> raiz, ArrayList<T> listaEmOrdem) {
        if (raiz != null) { 
            emOrdemRecursivo(raiz.esquerda, listaEmOrdem); 
            listaEmOrdem.add(raiz.dado);
            emOrdemRecursivo(raiz.direita, listaEmOrdem);
        }
        return listaEmOrdem;
    }

    public boolean pesquisar(T dado)  { 
        raiz = pesquisarRecursivo(raiz, dado); 
        if (raiz!= null){
            return true;
        }
        else {
            return false;
        }
    } 

    public No<T> pesquisarRecursivo(No<T> raiz, T dado)  { 
        if (raiz==null || raiz.dado.compareTo(dado) == 0) {
            return raiz; 
        }   
        if (raiz.dado.compareTo(dado) > 0){
            return pesquisarRecursivo(raiz.esquerda, dado); 
        }
        return pesquisarRecursivo(raiz.direita, dado); 
    } 
}

