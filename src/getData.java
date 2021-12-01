import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class getData {
    /* Cria a variavel que armazena o endereco URL */
    public static String temporario = "http://challenge.dienekes.com.br/api/numbers?page=1";

    public static void main(String[] args) throws IOException, InterruptedException {

        int j, indexInicial, indexFinal;
        int numbersDataVectorPosition = 0;
        double[] numbersDataVector = new double[1000000];

        int numberPage = 1;
        String numbersData = temporario.replace("1", Integer.toString(numberPage)); //para que seja possivel iniciar a partir de qualquer pagina
        temporario = numbersData;

        /* Iniciando o loop para leitura e armazenamento dos dados de todas as paginas */
        while (true) {
            /* Criando o cliente HTTP */
            HttpClient client = HttpClient.newHttpClient();

            /* Criando a requisicao */
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .timeout(Duration.ofMinutes(10))
                    .uri(URI.create(numbersData))
                    .build();

            /* Enviando solicitacao de resposta */
            HttpResponse <String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            /* Imprimindo o status code e o conteudo recebido */
            System.out.println(response.statusCode()); /*imprime o status code para saber se o processo foi
                                                         bem sucedido*/
            if (response.statusCode() != 200) {continue;} /*retorna para tentar acessar a pagina novamente caso
                                                            nao consiga*/
            //System.out.println(response.body()); //imprime o corpo da pagina

            /* Salvando os dados da pagina em uma string */
            String numbersDataString = response.body();
            System.out.println("numbersDataString: " + numbersDataString);
            System.out.println("numberPage = " + numberPage);

            /* Selecionando apenas o vetor de numeros da string */
            indexInicial = numbersDataString.indexOf("["); /*indice de abertura do colchete que guarda elementos
                                                             numericos*/
            indexFinal = numbersDataString.indexOf("]"); /*indice de fechamento do colchete que guarda elementos
                                                           numericos*/
            String numbersDataSubstring = numbersDataString.substring(indexInicial + 1, indexFinal);
            if ((indexFinal - indexInicial) == 1) {break;} /*quando o vetor de elementos numericos for vazio,
                                                             o loop eh interrompido*/

            /* Transformando os dados numericos da string para float */
            String[] numbersDataStringSplit = numbersDataSubstring.split(",");

            for (j = numbersDataVectorPosition; j < numbersDataVectorPosition + numbersDataStringSplit.length; ++j) {
                numbersDataVector[j] = Double.parseDouble(numbersDataStringSplit[j - numbersDataVectorPosition]);
            }
            numbersDataVectorPosition += numbersDataStringSplit.length;

            /* Alterando o endereco para acessar a pagina seguinte no proximo loop */
            numbersData = temporario.replace(Integer.toString(numberPage), Integer.toString(numberPage + 1));
            temporario = numbersData;

            ++numberPage; //atualiza o contador
        }

        /* Retirando as possiveis posicoes que nao foram ocupadas no vetor criado inicialmente */
        double[] numbersDataVectorFinal = new double[numbersDataVectorPosition];
        System.arraycopy(numbersDataVector, 0, numbersDataVectorFinal, 0, numbersDataVectorPosition);

        /* Aplicando a funcao do algoritmo de ordenacao Insertion Sort */
        insertionSort(numbersDataVectorFinal);

        /* Salvando os elementos do vetor ordenado em um arquivo .txt */
        PrintWriter numerosOrdenados = new PrintWriter(new FileWriter("numerosOrdenados.txt"));

        for (int i = 0; i < numbersDataVectorPosition; ++i) {
            numerosOrdenados.println(numbersDataVectorFinal[i]);
        }

        numerosOrdenados.flush();
        numerosOrdenados.close();
    }




    /* Criando a funcao para o Insertion Sort */
    /*a recursividade nao pode ser utilizada pois sao muitos os elementos para serem ordenados
      e a linguagem Java gera um erro de capacidade maxima de memoria atingida*/
    public static void insertionSort(double[] vetor) {
        int posicaoElemento, posicaoComparacao, posicaoInicial, tamV;
        double auxiliar;
        tamV = vetor.length;

        for (posicaoElemento = 1; posicaoElemento < tamV; ++posicaoElemento) {
            System.out.println("posicaoElemento = " + posicaoElemento);
            posicaoComparacao = posicaoElemento - 1;
            posicaoInicial = posicaoElemento;

            while (posicaoComparacao >= 0 && vetor[posicaoElemento] < vetor[posicaoComparacao]) {
                auxiliar = vetor[posicaoComparacao];
                vetor[posicaoComparacao] = vetor[posicaoElemento];
                vetor[posicaoElemento] = auxiliar;
                posicaoElemento = posicaoComparacao;
                --posicaoComparacao;
            }
            posicaoElemento = posicaoInicial;
        }
    }
}

/*posicaoInicial = 1; //posicao que inicia sendo comparada
        posicaoComparacao = posicaoInicial - 1; //posicao que inicia a comparacao

        while (posicaoComparacao >= 0 && v[posicaoInicial] < v[posicaoComparacao]) {
            auxiliar = v[posicaoComparacao];
            v[posicaoComparacao] = v[posicaoInicial];
            v[posicaoInicial] = auxiliar;
            posicaoInicial = posicaoComparacao;
            --posicaoComparacao;
        }

        posicaoInicial = 2; //posicao que inicia sendo comparada
        posicaoComparacao = posicaoInicial - 1; //posicao que inicia a comparacao
        while (posicaoComparacao >= 0 && v[posicaoInicial] < v[posicaoComparacao]) {
            auxiliar = v[posicaoComparacao];
            v[posicaoComparacao] = v[posicaoInicial];
            v[posicaoInicial] = auxiliar;
            posicaoInicial = posicaoComparacao;
            --posicaoComparacao;
        }

        posicaoInicial = 3; //posicao que inicia sendo comparada
        posicaoComparacao = posicaoInicial - 1; //posicao que inicia a comparacao
        while (posicaoComparacao >= 0 && v[posicaoInicial] < v[posicaoComparacao]) {
            auxiliar = v[posicaoComparacao];
            v[posicaoComparacao] = v[posicaoInicial];
            v[posicaoInicial] = auxiliar;
            posicaoInicial = posicaoComparacao;
            --posicaoComparacao;
        }

        posicaoInicial = 4; //posicao que inicia sendo comparada
        posicaoComparacao = posicaoInicial - 1; //posicao que inicia a comparacao
        while (posicaoComparacao >= 0 && v[posicaoInicial] < v[posicaoComparacao]) {
            auxiliar = v[posicaoComparacao];
            v[posicaoComparacao] = v[posicaoInicial];
            v[posicaoInicial] = auxiliar;
            posicaoInicial = posicaoComparacao;
            --posicaoComparacao;
        }

        posicaoInicial = 5; //posicao que inicia sendo comparada
        posicaoComparacao = posicaoInicial - 1; //posicao que inicia a comparacao
        while (posicaoComparacao >= 0 && v[posicaoInicial] < v[posicaoComparacao]) {
            auxiliar = v[posicaoComparacao];
            v[posicaoComparacao] = v[posicaoInicial];
            v[posicaoInicial] = auxiliar;
            posicaoInicial = posicaoComparacao;
            --posicaoComparacao;
        }

        posicaoInicial = 6; //posicao que inicia sendo comparada
        posicaoComparacao = posicaoInicial - 1; //posicao que inicia a comparacao
        while (posicaoComparacao >= 0 && v[posicaoInicial] < v[posicaoComparacao]) {
            auxiliar = v[posicaoComparacao];
            v[posicaoComparacao] = v[posicaoInicial];
            v[posicaoInicial] = auxiliar;
            posicaoInicial = posicaoComparacao;
            --posicaoComparacao;
        }

        posicaoInicial = 7; //posicao que inicia sendo comparada
        posicaoComparacao = posicaoInicial - 1; //posicao que inicia a comparacao
        while (posicaoComparacao >= 0 && v[posicaoInicial] < v[posicaoComparacao]) {
            auxiliar = v[posicaoComparacao];
            v[posicaoComparacao] = v[posicaoInicial];
            v[posicaoInicial] = auxiliar;
            posicaoInicial = posicaoComparacao;
            --posicaoComparacao;
        }*/