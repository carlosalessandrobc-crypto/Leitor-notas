package com.curriculo.model;

import java.util.List;
import java.util.ArrayList;

public class Curriculo {
    
    private Long id;
    private String nomeCompleto;
    private String email;
    private String telefone;
    private String endereco;
    private String linkedin;
    private String objetivo;
    private String resumoProfissional;
    
    private List<Experiencia> experiencias;
    private List<Educacao> educacoes;
    private List<String> habilidades;
    private List<String> idiomas;
    
    public Curriculo() {
        this.experiencias = new ArrayList<>();
        this.educacoes = new ArrayList<>();
        this.habilidades = new ArrayList<>();
        this.idiomas = new ArrayList<>();
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getLinkedin() {
        return linkedin;
    }
    
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    
    public String getObjetivo() {
        return objetivo;
    }
    
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    
    public String getResumoProfissional() {
        return resumoProfissional;
    }
    
    public void setResumoProfissional(String resumoProfissional) {
        this.resumoProfissional = resumoProfissional;
    }
    
    public List<Experiencia> getExperiencias() {
        return experiencias;
    }
    
    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }
    
    public List<Educacao> getEducacoes() {
        return educacoes;
    }
    
    public void setEducacoes(List<Educacao> educacoes) {
        this.educacoes = educacoes;
    }
    
    public List<String> getHabilidades() {
        return habilidades;
    }
    
    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }
    
    public List<String> getIdiomas() {
        return idiomas;
    }
    
    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }
}
