package interfazGrafica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Thr extends Thread{

 
    public Thr(String str){
        super(str);
    }
    @Override
    public void run() {
       imagen l = new imagen();
       l.setContentPane(new JLabel(new ImageIcon("C:\\Users\\dgc06\\Documents\\NetBeansProjects\\validacionJava\\src\\main\\java\\images\\log.png")));
       l.setVisible(true);
    }
    
}
