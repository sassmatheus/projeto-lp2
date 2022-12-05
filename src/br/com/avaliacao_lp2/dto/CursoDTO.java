package br.com.avaliacao_lp2.dto;

/**
 *
 * @author sassmatheus
 */
public class CursoDTO {
    private String nomeCurso, sigla, descricao;
    private int idCurso;
    
    public CursoDTO (int idCurso, String sigla, String descricao){
      this.idCurso=idCurso;
      this.sigla=sigla;
      this.descricao=descricao;
    }
    
    public CursoDTO(){
    }
    
    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }    

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    
    
}
