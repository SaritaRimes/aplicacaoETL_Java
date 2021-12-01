package io.github.SaritaRimes.getData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/* A classe controller vai fazer a interacao com a API */
@RestController
public class NumbersController {
    /* Criando uma matriz que vai armazenar os dados ordenados */
    public static double[][] dataExpose = new double[10000][100];

    @GetMapping("/numbers")
    public Numbers numbers(@RequestParam(value = "page", defaultValue = "0") int page) {
        /* Abrindo o arquivo .txt de dados ordenados e criando uma lista para armazenar esses dados */
        File file = new File("numerosOrdenados.txt"); /*o arquivo .txt foi movido para a pasta local para
                                                                 facilitar a execucao do programa em outros computadores*/
        List<Double> temporary = new ArrayList<>();

        /* Armazenando os dados do arquivo .txt na lista */
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text = null;

            while ((text = reader.readLine()) != null) {
                temporary.add(Double.parseDouble(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Salvando os elementos da lista na matriz criada */
        int k = 0; //indice dos elementos da lista
        int numberRows = temporary.size()/100; //para que nao sejam acessados, na lista, elementos que nao existem
        for (int i = 0; i < numberRows; ++i) {
            for (int j = 0; j < 100 ; ++j) {
                dataExpose[i][j] = temporary.get(k);
                ++k;
            }
        } /*os dados ordenados sao alocados na matriz na ordem da esquerda para a direita, de cima pra baixo, ou seja,
            dentre todos os dados, o elemento [0][0] contem o menor valor e o elemento [10000][100] contem o maior
            valor*/

        return new Numbers(dataExpose[page]);
    }

}
