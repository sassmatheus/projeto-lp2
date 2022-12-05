package br.com.avaliacao_lp2.dao;

import java.sql.*;
import br.com.avaliacao_lp2.dto.CursoDTO;
/**
 *
 * @author sassmatheus
 */
public class CursoDAO {
    public CursoDAO(){
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
    
    public boolean cadastrarCurso(CursoDTO cursoDTO){
        try{
            ConexaoDAO.ConnectDB();

            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into curso (nomeCurso, sigla, descricao) values ("
                    + "'" + cursoDTO.getNomeCurso() + "' ,"
                    + "'" + cursoDTO.getSigla() + "' ,"
                    + "'" + cursoDTO.getDescricao()+ "')";

            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            
            return true;
        } catch(Exception erro){
            System.out.println("Problema ao cadastrar no Banco de Dados." +
                    erro.getMessage());
            return false;
        } finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarCurso(CursoDTO cursoDTO){
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update curso set "
                    + "nomeCurso = '" + cursoDTO.getNomeCurso() + "' ,"
                    + "sigla = '" + cursoDTO.getSigla() + "' ,"
                    + "descricao = '" + cursoDTO.getDescricao() + "' "
                    + "where idCurso = " + cursoDTO.getIdCurso();

            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            
            return true;
        } catch (Exception erro) {
            System.out.println("Problema ao alterar no Banco de Dados." + 
                    erro.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirCurso(CursoDTO cursoDTO) {
        try{
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from curso where idCurso = " +
                    cursoDTO.getIdCurso();

            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            
            return true;
        } catch (Exception erro) {
            System.out.println("Problema ao excluir no Banco de Dados." +
                    erro.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet pesquisarCurso(CursoDTO cursoDTO, int opcao) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "Select c.* "
                            + "from curso c "
                            + "where nomeCurso like '" + cursoDTO.getNomeCurso() + "%' "
                            + "order by c.nomeCurso";
                    break;
                case 2:
                    comando = "Select c.* "
                            + "from curso c "
                            + "where c.idCurso = " + cursoDTO.getIdCurso();
                    break;
                case 3:
                    comando = "Select c.idCurso, c.sigla, c.descricao "
                            + "from curso c ";
                    break;

            }
            //System.out.println(comando);
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch (Exception erro) {
            System.out.println("Erro ao pesquisar no Banco de Dados (CursoDAO)" + 
                    erro.getMessage());
            return rs;
        }
    }   
}

//CREATE TABLE CURSO(
//	idCurso SERIAL,
//	nomeCurso VARCHAR(50),
//	sigla VARCHAR(10) UNIQUE,
//	descricao VARCHAR(300),
//	CONSTRAINT PK_CURSO PRIMARY KEY (idCurso)
//);


