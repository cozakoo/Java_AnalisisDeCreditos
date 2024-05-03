package com.ministerio.analisis.credito;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Permite procesar el archivo de empresas para poder cargarlo posteriormente a
 * la BD
 *
 * @author Martin Arcos Vargas
 */
public final class CsvEmpresa extends Csv {

    private final int[] COLUMNA_ENTEROS = {0, 1, 4, 5, 6, 9, 10, 12, 13, 14};
    private final int LINEA_INICIO = 1;
    private final String ACTIVO = "1";

    public CsvEmpresa(String ruta) throws IOException, CsvException {
        super(ruta);
        leerArchivo();
    }

    public int getLINEA_INICIO() {
        return LINEA_INICIO;
    }

    public int[] getCOLUMNA_ENTEROS() {
        return COLUMNA_ENTEROS;
    }

    @Override
    public void leerArchivo() throws IOException, CsvException {
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(this.getRuta()))
                .withCSVParser(this.getCsvParser()) // custom CSV parser
                .withSkipLines(this.LINEA_INICIO) // skip the first line, header info
                .build()) {
            this.setR(reader.readAll());
            System.out.println("");
        }
    }

    /**
     * Consulta para saber si hay que setear la celda
     *
     * @param arrays
     * @return
     */
    @Override
    public boolean consultarSeteoCsv(String[] arrays) {
        return ((!this.getNULO().equals(arrays[3]) && (this.ACTIVO.equals(arrays[12]))));
    }
    
    /**
     * Obtiene el valor numerico de mi celda
     * @param array. Es mi fila
     * @return 
     */

    public int obtenerValorNumerico(String array) {
        if (!array.equals(this.getNULO())) {
            return Integer.parseInt(array);
        }
        return 0;
    }
    
    /**
     * FALTA: Verificar si es el archivo correcto
     * @return 
     */
    public boolean isCorrect() {
        
        
        return true;
    }
}
