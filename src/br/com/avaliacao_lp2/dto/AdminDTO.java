package br.com.avaliacao_lp2.dto;

/**
 *
 * @author sassmatheus
 */
public class AdminDTO {
    
    private String loginAdmin, senhaAdmin;
    private int idAdmin;
    
    public AdminDTO(){
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public String getSenhaAdmin() {
        return senhaAdmin;
    }

    public void setSenhaAdmin(String senhaAdmin) {
        this.senhaAdmin = senhaAdmin;
    }
     
}
