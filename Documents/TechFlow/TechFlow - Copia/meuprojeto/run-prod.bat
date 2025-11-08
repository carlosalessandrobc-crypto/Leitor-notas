@echo off
echo ==========================================
echo   TechFlow - Executando em Produção
echo   Database: MySQL (localhost:3306/techflow_prod)
echo ==========================================
echo.

java -jar -Dspring.profiles.active=prod target/techflow-0.0.1-SNAPSHOT.jar

pause
