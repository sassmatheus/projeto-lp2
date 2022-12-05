package br.com.avaliacao_lp2.ctr;

import br.com.avaliacao_lp2.dao.AdminDAO;
import br.com.avaliacao_lp2.dao.ConexaoDAO;
import br.com.avaliacao_lp2.dto.AdminDTO;

/**
 *
 * @author sassmatheus 
 */
public class AdminCTR {
    public AdminCTR() {
    }
    
    AdminDAO adminDAO = new AdminDAO();
    
    public int logarAdmin(AdminDTO adminDTO) {       
        return adminDAO.logarAdmin(adminDTO);
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}
