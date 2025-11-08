package com.techflow.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.File;

@Configuration
public class DatabaseConfig {
    
    /**
     * Configuração específica para desenvolvimento (SQLite)
     * Cria diretório para o banco SQLite se não existir
     */
    @Profile("dev")
    @Bean
    public String createSQLiteDirectory() {
        try {
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                boolean created = dataDir.mkdirs();
                if (created) {
                    System.out.println("✅ Diretório ./data criado para SQLite");
                } else {
                    System.out.println("⚠️ Não foi possível criar diretório ./data");
                }
            } else {
                System.out.println("✅ Diretório ./data já existe");
            }
            
            // Verificar se o arquivo do banco pode ser criado
            File dbFile = new File("./data/techflow-dev.db");
            if (!dbFile.exists()) {
                System.out.println("🔄 Banco SQLite será criado em: " + dbFile.getAbsolutePath());
            } else {
                System.out.println("✅ Banco SQLite encontrado: " + dbFile.getAbsolutePath());
            }
            
            return "SQLite directory configured";
        } catch (Exception e) {
            System.err.println("❌ Erro ao configurar SQLite: " + e.getMessage());
            return "SQLite configuration failed";
        }
    }
    
    /**
     * Inicializador de dados para desenvolvimento
     */
    @Profile("dev")
    @Bean
    public DataSourceInitializer dataSourceInitializerDev(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data-dev.sql"));
        populator.setContinueOnError(true);
        
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
    
    /**
     * Inicializador de dados para testes
     */
    @Profile("test")
    @Bean
    public DataSourceInitializer dataSourceInitializerTest(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data-test.sql"));
        populator.setContinueOnError(true);
        
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
    
    /**
     * Configurações específicas para produção
     */
    @Profile("prod")
    @Configuration
    @ConfigurationProperties(prefix = "spring.datasource")
    public static class ProductionDatabaseConfig {
        
        @Bean
        public void validateProductionDatabase() {
            System.out.println("🚀 Configuração de produção ativa - MySQL");
            System.out.println("⚠️  Certifique-se de que o banco MySQL está configurado corretamente");
        }
    }
}
