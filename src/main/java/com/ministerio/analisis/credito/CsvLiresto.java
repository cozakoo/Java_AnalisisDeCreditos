package com.ministerio.analisis.credito;

import Utiles.Mensajes;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Perimite procesar el Archivo LiResto en formato CSV
 *
 * @author DGC
 */
public final class CsvLiresto extends Csv {

    private final int LINEA_INICIO = 0;

    // Definimos las constantes de nuestro liresto
    private final int COLUMNAS_STRNG_CLAVE = 1;
    private final int COLUMNAS_STRNG_EXTRA = 19;
    private final int COLUMNA_DOUBLE = 13;
    private final int[] COLUMNA_FECHA_1 = {14, 15};
    private final int[] COLUMNA_FECHA_2 = {16, 17, 18};
    private final int COLUMNA_PARCIAL = 10;
    private final int COLUMNA_SUBPARCIAL = 11;

    private String fecha1;
    private String fecha2;
    private String descripcion;
    private DateFormat dateFormat;
    private String fechaComoCadena;

    private String primerFechaLiquidacion = "";

    public CsvLiresto(String ruta, String descripcion) throws IOException, CsvException {
        super(ruta);
        this.setearFechas();
        this.leerArchivo();

        this.descripcion = descripcion;
        this.dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm aaa");
        this.fechaComoCadena = dateFormat.format(new Date());

    }

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    @Override
    public void leerArchivo() throws IOException, CsvException {
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(this.getRuta()))
                .withCSVParser(this.getCsvParser()) // custom CSV parser
                .withSkipLines(this.LINEA_INICIO) // skip the first line, header info
                .build()) {
            this.setR(reader.readAll());

        }
    }

    /**
     * Se obtiene el tipo de dato de mi columna
     *
     * @param index
     * @return
     */
    public int obtenerTipoDatoColumna(int index) {

        if (index == this.COLUMNAS_STRNG_CLAVE) {
            return 3;
        } else if (index == this.COLUMNA_DOUBLE) {
            return 2;
        } else if (Arrays.stream(this.COLUMNA_FECHA_1).boxed().collect(Collectors.toSet()).contains(index)) {
            return 4;
        } else if (Arrays.stream(this.COLUMNA_FECHA_2).boxed().collect(Collectors.toSet()).contains(index)) {
            return 5;
        } else if (index == this.COLUMNAS_STRNG_EXTRA) {
            return 6;
        } else {
            return 1; // mi columna es un entero
        }

    }

    public int obtenerValorNumerico(int cadenaComoEntero, int index) {

        if (((index == this.COLUMNA_PARCIAL) && (cadenaComoEntero == 0)) || ((index == this.COLUMNA_SUBPARCIAL) && (cadenaComoEntero == 0))) {
            return 1;
        } else {
            return cadenaComoEntero;
        }
    }

    public void setearFechas() {
        this.fecha1 = "";
        this.fecha2 = "";
    }

    public boolean isCorrect() {
        // Verificar el número de columnas
        int numColumnas = this.getR().get(0).length;

        if (numColumnas != 19) {
            return false;
        }
        return true;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaComoCadena() {
        return fechaComoCadena;
    }

    public String obtenerPrimerFechaLiquidacion() {
        return "ALSADO";
    }

    public String getPrimerFechaLiquidacion() {
        return primerFechaLiquidacion;
    }

    public void setPrimerFechaLiquidacion(String array) {

        String aux = "";
        if (array.length() == 1) {
            aux = "0" + array;
        } else {
            aux = array;
        }
        this.primerFechaLiquidacion = this.primerFechaLiquidacion + "/" + aux;
    }
}
