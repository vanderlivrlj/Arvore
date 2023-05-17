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


    public NoArvore buscar(long valor, NoArvore atual){
        if (valor == atual.valor)
            return atual;
        else if (atual == null)
            return null;
        else if (valor < atual.valor)
            return buscar (valor, atual.filhoEsquerdo);
        else
            return buscar (valor, atual.filhoDireito);
    }

    public void excluir(){

    }

    public void emOrdem(NoArvore atual){
        if (atual != null){
            emOrdem(atual.filhoDireito);
            System.out.println(atual.valor + " ");
            emOrdem(atual.filhoDireito);
        }
    }

    public void posOrdem(){

    }


}
