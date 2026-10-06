# Workshop Spring Boot + MongoDB

API REST de usuários e posts construída com Spring Boot e MongoDB. O projeto explora modelagem de dados em banco NoSQL: documentos aninhados, referências entre documentos, DTOs, consultas customizadas e tratamento de exceções.

## Tecnologias

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data MongoDB
- MongoDB
- Lombok
- Maven (wrapper incluso)

## Funcionalidades

- CRUD completo de usuários
- Listagem dos posts de um usuário
- Busca de posts por id, por título e por texto livre dentro de um intervalo de datas
- Posts com autor embutido (`AuthorDTO`, com `id` e `name`) e comentários aninhados (`CommentDTO`)
- Usuários com referência aos seus posts (`@DBRef`)
- Respostas de erro padronizadas em JSON (`StandardError`)
- Carga inicial de dados de exemplo ao subir a aplicação

## Arquitetura

```
com.myckamorais.workshop_springboot_mongodb
├── config                 # Instantiation: popula o banco com dados de exemplo
├── domain                 # Entidades (User, Post)
├── dto                    # UserDTO, AuthorDTO, CommentDTO
├── repository             # Interfaces MongoRepository
├── resources              # Controllers REST (UserResource, PostResource)
│   ├── exceptions         # ResourceExceptionHandler, StandardError
│   └── util               # URL: decodificação de parâmetros e conversão de datas
└── services               # Regras de negócio
    └── exception          # ObjectNotFoundException
```

## Endpoints

### Usuários

| Método | Rota | Descrição | Resposta |
|--------|------|-----------|----------|
| GET | `/users` | Lista todos os usuários | `200` |
| GET | `/users/{id}` | Busca um usuário por id | `200` / `404` |
| POST | `/users` | Cria um usuário | `201` + header `Location` |
| PUT | `/users/{id}` | Atualiza um usuário | `204` |
| DELETE | `/users/{id}` | Remove um usuário | `204` |
| GET | `/users/{id}/posts` | Lista os posts do usuário | `200` |

Exemplo de corpo para `POST` e `PUT`:

```json
{
  "name": "Maria Brown",
  "email": "maria@gmail.com"
}
```

### Posts

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/posts/{id}` | Busca um post por id |
| GET | `/posts/titlesearch?text=bom dia` | Busca posts pelo título |
| GET | `/posts/fullsearch?text=bom&minDate=2018-03-01&maxDate=2018-03-31` | Busca por texto no título, corpo e comentários, dentro de um período |

Parâmetros de `/posts/fullsearch`:

| Parâmetro | Descrição | Padrão |
|-----------|-----------|--------|
| `text` | Texto buscado (ignora maiúsculas/minúsculas) | vazio |
| `minDate` | Data mínima, no formato `yyyy-MM-dd` | 01/01/1970 |
| `maxDate` | Data máxima, no formato `yyyy-MM-dd` | data atual |

As datas são interpretadas em GMT.

### Formato de erro

Quando um recurso não é encontrado, a API responde `404` com:

```json
{
  "timestamp": "2026-10-06T03:00:00Z",
  "status": 404,
  "error": "Resource not found",
  "message": "Object not found",
  "path": "/users/123"
}
```

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

Ao iniciar, a classe `Instantiation` apaga os dados existentes e recria usuários, posts e comentários de exemplo. **Não use com um banco que tenha dados que você queira manter.**

## Testes

```bash
./mvnw test
```

## Aprendizados

- Documentos aninhados vs. referências (`@DBRef`) no MongoDB
- DTOs para controlar o que a API expõe
- Consultas com `@Query` e expressões regulares
- Tratamento global de exceções com `@ControllerAdvice`
- Diferenças de API entre versões do Spring Data (`saveAll`, `findById` com `Optional`)

## Créditos

Projeto desenvolvido a partir do workshop de Spring Boot com MongoDB do professor Nelio Alves.
