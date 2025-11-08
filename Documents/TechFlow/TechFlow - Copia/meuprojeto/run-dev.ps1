Write-Host "===========================================" -ForegroundColor Green
Write-Host "  TechFlow - Executando em Desenvolvimento" -ForegroundColor Green
Write-Host "  Database: SQLite (./data/techflow-dev.db)" -ForegroundColor Green
Write-Host "===========================================" -ForegroundColor Green
Write-Host ""

# Criar diretório data se não existir
if (-not (Test-Path "data")) {
    New-Item -ItemType Directory -Name "data"
    Write-Host "✅ Diretório ./data criado" -ForegroundColor Green
}

Write-Host "🚀 Iniciando aplicação..." -ForegroundColor Yellow
Write-Host ""

# Executar aplicação com profile dev
java "-Dspring.profiles.active=dev" -jar "target/techflow-0.0.1-SNAPSHOT.jar"
