public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria(){
        this.raiz = null;
    }

    public void deletaDado(int dado) { 
        raiz = deletaRecursivo(raiz, dado); 
    } 
   
    public No deletaRecursivo(No raiz, int dado)  { 
        if (raiz == null)  return raiz; 
   
        if (dado < raiz.dado)
            raiz.esquerda = deletaRecursivo(raiz.esquerda, dado); 
        else if (dado > raiz.dado)
            raiz.direita = deletaRecursivo(raiz.direita, dado); 
        else  { 
            if (raiz.esquerda == null) 
                return raiz.direita; 
            else if (raiz.direita == null) 
                return raiz.esquerda; 
   
            raiz.dado = minimoValor(raiz.direita); 
            raiz.direita = deletaRecursivo(raiz.direita, raiz.dado); 
        } 
        return raiz; 
    } 

    public int minimoValor(No raiz){
        int minimo = raiz.dado;

        while(raiz.esquerda != null){
            minimo = raiz.esquerda.dado; 
            raiz = raiz.esquerda; 
        } 
        return minimo; 
    }

    public void inserir(int dado)  { 
        raiz = inserirRecursivo(raiz, dado); 
    }

    public No inserirRecursivo(No raiz, int dado) { 
      if (raiz == null) { 
          raiz = new No(dado); 
          return raiz; 
      } 
      if (dado < raiz.dado)
          raiz.esquerda = inserirRecursivo(raiz.esquerda, dado); 
      else if (dado > raiz.dado)
          raiz.direita = inserirRecursivo(raiz.direita, dado); 
      return raiz; 
    }
  

    public void emOrdem() { 
        emOrdemRecursivo(raiz); 
    } 
   
    public void emOrdemRecursivo(No raiz) { 
        if (raiz != null) { 
            emOrdemRecursivo(raiz.esquerda); 
            System.out.print(raiz.dado + " "); 
            emOrdemRecursivo(raiz.direita); 
        } 
    }

    public boolean pesquisar(int dado)  { 
        raiz = pesquisarRecursivo(raiz, dado); 
        if (raiz!= null)
            return true;
        else
            return false;
    } 

    public No pesquisarRecursivo(No raiz, int dado)  { 
        if (raiz==null || raiz.dado==dado) 
            return raiz; 
        if (raiz.dado > dado) 
            return pesquisarRecursivo(raiz.esquerda, dado); 
        return pesquisarRecursivo(raiz.direita, dado); 
    } 
}

