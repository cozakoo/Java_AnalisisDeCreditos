package com.ministerio.analisis.credito;

import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DataBase {

    //Constantes
    private static Connection conection = null;
    private static Statement stmt = null;
    private static PreparedStatement pstmt = null;
    String sql = null;
    boolean estaEnUso = false;

    public void inicializar() throws SQLException, IOException, CsvException, ClassNotFoundException {

        Connection con = null;
        // String url = "jdbc:sqlite:C:\\Users\\Lucero\\Documents\\NetBeansProjects\\validacionJava\\base.db";
        conection = DriverManager.getConnection("jdbc:sqlite:./db/data.sqlite");

        stmt = conection.createStatement();
        this.crearTablas();

        cargarEmpresa();
    }

    public void cargarEmpresa() {
        try {
            sql = "delete from `empresa`;";
            stmt.execute(sql);

            String dirActual = System.getProperty("user.dir");
            dirActual = dirActual + "\\resource\\empresas.csv";
            CsvEmpresa csv = new CsvEmpresa(dirActual);
            importarEmpresa(csv);
        } catch (IOException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean existeSesion() throws SQLException {
        return (CantFilasCargadas("liresto")) > 0 && (CantFilasCargadas("credito") > 0);
    }

    public int CantFilasCargadas(String tabla) throws SQLException {
        //return cantidad filas de una tabla
        sql = "SELECT COUNT(*) AS total FROM " + tabla + ";";
        ResultSet result = stmt.executeQuery(sql);
        Object o = result.getObject(1);
        return (int) o;
    }

    public void cleanCredito() throws SQLException {
        sql = "delete from `credito`;";
        stmt.execute(sql);

    }
    
     public void cleanLiresto() throws SQLException {
        sql = "delete from `liresto`;";
        stmt.execute(sql);

    }

    public void cleanFechaCreacion(String tipo) throws SQLException {
        //tipo = 'EXCEL' || tipo = 'LIRESTO" -> EN MAYUSCULAS

        sql = "delete from fecha_creacion where tipo = '"+ tipo + "'";
        stmt.execute(sql);
        
    }

    public void cleanDataBase() throws SQLException {
        //borramos los datos existentes en las tablas  
        //borramos los datos existentes en las tablas  

        sql = "delete from `sqlite_sequence`";
        stmt.execute(sql);
        sql = "delete from `liresto`;";
        stmt.execute(sql);
        sql = "delete from `credito`;";
        stmt.execute(sql);
        sql = "delete from `fecha_creacion`;";
        stmt.execute(sql);

    }

    public ResultSet consulta(String query) {
        try {
            this.sql = query;
            return stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("filtrar vacio");
        }
        return null;
    }

    public boolean estaEnUso() {
        return estaEnUso;
    }

    public void setEstaEnUso(boolean resp) {
        this.estaEnUso = resp;
    }

    public void crearTablas() throws SQLException {

        sql = "CREATE TABLE IF NOT EXISTS \"liresto\" ("
                + "\t\"id_liresto\"\tINTEGER,"
                + "\t\"clave_saf\"\tTEXT,"
                + "\t\"programa\"\tNUMERIC,"
                + "\t\"subPrograma\"\tNUMERIC,"
                + "\t\"proyecto\"\tNUMERIC,"
                + "\t\"actividad\"\tNUMERIC,"
                + "\t\"obra\"\tNUMERIC,"
                + "\t\"fuente\"\tNUMERIC,"
                + "\t\"inciso\"\tNUMERIC,"
                + "\t\"principal\"\tNUMERIC,"
                + "\t\"parcial\"\tNUMERIC,"
                + "\t\"subParcial\"\tNUMERIC,"
                + "\t\"benef\"\tNUMERIC,"
                + "\t\"importe\"\tREAL,"
                + "\t\"fecha1\"\tNUMERIC,"
                + "\t\"fecha2\"\tNUMERIC,"
                + "\t\"extra\"\tTEXT,"
                + "\tPRIMARY KEY(\"id_liresto\"\tAUTOINCREMENT)"
                + ");";

        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS \"credito\" ("
                + "\t\"id_credito\"\tINTEGER,"
                + "\t\"saf\"\tNUMERIC,"
                + "\t\"nombre_saf\"\tTEXT,"
                + "\t\"programa\"\tNUMERIC,"
                + "\t\"subPrograma\"\tNUMERIC,"
                + "\t\"proyecto\"\tNUMERIC,"
                + "\t\"actividad\"\tNUMERIC,"
                + "\t\"obra\"\tNUMERIC,"
                + "\t\"descripcion_programa\"\tTEXT,"
                + "\t\"descripcion_objeto_gasto\"\tTEXT,"
                + "\t\"inciso\"\tNUMERIC,"
                + "\t\"principal\"\tNUMERIC,"
                + "\t\"parcial\"\tNUMERIC,"
                + "\t\"subParcial\"\tNUMERIC,"
                + "\t\"fuente\"\tNUMERIC,"
                + "\t\"descripcion_fuente\"\tTEXT,"
                + "\t\"finalidad_y_funcion\"\tNUMERIC,"
                + "\t\"credito_original\"\tNUMERIC,"
                + "\t\"vigente\"\tNUMERIC,"
                + "\t\"preventivo\"\tNUMERIC,"
                + "\t\"comprometido\"\tINTEGER,"
                + "\t\"ordenado\"\tNUMERIC,"
                + "\t\"pagado\"\tNUMERIC,"
                + "\t\"reserva\"\tNUMERIC,"
                + "\t\"credito_disponible\"\tREAL,"
                + "\t\"disponible_ordenado\"\tREAL,"
                + "\t\"porcentaje_ejec\"\tNUMERIC,"
                + "\t\"porcentaje_ejec_ordenado\"\tREAL,"
                + "\tPRIMARY KEY(\"id_credito\"\tAUTOINCREMENT)"
                + ");";

        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS \"empresa\" ("
                + "\t\"cod_emp\"\tNUMERIC,"
                + "\t\"saf\"\tNUMERIC,"
                + "\t\"descripcion\"\tTEXT,"
                + "\t\"clave_saf\"\tTEXT,"
                + "\t\"cod_tipo\"\tNUMERIC,"
                + "\t\"excep\"\tNUMERIC,"
                + "\t\"safcentrar\"\tNUMERIC,"
                + "\t\"cuit\"\tNUMERIC,"
                + "\t\"calle\"\tTEXT,"
                + "\t\"nro\"\tNUMERIC,"
                + "\t\"codsicore\"\tNUMERIC,"
                + "\t\"empresue\"\tTEXT,"
                + "\t\"activo\"\tNUMERIC,"
                + "\t\"caracter\"\tNUMERIC,"
                + "\t\"jur\"\tNUMERIC"
                + ");";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS \"fecha_creacion\"("
                + "\t\"tipo\"\tTEXT,"
                + "\t\"fechaSubida\"\tTEXT,"
                + "\t\"fechaliq\"\tTEXT,"
                + "\t\"descripcion\"\tTEXT"
                + ");";
        stmt.execute(sql);

    }

    public void importarExcelCredito(Excel credito) throws Exception, com.aspose.cells.CellsException {

        credito.abrir();
        Iterator<Row> filas = credito.getHoja().iterator();
        Iterator<Cell> celdas;
        Row fila;
        Cell celda = null;

        if (credito.isCorrect(filas)) {
            while (filas.hasNext()) {
                fila = filas.next();
                if (fila.getRowNum() != Excel.getPRIMER_FILA()) {
                    celdas = fila.cellIterator();
                    sql = "INSERT INTO credito ("
                            + "saf, nombre_saf, programa, subPrograma, proyecto, actividad, obra, "
                            + "descripcion_programa, descripcion_objeto_gasto, inciso, principal, parcial, subParcial, fuente, "
                            + "descripcion_fuente, finalidad_y_funcion, credito_original, vigente, preventivo, comprometido, "
                            + "ordenado, pagado, reserva, credito_disponible, disponible_ordenado, porcentaje_ejec, porcentaje_ejec_ordenado) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    pstmt = conection.prepareStatement(sql);
                    obtenerValorCeldas(celdas, celda, credito);
                    pstmt.executeUpdate();
                }
            }
            cargarFechaCredito(credito);
            System.err.println("ARCHIVO CARGADO CORRECTAMENTE");
        } else {
            System.err.println("EL ARCHIVO ES INCORRECTO");
        }
        credito.cerrar();

    }

    public void importarLiresto(CsvLiresto liresto) throws IOException, SQLException, NumberFormatException {

        boolean esPrimerRegistro = true;

        //FALTA IMPLEMENTAR
        if (liresto.isCorrect()) {
            for (String[] arrays : liresto.getR()) {
                //actuamente no tiene condicion de seteo
                sql = "INSERT INTO liresto (id_liresto, clave_saf, programa, subPrograma, proyecto, actividad, obra, fuente, inciso, principal, parcial, subParcial, benef, importe, fecha1, fecha2, extra) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                pstmt = conection.prepareStatement(sql);

                obtenerValor_LirestoCvc(liresto, arrays, esPrimerRegistro);
                esPrimerRegistro = false;
                pstmt.executeUpdate();
            }
            cargarFechaLiresto(liresto);
            harcore();

        } else {
            System.err.println("EL ARCHIVO ES INCORRECTO");
        }
    }

    private void obtenerValorCeldas(Iterator<Cell> celdas, Cell celda, Excel credito) throws SQLException {
        int numero_columna = 1;

        while (celdas.hasNext()) {
            celda = celdas.next();
            int tipoDatoCelda = credito.obtenerTipoDatoCelda(numero_columna, celda);

            if (tipoDatoCelda == 0) {
                // SETEA ENTERO
                pstmt.setInt(numero_columna, (int) celda.getNumericCellValue());
            } else if (tipoDatoCelda == 1) {
                // SETEA DOUBLE
                // System.out.println("VALOR: "+celda.getNumericCellValue());

                pstmt.setDouble(numero_columna, (double) celda.getNumericCellValue());
            } else if (tipoDatoCelda == 2) {
                // SETEA STRING A ENTERO
                String cadena = celda.getStringCellValue();
                int cadenaComoEntero = Integer.parseInt(cadena.replaceAll("\\.", ""));
                pstmt.setInt(numero_columna, cadenaComoEntero);
            } else if (tipoDatoCelda == 3) {
                String line = celda.getStringCellValue();
                String letra = line.substring(0, 1);
                int numero = Integer.parseInt(line.substring(1, line.length()));

                if ("A".equals(letra)) {
                    // Es actividad "A" + numero que la acompaña
                    pstmt.setInt(numero_columna, numero);
                    numero_columna++;
                    pstmt.setInt(numero_columna, 0);
                } else {
                    // Es OBRA "O" + numero que la acompaña
                    pstmt.setInt(numero_columna, 0);
                    numero_columna++;
                    pstmt.setInt(numero_columna, numero);
                }
            } else if (tipoDatoCelda == 4) {
                pstmt.setString(numero_columna, celda.getStringCellValue());
            }

            numero_columna++;
        }
    }

    public void importarEmpresa(CsvEmpresa empresa) throws SQLException {

        if (empresa.isCorrect()) {
            for (String[] arrays : empresa.getR()) {
                if (empresa.consultarSeteoCsv(arrays)) {
                    sql = "INSERT INTO empresa (cod_emp, saf, descripcion, clave_saf, cod_tipo, excep, safcentrar, cuit, calle, nro, codsicore, empresue, activo, caracter, jur) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    pstmt = conection.prepareStatement(sql);
                    obtenerValor_EmpresaCvc(empresa, arrays);
                    System.out.println(sql);

                    pstmt.executeUpdate();
                }
            }
        } else {
            System.err.println("EL ARCHIVO ES INCORRECTO");
        }
    }

    private void obtenerValor_EmpresaCvc(CsvEmpresa empresa, String[] arrays) throws SQLException {
        int index = 0;
        for (String array : arrays) {
            boolean found = Arrays.stream(empresa.getCOLUMNA_ENTEROS()).boxed().collect(Collectors.toSet()).contains(index);
            if (found) {
                pstmt.setInt(index + 1, empresa.obtenerValorNumerico(array));
            } else {
                pstmt.setString(index + 1, array);
            }
            index++;
        }
    }

    private void obtenerValor_LirestoCvc(CsvLiresto liresto, String[] arrays, boolean esPrimerRegistro) throws SQLException, NumberFormatException {
        int index = 1;
        int columna_fecha1 = 14;
        int columna_fecha2 = 15;
        int columna_extra = 16;

        for (String array : arrays) {
            switch (liresto.obtenerTipoDatoColumna(index)) {
                case 1:
                    // SETEA STRING A ENTERO
                    int cadenaComoEntero = Integer.parseInt(array.replaceAll("\\s+", ""));
                    pstmt.setInt(index + 1, liresto.obtenerValorNumerico(cadenaComoEntero, index));
                    break;
                case 2:
                    // SETEA STRING A DOUBLE
                    String cadenaSinEspacios = array.replaceAll("\\s+", "");
                    String cadenaSinEspacios_y_SinComas = cadenaSinEspacios.replaceAll("[,\\.]", "");

                    double cadenaComoDouble = Double.parseDouble(cadenaSinEspacios_y_SinComas);

                    if (cadenaSinEspacios.equals(cadenaSinEspacios_y_SinComas)) {
                        pstmt.setDouble(index + 1, cadenaComoDouble);
                    } else {
                        pstmt.setDouble(index + 1, cadenaComoDouble / 100);
                    }
                    break;
                case 3:
                    // SETEA STRING
                    pstmt.setString(index + 1, array);
                    break;
                case 4:
                    // CONCATENA FECHA DDMMAAAA
                    liresto.setFecha1(liresto.getFecha1() + array);
                    break;
                case 5:
                    // CONCATENA FECHA DDMMAAAA
                    if (esPrimerRegistro) {
                        liresto.setPrimerFechaLiquidacion(array.replaceAll("\\s+", ""));
                    }
                    liresto.setFecha2(liresto.getFecha2() + array);
                    break;
                case 6:
                    // COLUMNA EXTRA
                    pstmt.setString(columna_extra + 1, array);
                    break;
            }
            index++;
        }

        // Limpia las fechas quitando los espacios en blanco
        int fechaComoEntero = Integer.parseInt(liresto.getFecha1().replaceAll("\\s+", ""));
        pstmt.setInt(columna_fecha1 + 1, fechaComoEntero);

        fechaComoEntero = Integer.parseInt(liresto.getFecha2().replaceAll("\\s+", ""));
        pstmt.setInt(columna_fecha2 + 1, fechaComoEntero);

        liresto.setearFechas();
    }

    private void harcore() throws SQLException {

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("./config/sql_hard.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;

            while ((linea = br.readLine()) != null) {
                stmt.execute(linea);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void exportarExcel(File archivo, int nivel, String sql) throws SQLException, Exception {
        String ruta_extension;
        
        //Obtengo el ResultSet de la consulta SQL
        System.out.println(sql);
        ResultSet rs = consulta(sql);
        ruta_extension = archivo.getPath();
        if(!archivo.getPath().contains("xls")){
         ruta_extension = ruta_extension + ".xls";
        }
        
        switch (nivel) {
            case 1:
                ReporteExcelInciso reporte_inciso = new ReporteExcelInciso(ruta_extension, rs);
                reporte_inciso.exportarExcel();
                break;
            case 2:
                ReporteExcelPrincipal reporte_principal = new ReporteExcelPrincipal(ruta_extension, rs);
                reporte_principal.exportarExcel();
                break;
            case 3:
                ReporteExcelParcial reporte_parcial = new ReporteExcelParcial(ruta_extension, rs);
                reporte_parcial.exportarExcel();
                break;
            case 4:
                ReporteExcelSubparcial reporte_subparcial = new ReporteExcelSubparcial(ruta_extension, rs);
                reporte_subparcial.exportarExcel();
                break;
            case 5:
                ReporteExcelSinPartida reporte_sinPartida = new ReporteExcelSinPartida(ruta_extension, rs);
                reporte_sinPartida.exportarExcel();
                break;
            case 6:
                ReporteExcelFinFuncion reporte_finFuncion = new ReporteExcelFinFuncion(ruta_extension, rs);
                reporte_finFuncion.exportarExcel();
                break;
            case 7:
                ReporteExcelSinPrograma reporte_sinPrograma = new ReporteExcelSinPrograma(ruta_extension, rs);
                reporte_sinPrograma.exportarExcel();
                break;
        }
    }

    /**
     * Exporta saldos negativos a nivel inciso
     *
     * @param archivo
     * @param consulta
     * @throws SQLException
     */
    public void exportarSaldoNegativo(File archivo, String consulta) throws SQLException {

        //Obtengo el ResultSet de la consulta SQL
        ResultSet rs = consulta(consulta);

        String ruta_extension = archivo.getPath() + ".xls";
        ReporteExcelInciso reporte_inciso = new ReporteExcelInciso(ruta_extension, rs);
        reporte_inciso.exportarExcel();
    }

    public void exportarSinPartida(File archivo, String consulta) throws SQLException {
        //Obtengo el ResultSet de la consulta SQL
        ResultSet rs = consulta(consulta);

        String ruta_extension = archivo.getPath() + ".xls";
        ReporteExcelSinPartida reporte = new ReporteExcelSinPartida(ruta_extension, rs);
        reporte.exportarExcel();
    }

    public void exportarPartida151(File archivo, String consulta) throws SQLException {
        //Obtengo el ResultSet de la consulta SQL
        ResultSet rs = consulta(consulta);
        System.out.println(sql);
        String ruta_extension = archivo.getPath() + ".xls";
        ReporteExcelSubparcial reporte = new ReporteExcelSubparcial(ruta_extension, rs);
        reporte.exportarExcel();
    }

    private void cargarFechaCredito(Excel credito) throws SQLException {
        sql = "INSERT INTO fecha_creacion(tipo, fechaSubida, fechaliq, descripcion) VALUES (?,?,?,?)";

        pstmt = conection.prepareStatement(sql);

        pstmt.setString(1, "EXCEL");
        pstmt.setString(2, credito.getFechaComoCadena());
        pstmt.setString(3, "");
        pstmt.setString(4, credito.getDescripcion());

        pstmt.executeUpdate();
    }

    private void cargarFechaLiresto(CsvLiresto liresto) throws SQLException {
        sql = "INSERT INTO fecha_creacion(tipo, fechaSubida, fechaliq, descripcion) VALUES (?,?,?,?)";

        pstmt = conection.prepareStatement(sql);

        pstmt.setString(1, "LIRESTO");
        pstmt.setString(2, liresto.getFechaComoCadena());
        pstmt.setString(3, liresto.getPrimerFechaLiquidacion());
        pstmt.setString(4, liresto.getDescripcion());
        pstmt.executeUpdate();
    }

}
