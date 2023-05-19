public class Arvore {

    public NoArvore raiz;

    public Arvore(){
        raiz = null;
    }

    //Método utilizado para inserir os nós na raiz
    public void inserir(long valor){
        NoArvore novo = new NoArvore(valor);
        novo.valor = valor;
        novo.filhoDireito = null;
        novo.filhoEsquerdo = null;

        if (raiz == null){
            raiz = novo;
        }else {
            NoArvore atual = raiz;
            NoArvore pai;
            while (true) {
                pai = atual;
                if (valor <= atual.valor) {
                    atual = atual.filhoEsquerdo;
                    if (atual == null) {
                        pai.filhoEsquerdo = novo;
                        return;
                    }
                }
                else {
                    atual = atual.filhoDireito;
                    if (atual == null) {
                        pai.filhoDireito = novo;
                        return;
                    }
                }

            }
        }
    }


    public NoArvore buscar(long valor){
     if (raiz == null) return null;
     NoArvore atual = raiz;
     while (atual.valor != valor){
         if (valor < atual.valor) atual = atual.filhoEsquerdo;
         else atual = atual.filhoDireito;
         if (atual == null) return null;
     }
     return atual;
    }

    public void excluir(){

    }

    public void emOrdem(NoArvore atual){
        if (atual != null){
            emOrdem(atual.filhoEsquerdo);
            System.out.println(atual.valor + " ");
            emOrdem(atual.filhoDireito);
        }
    }

    public void posOrdem(NoArvore atual){
        if (atual != null){
            posOrdem(atual.filhoEsquerdo);
            posOrdem(atual.filhoDireito);
            System.out.println(atual.valor + " ");
        }

    }

    public void preOrdem(NoArvore atual){
        if (atual != null){
            System.out.println(atual.valor + "");
            preOrdem(atual.filhoEsquerdo);
            preOrdem(atual.filhoDireito);
        }
    }

    public void caminhar(){
        System.out.println("Em Ordem: ");
        emOrdem(raiz);
        System.out.println("Pós Ordem: ");
        posOrdem(raiz);
        System.out.println("Pré Ordem: ");
        preOrdem(raiz);
    }


}
