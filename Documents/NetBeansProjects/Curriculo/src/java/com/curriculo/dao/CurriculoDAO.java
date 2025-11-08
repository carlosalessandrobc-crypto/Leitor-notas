package com.curriculo.dao;

import com.curriculo.model.Curriculo;
import com.curriculo.model.Experiencia;
import com.curriculo.model.Educacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurriculoDAO {
    
    private static final String DB_URL = "jdbc:sqlite:curriculo.db";
    
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver não encontrado", e);
        }
        return DriverManager.getConnection(DB_URL);
    }
    
    public Long salvarCurriculo(Curriculo curriculo) throws SQLException {
        String sql = """
            INSERT INTO curriculos (nome_completo, email, telefone, endereco, linkedin, objetivo, resumo_profissional)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, curriculo.getNomeCompleto());
            stmt.setString(2, curriculo.getEmail());
            stmt.setString(3, curriculo.getTelefone());
            stmt.setString(4, curriculo.getEndereco());
            stmt.setString(5, curriculo.getLinkedin());
            stmt.setString(6, curriculo.getObjetivo());
            stmt.setString(7, curriculo.getResumoProfissional());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar currículo, nenhuma linha afetada.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long curriculoId = generatedKeys.getLong(1);
                    curriculo.setId(curriculoId);
                    
                    // Salvar experiências
                    salvarExperiencias(curriculoId, curriculo.getExperiencias());
                    
                    // Salvar educações
                    salvarEducacoes(curriculoId, curriculo.getEducacoes());
                    
                    // Salvar habilidades
                    salvarHabilidades(curriculoId, curriculo.getHabilidades());
                    
                    // Salvar idiomas
                    salvarIdiomas(curriculoId, curriculo.getIdiomas());
                    
                    return curriculoId;
                } else {
                    throw new SQLException("Falha ao criar currículo, nenhum ID obtido.");
                }
            }
        }
    }
    
    public Curriculo buscarCurriculo(Long id) throws SQLException {
        String sql = "SELECT * FROM curriculos WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curriculo curriculo = new Curriculo();
                    curriculo.setId(rs.getLong("id"));
                    curriculo.setNomeCompleto(rs.getString("nome_completo"));
                    curriculo.setEmail(rs.getString("email"));
                    curriculo.setTelefone(rs.getString("telefone"));
                    curriculo.setEndereco(rs.getString("endereco"));
                    curriculo.setLinkedin(rs.getString("linkedin"));
                    curriculo.setObjetivo(rs.getString("objetivo"));
                    curriculo.setResumoProfissional(rs.getString("resumo_profissional"));
                    
                    // Carregar experiências
                    curriculo.setExperiencias(buscarExperiencias(id));
                    
                    // Carregar educações
                    curriculo.setEducacoes(buscarEducacoes(id));
                    
                    // Carregar habilidades
                    curriculo.setHabilidades(buscarHabilidades(id));
                    
                    // Carregar idiomas
                    curriculo.setIdiomas(buscarIdiomas(id));
                    
                    return curriculo;
                }
            }
        }
        return null;
    }
    
    public List<Curriculo> listarCurriculos() throws SQLException {
        String sql = "SELECT * FROM curriculos ORDER BY created_at DESC";
        List<Curriculo> curriculos = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Curriculo curriculo = new Curriculo();
                curriculo.setId(rs.getLong("id"));
                curriculo.setNomeCompleto(rs.getString("nome_completo"));
                curriculo.setEmail(rs.getString("email"));
                curriculo.setTelefone(rs.getString("telefone"));
                curriculo.setEndereco(rs.getString("endereco"));
                curriculo.setLinkedin(rs.getString("linkedin"));
                curriculo.setObjetivo(rs.getString("objetivo"));
                curriculo.setResumoProfissional(rs.getString("resumo_profissional"));
                
                // Carregar dados relacionados
                curriculo.setExperiencias(buscarExperiencias(curriculo.getId()));
                curriculo.setEducacoes(buscarEducacoes(curriculo.getId()));
                curriculo.setHabilidades(buscarHabilidades(curriculo.getId()));
                curriculo.setIdiomas(buscarIdiomas(curriculo.getId()));
                
                curriculos.add(curriculo);
            }
        }
        return curriculos;
    }
    
    private void salvarExperiencias(Long curriculoId, List<Experiencia> experiencias) throws SQLException {
        if (experiencias == null || experiencias.isEmpty()) return;
        
        String sql = "INSERT INTO experiencias (curriculo_id, cargo, empresa, periodo, descricao) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (Experiencia exp : experiencias) {
                stmt.setLong(1, curriculoId);
                stmt.setString(2, exp.getCargo());
                stmt.setString(3, exp.getEmpresa());
                stmt.setString(4, exp.getPeriodo());
                stmt.setString(5, exp.getDescricao());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
    
    private void salvarEducacoes(Long curriculoId, List<Educacao> educacoes) throws SQLException {
        if (educacoes == null || educacoes.isEmpty()) return;
        
        String sql = "INSERT INTO educacoes (curriculo_id, curso, instituicao, periodo, status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (Educacao edu : educacoes) {
                stmt.setLong(1, curriculoId);
                stmt.setString(2, edu.getCurso());
                stmt.setString(3, edu.getInstituicao());
                stmt.setString(4, edu.getPeriodo());
                stmt.setString(5, edu.getStatus());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
    
    private void salvarHabilidades(Long curriculoId, List<String> habilidades) throws SQLException {
        if (habilidades == null || habilidades.isEmpty()) return;
        
        String sql = "INSERT INTO habilidades (curriculo_id, habilidade) VALUES (?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (String habilidade : habilidades) {
                if (habilidade != null && !habilidade.trim().isEmpty()) {
                    stmt.setLong(1, curriculoId);
                    stmt.setString(2, habilidade.trim());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        }
    }
    
    private void salvarIdiomas(Long curriculoId, List<String> idiomas) throws SQLException {
        if (idiomas == null || idiomas.isEmpty()) return;
        
        String sql = "INSERT INTO idiomas (curriculo_id, idioma) VALUES (?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (String idioma : idiomas) {
                if (idioma != null && !idioma.trim().isEmpty()) {
                    stmt.setLong(1, curriculoId);
                    stmt.setString(2, idioma.trim());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        }
    }
    
    private List<Experiencia> buscarExperiencias(Long curriculoId) throws SQLException {
        String sql = "SELECT * FROM experiencias WHERE curriculo_id = ? ORDER BY id";
        List<Experiencia> experiencias = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, curriculoId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Experiencia exp = new Experiencia();
                    exp.setId(rs.getLong("id"));
                    exp.setCurriculoId(rs.getLong("curriculo_id"));
                    exp.setCargo(rs.getString("cargo"));
                    exp.setEmpresa(rs.getString("empresa"));
                    exp.setPeriodo(rs.getString("periodo"));
                    exp.setDescricao(rs.getString("descricao"));
                    experiencias.add(exp);
                }
            }
        }
        return experiencias;
    }
    
    private List<Educacao> buscarEducacoes(Long curriculoId) throws SQLException {
        String sql = "SELECT * FROM educacoes WHERE curriculo_id = ? ORDER BY id";
        List<Educacao> educacoes = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, curriculoId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Educacao edu = new Educacao();
                    edu.setId(rs.getLong("id"));
                    edu.setCurriculoId(rs.getLong("curriculo_id"));
                    edu.setCurso(rs.getString("curso"));
                    edu.setInstituicao(rs.getString("instituicao"));
                    edu.setPeriodo(rs.getString("periodo"));
                    edu.setStatus(rs.getString("status"));
                    educacoes.add(edu);
                }
            }
        }
        return educacoes;
    }
    
    private List<String> buscarHabilidades(Long curriculoId) throws SQLException {
        String sql = "SELECT habilidade FROM habilidades WHERE curriculo_id = ? ORDER BY id";
        List<String> habilidades = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, curriculoId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    habilidades.add(rs.getString("habilidade"));
                }
            }
        }
        return habilidades;
    }
    
    private List<String> buscarIdiomas(Long curriculoId) throws SQLException {
        String sql = "SELECT idioma FROM idiomas WHERE curriculo_id = ? ORDER BY id";
        List<String> idiomas = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, curriculoId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    idiomas.add(rs.getString("idioma"));
                }
            }
        }
        return idiomas;
    }
    
    public void deletarCurriculo(Long id) throws SQLException {
        String sql = "DELETE FROM curriculos WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
