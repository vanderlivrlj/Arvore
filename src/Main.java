public class Main {

    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        arvore.inserir(10);
        arvore.inserir(15);
        arvore.inserir(8);
        arvore.inserir(20);

        arvore.buscar(10);

        arvore.caminhar();
        //arvore.preOrdem(arvore.raiz);
    }

}
