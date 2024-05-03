package com.ministerio.analisis.credito;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Row;

// REPORTE A NIVEL PRINCIPAL - NIVEL 2
public class ReporteExcelPrincipal extends ReporteExcelInciso {

    //NOMBRE DE LAS COLUMNAS EN MI EXCEL
    final String COLUMNA_PRINCIPAL = "PRINC";

    private int[] columnasaldos = {9, 10, 11};
    
    public ReporteExcelPrincipal(String ruta, ResultSet resultSet) {
        super(ruta, resultSet);
    }

    @Override
    public void exportarExcel() throws SQLException {
        generarCabezera();
        generarObjetos();
        super.setearObjetos();
        super.generarAncho(columnasaldos);
        super.generarDocumento();
    }

    private void generarCabezera() throws SQLException {

        int columnCount = getResultSet().getMetaData().getColumnCount();
        Row row = getSheet().createRow(0);

        for (int i = 1; i <= columnCount; i++) {
            getDatos().put("1", new Object[]{
                getCOLUMNA_SAF(),
                getCOLUMNA_PROGRAMA(),
                getCOLUMNA_SUBPROGRAMA(),
                getCOLUMNA_PROYECTO(),
                getCOLUMNA_ACTIVIDAD(),
                getCOLUMNA_OBRA(),
                getCOLUMNA_FUENTE(),
                getCOLUMNA_INCISO(),
                this.COLUMNA_PRINCIPAL,
                getCOLUMNA_DISPONIBLE(),
                getCOLUMNA_LIBERAMIENTO(),
                getCOLUMNA_SALDO()
            });
        }
    }

    private void generarObjetos() throws SQLException {

        int columnCount = getResultSet().getMetaData().getColumnCount();

        while (getResultSet().next()) {
            getDatos().put(String.valueOf(getResultSet().getRow()) + 1, new Object[]{
                Integer.valueOf(getResultSet().getString(1)),
                Integer.valueOf(getResultSet().getString(2)),
                Integer.valueOf(getResultSet().getString(3)),
                Integer.valueOf(getResultSet().getString(4)),
                Integer.valueOf(getResultSet().getString(5)),
                Integer.valueOf(getResultSet().getString(6)),
                Integer.valueOf(getResultSet().getString(7)),
                Integer.valueOf(getResultSet().getString(8)),
                Integer.valueOf(getResultSet().getString(9)),
                getResultSet().getDouble(10),
                getResultSet().getDouble(11),
                getResultSet().getDouble(12)
            });
        }
    }

    public String getCOLUMNA_PRINCIPAL() {
        return COLUMNA_PRINCIPAL;
    }

}
