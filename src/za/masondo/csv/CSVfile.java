package za.masondo.csv;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class CSVfile extends FileConverter {

	public CSVfile(Path path) throws IOException {
		super(path);
	}

	public CSVfile(List<String> content) {
		super(content);
	}

	public CSVfile(HttpServletRequest request, String partName) throws IOException, ServletException {
		super(request.getPart(partName).getInputStream());
	}

	public ExcelFile convertToExcel() {
		return convertToXLS();
	}

	public ExcelFile convertToXLS() {
		return new ExcelFile(content, ExcelFile.XLS_FORMAT);
	}

	public ExcelFile convertToXLSX() {
		return new ExcelFile(content, ExcelFile.XLS_FORMAT);
	}

	public PDFfile convertToPDF() {
		return new PDFfile(content);
	}

	@Override
	public void writeToStream(OutputStream out) throws IOException {
		for (String line : content) {
			out.write(line.getBytes());
			out.write('\n');
		}
	}
}
