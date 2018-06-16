package za.masondo.csv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> fileContent = new CSVfile(request, "file").getContent();

		String outputType = request.getParameter("reportFormat");
		FileConverter file;

		if (outputType.equals("xls")) {
			file = new ExcelFile(fileContent, ExcelFile.XLS_FORMAT);
			response.setContentType("application/vnd.ms-excel");
		} else if (outputType.equals("xlsx")) {
			file = new ExcelFile(fileContent, ExcelFile.XLSX_FORMAT);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		} else if (outputType.equals("pdf")) {
			file = new PDFfile(fileContent);
			response.setContentType("application/pdf");
		} else {
			file = new CSVfile(fileContent);
			response.setContentType("application/octet-stream");
		}
		response.setHeader("Content-Disposition", "inline; filename=\"file." + outputType + "\"");
		file.writeToStream(response.getOutputStream());

	}
}
