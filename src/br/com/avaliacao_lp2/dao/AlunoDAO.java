package br.com.avaliacao_lp2.dao;

import java.sql.*;
import br.com.avaliacao_lp2.dto.AlunoDTO;

/**
 *
 * @author sassmatheus
 */
public class AlunoDAO {
    public AlunoDAO(){
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
        
    public boolean cadastrarAluno(AlunoDTO alunoDTO){
        try{
            ConexaoDAO.ConnectDB();

            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into aluno (nomeAluno, cpf, email, telefone, estado, cidade) values ("
                    + "'" + alunoDTO.getNomeAluno() + "' ,"
                    + "'" + alunoDTO.getCpf() + "' ,"
                    + "'" + alunoDTO.getEmail() + "' ,"
                    + "'" + alunoDTO.getTelefone() + "' ,"
                    + "'" + alunoDTO.getEstado() + "' ,"
                    + "'" + alunoDTO.getCidade() + "')";

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
    
    public boolean alterarAluno(AlunoDTO alunoDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update aluno set "
                    + "nomeAluno = '" + alunoDTO.getNomeAluno() + "' ,"
                    + "cpf = '" + alunoDTO.getCpf() + "' ,"
                    + "email = '" + alunoDTO.getEmail() + "' ,"
                    + "telefone = '" + alunoDTO.getTelefone() + "' ,"
                    + "estado = '" + alunoDTO.getEstado() + "' ,"
                    + "cidade = '" + alunoDTO.getCidade() + "' "
                    + "where idAluno = " + alunoDTO.getIdAluno();

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
    
    public boolean excluirAluno(AlunoDTO alunoDTO) {
        try{
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from aluno where idAluno = " +
                    alunoDTO.getIdAluno();

            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            
            return true;
        } catch (Exception erro) {
            System.out.println("Problema ao excluir no Banco de Dados." +
                    erro.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet pesquisarAluno(AlunoDTO alunoDTO, int opcao) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "Select a.*"
                            + "from aluno a "
                            + "where nomeAluno like '" + alunoDTO.getNomeAluno() + "%' "
                            + "order by a.nomeAluno";
                    break;
                case 2:
                    comando = "Select a.* "
                            + "from aluno a "
                            + "where a.idAluno = " + alunoDTO.getIdAluno();
                    break;
                case 3:
                    comando = "Select a.idAluno, a.nomeAluno "
                            + "from aluno a ";

            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch (Exception erro) {
            System.out.println("Erro ao pesquisar no Banco de Dados (AlunoDAO)" + 
                    erro.getMessage());
            return rs;
        }
    }   
}

//CREATE TABLE ALUNO(
//	idAluno SERIAL,
//	nomeAluno VARCHAR(50),
//	cpf VARCHAR(14) UNIQUE,
//	email VARCHAR(50),
//	telefone VARCHAR(30),
//	estado VARCHAR(10),
//	cidade VARCHAR(30),
//	CONSTRAINT PK_ALUNO PRIMARY KEY (idAluno)
//);
