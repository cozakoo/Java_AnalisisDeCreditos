package interfazGrafica;

import java.io.File;

/**
 *
 * @author dgc06
 */
public class Filter extends javax.swing.filechooser.FileFilter{
    /*    publicFilter() {
    }*/
    private String extension;
    /*    @Override*/
    public Filter(String extension){
        this.extension = extension;
    }
    public boolean accept(File f) {
        return (f.isDirectory() || f.getAbsolutePath().endsWith(extension)); 
    }

    @Override
    public String getDescription() {
        return "Text documents (*.txt)";
    }
    
}
