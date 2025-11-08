-- Dados iniciais para desenvolvimento (SQLite)
-- Inserir parceiros de exemplo

-- Inserir dados apenas se a tabela estiver vazia
INSERT INTO parceiros (nome_cliente, endereco, email, numero_telefone) 
SELECT 'João Silva', 'Rua das Flores, 123 - São Paulo/SP', 'joao.silva@email.com', '11987654321'
WHERE NOT EXISTS (SELECT 1 FROM parceiros WHERE email = 'joao.silva@email.com');

INSERT INTO parceiros (nome_cliente, endereco, email, numero_telefone) 
SELECT 'Maria Santos', 'Av. Paulista, 456 - São Paulo/SP', 'maria.santos@email.com', '11876543210'
WHERE NOT EXISTS (SELECT 1 FROM parceiros WHERE email = 'maria.santos@email.com');

INSERT INTO parceiros (nome_cliente, endereco, email, numero_telefone) 
SELECT 'Pedro Oliveira', 'Rua Augusta, 789 - São Paulo/SP', 'pedro.oliveira@email.com', '11765432109'
WHERE NOT EXISTS (SELECT 1 FROM parceiros WHERE email = 'pedro.oliveira@email.com');

INSERT INTO parceiros (nome_cliente, endereco, email, numero_telefone) 
SELECT 'Ana Costa', 'Rua Oscar Freire, 321 - São Paulo/SP', 'ana.costa@email.com', '11654321098'
WHERE NOT EXISTS (SELECT 1 FROM parceiros WHERE email = 'ana.costa@email.com');

INSERT INTO parceiros (nome_cliente, endereco, email, numero_telefone) 
SELECT 'Carlos Ferreira', 'Av. Faria Lima, 654 - São Paulo/SP', 'carlos.ferreira@email.com', '11543210987'
WHERE NOT EXISTS (SELECT 1 FROM parceiros WHERE email = 'carlos.ferreira@email.com');

-- Verificar se os dados foram inseridos
-- SELECT COUNT(*) as total_parceiros FROM parceiros;
