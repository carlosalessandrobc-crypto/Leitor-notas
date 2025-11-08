package com.curriculo.service;

import com.curriculo.model.Curriculo;
import com.curriculo.model.Experiencia;
import com.curriculo.model.Educacao;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

@Service
public class PdfService {
    
    private CurriculoService curriculoService;
    
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
    private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
    
    public PdfService() {
        this.curriculoService = new CurriculoService();
    }
    
    public byte[] gerarPdfCurriculo(Curriculo curriculo) throws DocumentException, IOException {
        
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            
            // Cabeçalho com nome
            Paragraph nome = new Paragraph(curriculo.getNomeCompleto(), TITLE_FONT);
            nome.setAlignment(Element.ALIGN_CENTER);
            nome.setSpacingAfter(10f);
            document.add(nome);
            
            // Informações de contato
            adicionarInformacoesContato(document, curriculo);
            
            // Objetivo
            if (curriculo.getObjetivo() != null && !curriculo.getObjetivo().trim().isEmpty()) {
                adicionarSecao(document, "OBJETIVO", curriculo.getObjetivo());
            }
            
            // Resumo Profissional
            if (curriculo.getResumoProfissional() != null && !curriculo.getResumoProfissional().trim().isEmpty()) {
                adicionarSecao(document, "RESUMO PROFISSIONAL", curriculo.getResumoProfissional());
            }
            
            // Experiências Profissionais
            if (!curriculo.getExperiencias().isEmpty()) {
                adicionarExperiencias(document, curriculo);
            }
            
            // Educação
            if (!curriculo.getEducacoes().isEmpty()) {
                adicionarEducacao(document, curriculo);
            }
            
            // Habilidades
            if (!curriculo.getHabilidades().isEmpty()) {
                adicionarHabilidades(document, curriculo);
            }
            
            // Idiomas
            if (!curriculo.getIdiomas().isEmpty()) {
                adicionarIdiomas(document, curriculo);
            }
            
        } finally {
            document.close();
        }
        
        return baos.toByteArray();
    }
    
    /**
     * Gera PDF do currículo buscando dados do SQLite pelo ID
     */
    public byte[] gerarPdfCurriculoPorId(Long curriculoId) throws DocumentException, IOException, SQLException {
        Curriculo curriculo = curriculoService.buscarCurriculo(curriculoId);
        if (curriculo == null) {
            throw new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId);
        }
        return gerarPdfCurriculo(curriculo);
    }
    
    /**
     * Salva currículo no SQLite e gera PDF
     */
    public byte[] salvarEGerarPdf(Curriculo curriculo) throws DocumentException, IOException, SQLException {
        Long curriculoId = curriculoService.salvarCurriculo(curriculo);
        curriculo.setId(curriculoId);
        return gerarPdfCurriculo(curriculo);
    }
    
    private void adicionarInformacoesContato(Document document, Curriculo curriculo) throws DocumentException {
        StringBuilder contato = new StringBuilder();
        
        if (curriculo.getEmail() != null) contato.append("Email: ").append(curriculo.getEmail()).append(" | ");
        if (curriculo.getTelefone() != null) contato.append("Telefone: ").append(curriculo.getTelefone()).append(" | ");
        if (curriculo.getEndereco() != null) contato.append("Endereço: ").append(curriculo.getEndereco()).append(" | ");
        if (curriculo.getLinkedin() != null) contato.append("LinkedIn: ").append(curriculo.getLinkedin());
        
        String contatoStr = contato.toString();
        if (contatoStr.endsWith(" | ")) {
            contatoStr = contatoStr.substring(0, contatoStr.length() - 3);
        }
        
        Paragraph contatoParagraph = new Paragraph(contatoStr, NORMAL_FONT);
        contatoParagraph.setAlignment(Element.ALIGN_CENTER);
        contatoParagraph.setSpacingAfter(15f);
        document.add(contatoParagraph);
        
        // Linha separadora
        document.add(new Paragraph("_".repeat(80), NORMAL_FONT));
        document.add(Chunk.NEWLINE);
    }
    
    private void adicionarSecao(Document document, String titulo, String conteudo) throws DocumentException {
        Paragraph tituloP = new Paragraph(titulo, HEADER_FONT);
        tituloP.setSpacingBefore(10f);
        tituloP.setSpacingAfter(5f);
        document.add(tituloP);
        
        Paragraph conteudoP = new Paragraph(conteudo, NORMAL_FONT);
        conteudoP.setSpacingAfter(10f);
        document.add(conteudoP);
    }
    
    private void adicionarExperiencias(Document document, Curriculo curriculo) throws DocumentException {
        Paragraph titulo = new Paragraph("EXPERIÊNCIA PROFISSIONAL", HEADER_FONT);
        titulo.setSpacingBefore(10f);
        titulo.setSpacingAfter(5f);
        document.add(titulo);
        
        for (Experiencia exp : curriculo.getExperiencias()) {
            Paragraph cargo = new Paragraph(exp.getCargo() + " - " + exp.getEmpresa(), BOLD_FONT);
            cargo.setSpacingAfter(2f);
            document.add(cargo);
            
            Paragraph periodo = new Paragraph(exp.getPeriodo(), NORMAL_FONT);
            periodo.setSpacingAfter(3f);
            document.add(periodo);
            
            if (exp.getDescricao() != null && !exp.getDescricao().trim().isEmpty()) {
                Paragraph descricao = new Paragraph(exp.getDescricao(), NORMAL_FONT);
                descricao.setSpacingAfter(8f);
                document.add(descricao);
            }
        }
    }
    
    private void adicionarEducacao(Document document, Curriculo curriculo) throws DocumentException {
        Paragraph titulo = new Paragraph("EDUCAÇÃO", HEADER_FONT);
        titulo.setSpacingBefore(10f);
        titulo.setSpacingAfter(5f);
        document.add(titulo);
        
        for (Educacao edu : curriculo.getEducacoes()) {
            Paragraph curso = new Paragraph(edu.getCurso() + " - " + edu.getInstituicao(), BOLD_FONT);
            curso.setSpacingAfter(2f);
            document.add(curso);
            
            String info = edu.getPeriodo();
            if (edu.getStatus() != null && !edu.getStatus().trim().isEmpty()) {
                info += " - " + edu.getStatus();
            }
            
            Paragraph detalhes = new Paragraph(info, NORMAL_FONT);
            detalhes.setSpacingAfter(8f);
            document.add(detalhes);
        }
    }
    
    private void adicionarHabilidades(Document document, Curriculo curriculo) throws DocumentException {
        Paragraph titulo = new Paragraph("HABILIDADES", HEADER_FONT);
        titulo.setSpacingBefore(10f);
        titulo.setSpacingAfter(5f);
        document.add(titulo);
        
        String habilidades = String.join(" • ", curriculo.getHabilidades());
        Paragraph habilidadesP = new Paragraph(habilidades, NORMAL_FONT);
        habilidadesP.setSpacingAfter(10f);
        document.add(habilidadesP);
    }
    
    private void adicionarIdiomas(Document document, Curriculo curriculo) throws DocumentException {
        Paragraph titulo = new Paragraph("IDIOMAS", HEADER_FONT);
        titulo.setSpacingBefore(10f);
        titulo.setSpacingAfter(5f);
        document.add(titulo);
        
        String idiomas = String.join(" • ", curriculo.getIdiomas());
        Paragraph idiomasP = new Paragraph(idiomas, NORMAL_FONT);
        idiomasP.setSpacingAfter(10f);
        document.add(idiomasP);
    }
}
