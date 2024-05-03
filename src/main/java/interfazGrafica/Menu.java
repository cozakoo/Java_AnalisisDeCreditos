package interfazGrafica;

import Utiles.Mensajes;
import com.ministerio.analisis.credito.DataBase;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author dgc06
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form FormInicio
     */
    boolean lirestoCargado = false;
    boolean creditoCargado = false;
    DataBase db = null;

    public Menu(DataBase db) {
        initComponents();
        this.db = db;
        cargarImagenes();
        configurarCierre();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        city = new javax.swing.JLabel();
        partida151 = new javax.swing.JLabel();
        saldoNeg = new javax.swing.JLabel();
        sinPart = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        personalizado = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        archivosCargadosjLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DE COMPUTOS");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 160, 30));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DIRECCION GENERAL ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 200, 30));

        city.setText("jLabel1");
        jPanel1.add(city, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 0, 267, -1));

        partida151.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        partida151.setText("Partida151");
        partida151.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                partida151MouseClicked(evt);
            }
        });
        jPanel1.add(partida151, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, -1, -1));

        saldoNeg.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        saldoNeg.setText("Saldo Negativo");
        saldoNeg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saldoNegMouseClicked(evt);
            }
        });
        jPanel1.add(saldoNeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, -1, -1));

        sinPart.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        sinPart.setText(" Sin Partida ");
        sinPart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sinPartMouseClicked(evt);
            }
        });
        jPanel1.add(sinPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        jLabel8.setText("INFORMES ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 150, 30));

        personalizado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personalizadoMouseClicked(evt);
            }
        });
        jPanel1.add(personalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, -1, -1));

        jLabel11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel11.setText("Personalizado");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, -1));

        jLabel12.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel12.setText("Mas Frecuentes");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        archivosCargadosjLabel.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        archivosCargadosjLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\dgc06\\Documents\\nuevo repo\\Validacion MAIN\\validacionJava\\images\\intercambiar.png")); // NOI18N
        archivosCargadosjLabel.setText("Cambiar Archivos Cargados");
        archivosCargadosjLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archivosCargadosjLabelMouseClicked(evt);
            }
        });
        jPanel1.add(archivosCargadosjLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void personalizadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personalizadoMouseClicked
        try {
            // TODO add your handling code here:
            TablaIU table = new TablaIU(db);
            table.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_personalizadoMouseClicked

    /**
     * exportacion de saldos negativos
     *
     * @param evt
     */
    private void saldoNegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saldoNegMouseClicked

        //SALDOS NEGATIVOS
        String sql = "SELECT * FROM (SELECT c.saf, c.programa,c.subPrograma,c.proyecto, "
                + "c.actividad,c.obra,c.fuente,c.inciso,SUM(c.credito_disponible) AS Credito_disponible, "
                + "SUM(COALESCE(j.importe, 0)) AS libramiento, "
                + "(SUM(c.credito_disponible) - SUM(COALESCE(j.importe, 0))) AS saldo "
                + "FROM credito c "
                + "LEFT JOIN "
                + "(SELECT e.saf, programa,subPrograma,proyecto,actividad,obra, "
                + "fuente,inciso,principal,parcial,subParcial, importe "
                + "FROM liresto l "
                + "LEFT JOIN empresa e "
                + "ON l.clave_saf = e.clave_saf) j "
                + "ON c.saf = j.saf AND c.programa = j.programa "
                + "AND c.subPrograma = j.subPrograma AND c.proyecto = j.proyecto "
                + "AND c.actividad = j.actividad AND c.obra = j.obra "
                + "AND c.fuente = j.fuente AND c.inciso = j.inciso "
                + "AND c.principal = j.principal AND c.parcial = j.parcial "
                + "AND c.subParcial = j.subParcial "
                + "GROUP BY c.saf, c.programa,c.subPrograma,c.proyecto, "
                + "c.actividad,c.obra,c.fuente,c.inciso "
                + "HAVING "
                + "(SUM(c.credito_disponible) - SUM(COALESCE(j.importe, 0)))) l WHERE l.saldo < 0;";

        String entrada = null;
        JFileChooser ventana = new JFileChooser(entrada);
        ventana.setDialogTitle("Guardar");
        ventana.setFileFilter(new FileNameExtensionFilter("Archivos de Excel", "xls", "xlsx"));

        if (ventana.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            File archivo = ventana.getSelectedFile();

            try {
                this.db.exportarSaldoNegativo(archivo, sql);
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saldoNegMouseClicked

    /**
     * Exportacion de sin partida
     *
     * @param evt
     */
    private void sinPartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sinPartMouseClicked

        //SIN PARTIDA
        String sql = "SELECT e.saf,l.programa,l.subPrograma,l.proyecto,l.actividad,l.obra\n"
                + ",l.fuente,l.inciso,l.principal,l.parcial,l.subParcial, l.importe libramiento, c.credito_disponible\n"
                + "FROM liresto l\n"
                + "LEFT JOIN empresa e\n"
                + "on l.clave_saf = e.clave_saf \n"
                + "LEFT JOIN credito c \n"
                + "on e.saf = c.saf AND l.programa = c.programa\n"
                + "AND l.subPrograma =c.subPrograma AND l.proyecto = c.proyecto\n"
                + "   AND c.actividad = l.actividad AND c.obra = l.obra \n"
                + "   AND c.fuente = l.fuente AND c.inciso = l.inciso\n"
                + "   AND c.principal = l.principal AND c.parcial = l.parcial\n"
                + "   AND c.subParcial = l.subParcial\n"
                + "\n"
                + "WHERE c.credito_disponible IS NULL";

        String entrada = null;
        JFileChooser ventana = new JFileChooser(entrada);
        ventana.setDialogTitle("Guardar");
        ventana.setFileFilter(new FileNameExtensionFilter("Archivos de Excel", "xls", "xlsx"));

        if (ventana.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            File archivo = ventana.getSelectedFile();

            try {
                this.db.exportarSinPartida(archivo, sql);
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_sinPartMouseClicked

    private void partida151MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_partida151MouseClicked

        //CAMBIAR CONSULTA 
        //PARTIDA 151
        String sql = "SELECT * FROM (SELECT c.saf, c.programa,c.subPrograma,c.proyecto,\n"
                + "c.actividad,c.obra,c.fuente,c.inciso,c.principal,c.parcial,c.subParcial, c.credito_disponible , (coalesce(j.importe,0)) libramiento,\n"
                + "(c.credito_disponible)-(coalesce(j.importe, 0)) saldo\n"
                + "FROM credito c\n"
                + "LEFT JOIN\n"
                + "(SELECT e.saf, programa,subPrograma,proyecto,actividad,obra\n"
                + ",fuente,inciso,principal,parcial,subParcial, importe\n"
                + "FROM liresto l\n"
                + "LEFT JOIN empresa e\n"
                + "on l.clave_saf = e.clave_saf) j\n"
                + "on c.saf = j.saf AND c.programa = j.programa\n"
                + "   AND c.subPrograma = j.subPrograma AND c.proyecto = j.proyecto\n"
                + "   AND c.actividad = j.actividad AND c.obra = j.obra \n"
                + "   AND c.fuente = j.fuente AND c.inciso = j.inciso\n"
                + "   AND c.principal = j.principal AND c.parcial = j.parcial\n"
                + "   AND c.subParcial = j.subParcial\n"
                + "\n"
                + "WHERE c.saf >= 1 AND c.inciso = 1 AND c.principal = 5 AND c.parcial = 1 AND c.subParcial = 1\n"
                + "GROUP BY c.saf, c.programa,c.subPrograma,c.proyecto,\n"
                + "c.actividad,c.obra,c.fuente,c.inciso,c.principal,c.parcial,c.subParcial\n"
                + "HAVING\n"
                + "(c.credito_disponible)-(coalesce(j.importe, 0)))m WHERE m.libramiento > 0 ;";

        String entrada = null;
        JFileChooser ventana = new JFileChooser(entrada);
        ventana.setDialogTitle("Guardar");
        ventana.setFileFilter(new FileNameExtensionFilter("Archivos de Excel", "xls", "xlsx"));

        if (ventana.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            File archivo = ventana.getSelectedFile();

            try {
                this.db.exportarPartida151(archivo, sql);
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_partida151MouseClicked

    private void archivosCargadosjLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivosCargadosjLabelMouseClicked
        // TODO add your handling code here:
        if (Mensajes.confirmar("¿Desea reemplazar los archivos existentes?", "Nuevos archivos")) {
            new CambiarArchivos(db).setVisible(true);
            dispose();
            // TODO add your handling code here:
//                db.cleanDataBase();
//                new FormInicio(db).setVisible(true);
//                dispose();
        }
    }//GEN-LAST:event_archivosCargadosjLabelMouseClicked

    private void cargarImagenes() {
        String dirActual = System.getProperty("user.dir");
        // dirActual = dirActual + "\\images\\importar.png";
        System.out.println(dirActual);
        Icon excelIcon = new javax.swing.ImageIcon(dirActual + "\\images\\excel.png");
        partida151.setIcon(excelIcon);
        saldoNeg.setIcon(excelIcon);
        sinPart.setIcon(excelIcon);
        personalizado.setIcon(new javax.swing.ImageIcon(dirActual + "\\images\\mano.png"));
        city.setIcon(new javax.swing.ImageIcon(dirActual + "\\images\\city.png"));
        archivosCargadosjLabel.setIcon(new javax.swing.ImageIcon(dirActual + "\\images\\intercambiar.png"));
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel archivosCargadosjLabel;
    javax.swing.JLabel city;
    javax.swing.JLabel partida151;
    javax.swing.JLabel personalizado;
    javax.swing.JLabel saldoNeg;
    javax.swing.JLabel sinPart;
    // End of variables declaration//GEN-END:variables

    private void configurarCierre() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Deshabilita el cierre predeterminado
        JFrame form = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Mensajes.confirmar("¿Desea cerrar el programa?", "Cerrar programa")) {
                    // Cierra la ventana si el usuario elige "Sí"
                    form.dispose();
                    System.exit(0);
                }
            }
        });
    }

}
