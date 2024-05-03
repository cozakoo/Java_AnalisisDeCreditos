package com.ministerio.analisis.credito;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Row;

public class ReporteExcelFinFuncion extends ReporteExcelInciso {

    //NOMBRE DE LAS COLUMNAS EN MI EXCEL
    final String COLUMNA_FINALIDAD_FUNCION = "Fin. y fun.";

    private int[] columnasaldos = {4, 5, 6};

    public ReporteExcelFinFuncion(String ruta, ResultSet resultSet) {
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
                getCOLUMNA_FUENTE(),
                getCOLUMNA_PROGRAMA(),
                this.COLUMNA_FINALIDAD_FUNCION,
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
                getResultSet().getDouble(5),
                getResultSet().getDouble(6),
                getResultSet().getDouble(7)
            });
        }
    }

}
