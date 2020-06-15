package edu.cecar.modelo;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Clase: FiltroArchivoPlano
 *
 * @version: 0.1
 *
 * @since: Nov 24, 2019
 *
 * Fecha de Modificación:
 *
 * @author: Vincenzo Angelone
 *
 * Copyrigth: CECAR
 */
public class FiltroArchivoPlano extends FileFilter {

    public final static String TXT = "txt";

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equals(TXT)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "*.txt";
    }

    String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }


}
