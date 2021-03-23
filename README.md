# Clock In-Out

### DESAFIO – BACKEND | SPRING BOOT

#### Tecnologias utilizadas no projeto
``` 
 * Java 11
 * Spring Boot
 * OpenAPI
 * MongoDb
 * Docker
 * Gradle
 * Junit
 * Error Prone
 * Jacoco
``` 

#### Execução do sistema

#### MongoDB
```  
Abra o terminal na raiz do projeto onde esta o arquivo docker-compose
Digite docker-compose up -d
``` 

#### Aplicação
``` 
Apos subir o conteiner com o MongoDb, abrir um novo terminal na raiz da aplicação.

Digite ./gradlew clean build
Digite ./gradlew bootRun

Ou através de sua IDE
```  

#### OpenApi
```
Com a palicação rodando, digitar no seu navegador.

http://127.0.0.1:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
``` 

#### Exemplo de request
```
{
    "userId":"25855",
    "date":"29-01-2021",
    "time":"08:00"
}
```

#### Exemplo de response
```
{
    "id": "605936e3494a8e62b7122725",
    "userId": "25855",
    "period": "FIRST_PERIOD_IN",
    "date": "29-01-2021",
    "time": "08:00"
}
```


