# Configurar Maven
$env:MAVEN_HOME = "C:\Users\Alessandro\Documents\TechFlow\TechFlow - Copia\meuprojeto\tools\apache-maven-3.9.5"
$env:PATH = "$env:MAVEN_HOME\bin;$env:PATH"

Write-Host "===========================================" -ForegroundColor Green
Write-Host "  Compilando TechFlow com Maven" -ForegroundColor Green  
Write-Host "===========================================" -ForegroundColor Green
Write-Host ""

# Compilar o projeto
mvn clean compile

Write-Host ""
Write-Host "===========================================" -ForegroundColor Green
Write-Host "  Empacotando JAR" -ForegroundColor Green
Write-Host "===========================================" -ForegroundColor Green

# Criar JAR
mvn package -DskipTests

Write-Host ""
Write-Host "Compilação concluída!" -ForegroundColor Green
