# 📘 Microservices-Based Quiz Application
## 🚀 Overview
This project is a **Microservices Architecture Quiz Application** built using **Spring Boot, Spring Cloud, OpenFeign, Eureka, API-Gateway, **and **PostgreSQL**.
It demonstrates how distributed services communicate to generate quizzes, fetch questions, and calculate results.

## 🏗️ Microservices in the Project

### 1️⃣ Service Registry (Eureka Server)
- Central registry where all microservices register.
- Provides service discovery.
- Runs on port 8761.

### 2️⃣ Question-Service
Handles everything related to questions:
  - Stores questions in PostgreSQL (questiondb)
  - APIs:
    - Generate random question IDs by category
    - Return questions by list of IDs
    - Calculate score based on submitted answers

### 3️⃣ Quiz-Service
Responsible for quiz creation and scoring:
  - Communicates with Question-Service via OpenFeign
  - Stores quizzes in PostgreSQL (quizdb)
  - APIs:
    - Create a quiz (category, numQ, title)
    - Fetch quiz questions
    - Submit quiz answers and return score

### 4️⃣ API-Gateway
- Single entry point for all clients
- Routes requests to microservices
- Uses Eureka discovery for load balancing
- Runs on port 2525

## 🔗 Communication Flow
| Client → API-Gateway → Quiz-Service → Question-Service → PostgreSQL DBs |

## 🗄️ Databases
### 🟦 questiondb
Stores:
  - Question (id, title, options, right answer, category)

### 🟪 quizdb
  - Stores:
    - Quiz (id, title, list of questionIds)

## 🔌 Tech Stack
- Java 17
- Spring Boot 3
- Spring Cloud 2025
- OpenFeign
- Netflix Eureka
- Spring Data JPA
- PostgreSQL
- API-Gateway (Spring Cloud Gateway)

## ▶️ How to Run
- Start Eureka Server
- Start Question-Service
- Start Quiz-Service
- Start API-Gateway
- Access Gateway endpoints via:
- http://localhost:2525/

## 📡 Main Endpoints
### 🔸 Question-Service
- GET  /question/generate?category=Python&numQ=5
- POST /question/getQuestions
- POST /question/getScore

### 🔸 Quiz-Service
- POST /quiz/createQuiz
- GET  /quiz/getQuizById/{id}
- POST /quiz/submit/{id}

### 🔸 Eureka Dashboard
- http://localhost:8761

## ⭐ Features
- Complete microservices architecture
- Inter-service communication via OpenFeign
- Automatic service registration/discovery
- Distributed data storage
- Clean service separation
- Gateway-level routing

                         ┌───────────────────────────┐
                       │      Client / Frontend     │
                       │ (Postman / React / Angular)│
                       └───────────────┬───────────┘
                                       │
                                       ▼
                         ┌────────────────────────┐
                         │      API GATEWAY       │
                         │     (Port: 2525)       │
                         └─────────────┬──────────┘
                                       │  Routes
     ┌─────────────────────────────────┼─────────────────────────────────┐
     │                                 │                                 │
     ▼                                 ▼                                 ▼
┌──────────────┐            ┌────────────────┐                ┌─────────────────────┐
│ QUIZ-SERVICE │◄──────────►│ SERVICE REGISTRY│◄──────────────►│ QUESTION-SERVICE   │
│  (Port:8090) │  Eureka     │   (Eureka 8761) │   Eureka       │   (Port:8081/8080) │
│  PostgreSQL  │  Client     │   Discovery     │   Client       │   PostgreSQL       │
│   quizdb     │             └────────────────┘                │    questiondb       │
└──────────────┘                                                └─────────────────────┘
      │                                                                 ▲
      │                                                                 │
      │   OpenFeign (REST)                                              │
      └─────────────────────────────────────────────────────────────────┘


