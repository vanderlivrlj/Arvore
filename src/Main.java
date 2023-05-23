public class Main  {

    public static void main(String[] args)  {
        Arvore arvore = new Arvore();

       /* arvore.inserir(10);
        arvore.inserir(15);
        arvore.inserir(8);
        arvore.inserir(20);

        arvore.buscar(10);

        arvore.excluir(8);

        arvore.preOrdem(arvore.raiz);*/

        //Path do caminho absoluto do arquivo txt. No meu caso estou utilizando Ubuntu.
        arvore.lerArquivo("/home/vanderli/Documentos/arquivo/file.txt");
    }

}
