-- Dados iniciais para testes (SQLite em memória)
-- Dados mínimos para testes automatizados

INSERT INTO parceiros (id_parceiro, nome_cliente, endereco, email, numero_telefone, created_at, updated_at) 
VALUES 
(1, 'Test User 1', 'Test Address 1', 'test1@test.com', '11999999999', datetime('now'), datetime('now')),
(2, 'Test User 2', 'Test Address 2', 'test2@test.com', '11888888888', datetime('now'), datetime('now'));

-- Dados para testes específicos
INSERT INTO parceiros (id_parceiro, nome_cliente, endereco, email, numero_telefone, created_at, updated_at) 
VALUES 
(999, 'Delete Test User', 'Delete Test Address', 'delete@test.com', '11777777777', datetime('now'), datetime('now'));
