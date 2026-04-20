# ☕ ONG Grupo Amparo e Alívio - Backend (API)

![Status](https://img.shields.io/badge/Status-Concluído-brightgreen)
![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![License](https://img.shields.io/badge/License-MIT-blue)

Bem-vindo ao repositório **Backend** do ecossistema digital da ONG **Grupo Amparo e Alívio (GAA)**.

Esta é uma API RESTful de alta performance que serve como o "cérebro" da aplicação. Ela gerencia toda a lógica de negócios, segurança, validação de dados e comunicação com o banco de dados, servindo as informações consumidas pelo [Frontend](https://github.com/GabrieldePaulaDev/Frontend_ONG).

> **Nota:** Esta API foi projetada para rodar em conjunto com o Frontend. Certifique-se de configurar o `application.properties` corretamente antes de iniciar.

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas (Layered Architecture) para garantir a separação de responsabilidades e facilidade de manutenção:

* **Controller Layer:** Pontos de entrada da API (Endpoints REST).
* **Service Layer:** Regras de negócio, validações e lógica complexa.
* **Repository Layer (JPA):** Abstração da comunicação com o banco de dados.
* **DTO (Data Transfer Object):** Garante que apenas os dados necessários trafeguem entre o cliente e o servidor.
* **Mappers:** Conversão limpa entre Entidades e DTOs.
* **Exception Handling:** Tratamento global de erros para respostas HTTP consistentes.

---

## 🚀 Tecnologias Utilizadas

Este projeto utiliza a stack moderna do ecossistema Java:

* ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white) **Java JDK 17+**
* ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white) **Spring Boot 3** (Web, Data JPA, Validation)
* ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apache-maven&logoColor=white) **Maven** (Gerenciamento de dependências)
* ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) **MySQL / PostgreSQL** (Banco de Dados)
* ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=hibernate&logoColor=white) **Hibernate** (ORM)

---

## ✨ Funcionalidades da API

### 📋 Gestão de Voluntários
* **CRUD Completo:** Cadastro, Listagem, Atualização e Remoção.
* **Soft Delete:** Implementação de exclusão lógica (o dado não é apagado, apenas inativado `ativo = false`), preservando o histórico da ONG.
* **Reativação:** Endpoint específico para reativar voluntários antigos.

### 📅 Controle de Disponibilidade Complexa
* **One-to-Many:** Um voluntário pode ter múltiplos períodos de disponibilidade.
* **Formato Flexível:** Suporte a dias da semana e horários combinados (ex: "Segunda: 14:00 - 18:00").

### 🛡️ Segurança e Qualidade
* **CORS Configurado:** Permite conexões seguras vindas do Frontend.
* **Validação de Dados:** Verifica CPF, formatos de data e campos obrigatórios (`@Valid`).
* **Tratamento de Erros:** Respostas JSON claras em caso de falha (404, 400, 500).

---

## 📂 Estrutura de Pastas

O código fonte está organizado dentro de `src/main/java/com/techcare/cadastro_voluntarios`:
backend/ ├── controller/ **Controladores REST (Ex: VoluntarioController)** ├── dto/ **Objetos de Transferência de Dados (Request/Response)** ├── exception/ **Classes de Erro Personalizadas (GlobalExceptionHandler)** ├── mapper/ **Conversores (Entity <-> DTO)** ├── model/ **Entidades do Banco de Dados (Voluntario, Telefone, etc.)** ├── repository/ **Interfaces JPA Repository** └── service/ **Lógica de Negócio**


---

## 🔌 Endpoints Principais

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/api/voluntarios` | Lista todos os voluntários (com filtro de status). |
| `GET` | `/api/voluntarios/{id}` | Busca os detalhes completos de um voluntário. |
| `POST` | `/api/voluntarios` | Cria um novo cadastro. |
| `PUT` | `/api/voluntarios/{id}` | Atualiza dados cadastrais e horários. |
| `DELETE`| `/api/voluntarios/{id}` | Inativa um voluntário (Soft Delete). |
| `PATCH` | `/api/voluntarios/{id}/reativar` | Reativa um voluntário inativo. |

---

## 🔧 Como Rodar Localmente

### Pré-requisitos
1.  **Java JDK 17** ou superior instalado.
2.  **Banco de Dados** (MySQL ou PostgreSQL) rodando.

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/Backend_ONG.git](https://github.com/seu-usuario/Backend_ONG.git)
    ```

2.  **Configure o Banco de Dados:**
    Abra o arquivo `src/main/resources/application.properties` e ajuste as credenciais:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

3.  **Execute a Aplicação:**
    No terminal, dentro da pasta do projeto:
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Teste:**
    A API estará rodando em: `http://localhost:8080`.

---

## 👨‍💻 Autor

Desenvolvido pelo grupo **Techcare** como parte do Projeto Interdisciplinar Fatec.
