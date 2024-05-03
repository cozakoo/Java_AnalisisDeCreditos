package com.ministerio.analisis.credito;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * Clase para darle estilo al excel exportado
 *
 * @author Martin Arcos Vargas.
 */
public class CellStyler {

    //Atributos
    private CellStyle cellstyle;
    private Font cellFont;

    public CellStyler(CellStyle cellstyle, Font cellFont) {
        this.cellstyle = cellstyle;
        this.cellFont = cellFont;
    }

    /**
     * Configura el estilo de la columna de mis nombres
     */
    public void styleCabecera() {
        //letra en negrita
        this.cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        //tipo de letra
        this.cellFont.setFontName("Arial");
        this.cellstyle.setFont(cellFont);
        //Alineacion central
        this.cellstyle.setAlignment(HorizontalAlignment.CENTER);
        //color de fondo de celda
        this.cellstyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
        this.cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public CellStyle getCellstyle() {
        return cellstyle;
    }
}