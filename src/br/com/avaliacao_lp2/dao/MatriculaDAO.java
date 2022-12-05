package br.com.avaliacao_lp2.dao;

import br.com.avaliacao_lp2.dto.AlunoDTO;
import br.com.avaliacao_lp2.dto.MatriculaDTO;
import br.com.avaliacao_lp2.dto.CursoDTO;
import java.sql.*;

/**
 *
 * @author sassmatheus
 */
public class MatriculaDAO {
    
    public MatriculaDAO() {
    }

    private ResultSet rs = null;

    Statement stmt = null;
    
    public boolean efetuarMatricula(AlunoDTO alunoDTO, CursoDTO cursoDTO, MatriculaDTO matriculaDTO) {
        try {
            ConexaoDAO.ConnectDB();

            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into matricula (idCurso, idAluno, nomeAluno, nomeCurso) values ("
                    + cursoDTO.getIdCurso() + ", "
                    + alunoDTO.getIdAluno() + ", "
                    + "'" + alunoDTO.getNomeAluno() + "', "
                    + "'" + cursoDTO.getNomeCurso() + "')";
            
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
           return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarMatricula(AlunoDTO alunoDTO, int opcao) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Select m.*"
                            + "from matricula m "
                            + "where nomeAluno like '" + alunoDTO.getNomeAluno() + "%' "
                            + "order by m.nomeAluno";
                   
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch (Exception erro) {
            System.out.println("Erro ao pesquisar no Banco de Dados MatriculaDAO " + 
                    erro.getMessage());
            return rs;
        }
    } 
    
    public boolean excluirMatricula(MatriculaDTO matriculaDTO){
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from matricula where idMatricula = " +
                    matriculaDTO.getIdMatricula();
            
            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            
            return true;
        } catch (Exception erro){
            System.out.println("Problema ao excluir no Banco de Dados." +
                    erro.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }  
}

//CREATE TABLE MATRICULA(
//	idMatricula SERIAL,
//	idCurso INTEGER,
//	idAluno INTEGER,
//	nomeAluno VARCHAR(50),
//	nomeCurso VARCHAR(50),
//	FOREIGN KEY (idAluno) REFERENCES ALUNO(idAluno),
//	FOREIGN KEY (idCurso) REFERENCES CURSO(idCurso),
//	CONSTRAINT CHAVE UNIQUE(idCurso, idAluno)
//);