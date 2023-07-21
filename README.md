# perinity-parte-1

# 🛠️ Abrir e rodar o projeto

- Você pode executar a aplicação da maneira que quiser e utilizando a IDE de sua preferência. 
- Caso queira executar a aplicação via linha de comando, execute primeiramente o comando:
  
                   ./mvnw clean package  para linux.
                   .\mvnw clean package  para windows.
- Após isso execute o comando:

                             java -jar <...caminhoParaSeuJar>

- Você pode exportar a coleção criada no Postman para testes com o link : 

      https://api.postman.com/collections/20493089-9e2d4abb-723d-4c9f-9ca3-fe9ada406680?access_key=PMAT-01H5VNF1G1TGNTEZDM8NWGRCJQ

- A aplicação possui um atalho para execução de um container docker prontamente configurado para aplicação no caminho docker/run-postgres.sh, ou se preferir, executar diretamente no terminal : 

      docker run --name perinity-db  -p 5432:5432 -e POSTGRES_DB=application -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres
