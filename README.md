# Workshop Spring Boot + MongoDB

API REST de usuários e posts construída com Spring Boot e MongoDB. O projeto explora modelagem de dados em banco NoSQL: documentos aninhados, referências entre documentos, DTOs e consultas customizadas.

## Tecnologias

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data MongoDB
- MongoDB
- Lombok
- Maven (wrapper incluso)

## Funcionalidades

- Listagem e busca de usuários por id
- Listagem dos posts de um usuário
- Posts com autor embutido (`AuthorDTO`, apenas `id` e `name`) e comentários aninhados (`CommentDTO`)
- Usuários com referência aos seus posts (`@DBRef`)
- Busca de posts por texto (título, corpo e comentários) dentro de um intervalo de datas
- Tratamento de erro para objeto não encontrado (`ObjectNotFoundException`)
- Carga inicial de dados de exemplo ao subir a aplicação

## Arquitetura

O projeto segue a divisão em camadas:

```
com.myckamorais.workshop_springboot_mongodb
├── config        # Instantiation: popula o banco com dados de exemplo
├── domain        # Entidades (User, Post)
├── dto           # UserDTO, AuthorDTO, CommentDTO
├── repository    # Interfaces MongoRepository
├── resources     # Controllers REST
└── services      # Regras de negócio
```

<!-- confirmar: pacotes de exceção/handler e a classe utilitária URL -->

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/users` | Lista todos os usuários (retorna `UserDTO`) |
| GET | `/users/{id}` | Busca um usuário por id <!-- confirmar --> |
| GET | `/users/{id}/posts` | Lista os posts de um usuário |
| GET | `/posts/{id}` | Busca um post por id <!-- confirmar --> |
| GET | `/posts/fullsearch` | Busca posts por texto e período <!-- confirmar rota --> |

Parâmetros da busca completa:

| Parâmetro | Descrição | Padrão |
|-----------|-----------|--------|
| `text` | Texto buscado no título, corpo e comentários (ignora maiúsculas/minúsculas) | vazio |
| `minDate` | Data mínima | 01/01/1970 |
| `maxDate` | Data máxima | data atual |

<!-- confirmar o formato de data aceito (ex.: yyyy-MM-dd) -->

## Como executar

### Pré-requisitos

- JDK 25
- MongoDB em execução na porta padrão (`27017`)

### Passos

1. Clone o repositório:

```bash
   git clone https://github.com/Myckamorais/workshop-springboot-mongodb.git
   cd workshop-springboot-mongodb
```

2. Confira a conexão com o MongoDB em `src/main/resources/application.properties`, por exemplo:

```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/workshop_mongo
```

3. Execute a aplicação:

```bash
   ./mvnw spring-boot:run
```

   No Windows: `mvnw.cmd spring-boot:run`

4. Acesse `http://localhost:8080/users`.

Ao iniciar, a classe `Instantiation` apaga os dados existentes e cria usuários e posts de exemplo.

## Testes

```bash
./mvnw test
```

## Aprendizados

- Documentos aninhados vs. referências (`@DBRef`) no MongoDB
- Uso de DTOs para controlar o que a API expõe
- Consultas com `@Query` e expressões regulares
- Diferenças de API entre versões do Spring Data (`saveAll`, `findById` com `Optional`)
