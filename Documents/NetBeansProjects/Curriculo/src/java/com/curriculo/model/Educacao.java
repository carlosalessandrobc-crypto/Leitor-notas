package com.curriculo.model;

public class Educacao {
    
    private Long id;
    private Long curriculoId;
    private String curso;
    private String instituicao;
    private String periodo;
    private String status;
    
    public Educacao() {}
    
    public Educacao(Long curriculoId, String curso, String instituicao, String periodo, String status) {
        this.curriculoId = curriculoId;
        this.curso = curso;
        this.instituicao = instituicao;
        this.periodo = periodo;
        this.status = status;
    }
    
    public Educacao(String curso, String instituicao, String periodo, String status) {
        this.curso = curso;
        this.instituicao = instituicao;
        this.periodo = periodo;
        this.status = status;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getCurriculoId() {
        return curriculoId;
    }
    
    public void setCurriculoId(Long curriculoId) {
        this.curriculoId = curriculoId;
    }
    
    public String getCurso() {
        return curso;
    }
    
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public String getInstituicao() {
        return instituicao;
    }
    
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    
    public String getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
