import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Arvore {

    //Criando uma instancia da classe No Arvore com o objeto da raiz
    public NoArvore raiz;

    //Iniciando a arvore com a raiz nula
    public Arvore(){
        raiz = null;
    }

    //Método utilizado para inserir os nós na raiz
    public void inserir(long valor) {
        // Cria um novo nó com o valor passado como parâmetro
        NoArvore novo = new NoArvore(valor);
        // Define o valor do nó novo com o valor passado
        novo.valor = valor;
        // Define os filhos esquerdo e direito do nó novo como nulos
        novo.filhoDireito = null;
        novo.filhoEsquerdo = null;

        // Verifica se a árvore está vazia
        if (raiz == null) {
            // Se estiver vazia, o nó novo se torna a raiz da árvore
            raiz = novo;
        } else {
            // Se a árvore não estiver vazia, inicia a busca pelo local de inserção
            NoArvore atual = raiz;
            NoArvore pai;
            while (true) {
                pai = atual;
                // Verifica se o valor a ser inserido é menor ou igual ao valor do nó atual
                if (valor <= atual.valor) {
                    // Se for menor ou igual, desce para o filho esquerdo do nó atual
                    atual = atual.filhoEsquerdo;
                    // Verifica se chegou ao final do ramo esquerdo da árvore
                    if (atual == null) {
                        // Se chegou ao final, o nó novo é inserido como filho esquerdo do pai
                        pai.filhoEsquerdo = novo;
                        return;
                    }
                } else {
                    // Se for maior, desce para o filho direito do nó atual
                    atual = atual.filhoDireito;
                    // Verifica se chegou ao final do ramo direito da árvore
                    if (atual == null) {
                        // Se chegou ao final, o nó novo é inserido como filho direito do pai
                        pai.filhoDireito = novo;
                        return;
                    }
                }
            }
        }
    }

    //Método para realizar busca na árvore
    public NoArvore buscar(long valor) {
        // Verifica se a árvore está vazia
        if (raiz == null) {
            // Se estiver vazia, retorna nulo, pois não há elementos para buscar
            return null;
        }

        // Inicia a busca a partir da raiz da árvore
        NoArvore atual = raiz;

        // Continua a busca até encontrar o valor desejado
        while (atual.valor != valor) {
            // Verifica se o valor buscado é menor que o valor do nó atual
            if (valor < atual.valor) {
                // Se for menor, desce para o filho esquerdo do nó atual
                atual = atual.filhoEsquerdo;
            } else {
                // Se for maior, desce para o filho direito do nó atual
                atual = atual.filhoDireito;
            }

            // Verifica se chegou ao final da árvore sem encontrar o valor buscado
            if (atual == null) {
                // Se chegou ao final, exibe uma mensagem de valor não encontrado e retorna nulo
                System.out.println("Valor " + valor + " não encontrado.");
                return null;
            }
        }

        // Após encontrar o valor desejado, exibe uma mensagem de valor encontrado
        System.out.println("Valor " + valor + " encontrado.");
        return atual;
    }

    //Método utilizado para excluir um nó na árvore
    public boolean excluir(long valor) {
        // Verifica se a árvore está vazia
        if (raiz == null) {
            // Se estiver vazia, retorna falso, pois não há elementos para excluir
            return false;
        }

        // Inicia a busca pelo valor a ser excluído a partir da raiz da árvore
        NoArvore atual = raiz;
        NoArvore pai = raiz;
        boolean filhoEsquerdo = true;

        // Realiza a busca até encontrar o valor desejado ou chegar ao final da árvore
        while (atual.valor != valor) {
            // Atualiza o nó pai e desce para o filho esquerdo ou direito, dependendo do valor comparado
            pai = atual;
            if (valor < atual.valor) {
                atual = atual.filhoEsquerdo;
                filhoEsquerdo = true;
            } else {
                atual = atual.filhoDireito;
                filhoEsquerdo = false;
            }

            // Verifica se chegou ao final da árvore sem encontrar o valor a ser excluído
            if (atual == null) {
                // Se chegou ao final, exibe uma mensagem de valor não encontrado para exclusão e retorna falso
                System.out.println("Valor " + valor + " não existe na árvore para exclusão.");
                return false;
            }
        }

        // Após encontrar o valor desejado, realiza a exclusão
        if (atual.filhoEsquerdo == null && atual.filhoDireito == null) {
            // Caso 1: O nó a ser excluído não possui filhos
            if (atual == raiz) {
                raiz = null;
            } else if (filhoEsquerdo) {
                pai.filhoEsquerdo = null;
            } else {
                pai.filhoDireito = null;
            }
        } else if (atual.filhoDireito == null) {
            // Caso 2: O nó a ser excluído possui apenas filho esquerdo
            if (atual == raiz) {
                raiz = atual.filhoEsquerdo;
            } else if (filhoEsquerdo) {
                pai.filhoEsquerdo = atual.filhoEsquerdo;
            } else {
                pai.filhoDireito = atual.filhoEsquerdo;
            }
        } else {
            // Caso 3: O nó a ser excluído possui filho direito
            // Encontra o sucessor do nó a ser excluído
            NoArvore sucessor = noSucessor(atual);

            if (atual == raiz) {
                raiz = sucessor;
            } else if (filhoEsquerdo) {
                pai.filhoEsquerdo = sucessor;
            } else {
                pai.filhoDireito = sucessor;
            }

            // Atualiza as referências do sucessor
            sucessor.filhoEsquerdo = atual.filhoEsquerdo;
        }

        // Retorna verdadeiro para indicar que a exclusão foi realizada com sucesso
        return true;
    }

    public NoArvore noSucessor(NoArvore apaga) {
        // Inicializa o pai do sucessor, o próprio sucessor e o nó atual
        NoArvore paiSucessor = apaga;
        NoArvore sucessor = apaga;
        NoArvore atual = apaga.filhoEsquerdo;

        // Encontra o sucessor do nó a ser excluído percorrendo os filhos esquerdos
        while (atual != null) {
            // Atualiza o pai do sucessor, o sucessor e o nó atual
            paiSucessor = sucessor;
            sucessor = atual;
            atual = atual.filhoEsquerdo;
        }

        // Verifica se o sucessor não é o filho direito do nó a ser excluído
        if (sucessor != apaga.filhoDireito) {
            // Ajusta as referências para remover o sucessor da sua posição original
            paiSucessor.filhoEsquerdo = sucessor.filhoDireito;
            sucessor.filhoDireito = apaga.filhoDireito;
        }

        // Retorna o nó sucessor encontrado
        return sucessor;
    }

    // Realiza uma travessia em ordem (esquerda, raiz, direita) na árvore a partir do nó atual
    public void emOrdem(NoArvore atual){
        if (atual != null){
            // Percorre o filho esquerdo em ordem
            emOrdem(atual.filhoEsquerdo);
            // Imprime o valor do nó atual
            System.out.println(atual.valor + " ");
            // Percorre o filho direito em ordem
            emOrdem(atual.filhoDireito);
        }
    }

    // Realiza uma travessia em pós-ordem (esquerda, direita, raiz) na árvore a partir do nó atual
    public void posOrdem(NoArvore atual){
        if (atual != null){
            // Percorre o filho esquerdo em pós-ordem
            posOrdem(atual.filhoEsquerdo);
            // Percorre o filho direito em pós-ordem
            posOrdem(atual.filhoDireito);
            // Imprime o valor do nó atual
            System.out.println(atual.valor + " ");
        }
    }

    // Realiza uma travessia em pré-ordem (raiz, esquerda, direita) na árvore a partir do nó atual
    public void preOrdem(NoArvore atual){
        if (atual != null){
            // Imprime o valor do nó atual
            System.out.println(atual.valor + " ");
            // Percorre o filho esquerdo em pré-ordem
            preOrdem(atual.filhoEsquerdo);
            // Percorre o filho direito em pré-ordem
            preOrdem(atual.filhoDireito);
        }
    }

    // Lê um arquivo de texto contendo comandos e executa cada comando
    public void lerArquivo(String nomeArquivo) {
        try {
            // Abre o arquivo para leitura
            FileReader fr = new FileReader(nomeArquivo, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(fr);
            String linha;

            while ((linha = br.readLine()) != null) {
                // Lê cada linha do arquivo e remove espaços em branco antes e depois da linha
                executarComando(linha.trim());
            }

            // Fecha o arquivo
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("O arquivo não foi lido, verifique o arquivo, o caminho e tente novamente!");
            e.printStackTrace();
        }
    }

    public void executarComando(String comando) {
        String[] partes = comando.split(" ");
        String operacao = partes[0];

        if (operacao.equals("INCLUIR")) {
            int valor = Integer.parseInt(partes[1]);
            // Chama o método de inserção da árvore binária passando o elemento
            inserir(valor);
        } else if (operacao.equals("EXCLUIR")) {
            int valor = Integer.parseInt(partes[1]);
            // Chama o método de exclusão da árvore binária passando o elemento
            excluir(valor);
        } else if (operacao.equals("BUSCAR")) {
            int valor = Integer.parseInt(partes[1]);
            // Chama o método de busca da árvore binária passando o elemento
            buscar(valor);
        } else if (operacao.equals("IMPRIME")) {
            String tipoPercorre = partes[1];
            if (tipoPercorre.equals("PREORDEM")) {
                // Chama o método de percorrer em pré-ordem da árvore binária
                preOrdem(raiz);
            } else if (tipoPercorre.equals("POSORDEM")) {
                // Chama o método de percorrer em pós-ordem da árvore binária
                posOrdem(raiz);
            } else if (tipoPercorre.equals("EMORDEM")) {
                // Chama o método de percorrer em ordem da árvore binária
                emOrdem(raiz);
            }
        }
    }


}
