package interfazGrafica;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author DGC
 */
public class FiltroExcel extends FileFilter {

    @Override
    public boolean accept(File pathname) {
        String filename = pathname.getName();
        if (pathname.isDirectory()) {
            return true;

        } else 
            if (filename.endsWith("xls") || filename.endsWith("xlsx")) {
                return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Excel Files";
    }
}
