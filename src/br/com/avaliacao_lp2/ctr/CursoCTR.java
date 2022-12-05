package br.com.avaliacao_lp2.ctr;

import br.com.avaliacao_lp2.dao.ConexaoDAO;
import br.com.avaliacao_lp2.dao.CursoDAO;
import br.com.avaliacao_lp2.dto.CursoDTO;
import java.sql.ResultSet;

/**
 *
 * @author sassmatheus
 */
public class CursoCTR {
    public CursoCTR(){
    }
    
    CursoDAO cursoDAO = new CursoDAO();
    
    public String cadastrarCurso(CursoDTO cursoDTO) {
        try {
            if (cursoDAO.cadastrarCurso(cursoDTO)) {
                return "Curso cadastrado.";
            } else {
                return "Curso NÃO cadastrado.";
            }
        }
        catch (Exception erro) {
            System.out.println(erro.getMessage());
            return "Erro ao cadastrar.";
        }
    }
    
    public String alterarCurso(CursoDTO cursoDTO) {
        try {
            if (cursoDAO.alterarCurso(cursoDTO)) {
                return "Cadastro alterado.";
            } else {
                return "Cadastro NÃO alterado.";
            }
        }
        catch (Exception erro) {
            System.out.println(erro.getMessage());
            return "Erro ao alterar.";
        }
    }
    
    public String excluirCurso(CursoDTO cursoDTO) {
        try {
            if (cursoDAO.excluirCurso(cursoDTO)) {
                return "Cadastro excluído.";
            } else {
                return "Cadastro NÃO excluído.";
            }
        }
        catch (Exception erro) {
            System.out.println(erro.getMessage());
            return "Erro ao excluir.";
        }
    }
    
    public ResultSet pesquisarCurso(CursoDTO cursoDTO, int opcao) {
        
        ResultSet rs = null;
        rs = cursoDAO.pesquisarCurso(cursoDTO, opcao);
        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}

