# Vacation Pay Calculator
Микросервис для расчёта отпускных

## Описание
Приложение предоставляет один REST API endpoint: GET "/calculacte", который рассчитывает сумму отпускных:
- по количеству дней отпуска
- по датам начала и конца отпуска с учётом праздников в 2026 году

## Запуск приложения

### Локально
    - mvn clean package
    - java -jar target/vacation-calculator-*.jar

### Docker
    - docker-compose up --build

Приложение будет доступно по адресу:
- http://localhost:8080

## Swagger (API документация)

После запуска приложения Swagger UI доступен по адресу:
- http://localhost:8080/swagger-ui/index.html