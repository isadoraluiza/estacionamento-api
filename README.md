# Sistema de Estacionamento 

Este projeto é um Sistema de Controle de Estacionamento desenvolvido em Java com Spring Boot.
Ele permite gerenciar a entrada e saída de veículos, calcular valores conforme o tempo de permanência e registrar diferentes tipos de serviços oferecidos pelo estacionamento (por hora, diária ou mensalista).

O sistema foi projetado para ser simples de usar e fácil de testar através do Swagger UI.

Softwares Necessários
Para executar o projeto, instale:

Java 21

IntelliJ IDEA (Community version)

XAMPP (para executar o MySQL)

Maven

Swagger UI (já incluído no projeto — acessado no navegador)

Como rodar o projeto
1️ Faça o download ou clone o repositório do projeto.

2️  Abra o XAMPP e inicie o MySQL.

3 Acesse o phpMyAdmin e crie o banco de dados utilizado no projeto.

4 Abra o arquivo application.properties

Altere essas informações para corresponder ao MySQL da sua máquina.

spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

5 Abra o projeto no IntelliJ, aguarde o Maven baixar as dependências;

Rode o arquivo principal da aplicação;

Quando a aplicação iniciar, o sistema estará disponível em:

http://localhost:8080

Testando a API pelo Swagger UI

Acesse no navegador:

http://localhost:8080/swagger-ui.html
No Swagger, você poderá:

Registrar entrada de veículos

Registrar saída

Consultar registros

Testar cálculos automáticos de permanência e valor

Validar os endpoints sem precisar usar Postman

Como o Sistema Funciona
O sistema de estacionamento é baseado em regras simples:

Entrada de Veículos
O usuário informa placa, modelo e tipo de serviço.

O sistema registra a data e hora de entrada automaticamente.

Saída de Veículos
Ao registrar a saída, o sistema calcula automaticamente:

Tempo total de permanência

Tipo de cobrança (hora, diária ou mensalista)

Valor final a pagar

Tipos de Serviço
O sistema trabalha com:

Por hora → cálculo proporcional ao tempo

Diária → valor fixo

Mensalista → cadastro especial que não cobra por saída

Persistência
Todas as informações são salvas no banco MySQL configurado no XAMPP.
