package com.techflow.application.controller;

import com.techflow.application.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class AuthController {
    
    // POST /auth/login - Autenticar usuário
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            // Autenticação simples (para demonstração)
            if ("admin".equals(loginDTO.getUsername()) && "admin".equals(loginDTO.getPassword())) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login realizado com sucesso");
                response.put("token", "fake-jwt-token-" + System.currentTimeMillis());
                response.put("user", Map.of(
                    "id", 1,
                    "username", "admin",
                    "role", "ADMIN"
                ));
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Credenciais inválidas");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    // POST /auth/logout - Logout do usuário
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout realizado com sucesso");
        return ResponseEntity.ok(response);
    }
    
    // GET /auth/validate - Validar token
    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String token) {
        try {
            // Validação simples do token (para demonstração)
            if (token != null && token.startsWith("Bearer fake-jwt-token-")) {
                Map<String, Object> response = new HashMap<>();
                response.put("valid", true);
                response.put("user", Map.of(
                    "id", 1,
                    "username", "admin",
                    "role", "ADMIN"
                ));
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> error = new HashMap<>();
                error.put("valid", false);
                error.put("error", "Token inválido");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("valid", false);
            error.put("error", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
