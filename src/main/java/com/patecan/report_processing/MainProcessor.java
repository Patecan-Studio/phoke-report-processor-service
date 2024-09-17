package com.patecan.report_processing;


import com.patecan.report_processing.report_processor.models.report.GeneralModel;
import com.patecan.report_processing.report_processor.models.report.GeneralModelList;
import com.patecan.report_processing.report_processor.models.report.row_cell.CellAttribute;
import com.patecan.report_processing.report_processor.models.report.row_cell.ReportRow;
import com.patecan.report_processing.report_processor.service.ReportToDatabaseProcessor;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class MainProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MainProcessor.class);

    @Autowired
    ReportRow reportRow;

    private final ReportToDatabaseProcessor reportToDatabaseProcessor;

    public MainProcessor(ReportToDatabaseProcessor reportToDatabaseProcessor){
        this.reportToDatabaseProcessor = reportToDatabaseProcessor;
    }



    public void readExcelFile() {
        GeneralModelList generalModelList = new GeneralModelList();
        Workbook workbook = null;
        try {

            workbook = WorkbookFactory.create(new File("/Volumes/PatecanDisk/Important_Projects/phe_co/report/file_xuat_theo_danh_sach_mat_hang_2024-08-31_1725100358.xls"));

            // Retrieving the number of sheets in the Workbook
            logger.info("Number of sheets: " + workbook.getNumberOfSheets());

            workbook.forEach(sheet -> {
                logger.info("Title of sheet => " + sheet.getSheetName());
                int count = 0;

                HashMap<Integer, List<Integer>> categoriesLocationMap = new HashMap<Integer, List<Integer>>();
                HashMap<Integer, Map<String, Queue<String>>> allAttributesMap = new HashMap<Integer, Map<String, Queue<String>>>();
                Map<Integer, CellAttribute> CellAttributeLocationMap = new HashMap<Integer, CellAttribute>();
                ArrayList<String> categoryName = new ArrayList<>();


                for (Row row : sheet) {
                    if (row.getRowNum() == reportRow.getHeadingCategoryRow()) {
                        int startIndex = 0;
                        for (Cell cell : row) {
                            if (cell.getColumnIndex() >= startIndex) {
                                Cell dataCell = row.getCell(cell.getColumnIndex());

                                if (dataCell.getStringCellValue().isBlank()) {
                                    startIndex++;
                                } else {
                                    CellRangeAddress mergedCellAddresses = getMergedRegionForCell(dataCell);
                                    if (mergedCellAddresses != null) {
                                        categoriesLocationMap.put(count, List.of(startIndex, mergedCellAddresses.getLastColumn()));
                                        startIndex = mergedCellAddresses.getLastColumn() + 1;
                                        count++;
                                    }
                                }

                            }
                        }

                        categoriesLocationMap.forEach((key, value) -> {
                            String categoryNameGetFromCell = row.getCell(value.get(0)).getStringCellValue();

                            allAttributesMap.put(key, Map.of(categoryNameGetFromCell, new LinkedList<>()));
                            categoryName.add(categoryNameGetFromCell);
                        });

                    }

                    if (row.getRowNum() == reportRow.getAttributeRow()) {

                        int categoryIndex = 0;
                        int totalCategories = allAttributesMap.size();
                        while (categoryIndex < totalCategories) {
                            for (int i = categoriesLocationMap.get(categoryIndex).get(0); i <= categoriesLocationMap.get(categoryIndex).get(1); i++) {
                                CellAttribute CellAttribute = new CellAttribute();
                                allAttributesMap.get(categoryIndex).get(categoryName.get(categoryIndex)).add(row.getCell(i).getStringCellValue());
                                CellAttribute.setColNum(i);
                                CellAttribute.setCategory(categoryName.get(categoryIndex));
                                CellAttribute.setColName((allAttributesMap.get(categoryIndex).get(categoryName.get(categoryIndex))).poll());

                                CellAttributeLocationMap.put(i, CellAttribute);
                                //System.out.println(CellAttribute);
                            }
                            categoryIndex++;
                        }
                        //CellAttributeList.forEach((k, v) -> System.out.println(k.toString() + " => " + v.toString()));
                    }


                    if (row.getRowNum() >= reportRow.getBeginRecordRow()) {
                        GeneralModel generalModel = new GeneralModel();
                        final int realRowNumInExcel = row.getRowNum()+1;

                        for (Cell cell : row) {
                            int cellIndex = cell.getColumnIndex();

                            if (CellAttributeLocationMap.get(cellIndex) != null) {
                                String currentCategoryName = CellAttributeLocationMap.get(cellIndex).getCategory();


                                CellAttribute currentIndex = CellAttributeLocationMap.get(cellIndex);

                                String keyStrForSearching = currentIndex.getCategory().toLowerCase().replace(" ", "").trim();
                                generalModel = GeneralModel.getObjectFromString(keyStrForSearching);

                                switch (cell.getCellType()) {
                                    case STRING:
                                        generalModel.setDetail(currentIndex.getColName(), cell.getStringCellValue());
                                        break;
                                    case NUMERIC:
                                        generalModel.setDetail(currentIndex.getColName(), cell.getNumericCellValue());
                                        break;
                                    case BLANK:
                                        generalModel.setDetail(currentIndex.getColName(), "");
                                    case _NONE:
                                        generalModel.setDetail(currentIndex.getColName(), null);
                                        break;
                                }

                                if(generalModelList.getGeneralModelList().get(realRowNumInExcel) == null) {
                                    Map<String,LinkedHashMap<String, Object>> newMap = new HashMap<>();
                                    newMap.put(currentCategoryName, new LinkedHashMap<String, Object>());
                                    generalModelList.getGeneralModelList().put(realRowNumInExcel, newMap);
                                } else
                                    generalModelList.getGeneralModelList().get(realRowNumInExcel).computeIfAbsent(currentCategoryName, k -> new LinkedHashMap<String, Object>());

                                String firstKey = generalModel.getDetails().keySet().iterator().next();
                                generalModelList.getGeneralModelList().get(realRowNumInExcel).get(currentCategoryName).put(firstKey, generalModel.getDetails().get(firstKey));
                            }
                        }
                    }

                }

                for (Row row : sheet) {
                    if(row.getRowNum()> reportRow.getAttributeRow())
                        reportToDatabaseProcessor.processDataFromReportToDatabase(generalModelList.getGeneralModelList(), row.getRowNum()+1);

                }

//                tempCategories.forEach((key, value) -> {
//                    System.out.println(key + " => " + value);
//                });

//                mainCategories.forEach((key, value) -> {
//                    System.out.println(key + " => " + value);
//                });

//                CellAttributeList.forEach((key, value) -> {
//                    System.out.println(key + " => " + value);
//                });





            });

        } catch (EncryptedDocumentException | IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (workbook != null)
                    workbook.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    public static CellRangeAddress getMergedRegionForCell(Cell c) {
        Sheet s = c.getRow().getSheet();
        for (CellRangeAddress mergedRegion : s.getMergedRegions()) {
            if (mergedRegion.isInRange(c.getRowIndex(), c.getColumnIndex())) {
                return mergedRegion;
            }
        }
        // Not in any
        return null;
    }




}
