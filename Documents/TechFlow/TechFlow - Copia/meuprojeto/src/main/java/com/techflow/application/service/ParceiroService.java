package com.techflow.application.service;

import com.techflow.application.dto.ParceiroDTO;
import com.techflow.application.model.Parceiro;
import com.techflow.application.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParceiroService {
    
    @Autowired
    private ParceiroRepository parceiroRepository;
    
    // Criar novo parceiro
    public Parceiro criarParceiro(ParceiroDTO parceiroDTO) {
        // Verificar se email já existe
        if (parceiroRepository.existsByEmail(parceiroDTO.getEmail())) {
            throw new RuntimeException("Email já está em uso: " + parceiroDTO.getEmail());
        }
        
        Parceiro parceiro = new Parceiro(
            parceiroDTO.getNome(),
            parceiroDTO.getEndereco(), 
            parceiroDTO.getEmail(),
            parceiroDTO.getTelefone()
        );
        
        return parceiroRepository.save(parceiro);
    }
    
    // Buscar parceiro por ID
    @Transactional(readOnly = true)
    public Optional<Parceiro> buscarPorId(Long id) {
        return parceiroRepository.findById(id);
    }
    
    // Buscar todos os parceiros
    @Transactional(readOnly = true)
    public List<Parceiro> buscarTodos() {
        return parceiroRepository.findAll();
    }
    
    // Buscar parceiros com paginação
    @Transactional(readOnly = true)
    public Page<Parceiro> buscarComPaginacao(Pageable pageable) {
        return parceiroRepository.findAll(pageable);
    }
    
    // Buscar por nome
    @Transactional(readOnly = true)
    public List<Parceiro> buscarPorNome(String nome) {
        return parceiroRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    // Buscar por email
    @Transactional(readOnly = true)
    public Optional<Parceiro> buscarPorEmail(String email) {
        return parceiroRepository.findByEmail(email);
    }
    
    // Buscar por termo (nome ou email)
    @Transactional(readOnly = true)
    public List<Parceiro> buscarPorTermo(String termo) {
        return parceiroRepository.findByNomeOrEmailContaining(termo);
    }
    
    // Atualizar parceiro
    public Parceiro atualizarParceiro(Long id, ParceiroDTO parceiroDTO) {
        Parceiro parceiro = parceiroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Parceiro não encontrado com ID: " + id));
        
        // Verificar se email já existe (exceto para o próprio parceiro)
        Optional<Parceiro> parceiroComEmail = parceiroRepository.findByEmail(parceiroDTO.getEmail());
        if (parceiroComEmail.isPresent() && !parceiroComEmail.get().getId().equals(id)) {
            throw new RuntimeException("Email já está em uso: " + parceiroDTO.getEmail());
        }
        
        // Atualizar campos
        parceiro.setNome(parceiroDTO.getNome());
        parceiro.setEndereco(parceiroDTO.getEndereco());
        parceiro.setEmail(parceiroDTO.getEmail());
        parceiro.setTelefone(parceiroDTO.getTelefone());
        
        return parceiroRepository.save(parceiro);
    }
    
    // Deletar parceiro
    public void deletarParceiro(Long id) {
        if (!parceiroRepository.existsById(id)) {
            throw new RuntimeException("Parceiro não encontrado com ID: " + id);
        }
        parceiroRepository.deleteById(id);
    }
    
    // Contar total de parceiros
    @Transactional(readOnly = true)
    public long contarParceiros() {
        return parceiroRepository.countAllParceiros();
    }
    
    // Verificar se email existe
    @Transactional(readOnly = true)
    public boolean emailExiste(String email) {
        return parceiroRepository.existsByEmail(email);
    }
}
