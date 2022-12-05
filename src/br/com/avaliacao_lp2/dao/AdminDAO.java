package br.com.avaliacao_lp2.dao;

import br.com.avaliacao_lp2.dto.AdminDTO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPasswordField;

/**
 *
 * @author sassmatheus
 */
public class AdminDAO {

    public static Object criptografar(JPasswordField senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public AdminDAO(){
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
      
    public int logarAdmin(AdminDTO AdminDTO) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Select a.idAdmin " +
                             "from adm a " + 
                             "where a.login = '" + AdminDTO.getLoginAdmin()+ "'" +
                             "and a.senha = '" + AdminDTO.getSenhaAdmin()+ "'";
            
            rs = null;
            rs = stmt.executeQuery(comando);
            if(rs.next()){
                return rs.getInt("idAdmin");
            }
            else{
                return 0;
            }         
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public static byte[] hashPass(String senha) {  
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException erro) {
            System.out.println(erro.getMessage());
            return null;
        }
        try {
            return md.digest(senha.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException erro) {
            System.out.println(erro.getMessage());
            return null;
        }     
    }
       
}

//CREATE TABLE ADM(
//	idAdmin SERIAL,
//	login VARCHAR(64),
//	senha VARCHAR(64),
//	CONSTRAINT PK_ADM PRIMARY KEY (idAdmin)
//);
//INSERT INTO ADM VALUES (10, 'admin', '9B7C240AC851C0BAD37B0F93713A05FE959F75AF5D81B6F80A5F70D0E9C4326E');