# Session Payment API

## Описание
Session Payment API — это тестовый проект, реализующий API для авторизации пользователей и проведения платежей. Проект включает три основных эндпоинта: `login`, `logout` и `payment`.

## Стек технологий
- **Java 17**
- **Spring Boot 3.3.2**
- **PostgreSQL 15.7**
- **Flyway 9.22.3**
- **Lombok**
- **Hibernate**
- **Maven**

## Установка и запуск

### 1. Клонирование репозитория
```bash
git clone https://github.com/yourusername/session-payment-api.git
cd session-payment-api
```

### 2. Поднятие базы данных PostgreSQL с использованием Docker
```bash
docker run --name session_payment_api -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -p 5432:5432 -d postgres:15.7
```

### 3. Конфигурация проекта
Проект настроен для подключения к базе данных PostgreSQL с использованием следующих параметров:
```bash
URL: jdbc:postgresql://localhost:5432/postgres
Username: postgres
Password: postgres
```
### 4. Запуск проекта
Для сборки и запуска проекта выполните команды:

bash
Копировать код
mvn clean install
mvn spring-boot:run

### 5. Доступ к API
API будет доступен по адресу http://localhost:8080.

## Эндпоинты API
### 1. POST /login
Авторизация пользователя. При успешной авторизации возвращается JWT токен.

### 2. POST /logout
Деактивация токена и завершение сессии.

### 3. POST /payment
Платежная операция. Снимает 1.1 USD с баланса пользователя при каждом вызове.

## Миграции базы данных
Миграции базы данных управляются с помощью Flyway. Скрипты миграции находятся в директории src/main/resources/db/migration.