package br.com.avaliacao_lp2.ctr;

import br.com.avaliacao_lp2.dao.ConexaoDAO;
import br.com.avaliacao_lp2.dto.AlunoDTO;
import br.com.avaliacao_lp2.dao.AlunoDAO;
import java.sql.ResultSet;

/**
 *
 * @author sassmatheus
 */
public class AlunoCTR {
    public AlunoCTR(){
    }
    
    AlunoDAO alunoDAO = new AlunoDAO();
    
    public String cadastrarAluno(AlunoDTO alunoDTO) {
        try {
            if (alunoDAO.cadastrarAluno(alunoDTO)) {
                return "Aluno cadastrado.";
            } else {
                return "Aluno NÃO cadastrado.";
            }
        }
        catch (Exception erro) {
            System.out.println(erro.getMessage());
            return "Erro ao cadastrar.";
        }
    }
    
    public String alterarAluno(AlunoDTO alunoDTO) {
        try {
            if (alunoDAO.alterarAluno(alunoDTO)) {
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
    
    public String excluirAluno(AlunoDTO alunoDTO) {
        try {
            if (alunoDAO.excluirAluno(alunoDTO)) {
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
    
    public ResultSet pesquisarAluno(AlunoDTO alunoDTO, int opcao) {
        
        ResultSet rs = null;
        rs = alunoDAO.pesquisarAluno(alunoDTO, opcao);
        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}
