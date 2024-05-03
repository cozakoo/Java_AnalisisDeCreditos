package com.ministerio.analisis.credito;

import com.formdev.flatlaf.FlatIntelliJLaf;
import interfazGrafica.FormInicio;
import interfazGrafica.SesionUsuario;
import java.sql.SQLException;
import javax.swing.UIManager;

public class MinisterioComparacion {

    public static void main(String[] args) throws Exception {
        try {
            DataBase db = new DataBase();
            db.inicializar();

            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }

            if (!db.existeSesion()) {
                FormInicio f = new FormInicio(db);
                f.setVisible(true);
                db.cleanDataBase();
            } else {
                SesionUsuario sesion = new SesionUsuario(db);
                sesion.setVisible(true);
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
