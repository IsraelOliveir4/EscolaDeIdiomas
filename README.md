# Processo seletivo - QA

Bem vindo, candidato. 

Estamos felizes que você esteja participando do processo seletivo para a vaga de QA do Senai - Soluções Digitais.

A prova deverá utilizar as seguintes tecnologias: 
- Linguagem de programação orientada a objeto
- Banco de dados PostgreSQL
- GIT

Na etapa da entrevista deverá ser apresentado a aplicação em funcionamento.

## Instruções para a execução da prova

***O documento com o estudo de caso do que precisa ser desenvolvido será enviado por e-mail no horário previsto em edital.***

A prova será uma aplicação web dividida em backend e frontend. O sistema deve ser desenvolvido utilizando uma das seguintes linguagens orientadas a objeto: Java, PHP ou Javascript. O backend e frontend podem ser de linguagens diferentes. O banco de dados deverá ser o PostgreSQL.

Fica a escolha do candidato quais frameworks e servidores serão utilizados, desde que seja uma aplicação web. 

***O Banco utilizado na prova deverá ser PostgreSQL.***

Esse repositório possui apenas esse Readme com as instruções da prova. No entanto, **todo desenvolvimento deve ser commitado nesse repositório** até a data citada no edital.

Por fim, altere esse arquivo com as instruções de como poderemos testar o seu código (quais libs usar, qual servidor, etc) abaixo.

## Informações extras

- Descreva ao final deste documento (Readme.md) o detalhamento de funcionalidades implementadas, sejam elas já descritas na modelagem e / ou extras.
- Detalhar também as funcionalidades que não conseguiu implementar e o motivo.
- Caso tenha adicionado novas libs ou frameworks, descreva quais foram e porque dessa agregação.

(Escreva aqui as instruções para que possamos corrigir sua prova, bem como qualquer outra observação sobre a prova que achar pertinente compartilhar)
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

PGAdmin: http://localhost:5050 (admin@admin.com | 123456)

Documentação Swagger: http://localhost:8080/swagger-ui.html
