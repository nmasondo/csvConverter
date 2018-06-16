package za.masondo.csv;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class FileConverterTest {

	private static final String DEFAULT_FOLDER = "data/";
	private static final String DEFAULT_FILENAME = "test";
	private static final String DEFAULT_PATH = DEFAULT_FOLDER + DEFAULT_FILENAME;

	@Test
	public void testConvertingToALL() throws IOException {
		String CSVpath = DEFAULT_PATH + ".csv";
		String XLSpath = DEFAULT_PATH + ".xls";
		String XLSXpath = DEFAULT_PATH + ".xlsx";
		String PDFpath = DEFAULT_PATH + ".xls";

		new File(CSVpath).createNewFile();
		Files.deleteIfExists(Paths.get(XLSpath));
		Files.deleteIfExists(Paths.get(XLSXpath));
		Files.deleteIfExists(Paths.get(PDFpath));

		CSVfile csvFile = new CSVfile(Paths.get(CSVpath));

		ExcelFile xlsFile = new ExcelFile(csvFile);
		ExcelFile xlsxFile = new ExcelFile(csvFile, true);
		PDFfile pdfFile = new PDFfile(csvFile);

		xlsFile.saveToFile(XLSpath);
		xlsxFile.saveToFile(XLSXpath);
		pdfFile.saveToFile(PDFpath);

		assertTrue(new File(XLSpath).exists());
		assertTrue(new File(XLSXpath).exists());
		assertTrue(new File(PDFpath).exists());
	}

	@Test
	public void testConvertingToXLS() throws IOException {
		String CSVpath = DEFAULT_PATH + ".csv";
		String XLSpath = DEFAULT_PATH + ".xls";

		Files.deleteIfExists(Paths.get(XLSpath));

		CSVfile csvFile = new CSVfile(Paths.get(CSVpath));
		ExcelFile xlsFile = csvFile.convertToXLS();
		xlsFile.saveToFile(XLSpath);
		assertTrue(new File(XLSpath).exists());
	}

	@Test
	public void testConvertingToXLSX() throws IOException {
		String CSVpath = DEFAULT_PATH + ".csv";
		String XLSXpath = DEFAULT_PATH + ".xlsx";

		Files.deleteIfExists(Paths.get(XLSXpath));

		CSVfile csvFile = new CSVfile(Paths.get(CSVpath));
		ExcelFile xlsxFile = csvFile.convertToXLSX();
		xlsxFile.saveToFile(XLSXpath);
		assertTrue(new File(XLSXpath).exists());
	}

	@Test
	public void testConvertingToPDF() throws IOException {
		String CSVpath = DEFAULT_PATH + ".csv";
		String PDFpath = DEFAULT_PATH + ".pdf";

		Files.deleteIfExists(Paths.get(PDFpath));

		CSVfile csvFile = new CSVfile(Paths.get(CSVpath));
		PDFfile pdfFile = csvFile.convertToPDF();
		pdfFile.saveToFile(PDFpath);
		assertTrue(new File(PDFpath).exists());
	}

}
