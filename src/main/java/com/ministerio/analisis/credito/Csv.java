package com.ministerio.analisis.credito;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;


/**
 * Clase abstracta para poder procesar archivos de tipo CSV
 * 
 * @author Martin Arcos Vargas
 */
public abstract class Csv {

    //Atributos
    private final Character SEPARATOR = ';';
    private final String NULO = "";
    private CSVParser csvParser;
    private CSVReader reader;
    private String ruta;
    private List<String[]> r;

    public Csv(String ruta) throws IOException, CsvException {
        this.ruta = ruta;
        this.csvParser = new CSVParserBuilder().withSeparator(this.SEPARATOR).build();
    }

    public String getRuta() {
        return ruta;
    }

    public String getNULO() {
        return NULO;
    }

    public Character getSEPARATOR() {
        return SEPARATOR;
    }

    public CSVParser getCsvParser() {
        return csvParser;
    }

    public void setCsvParser(CSVParser csvParser) {
        this.csvParser = csvParser;
    }

    public List<String[]> getR() {
        return r;
    }

    public void setR(List<String[]> r) {
        this.r = r;
    }

    public abstract void leerArchivo() throws IOException, CsvException;
    
    public boolean consultarSeteoCsv(String[] arrays) {
        return true;
    }
}