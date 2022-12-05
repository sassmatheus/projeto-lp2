package br.com.avaliacao_lp2.ctr;

import br.com.avaliacao_lp2.dto.MatriculaDTO;
import br.com.avaliacao_lp2.dao.MatriculaDAO;
import br.com.avaliacao_lp2.dto.AlunoDTO;
import br.com.avaliacao_lp2.dao.ConexaoDAO;
import br.com.avaliacao_lp2.dto.CursoDTO;
import java.sql.ResultSet;

/**
 *
 * @author sassmatheus
 */
public class MatriculaCTR{
    public MatriculaCTR(){
    }
    
    MatriculaDAO matriculaDAO = new MatriculaDAO();
    
    public String efetuarMatricula(AlunoDTO alunoDTO, CursoDTO cursoDTO, MatriculaDTO matriculaDTO) {
        try {
            if (matriculaDAO.efetuarMatricula(alunoDTO, cursoDTO, matriculaDTO)) {
                return "Matrícula efetuada com sucesso.";
            } else {
                return "Matrícula NÃO efetuada.";
            }
        }
        catch (Exception erro) {
            System.out.println(erro.getMessage());
            return "Erro ao cadastrar.";
        }
    }

    public ResultSet consultarMatricula(AlunoDTO alunoDTO, int opcao) {
        
        ResultSet rs = null;
        rs = matriculaDAO.consultarMatricula(alunoDTO, opcao);
        return rs;
    }
    
    public String excluirMatricula(MatriculaDTO matriculaDTO){
        try {
            if (matriculaDAO.excluirMatricula(matriculaDTO)){
                return "Matricula excluída!";
            } else {
                return "Matricula NÃO excluída!";
            }
        } catch (Exception erro){
            System.out.println(erro.getMessage());
            return "Matrícula NÃO excluída!";
        }
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}
