@echo off
echo Starting Microservices E-commerce System...

echo Starting Config Server...
start "Config Server" cmd /c "cd config-server && mvn spring-boot:run"

echo Waiting 5 seconds for Config Server to start...
timeout /t 5 /nobreak > nul

echo Starting Eureka Server...
start "Eureka Server" cmd /c "cd eureka-server && mvn spring-boot:run"

echo Waiting 15 seconds for Eureka Server to start...
timeout /t 15 /nobreak > nul

echo Starting User Service...
start "User Service" cmd /c "cd user-service && mvn spring-boot:run"

echo Starting Product Service...
start "Product Service" cmd /c "cd product-service && mvn spring-boot:run"

echo Starting Order Service...
start "Order Service" cmd /c "cd order-service && mvn spring-boot:run"

echo Starting API Gateway...
start "API Gateway" cmd /c "cd api-gateway && mvn spring-boot:run"

echo All services are starting up! Please check the individual windows for logs.
echo Eureka Dashboard: http://localhost:8761
echo Test Order API: http://localhost:8080/api/orders/101/details
pause
