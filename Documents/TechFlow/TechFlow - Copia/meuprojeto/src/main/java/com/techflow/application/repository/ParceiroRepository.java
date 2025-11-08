package com.techflow.application.repository;

import com.techflow.application.model.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
    
    // Buscar por email (único)
    Optional<Parceiro> findByEmail(String email);
    
    // Buscar por nome (case insensitive)
    List<Parceiro> findByNomeContainingIgnoreCase(String nome);
    
    // Buscar por telefone
    Optional<Parceiro> findByTelefone(String telefone);
    
    // Verificar se email já existe
    boolean existsByEmail(String email);
    
    // Buscar parceiros por parte do nome ou email
    @Query("SELECT p FROM Parceiro p WHERE " +
           "LOWER(p.nome) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Parceiro> findByNomeOrEmailContaining(@Param("searchTerm") String searchTerm);
    
    // Contar parceiros ativos
    @Query("SELECT COUNT(p) FROM Parceiro p")
    long countAllParceiros();
}
