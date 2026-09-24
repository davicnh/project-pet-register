# Project Pet Register

API REST desenvolvida com **Java e Spring Boot** para cadastro e gerenciamento de pets.

O projeto evoluiu de uma aplicação de terminal com armazenamento em arquivos para uma API REST utilizando **PostgreSQL**, com validações, buscas dinâmicas, tratamento de exceções e testes unitários.

---

## Tecnologias

- Java 25
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Hibernate
- Bean Validation
- JUnit
- Mockito
- Maven

---

## Funcionalidades

- Cadastrar, listar, atualizar e excluir pets
- Buscar pet por ID
- Buscar pets por múltiplos critérios
- Limitar pesquisas a até dois critérios
- Validação dos dados recebidos
- Tratamento global de exceções
- Testes unitários da camada de serviço

---

## Busca

A API permite pesquisar por:

- Nome
- Raça
- Cidade
- Endereço
- Idade
- Peso
- Tipo
- Sexo

As buscas são construídas dinamicamente utilizando **JPA Specifications**.

Exemplos:

```http
GET /pets/search?name=Rex
```

```http
GET /pets/search?city=Blumenau&type=DOG
```

---

## Arquitetura

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

O projeto utiliza **DTOs** para entrada de dados, **JPA Specifications** para consultas dinâmicas e exceções personalizadas para tratamento de erros.

---

## Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/pets` | Cadastrar pet |
| `GET` | `/pets` | Listar pets |
| `GET` | `/pets/{id}` | Buscar por ID |
| `GET` | `/pets/search` | Buscar por critérios |
| `PUT` | `/pets/{id}` | Atualizar pet |
| `DELETE` | `/pets/{id}` | Excluir pet |

---

## Testes

Testes unitários utilizando **JUnit e Mockito**, cobrindo:

- Busca por ID
- Cadastro
- Atualização
- Exclusão
- Recursos inexistentes
- Limite de critérios de pesquisa

