# election-java-rmi-MatheusFFM
# Chat Multicast Java

## AUTOR
* [Matheus Felipe Ferreira Martins](https://github.com/MatheusFFM)

## Sobre o sistema
É um sistema que utiliza java rmi para simular uma eleição, em que o eleitor pode votar em um candidato ou ver a quantidade de votos de um candidato. Cada eleitor porderá votar apenas uma vez, essa limitação é de acordo com o nome inserido, permitindo apenas um voto por nome, porém letras maiúsculas e minúsculas são consideradas diferentes.

## Execução

```bash
# Clonar o repositório
$ git clone https://github.com/PUC-DISCIPLINAS/election-java-rmi-MatheusFFM.git
```

### Execução - Servidor

```bash
# Caminho para os arquivos .class
$ cd election-java-rmi/out/production/election-java-rmi

# Executar o servidor
$ java network.ElectionServer
```

Retorna que o servidor está pronto se a execução ocorrer com sucesso.

### Execução - Cliente

```bash
# Caminho para os arquivos .class
$ cd election-java-rmi/out/production/election-java-rmi

# Executar o cliente
$ java network.ElectionClient
```

Inicia pedindo um nome e então oferece a opção de votar ou ver o resultado de um candidato.


## Classes

- **Election:** Interface que implementa Remote e possui os métodos para votar e ver resultados; 
- **ElectionServer:** Inicializa o servidor mas também implementa os métodos lógicos do servant de votar e ver resultados. Possui a classe main do lado do server;
- **ElectionClient:** Inicializa um stub com o servidor, recebe inputs do usuário e executa os métods de ElectionServer, trata a resposta do usuário e controla faz o controle de reconexão ou timeout. Possui a classe main do lado do cliente;
- **User:** Modelo do usuário, possuindo seu nome e código hash.
- **Config:** Possui constantes que representam valores recorrentes durante a aplicação.
- **SenatorFile:** Realiza a leitura e escrita em arquivos para salvar, recuperar e inicializar dados.
- **HashMD5:** Realiza a transformação do nome do usuário para o código hash MD5.