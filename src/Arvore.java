public class Arvore {

    public NoArvore raiz;

    public Arvore(){
        raiz = null;
    }

    //Método utilizado para inserir os nós na raiz
    public void inserir(long valor){
        NoArvore novo = new NoArvore(valor);
        novo.valor = valor;
        if (raiz == null){
            raiz = novo;
        }else {
            NoArvore atual = raiz;
            NoArvore pai;
            while (true) {
                pai = atual;
                if (valor < atual.valor) {
                    atual = atual.filhoEsquerdo;
                    if (atual == null) {
                        pai.filhoEsquerdo = novo;
                        break;
                    }
                }
                else {
                    atual = atual.filhoDireito;
                    if (atual == null) {
                        pai.filhoDireito = novo;
                    }
                }

            }
        }
    }


    public void buscar(){

    }

    public void excluir(){

    }

    public void emOrdem(){

    }

    public void posOrdem(){

    }


}
