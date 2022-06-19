# TASK MANAGER API
Esta API permite salvar, editar, listar, deletar, fazer buscas avançadas, gerenciar tarefas e projetos conforme os requisitos de tempo e prioridade do usuário, criando perfis com credenciais e acessos de diferentes níveis.

## Requisitos
* Java 8
* Spring 2
* PostgreSQL 13

## Dependências
Há numerosas dependências de terceiros usadas no projeto. Abra o arquivo pom.xml para obter detalhes das bibliotecas e versões utilizadas

## Tecnologias utilizadas
* Spring Boot
* Maven
* Web Token Authentication JWT
* Spring Security
* Spring MVC
* Spring Data JPA
* PostgreSQL 13
* Flyway
* MapStruct
* Lombok
* Swagger 3

## Construindo o projeto
No projeto foi utilizado o **PostgreSQL** diretamente num container **PostgreSQL**, se você tiver um banco de dados configurado na sua máquina não esqueça de disponibilizar o serviço na porta 5432 ou alterar as configurações em **application-dev.yml**

Clone o projeto: 
```
https://github.com/alefejonatha/task-manager.git
```

Acesse o diretório do projeto:
```
cd task-manager-main 
```

Use o Maven para construir o servidor:
```
$ mvn spring-boot:run
```
## API endpoints

### Project
Método | URL | Retorno
:---: | --- | ---
GET | .../api/v1/projects | Lista de todos os Project registrados
GET | .../api/v1/projects/{id} | Encontra um Project por id
GET | .../api/v1/projects/find_by_title?title={title} | Lista de Project encontrados por título
POST | .../api/v1/projects | Cria um novo Project
PUT | .../api/v1/projects | Atualiza um Project existente
DELETE | .../api/v1/projects/{id} | Remove um Project

### Task
Método | URL | Retorno
:---: | --- | ---
GET | .../api/v1/tasks | Lista de todos os Task registrados
GET | .../api/v1/tasks/{id} | Encontra um Task por id
GET | .../api/v1/tasks/find_by_title/?title={title} | Lista de Task encontrados por título
GET | .../api/v1/tasks/find_all_by_project/{id} | Lista de Task encontrados por id do Project
GET | .../api/v1/tasks/order_by_status | Lista de Task ordenadas por status ('FINISHED','UNFINISHED')
GET | .../api/v1/tasks/list_tasks_by_filters?initialDate={yyyy-MM-dd}&finalDate={yyyy-MM-dd}&strict={true} | Retorna uma lista de Task de acordo com os filtros (inicialDate, finalDate, strict)
GET | .../api/v1/tasks/list_tasks_for_today?availableTime={HH:mm} | Retorna uma lista de Task de acordo com o tempo disponível do usuário

### User
Método | URL | Retorno
:---: | --- | ---
POST | .../api/v1/users | Cria um novo User
PUT | .../api/v1/users | Atualiza um User existente
DELETE | .../api/v1/users | Remove um User
POST | .../api/v1/users/authenticate | Operação de autenticação do usuário, retorna um JSON Web Token

Você pode obter mais informações através da documentação da própria API acessando o link abaixo:
```
http://localhost:8080/swagger-ui/index.html
```