package za.masondo.csv;

import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFfile extends FileConverter{

	private static final Logger LOGGER = Logger.getLogger(Class.class.getName());
	
	public PDFfile(List<String> content) {
		super(content);
	}
	

	public PDFfile(CSVfile csvFile) {
		super(csvFile);
	}


	@Override
	public void writeToStream(OutputStream out) {
		Document document = new Document();
		PdfPTable table = new PdfPTable(content.get(0).split(",").length);

		for (String row : content) {
			if (!row.trim().isEmpty()) {
				for (String col : row.split(",")) {
					table.addCell(col);
				}
			}
		}

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);
			document.close();
		} catch (DocumentException e) {
			LOGGER.log(Level.SEVERE, "context", e);
		}
	}

}
