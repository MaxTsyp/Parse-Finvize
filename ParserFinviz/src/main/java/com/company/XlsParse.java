package com.company;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class XlsParse {
    static String filename = "C:\\Users\\Max\\Desktop\\NewExcelFile.xls" ;

    static HSSFWorkbook workbook = new HSSFWorkbook();
    public  static HSSFSheet sheet = workbook.createSheet("FirstSheet");



    public void CreatRow(String row) throws IOException {
        int i = 0;


        HSSFRow row1 = sheet.createRow((short)i++);
        row1.createCell(0).setCellValue(row);


        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("Your excel file has been generated!");
    }
}
