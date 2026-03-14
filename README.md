# Sistema de Gerenciamento de Loja Desktop

Sistema desktop desenvolvido em **Java** utilizando **SQLite** para persistência de dados.  
O sistema gerencia operações básicas de uma loja, como cadastro de produtos e controle de vendas.

---

## Tecnologias utilizadas

- Java 21
- SQLite
- JDBC

---

## Estrutura do projeto

- **Model** → representa as entidades do sistema (Produto, Venda, ItemVenda)  
- **Repository** → comunicação com o banco de dados  
- **Database** → gerencia a conexão com o SQLite  
- **Controller** → controla a lógica do menu e fluxo da aplicação  
- **Service** → responsável pela lógica do sistema  
- **Main** → ponto inicial de execução do sistema

---

## Funcionalidades

- Cadastro de produtos  
- Listagem de produtos  
- Registro de vendas  
- Persistência de dados em banco SQLite

---

## Banco de dados

O sistema utiliza o banco SQLite, armazenado localmente no arquivo:



## Como executar o projeto

1. Clone o repositório

git clone https://github.com/ericpatricio23/dead-line-store.git

2. Abra o projeto em uma IDE Java (como IntelliJ)

3. Execute a classe principal:

SistemaLoja


