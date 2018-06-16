package za.masondo.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Getter;

public class ExcelFile extends FileConverter {

	public static final boolean XLS_FORMAT = false;
	public static final boolean XLSX_FORMAT = true;

	@Getter
	Workbook workbook;

	public ExcelFile(boolean useNewFormat) {
		workbook = useNewFormat ? new HSSFWorkbook() : new XSSFWorkbook();
	}

	public ExcelFile() {
		this(XLS_FORMAT);
	}

	public ExcelFile(CSVfile csv) {
		this(csv, XLS_FORMAT);
	}

	public ExcelFile(CSVfile csv, boolean useNewFormat) {
		this(csv.getContent(), useNewFormat);
	}

	public ExcelFile(List<String> content, boolean useNewFormat) {
		this(useNewFormat);
		Sheet worksheet = workbook.createSheet("Sheet 1");

		for (int r = 0; r < content.size(); r++) {
			String[] lineItems = content.get(r).split(",");
			if (lineItems != null) {
				Row row = worksheet.createRow(r);
				for (int c = 0; c < lineItems.length; c++) {
					Cell col = row.createCell(c);
					try {
						col.setCellValue((Integer) Integer.parseInt(lineItems[c]));
					} catch (NumberFormatException valueIsNotInt) {
						col.setCellValue(lineItems[c]);
					}
				}
			}
		}
	}

	@Override
	public void saveToFile(String pathToSave) throws IOException {
		File xlsFile = new File(pathToSave);
		if (xlsFile.exists())
			Files.delete(Paths.get(xlsFile.getPath()));
		if (xlsFile.createNewFile()) {
			writeToStream(new FileOutputStream(xlsFile));
		}
	}

	public void writeToStream(OutputStream out) throws IOException {
		workbook.write(out);
		workbook.close();
	}
}
