# Postgres CDC with Debezium

## Prerequisites

- Docker 
- Docker Compose 
- curl

## Demo
1. Create a .env file in the root directory:
```markdown
POSTGRES_PASSWORD=<PASSWORD>
```
2. Start the infrastructure
```shell
docker-compose up -d
```

3. Create the connector using Kafka Connect REST API:
```shell
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d @cdc/postgres-cdc.json
```
4. Run Spring Boot Consumer
```shell
mvn spring-boot:run
```

5. Insert data into the table
```sql
INSERT INTO orders (user_id, amount, status)
VALUES (101, 12000, 'PROCESSING');
```