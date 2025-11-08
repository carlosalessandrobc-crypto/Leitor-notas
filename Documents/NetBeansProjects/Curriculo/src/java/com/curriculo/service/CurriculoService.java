package com.curriculo.service;

import com.curriculo.dao.CurriculoDAO;
import com.curriculo.model.Curriculo;
import java.sql.SQLException;
import java.util.List;

public class CurriculoService {
    
    private CurriculoDAO curriculoDAO;
    
    public CurriculoService() {
        this.curriculoDAO = new CurriculoDAO();
    }
    
    public Long salvarCurriculo(Curriculo curriculo) throws SQLException {
        return curriculoDAO.salvarCurriculo(curriculo);
    }
    
    public Curriculo buscarCurriculo(Long id) throws SQLException {
        return curriculoDAO.buscarCurriculo(id);
    }
    
    public List<Curriculo> listarCurriculos() throws SQLException {
        return curriculoDAO.listarCurriculos();
    }
    
    public void deletarCurriculo(Long id) throws SQLException {
        curriculoDAO.deletarCurriculo(id);
    }
}
