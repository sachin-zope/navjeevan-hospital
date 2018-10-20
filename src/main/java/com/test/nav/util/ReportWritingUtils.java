
package com.test.nav.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.nav.model.DTOIndoorRegister;

public class ReportWritingUtils {

	public void write(String filePath, List<DTOIndoorRegister> indoorList) throws IOException {
		Workbook workbook = new XSSFWorkbook();

		CreationHelper createHelper = workbook.getCreationHelper();

		Sheet sheet = workbook.createSheet("Indoor Register");
		BorderStyle thinBorder = BorderStyle.THIN;

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setColor(IndexedColors.BLACK.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setBorderBottom(thinBorder);
		headerCellStyle.setBorderTop(thinBorder);
		headerCellStyle.setBorderRight(thinBorder);
		headerCellStyle.setBorderLeft(thinBorder);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		String[] columns = { "IPD No.", "DOA", "DOD", "Name", "Diagnosis", "Treatment", "Fees" };

		// Create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		
		Font dataFont = workbook.createFont();
		dataFont.setBold(false);
		dataFont.setFontHeightInPoints((short) 10);
		dataFont.setColor(IndexedColors.BLACK.getIndex());

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
		dateCellStyle.setFont(dataFont);
		dateCellStyle.setAlignment(HorizontalAlignment.CENTER);
		dateCellStyle.setVerticalAlignment(VerticalAlignment.TOP);
		dateCellStyle.setBorderBottom(thinBorder);
		dateCellStyle.setBorderTop(thinBorder);
		dateCellStyle.setBorderRight(thinBorder);
		dateCellStyle.setBorderLeft(thinBorder);

		CellStyle leftTopStyle = workbook.createCellStyle();
		leftTopStyle.setFont(dataFont);
		leftTopStyle.setAlignment(HorizontalAlignment.LEFT);
		leftTopStyle.setVerticalAlignment(VerticalAlignment.TOP);
		leftTopStyle.setBorderBottom(thinBorder);
		leftTopStyle.setBorderTop(thinBorder);
		leftTopStyle.setBorderRight(thinBorder);
		leftTopStyle.setBorderLeft(thinBorder);

		CellStyle feeStyle = workbook.createCellStyle();
		feeStyle.setFont(dataFont);
		feeStyle.setAlignment(HorizontalAlignment.RIGHT);
		feeStyle.setVerticalAlignment(VerticalAlignment.TOP);
		feeStyle.setBorderBottom(thinBorder);
		feeStyle.setBorderTop(thinBorder);
		feeStyle.setBorderRight(thinBorder);
		feeStyle.setBorderLeft(thinBorder);

		// Create Other rows and cells with employees data
		int rowNum = 1;
		for (DTOIndoorRegister ir : indoorList) {
			Row row = sheet.createRow(rowNum++);

			Cell ipdNoCell = row.createCell(0);
			ipdNoCell.setCellValue(ir.getIpdNo());
			ipdNoCell.setCellStyle(leftTopStyle);

			Cell doaCell = row.createCell(1);
			doaCell.setCellValue(ir.getAdmitDate());
			doaCell.setCellStyle(dateCellStyle);

			Cell dodCell = row.createCell(2);
			dodCell.setCellValue(ir.getDischargeDate());
			dodCell.setCellStyle(dateCellStyle);

			Cell nameCell = row.createCell(3);
			nameCell.setCellValue(ir.getPatientName());
			nameCell.setCellStyle(leftTopStyle);

			Cell diagnosisCell = row.createCell(4);
			diagnosisCell.setCellValue(ir.getDiagnosis());
			diagnosisCell.setCellStyle(leftTopStyle);

			Cell treatmentCell = row.createCell(5);
			treatmentCell.setCellValue(ir.getTreatment());
			treatmentCell.setCellStyle(leftTopStyle);

			Cell feeCell = row.createCell(6);
			feeCell.setCellValue(ir.getFees());
			feeCell.setCellStyle(feeStyle);
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(filePath);
		workbook.write(fileOut);
		fileOut.close();

		// Closing the workbook
		workbook.close();
	}

}
