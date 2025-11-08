package com.techflow.application.controller;

import com.techflow.application.dto.ParceiroDTO;
import com.techflow.application.model.Parceiro;
import com.techflow.application.service.ParceiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/parceiros")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class ParceiroController {
    
    @Autowired
    private ParceiroService parceiroService;
    
   
    @GetMapping
    public ResponseEntity<List<Parceiro>> listarTodos() {
        try {
            List<Parceiro> parceiros = parceiroService.buscarTodos();
            return ResponseEntity.ok(parceiros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
   
    @GetMapping("/paginado")
    public ResponseEntity<Page<Parceiro>> listarComPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        try {
            Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<Parceiro> parceiros = parceiroService.buscarComPaginacao(pageable);
            return ResponseEntity.ok(parceiros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Parceiro> buscarPorId(@PathVariable Long id) {
        try {
            Optional<Parceiro> parceiro = parceiroService.buscarPorId(id);
            return parceiro.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Parceiro>> buscarPorTermo(@RequestParam String termo) {
        try {
            List<Parceiro> parceiros = parceiroService.buscarPorTermo(termo);
            return ResponseEntity.ok(parceiros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Parceiro> buscarPorEmail(@PathVariable String email) {
        try {
            Optional<Parceiro> parceiro = parceiroService.buscarPorEmail(email);
            return parceiro.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> criarParceiro(@Valid @RequestBody ParceiroDTO parceiroDTO) {
        try {
            Parceiro novoParceiro = parceiroService.criarParceiro(parceiroDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("id", novoParceiro.getId());
            response.put("message", "Parceiro criado com sucesso");
            response.put("parceiro", novoParceiro);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarParceiro(
            @PathVariable Long id, 
            @Valid @RequestBody ParceiroDTO parceiroDTO) {
        try {
            Parceiro parceiroAtualizado = parceiroService.atualizarParceiro(id, parceiroDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Parceiro atualizado com sucesso");
            response.put("parceiro", parceiroAtualizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarParceiro(@PathVariable Long id) {
        try {
            parceiroService.deletarParceiro(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Parceiro deletado com sucesso");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> contarParceiros() {
        try {
            long count = parceiroService.contarParceiros();
            Map<String, Long> response = new HashMap<>();
            response.put("total", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @GetMapping("/email-exists/{email}")
    public ResponseEntity<Map<String, Boolean>> verificarEmail(@PathVariable String email) {
        try {
            boolean exists = parceiroService.emailExiste(email);
            Map<String, Boolean> response = new HashMap<>();
            response.put("exists", exists);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
