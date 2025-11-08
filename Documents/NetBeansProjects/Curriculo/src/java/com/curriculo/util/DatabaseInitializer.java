package com.curriculo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    
    private static final String DB_URL = "jdbc:sqlite:curriculo.db";
    
    public static void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 Statement stmt = connection.createStatement()) {
                
                // Criar tabela curriculos
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS curriculos (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome_completo TEXT NOT NULL,
                        email TEXT,
                        telefone TEXT,
                        endereco TEXT,
                        linkedin TEXT,
                        objetivo TEXT,
                        resumo_profissional TEXT,
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
                    )
                """);
                
                // Criar tabela experiencias
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS experiencias (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        curriculo_id INTEGER NOT NULL,
                        cargo TEXT NOT NULL,
                        empresa TEXT NOT NULL,
                        periodo TEXT NOT NULL,
                        descricao TEXT,
                        FOREIGN KEY (curriculo_id) REFERENCES curriculos(id) ON DELETE CASCADE
                    )
                """);
                
                // Criar tabela educacoes
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS educacoes (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        curriculo_id INTEGER NOT NULL,
                        curso TEXT NOT NULL,
                        instituicao TEXT NOT NULL,
                        periodo TEXT NOT NULL,
                        status TEXT,
                        FOREIGN KEY (curriculo_id) REFERENCES curriculos(id) ON DELETE CASCADE
                    )
                """);
                
                // Criar tabela habilidades
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS habilidades (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        curriculo_id INTEGER NOT NULL,
                        habilidade TEXT NOT NULL,
                        FOREIGN KEY (curriculo_id) REFERENCES curriculos(id) ON DELETE CASCADE
                    )
                """);
                
                // Criar tabela idiomas
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS idiomas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        curriculo_id INTEGER NOT NULL,
                        idioma TEXT NOT NULL,
                        FOREIGN KEY (curriculo_id) REFERENCES curriculos(id) ON DELETE CASCADE
                    )
                """);
                
                // Habilitar foreign keys no SQLite
                stmt.execute("PRAGMA foreign_keys = ON");
                
                System.out.println("Banco de dados SQLite inicializado com sucesso!");
                
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar banco de dados: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        initializeDatabase();
    }
}
