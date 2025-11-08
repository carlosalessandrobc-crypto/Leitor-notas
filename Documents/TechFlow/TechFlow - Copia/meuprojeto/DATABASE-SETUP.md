# 🗄️ Configuração Multi-Database - TechFlow

## 📋 Visão Geral

O projeto TechFlow agora suporta **múltiplos ambientes** com diferentes bancos de dados:

- **🔧 Desenvolvimento**: SQLite (arquivo local)
- **🧪 Teste**: SQLite (em memória)  
- **🚀 Produção**: MySQL

## 🚀 Como Executar

### 1. Desenvolvimento (SQLite)
```bash
# Ativar profile de desenvolvimento
java -jar -Dspring.profiles.active=dev target/techflow-0.0.1-SNAPSHOT.jar

# Ou usar o script
./run-dev.bat
```

**Características:**
- ✅ Banco SQLite em `./data/techflow-dev.db`
- ✅ Dados de exemplo pré-carregados
- ✅ Logs detalhados para debug
- ✅ Recria tabelas a cada execução

### 2. Teste (SQLite em Memória)
```bash
# Ativar profile de teste
java -jar -Dspring.profiles.active=test target/techflow-0.0.1-SNAPSHOT.jar
```

**Características:**
- ✅ Banco em memória (rápido)
- ✅ Dados mínimos para testes
- ✅ Logs reduzidos
- ✅ Ideal para testes automatizados

### 3. Produção (MySQL)
```bash
# Ativar profile de produção
java -jar -Dspring.profiles.active=prod target/techflow-0.0.1-SNAPSHOT.jar

# Ou usar o script
./run-prod.bat
```

**Características:**
- ✅ Banco MySQL otimizado
- ✅ Pool de conexões configurado
- ✅ Logs de produção
- ✅ Não altera estrutura do banco

## 🔧 Configuração do MySQL (Produção)

### 1. Criar Banco de Dados
```sql
CREATE DATABASE techflow_prod CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'techflow'@'localhost' IDENTIFIED BY 'senha_segura';
GRANT ALL PRIVILEGES ON techflow_prod.* TO 'techflow'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Configurar Variáveis de Ambiente
```bash
# Windows
set DB_USERNAME=techflow
set DB_PASSWORD=senha_segura

# Linux/Mac
export DB_USERNAME=techflow
export DB_PASSWORD=senha_segura
```

### 3. Executar Migração Inicial
```sql
-- Executar apenas na primeira vez
USE techflow_prod;

CREATE TABLE parceiros (
    id_parceiro BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(100) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    numero_telefone VARCHAR(15) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## 📁 Estrutura de Arquivos

```
src/main/resources/
├── application.yml              # Configuração base
├── application-dev.yml          # SQLite desenvolvimento
├── application-test.yml         # SQLite teste
├── application-prod.yml         # MySQL produção
├── data-dev.sql                # Dados de desenvolvimento
└── data-test.sql               # Dados de teste
```

## 🔍 APIs Disponíveis

Todas as APIs funcionam em qualquer ambiente:

```
GET    /api/parceiros              # Listar todos
GET    /api/parceiros/{id}         # Buscar por ID
POST   /api/parceiros              # Criar novo
PUT    /api/parceiros/{id}         # Atualizar
DELETE /api/parceiros/{id}         # Deletar
POST   /api/auth/login             # Login (admin/admin)
```

## 🧪 Testando

### Desenvolvimento
```bash
# Testar API
curl http://localhost:8080/api/parceiros

# Ver banco SQLite
sqlite3 ./data/techflow-dev.db "SELECT * FROM parceiros;"
```

### Produção
```bash
# Testar API
curl http://localhost:8080/api/parceiros

# Ver banco MySQL
mysql -u techflow -p techflow_prod -e "SELECT * FROM parceiros;"
```

## 🔄 Mudança de Profile

Para mudar o ambiente, basta alterar o profile ativo:

```yaml
# application.yml
spring:
  profiles:
    active: dev  # dev, test, ou prod
```

## 📊 Monitoramento

### Desenvolvimento
- Logs detalhados no console
- SQL queries visíveis
- Stacktraces completos

### Produção  
- Logs otimizados em arquivo
- Métricas disponíveis em `/api/actuator/health`
- Compressão e HTTP/2 habilitados

## ⚠️ Importante

1. **Desenvolvimento**: Dados são recriados a cada execução
2. **Teste**: Banco em memória é limpo após cada execução  
3. **Produção**: Estrutura do banco NÃO é alterada automaticamente

## 🆘 Troubleshooting

### SQLite não funciona
```bash
# Verificar se diretório existe
ls -la ./data/

# Criar manualmente se necessário
mkdir data
```

### MySQL não conecta
```bash
# Verificar se MySQL está rodando
mysql -u root -p -e "SHOW DATABASES;"

# Testar conexão
mysql -u techflow -p techflow_prod -e "SELECT 1;"
```
