package com.curriculo.model;

public class Experiencia {
    
    private Long id;
    private Long curriculoId;
    private String cargo;
    private String empresa;
    private String periodo;
    private String descricao;
    
    public Experiencia() {}
    
    public Experiencia(Long curriculoId, String cargo, String empresa, String periodo, String descricao) {
        this.curriculoId = curriculoId;
        this.cargo = cargo;
        this.empresa = empresa;
        this.periodo = periodo;
        this.descricao = descricao;
    }
    
    public Experiencia(String cargo, String empresa, String periodo, String descricao) {
        this.cargo = cargo;
        this.empresa = empresa;
        this.periodo = periodo;
        this.descricao = descricao;
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
    
    public String getCargo() {
        return cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getEmpresa() {
        return empresa;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public String getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
