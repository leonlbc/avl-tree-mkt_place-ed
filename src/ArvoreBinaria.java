import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArvoreBinaria<T extends Comparable<T>> {
    No<T> raiz;

    public ArvoreBinaria(){
        this.raiz = null;
    }

    public void deletaDado(T dado) { 
        raiz = deletaRecursivo(raiz, dado); 
    } 
   
    private No<T> deletaRecursivo(No<T> raiz, T dado)  { 
        if (raiz == null) {
            return raiz;
        } 
        else if (dado.compareTo(raiz.dado) < 0){
            raiz.esquerda = deletaRecursivo(raiz.esquerda, dado);
            //se o no que a gente procurar for uma folha da esquerda
            //quando encontrar o no, vai retornar nulo (deleta o no)

            //se o no que a gente procurar for da esquerda, mas tiver um filho
            //vai substituir pelo filho (deleta o no)

            //se o no que a gente procurar for da esquerda e tiver 2 filhos
            //vai substituir pelo valor minimo da subarvore direita
            //vai deletar o valor que substituimos e esta repetido
        }
        else if (dado.compareTo(raiz.dado) > 0){
            raiz.direita = deletaRecursivo(raiz.direita, dado); 
        }
        else  { //encontrou com o valor procurado
            if (raiz.esquerda == null) {
                return raiz.direita; 
            }
            else if (raiz.direita == null) {
                return raiz.esquerda; 
            }

            //tem 2 filhos, entao...
            raiz.dado = minimoValor(raiz.direita); // o minimo da subarvore direita substitui 
            raiz.direita = deletaRecursivo(raiz.direita, raiz.dado); //remove o no com valor igual que sobrou
        }
        atualizaAltura(raiz);
        return rebalancear(raiz); 
    } 

    private T minimoValor(No<T> raiz){
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

    private No<T> inserirRecursivo(No<T> raiz, T dado) { 
        if (raiz == null) { 
            raiz = new No<T>(dado);  
        } 
        else if (dado.compareTo(raiz.dado) < 0){
            raiz.esquerda = inserirRecursivo(raiz.esquerda, dado);
        }
        else if (dado.compareTo(raiz.dado) > 0) {
            raiz.direita = inserirRecursivo(raiz.direita, dado); 
        }  

        atualizaAltura(raiz);
        return rebalancear(raiz);
    }

    public List<T> emOrdem(List<T> listaEmOrdem) { 
        List<No<T>> listaNo = listaEmOrdem.stream().map((t) -> new No<T>(t)).collect(Collectors.toList());
        return emOrdemRecursivo(raiz, listaNo)
        .stream().map((t) -> t.dado).collect(Collectors.toList());
    } 

    public List<No<T>> emOrdemNo(List<No<T>> listaEmOrdem) { 
        return emOrdemRecursivo(raiz, listaEmOrdem);
    } 
   
    private List<No<T>> emOrdemRecursivo(No<T> raiz, List<No<T>> listaEmOrdem) {
        if (raiz != null) { 
            emOrdemRecursivo(raiz.esquerda, listaEmOrdem); 
            listaEmOrdem.add(raiz);
            emOrdemRecursivo(raiz.direita, listaEmOrdem);
        }
        return listaEmOrdem;
    }

    public Optional<T> pesquisar(T dado)  { 
        Optional<No<T>> talvezNo = Optional.ofNullable(pesquisarRecursivo(raiz, dado));
        return talvezNo.map( (No<T> t) -> t.dado);
    } 

    private No<T> pesquisarRecursivo(No<T> raiz, T dado)  { 
        if (raiz==null || raiz.dado.compareTo(dado) == 0) {
            return raiz; 
        }   
        if (raiz.dado.compareTo(dado) > 0){
            return pesquisarRecursivo(raiz.esquerda, dado); 
        }
        return pesquisarRecursivo(raiz.direita, dado); 
    }

    private int altura(No<T> no) {
        return no != null ? no.altura : -1;
    }
    
    private void atualizaAltura(No<T> no) {
        int alturaEsquerda = altura(no.esquerda);
        int alturaDireita = altura(no.direita);
        no.altura = Math.max(alturaEsquerda, alturaDireita) + 1;
    }
    
    private int fatorDeBalanceamento(No<T> no) {
        return altura(no.direita) - altura(no.esquerda);
    }

    private No<T> rotacionaDireita(No<T> no) {
        No<T> noEsquerda = no.esquerda;
        
        no.esquerda = noEsquerda.direita;
        noEsquerda.direita = no;
        
        atualizaAltura(no);
        atualizaAltura(noEsquerda);
        
        return noEsquerda;
    }

    private No<T> rotacionaEsquerda(No<T> no) {
        No<T> noDireita = no.direita;
        
        no.direita = noDireita.esquerda;
        noDireita.esquerda = no;
        
        atualizaAltura(no);
        atualizaAltura(noDireita);
        
        return noDireita;
    }

    private No<T> rebalancear(No<T> no) {
        int fatorDeBalanceamento = fatorDeBalanceamento(no);
      
        // esquerda desbalanceada
        if (fatorDeBalanceamento < -1) {
          if (fatorDeBalanceamento(no.esquerda) <= 0) {
            // rotacao direita
            no = rotacionaDireita(no);
          } else {
            // rotacao esquerda-direita
            no.esquerda = rotacionaDireita(no.esquerda);
            no = rotacionaEsquerda(no);
          }
        }
      
        if (fatorDeBalanceamento > 1) {
          if (fatorDeBalanceamento(no.direita) >= 0) {
            // rotacao esquerda
            no = rotacionaEsquerda(no);
          } else {
            // rotacao direita-equerda
            no.direita = rotacionaDireita(no.direita);
            no = rotacionaEsquerda(no);
          }
        }
        return no;
    }
}

