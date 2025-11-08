package com.curriculo.controller;

import com.curriculo.model.Curriculo;
import com.curriculo.model.Experiencia;
import com.curriculo.model.Educacao;
import com.curriculo.service.PdfService;
import com.curriculo.service.CurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/curriculo")
public class CurriculoController {
    
    @Autowired
    private PdfService pdfService;
    
    private CurriculoService curriculoService;
    
    public CurriculoController() {
        this.curriculoService = new CurriculoService();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("curriculo", new Curriculo());
        return "curriculo/form";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("curriculo", new Curriculo());
        return "curriculo/form";
    }
    
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public String preview(@ModelAttribute("curriculo") Curriculo curriculo, 
                         @RequestParam(value = "experiencias", required = false) String[] experienciasArray,
                         @RequestParam(value = "educacoes", required = false) String[] educacoesArray,
                         @RequestParam(value = "habilidades", required = false) String habilidadesStr,
                         @RequestParam(value = "idiomas", required = false) String idiomasStr,
                         Model model) {
        
        // Processar experiências
        if (experienciasArray != null) {
            List<Experiencia> experiencias = new ArrayList<>();
            for (int i = 0; i < experienciasArray.length; i += 4) {
                if (i + 3 < experienciasArray.length) {
                    Experiencia exp = new Experiencia();
                    exp.setCargo(experienciasArray[i]);
                    exp.setEmpresa(experienciasArray[i + 1]);
                    exp.setPeriodo(experienciasArray[i + 2]);
                    exp.setDescricao(experienciasArray[i + 3]);
                    experiencias.add(exp);
                }
            }
            curriculo.setExperiencias(experiencias);
        }
        
        // Processar educações
        if (educacoesArray != null) {
            List<Educacao> educacoes = new ArrayList<>();
            for (int i = 0; i < educacoesArray.length; i += 4) {
                if (i + 3 < educacoesArray.length) {
                    Educacao edu = new Educacao();
                    edu.setCurso(educacoesArray[i]);
                    edu.setInstituicao(educacoesArray[i + 1]);
                    edu.setPeriodo(educacoesArray[i + 2]);
                    edu.setStatus(educacoesArray[i + 3]);
                    educacoes.add(edu);
                }
            }
            curriculo.setEducacoes(educacoes);
        }
        
        // Processar habilidades
        if (habilidadesStr != null && !habilidadesStr.trim().isEmpty()) {
            List<String> habilidades = Arrays.asList(habilidadesStr.split(","));
            curriculo.setHabilidades(habilidades);
        }
        
        // Processar idiomas
        if (idiomasStr != null && !idiomasStr.trim().isEmpty()) {
            List<String> idiomas = Arrays.asList(idiomasStr.split(","));
            curriculo.setIdiomas(idiomas);
        }
        
        model.addAttribute("curriculo", curriculo);
        return "curriculo/preview";
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadPdf(@ModelAttribute("curriculo") Curriculo curriculo,
                                            @RequestParam(value = "experiencias", required = false) String[] experienciasArray,
                                            @RequestParam(value = "educacoes", required = false) String[] educacoesArray,
                                            @RequestParam(value = "habilidades", required = false) String habilidadesStr,
                                            @RequestParam(value = "idiomas", required = false) String idiomasStr) {
        
        try {
            // Processar dados da mesma forma que no preview
            if (experienciasArray != null) {
                List<Experiencia> experiencias = new ArrayList<>();
                for (int i = 0; i < experienciasArray.length; i += 4) {
                    if (i + 3 < experienciasArray.length) {
                        Experiencia exp = new Experiencia();
                        exp.setCargo(experienciasArray[i]);
                        exp.setEmpresa(experienciasArray[i + 1]);
                        exp.setPeriodo(experienciasArray[i + 2]);
                        exp.setDescricao(experienciasArray[i + 3]);
                        experiencias.add(exp);
                    }
                }
                curriculo.setExperiencias(experiencias);
            }
            
            if (educacoesArray != null) {
                List<Educacao> educacoes = new ArrayList<>();
                for (int i = 0; i < educacoesArray.length; i += 4) {
                    if (i + 3 < educacoesArray.length) {
                        Educacao edu = new Educacao();
                        edu.setCurso(educacoesArray[i]);
                        edu.setInstituicao(educacoesArray[i + 1]);
                        edu.setPeriodo(educacoesArray[i + 2]);
                        edu.setStatus(educacoesArray[i + 3]);
                        educacoes.add(edu);
                    }
                }
                curriculo.setEducacoes(educacoes);
            }
            
            if (habilidadesStr != null && !habilidadesStr.trim().isEmpty()) {
                List<String> habilidades = Arrays.asList(habilidadesStr.split(","));
                curriculo.setHabilidades(habilidades);
            }
            
            if (idiomasStr != null && !idiomasStr.trim().isEmpty()) {
                List<String> idiomas = Arrays.asList(idiomasStr.split(","));
                curriculo.setIdiomas(idiomas);
            }
            
            // Gerar PDF
            byte[] pdfBytes = pdfService.gerarPdfCurriculo(curriculo);
            
            // Configurar headers para download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "curriculo_" + 
                curriculo.getNomeCompleto().replaceAll("\\s+", "_") + ".pdf");
            headers.setContentLength(pdfBytes.length);
            
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Salva currículo no SQLite e gera PDF
     */
    @RequestMapping(value = "/save-and-download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> saveAndDownloadPdf(@ModelAttribute("curriculo") Curriculo curriculo,
                                                   @RequestParam(value = "experiencias", required = false) String[] experienciasArray,
                                                   @RequestParam(value = "educacoes", required = false) String[] educacoesArray,
                                                   @RequestParam(value = "habilidades", required = false) String habilidadesStr,
                                                   @RequestParam(value = "idiomas", required = false) String idiomasStr) {
        
        try {
            // Processar dados da mesma forma que no download normal
            processarDadosFormulario(curriculo, experienciasArray, educacoesArray, habilidadesStr, idiomasStr);
            
            // Salvar no banco e gerar PDF
            byte[] pdfBytes = pdfService.salvarEGerarPdf(curriculo);
            
            // Configurar headers para download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "curriculo_" + 
                curriculo.getNomeCompleto().replaceAll("\\s+", "_") + ".pdf");
            headers.setContentLength(pdfBytes.length);
            
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Lista currículos salvos
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCurriculos(Model model) {
        try {
            List<Curriculo> curriculos = curriculoService.listarCurriculos();
            model.addAttribute("curriculos", curriculos);
            return "curriculo/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao carregar currículos: " + e.getMessage());
            return "curriculo/list";
        }
    }
    
    /**
     * Gera PDF de um currículo salvo pelo ID
     */
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadSavedPdf(@PathVariable Long id) {
        try {
            Curriculo curriculo = curriculoService.buscarCurriculo(id);
            if (curriculo == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            byte[] pdfBytes = pdfService.gerarPdfCurriculoPorId(id);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "curriculo_" + 
                curriculo.getNomeCompleto().replaceAll("\\s+", "_") + ".pdf");
            headers.setContentLength(pdfBytes.length);
            
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Deleta um currículo salvo
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCurriculo(@PathVariable Long id) {
        try {
            curriculoService.deletarCurriculo(id);
            return new ResponseEntity<>("Currículo deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao deletar currículo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Método auxiliar para processar dados do formulário
     */
    private void processarDadosFormulario(Curriculo curriculo, String[] experienciasArray, 
                                        String[] educacoesArray, String habilidadesStr, String idiomasStr) {
        // Processar experiências
        if (experienciasArray != null) {
            List<Experiencia> experiencias = new ArrayList<>();
            for (int i = 0; i < experienciasArray.length; i += 4) {
                if (i + 3 < experienciasArray.length) {
                    Experiencia exp = new Experiencia();
                    exp.setCargo(experienciasArray[i]);
                    exp.setEmpresa(experienciasArray[i + 1]);
                    exp.setPeriodo(experienciasArray[i + 2]);
                    exp.setDescricao(experienciasArray[i + 3]);
                    experiencias.add(exp);
                }
            }
            curriculo.setExperiencias(experiencias);
        }
        
        // Processar educações
        if (educacoesArray != null) {
            List<Educacao> educacoes = new ArrayList<>();
            for (int i = 0; i < educacoesArray.length; i += 4) {
                if (i + 3 < educacoesArray.length) {
                    Educacao edu = new Educacao();
                    edu.setCurso(educacoesArray[i]);
                    edu.setInstituicao(educacoesArray[i + 1]);
                    edu.setPeriodo(educacoesArray[i + 2]);
                    edu.setStatus(educacoesArray[i + 3]);
                    educacoes.add(edu);
                }
            }
            curriculo.setEducacoes(educacoes);
        }
        
        // Processar habilidades
        if (habilidadesStr != null && !habilidadesStr.trim().isEmpty()) {
            List<String> habilidades = Arrays.asList(habilidadesStr.split(","));
            curriculo.setHabilidades(habilidades);
        }
        
        // Processar idiomas
        if (idiomasStr != null && !idiomasStr.trim().isEmpty()) {
            List<String> idiomas = Arrays.asList(idiomasStr.split(","));
            curriculo.setIdiomas(idiomas);
        }
    }
}
