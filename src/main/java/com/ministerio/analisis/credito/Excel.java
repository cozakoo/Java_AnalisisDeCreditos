package com.ministerio.analisis.credito;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Row;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Esta clase esta destinada para el archivo Excel y poder manejarla Proporciona
 * información para poder procesarlo.
 *
 * @author Martin Arcos Vargas.
 */
public class Excel {

    /**
     * Constantes. COLUMNA_ACTIVIDAD: Descomponer la columna Actividad/Obra del
     * excel en dos columnas por separado; PRIMER_FILA: Utilizada para poder
     * saltear la fila y no poder procesarla; NOMBRE_COLUMNAS: nombres de las
     * colunmas para poder verificar que sea el archivo correcto
     */
    private static final int COLUMNA_ACTIVIDAD = 6;
    private static final int PRIMER_FILA = 0;
    int[] COLUMNA_ENTEROS = {1, 3, 4, 5, 10, 11, 12, 13, 14, 16};
    private static final String[] NOMBRE_COLUMNAS = {
        "SAF",
        "Nombre SAF",
        "Prog",
        "Subp",
        "Proy",
        "Act/Obra",
        "Descripción",
        "Descripcion",
        "INC",
        "PRINC",
        "PARC",
        "SUBPARCIAL",
        "Fte",
        "Descripcion",
        "Finalidad y Funcion",
        "Cred. Original",
        "Vigente",
        "Prev.",
        "Comprometido",
        "Ordenado",
        "Pagado",
        "Reserva",
        "Disponible",
        "Disponible Ordenado",
        "% Ejec",
        "%Ejec Ordenado"
    };

    // Atributos
    private String ruta = null;
    private FileInputStream file;
    private XSSFWorkbook libro;
    private XSSFSheet hoja;

    private String descripcion;
    private DateFormat dateFormat;
    private String fechaComoCadena;

    //constructor
    public Excel(String ruta, String descripcion) {
        this.ruta = ruta;
        this.descripcion = descripcion;
        //obtengo la fecha actual
        this.dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm aaa");
        this.fechaComoCadena = dateFormat.format(new Date());
    }

    public FileInputStream getFile() {
        return file;
    }

    public XSSFWorkbook getLibro() {
        return libro;
    }

    public XSSFSheet getHoja() {
        return hoja;
    }

    public static int getPRIMER_FILA() {
        return PRIMER_FILA;
    }

    public static int getCOLUMNA_ACTIVIDAD() {
        return COLUMNA_ACTIVIDAD;
    }

    /**
     * abrir(). Permite abrir el archivo para poder procesarlo
     *
     * @throws java.io.FileNotFoundException
     */
    public void abrir() throws FileNotFoundException, IOException, Exception {
        limpiar();
        this.file = new FileInputStream(this.ruta);
        this.libro = new XSSFWorkbook(file);
        //seleccionamos la primera hoja
        this.hoja = libro.getSheetAt(0);
        //Obtenemos todas las filas de esa hoja

    }

    /**
     * obtenerTipoDatoCelda.Obtiene el tipo de dato de la celda del excel
     *
     * @param numero_columna -> el numero de la columna del la tabla que estoy
     * evaluando
     * @param celda -> la celda que estoy evaluando para tener el dato
     * @return 0 -> Columna de enteros y esta contenido en el arreglo. Se
     * seteara como entero; 1 -> Columna de entero que hay que setear como un
     * double; 2 -> Columna de string que hay que transformar a un entero; 3 ->
     * Columna de Actividad/obra que hay que descomponer; 4 -> columna de string
     * que se seteara como string; -1 -> error
     *
     */
    public int obtenerTipoDatoCelda(int numero_columna, Cell celda) {
        boolean found = Arrays.stream(COLUMNA_ENTEROS)
                .boxed()
                .collect(Collectors.toSet())
                .contains(numero_columna);

        switch (celda.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return found ? 0 : 1;

            case Cell.CELL_TYPE_STRING:
                if (found) {
                    return 2;
                } else if (numero_columna == COLUMNA_ACTIVIDAD) {
                    return 3;
                } else {
                    return 4;
                }

            default:
                return -1;
        }
    }

    /**
     * isCoorect. Proporciona informacion para validar si es el excel correcto
     *
     * @param filas son todas las filas contenidas en el excel.
     * @return verdadero si los nombres de las columnas del excel coinciden con
     * el arreglo de NOMBRE_COLUMNAS.
     *
     */
    boolean isCorrect(Iterator<Row> filas) {
        //obtenemos la primer fila
        Row fila = filas.next();
        Iterator<Cell> celdas;
        celdas = fila.cellIterator();
        Cell celda = null;
        while (celdas.hasNext()) {
            celda = celdas.next();
            if (!Arrays.asList(NOMBRE_COLUMNAS).contains(celda.getStringCellValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * limpiar(). Elimina las filas y columnas que no contengan nada
     */
    private void limpiar() throws Exception {

        Workbook workbook = new Workbook(this.ruta);
        // Obtenga la colección de hojas de trabajo en la hoja de cálculo.
        WorksheetCollection sheets = workbook.getWorksheets();
        // Obtenga la primera hoja de trabajo de WorksheetCollection por índice.
        Worksheet sheet = sheets.get(0);
        // Elimina las columnas en blanco.
        sheet.getCells().deleteBlankColumns();
        // Guarde el archivo de Excel actualizado.
        workbook.save(this.ruta);
    }

    /**
     * cerar().Cierra el archivo
     *
     * @throws java.io.IOException
     */
    public void cerrar() throws IOException {
        this.file.close();
        this.libro.close();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaComoCadena() {
        return fechaComoCadena;
    }

}
