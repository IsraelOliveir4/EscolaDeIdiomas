
# 🏫 Sistema de Gestão Escolar - Escola de Idiomas

![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=openjdk)
![Spring](https://img.shields.io/badge/Spring_Boot-3.1-6DB33F?logo=spring)
![React](https://img.shields.io/badge/React-18-61DAFB?logo=react)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-24.0-2496ED?logo=docker)

## 📌 Visão Geral

Sistema completo para administração de matrículas, professores e agendamentos de aulas em escola de idiomas, com arquitetura moderna e containerizada.

## 🚀 Tecnologias Utilizadas

| Camada          | Tecnologias                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| **Frontend**    | React 18, Axios, Vite, TailwindCSS                                         |
| **Backend**     | Spring Boot 3.1, JPA/Hibernate, Lombok, Validation                         |
| **Banco Dados** | PostgreSQL 16                                                              |
| **Infra**       | Docker 24, Docker Compose, Build Multi-Estágio                             |

## ✅ Funcionalidades Implementadas

### Backend (Spring Boot)
✔ CRUD completo de alunos e professores  
✔ Validação de dados com Bean Validation  
✔ Integração com PostgreSQL  
✔ Configuração de CORS  
✔ Tratamento global de exceções  
✔ Health Check endpoints (`/actuator/health`)  

### Frontend (React)
✔ Cadastro dinâmico com máscaras (CPF, telefone)  
✔ Listagem com paginação  
✔ Formulários com validação em tempo real  
✔ Integração REST com tratamento de erros  
✔ Responsividade básica  

### Infraestrutura
✔ Dockerização de todos os componentes  
✔ Orquestração com Docker Compose  
✔ Volumes persistentes para PostgreSQL  

## 📋 Pendências/Melhorias Futuras

### Backend 
🔲 Autenticação JWT   
🔲 Cache com Redis  

### Frontend
🔲 Sistema de autenticação  
🔲 Dashboard analítico  
🔲 Testes E2E  

## 🛠️ Como Executar

### Pré-requisitos
- Docker 24+
- Docker Compose 2.20+

```bash
# Clone o repositório do projeto
git clone https://github.com/SENAI-SD/qa-junior-00925-2025-089.039.185-84
cd escola-idiomas

# Inicie os containers
docker-compose up --build -d
```
Acesse:

Frontend: http://localhost:3000

Backend: http://localhost:8080/api

Documentação Swagger: http://localhost:8080/swagger-ui.html
