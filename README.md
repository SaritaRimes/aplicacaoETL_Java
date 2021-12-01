### Aplicação ETL (Extract, Transform and Load)

O presente repositório contém uma aplicação que realiza a **extração** de dados de diversas páginas, aplica a estes uma **transformação**, ordenando-os de forma crescente, e os **disponibiliza** em uma API.



Linguagem da aplicação :arrow_right:  *Java*

Extração feita através de :arrow_right: *protocolo HTTP*

Algoritmo de ordenação :arrow_right: *Insertion Sort*

Framework :arrow_right: *Spring Boot*

Arquitetura da API :arrow_right: *RESTful*



A disponibilização dos números foi dividida em 10.000 páginas com 100 elementos cada, espelhando-se nos dados extraídos. Para acessar cada uma das páginas, deve ser adicionado ao final do endereço do navegador a expressão `?page=num_pag`, onde `num_pag` é o inteiro relativo a qual página deseja-se ter acesso.