package br.com.avaliacao_lp2.dao;

import java.sql.*;

/**
 *
 * @author sassmatheus
 */
public class ConexaoDAO {
    public static Connection con = null;
    
    public ConexaoDAO(){
    }
    
    public static void ConnectDB(){
        try{
            String dsn = "projeto_avaliacao_lp2";
            String user = "postgres";
            String senha = "0131";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            
            String url = "jdbc:postgresql://localhost:5432/" + dsn;
            
            con = DriverManager.getConnection(url, user, senha);
            
            con.setAutoCommit(false);
            if (con == null){
                System.out.println("Erro ao abrir banco de dados!");
            }        
        } catch (Exception erro){
            System.out.println("Problema ao abrir banco de dados!" +
                erro.getMessage());
        }
    }
    
    public static void CloseDB(){
        try{
            con.close();
        } catch (Exception erro){
            System.out.println("Problema ao fechar banco de dados!" +
                erro.getMessage());
        }
    }
}
