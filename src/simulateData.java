import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class simulateData {
    public static void main(String[] args) throws IOException {
        Random gerador = new Random();
        int tamVetor = 10;
        int i;
        double[] v = new double[tamVetor];
        PrintWriter teste1 = new PrintWriter(new FileWriter("teste.txt"));

        for (i = 0; i < tamVetor; ++i) {
            v[i] = gerador.nextDouble();
            //System.out.println(v[i]);
        }

        /* Aplicando a funcao do algoritmo de ordenacao Insertion Sort */
        insertionSort(v);


        for (i = 0; i < tamVetor; ++i) {
            System.out.println(v[i]);
        }

        List<Double> list = new ArrayList<Double>();
        File file = new File("teste.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(Double.parseDouble(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ignored) {}
        }

        System.out.println(list);





    }


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




    /* Criando a funcao para o Insertion Sort */
    /*public static void insertionSort(int[] vetor, int posicaoElemento) {
        int auxiliar;
        int posicaoComparacao = posicaoElemento - 1;
        int posicaoInicial = posicaoElemento;

        if (posicaoElemento < 1) {
            System.out.println("A posição inicial de comparação deve ser maior do que 0.");
            return;
        }

        while (posicaoComparacao >= 0 && vetor[posicaoElemento] < vetor[posicaoComparacao]) {
            auxiliar = vetor[posicaoComparacao];
            vetor[posicaoComparacao] = vetor[posicaoElemento];
            vetor[posicaoElemento] = auxiliar;
            posicaoElemento = posicaoComparacao;
            --posicaoComparacao;
        }

        if (posicaoInicial == (vetor.length - 1)) {System.out.println("A ordenação foi finalizada."); return;}

        insertionSort(vetor, posicaoInicial + 1);
    }*/
}
