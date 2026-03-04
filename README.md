# ForumHUB

API REST desenvolvida como parte do Challenge Back-End da Alura.
A aplicação permite autenticação via JWT e gerenciamento completo de tópicos.

---
## Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Security
* JWT (Auth0)
* Spring Data JPA
* MySQL
* Flyway
* Lombok
* Maven
---

## Autenticação

A API utiliza autenticação baseada em **JWT**.

1. O usuário realiza login.
2. Recebe um token JWT.
3. Envia o token no header das requisições protegidas:


## Configuração do Banco de Dados

Criar banco MySQL:

sql
CREATE DATABASE forumhub;


Configurar application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true

api.security.token.secret=123456




## Endpoints Disponíveis

### Autenticação
| POST   | "/auth"  | Realiza login e retorna token JWT |

---

### 📝 Tópicos

| POST   | "/topicos"      | Cadastrar novo tópico |
| GET    | "/topicos"      | Listar tópicos        |
| GET    | "/topicos/{id}" | Detalhar tópico       |
| PUT    | "/topicos/{id}" | Atualizar tópico      |
| DELETE | "/topicos/{id}" | Remover tópico        |

---
