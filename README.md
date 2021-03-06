# election-java-rmi-MatheusFFM
# Chat Multicast Java

## AUTOR
* [Matheus Felipe Ferreira Martins](https://github.com/MatheusFFM)

## Execução

```bash
# Clonar o repositório
$ git clone https://github.com/PUC-DISCIPLINAS/election-java-rmi-MatheusFFM.git
```

### Execução - Servidor

```bash
# Caminho para os arquivos .class
cd chat-multicast-java-MatheusFFM/out/production/chat-multicast-java

# Executar o servidor
java network.Server
```

Retorna que o servidor está aberto se a execução ocorrer com sucesso.

### Execução - Cliente

```bash
# Caminho para os arquivos .class
cd chat-multicast-java-MatheusFFM/out/production/chat-multicast-java

# Executar o cliente
java network.Client
```

Possui diversas interações com o usuário. Inicia pedindo um username e então mostra o menu de opções. Execute outros clients para poder conferir a interação entre eles.


## Classes

- **SERVER:** Cria um server TCP que instancia a classe Connection. Possui a classe main do lado do servidor.
- **CONECTION:** Realiza o controle dos dados, recebe comandos de usuário a partir da classe Client, manipula ou cria salas e usuários e retorna um valor de resposta para a classe Client, podendo também ser um erro.
- **CLIENT:** Estabelece uma conexão com o servidor, recebe inputs do usuário e os manda para essa conexão, trata a resposta do usuário e controla a conexão multicast com as salas de chat. Possui a classe main do lado do cliente.
- **COMMANDS:** Possui os comandos do sistema.
- **USER:** Modelo do usuário, possuindo seu nome.
- **ROOM:** Modelo da sala, com seu nome, endereço e usuários.
- **IpAddress:** Modelo de endereço para gerar endereços multicast com mais facilidade.