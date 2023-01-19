
# Votes API

Gerenciamento de sessões para votações entre cooperados


## Tech Stack

**Language:** Java 11

**Stack:** Spring Boot, Web, Data, Test e Bean Validation, Log4J,

**Dados:** Postgres, H2 para testes

**Healt check:**


## Installation

Install my-project with npm

```bash
  npm install my-project
  cd my-project
```
    
## API Reference

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.


## Deployment

To deploy this project run

```bash
  npm run deploy
```


## Running Tests

To run tests, run the following command

```bash
  npm run test
```

