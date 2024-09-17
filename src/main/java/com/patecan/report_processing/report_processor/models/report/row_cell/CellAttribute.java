package com.patecan.report_processing.report_processor.models.report.row_cell;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CellAttribute {
    int colNum;
    String category;
    String colName;


    public CellAttribute(int colNum, String category, String colName) {
        this.colNum = colNum;
        this.category = category;
        this.colName = colName;
    }

    public CellAttribute() {
    }

    @Override
    public String toString() {
        return "ColNum: " + colNum + ", Category: " + category + ", ColName: " + colName;
    }
}
