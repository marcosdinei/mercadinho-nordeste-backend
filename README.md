# Mercadinho Nordeste - Backend
[![Java Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Badge](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![PostgreSQL Badge](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)

## Descrição
API em Java com Spring & SpringBoot para gerenciar os produtos, compras e vendas do Mercadinho Nordeste e que será consumida por uma [aplicação Angular](https://github.com/marcosdinei/mercadinho-nordeste-frontend).

O Mercadinho Nordeste está localizado no Bosque Brasil, Macaíba-RN. Para facilitar a movimentação do caixa (feita com anotações em cadernos), o sistema foi pensado com o objetivo de registrar os produtos, compras e vendas, além de gerar relatórios periódicos de toda a movimentação.

## Dependências
- ``SpringData JPA``
- ``Spring Web``
- ``Validation``
- ``Spring Doc``
- ``PostgreSQL``
- ``Flyway``
- ``Lombok``

## Diagrama de Classes
[Clique aqui](https://lucid.app/lucidchart/b226f064-d01c-4728-a9ab-161c867ebfa0/edit?viewport_loc=-243%2C58%2C2476%2C1216%2C0_0&invitationId=inv_3502613d-7d40-42fc-a0e9-3c0a8f9a143c) para visualizar o diagrama.

## Como inicializar a aplicação
### Requisitos
- [Java 19](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Maven 3](https://maven.apache.org/download.cgi)

### Build
Deve ser criado um banco de dados ``db_mercadinhonordeste`` no PostgreSQL. Para realizar o _build_, navegue até a pasta raiz do projeto e utilize o comando ``mvn clean package``.

### Inicialização da aplicação
Após o processo de _build_ ser finalizado, ainda na pasta raiz do projeto utilize o comando ``java -jar target/mercadinhonordeste-0.0.1-SNAPSHOT.jar``.

## Documentação
Esse projeto possui documentação com SwaggerUI. Para visualizar, acessar ``{URL}:8080/api/swagger-ui/index.html`` (quando a aplicação estiver rodando localmente, URL = localhost).
