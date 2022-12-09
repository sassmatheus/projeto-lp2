package br.com.avaliacao_lp2.ctr;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author sassmatheus
 */
public class BackupCTR {

    private static final Logger log = Logger.getLogger(BackupCTR.class.getName());

    public static void executeCommand(final String command) throws IOException {
        final ArrayList<String> commands = new ArrayList<>();
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            commands.add("/bin/bash");
            commands.add("-c");
        } else {
            commands.add("powershell.exe");
        }
        commands.add(command);
        BufferedReader br = null;
        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Retorno do comando = [" + line + "]");
            }
        } catch (IOException ioe) {
            log.severe("Erro ao executar comando " + ioe.getMessage());
            throw ioe;
        } finally {
            secureClose(br);
        }
    }

    private static void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe("Erro: " + ex.getMessage());
        }
    }

    public void confirmaBackup() throws IOException {
        JDialog.setDefaultLookAndFeelDecorated(false);
        int response = JOptionPane.showConfirmDialog(null, "Fazer backup do sistema?", "Confirmar",
            JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.getDefaultLocale();
        } else if (response == JOptionPane.YES_OPTION) {
            System.out.println(System.getProperty("os.name"));
            if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
                try{
                    BackupCTR.executeCommand("Compress-Archive -Path C:\\zzProjeto\\Projeto_Avaliacao_LP2 -DestinationPath C:\\zzBackup\\ProjetoTESTE.zip -Force");
                    JOptionPane.showMessageDialog(null, "Backup concluído com sucesso!");
                } catch(Exception e){
                    System.out.println("Erro ao fazer backup." + e);
                }  
            } 
        }
    }  

    public void confirmaRestaurarBackup() throws IOException {
        JDialog.setDefaultLookAndFeelDecorated(false);
        int response = JOptionPane.showConfirmDialog(null, "Fazer restauração do sistema?", "Confirmar",
            JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.getDefaultLocale();
        } else if (response == JOptionPane.YES_OPTION) {
            System.out.println(System.getProperty("os.name"));
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                try{
                    BackupCTR.executeCommand("Expand-Archive -Path C:\\zzBackup\\ProjetoTESTE.zip -DestinationPath C:\\zzProjeto -Force");
                JOptionPane.showMessageDialog(null, "Restauração concluída com sucesso!");
                } catch(Exception e){
                    System.out.println("Erro ao restaurar." + e);
                }
            }
        }
    }
    
}
