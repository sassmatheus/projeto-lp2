package br.com.avaliacao_lp2.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import br.com.avaliacao_lp2.dto.AlunoDTO;
import br.com.avaliacao_lp2.dto.CursoDTO;
import br.com.avaliacao_lp2.dto.MatriculaDTO;
import br.com.avaliacao_lp2.ctr.AlunoCTR;
import br.com.avaliacao_lp2.ctr.CursoCTR;
import br.com.avaliacao_lp2.ctr.MatriculaCTR;
import java.text.MessageFormat;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JTable;

/**
 *
 * @author sassmatheus
 */        
public class MatriculaVIEW extends javax.swing.JInternalFrame {
    
    MatriculaCTR matriculaCTR = new MatriculaCTR();
    MatriculaDTO matriculaDTO = new MatriculaDTO();
    CursoCTR cursoCTR = new CursoCTR();
    CursoDTO cursoDTO = new CursoDTO();
    AlunoCTR alunoCTR = new AlunoCTR();
    AlunoDTO alunoDTO = new AlunoDTO();
    
    public MatriculaVIEW() {
        initComponents();
        liberaBotoes(false, true, false);
        liberaExcluir(false);
        liberaCurso(false);
        liberaImpressao(false);
        modelo_jtl_consultar_aluno = (DefaultTableModel) jtl_consultar_aluno.getModel();
        modelo_jtl_consultar_curso = (DefaultTableModel) jtl_consultar_curso.getModel();
        modelo_jtl_consultar_matricula = (DefaultTableModel) jtl_consultar_matricula.getModel();
    }
    
    ResultSet rs ;
    int gravar_alterar;
    
    DefaultTableModel modelo_jtl_consultar_aluno;
    DefaultTableModel modelo_jtl_consultar_curso;
    DefaultTableModel modelo_jtl_consultar_matricula;
   
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, 
                (d.height - this.getSize().height) / 2);
    }
    
    private void preencheTabelaAluno(String nome_aluno){
        try{
            modelo_jtl_consultar_aluno.setNumRows(0);
            alunoDTO.setNomeAluno(nome_aluno);
            rs = alunoCTR.pesquisarAluno(alunoDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_aluno.addRow(new Object[]{
                    rs.getString("idAluno"),
                    rs.getString("nomeAluno")
                });
            }
        } catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
    }
    
    private void preencheTabelaCurso(String nomeCurso){
        try{
            modelo_jtl_consultar_curso.setNumRows(0);
            cursoDTO.setNomeCurso(nomeCurso);
            rs = cursoCTR.pesquisarCurso(cursoDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_curso.addRow(new Object[]{
                    rs.getString("idCurso"),
                    rs.getString("nomeCurso")
                });
            }
        } catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
    }
    
    private void preencheTabelaMatricula(String nomeAluno){
        try{
            modelo_jtl_consultar_matricula.setNumRows(0);
            alunoDTO.setNomeAluno(nomeAluno);
            rs = matriculaCTR.consultarMatricula(alunoDTO, 1);
            
            while(rs.next()){
                modelo_jtl_consultar_matricula.addRow(new Object[]{
                    rs.getString("idMatricula"),
                    rs.getString("nomeAluno"),
                    rs.getString("nomeCurso"),
                });
            }
        } catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
    }
    
    private void efetuarMatricula(){
        try{            
            cursoDTO.setIdCurso(Integer.parseInt(String.valueOf(
            jtl_consultar_curso.getValueAt(jtl_consultar_curso.getSelectedRow(), 0))));
            
            alunoDTO.setIdAluno(Integer.parseInt(String.valueOf(
            jtl_consultar_aluno.getValueAt(jtl_consultar_aluno.getSelectedRow(), 0))));
            
            alunoDTO.setNomeAluno(String.valueOf(
            jtl_consultar_aluno.getValueAt(jtl_consultar_aluno.getSelectedRow(), 1)));
            
            cursoDTO.setNomeCurso(String.valueOf(
            jtl_consultar_curso.getValueAt(jtl_consultar_curso.getSelectedRow(), 1)));
        
        JOptionPane.showMessageDialog(null,
                matriculaCTR.efetuarMatricula(alunoDTO, cursoDTO, matriculaDTO)
        );
        } catch (Exception erro){
            System.out.println("ErroSQL: " + erro);
        }
    }
    
    private void excluirMatricula(){
        if(JOptionPane.showConfirmDialog(null, "Deseja EXCLUIR a matrícula?","Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,
                    matriculaCTR.excluirMatricula(matriculaDTO)
            );
        }
    }
    
    private void liberaBotoes(boolean a, boolean b, boolean c){
        btnEfetuarMatricula.setEnabled(a);
        btnSair.setEnabled(b);
        btnPesquisarCurso.setEnabled(c);
    }
    
    private void liberaCurso(boolean a){
        pesquisa_curso.setEnabled(a);
    }
    
    private void liberaImpressao(boolean a){
        btnPrint.setEnabled(a);
    }
    
    private void limpaCampos(){
        pesquisa_aluno.setText("");
        pesquisa_curso.setText("");
        pesquisa_matricula.setText("");
    }
    private void limpaTabelas(){
        modelo_jtl_consultar_aluno.setNumRows(0);
        modelo_jtl_consultar_curso.setNumRows(0);
        modelo_jtl_consultar_matricula.setNumRows(0);
    }
    
    private void liberaExcluir(boolean a){
        btnExcluirMatricula.setEnabled(a);
    }
    
    private void setDelete(int idMatricula){
        try{
            matriculaDTO.setIdMatricula(idMatricula);
            rs = matriculaCTR.consultarMatricula(alunoDTO, 2);
        } catch(Exception e){
            System.out.println("Erro ao definir deleção. Erro: " + e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pesquisa_matricula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_matricula = new javax.swing.JTable();
        btnPesquisarMatricula = new javax.swing.JButton();
        btnExcluirMatricula = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pesquisa_aluno = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_aluno = new javax.swing.JTable();
        btnPesquisarAluno = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pesquisa_curso = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_curso = new javax.swing.JTable();
        btnPesquisarCurso = new javax.swing.JButton();
        btnEfetuarMatricula = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Pesquisar matrículas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Liberation Sans", 1, 13))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nome:");

        jtl_consultar_matricula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Aluno", "Curso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_matricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_matriculaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_matricula);
        if (jtl_consultar_matricula.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_matricula.getColumnModel().getColumn(0).setMaxWidth(33);
            jtl_consultar_matricula.getColumnModel().getColumn(0).setHeaderValue("ID");
        }

        btnPesquisarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_lp2/view/images/search.png"))); // NOI18N
        btnPesquisarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarMatriculaActionPerformed(evt);
            }
        });

        btnExcluirMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_lp2/view/images/delete.png"))); // NOI18N
        btnExcluirMatricula.setText("Excluir matrícula");
        btnExcluirMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirMatriculaActionPerformed(evt);
            }
        });

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_lp2/view/images/printer.png"))); // NOI18N
        btnPrint.setText("Imprimir");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluirMatricula)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesquisa_matricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(btnPesquisarMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluirMatricula)
                        .addComponent(btnPrint)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Selecionar aluno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Liberation Sans", 1, 13))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nome:");

        jtl_consultar_aluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Aluno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_aluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_alunoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_aluno);
        if (jtl_consultar_aluno.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_aluno.getColumnModel().getColumn(0).setMaxWidth(33);
        }

        btnPesquisarAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_lp2/view/images/search.png"))); // NOI18N
        btnPesquisarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarAlunoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_aluno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(pesquisa_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPesquisarAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Matrículas");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Selecionar curso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Liberation Sans", 1, 13))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nome:");

        jtl_consultar_curso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Curso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_curso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_cursoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtl_consultar_curso);
        if (jtl_consultar_curso.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_curso.getColumnModel().getColumn(0).setMaxWidth(33);
        }

        btnPesquisarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_lp2/view/images/search.png"))); // NOI18N
        btnPesquisarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCursoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_curso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(pesquisa_curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPesquisarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEfetuarMatricula.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        btnEfetuarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_lp2/view/images/link.png"))); // NOI18N
        btnEfetuarMatricula.setText(" Efetuar Matrícula");
        btnEfetuarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfetuarMatriculaActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_lp2/view/images/exit.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 2, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(btnEfetuarMatricula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSair)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEfetuarMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarAlunoActionPerformed
        preencheTabelaAluno(pesquisa_aluno.getText());
        
    }//GEN-LAST:event_btnPesquisarAlunoActionPerformed

    private void btnEfetuarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfetuarMatriculaActionPerformed
        efetuarMatricula();
        limpaCampos();
        limpaTabelas(); 
        liberaBotoes(false, true, false);
        liberaCurso(false);
        liberaImpressao(false);
    }//GEN-LAST:event_btnEfetuarMatriculaActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarMatriculaActionPerformed
       preencheTabelaMatricula(pesquisa_matricula.getText());
       liberaImpressao(true);
    }//GEN-LAST:event_btnPesquisarMatriculaActionPerformed

    private void btnExcluirMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirMatriculaActionPerformed
        excluirMatricula();
        limpaCampos();
        limpaTabelas();
        liberaExcluir(false);
        liberaImpressao(false);
    }//GEN-LAST:event_btnExcluirMatriculaActionPerformed

    private void btnPesquisarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCursoActionPerformed
        preencheTabelaCurso(pesquisa_curso.getText());
    }//GEN-LAST:event_btnPesquisarCursoActionPerformed

    private void jtl_consultar_matriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_matriculaMouseClicked
        setDelete(Integer.parseInt((String.valueOf( 
                jtl_consultar_matricula.getValueAt(jtl_consultar_matricula.getSelectedRow(), 0)))));
        liberaExcluir(true); 
    }//GEN-LAST:event_jtl_consultar_matriculaMouseClicked

    private void jtl_consultar_alunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_alunoMouseClicked
        liberaCurso(true);
        liberaBotoes(false, true, true);
    }//GEN-LAST:event_jtl_consultar_alunoMouseClicked

    private void jtl_consultar_cursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_cursoMouseClicked
        liberaBotoes(true, true, true);
    }//GEN-LAST:event_jtl_consultar_cursoMouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        MessageFormat cabecalho = new MessageFormat("Relatório - matrículas ativas");
        MessageFormat rodape = new MessageFormat("Matheus Sass / LP2S4 2022");

        try{
            setPosicao();
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.PORTRAIT);
            jtl_consultar_matricula.print(JTable.PrintMode.FIT_WIDTH, cabecalho, rodape, true, set, true);
            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
        }
        catch (java.awt.print.PrinterException erro) {
            JOptionPane.showMessageDialog(null, "ERRO!\n" + erro);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEfetuarMatricula;
    private javax.swing.JButton btnExcluirMatricula;
    private javax.swing.JButton btnPesquisarAluno;
    private javax.swing.JButton btnPesquisarCurso;
    private javax.swing.JButton btnPesquisarMatricula;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtl_consultar_aluno;
    private javax.swing.JTable jtl_consultar_curso;
    private javax.swing.JTable jtl_consultar_matricula;
    private javax.swing.JTextField pesquisa_aluno;
    private javax.swing.JTextField pesquisa_curso;
    private javax.swing.JTextField pesquisa_matricula;
    // End of variables declaration//GEN-END:variables
}
