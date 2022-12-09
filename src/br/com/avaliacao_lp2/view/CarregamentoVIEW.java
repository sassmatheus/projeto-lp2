package br.com.avaliacao_lp2.view;

import java.awt.Color;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author sassmatheus
 */
public class CarregamentoVIEW extends JWindow{
    
    public static void main(String[] args) throws InterruptedException{
        try{
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
            }
            new CarregamentoVIEW();
            new LoginVIEW().setVisible(true);
        }
        catch(Exception e){
            System.out.println("Falha ao carregar! " + e);
        }      
    }  

    public CarregamentoVIEW() throws InterruptedException{
        //Get screen width
        int largura = this.getToolkit().getDefaultToolkit().getScreenSize().width;
        //Get screen height
        int altura = this.getToolkit().getDefaultToolkit().getScreenSize().height;
        
        int x = (largura-521) / 2;
        int y = (altura-335) / 2;
        
        JLabel labelimg = new JLabel(new ImageIcon(getClass().getResource("images/logo.gif")));    
        labelimg.setLocation(new Point(0,0));
        
        labelimg.setSize(521, 340);
        
        this.setLayout(null);
        this.add(labelimg);
        this.setLocation(new Point(x, y));
        this.setSize(521,370);
        this.setVisible(true);

        JProgressBar barraCarregamento = new JProgressBar();
        
        barraCarregamento.setBackground(new Color(200,200,200));
        barraCarregamento.setForeground(new Color(0,0,0));
        barraCarregamento.setBounds(0,340,521,30);
        
        // %%%%%
        barraCarregamento.setStringPainted(true);

        this.add(barraCarregamento);
        
        new Thread(){
            public void run(){
                for(int andamento = 0; andamento <= 100; andamento++){
                    barraCarregamento.setValue(andamento);
                    try {
                        sleep(40);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }.start();
        
        Thread.sleep(4750);   
        
        this.dispose();
    }
 
}
